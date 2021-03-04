package org.zoho.zlabs.webCLI.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "file")
public class ApplicationProperties {

    private String uploadDir;
    private String outDir;
    
    private String pptxVideoScriptfile;
    private String pptxVideoInDir;
    private String pptxVideoOutDir;

    private String ffmpegVideoScriptfile;
    private String ffmpegVideoInDir;
    private String ffmpegVideoOutDir;

}