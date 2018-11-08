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
	public User getUserById (int id)
	throws IllegalArgumentException {
		
		return this.uMapper.findByID(id);
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
	
	public Post createPost(String content, Timestamp createDate) throws IllegalArgumentException{
		
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		
		
		Post p = new Post();
		p.setContent(content);
		//p.setCreateDate(new Timestamp());
	}
	
	
	
	
}
