package cn.mx.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class FileUploader {


    private static final String UPLOAD_URL = "http://5.180.98.233:5000/upload";

    public static Map<String, Object> uploadFile(MultipartFile file) {
        String boundary = UUID.randomUUID().toString();
        String LINE_FEED = "\r\n";
        HttpURLConnection httpConn = null;
        Map<String, Object> responseMap = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            URL url = new URL(UPLOAD_URL);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setUseCaches(false);
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            OutputStream outputStream = httpConn.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);

            // 添加文件部分
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getOriginalFilename() + "\"").append(LINE_FEED);
            writer.append("Content-Type: " + file.getContentType()).append(LINE_FEED);
            writer.append(LINE_FEED).flush();

            InputStream inputStream = file.getInputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();

            writer.append(LINE_FEED).flush();
            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.close();

            // 检查服务器响应
            int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 解析JSON响应
                responseMap = objectMapper.readValue(response.toString(), HashMap.class);
                responseMap.put("status", "success");
            } else {
                responseMap.put("status", "error");
                responseMap.put("message", "File upload failed. Response code: " + responseCode);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            responseMap.put("status", "error");
            responseMap.put("message", "File upload failed. Exception: " + ex.getMessage());
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }
        return responseMap;
    }
}
