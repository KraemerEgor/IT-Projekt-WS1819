package de.hdm.itp.server;

import de.hdm.itp.shared.bo.BusinessObject;
import de.hdm.itp.shared.bo.User;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.Like;
import de.hdm.itp.shared.bo.Subs;
import de.hdm.itp.shared.bo.Comment;
import de.hdm.itp.server.db.CommentMapper;
import de.hdm.itp.server.db.LikeMapper;
import de.hdm.itp.server.db.PostMapper;
import de.hdm.itp.server.db.SubsMapper;
import de.hdm.itp.server.db.UserMapper;
import de.hdm.itp.shared.*;

import java.util.ArrayList;
import java.util.Vector;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


@SuppressWarnings("serial")
public class EditorAdministrationImpl extends RemoteServiceServlet implements EditorAdministration{

	/**
	 * Referenz auf den Commentmapper, der die Kommentare mit der Datenbank abgleicht
	 */
	private CommentMapper cMapper = null;
	
	/**
	 * Refernz auf den Likemapper, der die Likes mit der Datenbank abgleicht
	 */
	private LikeMapper liMapper = null;
	
	/**
	 * Referenz auf den Postmapper, der die Posts mit der Datenbank abgleicht
	 */
	private PostMapper pMapper = null;
	
	/**
	 * Referenz auf den Subsmapper, der die Subscriptions mit der Datenbank abgleicht
	 */
	private SubsMapper sMapper = null;
	
	/**
	 * Referenz auf den Usermapper, der die User mit der Datenbank abgleicht
	 */
	private UserMapper uMapper = null;
	
	private static EditorAdministrationImpl editorAdministrationImpl = null;
	
	public static EditorAdministrationImpl editorAdministrationImpl() {
		if(editorAdministrationImpl == null) {
			editorAdministrationImpl = new EditorAdministrationImpl();
		}
		return editorAdministrationImpl;
	}
	
	/**
	 * No-Arguments-Constructor
	 */
	public EditorAdministrationImpl() throws IllegalArgumentException{
		
	}
	
	/**
	 * Initialisierungsmethode
	 */
	public void init() throws IllegalArgumentException{
		
		this.cMapper = CommentMapper.commentMapper();
		this.liMapper = LikeMapper.likeMapper();
		this.pMapper = PostMapper.postMapper();
		this.sMapper = SubsMapper.subsMapper();
		this.uMapper = UserMapper.userMapper();
		
	}
	
	/**
	 * Ende Initialisierung
	 */

	
	/**
	 * Erstellen eines Users, der dann mit Hilfe des entsprechenden Mappers in der Datenbank gespeichert wird
	 */
	
	public User createUser(String email, String firstname, String lastname, String nickname, String gender) {
		
		User u = new User();
		u.setEmail(email);
		u.setFirstname(firstname);
		u.setLastname(lastname);
		u.setNickname(nickname);
		u.setGender(gender);
		
		
		uMapper.insert(u);
		
		return u;
	}
	
	/**
	 * Methode, die einen User anhand des Nicknames in der Datenbank sucht
	 * @param nickname, nach dem in der Datenbank gesucht wird
	 * @return einen Vector, der alle Userobjekte enthält, die vom Mapper aus der Datenbank übergeben werden
	 * @throws IllegalArgumentException
	 */
	public Vector<User> getUserByNickname (String nickname) throws IllegalArgumentException{
		
		return this.uMapper.findByNickname(nickname);
	}
	
