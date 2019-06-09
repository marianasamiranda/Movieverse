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

public class Badge {
	public Badge() {
	}
	
	private int id;
	
	private String name;
	
	private String image;

	private String description;
	
	private java.util.Set achievements = new java.util.HashSet();
	
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

	public void setDescription(String value) {
		this.description = value;
	}

	public String getDescription() {
		return description;
	}

	public MUser[] getmUsers() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = achievements.iterator();lIter.hasNext();) {
			lValues.add(((Achievement)lIter.next()).getmUser());
		}
		return (MUser[])lValues.toArray(new MUser[lValues.size()]);
	}
	
	public void removemUser(MUser amUser) {
		Achievement[] lAchievements = (Achievement[])achievements.toArray(new Achievement[achievements.size()]);
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
		Achievement[] lAchievements = (Achievement[])achievements.toArray(new Achievement[achievements.size()]);
		for(int i = 0; i < lAchievements.length; i++) {
			if(lAchievements[i].getmUser().equals(amUser)) {
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
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
