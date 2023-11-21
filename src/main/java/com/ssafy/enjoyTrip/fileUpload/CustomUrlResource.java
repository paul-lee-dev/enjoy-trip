package com.ssafy.enjoyTrip.fileUpload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

@Getter
public class CustomUrlResource {
    private URI uri;
    private URL url;

    public CustomUrlResource(UrlResource urlResource) {
        try {
            this.uri = urlResource.getURI();
            this.url = urlResource.getURL();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