	/**
	 * Methode, die den User anhand seiner Email in der Datenbank sucht
	 * @param email, die den User eindeutig in der Datenbank identifiziert
	 * @return den User, der vom Mapper aus der Datenbank übergeben wird
	 * @throws IllegalArgumentException
	 */
	public User getUserByEmail (String email)
	throws IllegalArgumentException {
		
		return this.uMapper.findByEMail(email);
		
	}
	
	
	/**
	 * Methode, die einen Benutzer anhand seiner Id in der Datenbank sucht
	 * @param id, die den User eindeutig in der Datenbank identifiziert
	 * @return das Userobjekt, das vom Mapper aus der Datenbank übergeben wird
	 * @throws IllegalArgumentException
	 */
	public User getUserById (int userId)
	throws IllegalArgumentException {
		
		User u = new User();
		
		u.setId(userId);
		return this.uMapper.findByID(u);
	}
	
	
	/**
	 * Methode, die alle Benutzerobjekte ausgibt
	 * @return alle Benutzerobjekte aus der Datenbank, die vom Mapper übergeben werden
	 * @throws IllegalArgumentException
	 */
	public Vector<User> getAllUser()
	throws IllegalArgumentException {
		
		return this.uMapper.findAll();
	}
	
	/**
	 * **************************
	 * Ende der User-Methoden
	 * **************************
	 */
	
	/**
	 * **************************
	 * Beginn der Post-Methoden
	 * **************************
	 */
	
	
	/**
	 * Methode zum Erstellen eines Posts
	 * @param content enthält den Text eines Posts
	 * @return gibt den Post zurück, der mit Hilfe des Mappers in der Datenbank gespeichert wird
	 * @throws IllegalArgumentException
	 */
	public Post createPost(String content) throws IllegalArgumentException{
		
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		long time = date.getTime();
		
		
		Post p = new Post();
		p.setContent(content);
		p.setCreateDate(new Timestamp(time));
		
		return pMapper.insert(p);
	}
	
	/**
	 * Methode, die einen Post modifiziert und wieder in der Datenbank speichert
	 * @param p enthält den Post, der dem Mapper übergeben wird
	 * @param content enthält den String, der als neuer Text im Post gespeichert werden soll
	 * @return gibt den aktualisierten Post zurück, der vom Mapper in der Datenbank gespeichert wird
	 * @throws IllegalArgumentException
	 */
	
	public Post updatePost (Post p, String content) throws IllegalArgumentException{
		
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		long time = date.getTime();
		
		pMapper.findByID(p);
		p.setContent(content);
		p.setModDate(new Timestamp(time));
		
		return pMapper.update(p);
		
	}
	
	/**
	 * Methode zum Löschen eines Posts. Zunächst werden alle Likes und Kommentare, die zu diesem Post
	 * gehören, gelöscht, bevor der eigentliche Post an sich gelöscht wird
	 * @param p enthält den Post, der gelöscht werden soll
	 * @throws IllegalArgumentException
	 */
	
	public void deletePost (Post p) throws IllegalArgumentException{
		
		Vector<Like> likesOfPost = liMapper.findAllByPID(p);
		for (Like l :likesOfPost) {
			liMapper.delete(l);
		}
		
		Vector<Comment> commentsOfPost = cMapper.findAllByPID(p);
		for (Comment c : commentsOfPost) {
			cMapper.delete(c);
		}
		
		pMapper.delete(p);
	}
	
	public Post getPostById(int postId) throws IllegalArgumentException{
	
		Post p = new Post();
		p.setId(postId);
		return pMapper.findByID(p);
		
	}
	
	/**
	 * Methode zum Abrufen aller Posts, die von einem User erstellt wurden
	 * @param u enthält den User, dessen Posts abgerufen werden sollen und der an den Mapper
	 * übergeben wird
	 * @return einen Vektor, der alle Posts enthält, die vom Mapper zurückgegeben werden
	 * @throws IllegalArgumentException
	 */
	
	public Vector<Post> getAllPostsOfUser(User u) throws IllegalArgumentException{
		
		return pMapper.findAllByUID(u);
		
	}
	
	
	/**
	 * Methode zum Abrufen aller Posts aus der Datenbank
	 * @return einen Vektor, der alle Posts enthält, die vom Mapper zurückgegeben werden
	 * @throws IllegalArgumentException
	 */
	public Vector<Post> getAllPosts() throws IllegalArgumentException{
		
		return pMapper.findAll();
		
	}
	
