package de.hdm.itp.shared.bo;


public class Subs extends BusinessObject {
	
	/**
	 * der aktuelle User
	 */
	private int currentUser;
	
	/**
	 * der User, der abonniert wird
	 */
	private int targetUser;
	
	
	/**
	 * Auslesen des aktuellen Benutzers
	 * @return currentUser, die den aktuellen Nutzer eindeutig identifiziert
	 */

	public int getCurrentUser() {
		return currentUser;
	}
	
	
	/**
	 * Setzen des aktuellen Benutzers
	 * @param currentUser, die den aktuellen Nutzer eindeutig identifiziert
	 */

	public void setCurrentUser(int currentUser) {
		this.currentUser = currentUser;
	}
	
	/**
	 * Aulesen des Benutzers, der abonniert wird
	 * @return targetUser, die abonnierten Benutzer eindeutig identifiziert
	 */

	public int getTargetUser() {
		return targetUser;
	}

	/**
	 * Setzen des Benutzers, der abonniert wird
	 * @param targetUser, die den abonnierten Benutzer eindeutig identifiziert
	 */
	public void setTargetUser(int targetUser) {
		this.targetUser = targetUser;
	}
	
	
}
