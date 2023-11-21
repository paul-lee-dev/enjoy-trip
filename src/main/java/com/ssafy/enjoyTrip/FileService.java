package com.ssafy.enjoyTrip;

import com.google.common.io.Files;
import com.ssafy.enjoyTrip.common.BaseException;
import com.ssafy.enjoyTrip.common.BaseResponseStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.ssafy.enjoyTrip.common.BaseResponseStatus.*;

@Service
public class FileService {

    @Value("${file.dirPath.img}")
    private String imgFilePath;
    private final Set<String> IMG_EXTENSION_LIST;

    public FileService() {
        Set<String> imgFileExts = new HashSet<>();
        imgFileExts.add("png");
        imgFileExts.add("jpg");

        IMG_EXTENSION_LIST = imgFileExts;
    }

    /**
     * 파일 서버에 저장 후, 경로 반환
     */
    public String uploadFile(MultipartFile file) throws BaseException {
        String originFileName = file.getOriginalFilename();
        String saveFileName = createFileName(originFileName);

        String fullPath = imgFilePath + saveFileName;
        System.out.println(fullPath);
        typeCheck(originFileName);

        try {
            file.transferTo(new File(fullPath));
            
            return saveFileName;
        } catch (IOException e) {
            throw new BaseException(FILE_UPLOAD_ERROR);
        }
    }

    public byte[] getFile(String saveFileName) throws BaseException{
        String fullPath = imgFilePath + saveFileName;

        try {
            return Files.toByteArray(
                    new File(fullPath)
            );
        } catch (Exception e) {
            throw new BaseException(FILE_DOWNLOAD_ERROR);
        }
    }
//    public UrlResource getFile(String saveFileName) throws BaseException{
//        String fullPath = imgFilePath + saveFileName;
//
//        try {
//            return new UrlResource("file:" + imgFilePath + "saveFileName");
//        } catch (IOException e) {
//            throw new BaseException(FILE_DOWNLOAD_ERROR);
//        }
//    }

    private String createFileName(String originFileName) {
        String uuid = UUID.randomUUID().toString();
        return uuid + originFileName;
    }

    private void typeCheck(String originFileName) throws BaseException {
        String extension = originFileName.substring(
                originFileName.lastIndexOf(".")+1
        );
        if (!IMG_EXTENSION_LIST.contains(extension)) {
            throw new BaseException(WRONG_IMG_TYPE);
        }
    }
}
