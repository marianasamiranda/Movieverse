/**
 * Licensee: mariana(Universidade do Minho)
 * License Type: Academic
 */
package data.ormsamples;

import org.orm.*;
public class DeleteMovieverseData {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = data.movieverse.MovieversePersistentManager.instance().getSession().beginTransaction();
		try {
			data.movieverse.MUser lmovieverseMUser = data.movieverse.MUserDAO.loadMUserByQuery(null, null);
			// Delete the persistent object
			data.movieverse.MUserDAO.delete(lmovieverseMUser);
			data.movieverse.Movie lmovieverseMovie = data.movieverse.MovieDAO.loadMovieByQuery(null, null);
			// Delete the persistent object
			data.movieverse.MovieDAO.delete(lmovieverseMovie);
			data.movieverse.Genre lmovieverseGenre = data.movieverse.GenreDAO.loadGenreByQuery(null, null);
			// Delete the persistent object
			data.movieverse.GenreDAO.delete(lmovieverseGenre);
			data.movieverse.Company lmovieverseCompany = data.movieverse.CompanyDAO.loadCompanyByQuery(null, null);
			// Delete the persistent object
			data.movieverse.CompanyDAO.delete(lmovieverseCompany);
			data.movieverse.Country lmovieverseCountry = data.movieverse.CountryDAO.loadCountryByQuery(null, null);
			// Delete the persistent object
			data.movieverse.CountryDAO.delete(lmovieverseCountry);
			data.movieverse.MUserMovie lmovieverseMUserMovie = data.movieverse.MUserMovieDAO.loadMUserMovieByQuery(null, null);
			// Delete the persistent object
			data.movieverse.MUserMovieDAO.delete(lmovieverseMUserMovie);
			data.movieverse.Member lmovieverseMember = data.movieverse.MemberDAO.loadMemberByQuery(null, null);
			// Delete the persistent object
			data.movieverse.MemberDAO.delete(lmovieverseMember);
			data.movieverse.MovieMember lmovieverseMovieMember = data.movieverse.MovieMemberDAO.loadMovieMemberByQuery(null, null);
			// Delete the persistent object
			data.movieverse.MovieMemberDAO.delete(lmovieverseMovieMember);
			data.movieverse.Badge lmovieverseBadge = data.movieverse.BadgeDAO.loadBadgeByQuery(null, null);
			// Delete the persistent object
			data.movieverse.BadgeDAO.delete(lmovieverseBadge);
			data.movieverse.Friendship lmovieverseFriendship = data.movieverse.FriendshipDAO.loadFriendshipByQuery(null, null);
			// Delete the persistent object
			data.movieverse.FriendshipDAO.delete(lmovieverseFriendship);
			data.movieverse.Comment lmovieverseComment = data.movieverse.CommentDAO.loadCommentByQuery(null, null);
			// Delete the persistent object
			data.movieverse.CommentDAO.delete(lmovieverseComment);
			data.movieverse.Feed lmovieverseFeed = data.movieverse.FeedDAO.loadFeedByQuery(null, null);
			// Delete the persistent object
			data.movieverse.FeedDAO.delete(lmovieverseFeed);
			data.movieverse.Achievement lmovieverseAchievement = data.movieverse.AchievementDAO.loadAchievementByQuery(null, null);
			// Delete the persistent object
			data.movieverse.AchievementDAO.delete(lmovieverseAchievement);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			DeleteMovieverseData deleteMovieverseData = new DeleteMovieverseData();
			try {
				deleteMovieverseData.deleteTestData();
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
