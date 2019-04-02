/**
 * Licensee: mariana(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class RetrieveAndUpdateMovieverseData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = movieverse.MovieversePersistentManager.instance().getSession().beginTransaction();
		try {
			movieverse.MUser lmovieverseMUser = movieverse.MUserDAO.loadMUserByQuery(null, null);
			// Update the properties of the persistent object
			movieverse.MUserDAO.save(lmovieverseMUser);
			movieverse.Movie lmovieverseMovie = movieverse.MovieDAO.loadMovieByQuery(null, null);
			// Update the properties of the persistent object
			movieverse.MovieDAO.save(lmovieverseMovie);
			movieverse.Genre lmovieverseGenre = movieverse.GenreDAO.loadGenreByQuery(null, null);
			// Update the properties of the persistent object
			movieverse.GenreDAO.save(lmovieverseGenre);
			movieverse.Company lmovieverseCompany = movieverse.CompanyDAO.loadCompanyByQuery(null, null);
			// Update the properties of the persistent object
			movieverse.CompanyDAO.save(lmovieverseCompany);
			movieverse.Country lmovieverseCountry = movieverse.CountryDAO.loadCountryByQuery(null, null);
			// Update the properties of the persistent object
			movieverse.CountryDAO.save(lmovieverseCountry);
			movieverse.MUserMovie lmovieverseMUserMovie = movieverse.MUserMovieDAO.loadMUserMovieByQuery(null, null);
			// Update the properties of the persistent object
			movieverse.MUserMovieDAO.save(lmovieverseMUserMovie);
			movieverse.Member lmovieverseMember = movieverse.MemberDAO.loadMemberByQuery(null, null);
			// Update the properties of the persistent object
			movieverse.MemberDAO.save(lmovieverseMember);
			movieverse.MovieMember lmovieverseMovieMember = movieverse.MovieMemberDAO.loadMovieMemberByQuery(null, null);
			// Update the properties of the persistent object
			movieverse.MovieMemberDAO.save(lmovieverseMovieMember);
			movieverse.Badge lmovieverseBadge = movieverse.BadgeDAO.loadBadgeByQuery(null, null);
			// Update the properties of the persistent object
			movieverse.BadgeDAO.save(lmovieverseBadge);
			movieverse.Friendship lmovieverseFriendship = movieverse.FriendshipDAO.loadFriendshipByQuery(null, null);
			// Update the properties of the persistent object
			movieverse.FriendshipDAO.save(lmovieverseFriendship);
			movieverse.Comment lmovieverseComment = movieverse.CommentDAO.loadCommentByQuery(null, null);
			// Update the properties of the persistent object
			movieverse.CommentDAO.save(lmovieverseComment);
			movieverse.Feed lmovieverseFeed = movieverse.FeedDAO.loadFeedByQuery(null, null);
			// Update the properties of the persistent object
			movieverse.FeedDAO.save(lmovieverseFeed);
			movieverse.Achievement lmovieverseAchievement = movieverse.AchievementDAO.loadAchievementByQuery(null, null);
			// Update the properties of the persistent object
			movieverse.AchievementDAO.save(lmovieverseAchievement);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving MUser by MUserCriteria");
		movieverse.MUserCriteria lmovieverseMUserCriteria = new movieverse.MUserCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseMUserCriteria.id.eq();
		System.out.println(lmovieverseMUserCriteria.uniqueMUser());
		
		System.out.println("Retrieving Movie by MovieCriteria");
		movieverse.MovieCriteria lmovieverseMovieCriteria = new movieverse.MovieCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseMovieCriteria.id.eq();
		System.out.println(lmovieverseMovieCriteria.uniqueMovie());
		
		System.out.println("Retrieving Genre by GenreCriteria");
		movieverse.GenreCriteria lmovieverseGenreCriteria = new movieverse.GenreCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseGenreCriteria.id.eq();
		System.out.println(lmovieverseGenreCriteria.uniqueGenre());
		
		System.out.println("Retrieving Company by CompanyCriteria");
		movieverse.CompanyCriteria lmovieverseCompanyCriteria = new movieverse.CompanyCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseCompanyCriteria.id.eq();
		System.out.println(lmovieverseCompanyCriteria.uniqueCompany());
		
		System.out.println("Retrieving Country by CountryCriteria");
		movieverse.CountryCriteria lmovieverseCountryCriteria = new movieverse.CountryCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseCountryCriteria.id.eq();
		System.out.println(lmovieverseCountryCriteria.uniqueCountry());
		
		System.out.println("Retrieving MUserMovie by MUserMovieCriteria");
		movieverse.MUserMovieCriteria lmovieverseMUserMovieCriteria = new movieverse.MUserMovieCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseMUserMovieCriteria.id.eq();
		System.out.println(lmovieverseMUserMovieCriteria.uniqueMUserMovie());
		
		System.out.println("Retrieving Member by MemberCriteria");
		movieverse.MemberCriteria lmovieverseMemberCriteria = new movieverse.MemberCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseMemberCriteria.id.eq();
		System.out.println(lmovieverseMemberCriteria.uniqueMember());
		
		System.out.println("Retrieving MovieMember by MovieMemberCriteria");
		movieverse.MovieMemberCriteria lmovieverseMovieMemberCriteria = new movieverse.MovieMemberCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseMovieMemberCriteria.id.eq();
		System.out.println(lmovieverseMovieMemberCriteria.uniqueMovieMember());
		
		System.out.println("Retrieving Badge by BadgeCriteria");
		movieverse.BadgeCriteria lmovieverseBadgeCriteria = new movieverse.BadgeCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseBadgeCriteria.id.eq();
		System.out.println(lmovieverseBadgeCriteria.uniqueBadge());
		
		System.out.println("Retrieving Friendship by FriendshipCriteria");
		movieverse.FriendshipCriteria lmovieverseFriendshipCriteria = new movieverse.FriendshipCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseFriendshipCriteria.id.eq();
		System.out.println(lmovieverseFriendshipCriteria.uniqueFriendship());
		
		System.out.println("Retrieving Comment by CommentCriteria");
		movieverse.CommentCriteria lmovieverseCommentCriteria = new movieverse.CommentCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseCommentCriteria.id.eq();
		System.out.println(lmovieverseCommentCriteria.uniqueComment());
		
		System.out.println("Retrieving Feed by FeedCriteria");
		movieverse.FeedCriteria lmovieverseFeedCriteria = new movieverse.FeedCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseFeedCriteria.id.eq();
		System.out.println(lmovieverseFeedCriteria.uniqueFeed());
		
		System.out.println("Retrieving Achievement by AchievementCriteria");
		movieverse.AchievementCriteria lmovieverseAchievementCriteria = new movieverse.AchievementCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseAchievementCriteria.id.eq();
		System.out.println(lmovieverseAchievementCriteria.uniqueAchievement());
		
	}
	
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateMovieverseData retrieveAndUpdateMovieverseData = new RetrieveAndUpdateMovieverseData();
			try {
				retrieveAndUpdateMovieverseData.retrieveAndUpdateTestData();
				//retrieveAndUpdateMovieverseData.retrieveByCriteria();
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
