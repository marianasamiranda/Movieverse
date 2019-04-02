/**
 * Licensee: mariana(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class CreateMovieverseData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = movieverse.MovieversePersistentManager.instance().getSession().beginTransaction();
		try {
			movieverse.MUser lmovieverseMUser = movieverse.MUserDAO.createMUser();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : friends, receivedFriendships, mUserMovies, achievements, feed, comments, requestedFriendships, hoursCount, movieCount, gender, user_country
			movieverse.MUserDAO.save(lmovieverseMUser);
			movieverse.Movie lmovieverseMovie = movieverse.MovieDAO.createMovie();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : movieMembers, comments, companies, genres, mUserMovies, ratingCount, ratingSum, runtime
			movieverse.MovieDAO.save(lmovieverseMovie);
			movieverse.Genre lmovieverseGenre = movieverse.GenreDAO.createGenre();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : movies
			movieverse.GenreDAO.save(lmovieverseGenre);
			movieverse.Company lmovieverseCompany = movieverse.CompanyDAO.createCompany();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : movies, company_country
			movieverse.CompanyDAO.save(lmovieverseCompany);
			movieverse.Country lmovieverseCountry = movieverse.CountryDAO.createCountry();
			// Initialize the properties of the persistent object here
			movieverse.CountryDAO.save(lmovieverseCountry);
			movieverse.MUserMovie lmovieverseMUserMovie = movieverse.MUserMovieDAO.createMUserMovie();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : mUser, movie, visible, favourite, rating, status
			movieverse.MUserMovieDAO.save(lmovieverseMUserMovie);
			movieverse.Member lmovieverseMember = movieverse.MemberDAO.createMember();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : movieMembers, gender
			movieverse.MemberDAO.save(lmovieverseMember);
			movieverse.MovieMember lmovieverseMovieMember = movieverse.MovieMemberDAO.createMovieMember();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : movie, member
			movieverse.MovieMemberDAO.save(lmovieverseMovieMember);
			movieverse.Badge lmovieverseBadge = movieverse.BadgeDAO.createBadge();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : achievements
			movieverse.BadgeDAO.save(lmovieverseBadge);
			movieverse.Friendship lmovieverseFriendship = movieverse.FriendshipDAO.createFriendship();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : requestedMuser, receivedMuser
			movieverse.FriendshipDAO.save(lmovieverseFriendship);
			movieverse.Comment lmovieverseComment = movieverse.CommentDAO.createComment();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : upvoter, likes, parent, commenter, movie
			movieverse.CommentDAO.save(lmovieverseComment);
			movieverse.Feed lmovieverseFeed = movieverse.FeedDAO.createFeed();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : idContent, type, user
			movieverse.FeedDAO.save(lmovieverseFeed);
			movieverse.Achievement lmovieverseAchievement = movieverse.AchievementDAO.createAchievement();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : mUser, badge
			movieverse.AchievementDAO.save(lmovieverseAchievement);
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
				movieverse.MovieversePersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
