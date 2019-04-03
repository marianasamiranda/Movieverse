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
		movieverse.MUser[] movieverseMUsers = movieverse.MUserDAO.listMUserByQuery(null, null);
		int length = Math.min(movieverseMUsers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseMUsers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Movie...");
		movieverse.Movie[] movieverseMovies = movieverse.MovieDAO.listMovieByQuery(null, null);
		length = Math.min(movieverseMovies.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseMovies[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Genre...");
		movieverse.Genre[] movieverseGenres = movieverse.GenreDAO.listGenreByQuery(null, null);
		length = Math.min(movieverseGenres.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseGenres[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Company...");
		movieverse.Company[] movieverseCompanys = movieverse.CompanyDAO.listCompanyByQuery(null, null);
		length = Math.min(movieverseCompanys.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseCompanys[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Country...");
		movieverse.Country[] movieverseCountrys = movieverse.CountryDAO.listCountryByQuery(null, null);
		length = Math.min(movieverseCountrys.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseCountrys[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing MUserMovie...");
		movieverse.MUserMovie[] movieverseMUserMovies = movieverse.MUserMovieDAO.listMUserMovieByQuery(null, null);
		length = Math.min(movieverseMUserMovies.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseMUserMovies[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Member...");
		movieverse.Member[] movieverseMembers = movieverse.MemberDAO.listMemberByQuery(null, null);
		length = Math.min(movieverseMembers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseMembers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing MovieMember...");
		movieverse.MovieMember[] movieverseMovieMembers = movieverse.MovieMemberDAO.listMovieMemberByQuery(null, null);
		length = Math.min(movieverseMovieMembers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseMovieMembers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Badge...");
		movieverse.Badge[] movieverseBadges = movieverse.BadgeDAO.listBadgeByQuery(null, null);
		length = Math.min(movieverseBadges.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseBadges[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Friendship...");
		movieverse.Friendship[] movieverseFriendships = movieverse.FriendshipDAO.listFriendshipByQuery(null, null);
		length = Math.min(movieverseFriendships.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseFriendships[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Comment...");
		movieverse.Comment[] movieverseComments = movieverse.CommentDAO.listCommentByQuery(null, null);
		length = Math.min(movieverseComments.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseComments[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Feed...");
		movieverse.Feed[] movieverseFeeds = movieverse.FeedDAO.listFeedByQuery(null, null);
		length = Math.min(movieverseFeeds.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseFeeds[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Achievement...");
		movieverse.Achievement[] movieverseAchievements = movieverse.AchievementDAO.listAchievementByQuery(null, null);
		length = Math.min(movieverseAchievements.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseAchievements[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing MUser by Criteria...");
		movieverse.MUserCriteria lmovieverseMUserCriteria = new movieverse.MUserCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseMUserCriteria.id.eq();
		lmovieverseMUserCriteria.setMaxResults(ROW_COUNT);
		movieverse.MUser[] movieverseMUsers = lmovieverseMUserCriteria.listMUser();
		int length =movieverseMUsers== null ? 0 : Math.min(movieverseMUsers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseMUsers[i]);
		}
		System.out.println(length + " MUser record(s) retrieved."); 
		
		System.out.println("Listing Movie by Criteria...");
		movieverse.MovieCriteria lmovieverseMovieCriteria = new movieverse.MovieCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseMovieCriteria.id.eq();
		lmovieverseMovieCriteria.setMaxResults(ROW_COUNT);
		movieverse.Movie[] movieverseMovies = lmovieverseMovieCriteria.listMovie();
		length =movieverseMovies== null ? 0 : Math.min(movieverseMovies.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseMovies[i]);
		}
		System.out.println(length + " Movie record(s) retrieved."); 
		
		System.out.println("Listing Genre by Criteria...");
		movieverse.GenreCriteria lmovieverseGenreCriteria = new movieverse.GenreCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseGenreCriteria.id.eq();
		lmovieverseGenreCriteria.setMaxResults(ROW_COUNT);
		movieverse.Genre[] movieverseGenres = lmovieverseGenreCriteria.listGenre();
		length =movieverseGenres== null ? 0 : Math.min(movieverseGenres.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseGenres[i]);
		}
		System.out.println(length + " Genre record(s) retrieved."); 
		
		System.out.println("Listing Company by Criteria...");
		movieverse.CompanyCriteria lmovieverseCompanyCriteria = new movieverse.CompanyCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseCompanyCriteria.id.eq();
		lmovieverseCompanyCriteria.setMaxResults(ROW_COUNT);
		movieverse.Company[] movieverseCompanys = lmovieverseCompanyCriteria.listCompany();
		length =movieverseCompanys== null ? 0 : Math.min(movieverseCompanys.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseCompanys[i]);
		}
		System.out.println(length + " Company record(s) retrieved."); 
		
		System.out.println("Listing Country by Criteria...");
		movieverse.CountryCriteria lmovieverseCountryCriteria = new movieverse.CountryCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseCountryCriteria.id.eq();
		lmovieverseCountryCriteria.setMaxResults(ROW_COUNT);
		movieverse.Country[] movieverseCountrys = lmovieverseCountryCriteria.listCountry();
		length =movieverseCountrys== null ? 0 : Math.min(movieverseCountrys.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseCountrys[i]);
		}
		System.out.println(length + " Country record(s) retrieved."); 
		
		System.out.println("Listing MUserMovie by Criteria...");
		movieverse.MUserMovieCriteria lmovieverseMUserMovieCriteria = new movieverse.MUserMovieCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseMUserMovieCriteria.id.eq();
		lmovieverseMUserMovieCriteria.setMaxResults(ROW_COUNT);
		movieverse.MUserMovie[] movieverseMUserMovies = lmovieverseMUserMovieCriteria.listMUserMovie();
		length =movieverseMUserMovies== null ? 0 : Math.min(movieverseMUserMovies.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseMUserMovies[i]);
		}
		System.out.println(length + " MUserMovie record(s) retrieved."); 
		
		System.out.println("Listing Member by Criteria...");
		movieverse.MemberCriteria lmovieverseMemberCriteria = new movieverse.MemberCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseMemberCriteria.id.eq();
		lmovieverseMemberCriteria.setMaxResults(ROW_COUNT);
		movieverse.Member[] movieverseMembers = lmovieverseMemberCriteria.listMember();
		length =movieverseMembers== null ? 0 : Math.min(movieverseMembers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseMembers[i]);
		}
		System.out.println(length + " Member record(s) retrieved."); 
		
		System.out.println("Listing MovieMember by Criteria...");
		movieverse.MovieMemberCriteria lmovieverseMovieMemberCriteria = new movieverse.MovieMemberCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseMovieMemberCriteria.id.eq();
		lmovieverseMovieMemberCriteria.setMaxResults(ROW_COUNT);
		movieverse.MovieMember[] movieverseMovieMembers = lmovieverseMovieMemberCriteria.listMovieMember();
		length =movieverseMovieMembers== null ? 0 : Math.min(movieverseMovieMembers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseMovieMembers[i]);
		}
		System.out.println(length + " MovieMember record(s) retrieved."); 
		
		System.out.println("Listing Badge by Criteria...");
		movieverse.BadgeCriteria lmovieverseBadgeCriteria = new movieverse.BadgeCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseBadgeCriteria.id.eq();
		lmovieverseBadgeCriteria.setMaxResults(ROW_COUNT);
		movieverse.Badge[] movieverseBadges = lmovieverseBadgeCriteria.listBadge();
		length =movieverseBadges== null ? 0 : Math.min(movieverseBadges.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseBadges[i]);
		}
		System.out.println(length + " Badge record(s) retrieved."); 
		
		System.out.println("Listing Friendship by Criteria...");
		movieverse.FriendshipCriteria lmovieverseFriendshipCriteria = new movieverse.FriendshipCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseFriendshipCriteria.id.eq();
		lmovieverseFriendshipCriteria.setMaxResults(ROW_COUNT);
		movieverse.Friendship[] movieverseFriendships = lmovieverseFriendshipCriteria.listFriendship();
		length =movieverseFriendships== null ? 0 : Math.min(movieverseFriendships.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseFriendships[i]);
		}
		System.out.println(length + " Friendship record(s) retrieved."); 
		
		System.out.println("Listing Comment by Criteria...");
		movieverse.CommentCriteria lmovieverseCommentCriteria = new movieverse.CommentCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseCommentCriteria.id.eq();
		lmovieverseCommentCriteria.setMaxResults(ROW_COUNT);
		movieverse.Comment[] movieverseComments = lmovieverseCommentCriteria.listComment();
		length =movieverseComments== null ? 0 : Math.min(movieverseComments.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseComments[i]);
		}
		System.out.println(length + " Comment record(s) retrieved."); 
		
		System.out.println("Listing Feed by Criteria...");
		movieverse.FeedCriteria lmovieverseFeedCriteria = new movieverse.FeedCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseFeedCriteria.id.eq();
		lmovieverseFeedCriteria.setMaxResults(ROW_COUNT);
		movieverse.Feed[] movieverseFeeds = lmovieverseFeedCriteria.listFeed();
		length =movieverseFeeds== null ? 0 : Math.min(movieverseFeeds.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseFeeds[i]);
		}
		System.out.println(length + " Feed record(s) retrieved."); 
		
		System.out.println("Listing Achievement by Criteria...");
		movieverse.AchievementCriteria lmovieverseAchievementCriteria = new movieverse.AchievementCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseAchievementCriteria.id.eq();
		lmovieverseAchievementCriteria.setMaxResults(ROW_COUNT);
		movieverse.Achievement[] movieverseAchievements = lmovieverseAchievementCriteria.listAchievement();
		length =movieverseAchievements== null ? 0 : Math.min(movieverseAchievements.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseAchievements[i]);
		}
		System.out.println(length + " Achievement record(s) retrieved."); 
		
	}
	
	public static void main(String[] args) {
		try {
			ListMovieverseData listMovieverseData = new ListMovieverseData();
			try {
				listMovieverseData.listTestData();
				//listMovieverseData.listByCriteria();
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
