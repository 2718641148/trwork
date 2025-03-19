package com.example.web_homework;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.web.multipart.MultipartFile;

public class Oss {

    private String endpoint = "oss-cn-beijing-aliyuncs.com"; // 例如："oss-cn-hangzhou.aliyuncs.com"
    private String accessKeyId = "LTAI5tCnGkXPkrN6XmjSoc6L";
    private String accessKeySecret = "q7u4y3u1QewcUoULPD5WeDpd5gsi8y";
    private String bucketName = "web-homework-1";

    public String uploadFile(MultipartFile file) {
        String url = "";
        OSS ossClient = null;
        try {
            // 创建OSSClient实例
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 获取文件名
            String fileName = file.getOriginalFilename();
            if (fileName != null && !fileName.isEmpty()) {
                // 生成唯一的文件名，避免冲突
                String[] filePaths = fileName.split("\\.");
                String newName = System.currentTimeMillis() + "." + filePaths[filePaths.length - 1];

                // 开始上传文件
                ossClient.putObject(bucketName, newName, file.getInputStream());

                // 拼接最终的URL
                url = "http://" + bucketName + "." + endpoint + "/" + newName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown(); // 关闭OSSClient
            }
        }
        return url;
    }
}