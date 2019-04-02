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
package movieverse;

public class MUser {
	public MUser() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_MUSER_REQUESTEDFRIENDSHIPS) {
			return ORM_requestedFriendships;
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
		else if (key == ORMConstants.KEY_MUSER_MUSERMOVIES) {
			return ORM_mUserMovies;
		}
		else if (key == ORMConstants.KEY_MUSER_RECEIVEDFRIENDSHIPS) {
			return ORM_receivedFriendships;
		}
		else if (key == ORMConstants.KEY_MUSER_FRIENDS) {
			return ORM_friends;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_MUSER_USER_COUNTRY) {
			this.user_country = (movieverse.Country) owner;
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
	
	private movieverse.Country user_country;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String name;
	
	private java.util.Date birthDate;
	
	private boolean gender;
	
	private int movieCount;
	
	private int hoursCount;
	
	private String avatar;
	
	private java.util.Date joinDate;
	
	private java.util.Set ORM_requestedFriendships = new java.util.HashSet();
	
	private java.util.Set ORM_comments = new java.util.HashSet();
	
	private java.util.Set ORM_feed = new java.util.HashSet();
	
	private java.util.Set ORM_achievements = new java.util.HashSet();
	
	private java.util.Set ORM_mUserMovies = new java.util.HashSet();
	
	private java.util.Set ORM_receivedFriendships = new java.util.HashSet();
	
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
	
	public void setGender(boolean value) {
		this.gender = value;
	}
	
	public boolean getGender() {
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
	
	public movieverse.MUser[] getRequestedMusers() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = requestedFriendships.getIterator();lIter.hasNext();) {
			lValues.add(((movieverse.Friendship)lIter.next()).getRequestedMuser());
		}
		return (movieverse.MUser[])lValues.toArray(new movieverse.MUser[lValues.size()]);
	}
	
	public void removeRequestedMuser(movieverse.MUser aRequestedMuser) {
		movieverse.Friendship[] lRequestedFriendships = requestedFriendships.toArray();
		for(int i = 0; i < lRequestedFriendships.length; i++) {
			if(lRequestedFriendships[i].getRequestedMuser().equals(aRequestedMuser)) {
				requestedFriendships.remove(lRequestedFriendships[i]);
			}
		}
	}
	
	public void addRequestedMuser(movieverse.Friendship aFriendship, movieverse.MUser aRequestedMuser) {
		aFriendship.setRequestedMuser(aRequestedMuser);
		requestedFriendships.add(aFriendship);
	}
	
	public movieverse.Friendship getFriendshipByRequestedMuser(movieverse.MUser aRequestedMuser) {
		movieverse.Friendship[] lRequestedFriendships = requestedFriendships.toArray();
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
	
	public final movieverse.FriendshipSetCollection requestedFriendships = new movieverse.FriendshipSetCollection(this, _ormAdapter, ORMConstants.KEY_MUSER_REQUESTEDFRIENDSHIPS, ORMConstants.KEY_FRIENDSHIP_RECEIVEDMUSER, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public void setUser_country(movieverse.Country value) {
		this.user_country = value;
	}
	
	public movieverse.Country getUser_country() {
		return user_country;
	}
	
	private void setORM_Comments(java.util.Set value) {
		this.ORM_comments = value;
	}
	
	private java.util.Set getORM_Comments() {
		return ORM_comments;
	}
	
	public final movieverse.CommentSetCollection comments = new movieverse.CommentSetCollection(this, _ormAdapter, ORMConstants.KEY_MUSER_COMMENTS, ORMConstants.KEY_COMMENT_COMMENTER, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Feed(java.util.Set value) {
		this.ORM_feed = value;
	}
	
	private java.util.Set getORM_Feed() {
		return ORM_feed;
	}
	
	public final movieverse.FeedSetCollection feed = new movieverse.FeedSetCollection(this, _ormAdapter, ORMConstants.KEY_MUSER_FEED, ORMConstants.KEY_FEED_USER, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public movieverse.Badge[] getBadges() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = achievements.getIterator();lIter.hasNext();) {
			lValues.add(((movieverse.Achievement)lIter.next()).getBadge());
		}
		return (movieverse.Badge[])lValues.toArray(new movieverse.Badge[lValues.size()]);
	}
	
	public void removeBadge(movieverse.Badge aBadge) {
		movieverse.Achievement[] lAchievements = achievements.toArray();
		for(int i = 0; i < lAchievements.length; i++) {
			if(lAchievements[i].getBadge().equals(aBadge)) {
				achievements.remove(lAchievements[i]);
			}
		}
	}
	
	public void addBadge(movieverse.Achievement aAchievement, movieverse.Badge aBadge) {
		aAchievement.setBadge(aBadge);
		achievements.add(aAchievement);
	}
	
	public movieverse.Achievement getAchievementByBadge(movieverse.Badge aBadge) {
		movieverse.Achievement[] lAchievements = achievements.toArray();
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
	
	public final movieverse.AchievementSetCollection achievements = new movieverse.AchievementSetCollection(this, _ormAdapter, ORMConstants.KEY_MUSER_ACHIEVEMENTS, ORMConstants.KEY_ACHIEVEMENT_MUSER, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public movieverse.Movie[] getMovies() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = mUserMovies.getIterator();lIter.hasNext();) {
			lValues.add(((movieverse.MUserMovie)lIter.next()).getMovie());
		}
		return (movieverse.Movie[])lValues.toArray(new movieverse.Movie[lValues.size()]);
	}
	
	public void removeMovie(movieverse.Movie aMovie) {
		movieverse.MUserMovie[] lmUserMovies = mUserMovies.toArray();
		for(int i = 0; i < lmUserMovies.length; i++) {
			if(lmUserMovies[i].getMovie().equals(aMovie)) {
				mUserMovies.remove(lmUserMovies[i]);
			}
		}
	}
	
	public void addMovie(movieverse.MUserMovie aMUserMovie, movieverse.Movie aMovie) {
		aMUserMovie.setMovie(aMovie);
		mUserMovies.add(aMUserMovie);
	}
	
	public movieverse.MUserMovie getMUserMovieByMovie(movieverse.Movie aMovie) {
		movieverse.MUserMovie[] lmUserMovies = mUserMovies.toArray();
		for(int i = 0; i < lmUserMovies.length; i++) {
			if(lmUserMovies[i].getMovie().equals(aMovie)) {
				return lmUserMovies[i];
			}
		}
		return null;
	}
	
	private void setORM_mUserMovies(java.util.Set value) {
		this.ORM_mUserMovies = value;
	}
	
	private java.util.Set getORM_mUserMovies() {
		return ORM_mUserMovies;
	}
	
	public final movieverse.MUserMovieSetCollection mUserMovies = new movieverse.MUserMovieSetCollection(this, _ormAdapter, ORMConstants.KEY_MUSER_MUSERMOVIES, ORMConstants.KEY_MUSERMOVIE_MUSER, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public movieverse.MUser[] getReceivedMusers() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = receivedFriendships.getIterator();lIter.hasNext();) {
			lValues.add(((movieverse.Friendship)lIter.next()).getReceivedMuser());
		}
		return (movieverse.MUser[])lValues.toArray(new movieverse.MUser[lValues.size()]);
	}
	
	public void removeReceivedMuser(movieverse.MUser aReceivedMuser) {
		movieverse.Friendship[] lReceivedFriendships = receivedFriendships.toArray();
		for(int i = 0; i < lReceivedFriendships.length; i++) {
			if(lReceivedFriendships[i].getReceivedMuser().equals(aReceivedMuser)) {
				receivedFriendships.remove(lReceivedFriendships[i]);
			}
		}
	}
	
	public void addReceivedMuser(movieverse.Friendship aFriendship, movieverse.MUser aReceivedMuser) {
		aFriendship.setReceivedMuser(aReceivedMuser);
		receivedFriendships.add(aFriendship);
	}
	
	public movieverse.Friendship getFriendshipByReceivedMuser(movieverse.MUser aReceivedMuser) {
		movieverse.Friendship[] lReceivedFriendships = receivedFriendships.toArray();
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
	
	public final movieverse.FriendshipSetCollection receivedFriendships = new movieverse.FriendshipSetCollection(this, _ormAdapter, ORMConstants.KEY_MUSER_RECEIVEDFRIENDSHIPS, ORMConstants.KEY_FRIENDSHIP_REQUESTEDMUSER, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Friends(java.util.Set value) {
		this.ORM_friends = value;
	}
	
	private java.util.Set getORM_Friends() {
		return ORM_friends;
	}
	
	public final movieverse.FriendshipSetCollection friends = new movieverse.FriendshipSetCollection(this, _ormAdapter, ORMConstants.KEY_MUSER_FRIENDS, ORMConstants.KEY_MUL_MANY_TO_MANY);
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
