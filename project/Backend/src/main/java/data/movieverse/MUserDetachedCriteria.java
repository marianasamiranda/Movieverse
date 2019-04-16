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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class MUserDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final IntegerExpression userCountryId;
	public final AssociationExpression userCountry;
	public final StringExpression username;
	public final StringExpression password;
	public final StringExpression email;
	public final StringExpression name;
	public final DateExpression birthDate;
	public final CharacterExpression gender;
	public final IntegerExpression movieCount;
	public final IntegerExpression hoursCount;
	public final StringExpression avatar;
	public final DateExpression joinDate;
	public final StringExpression token;
	public final DateExpression tokenLimit;
	public final CollectionExpression receivedFriendships;
	public final CollectionExpression comments;
	public final CollectionExpression feed;
	public final CollectionExpression achievements;
	public final CollectionExpression userMovies;
	public final CollectionExpression requestedFriendships;
	public final CollectionExpression friends;
	
	public MUserDetachedCriteria() {
		super(data.movieverse.MUser.class, data.movieverse.MUserCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		userCountryId = new IntegerExpression("userCountry.id", this.getDetachedCriteria());
		userCountry = new AssociationExpression("userCountry", this.getDetachedCriteria());
		username = new StringExpression("username", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		birthDate = new DateExpression("birthDate", this.getDetachedCriteria());
		gender = new CharacterExpression("gender", this.getDetachedCriteria());
		movieCount = new IntegerExpression("movieCount", this.getDetachedCriteria());
		hoursCount = new IntegerExpression("hoursCount", this.getDetachedCriteria());
		avatar = new StringExpression("avatar", this.getDetachedCriteria());
		joinDate = new DateExpression("joinDate", this.getDetachedCriteria());
		token = new StringExpression("token", this.getDetachedCriteria());
		tokenLimit = new DateExpression("tokenLimit", this.getDetachedCriteria());
		receivedFriendships = new CollectionExpression("ORM_ReceivedFriendships", this.getDetachedCriteria());
		comments = new CollectionExpression("ORM_Comments", this.getDetachedCriteria());
		feed = new CollectionExpression("ORM_Feed", this.getDetachedCriteria());
		achievements = new CollectionExpression("ORM_Achievements", this.getDetachedCriteria());
		userMovies = new CollectionExpression("ORM_UserMovies", this.getDetachedCriteria());
		requestedFriendships = new CollectionExpression("ORM_RequestedFriendships", this.getDetachedCriteria());
		friends = new CollectionExpression("ORM_Friends", this.getDetachedCriteria());
	}
	
	public MUserDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, data.movieverse.MUserCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		userCountryId = new IntegerExpression("userCountry.id", this.getDetachedCriteria());
		userCountry = new AssociationExpression("userCountry", this.getDetachedCriteria());
		username = new StringExpression("username", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		birthDate = new DateExpression("birthDate", this.getDetachedCriteria());
		gender = new CharacterExpression("gender", this.getDetachedCriteria());
		movieCount = new IntegerExpression("movieCount", this.getDetachedCriteria());
		hoursCount = new IntegerExpression("hoursCount", this.getDetachedCriteria());
		avatar = new StringExpression("avatar", this.getDetachedCriteria());
		joinDate = new DateExpression("joinDate", this.getDetachedCriteria());
		token = new StringExpression("token", this.getDetachedCriteria());
		tokenLimit = new DateExpression("tokenLimit", this.getDetachedCriteria());
		receivedFriendships = new CollectionExpression("ORM_ReceivedFriendships", this.getDetachedCriteria());
		comments = new CollectionExpression("ORM_Comments", this.getDetachedCriteria());
		feed = new CollectionExpression("ORM_Feed", this.getDetachedCriteria());
		achievements = new CollectionExpression("ORM_Achievements", this.getDetachedCriteria());
		userMovies = new CollectionExpression("ORM_UserMovies", this.getDetachedCriteria());
		requestedFriendships = new CollectionExpression("ORM_RequestedFriendships", this.getDetachedCriteria());
		friends = new CollectionExpression("ORM_Friends", this.getDetachedCriteria());
	}
	
	public CountryDetachedCriteria createUserCountryCriteria() {
		return new CountryDetachedCriteria(createCriteria("userCountry"));
	}
	
	public FriendshipDetachedCriteria createReceivedFriendshipsCriteria() {
		return new FriendshipDetachedCriteria(createCriteria("ORM_ReceivedFriendships"));
	}
	
	public CommentDetachedCriteria createCommentsCriteria() {
		return new CommentDetachedCriteria(createCriteria("ORM_Comments"));
	}
	
	public FeedDetachedCriteria createFeedCriteria() {
		return new FeedDetachedCriteria(createCriteria("ORM_Feed"));
	}
	
	public AchievementDetachedCriteria createAchievementsCriteria() {
		return new AchievementDetachedCriteria(createCriteria("ORM_Achievements"));
	}
	
	public UserMovieDetachedCriteria createUserMoviesCriteria() {
		return new UserMovieDetachedCriteria(createCriteria("ORM_UserMovies"));
	}
	
	public FriendshipDetachedCriteria createRequestedFriendshipsCriteria() {
		return new FriendshipDetachedCriteria(createCriteria("ORM_RequestedFriendships"));
	}
	
	public FriendshipDetachedCriteria createFriendsCriteria() {
		return new FriendshipDetachedCriteria(createCriteria("ORM_Friends"));
	}
	
	public MUser uniqueMUser(PersistentSession session) {
		return (MUser) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public MUser[] listMUser(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (MUser[]) list.toArray(new MUser[list.size()]);
	}
}

