package de.hdm.itp.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.hdm.itp.shared.bo.Comment;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.User;

public class CommentMapper {
	/**
	* Konstruktor f�r den CommentMapper (Singleton) 
	* static weil Singleton. Einzige Instanz dieser Klasse
	* 
	* @author Egor Kr�mer
	*/
	private static CommentMapper  commentmapper = null;
	
	/**
	 * Falls noch kein PostMapper existiert wird ein neuen PostMapper erstellt und gibt ihn zur�ck
	 * 
	 * @return erstmalig erstellter PostMapper
	 * 
	 * @author Egor Kr�mer
	 */
	public static CommentMapper commentMapper() {
		if (commentmapper == null){
			commentmapper = new CommentMapper();
		}
		return commentmapper;
		}
	
	/**
	 * Findet Kommentare durch eine C_ID und speichert die dazugehörigen Werte (C_ID, currentUser, post, text, createDate, modDate) in einem Kommentar Objekt ab und gibt dieses wieder
	 * 
	 * @param cid übergebener Integer der C_ID
	 * @return Ein vollständiges Comment Objekt
	 * 
	 * @author Egor Krämer
	 */
	public Comment findByID(Comment comment){
		Connection con = DBConnection.connection();
		Comment c = new Comment();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT C_ID, currentUser, post, text, createDate, modDate FROM T_Comment WHERE C_ID ="+ comment.getId() + " ORDER BY modDate");
			
			if (rs.next()){
				
				c.setId(rs.getInt("C_ID"));
				c.setOwnerId(rs.getInt("currentUser"));
				c.setPostId(rs.getInt("post"));
				c.setText(rs.getString("text"));
				c.setCreateDate(rs.getTimestamp("createDate"));
				c.setModDate(rs.getTimestamp("modDate"));
				
				return c;	
			}
		}
		
		catch (SQLException e){
			e.printStackTrace();
			return c;
		}
		
