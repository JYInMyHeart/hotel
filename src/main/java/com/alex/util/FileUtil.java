package com.alex.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class FileUtil {

    public static String uploadFile(MultipartFile file, String prefix, String storePath){
        String fileName = file.getOriginalFilename();
        // 获取文件后缀
        String suffix= fileName != null? fileName.substring(fileName.lastIndexOf(".")) : null;
        UUID uuid = UUID.randomUUID();
        File f = new File(storePath, prefix + uuid + suffix);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdir();
        }
        int n;
        try (InputStream in  = file.getInputStream(); OutputStream os = new FileOutputStream(f)){
            // 得到文件流。以文件流的方式输出到新文件
            // 可以使用byte[] ss = multipartFile.getBytes();代替while
            byte[] buffer = new byte[4096];
            while ((n = in.read(buffer,0,4096)) != -1){
                os.write(buffer,0,n);
            }
            // 读取文件第一行
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
            // 输出路径
            bufferedReader.close();
        }catch (IOException e){
           throw new RuntimeException(e);
        }
        return f.getName();
    }
}
