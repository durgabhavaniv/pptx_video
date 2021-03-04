package org.zoho.zlabs.webCLI.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.zoho.zlabs.webCLI.config.ApplicationProperties;
import org.zoho.zlabs.webCLI.model.UploadFileResponse;
import org.zoho.zlabs.webCLI.service.CommandExecuter;
import org.zoho.zlabs.webCLI.service.PPTXToVideoService;
import org.zoho.zlabs.webCLI.service.FfmpegFilterService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@RestController
@Log4j2
public class AppController {

    @Autowired
    private CommandExecuter commandExecuter;
    @Autowired
    private PPTXToVideoService pptxToVideoService;
    @Autowired
    private FfmpegFilterService ffmpegFilterService;
    @Autowired
    private ApplicationProperties applicationProperties;

    @PostMapping("/api/execute")
    public ResponseEntity<String> runCommand(@RequestBody HashMap<String,String> requestData){
        String command = requestData.get("command");

        return ResponseEntity.ok().body(commandExecuter.processCommand(command));
    }

    @PostMapping("/card/pptxVideoUploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = pptxToVideoService.loadVideoFromPPTX(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/card/pptxVideoDownloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/card/pptxVideoDownloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = pptxToVideoService.getVideoFromPPTX(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/card/ffmpegVideoUploadFile")
    public UploadFileResponse uploadFile1(@RequestParam("file") MultipartFile file) {
        String fileName = ffmpegFilterService.loadVideoFromPPTX(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/card/ffmpegVideoDownloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/card/ffmpegVideoDownloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile1(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = ffmpegFilterService.getVideoFromPPTX(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