	/**
	 * ****************************
	 * Ende der Post-Methoden
	 * ****************************
	 */
	/**
	 * ****************************
	 * Anfang der Like-Methoden
	 * ****************************
	 */
	
	
	/**
	 * Methode zum Erstellen eines Likes
	 * @param p enthält den Post, bei dem der Like gesetzt wird
	 * @return das Like-Objekt, das vom Mapper zurückgegeben wird
	 */
	public Like createLike (Post p) throws IllegalArgumentException{
		
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		long time = date.getTime();
		
		Like l = new Like();
		l.setPostId(p.getId());
		l.setCreateDate(new Timestamp(time));
		
		return liMapper.insert(l);
		
	}
	
	
	/**
	 * Methode zum Löschen eines Likes
	 * @param l definiert den Like, der aus der Datenbank gelöscht werden soll
	 * @throws IllegalArgumentException
	 */
	public void deleteLike(Like l) throws IllegalArgumentException{
		
		liMapper.delete(l);
		
	}
	
	public Like getLikeById() throws IllegalArgumentException{
		
		//TODO
		return new Like();
		
	}
	
	/**
	 * Methode zum Auslesen aller Likes, die ein Beitrag erhalten hat
	 * @param p enthält den Post, dessen Likes zurückgegeben werden sollen
	 * @return ein Vektor-Objekt, das alle Likes von p enthält
	 * @throws IllegalArgumentException
	 */
	public Vector<Like> getAllLikesOfPost(Post p) throws IllegalArgumentException{
		
		return liMapper.findAllByPID(p);
		
	}
	
	/**
	 * Methode zum Ausgeben aller Likes, die ein Nutzer gesetzt hat
	 * @param u definiert den Nutzer, dessen Likes angezeigt werden sollen
	 * @return ein Vektor-Objekt, das alle Likes des Nutzers enthält
	 * @throws IllegalArgumentException
	 */
	public Vector<Like> getAllLikesOfUser(User u) throws IllegalArgumentException{
		
		return liMapper.findAllByUID(u);
		
	}
	
	/**
	 * Methode zum Ausgeben aller Likes, die gesetzt wurden
	 * @return ein Vektor-Objekt, das alle Likes enthält
	 * @throws IllegalArgumentException
	 */
	public Vector<Like> getAllLikes() throws IllegalArgumentException{
		
		return liMapper.findAll();
		
	}
	
	/**
	 * ***********************
	 * Ende der Like-Methoden
	 * ***********************
	 */

	
	/**
	 * *******************************
	 * Anfang der Kommentar-Methoden
	 * *******************************
	 */
	
	
	/**
	 * Methode zum Erstellen eines Kommentars
	 * @param p enthält den Post, zu dem der Kommentar erstellt wird
	 * @param text definiert den Inhalt des Kommentars als String
	 * @return ein Objekt vom Typ Comment, das vom Mapper zurückgegeben wird
	 * @throws IllegalArgumentException
	 */
	public Comment createComment(Post p, String text) throws IllegalArgumentException {
		
		Comment c = new Comment();
		c.setPostId(p.getId());
		c.setText(text);
		
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		long time = date.getTime();
		
		c.setCreateDate(new Timestamp(time));
		
		return cMapper.insert(c);
		
	}
	
	
	/**
	 * Methode zum Löschen eines Kommentars
	 * @param c ist das Kommentarobjekt, das aus der Datenbank gelöscht werden soll
	 * @throws IllegalArgumentException
	 */
	public void deleteComment(Comment c) throws IllegalArgumentException {
		
		cMapper.delete(c);
		
	}
	
	
	/**
	 * Methode zum Ausgeben eines Kommentars anhand seiner Id
	 * @param commentId übergibt die Id des Kommentars, der zurückgegeben werden soll
	 * @return ein Objekt vom Typ Kommentar, das vom Mapper zurückgegeben wird
	 * @throws IllegalArgumentException
	 */
	public Comment getCommentById(int commentId) throws IllegalArgumentException {
		
		Comment c = new Comment();
		c.setId(commentId);
		//TODO: Mapper anpassen, dass in der Datenbank nach id gesucht wird, nicht nach Objekt
		return cMapper.findByID(c);
		
	}
	
	
	/**
	 * Methode zum Ausgeben aller Kommentare eines Posts
	 * @param p definiert den Post, dessen Kommentare angezeigt werden sollen
	 * @return ein Vektor-Objekt mit Kommentaren des Posts
	 * @throws IllegalArgumentException
	 */
	public Vector<Comment> getCommentsOfPost(Post p) throws IllegalArgumentException {
		
		return cMapper.findAllByPID(p);
		
	}
	
