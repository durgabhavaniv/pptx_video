package org.zoho.zlabs.webCLI.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UploadFileResponse {
    @Id
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
