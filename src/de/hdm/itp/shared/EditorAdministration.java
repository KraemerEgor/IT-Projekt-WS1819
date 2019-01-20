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

@RemoteServiceRelativePath("administration")
public interface EditorAdministration extends RemoteService {

	/**
	 * Initialisierungsmethode
	 */
	void init() throws IllegalArgumentException;
	

	/**
	 * Erstellen eines Users, der dann mit Hilfe des entsprechenden Mappers in der Datenbank gespeichert wird
	 */

	User createUser(String email, String firstname, String lastname, String nickname, String gender) throws IllegalArgumentException;

	/**
	 * Methode, die einen User anhand des Nicknames in der Datenbank sucht
	 * @param nickname, nach dem in der Datenbank gesucht wird
	 * @return einen Vector, der alle Userobjekte enthält, die vom Mapper aus der Datenbank übergeben werden
	 * @throws IllegalArgumentException
	 */
	Vector<User> getUserByNickname(String nickname) throws IllegalArgumentException;

	/**
	 * Methode, die den User anhand seiner Email in der Datenbank sucht
	 * @param email, die den User eindeutig in der Datenbank identifiziert
	 * @return den User, der vom Mapper aus der Datenbank �bergeben wird
	 * @throws IllegalArgumentException
	 */
	User getUserByEmail(String email) throws IllegalArgumentException;

	/**
	 * Methode, die einen Benutzer anhand seiner Id in der Datenbank sucht
	 * @param id, die den User eindeutig in der Datenbank identifiziert
	 * @return das Userobjekt, das vom Mapper aus der Datenbank �bergeben wird
	 * @throws IllegalArgumentException
	 */
	User getUserById(int userId) throws IllegalArgumentException;

	/**
	 * Methode, die alle Benutzerobjekte ausgibt
	 * @return alle Benutzerobjekte aus der Datenbank, die vom Mapper �bergeben werden
	 * @throws IllegalArgumentException
	 */
	Vector<User> getAllUser() throws IllegalArgumentException;

	/**
	 * Methode zum Erstellen eines Posts
	 * @param content enth�lt den Text eines Posts
	 * @return gibt den Post zur�ck, der mit Hilfe des Mappers in der Datenbank gespeichert wird
	 * @throws IllegalArgumentException
	 */
	Post createPost(String content, User currentUsers) throws IllegalArgumentException;

	/**
	 * Methode, die einen Post modifiziert und wieder in der Datenbank speichert
	 * @param p enth�lt den Post, der dem Mapper �bergeben wird
	 * @param content enth�lt den String, der als neuer Text im Post gespeichert werden soll
	 * @return gibt den aktualisierten Post zur�ck, der vom Mapper in der Datenbank gespeichert wird
	 * @throws IllegalArgumentException
	 */

	Post updatePost(Post p, String content) throws IllegalArgumentException;

	/**
	 * Methode zum L�schen eines Posts. Zun�chst werden alle Likes und Kommentare, die zu diesem Post
	 * geh�ren, gel�scht, bevor der eigentliche Post an sich gel�scht wird
	 * @param p enth�lt den Post, der gel�scht werden soll
	 * @throws IllegalArgumentException
	 */

	void deletePost(Post p) throws IllegalArgumentException;

	Post getPostById(int postId) throws IllegalArgumentException;

	/**
	 * Methode zum Abrufen aller Posts, die von einem User erstellt wurden
	 * @param u enth�lt den User, dessen Posts abgerufen werden sollen und der an den Mapper
	 * �bergeben wird
	 * @return einen Vektor, der alle Posts enth�lt, die vom Mapper zur�ckgegeben werden
	 * @throws IllegalArgumentException
	 */

	
	Vector<Post> getAllPostsOfUser(User u) throws IllegalArgumentException;
	
	/**
	 * Methode zum Abrufen aller Kommentare eines Posts aus der Datenbank
	 * @return einen Vektor, der alle Posts enth�lt, die vom Mapper zur�ckgegeben werden
	 * @throws IllegalArgumentException
	 */
	
	Vector<Comment> getAllCommentsOfPost(Post p) throws IllegalArgumentException;
	/**
	 * Methode zum Abrufen aller Posts aus der Datenbank
	 * @return einen Vektor, der alle Posts enth�lt, die vom Mapper zur�ckgegeben werden
	 * @throws IllegalArgumentException
	 */
	Vector<Post> getAllPosts() throws IllegalArgumentException;

	/**
	 * Methode zum Erstellen eines Likes
	 * @param p enth�lt den Post, bei dem der Like gesetzt wird
	 * @return das Like-Objekt, das vom Mapper zur�ckgegeben wird
	 */
	Like createLike(Post p, User owner) throws IllegalArgumentException;

	/**
	 * Methode zum L�schen eines Likes
	 * @param l definiert den Like, der aus der Datenbank gel�scht werden soll
	 * @throws IllegalArgumentException
	 */
	void deleteLike(Like l) throws IllegalArgumentException;

	Like getLikeById() throws IllegalArgumentException;

	/**
	 * Methode zum Auslesen aller Likes, die ein Beitrag erhalten hat
	 * @param p enth�lt den Post, dessen Likes zur�ckgegeben werden sollen
	 * @return ein Vektor-Objekt, das alle Likes von p enth�lt
	 * @throws IllegalArgumentException
	 */
	Vector<Like> getAllLikesOfPost(Post p) throws IllegalArgumentException;

