package de.hdm.itp.client;

import java.io.Serializable;

/**
 * Das Ergebnis des Login-Dienstes ist eine Instanz der Klasse "LoginInfo" mit
 * verschiedenen Informationen über den angemeldeten Nutzer.
 */
public class LoginInfo implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	/** The logged in. */
	private boolean loggedIn = false;
	
	/** The login url. */
	private String loginUrl;
	
	/** The logout url. */
	private String logoutUrl;
	
	/** The email address. */
	private String emailAddress;
	
	/** The nickname. */
	private String nickname;
	
	/**
	 * Instantiates a new login info.
	 */
	public LoginInfo() {
		
	}
	
	/**
	 * Checks if is logged in.
	 *
	 * @return true, if is logged in
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * Sets the logged in.
	 *
	 * @param loggedIn the new logged in
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	/**
	 * Gets the login url.
	 *
	 * @return the login url
	 */
	public String getLoginUrl() {
		return loginUrl;
	}

	/**
	 * Sets the login url.
	 *
	 * @param loginUrl the new login url
	 */
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	/**
	 * Gets the logout url.
	 *
	 * @return the logout url
	 */
	public String getLogoutUrl() {
		return logoutUrl;
	}

	/**
	 * Sets the logout url.
	 *
	 * @param logoutUrl the new logout url
	 */
	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	/**
	 * Gets the email address.
	 *
	 * @return the email address
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Sets the email address.
	 *
	 * @param emailAddress the new email address
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Gets the nickname.
	 *
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Sets the nickname.
	 *
	 * @param nickname the new nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}