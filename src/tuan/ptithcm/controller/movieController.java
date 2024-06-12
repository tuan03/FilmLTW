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
import ptithcm.entity.MovieGenre;
import ptithcm.entity.Reply;
import ptithcm.entity.User;
import ptithcm.utils.ExceptionHandlerUtil;


@Controller
@RequestMapping("movie")
public class movieController {
	@Autowired 
	SessionFactory factory;
	@Autowired
	ServletContext context;
	
	String hostServer = "http://localhost:8080/LTW";
	String folFile = "/resources/video"; 
	
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
	
	@RequestMapping(value = "del", method = RequestMethod.GET)
	@Transactional
	public String uploadRender(@RequestParam("id") Long idMovie,HttpServletRequest request, ModelMap model) {
		Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Movie m = new Movie();
            m.setId(idMovie);
            session.delete(m);
            transaction.commit();
            return "redirect:/admin.htm";
        } catch (Exception e) {
            ExceptionHandlerUtil.handleException(transaction, e, model);
        } finally {
            session.close();
        }
        return "forward:/admin.htm";
	}
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@Transactional
	public String upload(@RequestParam("movie-cover") MultipartFile photo,
			@RequestParam("movie-description") String description,
			@RequestParam("movie-title") String title,
			@RequestParam("release-date") String releaseDate,
			@RequestParam("genres") String genres
			,HttpServletRequest request, ModelMap model) {
		
		if(photo.isEmpty()) {
			model.addAttribute("errorMessage","Vui lòng chọn ảnh !");
		} else {
			Session session = null;
			Transaction transaction = null;
			try {
			if (!photo.getContentType().startsWith("image")) {
                model.addAttribute("errorMessage", "Ảnh phải là file hình ảnh !");
                return "upload";
            }
			
			

			String directoryPath = context.getRealPath(folFile);
            File directory = new File(directoryPath);
            
            // Tạo thư mục nếu không tồn tại
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String originalFilename = photo.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
            String fileNameWithoutExtension = originalFilename.substring(0, originalFilename.lastIndexOf('.'));
            String sanitizedFilename = fileNameWithoutExtension.replaceAll("[^a-zA-Z0-9.-]", ""); // Xóa tất cả các ký tự không phải là chữ cái, số, dấu gạch ngang hoặc dấu chấm
            
            long currentTimeMillis = System.currentTimeMillis(); // Lấy thời gian hiện tại
            String newNameFile = sanitizedFilename + currentTimeMillis; // tên tệp kết hợp với thời gian

            String filePath = directoryPath + File.separator + newNameFile + extension;
            photo.transferTo(new File(filePath));
            
            String url = hostServer + folFile +"/" + newNameFile + extension;
            
            

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            // Chuyển đổi chuỗi thành LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(releaseDate, formatter);

            // Chuyển đổi LocalDateTime thành Timestamp
            Timestamp timestamp = Timestamp.valueOf(dateTime);
            
           
            
            
            List<Long> ids = Arrays.stream(genres.split(" "))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            
            session = factory.openSession();
            transaction = session.beginTransaction();
            Movie movie = new Movie();
            movie.setDescription(description);
            movie.setReleaseDate(timestamp);
            movie.setTitle(title);
            movie.setPosterUrl(url);
            
            session.save(movie);
            
            for(Long idGenre : ids) {
            	MovieGenre mo_gen = new MovieGenre();
            	Genre gen = (Genre)session.get(Genre.class, idGenre);
            	mo_gen.setGenre(gen);
            	mo_gen.setMovie(movie);
            	session.save(mo_gen);
            }
            transaction.commit();
           
            
            
			return "upload";
		}
		catch(Exception e) {
			transaction.rollback();
			System.out.print(e);
			model.addAttribute("errorMessage", "Lỗi !");
		} finally {
			session.close();
		}
		}
		return "upload";
	
	}
}
