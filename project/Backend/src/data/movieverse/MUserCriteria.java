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

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class MUserCriteria extends AbstractORMCriteria {
	public final IntegerExpression id;
	public final IntegerExpression user_countryId;
	public final AssociationExpression user_country;
	public final StringExpression username;
	public final StringExpression password;
	public final StringExpression email;
	public final StringExpression name;
	public final DateExpression birthDate;
	public final BooleanExpression gender;
	public final IntegerExpression movieCount;
	public final IntegerExpression hoursCount;
	public final StringExpression avatar;
	public final DateExpression joinDate;
	public final CollectionExpression requestedFriendships;
	public final CollectionExpression comments;
	public final CollectionExpression feed;
	public final CollectionExpression achievements;
	public final CollectionExpression mUserMovies;
	public final CollectionExpression receivedFriendships;
	public final CollectionExpression friends;
	
	public MUserCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		user_countryId = new IntegerExpression("user_country.id", this);
		user_country = new AssociationExpression("user_country", this);
		username = new StringExpression("username", this);
		password = new StringExpression("password", this);
		email = new StringExpression("email", this);
		name = new StringExpression("name", this);
		birthDate = new DateExpression("birthDate", this);
		gender = new BooleanExpression("gender", this);
		movieCount = new IntegerExpression("movieCount", this);
		hoursCount = new IntegerExpression("hoursCount", this);
		avatar = new StringExpression("avatar", this);
		joinDate = new DateExpression("joinDate", this);
		requestedFriendships = new CollectionExpression("ORM_RequestedFriendships", this);
		comments = new CollectionExpression("ORM_Comments", this);
		feed = new CollectionExpression("ORM_Feed", this);
		achievements = new CollectionExpression("ORM_Achievements", this);
		mUserMovies = new CollectionExpression("ORM_mUserMovies", this);
		receivedFriendships = new CollectionExpression("ORM_ReceivedFriendships", this);
		friends = new CollectionExpression("ORM_Friends", this);
	}
	
	public MUserCriteria(PersistentSession session) {
		this(session.createCriteria(MUser.class));
	}
	
	public MUserCriteria() throws PersistentException {
		this(MovieversePersistentManager.instance().getSession());
	}
	
	public CountryCriteria createUser_countryCriteria() {
		return new CountryCriteria(createCriteria("user_country"));
	}
	
	public FriendshipCriteria createRequestedFriendshipsCriteria() {
		return new FriendshipCriteria(createCriteria("ORM_RequestedFriendships"));
	}
	
	public CommentCriteria createCommentsCriteria() {
		return new CommentCriteria(createCriteria("ORM_Comments"));
	}
	
	public FeedCriteria createFeedCriteria() {
		return new FeedCriteria(createCriteria("ORM_Feed"));
	}
	
	public AchievementCriteria createAchievementsCriteria() {
		return new AchievementCriteria(createCriteria("ORM_Achievements"));
	}
	
	public MUserMovieCriteria createMUserMoviesCriteria() {
		return new MUserMovieCriteria(createCriteria("ORM_mUserMovies"));
	}
	
	public FriendshipCriteria createReceivedFriendshipsCriteria() {
		return new FriendshipCriteria(createCriteria("ORM_ReceivedFriendships"));
	}
	
	public FriendshipCriteria createFriendsCriteria() {
		return new FriendshipCriteria(createCriteria("ORM_Friends"));
	}
	
	public MUser uniqueMUser() {
		return (MUser) super.uniqueResult();
	}
	
	public MUser[] listMUser() {
		java.util.List list = super.list();
		return (MUser[]) list.toArray(new MUser[list.size()]);
	}
}

