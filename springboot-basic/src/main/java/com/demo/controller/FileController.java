package com.demo.controller;

import com.demo.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    private static final String FILE_DIR = "D:/xyc/";

    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        File dir = new File(FILE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        String fileExt = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + fileExt;
        try {
            file.transferTo(new File(FILE_DIR + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success("文件上传成功", fileName);
    }

    @GetMapping("/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(FILE_DIR + fileName));
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
