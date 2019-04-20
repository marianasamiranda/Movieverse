/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: mariana(Universidade do Minho)
 * License Type: Academic
 */
package data.movieverse;

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class MovieDAO {
	public static Movie loadMovieByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMovieByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Movie getMovieByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return getMovieByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Movie loadMovieByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMovieByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Movie getMovieByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return getMovieByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Movie loadMovieByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (Movie) session.load(data.movieverse.Movie.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Movie getMovieByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (Movie) session.get(data.movieverse.Movie.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Movie loadMovieByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Movie) session.load(data.movieverse.Movie.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Movie getMovieByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Movie) session.get(data.movieverse.Movie.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMovie(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return queryMovie(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMovie(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return queryMovie(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Movie[] listMovieByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return listMovieByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Movie[] listMovieByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return listMovieByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMovie(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Movie as Movie");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMovie(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Movie as Movie");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Movie", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Movie[] listMovieByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryMovie(session, condition, orderBy);
			return (Movie[]) list.toArray(new Movie[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Movie[] listMovieByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryMovie(session, condition, orderBy, lockMode);
			return (Movie[]) list.toArray(new Movie[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Movie loadMovieByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMovieByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Movie loadMovieByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return loadMovieByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Movie loadMovieByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Movie[] movies = listMovieByQuery(session, condition, orderBy);
		if (movies != null && movies.length > 0)
			return movies[0];
		else
			return null;
	}
	
	public static Movie loadMovieByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Movie[] movies = listMovieByQuery(session, condition, orderBy, lockMode);
		if (movies != null && movies.length > 0)
			return movies[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateMovieByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return iterateMovieByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateMovieByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = MovieversePersistentManager.instance().getSession();
			return iterateMovieByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateMovieByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Movie as Movie");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateMovieByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From data.movieverse.Movie as Movie");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Movie", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Movie createMovie() {
		return new data.movieverse.Movie();
	}
	
	public static boolean save(data.movieverse.Movie movie) throws PersistentException {
		try {
			MovieversePersistentManager.instance().saveObject(movie);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(data.movieverse.Movie movie) throws PersistentException {
		try {
			MovieversePersistentManager.instance().deleteObject(movie);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(data.movieverse.Movie movie)throws PersistentException {
		try {
			data.movieverse.UserMovie[] lUserMoviess = movie.userMovies.toArray();
			for(int i = 0; i < lUserMoviess.length; i++) {
				lUserMoviess[i].setMovie(null);
			}
			data.movieverse.Genre[] lGenress = movie.genres.toArray();
			for(int i = 0; i < lGenress.length; i++) {
				lGenress[i].movies.remove(movie);
			}
			data.movieverse.Company[] lCompaniess = movie.companies.toArray();
			for(int i = 0; i < lCompaniess.length; i++) {
				lCompaniess[i].movies.remove(movie);
			}
			data.movieverse.Comment[] lCommentss = movie.comments.toArray();
			for(int i = 0; i < lCommentss.length; i++) {
				lCommentss[i].setMovie(null);
			}
			data.movieverse.MovieMember[] lMovieMemberss = movie.movieMembers.toArray();
			for(int i = 0; i < lMovieMemberss.length; i++) {
				lMovieMemberss[i].setMovie(null);
			}
			data.movieverse.Showtime[] lShowtimes = movie.showtime.toArray();
			for(int i = 0; i < lShowtimes.length; i++) {
				lShowtimes[i].setMovie(null);
			}
			return delete(movie);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(data.movieverse.Movie movie, org.orm.PersistentSession session)throws PersistentException {
		try {
			data.movieverse.UserMovie[] lUserMoviess = movie.userMovies.toArray();
			for(int i = 0; i < lUserMoviess.length; i++) {
				lUserMoviess[i].setMovie(null);
			}
			data.movieverse.Genre[] lGenress = movie.genres.toArray();
			for(int i = 0; i < lGenress.length; i++) {
				lGenress[i].movies.remove(movie);
			}
			data.movieverse.Company[] lCompaniess = movie.companies.toArray();
			for(int i = 0; i < lCompaniess.length; i++) {
				lCompaniess[i].movies.remove(movie);
			}
			data.movieverse.Comment[] lCommentss = movie.comments.toArray();
			for(int i = 0; i < lCommentss.length; i++) {
				lCommentss[i].setMovie(null);
			}
			data.movieverse.MovieMember[] lMovieMemberss = movie.movieMembers.toArray();
			for(int i = 0; i < lMovieMemberss.length; i++) {
				lMovieMemberss[i].setMovie(null);
			}
			data.movieverse.Showtime[] lShowtimes = movie.showtime.toArray();
			for(int i = 0; i < lShowtimes.length; i++) {
				lShowtimes[i].setMovie(null);
			}
			try {
				session.delete(movie);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(data.movieverse.Movie movie) throws PersistentException {
		try {
			MovieversePersistentManager.instance().getSession().refresh(movie);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(data.movieverse.Movie movie) throws PersistentException {
		try {
			MovieversePersistentManager.instance().getSession().evict(movie);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Movie loadMovieByCriteria(MovieCriteria movieCriteria) {
		Movie[] movies = listMovieByCriteria(movieCriteria);
		if(movies == null || movies.length == 0) {
			return null;
		}
		return movies[0];
	}
	
	public static Movie[] listMovieByCriteria(MovieCriteria movieCriteria) {
		return movieCriteria.listMovie();
	}
}
