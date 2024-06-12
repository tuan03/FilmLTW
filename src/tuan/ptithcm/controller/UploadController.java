package ptithcm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ptithcm.bean.Mailer;
import ptithcm.dto.ContentDTO;
import ptithcm.dto.MovieWithViewsDTO;
import ptithcm.dto.UserDTO;
import ptithcm.entity.Comment;
import ptithcm.entity.Episode;
import ptithcm.entity.Genre;
import ptithcm.entity.Movie;
import ptithcm.entity.Reply;
import ptithcm.entity.User;
import ptithcm.utils.ExceptionHandlerUtil;


@Controller
public class UploadController {
	@Autowired 
	SessionFactory factory;
	@Autowired
	ServletContext context;
	
	@Autowired
	Mailer mailer;
	@RequestMapping(value = "upload", method = RequestMethod.GET)
	@Transactional
	public String uploadRender(HttpServletRequest request, ModelMap model) {
		Session session = factory.getCurrentSession();
		List<Genre> genres = session.createQuery("FROM Genre").list();
		model.addAttribute("genres",genres);
	    return "upload";	
	}
	
	
	
	@RequestMapping(value = "epupload", method = RequestMethod.GET)
	@Transactional
	public String tuploadRender(@RequestParam("id") Long idMovie,HttpServletRequest request, ModelMap model) {
		Session session = factory.getCurrentSession();
		List<Genre> genres = session.createQuery("FROM Genre").list();
		model.addAttribute("genres",genres);
	    return "uploadEp";	
	}
	
	@RequestMapping(value = "uploadEp", method = RequestMethod.POST)
	@Transactional
	public String uploadd(@RequestParam("file") MultipartFile file,HttpServletRequest request, ModelMap model) {
		

		if(file.isEmpty()) {
			model.addAttribute("errorMessage","Vui lòng chọn ảnh !");
		} else {
			try {
			if (!file.getContentType().startsWith("image")) {
                model.addAttribute("errorMessage", "Ảnh phải là file hình ảnh !");
                return "upload";
            }
			
			
			String directoryPath = context.getRealPath("/resources/video/");
            File directory = new File(directoryPath);
            
            // Tạo thư mục nếu không tồn tại
            if (!directory.exists()) {
                directory.mkdirs();
            }
			String nameFile = file.getOriginalFilename();
            String photoPath = directoryPath + nameFile;
            file.transferTo(new File(photoPath));
            System.out.println(photoPath);

            
            
			return "uploadEp";
		}
		catch(Exception e) {
			System.out.print(e);
			model.addAttribute("errorMessage", "Lỗi !");
		}
		}
		return "uploadEp";
	}
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@Transactional
	public String upload(@RequestParam("movie-cover") MultipartFile photo,
			@RequestParam("movie-description") String description,
			@RequestParam("movie-title") String title,
			@RequestParam("release-date") String releaseDate,
			@RequestParam("genres") String genres
			,HttpServletRequest request, ModelMap model) {
		
		System.out.println(description + ' ' + releaseDate + ' ' + genres);
		if(photo.isEmpty()) {
			model.addAttribute("errorMessage","Vui lòng chọn ảnh !");
		} else {
			try {
			if (!photo.getContentType().startsWith("image")) {
                model.addAttribute("errorMessage", "Ảnh phải là file hình ảnh !");
                return "upload";
            }
			
			
//			String directoryPath ="D:\\LAMVIEC\\LTW\\LTW\\WebContent\\resources\\img\\";
			String directoryPath = context.getRealPath("/resources/img/");
            File directory = new File(directoryPath);
            
            // Tạo thư mục nếu không tồn tại
            if (!directory.exists()) {
                directory.mkdirs();
            }
			String nameFile = photo.getOriginalFilename();
            String photoPath = directoryPath + nameFile;
            photo.transferTo(new File(photoPath));
            System.out.println(photoPath);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            // Chuyển đổi chuỗi thành LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(releaseDate, formatter);

            // Chuyển đổi LocalDateTime thành Timestamp
            Timestamp timestamp = Timestamp.valueOf(dateTime);
            
            
            Movie movie = new Movie();
            movie.setDescription(description);
            movie.setReleaseDate(timestamp);
            movie.setTitle(title);
            movie.setPosterUrl("http://localhost:8080/LTW/files/"+nameFile+".htm");
            List<Long> ids = Arrays.stream(genres.split(" "))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            
            Session session = factory.openSession();
            
            List<Genre> genresList = session.createQuery("FROM Genre WHERE id IN (:ids)").setParameterList("ids", ids).list();
            movie.setGenres(genresList);
            
            session.save(movie);
            
            
			return "upload";
		}
		catch(Exception e) {
			System.out.print(e);
			model.addAttribute("errorMessage", "Lỗi !");
		}
		}
		return "upload";
	
	}
	
	
	
	@RequestMapping("/files/{fileName}")
    @ResponseBody
    public void getFile(@PathVariable String fileName, HttpServletResponse response) throws IOException {
		String FILE_DIRECTORY = context.getRealPath("/resources/img/");
		File file = new File(FILE_DIRECTORY + fileName);
        
        try (FileInputStream fis = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {
            // Thiết lập thông tin phản hồi
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            
            // Đọc dữ liệu từ tệp và ghi vào luồng phản hồi
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }
}
