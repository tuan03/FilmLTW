package ptithcm.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import javax.servlet.ServletContext;


@Controller
@RequestMapping("upload")
public class UploadApiController {
	@Autowired
	ServletContext context;
	String hostServer = "http://localhost:8080/LTW";
	String folFile = "/resources/video"; 
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
    public String helloWorld(@RequestParam("file") MultipartFile file) {
		if(file.isEmpty()) {
			return "{\"error\",\"Vui lòng chọn file !\"}";
		} else {
			try {
				if (!file.getContentType().startsWith("video")) {
				    return "{\"error\": \"Vui lòng chọn file video!\"}";
				}
			
			
			String directoryPath = context.getRealPath(folFile);
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
            String fileNameWithoutExtension = originalFilename.substring(0, originalFilename.lastIndexOf('.'));
            String sanitizedFilename = fileNameWithoutExtension.replaceAll("[^a-zA-Z0-9.-]", ""); // Xóa tất cả các ký tự không phải là chữ cái, số, dấu gạch ngang hoặc dấu chấm
            
            long currentTimeMillis = System.currentTimeMillis(); // Lấy thời gian hiện tại
            String newNameFile = sanitizedFilename + currentTimeMillis; // tên tệp kết hợp với thời gian

            String filePath = directoryPath + File.separator + newNameFile + extension;
            file.transferTo(new File(filePath));
            
            String url = hostServer + folFile +"/" + newNameFile + extension;
            System.out.println(url);
            
            return "{\"result\": \""+url+"\"}";
		}
		catch(Exception e) {
			return "{\"error\": \"Có Lỗi Xảy Ra!!\"}";
		}
		}
    }
}


