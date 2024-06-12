package ptithcm.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
public class AdminController {
	@Autowired 
	SessionFactory factory;
	
	@Autowired
	Mailer mailer;
	@RequestMapping(value = "admin", method = RequestMethod.GET)
	@Transactional
	public String cinema(HttpServletRequest request, ModelMap model) {

	    Session session = factory.getCurrentSession();
	    List<Genre> genres= session.createQuery("FROM Genre").list();
	    
	    List<Movie> movies = session.createQuery("FROM Movie").list();
	    model.addAttribute("genres",genres);
	    model.addAttribute("movies",movies);
	    return "admin";	
	}
	
	
}
