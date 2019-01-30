package de.hdm.itp.shared;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itp.shared.bo.Comment;
import de.hdm.itp.shared.bo.Like;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.Subs;
import de.hdm.itp.shared.bo.User;

/**
 * The Interface EditorAdministration.
 */
@RemoteServiceRelativePath("administration")
public interface EditorAdministration extends RemoteService {

	/**
	 * Initialisierungsmethode.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	void init() throws IllegalArgumentException;
	

	/**
	 * Erstellen eines Users, der dann mit Hilfe des entsprechenden Mappers in der Datenbank gespeichert wird.
	 *
	 * @param email the email
	 * @param firstname the firstname
	 * @param lastname the lastname
	 * @param nickname the nickname
	 * @param gender the gender
	 * @return the user
	 * @throws IllegalArgumentException the illegal argument exception
	 */

	User createUser(String email, String firstname, String lastname, String nickname, String gender) throws IllegalArgumentException;

	/**
	 * Methode, die einen User anhand des Nicknames in der Datenbank sucht.
	 *
	 * @param nickname the nickname
	 * @return einen Vector, der alle Userobjekte enthält, die vom Mapper aus der Datenbank übergeben werden
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Vector<User> getUserByNickname(String nickname) throws IllegalArgumentException;

	/**
	 * Methode, die den User anhand seiner Email in der Datenbank sucht.
	 *
	 * @param email the email
	 * @return den User, der vom Mapper aus der Datenbank �bergeben wird
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	User getUserByEmail(String email) throws IllegalArgumentException;

	/**
	 * Methode, die einen Benutzer anhand seiner Id in der Datenbank sucht.
	 *
	 * @param userId the user id
	 * @return das Userobjekt, das vom Mapper aus der Datenbank �bergeben wird
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	User getUserById(int userId) throws IllegalArgumentException;

	/**
	 * Methode, die alle Benutzerobjekte ausgibt.
	 *
	 * @return alle Benutzerobjekte aus der Datenbank, die vom Mapper �bergeben werden
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Vector<User> getAllUser() throws IllegalArgumentException;

	/**
	 * Methode zum Erstellen eines Posts.
	 *
	 * @param content enth�lt den Text eines Posts
	 * @param currentUsers the current users
	 * @return gibt den Post zur�ck, der mit Hilfe des Mappers in der Datenbank gespeichert wird
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Post createPost(String content, User currentUsers) throws IllegalArgumentException;

	/**
	 * Methode, die einen Post modifiziert und wieder in der Datenbank speichert.
	 *
	 * @param p enth�lt den Post, der dem Mapper �bergeben wird
	 * @param content enth�lt den String, der als neuer Text im Post gespeichert werden soll
	 * @return gibt den aktualisierten Post zur�ck, der vom Mapper in der Datenbank gespeichert wird
	 * @throws IllegalArgumentException the illegal argument exception
	 */

	Post updatePost(Post p, String content) throws IllegalArgumentException;

	/**
	 * Methode zum L�schen eines Posts. Zun�chst werden alle Likes und Kommentare, die zu diesem Post
	 * geh�ren, gel�scht, bevor der eigentliche Post an sich gel�scht wird
	 *
	 * @param p enth�lt den Post, der gel�scht werden soll
	 * @throws IllegalArgumentException the illegal argument exception
	 */

	void deletePost(Post p) throws IllegalArgumentException;

	/**
	 * Gets the post by id.
	 *
	 * @param postId the post id
	 * @return the post by id
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Post getPostById(int postId) throws IllegalArgumentException;

	/**
	 * Methode zum Abrufen aller Posts, die von einem User erstellt wurden.
	 *
	 * @param u enth�lt den User, dessen Posts abgerufen werden sollen und der an den Mapper
	 * �bergeben wird
	 * @return einen Vektor, der alle Posts enth�lt, die vom Mapper zur�ckgegeben werden
	 * @throws IllegalArgumentException the illegal argument exception
	 */

	
	Vector<Post> getAllPostsOfUser(User u) throws IllegalArgumentException;
	
