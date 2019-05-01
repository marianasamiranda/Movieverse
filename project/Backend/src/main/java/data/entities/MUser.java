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
package data.entities;

import java.util.Set;
import java.util.stream.Collectors;

public class MUser {
	public MUser() {
	}
	
	private int id;
	
	private Genre favouriteGenre;
	
	private Country userCountry;
	
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
	
	private int likesCount;
	
	private java.util.Set receivedFriendships = new java.util.HashSet();
	
	private java.util.Set comments = new java.util.HashSet();
	
	private java.util.Set feed = new java.util.HashSet();
	
	private java.util.Set achievements = new java.util.HashSet();
	
	private java.util.Set userMovies = new java.util.HashSet();
	
	private java.util.Set requestedFriendships = new java.util.HashSet();
	
	private java.util.Set friends = new java.util.HashSet();
	
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
	
	public void setLikesCount(int value) {
		this.likesCount = value;
	}
	
	public int getLikesCount() {
		return likesCount;
	}
	
	public MUser[] getReceivedMusers() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = receivedFriendships.iterator();lIter.hasNext();) {
			lValues.add(((Friendship)lIter.next()).getReceivedMuser());
		}
		return (MUser[])lValues.toArray(new MUser[lValues.size()]);
	}
	
	public void removeReceivedMuser(MUser aReceivedMuser) {
		Friendship[] lReceivedFriendships = (Friendship[])receivedFriendships.toArray(new Friendship[receivedFriendships.size()]);
		for(int i = 0; i < lReceivedFriendships.length; i++) {
			if(lReceivedFriendships[i].getReceivedMuser().equals(aReceivedMuser)) {
				receivedFriendships.remove(lReceivedFriendships[i]);
			}
		}
	}
	
	public void addReceivedMuser(Friendship aFriendship, MUser aReceivedMuser) {
		aFriendship.setReceivedMuser(aReceivedMuser);
		receivedFriendships.add(aFriendship);
	}
	
	public Friendship getFriendshipByReceivedMuser(MUser aReceivedMuser) {
		Friendship[] lReceivedFriendships = (Friendship[])receivedFriendships.toArray(new Friendship[receivedFriendships.size()]);
		for(int i = 0; i < lReceivedFriendships.length; i++) {
			if(lReceivedFriendships[i].getReceivedMuser().equals(aReceivedMuser)) {
				return lReceivedFriendships[i];
			}
		}
		return null;
	}
	
	public void setReceivedFriendships(java.util.Set value) {
		this.receivedFriendships = value;
	}
	
	public java.util.Set getReceivedFriendships() {
		//receivedFriendships = (Set) receivedFriendships.stream().filter(f -> ((Friendship) f).getPending() == true).collect(Collectors.toSet());
		return receivedFriendships;
	}

	public void removeReceivedFriendships(Friendship friendship) {
		 receivedFriendships.remove(friendship);
	}

	
	public void setUserCountry(Country value) {
		this.userCountry = value;
	}
	
	public Country getUserCountry() {
		return userCountry;
	}
	
	public void setComments(java.util.Set value) {
		this.comments = value;
	}
	
	public java.util.Set getComments() {
		return comments;
	}
	
	
	public void setFeed(java.util.Set value) {
		this.feed = value;
	}
	
	public java.util.Set getFeed() {
		return feed;
	}
	
	
	public Badge[] getBadges() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for (Object achievement : achievements) {
			lValues.add(((Achievement) achievement).getBadge());
		}
		return (Badge[])lValues.toArray(new Badge[lValues.size()]);
	}
	
	public void removeBadge(Badge aBadge) {
		Achievement[] lAchievements = (Achievement[])achievements.toArray(new Achievement[achievements.size()]);
		for(int i = 0; i < lAchievements.length; i++) {
			if(lAchievements[i].getBadge().equals(aBadge)) {
				achievements.remove(lAchievements[i]);
			}
		}
	}
	
	public void addBadge(Achievement aAchievement, Badge aBadge) {
		aAchievement.setBadge(aBadge);
		achievements.add(aAchievement);
	}
	
	public Achievement getAchievementByBadge(Badge aBadge) {
		Achievement[] lAchievements = (Achievement[])achievements.toArray(new Achievement[achievements.size()]);
		for(int i = 0; i < lAchievements.length; i++) {
			if(lAchievements[i].getBadge().equals(aBadge)) {
				return lAchievements[i];
			}
		}
		return null;
	}
	
	public void setAchievements(java.util.Set value) {
		this.achievements = value;
	}
	
	public java.util.Set getAchievements() {
		return achievements;
	}
	
	
	public Movie[] getMovies() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = userMovies.iterator();lIter.hasNext();) {
			lValues.add(((UserMovie)lIter.next()).getMovie());
		}
		return (Movie[])lValues.toArray(new Movie[lValues.size()]);
	}
	
	public void removeMovie(Movie aMovie) {
		UserMovie[] lUserMovies = (UserMovie[])userMovies.toArray(new UserMovie[userMovies.size()]);
		for(int i = 0; i < lUserMovies.length; i++) {
			if(lUserMovies[i].getMovie().equals(aMovie)) {
				userMovies.remove(lUserMovies[i]);
			}
		}
	}
	
	public void addMovie(UserMovie aUserMovie, Movie aMovie) {
		aUserMovie.setMovie(aMovie);
		userMovies.add(aUserMovie);
	}
	
	public UserMovie getUserMovieByMovie(Movie aMovie) {
		UserMovie[] lUserMovies = (UserMovie[])userMovies.toArray(new UserMovie[userMovies.size()]);
		for(int i = 0; i < lUserMovies.length; i++) {
			if(lUserMovies[i].getMovie().equals(aMovie)) {
				return lUserMovies[i];
			}
		}
		return null;
	}
	
	public void setUserMovies(java.util.Set value) {
		this.userMovies = value;
	}
	
	public java.util.Set getUserMovies() {
		return userMovies;
	}
	
	
	public MUser[] getRequestedMusers() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = requestedFriendships.iterator();lIter.hasNext();) {
			lValues.add(((Friendship)lIter.next()).getRequestedMuser());
		}
		return (MUser[])lValues.toArray(new MUser[lValues.size()]);
	}
	
	public void removeRequestedMuser(MUser aRequestedMuser) {
		Friendship[] lRequestedFriendships = (Friendship[])requestedFriendships.toArray(new Friendship[requestedFriendships.size()]);
		for(int i = 0; i < lRequestedFriendships.length; i++) {
			if(lRequestedFriendships[i].getRequestedMuser().equals(aRequestedMuser)) {
				requestedFriendships.remove(lRequestedFriendships[i]);
			}
		}
	}
	
	public void addRequestedMuser(Friendship aFriendship, MUser aRequestedMuser) {
		aFriendship.setRequestedMuser(aRequestedMuser);
		requestedFriendships.add(aFriendship);
	}
	
	public Friendship getFriendshipByRequestedMuser(MUser aRequestedMuser) {
		Friendship[] lRequestedFriendships = (Friendship[])requestedFriendships.toArray(new Friendship[requestedFriendships.size()]);
		for(int i = 0; i < lRequestedFriendships.length; i++) {
			if(lRequestedFriendships[i].getRequestedMuser().equals(aRequestedMuser)) {
				return lRequestedFriendships[i];
			}
		}
		return null;
	}
	
	public void setRequestedFriendships(java.util.Set value) {
		this.requestedFriendships = value;
	}
	
	public java.util.Set getRequestedFriendships() {
		return requestedFriendships;
	}


	public void setFriends(java.util.Set value) {
		this.friends = value;
	}
	
	public java.util.Set getFriends() {
		return friends;
	}


	public void addFriend(Friendship friend) {
		friends.add(friend);
		friendsCount++;
	}
	
	public void setFavouriteGenre(Genre value) {
		this.favouriteGenre = value;
	}
	
	public Genre getFavouriteGenre() {
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
