/**
 * Licensee: mariana(Universidade do Minho)
 * License Type: Academic
 */
package data.ormsamples;

import org.orm.*;
public class CreateMovieverseData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = data.movieverse.MovieversePersistentManager.instance().getSession().beginTransaction();
		try {
			data.movieverse.MUser lmovieverseMUser = data.movieverse.MUserDAO.createMUser();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : friends, receivedFriendships, mUserMovies, achievements, feed, comments, requestedFriendships, hoursCount, movieCount, gender, user_country
			data.movieverse.MUserDAO.save(lmovieverseMUser);
			data.movieverse.Movie lmovieverseMovie = data.movieverse.MovieDAO.createMovie();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : movieMembers, comments, companies, genres, mUserMovies, ratingCount, ratingSum, runtime
			data.movieverse.MovieDAO.save(lmovieverseMovie);
			data.movieverse.Genre lmovieverseGenre = data.movieverse.GenreDAO.createGenre();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : movies
			data.movieverse.GenreDAO.save(lmovieverseGenre);
			data.movieverse.Company lmovieverseCompany = data.movieverse.CompanyDAO.createCompany();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : movies, company_country
			data.movieverse.CompanyDAO.save(lmovieverseCompany);
			data.movieverse.Country lmovieverseCountry = data.movieverse.CountryDAO.createCountry();
			// Initialize the properties of the persistent object here
			data.movieverse.CountryDAO.save(lmovieverseCountry);
			data.movieverse.MUserMovie lmovieverseMUserMovie = data.movieverse.MUserMovieDAO.createMUserMovie();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : mUser, movie, visible, favourite, rating, status
			data.movieverse.MUserMovieDAO.save(lmovieverseMUserMovie);
			data.movieverse.Member lmovieverseMember = data.movieverse.MemberDAO.createMember();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : movieMembers, gender
			data.movieverse.MemberDAO.save(lmovieverseMember);
			data.movieverse.MovieMember lmovieverseMovieMember = data.movieverse.MovieMemberDAO.createMovieMember();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : movie, member
			data.movieverse.MovieMemberDAO.save(lmovieverseMovieMember);
			data.movieverse.Badge lmovieverseBadge = data.movieverse.BadgeDAO.createBadge();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : achievements
			data.movieverse.BadgeDAO.save(lmovieverseBadge);
			data.movieverse.Friendship lmovieverseFriendship = data.movieverse.FriendshipDAO.createFriendship();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : requestedMuser, receivedMuser
			data.movieverse.FriendshipDAO.save(lmovieverseFriendship);
			data.movieverse.Comment lmovieverseComment = data.movieverse.CommentDAO.createComment();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : upvoter, likes, parent, commenter, movie
			data.movieverse.CommentDAO.save(lmovieverseComment);
			data.movieverse.Feed lmovieverseFeed = data.movieverse.FeedDAO.createFeed();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : idContent, type, user
			data.movieverse.FeedDAO.save(lmovieverseFeed);
			data.movieverse.Achievement lmovieverseAchievement = data.movieverse.AchievementDAO.createAchievement();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : mUser, badge
			data.movieverse.AchievementDAO.save(lmovieverseAchievement);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			CreateMovieverseData createMovieverseData = new CreateMovieverseData();
			try {
				createMovieverseData.createTestData();
			}
			finally {
				data.movieverse.MovieversePersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
