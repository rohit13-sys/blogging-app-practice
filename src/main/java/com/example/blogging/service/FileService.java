package com.example.blogging.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

     String savedFileName(MultipartFile multipartFile,String path) throws IOException;

    InputStream getImage(String path,String fileName) throws FileNotFoundException;

     String saveFile(String uploadDir, String fileName,
                           MultipartFile multipartFile) throws IOException;
}