	/**
	 * Methode zum Abrufen aller Kommentare eines Posts aus der Datenbank.
	 *
	 * @param p the p
	 * @return einen Vektor, der alle Posts enth�lt, die vom Mapper zur�ckgegeben werden
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	
	Vector<Comment> getAllCommentsOfPost(Post p) throws IllegalArgumentException;
	
	/**
	 * Methode zum Abrufen aller Posts aus der Datenbank.
	 *
	 * @return einen Vektor, der alle Posts enth�lt, die vom Mapper zur�ckgegeben werden
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Vector<Post> getAllPosts() throws IllegalArgumentException;

	/**
	 * Methode zum Erstellen eines Likes.
	 *
	 * @param p enth�lt den Post, bei dem der Like gesetzt wird
	 * @param owner the owner
	 * @return das Like-Objekt, das vom Mapper zur�ckgegeben wird
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Like createLike(Post p, User owner) throws IllegalArgumentException;

	/**
	 * Methode zum L�schen eines Likes.
	 *
	 * @param l definiert den Like, der aus der Datenbank gel�scht werden soll
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	void deleteLike(Like l) throws IllegalArgumentException;

	/**
	 * Gets the like by id.
	 *
	 * @return the like by id
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Like getLikeById() throws IllegalArgumentException;

	/**
	 * Methode zum Auslesen aller Likes, die ein Beitrag erhalten hat.
	 *
	 * @param p enth�lt den Post, dessen Likes zur�ckgegeben werden sollen
	 * @return ein Vektor-Objekt, das alle Likes von p enth�lt
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Vector<Like> getAllLikesOfPost(Post p) throws IllegalArgumentException;

	/**
	 * Methode zum Ausgeben aller Likes, die ein Nutzer gesetzt hat.
	 *
	 * @param u definiert den Nutzer, dessen Likes angezeigt werden sollen
	 * @return ein Vektor-Objekt, das alle Likes des Nutzers enth�lt
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Vector<Like> getAllLikesOfUser(User u) throws IllegalArgumentException;

	/**
	 * Methode zum Ausgeben aller Likes, die gesetzt wurden.
	 *
	 * @return ein Vektor-Objekt, das alle Likes enth�lt
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Vector<Like> getAllLikes() throws IllegalArgumentException;

	/**
	 * Methode zum Erstellen eines Kommentars.
	 *
	 * @param postID the post ID
	 * @param text definiert den Inhalt des Kommentars als String
	 * @param currentUser the current user
	 * @return ein Objekt vom Typ Comment, das vom Mapper zur�ckgegeben wird
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Comment createComment(int postID, String text, User currentUser) throws IllegalArgumentException;
	
	/**
	 * Methode zum Bearbeiten eines Kommentars.
	 *
	 * @param c the c
	 * @param text definiert den Inhalt des Kommentars als String
	 * @return ein Objekt vom Typ Comment, das vom Mapper zur�ckgegeben wird
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Comment updateComment(Comment c, String text) throws IllegalArgumentException;

	/**
	 * Methode zum L�schen eines Kommentars.
	 *
	 * @param c ist das Kommentarobjekt, das aus der Datenbank gel�scht werden soll
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	void deleteComment(Comment c) throws IllegalArgumentException;

	/**
	 * Methode zum Ausgeben eines Kommentars anhand seiner Id.
	 *
	 * @param commentId �bergibt die Id des Kommentars, der zur�ck gegeben werden soll
	 * @return ein Objekt vom Typ Kommentar, das vom Mapper zur�ckgegeben wird
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Comment getCommentById(int commentId) throws IllegalArgumentException;

	/**
	 * Methode zum Ausgeben aller Kommentare eines Posts.
	 *
	 * @param p definiert den Post, dessen Kommentare angezeigt werden sollen
	 * @return ein Vektor-Objekt mit Kommentaren des Posts
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Vector<Comment> getCommentsOfPost(Post p) throws IllegalArgumentException;

	/**
	 * Methode zum Ausgeben aller Kommentare, die ein Nutzer verfasst hat.
	 *
	 * @param u definiert den User, dessen Kommentare angezeigt werden sollen
	 * @return ein Vektor-Objekt mit den Kommentaren
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Vector<Comment> getCommentsOfUser(User u) throws IllegalArgumentException;
	
	/**
	 * Methode zum Ausgeben aller Kommentare.
	 *
	 * @return ein Vektor-Objekt, das alle Kommentare enthält
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Vector<Comment> getAllComments() throws IllegalArgumentException;

	/**
	 * Methode zum Erstellen eines Abonnements.
	 *
	 * @param currentUser definiert den im System angemeldeten User
	 * @param targetUser definiert den User, der abonniert werden soll
	 * @return das Abonnement, das vom vom Mapper zur�ckgeliefert wird
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Subs createSubs(int currentUser, int targetUser) throws IllegalArgumentException;

	/**
	 * Methode zum L�schen eines Abonnements.
	 *
	 * @param s definiert das Abonnement, das gel�scht werden soll
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	void deleteSubs(Subs s) throws IllegalArgumentException;

	/**
	 * Gets the subs of current user.
	 *
	 * @param u the u
	 * @return the subs of current user
	 */
	Vector<Subs> getSubsOfCurrentUser(User u);

	/**
	 * Methode zum Auslesen aller Abonnements, bei denen der aktuelle Benutzer abonniert wurde.
	 *
	 * @param u definiert den aktuellen Benutzer als Target-User
	 * @return ein Vektor-Objekt, das alle Abonnements enth�lt
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Vector<Subs> getSubsOfTargetUser(User u) throws IllegalArgumentException;

	/**
	 * Methode zum Auslesen aller vorhandenen Abonnements.
	 *
	 * @return ein Vektor-Objekt, das alle Abonnements enth�lt
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	Vector<Subs> getAllSubs() throws IllegalArgumentException;
	
	/**
	 * Holt die Information ob der Nutzer bereits aus der Datenbank.
	 * Identifiziert wird dies �ber einen boolschen R�ckgabewert, true wenn Nutzer bereits existiert, sonst false.
	 *
	 * @param email Email des Nutzers
	 * @return boolscher Wert
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public boolean isUserKnown (String email) throws IllegalArgumentException;
	
	/**
	 * Holt Klarnamen eines Nutzers aus der Datenbank.
	 *
	 * @param user Nutzer
	 * @return Klarname des Nutzers
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public String getFullNameOfUser(User user) throws IllegalArgumentException;


	/**
	 * Gets the all comments of user.
	 *
	 * @param u the u
	 * @return the all comments of user
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public Vector<Comment> getAllCommentsOfUser(User u) throws IllegalArgumentException;


	/**
	 * Gets the post by comment id.
	 *
	 * @param c the c
	 * @return the post by comment id
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public Post getPostByCommentId(Comment c) throws IllegalArgumentException;

}