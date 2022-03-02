package com.xhm.rs.common.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传工具类
 */
public class F {

    /**
     * 
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    public static String saveFile(MultipartFile uploadFile, String fpath) {
        String result = "";
        try {
            // 文件名
            String fileName = uploadFile.getOriginalFilename();
            // 在file文件夹中创建名为fileName的文件

            int split = fileName.lastIndexOf(".");
            // 文件后缀，用于判断上传的文件是否是合法的
            String suffix = fileName.substring(split + 1, fileName.length());
            // 保存文件
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File upload = new File(path.getAbsolutePath(), fpath);
            if (!upload.exists()) {
                upload.mkdirs();
            }
            String newName = System.currentTimeMillis() + "." + suffix;
            String filepath = upload +"/"+ newName;
            File savedFile = new File(filepath);
            uploadFile.transferTo(savedFile);

            result = fpath + newName;

            // {code: 200, msg: "操作成功", data: "upload/avatar/1626939612776.jpg"}

        } catch (Exception e) {
            throw new RuntimeException();
        }

        return result;
    }
    
    /**
     * 
     */
    public static void deleteFile(String rpath){

        File path;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
            String apath=path.getAbsolutePath()+ "/" + rpath;
            File file = new File(apath);
            if (file.exists())
               file.delete();  
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
      

    }
}