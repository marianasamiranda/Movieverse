/**
 * Licensee: mariana(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class ListMovieverseData {
	private static final int ROW_COUNT = 100;
	
	public void listTestData() throws PersistentException {
		System.out.println("Listing MUser...");
		data.movieverse.MUser[] datamovieverseMUsers = data.movieverse.MUserDAO.listMUserByQuery(null, null);
		int length = Math.min(datamovieverseMUsers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(datamovieverseMUsers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Movie...");
		data.movieverse.Movie[] datamovieverseMovies = data.movieverse.MovieDAO.listMovieByQuery(null, null);
		length = Math.min(datamovieverseMovies.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(datamovieverseMovies[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Genre...");
		data.movieverse.Genre[] datamovieverseGenres = data.movieverse.GenreDAO.listGenreByQuery(null, null);
		length = Math.min(datamovieverseGenres.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(datamovieverseGenres[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Company...");
		data.movieverse.Company[] datamovieverseCompanys = data.movieverse.CompanyDAO.listCompanyByQuery(null, null);
		length = Math.min(datamovieverseCompanys.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(datamovieverseCompanys[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Country...");
		data.movieverse.Country[] datamovieverseCountrys = data.movieverse.CountryDAO.listCountryByQuery(null, null);
		length = Math.min(datamovieverseCountrys.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(datamovieverseCountrys[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing UserMovie...");
		data.movieverse.UserMovie[] datamovieverseUserMovies = data.movieverse.UserMovieDAO.listUserMovieByQuery(null, null);
		length = Math.min(datamovieverseUserMovies.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(datamovieverseUserMovies[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Member...");
		data.movieverse.Member[] datamovieverseMembers = data.movieverse.MemberDAO.listMemberByQuery(null, null);
		length = Math.min(datamovieverseMembers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(datamovieverseMembers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing MovieMember...");
		data.movieverse.MovieMember[] datamovieverseMovieMembers = data.movieverse.MovieMemberDAO.listMovieMemberByQuery(null, null);
		length = Math.min(datamovieverseMovieMembers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(datamovieverseMovieMembers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Badge...");
		data.movieverse.Badge[] datamovieverseBadges = data.movieverse.BadgeDAO.listBadgeByQuery(null, null);
		length = Math.min(datamovieverseBadges.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(datamovieverseBadges[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Friendship...");
		data.movieverse.Friendship[] datamovieverseFriendships = data.movieverse.FriendshipDAO.listFriendshipByQuery(null, null);
		length = Math.min(datamovieverseFriendships.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(datamovieverseFriendships[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Comment...");
		data.movieverse.Comment[] datamovieverseComments = data.movieverse.CommentDAO.listCommentByQuery(null, null);
		length = Math.min(datamovieverseComments.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(datamovieverseComments[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Feed...");
		data.movieverse.Feed[] datamovieverseFeeds = data.movieverse.FeedDAO.listFeedByQuery(null, null);
		length = Math.min(datamovieverseFeeds.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(datamovieverseFeeds[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Achievement...");
		data.movieverse.Achievement[] datamovieverseAchievements = data.movieverse.AchievementDAO.listAchievementByQuery(null, null);
		length = Math.min(datamovieverseAchievements.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(datamovieverseAchievements[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Theater...");
		data.movieverse.Theater[] datamovieverseTheaters = data.movieverse.TheaterDAO.listTheaterByQuery(null, null);
		length = Math.min(datamovieverseTheaters.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(datamovieverseTheaters[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Showtime...");
		data.movieverse.Showtime[] datamovieverseShowtimes = data.movieverse.ShowtimeDAO.listShowtimeByQuery(null, null);
		length = Math.min(datamovieverseShowtimes.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(datamovieverseShowtimes[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Media...");
		data.movieverse.Media[] datamovieverseMedias = data.movieverse.MediaDAO.listMediaByQuery(null, null);
		length = Math.min(datamovieverseMedias.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(datamovieverseMedias[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing MUser by Criteria...");
		data.movieverse.MUserCriteria ldatamovieverseMUserCriteria = new data.movieverse.MUserCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldatamovieverseMUserCriteria.id.eq();
		ldatamovieverseMUserCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.MUser[] datamovieverseMUsers = ldatamovieverseMUserCriteria.listMUser();
		int length =datamovieverseMUsers== null ? 0 : Math.min(datamovieverseMUsers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(datamovieverseMUsers[i]);
		}
		System.out.println(length + " MUser record(s) retrieved."); 
		
		System.out.println("Listing Movie by Criteria...");
		data.movieverse.MovieCriteria ldatamovieverseMovieCriteria = new data.movieverse.MovieCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldatamovieverseMovieCriteria.id.eq();
		ldatamovieverseMovieCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Movie[] datamovieverseMovies = ldatamovieverseMovieCriteria.listMovie();
		length =datamovieverseMovies== null ? 0 : Math.min(datamovieverseMovies.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(datamovieverseMovies[i]);
		}
		System.out.println(length + " Movie record(s) retrieved."); 
		
		System.out.println("Listing Genre by Criteria...");
		data.movieverse.GenreCriteria ldatamovieverseGenreCriteria = new data.movieverse.GenreCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldatamovieverseGenreCriteria.id.eq();
		ldatamovieverseGenreCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Genre[] datamovieverseGenres = ldatamovieverseGenreCriteria.listGenre();
		length =datamovieverseGenres== null ? 0 : Math.min(datamovieverseGenres.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(datamovieverseGenres[i]);
		}
		System.out.println(length + " Genre record(s) retrieved."); 
		
		System.out.println("Listing Company by Criteria...");
		data.movieverse.CompanyCriteria ldatamovieverseCompanyCriteria = new data.movieverse.CompanyCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldatamovieverseCompanyCriteria.id.eq();
		ldatamovieverseCompanyCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Company[] datamovieverseCompanys = ldatamovieverseCompanyCriteria.listCompany();
		length =datamovieverseCompanys== null ? 0 : Math.min(datamovieverseCompanys.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(datamovieverseCompanys[i]);
		}
		System.out.println(length + " Company record(s) retrieved."); 
		
		System.out.println("Listing Country by Criteria...");
		data.movieverse.CountryCriteria ldatamovieverseCountryCriteria = new data.movieverse.CountryCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldatamovieverseCountryCriteria.id.eq();
		ldatamovieverseCountryCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Country[] datamovieverseCountrys = ldatamovieverseCountryCriteria.listCountry();
		length =datamovieverseCountrys== null ? 0 : Math.min(datamovieverseCountrys.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(datamovieverseCountrys[i]);
		}
		System.out.println(length + " Country record(s) retrieved."); 
		
		System.out.println("Listing UserMovie by Criteria...");
		data.movieverse.UserMovieCriteria ldatamovieverseUserMovieCriteria = new data.movieverse.UserMovieCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldatamovieverseUserMovieCriteria.id.eq();
		ldatamovieverseUserMovieCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.UserMovie[] datamovieverseUserMovies = ldatamovieverseUserMovieCriteria.listUserMovie();
		length =datamovieverseUserMovies== null ? 0 : Math.min(datamovieverseUserMovies.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(datamovieverseUserMovies[i]);
		}
		System.out.println(length + " UserMovie record(s) retrieved."); 
		
		System.out.println("Listing Member by Criteria...");
		data.movieverse.MemberCriteria ldatamovieverseMemberCriteria = new data.movieverse.MemberCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldatamovieverseMemberCriteria.id.eq();
		ldatamovieverseMemberCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Member[] datamovieverseMembers = ldatamovieverseMemberCriteria.listMember();
		length =datamovieverseMembers== null ? 0 : Math.min(datamovieverseMembers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(datamovieverseMembers[i]);
		}
		System.out.println(length + " Member record(s) retrieved."); 
		
		System.out.println("Listing MovieMember by Criteria...");
		data.movieverse.MovieMemberCriteria ldatamovieverseMovieMemberCriteria = new data.movieverse.MovieMemberCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldatamovieverseMovieMemberCriteria.id.eq();
		ldatamovieverseMovieMemberCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.MovieMember[] datamovieverseMovieMembers = ldatamovieverseMovieMemberCriteria.listMovieMember();
		length =datamovieverseMovieMembers== null ? 0 : Math.min(datamovieverseMovieMembers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(datamovieverseMovieMembers[i]);
		}
		System.out.println(length + " MovieMember record(s) retrieved."); 
		
		System.out.println("Listing Badge by Criteria...");
		data.movieverse.BadgeCriteria ldatamovieverseBadgeCriteria = new data.movieverse.BadgeCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldatamovieverseBadgeCriteria.id.eq();
		ldatamovieverseBadgeCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Badge[] datamovieverseBadges = ldatamovieverseBadgeCriteria.listBadge();
		length =datamovieverseBadges== null ? 0 : Math.min(datamovieverseBadges.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(datamovieverseBadges[i]);
		}
		System.out.println(length + " Badge record(s) retrieved."); 
		
		System.out.println("Listing Friendship by Criteria...");
		data.movieverse.FriendshipCriteria ldatamovieverseFriendshipCriteria = new data.movieverse.FriendshipCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldatamovieverseFriendshipCriteria.id.eq();
		ldatamovieverseFriendshipCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Friendship[] datamovieverseFriendships = ldatamovieverseFriendshipCriteria.listFriendship();
		length =datamovieverseFriendships== null ? 0 : Math.min(datamovieverseFriendships.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(datamovieverseFriendships[i]);
		}
		System.out.println(length + " Friendship record(s) retrieved."); 
		
		System.out.println("Listing Comment by Criteria...");
		data.movieverse.CommentCriteria ldatamovieverseCommentCriteria = new data.movieverse.CommentCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldatamovieverseCommentCriteria.id.eq();
		ldatamovieverseCommentCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Comment[] datamovieverseComments = ldatamovieverseCommentCriteria.listComment();
		length =datamovieverseComments== null ? 0 : Math.min(datamovieverseComments.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(datamovieverseComments[i]);
		}
		System.out.println(length + " Comment record(s) retrieved."); 
		
		System.out.println("Listing Feed by Criteria...");
		data.movieverse.FeedCriteria ldatamovieverseFeedCriteria = new data.movieverse.FeedCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldatamovieverseFeedCriteria.id.eq();
		ldatamovieverseFeedCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Feed[] datamovieverseFeeds = ldatamovieverseFeedCriteria.listFeed();
		length =datamovieverseFeeds== null ? 0 : Math.min(datamovieverseFeeds.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(datamovieverseFeeds[i]);
		}
		System.out.println(length + " Feed record(s) retrieved."); 
		
		System.out.println("Listing Achievement by Criteria...");
		data.movieverse.AchievementCriteria ldatamovieverseAchievementCriteria = new data.movieverse.AchievementCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldatamovieverseAchievementCriteria.id.eq();
		ldatamovieverseAchievementCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Achievement[] datamovieverseAchievements = ldatamovieverseAchievementCriteria.listAchievement();
		length =datamovieverseAchievements== null ? 0 : Math.min(datamovieverseAchievements.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(datamovieverseAchievements[i]);
		}
		System.out.println(length + " Achievement record(s) retrieved."); 
		
		System.out.println("Listing Theater by Criteria...");
		data.movieverse.TheaterCriteria ldatamovieverseTheaterCriteria = new data.movieverse.TheaterCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldatamovieverseTheaterCriteria.id.eq();
		ldatamovieverseTheaterCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Theater[] datamovieverseTheaters = ldatamovieverseTheaterCriteria.listTheater();
		length =datamovieverseTheaters== null ? 0 : Math.min(datamovieverseTheaters.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(datamovieverseTheaters[i]);
		}
		System.out.println(length + " Theater record(s) retrieved."); 
		
		System.out.println("Listing Showtime by Criteria...");
		data.movieverse.ShowtimeCriteria ldatamovieverseShowtimeCriteria = new data.movieverse.ShowtimeCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldatamovieverseShowtimeCriteria.id.eq();
		ldatamovieverseShowtimeCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Showtime[] datamovieverseShowtimes = ldatamovieverseShowtimeCriteria.listShowtime();
		length =datamovieverseShowtimes== null ? 0 : Math.min(datamovieverseShowtimes.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(datamovieverseShowtimes[i]);
		}
		System.out.println(length + " Showtime record(s) retrieved."); 
		
		System.out.println("Listing Media by Criteria...");
		data.movieverse.MediaCriteria ldatamovieverseMediaCriteria = new data.movieverse.MediaCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ldatamovieverseMediaCriteria.id.eq();
		ldatamovieverseMediaCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Media[] datamovieverseMedias = ldatamovieverseMediaCriteria.listMedia();
		length =datamovieverseMedias== null ? 0 : Math.min(datamovieverseMedias.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(datamovieverseMedias[i]);
		}
		System.out.println(length + " Media record(s) retrieved."); 
		
	}
	
	public static void main(String[] args) {
		try {
			ListMovieverseData listMovieverseData = new ListMovieverseData();
			try {
				listMovieverseData.listTestData();
				//listMovieverseData.listByCriteria();
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
