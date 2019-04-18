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
	public final IntegerExpression favouriteGenreId;
	public final AssociationExpression favouriteGenre;
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
	public final TimestampExpression tokenLimit;
	public final IntegerExpression commentsCount;
	public final IntegerExpression ratingsCount;
	public final IntegerExpression friendsCount;
	public final CollectionExpression receivedFriendships;
	public final CollectionExpression comments;
	public final CollectionExpression feed;
	public final CollectionExpression achievements;
	public final CollectionExpression userMovies;
	public final CollectionExpression requestedFriendships;
	public final CollectionExpression friends;
	
	public MUserCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		favouriteGenreId = new IntegerExpression("favouriteGenre.id", this);
		favouriteGenre = new AssociationExpression("favouriteGenre", this);
		userCountryId = new IntegerExpression("userCountry.id", this);
		userCountry = new AssociationExpression("userCountry", this);
		username = new StringExpression("username", this);
		password = new StringExpression("password", this);
		email = new StringExpression("email", this);
		name = new StringExpression("name", this);
		birthDate = new DateExpression("birthDate", this);
		gender = new CharacterExpression("gender", this);
		movieCount = new IntegerExpression("movieCount", this);
		hoursCount = new IntegerExpression("hoursCount", this);
		avatar = new StringExpression("avatar", this);
		joinDate = new DateExpression("joinDate", this);
		token = new StringExpression("token", this);
		tokenLimit = new TimestampExpression("tokenLimit", this);
		commentsCount = new IntegerExpression("commentsCount", this);
		ratingsCount = new IntegerExpression("ratingsCount", this);
		friendsCount = new IntegerExpression("friendsCount", this);
		receivedFriendships = new CollectionExpression("ORM_ReceivedFriendships", this);
		comments = new CollectionExpression("ORM_Comments", this);
		feed = new CollectionExpression("ORM_Feed", this);
		achievements = new CollectionExpression("ORM_Achievements", this);
		userMovies = new CollectionExpression("ORM_UserMovies", this);
		requestedFriendships = new CollectionExpression("ORM_RequestedFriendships", this);
		friends = new CollectionExpression("ORM_Friends", this);
	}
	
	public MUserCriteria(PersistentSession session) {
		this(session.createCriteria(MUser.class));
	}
	
	public MUserCriteria() throws PersistentException {
		this(MovieversePersistentManager.instance().getSession());
	}
	
	public GenreCriteria createFavouriteGenreCriteria() {
		return new GenreCriteria(createCriteria("favouriteGenre"));
	}
	
	public CountryCriteria createUserCountryCriteria() {
		return new CountryCriteria(createCriteria("userCountry"));
	}
	
	public FriendshipCriteria createReceivedFriendshipsCriteria() {
		return new FriendshipCriteria(createCriteria("ORM_ReceivedFriendships"));
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
	
	public UserMovieCriteria createUserMoviesCriteria() {
		return new UserMovieCriteria(createCriteria("ORM_UserMovies"));
	}
	
	public FriendshipCriteria createRequestedFriendshipsCriteria() {
		return new FriendshipCriteria(createCriteria("ORM_RequestedFriendships"));
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