	/**
	 * Methode zum Ausgeben aller Kommentare, die ein Nutzer verfasst hat
	 * @param u definiert den User, dessen Kommentare angezeigt werden sollen
	 * @return ein Vektor-Objekt mit den Kommentaren
	 * @throws IllegalArgumentException
	 */
	public Vector<Comment> getCommentsOfUser (User u) throws IllegalArgumentException {
		
		return cMapper.findAllByUID(u);
		
	}
	
	
	/**
	 * Methode zum Ausgeben aller Kommentare
	 * @return ein Vektor-Objekt, das alle Kommentare enthält
	 * @throws IllegalArgumentException
	 */
	public Vector<Comment> getAllComments () throws IllegalArgumentException {
		
		return cMapper.findAll();
		
	}
	
	/**
	 * *******************************
	 * Ende der Comment-Methoden
	 * *******************************
	 */
	/**
	 * *******************************
	 * Anfang der Subs-Methoden
	 * *******************************
	 */
	
	/**
	 * Methode zum Erstellen eines Abonnements
	 * @param currentUser definiert den im System angemeldeten User
	 * @param targetUser definiert den User, der abonniert werden soll
	 * @return das Abonnement, das vom vom Mapper zurückgeliefert wird
	 * @throws IllegalArgumentException
	 */
	public Subs createSubs(int currentUser, int targetUser) throws IllegalArgumentException {
		
		Subs s = new Subs();
		s.setCurrentUser(currentUser);
		s.setTargetUser(targetUser);
		
		return sMapper.insert(s);
		
	}
	
	/**
	 * Methode zum Löschen eines Abonnements
	 * @param s definiert das Abonnement, das gelöscht werden soll
	 * @throws IllegalArgumentException
	 */
	public void deleteSubs(Subs s) throws IllegalArgumentException {
		
		sMapper.delete(s);
		
	}
	
	/**
	 * Methode zum Auslesen aller Abonnements des aktuellen Benutzers
	 * @param u definiert den Benutzer, dessen Abonnements ausgelesen werden sollen
	 * @return ein Vektor-Objekt, das die Abonnements enthält
	 * @throws IllegalArgumentException
	 */
	public Vector<Subs> getSubsOfCurrentUser (User u) throws IllegalArgumentException {
		
		return sMapper.findAllByCurrentUserId(u);
		
	}
	
	/**
	 * Methode zum Auslesen aller Abonnements, bei denen der aktuelle Benutzer abonniert wurde
	 * @param u definiert den aktuellen Benutzer als Target-User
	 * @return ein Vektor-Objekt, das alle Abonnements enthält
	 * @throws IllegalArgumentException
	 */
	public Vector<Subs> getSubsOfTargetUser (User u) throws IllegalArgumentException {
		
		return sMapper.findAllByTargetUserId(u);
		
	}
	
	/**
	 * Methode zum Auslesen aller vorhandenen Abonnements
	 * @return ein Vektor-Objekt, das alle Abonnements enthält
	 * @throws IllegalArgumentException
	 */
	public Vector<Subs> getAllSubs() throws IllegalArgumentException {
		
		return sMapper.findAll();
		
	}
	
	/**
	 * ****************************
	 * Ende der Subs-Methoden
	 * ****************************
	 */
}