		return c;
	}
	
	
	public int findPostByID(Comment comment){
		Connection con = DBConnection.connection();
		int postId = 0;
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT post FROM T_Comment WHERE C_ID ="+ comment.getId() + " ORDER BY modDate");
			
			if (rs.next()){
				
				postId = rs.getInt("post");
				
				return postId;	
			}
		}
		
		catch (SQLException e){
			e.printStackTrace();
			return postId;
		}
		
		return postId;
	}
	
	
	
	/**
	 * Gibt alle Comment Objekte zur�ck welche mit C_ID, currentUser, post, text, createDate, modDate bef�llt sind von einem spezifischen User
	 * Hierf�r holen wir C_ID, currentUser, post, text, createDate, modDate aus der T_Comment Tabelle, die dem User mit der id zugeteilt sind, und speichern diese in einem Comment Objekt ab und f�gen diese dem Vector hinzu
	 * Diesen Vector bef�llt mit Posts geben wir zur�ck
	 * 
	 * @return Ein Vector voller Comment Objekte welche bef�llt sind
	 * 
	 * @author Egor Krämer
	 */
	public Vector<Comment> findAllByUID(User u){
Connection con = DBConnection.connection();
Vector<Comment> result = new Vector<Comment>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT C_ID, currentUser, post, text, createDate, modDate FROM T_Comment WHERE currentUser =" +u.getId()+" ORDER BY modDate");
			
			while (rs.next()){
				Comment c = new Comment();
				c.setId(rs.getInt("C_ID"));
				c.setOwnerId(rs.getInt("currentUser"));
				c.setPostId(rs.getInt("post"));
				c.setText(rs.getString("text"));
				c.setCreateDate(rs.getTimestamp("createDate"));
				c.setModDate(rs.getTimestamp("modDate"));
				result.addElement(c);
			}		
		}catch(SQLException e2){
			e2.printStackTrace();
		}
		return result;
	} 
	
	/*
	 * edited by Leo for findBetweenDates in the report generator begins in the DataBase ,
	 *  the Comment Object comes from the ReportMenue when the user Selects date From when and Till when he wants to select Comments
	 *  this will be needed for
	 *  Like 
	 *  Post
	 *  SubsFrom
	 *  SubsOf
	 *  (comment) 
	 *  
	 *  #### This method findAllByUIDandDate is not working for now ####
	 *  
	 *  ERROR
	 *  You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near '10:10:10.0 AND 2020-09-23 10:10:10.0 ORDER BY modDate' at line 1
	 *  
	 *  you can test it in the testMapper 
	 *  
	 *  like this
	 *  
	 *   		final CommentMapper cMapper = CommentMapper.commentMapper();

	 *  
	 *  	User u = new User();
		Comment c = new Comment();
	 *  
	 *  java.sql.Timestamp createdate = java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0");
		java.sql.Timestamp moddate = java.sql.Timestamp.valueOf("2020-09-23 10:10:10.0");

		c.setCreateDate(createdate);
		c.setModDate(moddate);
		
		System.out.println(ts);

		Vector<Comment> result = cMapper.findAllByUIDandDate(u, c);
		for (Comment c1 : result) {
			System.out.println(c1.getPostId());
			System.out.println(c1.getOwnerId());
			System.out.println(c1.getModDate());

		}

	 */
	
	public Vector<Comment> findAllByUIDandDate(User u , Comment cDate){
		Connection con = DBConnection.connection();
		Vector<Comment> result = new Vector<Comment>();
				
				try{
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT C_ID, currentUser, post, text, createDate, modDate FROM T_Comment WHERE currentUser =" +u.getId()+" BETWEEN " + cDate.getCreateDate()+ " AND " +cDate.getModDate()+ " ORDER BY modDate");
					
					while (rs.next()){
						Comment c = new Comment();
						c.setId(rs.getInt("C_ID"));
						c.setOwnerId(rs.getInt("currentUser"));
						c.setPostId(rs.getInt("post"));
						c.setText(rs.getString("text"));
						c.setCreateDate(rs.getTimestamp("createDate"));
						c.setModDate(rs.getTimestamp("modDate"));
						result.addElement(c);
					}		
				}catch(SQLException e2){
					e2.printStackTrace();
				}
				return result;
			}
	
	/**
	 * Gibt alle Comment Objekte zur�ck welche mit C_ID, currentUser, post, text, createDate, modDate bef�llt sind von einem spezifischen Post
	 * Hierf�r holen wir C_ID, currentUser, post, text, createDate, modDate aus der T_Comment Tabelle, die dem Post mit der id zugeteilt sind, und speichern diese in einem Comment Objekt ab und f�gen diese dem Vector hinzu
	 * Diesen Vector bef�llt mit Posts geben wir zur�ck
	 * 
	 * @return Ein Vector voller Comment Objekte welche bef�llt sind
	 * 
	 * @author Egor Krämer
	 */
	public Vector<Comment> findAllByPID(Post p){
Connection con = DBConnection.connection();
Vector<Comment> result = new Vector<Comment>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT C_ID, currentUser, post, text, createDate, modDate FROM T_Comment WHERE post =" +p.getId()+" ORDER BY modDate");
			
			while (rs.next()){
				Comment c = new Comment();
				c.setId(rs.getInt("C_ID"));
				c.setOwnerId(rs.getInt("currentUser"));
				c.setPostId(rs.getInt("post"));
				c.setText(rs.getString("text"));
				c.setCreateDate(rs.getTimestamp("createDate"));
				c.setModDate(rs.getTimestamp("modDate"));
				result.addElement(c);
			}		
		}catch(SQLException e2){
			e2.printStackTrace();
		}
		return result;
	}
	/**
	 * Gibt alle Comment Objekte zur�ck welche mit C_ID, currentUser, post, text, createDate, modDate bef�llt sind
	 * Hierf�r holen wir C_ID, currentUser, post, text, createDate, modDate aus der T_Comment Tabelle und speichern diese in einem Comment Objekt ab und f�gen diese dem Vector hinzu
	 * Diesen Vector bef�llt mit Comments geben wir zur�ck
	 * 
	 * @return Ein Vector voller Comment Objekte welche bef�llt sind
	 * 
	 * @author Egor Kr�mer
	 */
	public Vector<Comment> findAll(){
Connection con = DBConnection.connection();
Vector<Comment> result = new Vector<Comment>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT P_ID, creator, content, createDate, modDate FROM T_Post ORDER BY modDate");
			
			while (rs.next()){
				Comment c = new Comment();
				c.setId(rs.getInt("C_ID"));
				c.setOwnerId(rs.getInt("currentUser"));
				c.setPostId(rs.getInt("post"));
				c.setText(rs.getString("text"));
				c.setCreateDate(rs.getTimestamp("createDate"));
				c.setModDate(rs.getTimestamp("modDate"));
				result.addElement(c);
			}		
		}catch(SQLException e2){
			e2.printStackTrace();
		}
		return result;
	}
	/**
	 * Sucht nach der höchsten C_ID um diese um eins zu erhöhen und als neue C_ID zu nutzen
	 * Befüllt T_Comment mit C_ID, currentUser, post, text, createDate, modDate
	 * Ein Comment Objekt wird zurückgegeben
	 *
	 * @param comment übergebenes Comment Objekt mit allen Attributen
	 * @return Ein vollständiges Comment Objekt
	 * 
	 * @author Egor Krämer
	 */
	public Comment insert(Comment comment){
		Connection con = DBConnection.connection();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
		String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts);
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(C_ID) AS maxcid FROM T_Comment");
			if (rs.next()){
				
				comment.setId(rs.getInt("maxcid")+1);
				Statement stmt2 = con.createStatement();
				stmt2.executeUpdate("INSERT INTO T_Comment (C_ID, currentUser, post, text, createDate, modDate)"
				+ " VALUES ("
				+ comment.getId() 
				+ ", " 
				+ comment.getOwnerId()
				+ ", " 
				+ comment.getPostId()
				+ ", '" 
				+ comment.getText() 
				+ "', '" 
				+ s 
				+ "', '" 
				+ s
				+ "')") ;
						
				return comment;	
				
			}
		}
		catch (SQLException e2){
			e2.printStackTrace();
			return comment;
		}
		return comment;}
	
	/**
	 *  Befüllt T_Comment mit C_ID, currentUser, post, text, createDate, modDate, falls sich was geändert hat
	 * Ein Comment Objekt wird zurückgegeben
	 * 
	 * @param comment übergebenes Comment Objekt mit Attributen C_ID, currentUser, post, text, createDate, modDate
	 * @return Ein vollständiges Comment Objekt
	 * 
	 * @author Egor Krämer
	 */

	public Comment update(Comment comment){
		Connection con = DBConnection.connection();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
		String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts);
		
		try{
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE T_Comment SET C_ID ="
			+comment.getId()
			+", currentUser =" 
			+ comment.getOwnerId()
			+", post ='" 
			+ comment.getPostId()
			+ "', text='" 
			+ comment.getText()  
			+"', modDate='"
			+ s
			+ "' WHERE C_ID=" 
			+ comment.getId());
		}

	catch (SQLException e2){
		e2.printStackTrace();
		return comment;
	}
	return comment;}
	
	/**
	 * Entfernt alles aus T_Comment wo die C_ID der ID des übergebenen Objekts entspricht
	 * 
	 * @param comment übergebenes Comment Objekt mit Attribut C_ID
	 * 
	 * @author Egor Krämer
	 */
	public void delete (Comment comment){
		Connection con = DBConnection.connection();
					
					try{
						
						Statement stmt = con.createStatement();
						stmt.executeUpdate("DELETE FROM T_Comment WHERE C_ID =" + comment.getId());
					}
				
				catch (SQLException e2){
					e2.printStackTrace();
					}
				}
	/**
	 * Entfernt alles aus T_Comment wo die P_ID der ID des übergebenen Objekts entspricht
	 * 
	 * @param post übergebenes Post Objekt mit Attribut Post
	 * 
	 * @author Egor Krämer
	 */
	public void deleteAllByPID (Post post){
		Connection con = DBConnection.connection();
					
					try{
						
						Statement stmt = con.createStatement();
						stmt.executeUpdate("DELETE FROM T_Comment WHERE post =" + post.getId());
					}
				
				catch (SQLException e2){
					e2.printStackTrace();
					}
				}
}
