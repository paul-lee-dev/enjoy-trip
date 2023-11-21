package com.ssafy.enjoyTrip.fileUpload;

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
    private final String IMG_FILE_PATH;
    private final Set<String> IMG_EXTENSION_LIST;

    public FileService(@Value("${file.dirPath.img}") String internalPath) {
        Set<String> imgFileExts = new HashSet<>();
        imgFileExts.add("png");
        imgFileExts.add("jpg");

        IMG_EXTENSION_LIST = imgFileExts;
        IMG_FILE_PATH = new File(".").getAbsolutePath() // 항상 프로젝트 경로를 반환하므로, 실행환경과 상관없음
                + internalPath;
    }

    /**
     * 파일 서버에 저장 후, 경로 반환
     */
    public String uploadFile(MultipartFile file) throws BaseException {
        String originFileName = file.getOriginalFilename();
        String saveFileName = createFileName(originFileName);

        String fullPath = IMG_FILE_PATH + saveFileName;
        typeCheck(originFileName);

        try {
            File uploadFile = new File(fullPath);
            file.transferTo(uploadFile);
            
            return saveFileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException(FILE_UPLOAD_ERROR);
        }
    }

    public UrlResource getFile(String saveFileName) throws BaseException{
        String fullPath = IMG_FILE_PATH + saveFileName;
        try {
            return new UrlResource("file:" + fullPath);
        } catch (Exception e) {
            throw new BaseException(FILE_DOWNLOAD_ERROR);
        }
    }

    /**
     * 파일명 중복 방지를 위해 UUID 붙여서 반환
     * @param originFileName
     * @return
     */
    private String createFileName(String originFileName) {
        String uuid = UUID.randomUUID().toString();
        return uuid + originFileName;
    }

    /**
     * 지정된 파일 타입이 아니면, 예외 throw
     * @param originFileName
     * @throws BaseException
     */
    private void typeCheck(String originFileName) throws BaseException {
        String extension = originFileName.substring(
                originFileName.lastIndexOf(".")+1
        );
        if (!IMG_EXTENSION_LIST.contains(extension)) {
            throw new BaseException(WRONG_IMG_TYPE);
        }
    }
}
