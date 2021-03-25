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

    private String filter1VideoScriptfile;
    private String filter1VideoInDir;
    private String filter1VideoOutDir;

    private String filter2VideoScriptfile;
    private String filter2VideoInDir;
    private String filter2VideoOutDir;

    private String filter3VideoScriptfile;
    private String filter3VideoInDir;
    private String filter3VideoOutDir;

    private String filter4VideoScriptfile;
    private String filter4VideoInDir;
    private String filter4VideoOutDir;

    private String filter5VideoScriptfile;
    private String filter5VideoInDir;
    private String filter5VideoOutDir;

    private String filter6VideoScriptfile;
    private String filter6VideoInDir;
    private String filter6VideoOutDir;

    private String filter7VideoScriptfile;
    private String filter7VideoInDir;
    private String filter7VideoOutDir;

    private String filter8VideoScriptfile;
    private String filter8VideoInDir;
    private String filter8VideoOutDir;
}