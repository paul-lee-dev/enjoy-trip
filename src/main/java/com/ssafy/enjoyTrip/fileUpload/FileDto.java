package com.ssafy.enjoyTrip.fileUpload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.UrlResource;

import java.io.Serializable;

@Getter
@Setter
public class FileDto implements Serializable {

    private String fileName;
    private CustomUrlResource fileContent;

    public FileDto(String fileName, UrlResource urlResource) {
        this.fileName = fileName;
        this.fileContent = new CustomUrlResource(urlResource);
    }
}
