package com.ssafy.enjoyTrip.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.UrlResource;

import java.io.File;

@Getter
@Setter
@AllArgsConstructor
public class FileDto {

    private String filename;
    private byte[] fileContent;
}
