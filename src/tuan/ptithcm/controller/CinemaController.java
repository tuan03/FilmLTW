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
import ptithcm.entity.Movie;
import ptithcm.entity.Reply;
import ptithcm.entity.User;
import ptithcm.utils.ExceptionHandlerUtil;


@Controller
public class CinemaController {
	@Autowired 
	SessionFactory factory;
	
	@Autowired
	Mailer mailer;
	@RequestMapping(value = "cinema", method = RequestMethod.GET)
	@Transactional
	public String cinema(@RequestParam("id") Long idMovie, @RequestParam(value = "ep", defaultValue = "1") Integer epId, HttpServletRequest request, ModelMap model) {

	    Session session = factory.getCurrentSession();

	 // Lấy thông tin của bộ phim và các mối quan hệ của nó (episodes, comments, genres)
	    String hql = "SELECT DISTINCT m FROM Movie m " +
	             "LEFT JOIN FETCH m.episodes " +
	             "LEFT JOIN m.genres " +
	             "WHERE m.id = :movieId";
	    Movie movie = (Movie) session.createQuery(hql)
	                                 .setParameter("movieId", idMovie)
	                                 .uniqueResult();
	    
	    Collections.sort(movie.getEpisodes(), Comparator.comparingInt(Episode::getEpisodeNumber));

	    
	    Episode selectedEpisode = movie.getEpisodes().get(epId-1);

	    long totalViews = 0;
	    for (Episode episode : movie.getEpisodes()) {
	        totalViews += episode.getViews();
	    }
	    
	    String hql2 = "SELECT c FROM Comment c " +
                "JOIN FETCH c.user " +
                "LEFT JOIN FETCH c.replies " +
                "WHERE c.movie.id = :movieId " +
                "ORDER BY c.createdAt DESC";
	    
	    List<Comment> comment = session.createQuery(hql2)
                .setParameter("movieId", idMovie).list();
	    
	    
	    model.addAttribute("movie", movie);
	    model.addAttribute("comment", comment);
	    model.addAttribute("selectedEpisode", selectedEpisode);
	    model.addAttribute("totalViews", totalViews);

	    return "cinema";
	}
	
	
	@RequestMapping(value = "cinema", method = RequestMethod.POST)
	@Transactional
	public String comment(HttpSession s,@ModelAttribute("content") ContentDTO contentD,@RequestParam("id") Long idMovie, @RequestParam(value = "ep", defaultValue = "1") Long epId, HttpServletRequest request, ModelMap model) {
		Session session = factory.openSession();
		Transaction t = null;
		
		try {
		t= session.beginTransaction();
		UserDTO userDTO = (UserDTO) s.getAttribute("User");
		User user = (User)session.get(User.class, userDTO.getId());
        Episode episode = (Episode)session.get(Episode.class, epId);
        Movie movie = (Movie)session.get(Movie.class, idMovie);
        
        
        Comment cm = new Comment();
        cm.setContent(contentD.getContent());
        cm.setUser(user);
        cm.setEpisode(episode);
        cm.setMovie(movie);
        
        session.save(cm);
        
		t.commit();
		System.out.print("Thành Công "+user.getEmail()+"   "+movie.getPosterUrl());
		} catch (Exception e) {
            ExceptionHandlerUtil.handleException(t, e, model);
        } finally {
            session.close();
        }
		String urlR = "cinema.htm?id="+idMovie+"&ep="+epId;
		return "redirect:"+urlR;
	}
	
	
	
	@RequestMapping(value = "reply", method = RequestMethod.POST)
	@Transactional
	public String reply(HttpSession s,@ModelAttribute("content") ContentDTO contentD,@RequestParam("id") Long idMovie, @RequestParam("ep") Long epId, @RequestParam("idC") Long idComment, HttpServletRequest request, ModelMap model) {
		Session session = factory.openSession();
		Transaction t = null;
		
		try {
		t= session.beginTransaction();
		UserDTO userDTO = (UserDTO) s.getAttribute("User");
		User user = (User)session.get(User.class, userDTO.getId());
        Comment cm = (Comment)session.get(Comment.class, idComment);

        Reply rep = new Reply();
        rep.setComment(cm);
        rep.setContent(contentD.getContent());
        rep.setUser(user);
        
        session.save(rep);
        
		t.commit();

		} catch (Exception e) {
            ExceptionHandlerUtil.handleException(t, e, model);
        } finally {
            session.close();
        }
		String urlR = "cinema.htm?id="+idMovie+"&ep="+epId;
		return "redirect:"+urlR;
	}
}
