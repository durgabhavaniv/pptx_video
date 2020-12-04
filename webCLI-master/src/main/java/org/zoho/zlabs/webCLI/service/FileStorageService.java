package org.zoho.zlabs.webCLI.service;

import org.zoho.zlabs.webCLI.exception.FileStorageException;
import org.zoho.zlabs.webCLI.exception.MyFileNotFoundException;
import org.zoho.zlabs.webCLI.config.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private Path fileStorageLocation;
    private Path fileOutputLocation;
    private String scriptfile;
    

    @Autowired
    private FileStorageProperties fileStorageProperties;
    @Autowired
    private CommandExecuter commandExecuter;

    // @Autowired
    // public FileStorageService(FileStorageProperties fileStorageProperties, CommandExecuter commandExecuter) {
    //     this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
    //             .toAbsolutePath().normalize();
    //     this.fileOutputLocation = Paths.get(fileStorageProperties.getOutDir())
    //             .toAbsolutePath().normalize();
    //     this.scriptfile = fileStorageProperties.getScriptfile();
    //     this.commandExecuter = commandExecuter;

    //     try {
    //         Files.createDirectories(this.fileStorageLocation);
    //         Files.createDirectories(this.fileOutputLocation);
    //     } catch (Exception ex) {
    //         throw new FileStorageException("Could not create the directory where the files will be stored.", ex);
    //     }
    
    // }

    public String storeFile(MultipartFile file) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        this.fileOutputLocation = Paths.get(fileStorageProperties.getOutDir())
                .toAbsolutePath().normalize();
        this.scriptfile = fileStorageProperties.getScriptfile();
              
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            
            commandExecuter.processCommand(scriptfile+" "+fileStorageLocation.toString()+"/"+fileName+" "+fileOutputLocation.toString()+"/"+" "+fileName.replaceFirst("[.][^.]+$", ""));

            return fileName.replaceFirst("[.][^.]+$", "");
            // return "output.mp4";

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        // this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                // .toAbsolutePath().normalize();
        // this.fileOutputLocation = Paths.get(fileStorageProperties.getOutDir())
                // .toAbsolutePath().normalize();
        // this.scriptfile = fileStorageProperties.getScriptfile();
        // this.commandExecuter = commandExecuter;

        try {
            System.out.println (fileStorageProperties.getOutDir()+fileName.replaceFirst("[.][^.]+$", "/"));
            Path oPath = Paths.get(fileStorageProperties.getOutDir()+fileName.replaceFirst("[.][^.]+$", "/"))
            .toAbsolutePath().normalize();
            Path filePath = oPath.resolve("output.mp4").normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}