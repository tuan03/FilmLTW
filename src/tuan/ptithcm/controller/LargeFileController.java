package ptithcm.controller;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/file")
public class LargeFileController {

    private static final String FILE_PATH = "D:/data.txt";

    @RequestMapping("/e")
    public void streamFile(HttpServletResponse response) throws IOException {
        File file = new File(FILE_PATH);

        // Kiểm tra xem tệp tin có tồn tại không
        if (!file.exists()) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }

        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        response.setContentLengthLong(file.length());

        try (FileInputStream inputStream = new FileInputStream(file);
                OutputStream outputStream = response.getOutputStream()) {
               byte[] buffer = new byte[10240]; // 10 KB buffer
               int bytesRead;
               while ((bytesRead = inputStream.read(buffer)) != -1) {
                   outputStream.write(buffer, 0, bytesRead);
                   outputStream.flush(); // Đảm bảo rằng dữ liệu được gửi ngay lập tức
                   try {
                       Thread.sleep(1000); // Tạm dừng 1 giây
                   } catch (InterruptedException e) {
                       Thread.currentThread().interrupt();
                       throw new IOException("Thread interrupted while streaming file", e);
                   }
               }
           }
    }
}
