/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: mariana(Universidade do Minho)
 * License Type: Academic
 */
package data.movieverse;

public class MUser {
	public MUser() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_MUSER_RECEIVEDFRIENDSHIPS) {
			return ORM_receivedFriendships;
		}
		else if (key == ORMConstants.KEY_MUSER_COMMENTS) {
			return ORM_comments;
		}
		else if (key == ORMConstants.KEY_MUSER_FEED) {
			return ORM_feed;
		}
		else if (key == ORMConstants.KEY_MUSER_ACHIEVEMENTS) {
			return ORM_achievements;
		}
		else if (key == ORMConstants.KEY_MUSER_USERMOVIES) {
			return ORM_userMovies;
		}
		else if (key == ORMConstants.KEY_MUSER_REQUESTEDFRIENDSHIPS) {
			return ORM_requestedFriendships;
		}
		else if (key == ORMConstants.KEY_MUSER_FRIENDS) {
			return ORM_friends;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_MUSER_USERCOUNTRY) {
			this.userCountry = (data.movieverse.Country) owner;
		}
		
		else if (key == ORMConstants.KEY_MUSER_FAVOURITEGENRE) {
			this.favouriteGenre = (data.movieverse.Genre) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int id;
	
	private data.movieverse.Genre favouriteGenre;
	
	private data.movieverse.Country userCountry;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String name;
	
	private java.util.Date birthDate;
	
	private char gender;
	
	private int movieCount;
	
	private int hoursCount;
	
	private String avatar;
	
	private java.util.Date joinDate;
	
	private String token;
	
	private java.sql.Timestamp tokenLimit;
	
	private int commentsCount;
	
	private int ratingsCount;
	
	private int friendsCount;
	
	private java.util.Set ORM_receivedFriendships = new java.util.HashSet();
	
	private java.util.Set ORM_comments = new java.util.HashSet();
	
	private java.util.Set ORM_feed = new java.util.HashSet();
	
	private java.util.Set ORM_achievements = new java.util.HashSet();
	
	private java.util.Set ORM_userMovies = new java.util.HashSet();
	
	private java.util.Set ORM_requestedFriendships = new java.util.HashSet();
	
	private java.util.Set ORM_friends = new java.util.HashSet();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setBirthDate(java.util.Date value) {
		this.birthDate = value;
	}
	
	public java.util.Date getBirthDate() {
		return birthDate;
	}
	
	public void setGender(char value) {
		this.gender = value;
	}
	
	public char getGender() {
		return gender;
	}
	
	public void setMovieCount(int value) {
		this.movieCount = value;
	}
	
	public int getMovieCount() {
		return movieCount;
	}
	
	public void setHoursCount(int value) {
		this.hoursCount = value;
	}
	
	public int getHoursCount() {
		return hoursCount;
	}
	
	public void setAvatar(String value) {
		this.avatar = value;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setJoinDate(java.util.Date value) {
		this.joinDate = value;
	}
	
	public java.util.Date getJoinDate() {
		return joinDate;
	}
	
	public void setToken(String value) {
		this.token = value;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setTokenLimit(java.sql.Timestamp value) {
		this.tokenLimit = value;
	}
	
	public java.sql.Timestamp getTokenLimit() {
		return tokenLimit;
	}
	
	public void setCommentsCount(int value) {
		this.commentsCount = value;
	}
	
	public int getCommentsCount() {
		return commentsCount;
	}
	
	public void setRatingsCount(int value) {
		this.ratingsCount = value;
	}
	
	public int getRatingsCount() {
		return ratingsCount;
	}
	
	public void setFriendsCount(int value) {
		this.friendsCount = value;
	}
	
	public int getFriendsCount() {
		return friendsCount;
	}
	
	public data.movieverse.MUser[] getReceivedMusers() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = receivedFriendships.getIterator();lIter.hasNext();) {
			lValues.add(((data.movieverse.Friendship)lIter.next()).getReceivedMuser());
		}
		return (data.movieverse.MUser[])lValues.toArray(new data.movieverse.MUser[lValues.size()]);
	}
	
	public void removeReceivedMuser(data.movieverse.MUser aReceivedMuser) {
		data.movieverse.Friendship[] lReceivedFriendships = receivedFriendships.toArray();
		for(int i = 0; i < lReceivedFriendships.length; i++) {
			if(lReceivedFriendships[i].getReceivedMuser().equals(aReceivedMuser)) {
				receivedFriendships.remove(lReceivedFriendships[i]);
			}
		}
	}
	
	public void addReceivedMuser(data.movieverse.Friendship aFriendship, data.movieverse.MUser aReceivedMuser) {
		aFriendship.setReceivedMuser(aReceivedMuser);
		receivedFriendships.add(aFriendship);
	}
	
	public data.movieverse.Friendship getFriendshipByReceivedMuser(data.movieverse.MUser aReceivedMuser) {
		data.movieverse.Friendship[] lReceivedFriendships = receivedFriendships.toArray();
		for(int i = 0; i < lReceivedFriendships.length; i++) {
			if(lReceivedFriendships[i].getReceivedMuser().equals(aReceivedMuser)) {
				return lReceivedFriendships[i];
			}
		}
		return null;
	}
	
	private void setORM_ReceivedFriendships(java.util.Set value) {
		this.ORM_receivedFriendships = value;
	}
	
	private java.util.Set getORM_ReceivedFriendships() {
		return ORM_receivedFriendships;
	}
	
	public final data.movieverse.FriendshipSetCollection receivedFriendships = new data.movieverse.FriendshipSetCollection(this, _ormAdapter, ORMConstants.KEY_MUSER_RECEIVEDFRIENDSHIPS, ORMConstants.KEY_FRIENDSHIP_REQUESTEDMUSER, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public void setUserCountry(data.movieverse.Country value) {
		this.userCountry = value;
	}
	
	public data.movieverse.Country getUserCountry() {
		return userCountry;
	}
	
	private void setORM_Comments(java.util.Set value) {
		this.ORM_comments = value;
	}
	
	private java.util.Set getORM_Comments() {
		return ORM_comments;
	}
	
	public final data.movieverse.CommentSetCollection comments = new data.movieverse.CommentSetCollection(this, _ormAdapter, ORMConstants.KEY_MUSER_COMMENTS, ORMConstants.KEY_COMMENT_COMMENTER, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Feed(java.util.Set value) {
		this.ORM_feed = value;
	}
	
	private java.util.Set getORM_Feed() {
		return ORM_feed;
	}
	
	public final data.movieverse.FeedSetCollection feed = new data.movieverse.FeedSetCollection(this, _ormAdapter, ORMConstants.KEY_MUSER_FEED, ORMConstants.KEY_FEED_USER, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public data.movieverse.Badge[] getBadges() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = achievements.getIterator();lIter.hasNext();) {
			lValues.add(((data.movieverse.Achievement)lIter.next()).getBadge());
		}
		return (data.movieverse.Badge[])lValues.toArray(new data.movieverse.Badge[lValues.size()]);
	}
	
	public void removeBadge(data.movieverse.Badge aBadge) {
		data.movieverse.Achievement[] lAchievements = achievements.toArray();
		for(int i = 0; i < lAchievements.length; i++) {
			if(lAchievements[i].getBadge().equals(aBadge)) {
				achievements.remove(lAchievements[i]);
			}
		}
	}
	
	public void addBadge(data.movieverse.Achievement aAchievement, data.movieverse.Badge aBadge) {
		aAchievement.setBadge(aBadge);
		achievements.add(aAchievement);
	}
	
	public data.movieverse.Achievement getAchievementByBadge(data.movieverse.Badge aBadge) {
		data.movieverse.Achievement[] lAchievements = achievements.toArray();
		for(int i = 0; i < lAchievements.length; i++) {
			if(lAchievements[i].getBadge().equals(aBadge)) {
				return lAchievements[i];
			}
		}
		return null;
	}
	
	private void setORM_Achievements(java.util.Set value) {
		this.ORM_achievements = value;
	}
	
	private java.util.Set getORM_Achievements() {
		return ORM_achievements;
	}
	
	public final data.movieverse.AchievementSetCollection achievements = new data.movieverse.AchievementSetCollection(this, _ormAdapter, ORMConstants.KEY_MUSER_ACHIEVEMENTS, ORMConstants.KEY_ACHIEVEMENT_MUSER, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public data.movieverse.Movie[] getMovies() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = userMovies.getIterator();lIter.hasNext();) {
			lValues.add(((data.movieverse.UserMovie)lIter.next()).getMovie());
		}
		return (data.movieverse.Movie[])lValues.toArray(new data.movieverse.Movie[lValues.size()]);
	}
	
	public void removeMovie(data.movieverse.Movie aMovie) {
		data.movieverse.UserMovie[] lUserMovies = userMovies.toArray();
		for(int i = 0; i < lUserMovies.length; i++) {
			if(lUserMovies[i].getMovie().equals(aMovie)) {
				userMovies.remove(lUserMovies[i]);
			}
		}
	}
	
	public void addMovie(data.movieverse.UserMovie aUserMovie, data.movieverse.Movie aMovie) {
		aUserMovie.setMovie(aMovie);
		userMovies.add(aUserMovie);
	}
	
	public data.movieverse.UserMovie getUserMovieByMovie(data.movieverse.Movie aMovie) {
		data.movieverse.UserMovie[] lUserMovies = userMovies.toArray();
		for(int i = 0; i < lUserMovies.length; i++) {
			if(lUserMovies[i].getMovie().equals(aMovie)) {
				return lUserMovies[i];
			}
		}
		return null;
	}
	
	private void setORM_UserMovies(java.util.Set value) {
		this.ORM_userMovies = value;
	}
	
	private java.util.Set getORM_UserMovies() {
		return ORM_userMovies;
	}
	
	public final data.movieverse.UserMovieSetCollection userMovies = new data.movieverse.UserMovieSetCollection(this, _ormAdapter, ORMConstants.KEY_MUSER_USERMOVIES, ORMConstants.KEY_USERMOVIE_MUSER, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public data.movieverse.MUser[] getRequestedMusers() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = requestedFriendships.getIterator();lIter.hasNext();) {
			lValues.add(((data.movieverse.Friendship)lIter.next()).getRequestedMuser());
		}
		return (data.movieverse.MUser[])lValues.toArray(new data.movieverse.MUser[lValues.size()]);
	}
	
	public void removeRequestedMuser(data.movieverse.MUser aRequestedMuser) {
		data.movieverse.Friendship[] lRequestedFriendships = requestedFriendships.toArray();
		for(int i = 0; i < lRequestedFriendships.length; i++) {
			if(lRequestedFriendships[i].getRequestedMuser().equals(aRequestedMuser)) {
				requestedFriendships.remove(lRequestedFriendships[i]);
			}
		}
	}
	
	public void addRequestedMuser(data.movieverse.Friendship aFriendship, data.movieverse.MUser aRequestedMuser) {
		aFriendship.setRequestedMuser(aRequestedMuser);
		requestedFriendships.add(aFriendship);
	}
	
	public data.movieverse.Friendship getFriendshipByRequestedMuser(data.movieverse.MUser aRequestedMuser) {
		data.movieverse.Friendship[] lRequestedFriendships = requestedFriendships.toArray();
		for(int i = 0; i < lRequestedFriendships.length; i++) {
			if(lRequestedFriendships[i].getRequestedMuser().equals(aRequestedMuser)) {
				return lRequestedFriendships[i];
			}
		}
		return null;
	}
	
	private void setORM_RequestedFriendships(java.util.Set value) {
		this.ORM_requestedFriendships = value;
	}
	
	private java.util.Set getORM_RequestedFriendships() {
		return ORM_requestedFriendships;
	}
	
	public final data.movieverse.FriendshipSetCollection requestedFriendships = new data.movieverse.FriendshipSetCollection(this, _ormAdapter, ORMConstants.KEY_MUSER_REQUESTEDFRIENDSHIPS, ORMConstants.KEY_FRIENDSHIP_RECEIVEDMUSER, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Friends(java.util.Set value) {
		this.ORM_friends = value;
	}
	
	private java.util.Set getORM_Friends() {
		return ORM_friends;
	}
	
	public final data.movieverse.FriendshipSetCollection friends = new data.movieverse.FriendshipSetCollection(this, _ormAdapter, ORMConstants.KEY_MUSER_FRIENDS, ORMConstants.KEY_MUL_MANY_TO_MANY);
	
	public void setFavouriteGenre(data.movieverse.Genre value) {
		this.favouriteGenre = value;
	}
	
	public data.movieverse.Genre getFavouriteGenre() {
		return favouriteGenre;
	}
	
	public void setTokenLimit(java.util.Date tokenLimit) {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
