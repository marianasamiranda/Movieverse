/**
 * Licensee: mariana(Universidade do Minho)
 * License Type: Academic
 */
package data.ormsamples;

import org.orm.*;
public class RetrieveAndUpdateMovieverseData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = data.movieverse.MovieversePersistentManager.instance().getSession().beginTransaction();
		try {
			data.movieverse.MUser lmovieverseMUser = data.movieverse.MUserDAO.loadMUserByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.MUserDAO.save(lmovieverseMUser);
			data.movieverse.Movie lmovieverseMovie = data.movieverse.MovieDAO.loadMovieByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.MovieDAO.save(lmovieverseMovie);
			data.movieverse.Genre lmovieverseGenre = data.movieverse.GenreDAO.loadGenreByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.GenreDAO.save(lmovieverseGenre);
			data.movieverse.Company lmovieverseCompany = data.movieverse.CompanyDAO.loadCompanyByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.CompanyDAO.save(lmovieverseCompany);
			data.movieverse.Country lmovieverseCountry = data.movieverse.CountryDAO.loadCountryByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.CountryDAO.save(lmovieverseCountry);
			data.movieverse.MUserMovie lmovieverseMUserMovie = data.movieverse.MUserMovieDAO.loadMUserMovieByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.MUserMovieDAO.save(lmovieverseMUserMovie);
			data.movieverse.Member lmovieverseMember = data.movieverse.MemberDAO.loadMemberByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.MemberDAO.save(lmovieverseMember);
			data.movieverse.MovieMember lmovieverseMovieMember = data.movieverse.MovieMemberDAO.loadMovieMemberByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.MovieMemberDAO.save(lmovieverseMovieMember);
			data.movieverse.Badge lmovieverseBadge = data.movieverse.BadgeDAO.loadBadgeByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.BadgeDAO.save(lmovieverseBadge);
			data.movieverse.Friendship lmovieverseFriendship = data.movieverse.FriendshipDAO.loadFriendshipByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.FriendshipDAO.save(lmovieverseFriendship);
			data.movieverse.Comment lmovieverseComment = data.movieverse.CommentDAO.loadCommentByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.CommentDAO.save(lmovieverseComment);
			data.movieverse.Feed lmovieverseFeed = data.movieverse.FeedDAO.loadFeedByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.FeedDAO.save(lmovieverseFeed);
			data.movieverse.Achievement lmovieverseAchievement = data.movieverse.AchievementDAO.loadAchievementByQuery(null, null);
			// Update the properties of the persistent object
			data.movieverse.AchievementDAO.save(lmovieverseAchievement);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving MUser by MUserCriteria");
		data.movieverse.MUserCriteria lmovieverseMUserCriteria = new data.movieverse.MUserCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseMUserCriteria.id.eq();
		System.out.println(lmovieverseMUserCriteria.uniqueMUser());
		
		System.out.println("Retrieving Movie by MovieCriteria");
		data.movieverse.MovieCriteria lmovieverseMovieCriteria = new data.movieverse.MovieCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseMovieCriteria.id.eq();
		System.out.println(lmovieverseMovieCriteria.uniqueMovie());
		
		System.out.println("Retrieving Genre by GenreCriteria");
		data.movieverse.GenreCriteria lmovieverseGenreCriteria = new data.movieverse.GenreCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseGenreCriteria.id.eq();
		System.out.println(lmovieverseGenreCriteria.uniqueGenre());
		
		System.out.println("Retrieving Company by CompanyCriteria");
		data.movieverse.CompanyCriteria lmovieverseCompanyCriteria = new data.movieverse.CompanyCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseCompanyCriteria.id.eq();
		System.out.println(lmovieverseCompanyCriteria.uniqueCompany());
		
		System.out.println("Retrieving Country by CountryCriteria");
		data.movieverse.CountryCriteria lmovieverseCountryCriteria = new data.movieverse.CountryCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseCountryCriteria.id.eq();
		System.out.println(lmovieverseCountryCriteria.uniqueCountry());
		
		System.out.println("Retrieving MUserMovie by MUserMovieCriteria");
		data.movieverse.MUserMovieCriteria lmovieverseMUserMovieCriteria = new data.movieverse.MUserMovieCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseMUserMovieCriteria.id.eq();
		System.out.println(lmovieverseMUserMovieCriteria.uniqueMUserMovie());
		
		System.out.println("Retrieving Member by MemberCriteria");
		data.movieverse.MemberCriteria lmovieverseMemberCriteria = new data.movieverse.MemberCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseMemberCriteria.id.eq();
		System.out.println(lmovieverseMemberCriteria.uniqueMember());
		
		System.out.println("Retrieving MovieMember by MovieMemberCriteria");
		data.movieverse.MovieMemberCriteria lmovieverseMovieMemberCriteria = new data.movieverse.MovieMemberCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseMovieMemberCriteria.id.eq();
		System.out.println(lmovieverseMovieMemberCriteria.uniqueMovieMember());
		
		System.out.println("Retrieving Badge by BadgeCriteria");
		data.movieverse.BadgeCriteria lmovieverseBadgeCriteria = new data.movieverse.BadgeCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseBadgeCriteria.id.eq();
		System.out.println(lmovieverseBadgeCriteria.uniqueBadge());
		
		System.out.println("Retrieving Friendship by FriendshipCriteria");
		data.movieverse.FriendshipCriteria lmovieverseFriendshipCriteria = new data.movieverse.FriendshipCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseFriendshipCriteria.id.eq();
		System.out.println(lmovieverseFriendshipCriteria.uniqueFriendship());
		
		System.out.println("Retrieving Comment by CommentCriteria");
		data.movieverse.CommentCriteria lmovieverseCommentCriteria = new data.movieverse.CommentCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseCommentCriteria.id.eq();
		System.out.println(lmovieverseCommentCriteria.uniqueComment());
		
		System.out.println("Retrieving Feed by FeedCriteria");
		data.movieverse.FeedCriteria lmovieverseFeedCriteria = new data.movieverse.FeedCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lmovieverseFeedCriteria.id.eq();
		System.out.println(lmovieverseFeedCriteria.uniqueFeed());
		
		System.out.println("Retrieving Achievement by AchievementCriteria");
		data.movieverse.AchievementCriteria lmovieverseAchievementCriteria = new data.movieverse.AchievementCriteria();
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
				data.movieverse.MovieversePersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
