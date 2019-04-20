/**
 * Licensee: mariana(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class RetrieveAndUpdateMovieverseData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = data.movieverse.MovieversePersistentManager.instance().getSession().beginTransaction();
		try {
			data.movieverse.MUser ldatamovieverseMUser = data.movieverse.MUserDAO.loadMUserByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.MUserDAO.save(ldatamovieverseMUser);
			data.movieverse.Movie ldatamovieverseMovie = data.movieverse.MovieDAO.loadMovieByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.MovieDAO.save(ldatamovieverseMovie);
			data.movieverse.Genre ldatamovieverseGenre = data.movieverse.GenreDAO.loadGenreByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.GenreDAO.save(ldatamovieverseGenre);
			data.movieverse.Company ldatamovieverseCompany = data.movieverse.CompanyDAO.loadCompanyByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.CompanyDAO.save(ldatamovieverseCompany);
			data.movieverse.Country ldatamovieverseCountry = data.movieverse.CountryDAO.loadCountryByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.CountryDAO.save(ldatamovieverseCountry);
			data.movieverse.UserMovie ldatamovieverseUserMovie = data.movieverse.UserMovieDAO.loadUserMovieByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.UserMovieDAO.save(ldatamovieverseUserMovie);
			data.movieverse.Member ldatamovieverseMember = data.movieverse.MemberDAO.loadMemberByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.MemberDAO.save(ldatamovieverseMember);
			data.movieverse.MovieMember ldatamovieverseMovieMember = data.movieverse.MovieMemberDAO.loadMovieMemberByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.MovieMemberDAO.save(ldatamovieverseMovieMember);
			data.movieverse.Badge ldatamovieverseBadge = data.movieverse.BadgeDAO.loadBadgeByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.BadgeDAO.save(ldatamovieverseBadge);
			data.movieverse.Friendship ldatamovieverseFriendship = data.movieverse.FriendshipDAO.loadFriendshipByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.FriendshipDAO.save(ldatamovieverseFriendship);
			data.movieverse.Comment ldatamovieverseComment = data.movieverse.CommentDAO.loadCommentByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.CommentDAO.save(ldatamovieverseComment);
			data.movieverse.Feed ldatamovieverseFeed = data.movieverse.FeedDAO.loadFeedByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.FeedDAO.save(ldatamovieverseFeed);
			data.movieverse.Achievement ldatamovieverseAchievement = data.movieverse.AchievementDAO.loadAchievementByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.AchievementDAO.save(ldatamovieverseAchievement);
			data.movieverse.Theater ldatamovieverseTheater = data.movieverse.TheaterDAO.loadTheaterByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.TheaterDAO.save(ldatamovieverseTheater);
			data.movieverse.Showtime ldatamovieverseShowtime = data.movieverse.ShowtimeDAO.loadShowtimeByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.ShowtimeDAO.save(ldatamovieverseShowtime);
			data.movieverse.Media ldatamovieverseMedia = data.movieverse.MediaDAO.loadMediaByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.MediaDAO.save(ldatamovieverseMedia);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving MUser by MUserCriteria");
		data.movieverse.MUserCriteria ldatamovieverseMUserCriteria = new data.movieverse.MUserCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldatamovieverseMUserCriteria.id.eq();
		System.out.println(ldatamovieverseMUserCriteria.uniqueMUser());
		
		System.out.println("Retrieving Movie by MovieCriteria");
		data.movieverse.MovieCriteria ldatamovieverseMovieCriteria = new data.movieverse.MovieCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldatamovieverseMovieCriteria.id.eq();
		System.out.println(ldatamovieverseMovieCriteria.uniqueMovie());
		
		System.out.println("Retrieving Genre by GenreCriteria");
		data.movieverse.GenreCriteria ldatamovieverseGenreCriteria = new data.movieverse.GenreCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldatamovieverseGenreCriteria.id.eq();
		System.out.println(ldatamovieverseGenreCriteria.uniqueGenre());
		
		System.out.println("Retrieving Company by CompanyCriteria");
		data.movieverse.CompanyCriteria ldatamovieverseCompanyCriteria = new data.movieverse.CompanyCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldatamovieverseCompanyCriteria.id.eq();
		System.out.println(ldatamovieverseCompanyCriteria.uniqueCompany());
		
		System.out.println("Retrieving Country by CountryCriteria");
		data.movieverse.CountryCriteria ldatamovieverseCountryCriteria = new data.movieverse.CountryCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldatamovieverseCountryCriteria.id.eq();
		System.out.println(ldatamovieverseCountryCriteria.uniqueCountry());
		
		System.out.println("Retrieving UserMovie by UserMovieCriteria");
		data.movieverse.UserMovieCriteria ldatamovieverseUserMovieCriteria = new data.movieverse.UserMovieCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldatamovieverseUserMovieCriteria.id.eq();
		System.out.println(ldatamovieverseUserMovieCriteria.uniqueUserMovie());
		
		System.out.println("Retrieving Member by MemberCriteria");
		data.movieverse.MemberCriteria ldatamovieverseMemberCriteria = new data.movieverse.MemberCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldatamovieverseMemberCriteria.id.eq();
		System.out.println(ldatamovieverseMemberCriteria.uniqueMember());
		
		System.out.println("Retrieving MovieMember by MovieMemberCriteria");
		data.movieverse.MovieMemberCriteria ldatamovieverseMovieMemberCriteria = new data.movieverse.MovieMemberCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldatamovieverseMovieMemberCriteria.id.eq();
		System.out.println(ldatamovieverseMovieMemberCriteria.uniqueMovieMember());
		
		System.out.println("Retrieving Badge by BadgeCriteria");
		data.movieverse.BadgeCriteria ldatamovieverseBadgeCriteria = new data.movieverse.BadgeCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldatamovieverseBadgeCriteria.id.eq();
		System.out.println(ldatamovieverseBadgeCriteria.uniqueBadge());
		
		System.out.println("Retrieving Friendship by FriendshipCriteria");
		data.movieverse.FriendshipCriteria ldatamovieverseFriendshipCriteria = new data.movieverse.FriendshipCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldatamovieverseFriendshipCriteria.id.eq();
		System.out.println(ldatamovieverseFriendshipCriteria.uniqueFriendship());
		
		System.out.println("Retrieving Comment by CommentCriteria");
		data.movieverse.CommentCriteria ldatamovieverseCommentCriteria = new data.movieverse.CommentCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldatamovieverseCommentCriteria.id.eq();
		System.out.println(ldatamovieverseCommentCriteria.uniqueComment());
		
		System.out.println("Retrieving Feed by FeedCriteria");
		data.movieverse.FeedCriteria ldatamovieverseFeedCriteria = new data.movieverse.FeedCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldatamovieverseFeedCriteria.id.eq();
		System.out.println(ldatamovieverseFeedCriteria.uniqueFeed());
		
		System.out.println("Retrieving Achievement by AchievementCriteria");
		data.movieverse.AchievementCriteria ldatamovieverseAchievementCriteria = new data.movieverse.AchievementCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldatamovieverseAchievementCriteria.id.eq();
		System.out.println(ldatamovieverseAchievementCriteria.uniqueAchievement());
		
		System.out.println("Retrieving Theater by TheaterCriteria");
		data.movieverse.TheaterCriteria ldatamovieverseTheaterCriteria = new data.movieverse.TheaterCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldatamovieverseTheaterCriteria.id.eq();
		System.out.println(ldatamovieverseTheaterCriteria.uniqueTheater());
		
		System.out.println("Retrieving Showtime by ShowtimeCriteria");
		data.movieverse.ShowtimeCriteria ldatamovieverseShowtimeCriteria = new data.movieverse.ShowtimeCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldatamovieverseShowtimeCriteria.id.eq();
		System.out.println(ldatamovieverseShowtimeCriteria.uniqueShowtime());
		
		System.out.println("Retrieving Media by MediaCriteria");
		data.movieverse.MediaCriteria ldatamovieverseMediaCriteria = new data.movieverse.MediaCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//ldatamovieverseMediaCriteria.id.eq();
		System.out.println(ldatamovieverseMediaCriteria.uniqueMedia());
		
	}
	
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateMovieverseData retrieveAndUpdateMovieverseData = new RetrieveAndUpdateMovieverseData();
			try {
				retrieveAndUpdateMovieverseData.retrieveAndUpdateTestData();
				//retrieveAndUpdateMovieverseData.retrieveByCriteria();
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
