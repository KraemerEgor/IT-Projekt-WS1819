package de.hdm.itp.shared.bo;

/** 
 *Die Klasse User reräsentiert die Nutzer eines Systems. Jeder Nutzer besitzt eine 
 *eindeutige E-Mail Adresse. 
 */
public class User extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	/** Eine eindeutige E-Mail, mit der jeder Nutzer des Systems identifiziert werden kann. */ 
	private String email = null;
	
	/** Der Vorname des Nutzers */ 
	private String firstname = null;
	
	/** Der Nachname des Nutzers */ 
	private String lastname = null;
	
	/** Der Nickname des Nutzers */ 
	private String nickname = null;
	
	/** Das Geschlecht des Nutzers */ 
	private String gender = null;

	/**Auslesen der E-Mail-Adresse. 
	 * 
	 * @return Emailadresse des Nutzers
	 * */ 
	
	public String getEmail() {
		return email;
	}
	
	/**Setzen der E-Mail-Adresse 
	 * 
	 * @param email Emailadresse des Nutzers
	 * */

	public void setEmail(String email) {
		this.email = email;
	}
	
	/**Auslesen des Vornamen. 
	 * 
	 * @return Vorname des Nutzers
	 * */ 
	
	public String getFirstname() {
		return firstname;
	}
	
	/**Setzen des Vornamen
	 * 
	 * @param firstname Vorname des Nutzers
	 * */

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**Auslesen des Nachnamen. 
	 * 
	 * @return Nachname des Nutzers
	 * */ 
	
	public String getLastname() {
		return lastname;
	}
	
	/**Setzen des Nachnamen
	 * 
	 * @param lastname Nachname des Nutzers
	 * */

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**Auslesen des Nickname. 
	 * 
	 * @return Nickname des Nutzers
	 * */ 
	
	public String getNickname() {
		return nickname;
	}
	
	/**Setzen des Nickname
	 * 
	 * @param nickname Nickname des Nutzers
	 * */

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**Auslesen des Geschlechts. 
	 * 
	 * @return Geschlecht des Nutzers
	 * */ 
	
	public String getGender() {
		return gender;
	}
	
	/**Setzen des Geschlechts
	 * 
	 * @param gender Geschlecht des Nutzers
	 * */

	public void setGender(String gender) {
		this.gender = gender;
	}
}