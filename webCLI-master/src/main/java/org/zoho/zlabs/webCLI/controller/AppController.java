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
import org.zoho.zlabs.webCLI.service.Filter1Service;
import org.zoho.zlabs.webCLI.service.Filter2Service;
import org.zoho.zlabs.webCLI.service.Filter3Service;
import org.zoho.zlabs.webCLI.service.Filter4Service;
import org.zoho.zlabs.webCLI.service.Filter5Service;
import org.zoho.zlabs.webCLI.service.Filter6Service;
import org.zoho.zlabs.webCLI.service.Filter7Service;
import org.zoho.zlabs.webCLI.service.Filter8Service;

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
    private Filter1Service filter1Service;
    @Autowired
    private Filter2Service filter2Service;
    @Autowired
    private Filter3Service filter3Service;
    @Autowired
    private Filter4Service filter4Service;
    @Autowired
    private Filter5Service filter5Service;
    @Autowired
    private Filter6Service filter6Service;
    @Autowired
    private Filter7Service filter7Service;
    @Autowired
    private Filter8Service filter8Service;
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

    @PostMapping("/card/filter1VideoUploadFile")
    public UploadFileResponse uploadFile2(@RequestParam("file") MultipartFile file) {
        String fileName = filter1Service.loadVideoFromPPTX(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/card/filter1VideoDownloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/card/filter1VideoDownloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile2(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = filter1Service.getVideoFromPPTX(fileName);

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

    @PostMapping("/card/filter2VideoUploadFile")
    public UploadFileResponse uploadFile3(@RequestParam("file") MultipartFile file) {
        String fileName = filter2Service.loadVideoFromPPTX(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/card/filter2VideoDownloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/card/filter2VideoDownloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile3(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = filter2Service.getVideoFromPPTX(fileName);

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
    @PostMapping("/card/filter3VideoUploadFile")
    public UploadFileResponse uploadFile4(@RequestParam("file") MultipartFile file) {
        String fileName = filter3Service.loadVideoFromPPTX(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/card/filter3VideoDownloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/card/filter3VideoDownloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile4(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = filter3Service.getVideoFromPPTX(fileName);

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
    @PostMapping("/card/filter4VideoUploadFile")
    public UploadFileResponse uploadFile5(@RequestParam("file") MultipartFile file) {
        String fileName = filter4Service.loadVideoFromPPTX(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/card/filter4VideoDownloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/card/filter4VideoDownloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile5(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = filter4Service.getVideoFromPPTX(fileName);

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
    @PostMapping("/card/filter5VideoUploadFile")
    public UploadFileResponse uploadFile6(@RequestParam("file") MultipartFile file) {
        String fileName = filter5Service.loadVideoFromPPTX(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/card/filter5VideoDownloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/card/filter5VideoDownloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile6(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = filter5Service.getVideoFromPPTX(fileName);

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
    @PostMapping("/card/filter6VideoUploadFile")
    public UploadFileResponse uploadFile7(@RequestParam("file") MultipartFile file) {
        String fileName = filter6Service.loadVideoFromPPTX(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/card/filter6VideoDownloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/card/filter6VideoDownloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile7(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = filter6Service.getVideoFromPPTX(fileName);

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
    @PostMapping("/card/filter7VideoUploadFile")
    public UploadFileResponse uploadFile8(@RequestParam("file") MultipartFile file) {
        String fileName = filter7Service.loadVideoFromPPTX(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/card/filter7VideoDownloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/card/filter7VideoDownloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile8(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = filter7Service.getVideoFromPPTX(fileName);

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
    @PostMapping("/card/filter8VideoUploadFile")
    public UploadFileResponse uploadFile9(@RequestParam("file") MultipartFile file) {
        String fileName = filter8Service.loadVideoFromPPTX(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/card/filter8VideoDownloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/card/filter8VideoDownloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile9(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = filter8Service.getVideoFromPPTX(fileName);

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
