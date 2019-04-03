/**
 * Licensee: mariana(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class DeleteMovieverseData {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = movieverse.MovieversePersistentManager.instance().getSession().beginTransaction();
		try {
			movieverse.MUser lmovieverseMUser = movieverse.MUserDAO.loadMUserByQuery(null, null);
			// Delete the persistent object
			movieverse.MUserDAO.delete(lmovieverseMUser);
			movieverse.Movie lmovieverseMovie = movieverse.MovieDAO.loadMovieByQuery(null, null);
			// Delete the persistent object
			movieverse.MovieDAO.delete(lmovieverseMovie);
			movieverse.Genre lmovieverseGenre = movieverse.GenreDAO.loadGenreByQuery(null, null);
			// Delete the persistent object
			movieverse.GenreDAO.delete(lmovieverseGenre);
			movieverse.Company lmovieverseCompany = movieverse.CompanyDAO.loadCompanyByQuery(null, null);
			// Delete the persistent object
			movieverse.CompanyDAO.delete(lmovieverseCompany);
			movieverse.Country lmovieverseCountry = movieverse.CountryDAO.loadCountryByQuery(null, null);
			// Delete the persistent object
			movieverse.CountryDAO.delete(lmovieverseCountry);
			movieverse.MUserMovie lmovieverseMUserMovie = movieverse.MUserMovieDAO.loadMUserMovieByQuery(null, null);
			// Delete the persistent object
			movieverse.MUserMovieDAO.delete(lmovieverseMUserMovie);
			movieverse.Member lmovieverseMember = movieverse.MemberDAO.loadMemberByQuery(null, null);
			// Delete the persistent object
			movieverse.MemberDAO.delete(lmovieverseMember);
			movieverse.MovieMember lmovieverseMovieMember = movieverse.MovieMemberDAO.loadMovieMemberByQuery(null, null);
			// Delete the persistent object
			movieverse.MovieMemberDAO.delete(lmovieverseMovieMember);
			movieverse.Badge lmovieverseBadge = movieverse.BadgeDAO.loadBadgeByQuery(null, null);
			// Delete the persistent object
			movieverse.BadgeDAO.delete(lmovieverseBadge);
			movieverse.Friendship lmovieverseFriendship = movieverse.FriendshipDAO.loadFriendshipByQuery(null, null);
			// Delete the persistent object
			movieverse.FriendshipDAO.delete(lmovieverseFriendship);
			movieverse.Comment lmovieverseComment = movieverse.CommentDAO.loadCommentByQuery(null, null);
			// Delete the persistent object
			movieverse.CommentDAO.delete(lmovieverseComment);
			movieverse.Feed lmovieverseFeed = movieverse.FeedDAO.loadFeedByQuery(null, null);
			// Delete the persistent object
			movieverse.FeedDAO.delete(lmovieverseFeed);
			movieverse.Achievement lmovieverseAchievement = movieverse.AchievementDAO.loadAchievementByQuery(null, null);
			// Delete the persistent object
			movieverse.AchievementDAO.delete(lmovieverseAchievement);
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
				movieverse.MovieversePersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
