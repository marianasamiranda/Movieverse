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

public class Badge {
	public Badge() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_BADGE_ACHIEVEMENTS) {
			return ORM_achievements;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int id;
	
	private String name;
	
	private String image;
	
	private java.util.Set ORM_achievements = new java.util.HashSet();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setImage(String value) {
		this.image = value;
	}
	
	public String getImage() {
		return image;
	}
	
	public MUser[] getmUsers() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = achievements.getIterator();lIter.hasNext();) {
			lValues.add(((Achievement)lIter.next()).getmUser());
		}
		return (MUser[])lValues.toArray(new MUser[lValues.size()]);
	}
	
	public void removemUser(MUser amUser) {
		Achievement[] lAchievements = achievements.toArray();
		for(int i = 0; i < lAchievements.length; i++) {
			if(lAchievements[i].getmUser().equals(amUser)) {
				achievements.remove(lAchievements[i]);
			}
		}
	}
	
	public void addmUser(Achievement aAchievement, MUser amUser) {
		aAchievement.setmUser(amUser);
		achievements.add(aAchievement);
	}
	
	public Achievement getAchievementBymUser(MUser amUser) {
		Achievement[] lAchievements = achievements.toArray();
		for(int i = 0; i < lAchievements.length; i++) {
			if(lAchievements[i].getmUser().equals(amUser)) {
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
	
	public final AchievementSetCollection achievements = new AchievementSetCollection(this, _ormAdapter, ORMConstants.KEY_BADGE_ACHIEVEMENTS, ORMConstants.KEY_ACHIEVEMENT_BADGE, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
