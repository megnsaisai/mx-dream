package cn.mx.common.utils;

import org.springframework.web.multipart.MultipartFile;

/**
 * 类名: tool
 *
 * @version 1.0
 * @since 2024-07-17
 */

public class Tool {



    public static String convertFileSize(MultipartFile file){
        if (file.isEmpty()) {
            return "文件为空。";
        }

        // 获取文件大小（字节）
        long fileSizeInBytes = file.getSize();

        // 转换为兆字节
        double fileSizeInMB = fileSizeInBytes / 1048576.0;

        // 将文件大小格式化为两位小数

        return String.format("%.2f", fileSizeInMB);
    }
}
