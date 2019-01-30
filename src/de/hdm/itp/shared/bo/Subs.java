package de.hdm.itp.shared.bo;

/**
 * The Class Subs.
 */
public class Subs extends BusinessObject {
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	/** der aktuelle User. */
	private int currentUser;
	
	/** der User, der abonniert wird. */
	private int targetUser;
	
	
	/**
	 * Auslesen des aktuellen Benutzers.
	 *
	 * @return currentUser, die den aktuellen Nutzer eindeutig identifiziert
	 */
	public int getCurrentUser() {
		return currentUser;
	}
	
	
	/**
	 * Setzen des aktuellen Benutzers.
	 *
	 * @param currentUser the new current user
	 */
	public void setCurrentUser(int currentUser) {
		this.currentUser = currentUser;
	}
	
	/**
	 * Aulesen des Benutzers, der abonniert wird.
	 *
	 * @return targetUser, die abonnierten Benutzer eindeutig identifiziert
	 */
	public int getTargetUser() {
		return targetUser;
	}

	/**
	 * Setzen des Benutzers, der abonniert wird.
	 *
	 * @param targetUser the new target user
	 */
	public void setTargetUser(int targetUser) {
		this.targetUser = targetUser;
	}
	
	
}
