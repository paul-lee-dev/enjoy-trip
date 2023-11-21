package com.ssafy.enjoyTrip.fileUpload;

import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.common.BaseResponse;
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
    public ResponseEntity<?> uploadFile(@RequestPart MultipartFile file) throws BaseException {
        return ResponseEntity
                .ok()
                .body(new BaseResponse<>(fileService.uploadFile(file)));
    }
}