	/**
	 * Methode zum Ausgeben aller Likes, die ein Nutzer gesetzt hat
	 * @param u definiert den Nutzer, dessen Likes angezeigt werden sollen
	 * @return ein Vektor-Objekt, das alle Likes des Nutzers enth�lt
	 * @throws IllegalArgumentException
	 */
	Vector<Like> getAllLikesOfUser(User u) throws IllegalArgumentException;

	/**
	 * Methode zum Ausgeben aller Likes, die gesetzt wurden
	 * @return ein Vektor-Objekt, das alle Likes enth�lt
	 * @throws IllegalArgumentException
	 */
	Vector<Like> getAllLikes() throws IllegalArgumentException;

	/**
	 * Methode zum Erstellen eines Kommentars
	 * @param p enth�lt den Post, zu dem der Kommentar erstellt wird
	 * @param text definiert den Inhalt des Kommentars als String
	 * @return ein Objekt vom Typ Comment, das vom Mapper zur�ckgegeben wird
	 * @throws IllegalArgumentException
	 */
	Comment createComment(int postID, String text, User currentUser) throws IllegalArgumentException;
	
	/**
	 * Methode zum Bearbeiten eines Kommentars
	 * @param p enth�lt den Post, zu dem der Kommentar erstellt wird
	 * @param text definiert den Inhalt des Kommentars als String
	 * @return ein Objekt vom Typ Comment, das vom Mapper zur�ckgegeben wird
	 * @throws IllegalArgumentException
	 */
	Comment updateComment(Comment c, String text) throws IllegalArgumentException;

	/**
	 * Methode zum L�schen eines Kommentars
	 * @param c ist das Kommentarobjekt, das aus der Datenbank gel�scht werden soll
	 * @throws IllegalArgumentException
	 */
	void deleteComment(Comment c) throws IllegalArgumentException;

	/**
	 * Methode zum Ausgeben eines Kommentars anhand seiner Id
	 * @param commentId �bergibt die Id des Kommentars, der zur�ck gegeben werden soll
	 * @return ein Objekt vom Typ Kommentar, das vom Mapper zur�ckgegeben wird
	 * @throws IllegalArgumentException
	 */
	Comment getCommentById(int commentId) throws IllegalArgumentException;

	/**
	 * Methode zum Ausgeben aller Kommentare eines Posts
	 * @param p definiert den Post, dessen Kommentare angezeigt werden sollen
	 * @return ein Vektor-Objekt mit Kommentaren des Posts
	 * @throws IllegalArgumentException
	 */
	Vector<Comment> getCommentsOfPost(Post p) throws IllegalArgumentException;

	/**
	 * Methode zum Ausgeben aller Kommentare, die ein Nutzer verfasst hat
	 * @param u definiert den User, dessen Kommentare angezeigt werden sollen
	 * @return ein Vektor-Objekt mit den Kommentaren
	 * @throws IllegalArgumentException
	 */
	Vector<Comment> getCommentsOfUser(User u) throws IllegalArgumentException;
	
	/**
	 * Methode zum Ausgeben aller Kommentare
	 * @return ein Vektor-Objekt, das alle Kommentare enthält
	 * @throws IllegalArgumentException
	 */
	Vector<Comment> getAllComments() throws IllegalArgumentException;

	/**
	 * Methode zum Erstellen eines Abonnements
	 * @param currentUser definiert den im System angemeldeten User
	 * @param targetUser definiert den User, der abonniert werden soll
	 * @return das Abonnement, das vom vom Mapper zur�ckgeliefert wird
	 * @throws IllegalArgumentException
	 */
	Subs createSubs(int currentUser, int targetUser) throws IllegalArgumentException;

	/**
	 * Methode zum L�schen eines Abonnements
	 * @param s definiert das Abonnement, das gel�scht werden soll
	 * @throws IllegalArgumentException
	 */
	void deleteSubs(Subs s) throws IllegalArgumentException;

	Vector<Subs> getSubsOfCurrentUser(User u);

	/**
	 * Methode zum Auslesen aller Abonnements, bei denen der aktuelle Benutzer abonniert wurde
	 * @param u definiert den aktuellen Benutzer als Target-User
	 * @return ein Vektor-Objekt, das alle Abonnements enth�lt
	 * @throws IllegalArgumentException
	 */
	Vector<Subs> getSubsOfTargetUser(User u) throws IllegalArgumentException;

	/**
	 * Methode zum Auslesen aller vorhandenen Abonnements
	 * @return ein Vektor-Objekt, das alle Abonnements enth�lt
	 * @throws IllegalArgumentException
	 */
	Vector<Subs> getAllSubs() throws IllegalArgumentException;
	
	/**
	 * Holt die Information ob der Nutzer bereits aus der Datenbank.
	 * Identifiziert wird dies �ber einen boolschen R�ckgabewert, true wenn Nutzer bereits existiert, sonst false.
	 * 
	 * @param email Email des Nutzers
	 * @return boolscher Wert
	 * @throws IllegalArgumentException
	 */
	public boolean isUserKnown (String email) throws IllegalArgumentException;
	
	/**
	 * Holt Klarnamen eines Nutzers aus der Datenbank.
	 * 
	 * @param user Nutzer
	 * @return Klarname des Nutzers
	 * @throws IllegalArgumentException
	 */
	public String getFullNameOfUser(User user) throws IllegalArgumentException;


	public Vector<Comment> getAllCommentsOfUser(User u) throws IllegalArgumentException;


	public Post getPostByCommentId(Comment c) throws IllegalArgumentException;

}