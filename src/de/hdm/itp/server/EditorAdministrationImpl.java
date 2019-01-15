package de.hdm.itp.server;

import de.hdm.itp.shared.bo.BusinessObject;
import de.hdm.itp.shared.bo.User;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.Like;
import de.hdm.itp.shared.bo.Subs;
import de.hdm.itp.shared.bo.Comment;
import de.hdm.itp.client.ClientsideSettings;
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

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class EditorAdministrationImpl extends RemoteServiceServlet implements EditorAdministration {
	
	User currentUser = new User();

	/**
	 * Referenz auf den Commentmapper, der die Kommentare mit der Datenbank
	 * abgleicht
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
	 * Referenz auf den Subsmapper, der die Subscriptions mit der Datenbank
	 * abgleicht
	 */
	private SubsMapper sMapper = null;

	/**
	 * Referenz auf den Usermapper, der die User mit der Datenbank abgleicht
	 */
	private UserMapper uMapper = null;

	/**
	 * Referenz auf die EditorAdministration, welche administrative F�higkeiten definiert
	 */
	private static EditorAdministration editorAdministrationImpl = null;

	/**
	 * Abfrage ob bereits ein editorAdministrationImpl Object vorhanden ist sonst neu Erstellung eines und R�ckgabe dessen
	 * @return
	 */
	public static EditorAdministration editorAdministrationImpl() {
		if (editorAdministrationImpl == null) {
			editorAdministrationImpl = new EditorAdministrationImpl();
		}
		return editorAdministrationImpl;
	}

	/**
	 * No-Arguments-Constructor der EditorAdministrationImpl
	 */
	public EditorAdministrationImpl() throws IllegalArgumentException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hdm.itp.server.EditorAdministration#init()
	 */
	@Override
	public void init() throws IllegalArgumentException {

		this.cMapper = CommentMapper.commentMapper();
		this.liMapper = LikeMapper.likeMapper();
		this.pMapper = PostMapper.postMapper();
		this.sMapper = SubsMapper.subsMapper();
		this.uMapper = UserMapper.userMapper();
		currentUser = ClientsideSettings.getUser();

	}

	/**
	 * Ende Initialisierung
	 */

	/**
	 * Methode zum sp�teren Erstellen eines User Objects
	 * Hierbei wird ebenfalls definiert welche Attribute �bergeben werden m�ssen zum Erstellen
	 * @return User Object
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.server.EditorAdministration#getUserByNickname(java.lang.String)
	 */
	@Override
	public Vector<User> getUserByNickname(String nickname) throws IllegalArgumentException {

		return this.uMapper.findByNickname(nickname);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hdm.itp.server.EditorAdministration#getUserByEmail(java.lang.String)
	 */
	@Override
	public User getUserByEmail(String email) throws IllegalArgumentException {

		return this.uMapper.findByEMail(email);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hdm.itp.server.EditorAdministration#getUserById(int)
	 */
	@Override
	public User getUserById(int userId) throws IllegalArgumentException {

		User u = new User();

		u.setId(userId);
		return this.uMapper.findByID(u);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hdm.itp.server.EditorAdministration#getAllUser()
	 */
	@Override
	public Vector<User> getAllUser() throws IllegalArgumentException {

		return this.uMapper.findAll();
	}

	/**
	 * ************************** Ende der User-Methoden **************************
	 */

	/**
	 * ************************** Beginn der Post-Methoden
	 * **************************
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hdm.itp.server.EditorAdministration#createPost(java.lang.String)
	 */
	@Override
	public Post createPost(String content, User currentUsers) throws IllegalArgumentException {

		//DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		//Date date = new Date();
		//long time = date.getTime();

		Post p = new Post();

		System.out.println("Inhalt: "+content);
		p.setContent(content);
		//p.setCreateDate(new Timestamp(time));
		

		p.setOwnerId(currentUsers.getId());
		

		return pMapper.insert(p);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.server.EditorAdministration#updatePost(de.hdm.itp.shared.bo.Post,
	 * java.lang.String)
	 */

	@Override
	public Post updatePost(Post p, String content) throws IllegalArgumentException {

		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		long time = date.getTime();

		pMapper.findByID(p);
		p.setContent(content);
		p.setModDate(new Timestamp(time));

		return pMapper.update(p);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.server.EditorAdministration#deletePost(de.hdm.itp.shared.bo.Post)
	 */

	@Override
	public void deletePost(Post p) throws IllegalArgumentException {

		Vector<Like> likesOfPost = liMapper.findAllByPID(p);
		for (Like l : likesOfPost) {
			liMapper.delete(l);
		}

		Vector<Comment> commentsOfPost = cMapper.findAllByPID(p);
		for (Comment c : commentsOfPost) {
			cMapper.delete(c);
		}

		pMapper.delete(p);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hdm.itp.server.EditorAdministration#getPostById(int)
	 */
	@Override
	public Post getPostById(int postId) throws IllegalArgumentException {

		Post p = new Post();
		p.setId(postId);
		return pMapper.findByID(p);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.server.EditorAdministration#getAllPostsOfUser(de.hdm.itp.shared.bo
	 * .User)
	 */

	@Override
	public Vector<Post> getAllPostsOfUser(User u) throws IllegalArgumentException {

		return pMapper.findAllByUID(u);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hdm.itp.server.EditorAdministration#getAllPosts()
	 */
	@Override
	public Vector<Post> getAllPosts() throws IllegalArgumentException {

		return pMapper.findAll();

	}

	/**
	 * **************************** Ende der Post-Methoden
	 * ****************************
	 */
	/**
	 * **************************** Anfang der Like-Methoden
	 * ****************************
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.server.EditorAdministration#createLike(de.hdm.itp.shared.bo.Post)
	 */
	@Override
	public Like createLike(Post p, User owner) throws IllegalArgumentException {

		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		long time = date.getTime();

		Like l = new Like();
		l.setOwnerId(owner.getId());
		l.setPostId(p.getId());
		l.setCreateDate(new Timestamp(time));

		return liMapper.insert(l);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.server.EditorAdministration#deleteLike(de.hdm.itp.shared.bo.Like)
	 */
	@Override
	public void deleteLike(Like l) throws IllegalArgumentException {

		liMapper.delete(l);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hdm.itp.server.EditorAdministration#getLikeById()
	 */
	@Override
	public Like getLikeById() throws IllegalArgumentException {

		// TODO
		return new Like();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.server.EditorAdministration#getAllLikesOfPost(de.hdm.itp.shared.bo
	 * .Post)
	 */
	@Override
	public Vector<Like> getAllLikesOfPost(Post p) throws IllegalArgumentException {

		return liMapper.findAllByPID(p);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.server.EditorAdministration#getAllLikesOfUser(de.hdm.itp.shared.bo
	 * .User)
	 */
	@Override
	public Vector<Like> getAllLikesOfUser(User u) throws IllegalArgumentException {

		return liMapper.findAllByUID(u);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hdm.itp.server.EditorAdministration#getAllLikes()
	 */
	@Override
	public Vector<Like> getAllLikes() throws IllegalArgumentException {

		return liMapper.findAll();

	}

	/**
	 * *********************** Ende der Like-Methoden ***********************
	 */

	/**
	 * ******************************* Anfang der Kommentar-Methoden
	 * *******************************
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.server.EditorAdministration#createComment(de.hdm.itp.shared.bo.
	 * Post, java.lang.String)
	 */
	@Override
	public Comment createComment(int postID, String text, User currentUser) throws IllegalArgumentException {

		Comment c = new Comment();
		c.setPostId(postID);
		c.setText(text);
		c.setOwnerId(currentUser.getId());

		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		long time = date.getTime();

		c.setCreateDate(new Timestamp(time));

		return cMapper.insert(c);

	}
	@Override
	public Comment updateComment(Comment c, String content) throws IllegalArgumentException {

		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		long time = date.getTime();

		c = cMapper.findByID(c);
		c.setText(content);
		c.setModDate(new Timestamp(time));

		return cMapper.update(c);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.server.EditorAdministration#deleteComment(de.hdm.itp.shared.bo.
	 * Comment)
	 */
	@Override
	public void deleteComment(Comment c) throws IllegalArgumentException {

		cMapper.delete(c);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hdm.itp.server.EditorAdministration#getCommentById(int)
	 */
	@Override
	public Comment getCommentById(int commentId) throws IllegalArgumentException {

		Comment c = new Comment();
		c.setId(commentId);
		// TODO: Mapper anpassen, dass in der Datenbank nach id gesucht wird, nicht nach
		// Objekt
		return cMapper.findByID(c);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.server.EditorAdministration#getCommentsOfPost(de.hdm.itp.shared.bo
	 * .Post)
	 */
	@Override
	public Vector<Comment> getCommentsOfPost(Post p) throws IllegalArgumentException {

		return cMapper.findAllByPID(p);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.server.EditorAdministration#getCommentsOfUser(de.hdm.itp.shared.bo
	 * .User)
	 */
	@Override
	public Vector<Comment> getCommentsOfUser(User u) throws IllegalArgumentException {

		return cMapper.findAllByUID(u);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hdm.itp.server.EditorAdministration#getAllComments()
	 */
	@Override
	public Vector<Comment> getAllComments() throws IllegalArgumentException {

		return cMapper.findAll();

	}

	/**
	 * ******************************* Ende der Comment-Methoden
	 * *******************************
	 */
	/**
	 * ******************************* Anfang der Subs-Methoden
	 * *******************************
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hdm.itp.server.EditorAdministration#createSubs(int, int)
	 */
	@Override
	public Subs createSubs(int currentUser, int targetUser) throws IllegalArgumentException {

		Subs s = new Subs();
		s.setCurrentUser(currentUser);
		s.setTargetUser(targetUser);

		return sMapper.insert(s);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.server.EditorAdministration#deleteSubs(de.hdm.itp.shared.bo.Subs)
	 */
	@Override
	public void deleteSubs(Subs s) throws IllegalArgumentException {

		sMapper.delete(s);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.server.EditorAdministration#getSubsOfCurrentUser(de.hdm.itp.shared
	 * .bo.User)
	 */
	@Override
	public Vector<Subs> getSubsOfCurrentUser(User u) throws IllegalArgumentException {

		return sMapper.findAllByCurrentUserId(u);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.server.EditorAdministration#getSubsOfTargetUser(de.hdm.itp.shared.
	 * bo.User)
	 */
	@Override
	public Vector<Subs> getSubsOfTargetUser(User u) throws IllegalArgumentException {

		return sMapper.findAllByTargetUserId(u);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hdm.itp.server.EditorAdministration#getAllSubs()
	 */
	@Override
	public Vector<Subs> getAllSubs() throws IllegalArgumentException {

		return sMapper.findAll();

	}

	/**
	 * **************************** Ende der Subs-Methoden
	 * ****************************
	 */

	/**
	 * Holt die Information ob der Nutzer bereits aus der Datenbank. Identifiziert
	 * wird dies über einen boolschen Rückgabewert, true wenn Nutzer bereits
	 * existiert, sonst false.
	 * 
	 * @param email Email des Nutzers
	 * @return boolscher Wert
	 * @throws IllegalArgumentException
	 */
	public boolean isUserKnown(String email) throws IllegalArgumentException {

		// Wenn der User noch nicht in der Datenbank existiert, wird ein neuer User
		// angelegt.
		if (getUserByEmail(email).getEmail() == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Holt Klarnamen eines Nutzers aus der Datenbank.
	 * 
	 * @param user Nutzer
	 * @return Klarname des Nutzers
	 * @throws IllegalArgumentException
	 */
	public String getFullNameOfUser(User user) throws IllegalArgumentException {

		String result = null;
		user = getUserByEmail(user.getEmail());

		result = user.getFirstname() + " " + user.getLastname();
		return result;
	}

}
