package org.zoho.zlabs.webCLI.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.zoho.zlabs.webCLI.config.ApplicationProperties;
import org.zoho.zlabs.webCLI.model.UploadFileResponse;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Log4j2
public class Filter7Service {

    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    private ApplicationProperties applicationProperties;
    @Autowired
    private CommandExecuter commandExecuter;

    public String loadVideoFromPPTX(MultipartFile file){

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path inPath = Paths.get(applicationProperties.getFilter7VideoInDir())
                .toAbsolutePath().normalize();
        inPath = inPath.resolve(fileName);
        log.info(inPath.toString());
        fileStorageService.storeFile(inPath,file);
        commandExecuter.processCommand(applicationProperties.getFilter7VideoScriptfile()+" "+
                applicationProperties.getFilter7VideoInDir()+fileName+" "+
                applicationProperties.getFilter7VideoOutDir()+" "+
                fileName.replaceFirst("[.][^.]+$", ""));

        return fileName;
    }

    public Resource getVideoFromPPTX(String fileName) {
//        System.out.println (applicationProperties.getPptxVideoOutDir()+fileName.replaceFirst("[.][^.]+$", "/"));
        Path outPath = Paths.get(applicationProperties.getFilter7VideoOutDir()+fileName.replaceFirst("[.][^.]+$", "/"))
                .toAbsolutePath().normalize();
        outPath = outPath.resolve("output.mp4");
        log.info(outPath.toString());
       return fileStorageService.loadFileAsResource(outPath, "output.mp4");
    }


}
