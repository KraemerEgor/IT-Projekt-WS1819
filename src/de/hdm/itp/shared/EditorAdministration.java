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
	 * @return den User, der vom Mapper aus der Datenbank übergeben wird
	 * @throws IllegalArgumentException
	 */
	User getUserByEmail(String email) throws IllegalArgumentException;

	/**
	 * Methode, die einen Benutzer anhand seiner Id in der Datenbank sucht
	 * @param id, die den User eindeutig in der Datenbank identifiziert
	 * @return das Userobjekt, das vom Mapper aus der Datenbank übergeben wird
	 * @throws IllegalArgumentException
	 */
	User getUserById(int userId) throws IllegalArgumentException;

	/**
	 * Methode, die alle Benutzerobjekte ausgibt
	 * @return alle Benutzerobjekte aus der Datenbank, die vom Mapper übergeben werden
	 * @throws IllegalArgumentException
	 */
	Vector<User> getAllUser() throws IllegalArgumentException;

	/**
	 * Methode zum Erstellen eines Posts
	 * @param content enthält den Text eines Posts
	 * @return gibt den Post zurück, der mit Hilfe des Mappers in der Datenbank gespeichert wird
	 * @throws IllegalArgumentException
	 */
	Post createPost(String content) throws IllegalArgumentException;

	/**
	 * Methode, die einen Post modifiziert und wieder in der Datenbank speichert
	 * @param p enthält den Post, der dem Mapper übergeben wird
	 * @param content enthält den String, der als neuer Text im Post gespeichert werden soll
	 * @return gibt den aktualisierten Post zurück, der vom Mapper in der Datenbank gespeichert wird
	 * @throws IllegalArgumentException
	 */

	Post updatePost(Post p, String content) throws IllegalArgumentException;

	/**
	 * Methode zum Löschen eines Posts. Zunächst werden alle Likes und Kommentare, die zu diesem Post
	 * gehören, gelöscht, bevor der eigentliche Post an sich gelöscht wird
	 * @param p enthält den Post, der gelöscht werden soll
	 * @throws IllegalArgumentException
	 */

	void deletePost(Post p) throws IllegalArgumentException;

	Post getPostById(int postId) throws IllegalArgumentException;

	/**
	 * Methode zum Abrufen aller Posts, die von einem User erstellt wurden
	 * @param u enthält den User, dessen Posts abgerufen werden sollen und der an den Mapper
	 * übergeben wird
	 * @return einen Vektor, der alle Posts enthält, die vom Mapper zurückgegeben werden
	 * @throws IllegalArgumentException
	 */

	Vector<Post> getAllPostsOfUser(User u) throws IllegalArgumentException;

	/**
	 * Methode zum Abrufen aller Posts aus der Datenbank
	 * @return einen Vektor, der alle Posts enthält, die vom Mapper zurückgegeben werden
	 * @throws IllegalArgumentException
	 */
	Vector<Post> getAllPosts() throws IllegalArgumentException;

	/**
	 * Methode zum Erstellen eines Likes
	 * @param p enthält den Post, bei dem der Like gesetzt wird
	 * @return das Like-Objekt, das vom Mapper zurückgegeben wird
	 */
	Like createLike(Post p) throws IllegalArgumentException;

	/**
	 * Methode zum Löschen eines Likes
	 * @param l definiert den Like, der aus der Datenbank gelöscht werden soll
	 * @throws IllegalArgumentException
	 */
	void deleteLike(Like l) throws IllegalArgumentException;

	Like getLikeById() throws IllegalArgumentException;

	/**
	 * Methode zum Auslesen aller Likes, die ein Beitrag erhalten hat
	 * @param p enthält den Post, dessen Likes zurückgegeben werden sollen
	 * @return ein Vektor-Objekt, das alle Likes von p enthält
	 * @throws IllegalArgumentException
	 */
	Vector<Like> getAllLikesOfPost(Post p) throws IllegalArgumentException;

	/**
	 * Methode zum Ausgeben aller Likes, die ein Nutzer gesetzt hat
	 * @param u definiert den Nutzer, dessen Likes angezeigt werden sollen
	 * @return ein Vektor-Objekt, das alle Likes des Nutzers enthält
	 * @throws IllegalArgumentException
	 */
	Vector<Like> getAllLikesOfUser(User u) throws IllegalArgumentException;

	/**
	 * Methode zum Ausgeben aller Likes, die gesetzt wurden
	 * @return ein Vektor-Objekt, das alle Likes enthält
	 * @throws IllegalArgumentException
	 */
	Vector<Like> getAllLikes() throws IllegalArgumentException;

	/**
	 * Methode zum Erstellen eines Kommentars
	 * @param p enthält den Post, zu dem der Kommentar erstellt wird
	 * @param text definiert den Inhalt des Kommentars als String
	 * @return ein Objekt vom Typ Comment, das vom Mapper zurückgegeben wird
	 * @throws IllegalArgumentException
	 */
	Comment createComment(Post p, String text) throws IllegalArgumentException;

	/**
	 * Methode zum Löschen eines Kommentars
	 * @param c ist das Kommentarobjekt, das aus der Datenbank gelöscht werden soll
	 * @throws IllegalArgumentException
	 */
	void deleteComment(Comment c) throws IllegalArgumentException;

	/**
	 * Methode zum Ausgeben eines Kommentars anhand seiner Id
	 * @param commentId übergibt die Id des Kommentars, der zurückgegeben werden soll
	 * @return ein Objekt vom Typ Kommentar, das vom Mapper zurückgegeben wird
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
	
	Vector<Comment> getCommentsOfUserBetweenDates(User u, Date dateFrom, Date dateTill) throws IllegalArgumentException;

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
	 * @return das Abonnement, das vom vom Mapper zurückgeliefert wird
	 * @throws IllegalArgumentException
	 */
	Subs createSubs(int currentUser, int targetUser) throws IllegalArgumentException;

	/**
	 * Methode zum Löschen eines Abonnements
	 * @param s definiert das Abonnement, das gelöscht werden soll
	 * @throws IllegalArgumentException
	 */
	void deleteSubs(Subs s) throws IllegalArgumentException;

	Vector<Subs> getSubsOfCurrentUser(User u);

	/**
	 * Methode zum Auslesen aller Abonnements, bei denen der aktuelle Benutzer abonniert wurde
	 * @param u definiert den aktuellen Benutzer als Target-User
	 * @return ein Vektor-Objekt, das alle Abonnements enthält
	 * @throws IllegalArgumentException
	 */
	Vector<Subs> getSubsOfTargetUser(User u) throws IllegalArgumentException;

	/**
	 * Methode zum Auslesen aller vorhandenen Abonnements
	 * @return ein Vektor-Objekt, das alle Abonnements enthält
	 * @throws IllegalArgumentException
	 */
	Vector<Subs> getAllSubs() throws IllegalArgumentException;
	
	/**
	 * Holt die Information ob der Nutzer bereits aus der Datenbank.
	 * Identifiziert wird dies über einen boolschen Rückgabewert, true wenn Nutzer bereits existiert, sonst false.
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

}