package com.ssafy.enjoyTrip.fileUpload;

import com.ssafy.enjoyTrip.common.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public String uploadFile(@RequestPart MultipartFile file) throws BaseException {
        return fileService.uploadFile(file);
    }

    @GetMapping(value = "/test-file", produces="image/png")
    public ResponseEntity<?> downloadOnlyFile(String fileName) throws BaseException {
        return ResponseEntity
                .ok()
                .body(fileService.getFile(fileName));
    }

    @GetMapping("/test-Obj")
    public ResponseEntity<?> downloadFileObj(String fileName) throws BaseException {
        return ResponseEntity
                .ok()
                .body(new FileDto(fileName, fileService.getFile(fileName)));
    }
}
