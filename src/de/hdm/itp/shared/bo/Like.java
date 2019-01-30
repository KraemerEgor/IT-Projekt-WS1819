package de.hdm.itp.shared.bo;

/**
 * The Class Like.
 */
public class Like extends BusinessObject {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Benutzer, der den Like gesetzt hat. */
	private int ownerId;
	
	/** Post, auf den sich der Like bezieht. */
	private int postId;

	
	/**
	 * Auslesen des Benutzers, der den Like gesetzt hat.
	 *
	 * @return ownerId, die den Benutzer eindeutig identifiziert
	 */
	public int getOwnerId() {
		return ownerId;
	}

	
	/**
	 * Setzen des Benutzers, der den Like setzt.
	 *
	 * @param ownerId the new owner id
	 */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * Auslesen des Posts, auf den sich der Like bezieht.
	 *
	 * @return postId, die den Post eindeutig identifiziert
	 */
	public int getPostId() {
		return postId;
	}

	/**
	 * Setzen des Posts, auf den sich der Like bezieht.
	 *
	 * @param postId the new post id
	 */
	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	
	
	
	
}
