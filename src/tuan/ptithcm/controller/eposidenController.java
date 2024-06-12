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
@RequestMapping("eposide")
public class eposidenController {
	@Autowired 
	SessionFactory factory;
	@RequestMapping(value = "upload", method = RequestMethod.GET)
	@Transactional
	public String renderUpload(@RequestParam("id") Long idMovie,HttpServletRequest request, ModelMap model) {
		Session session = factory.getCurrentSession();
		Movie movie = (Movie) session.get(Movie.class, idMovie);
		model.addAttribute("movie",movie);
	    return "uploadEp";	
	}
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String upload(@RequestParam("idMovie") Long idMovie,@RequestParam("urlVideo") String urlVideo,
			@RequestParam("numberEp") String numberEp,
			@RequestParam("title") String title,
			HttpServletRequest request, ModelMap model) {
		
		Session session = factory.openSession();
		Transaction t = null;
		
		try {
		t= session.beginTransaction();
		
		Episode ep = new Episode();
		ep.setVideoUrl(urlVideo);
		ep.setTitle(title);
		
		Movie movie = (Movie) session.get(Movie.class, idMovie);
		ep.setMovie(movie);
		
		session.save(ep);
		t.commit();
		} catch (Exception e) {
            ExceptionHandlerUtil.handleException(t, e, model);
        } finally {
            session.close();
        }
		return "forward:uploadEp";
	}
}
