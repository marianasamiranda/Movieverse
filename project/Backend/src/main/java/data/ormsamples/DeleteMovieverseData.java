/**
 * Licensee: mariana(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class DeleteMovieverseData {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = data.movieverse.MovieversePersistentManager.instance().getSession().beginTransaction();
		try {
			data.movieverse.MUser ldatamovieverseMUser = data.movieverse.MUserDAO.loadMUserByQuery(null, null);
			// Delete the persistent object
			data.movieverse.MUserDAO.delete(ldatamovieverseMUser);
			data.movieverse.Movie ldatamovieverseMovie = data.movieverse.MovieDAO.loadMovieByQuery(null, null);
			// Delete the persistent object
			data.movieverse.MovieDAO.delete(ldatamovieverseMovie);
			data.movieverse.Genre ldatamovieverseGenre = data.movieverse.GenreDAO.loadGenreByQuery(null, null);
			// Delete the persistent object
			data.movieverse.GenreDAO.delete(ldatamovieverseGenre);
			data.movieverse.Company ldatamovieverseCompany = data.movieverse.CompanyDAO.loadCompanyByQuery(null, null);
			// Delete the persistent object
			data.movieverse.CompanyDAO.delete(ldatamovieverseCompany);
			data.movieverse.Country ldatamovieverseCountry = data.movieverse.CountryDAO.loadCountryByQuery(null, null);
			// Delete the persistent object
			data.movieverse.CountryDAO.delete(ldatamovieverseCountry);
			data.movieverse.UserMovie ldatamovieverseUserMovie = data.movieverse.UserMovieDAO.loadUserMovieByQuery(null, null);
			// Delete the persistent object
			data.movieverse.UserMovieDAO.delete(ldatamovieverseUserMovie);
			data.movieverse.Member ldatamovieverseMember = data.movieverse.MemberDAO.loadMemberByQuery(null, null);
			// Delete the persistent object
			data.movieverse.MemberDAO.delete(ldatamovieverseMember);
			data.movieverse.MovieMember ldatamovieverseMovieMember = data.movieverse.MovieMemberDAO.loadMovieMemberByQuery(null, null);
			// Delete the persistent object
			data.movieverse.MovieMemberDAO.delete(ldatamovieverseMovieMember);
			data.movieverse.Badge ldatamovieverseBadge = data.movieverse.BadgeDAO.loadBadgeByQuery(null, null);
			// Delete the persistent object
			data.movieverse.BadgeDAO.delete(ldatamovieverseBadge);
			data.movieverse.Friendship ldatamovieverseFriendship = data.movieverse.FriendshipDAO.loadFriendshipByQuery(null, null);
			// Delete the persistent object
			data.movieverse.FriendshipDAO.delete(ldatamovieverseFriendship);
			data.movieverse.Comment ldatamovieverseComment = data.movieverse.CommentDAO.loadCommentByQuery(null, null);
			// Delete the persistent object
			data.movieverse.CommentDAO.delete(ldatamovieverseComment);
			data.movieverse.Feed ldatamovieverseFeed = data.movieverse.FeedDAO.loadFeedByQuery(null, null);
			// Delete the persistent object
			data.movieverse.FeedDAO.delete(ldatamovieverseFeed);
			data.movieverse.Achievement ldatamovieverseAchievement = data.movieverse.AchievementDAO.loadAchievementByQuery(null, null);
			// Delete the persistent object
			data.movieverse.AchievementDAO.delete(ldatamovieverseAchievement);
			data.movieverse.Theater ldatamovieverseTheater = data.movieverse.TheaterDAO.loadTheaterByQuery(null, null);
			// Delete the persistent object
			data.movieverse.TheaterDAO.delete(ldatamovieverseTheater);
			data.movieverse.Showtime ldatamovieverseShowtime = data.movieverse.ShowtimeDAO.loadShowtimeByQuery(null, null);
			// Delete the persistent object
			data.movieverse.ShowtimeDAO.delete(ldatamovieverseShowtime);
			data.movieverse.Media ldatamovieverseMedia = data.movieverse.MediaDAO.loadMediaByQuery(null, null);
			// Delete the persistent object
			data.movieverse.MediaDAO.delete(ldatamovieverseMedia);
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
