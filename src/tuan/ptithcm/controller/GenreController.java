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
@RequestMapping("genre")
public class GenreController {
	@Autowired 
	SessionFactory factory;
	
	@Autowired
	Mailer mailer;

	
	@RequestMapping(value = "del", method = RequestMethod.GET)
    public String deleteGenre(@RequestParam("id") Long id, ModelMap model) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Genre genre = new Genre();
            genre.setId(id);
            session.delete(genre);
            transaction.commit();
            return "redirect:/admin.htm";
        } catch (Exception e) {
            ExceptionHandlerUtil.handleException(transaction, e, model);
        } finally {
            session.close();
        }
        return "forward:/admin.htm";
    }
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@ModelAttribute("title") ContentDTO titleD, ModelMap model) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Genre genre = new Genre();
            genre.setName(titleD.getContent());
            
            
            session.save(genre);
            
            
            transaction.commit();
            return "redirect:/admin.htm";
        } catch (Exception e) {
            ExceptionHandlerUtil.handleException(transaction, e, model);
        } finally {
            session.close();
        }
        return "forward:/admin.htm";
    }
	@RequestMapping(value = "edit", method = RequestMethod.POST)
    @Transactional
    public String showEditGenreForm(@RequestParam("id") Long id,@ModelAttribute("content") ContentDTO titleD, ModelMap model) {
		Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Genre genre = (Genre) session.get(Genre.class, id);
            genre.setName(titleD.getContent());
            session.save(genre);
            transaction.commit();
            return "redirect:/admin.htm";
        } catch (Exception e) {
            ExceptionHandlerUtil.handleException(transaction, e, model);
        } finally {
            session.close();
        }
        return "redirect:/admin.htm";
    }
}
