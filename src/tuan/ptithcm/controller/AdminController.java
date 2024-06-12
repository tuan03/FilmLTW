package ptithcm.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
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
import ptithcm.dto.NewMovieDTO;
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
public class AdminController {
	@Autowired
	SessionFactory factory;

	@Autowired
	Mailer mailer;

	@RequestMapping(value = "admin", method = RequestMethod.GET)
	@Transactional
	public String cinema(HttpServletRequest request, ModelMap model) {

		Session session = factory.getCurrentSession();
		List<Genre> genres = session.createQuery("FROM Genre").list();

		List<Movie> movies = session.createQuery("FROM Movie").list();
		model.addAttribute("genres", genres);
		model.addAttribute("movies", movies);
		return "admin";
	}

	@RequestMapping(value = "admin/update-series", method = RequestMethod.GET)
	@Transactional
	public String manageSeriesUI(HttpServletRequest request, ModelMap model, @RequestParam("id") Long id) {
		Session session = factory.getCurrentSession();
		List<Genre> genres = session.createQuery("FROM Genre").list();
		String hql = "SELECT DISTINCT m FROM Movie m " + "LEFT JOIN FETCH m.episodes " + "LEFT JOIN m.genres "
				+ "WHERE m.id = :movieId";
		Movie movie = (Movie) session.createQuery(hql).setParameter("movieId", id).uniqueResult();
		model.addAttribute("genres", genres);
		model.addAttribute("movie", movie);
		model.addAttribute("movieEpisodes", movie.getEpisodes());
		model.addAttribute("movieGenres", movie.getGenres());
		return "update-series";
	}

	@RequestMapping(value = "admin/update-series/{movieId}", method = RequestMethod.POST)
	@Transactional
	public String updateSeries(HttpServletRequest request, ModelMap model, @PathVariable("movieId") Long movieId,
			@ModelAttribute("NewMovie") NewMovieDTO movie) throws Exception {
		if (movieId == null || movie == null) {
			throw new Exception("Đầu vào không hợp lệ");
		}
		Session session = factory.openSession();
		Transaction t = null;
		Movie movieFindbyId = (Movie) session.get(Movie.class, movieId);
		movieFindbyId.setTitle(movie.getTitle());
		movieFindbyId.setDescription(movie.getDescription());
		List<Genre> genres = null;
		Movie fetchedMovie = null;
		String[] genresToUpdate = movie.getGenres().split(",");
		List<String> list = Arrays.asList(genresToUpdate);
		try {
			t = session.beginTransaction();
			session.update(movieFindbyId);
			for (String genre : list) {
				MovieGenre movieGenre = new MovieGenre();
				Genre genreToSet = new Genre();
				genreToSet.setId(Long.parseLong(genre));
				Movie movieToSet = new Movie();
				movieToSet.setId(movieId);
				movieGenre.setGenre(genreToSet);
				movieGenre.setMovie(movieToSet);
				session.save(movieGenre);
			}
			genres = session.createQuery("FROM Genre").list();
			String hql = "SELECT DISTINCT m FROM Movie m " + "LEFT JOIN FETCH m.episodes " + "LEFT JOIN m.genres "
					+ "WHERE m.id = :movieId";
			fetchedMovie = (Movie) session.createQuery(hql).setParameter("movieId", movieId).uniqueResult();
			t.commit();
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
		model.addAttribute("movie", movieFindbyId);
		model.addAttribute("genres", genres);
		model.addAttribute("movieEpisodes", fetchedMovie.getEpisodes());
		model.addAttribute("movieGenres", fetchedMovie.getGenres());
		return "update-series";
	}
}