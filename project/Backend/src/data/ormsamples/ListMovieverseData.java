/**
 * Licensee: mariana(Universidade do Minho)
 * License Type: Academic
 */
package data.ormsamples;

import org.orm.*;
public class ListMovieverseData {
	private static final int ROW_COUNT = 100;
	
	public void listTestData() throws PersistentException {
		System.out.println("Listing MUser...");
		data.movieverse.MUser[] movieverseMUsers = data.movieverse.MUserDAO.listMUserByQuery(null, null);
		int length = Math.min(movieverseMUsers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseMUsers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Movie...");
		data.movieverse.Movie[] movieverseMovies = data.movieverse.MovieDAO.listMovieByQuery(null, null);
		length = Math.min(movieverseMovies.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseMovies[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Genre...");
		data.movieverse.Genre[] movieverseGenres = data.movieverse.GenreDAO.listGenreByQuery(null, null);
		length = Math.min(movieverseGenres.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseGenres[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Company...");
		data.movieverse.Company[] movieverseCompanys = data.movieverse.CompanyDAO.listCompanyByQuery(null, null);
		length = Math.min(movieverseCompanys.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseCompanys[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Country...");
		data.movieverse.Country[] movieverseCountrys = data.movieverse.CountryDAO.listCountryByQuery(null, null);
		length = Math.min(movieverseCountrys.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseCountrys[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing MUserMovie...");
		data.movieverse.MUserMovie[] movieverseMUserMovies = data.movieverse.MUserMovieDAO.listMUserMovieByQuery(null, null);
		length = Math.min(movieverseMUserMovies.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseMUserMovies[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Member...");
		data.movieverse.Member[] movieverseMembers = data.movieverse.MemberDAO.listMemberByQuery(null, null);
		length = Math.min(movieverseMembers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseMembers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing MovieMember...");
		data.movieverse.MovieMember[] movieverseMovieMembers = data.movieverse.MovieMemberDAO.listMovieMemberByQuery(null, null);
		length = Math.min(movieverseMovieMembers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseMovieMembers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Badge...");
		data.movieverse.Badge[] movieverseBadges = data.movieverse.BadgeDAO.listBadgeByQuery(null, null);
		length = Math.min(movieverseBadges.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseBadges[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Friendship...");
		data.movieverse.Friendship[] movieverseFriendships = data.movieverse.FriendshipDAO.listFriendshipByQuery(null, null);
		length = Math.min(movieverseFriendships.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseFriendships[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Comment...");
		data.movieverse.Comment[] movieverseComments = data.movieverse.CommentDAO.listCommentByQuery(null, null);
		length = Math.min(movieverseComments.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseComments[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Feed...");
		data.movieverse.Feed[] movieverseFeeds = data.movieverse.FeedDAO.listFeedByQuery(null, null);
		length = Math.min(movieverseFeeds.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseFeeds[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Achievement...");
		data.movieverse.Achievement[] movieverseAchievements = data.movieverse.AchievementDAO.listAchievementByQuery(null, null);
		length = Math.min(movieverseAchievements.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(movieverseAchievements[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing MUser by Criteria...");
		data.movieverse.MUserCriteria lmovieverseMUserCriteria = new data.movieverse.MUserCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseMUserCriteria.id.eq();
		lmovieverseMUserCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.MUser[] movieverseMUsers = lmovieverseMUserCriteria.listMUser();
		int length =movieverseMUsers== null ? 0 : Math.min(movieverseMUsers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseMUsers[i]);
		}
		System.out.println(length + " MUser record(s) retrieved."); 
		
		System.out.println("Listing Movie by Criteria...");
		data.movieverse.MovieCriteria lmovieverseMovieCriteria = new data.movieverse.MovieCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseMovieCriteria.id.eq();
		lmovieverseMovieCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Movie[] movieverseMovies = lmovieverseMovieCriteria.listMovie();
		length =movieverseMovies== null ? 0 : Math.min(movieverseMovies.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseMovies[i]);
		}
		System.out.println(length + " Movie record(s) retrieved."); 
		
		System.out.println("Listing Genre by Criteria...");
		data.movieverse.GenreCriteria lmovieverseGenreCriteria = new data.movieverse.GenreCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseGenreCriteria.id.eq();
		lmovieverseGenreCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Genre[] movieverseGenres = lmovieverseGenreCriteria.listGenre();
		length =movieverseGenres== null ? 0 : Math.min(movieverseGenres.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseGenres[i]);
		}
		System.out.println(length + " Genre record(s) retrieved."); 
		
		System.out.println("Listing Company by Criteria...");
		data.movieverse.CompanyCriteria lmovieverseCompanyCriteria = new data.movieverse.CompanyCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseCompanyCriteria.id.eq();
		lmovieverseCompanyCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Company[] movieverseCompanys = lmovieverseCompanyCriteria.listCompany();
		length =movieverseCompanys== null ? 0 : Math.min(movieverseCompanys.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseCompanys[i]);
		}
		System.out.println(length + " Company record(s) retrieved."); 
		
		System.out.println("Listing Country by Criteria...");
		data.movieverse.CountryCriteria lmovieverseCountryCriteria = new data.movieverse.CountryCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseCountryCriteria.id.eq();
		lmovieverseCountryCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Country[] movieverseCountrys = lmovieverseCountryCriteria.listCountry();
		length =movieverseCountrys== null ? 0 : Math.min(movieverseCountrys.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseCountrys[i]);
		}
		System.out.println(length + " Country record(s) retrieved."); 
		
		System.out.println("Listing MUserMovie by Criteria...");
		data.movieverse.MUserMovieCriteria lmovieverseMUserMovieCriteria = new data.movieverse.MUserMovieCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseMUserMovieCriteria.id.eq();
		lmovieverseMUserMovieCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.MUserMovie[] movieverseMUserMovies = lmovieverseMUserMovieCriteria.listMUserMovie();
		length =movieverseMUserMovies== null ? 0 : Math.min(movieverseMUserMovies.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseMUserMovies[i]);
		}
		System.out.println(length + " MUserMovie record(s) retrieved."); 
		
		System.out.println("Listing Member by Criteria...");
		data.movieverse.MemberCriteria lmovieverseMemberCriteria = new data.movieverse.MemberCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseMemberCriteria.id.eq();
		lmovieverseMemberCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Member[] movieverseMembers = lmovieverseMemberCriteria.listMember();
		length =movieverseMembers== null ? 0 : Math.min(movieverseMembers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseMembers[i]);
		}
		System.out.println(length + " Member record(s) retrieved."); 
		
		System.out.println("Listing MovieMember by Criteria...");
		data.movieverse.MovieMemberCriteria lmovieverseMovieMemberCriteria = new data.movieverse.MovieMemberCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseMovieMemberCriteria.id.eq();
		lmovieverseMovieMemberCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.MovieMember[] movieverseMovieMembers = lmovieverseMovieMemberCriteria.listMovieMember();
		length =movieverseMovieMembers== null ? 0 : Math.min(movieverseMovieMembers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseMovieMembers[i]);
		}
		System.out.println(length + " MovieMember record(s) retrieved."); 
		
		System.out.println("Listing Badge by Criteria...");
		data.movieverse.BadgeCriteria lmovieverseBadgeCriteria = new data.movieverse.BadgeCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseBadgeCriteria.id.eq();
		lmovieverseBadgeCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Badge[] movieverseBadges = lmovieverseBadgeCriteria.listBadge();
		length =movieverseBadges== null ? 0 : Math.min(movieverseBadges.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseBadges[i]);
		}
		System.out.println(length + " Badge record(s) retrieved."); 
		
		System.out.println("Listing Friendship by Criteria...");
		data.movieverse.FriendshipCriteria lmovieverseFriendshipCriteria = new data.movieverse.FriendshipCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseFriendshipCriteria.id.eq();
		lmovieverseFriendshipCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Friendship[] movieverseFriendships = lmovieverseFriendshipCriteria.listFriendship();
		length =movieverseFriendships== null ? 0 : Math.min(movieverseFriendships.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseFriendships[i]);
		}
		System.out.println(length + " Friendship record(s) retrieved."); 
		
		System.out.println("Listing Comment by Criteria...");
		data.movieverse.CommentCriteria lmovieverseCommentCriteria = new data.movieverse.CommentCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseCommentCriteria.id.eq();
		lmovieverseCommentCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Comment[] movieverseComments = lmovieverseCommentCriteria.listComment();
		length =movieverseComments== null ? 0 : Math.min(movieverseComments.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseComments[i]);
		}
		System.out.println(length + " Comment record(s) retrieved."); 
		
		System.out.println("Listing Feed by Criteria...");
		data.movieverse.FeedCriteria lmovieverseFeedCriteria = new data.movieverse.FeedCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseFeedCriteria.id.eq();
		lmovieverseFeedCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Feed[] movieverseFeeds = lmovieverseFeedCriteria.listFeed();
		length =movieverseFeeds== null ? 0 : Math.min(movieverseFeeds.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(movieverseFeeds[i]);
		}
		System.out.println(length + " Feed record(s) retrieved."); 
		
		System.out.println("Listing Achievement by Criteria...");
		data.movieverse.AchievementCriteria lmovieverseAchievementCriteria = new data.movieverse.AchievementCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lmovieverseAchievementCriteria.id.eq();
		lmovieverseAchievementCriteria.setMaxResults(ROW_COUNT);
		data.movieverse.Achievement[] movieverseAchievements = lmovieverseAchievementCriteria.listAchievement();
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
				data.movieverse.MovieversePersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
