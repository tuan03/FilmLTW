package ptithcm.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ptithcm.bean.Mailer;
import ptithcm.dto.ContentDTO;
import ptithcm.dto.MovieWithViewsDTO;
import ptithcm.entity.Movie;
import ptithcm.entity.User;


@Controller
public class indexController {
	@Autowired 
	SessionFactory factory;
	
	@Autowired
	Mailer mailer;

	@RequestMapping(value = "page", method = RequestMethod.GET)
	@Transactional
	public String homePage(@RequestParam("page") Long page,HttpServletRequest request, ModelMap model) {
		Session session = factory.getCurrentSession();
		
		String hql2 = "SELECT new ptithcm.dto.MovieWithViewsDTO(m.id, m.title, m.posterUrl, SUM(e.views)) " +
			       "FROM Movie m " +
			       "LEFT JOIN m.episodes e " +
			       "GROUP BY m.id, m.title " +
			       "ORDER BY m.createdAt DESC";
		List<MovieWithViewsDTO> newMovie = session.createQuery(hql2).list();
		
		int numPage = (newMovie.size()+10)/10;
		model.addAttribute("numPage",numPage);
		model.addAttribute("currentPage",page);
		
		List<MovieWithViewsDTO> result = newMovie.size() > page*10 ? newMovie.subList((int)(page-1)*10, (int)(page*10)) : newMovie.subList((int)(page-1)*10, newMovie.size()) ;
		model.addAttribute("newMovie",result);
		
		return "homePage";
	}
	
	
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	@Transactional
	public String home(HttpServletRequest request, ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql1 = "SELECT new ptithcm.dto.MovieWithViewsDTO(m.id, m.title, m.posterUrl, SUM(e.views)) " +
		           "FROM Movie m JOIN m.episodes e " +
		           "GROUP BY m.id, m.title " +
		           "ORDER BY SUM(e.views) DESC";
		List<MovieWithViewsDTO> top10_views_movies = session.createQuery(hql1).list();
		
		String hql2 = "SELECT new ptithcm.dto.MovieWithViewsDTO(m.id, m.title, m.posterUrl, SUM(e.views)) " +
			       "FROM Movie m " +
			       "LEFT JOIN m.episodes e " +
			       "GROUP BY m.id, m.title " +
			       "ORDER BY m.createdAt DESC";
		List<MovieWithViewsDTO> newMovie = session.createQuery(hql2).list();
		model.addAttribute("t8views",top10_views_movies.subList(0, 10));
		
		List<MovieWithViewsDTO> result = newMovie.size() > 10 ? newMovie.subList(0, 10) : newMovie;
		model.addAttribute("newMovie",result);
		int numPage = (newMovie.size()+9)/10;
		model.addAttribute("numPage",numPage);
		model.addAttribute("currentPage",1);
		return "home";
	}
}
