package de.hdm.itp.server.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.util.Vector;

import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.User;

//import com.google.cloud.sql.jdbc.Connection;
/**
 * The Class PostMapper.
 */
public class PostMapper {
	
	/**
	* Konstruktor f�r den PostMapper (Singleton) 
	* static weil Singleton. Einzige Instanz dieser Klasse
	* 
	* @author Egor Kr�mer
	*/
	private static PostMapper  postmapper = null;
	
	/**
	 * Falls noch kein PostMapper existiert wird ein neuen PostMapper erstellt und gibt ihn zur�ck.
	 *
	 * @author Egor Kr�mer
	 * @return erstmalig erstellter PostMapper
	 */
	public static PostMapper postMapper() {
		if (postmapper == null){
			postmapper = new PostMapper();
		}
		return postmapper;
		}
	
	/**
	 * Findet Posts durch eine P_ID und speichert die dazugehörigen Werte (P_ID, creator, content, createDate, modDate) in einem Post Objekt ab und gibt dieses wieder.
	 *
	 * @author Egor Krämer
	 * @param post the post
	 * @return Ein vollständiges Post Objekt
	 */
	public Post findByID(Post post){
		Connection con = DBConnection.connection();
		Post p = new Post();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT P_ID, creator, content, createDate, modDate FROM T_Post WHERE P_ID ="+ post.getId() + " ORDER BY modDate");
			
			if (rs.next()){
				
				p.setId(rs.getInt("P_ID"));
				p.setOwnerId(rs.getInt("creator"));
				p.setContent(rs.getString("content"));
				p.setCreateDate(rs.getTimestamp("createDate"));
				p.setModDate(rs.getTimestamp("modDate"));
				
				return p;	
			}
		}
		
		catch (SQLException e){
			e.printStackTrace();
			return p;
		}
		
		return p;
	}
	
	/**
	 * Gibt alle Post Objekte zur�ck welche mit P_ID, creator, cintent, createDate und modDate bef�llt sind von einem spezifischen User
	 * Hierf�r holen wir P_ID, creator, cintent, createDate und modDate aus der T_Post Tabelle, die dem User mit der id zugeteilt sind, und speichern diese in einem Post Objekt ab und f�gen diese dem Vector hinzu
	 * Diesen Vector bef�llt mit Posts geben wir zur�ck.
	 *
	 * @author Egor Krämer
	 * @param u the u
	 * @return Ein Vector voller Post Objekte welche bef�llt sind
	 */
	public Vector<Post> findAllByUID(User u){
Connection con = DBConnection.connection();
Vector<Post> result = new Vector<Post>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT P_ID, creator, content, createDate, modDate FROM T_Post WHERE creator =" +u.getId()+" ORDER BY modDate");
			
			while (rs.next()){
				Post p = new Post();
				p.setId(rs.getInt("P_ID"));
				p.setOwnerId(rs.getInt("creator"));
				p.setContent(rs.getString("content"));
				p.setCreateDate(rs.getTimestamp("createDate"));
				p.setModDate(rs.getTimestamp("modDate"));
				result.addElement(p);
			}		
		}catch(SQLException e2){
			e2.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Gibt alle Post Objekte zur�ck welche mit P_ID, creator, content, createDate und modDate bef�llt sind
	 * Hierf�r holen wir P_ID, creator, cintent, createDate und modDate aus der T_Post Tabelle und speichern diese in einem Post Objekt ab und f�gen diese dem Vector hinzu
	 * Diesen Vector bef�llt mit Posts geben wir zur�ck.
	 *
	 * @author Egor Kr�mer
	 * @return Ein Vector voller Post Objekte welche bef�llt sind
	 */
		public Vector<Post> findAll(){
	Connection con = DBConnection.connection();
	Vector<Post> result = new Vector<Post>();
			
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT P_ID, creator, content, createDate, modDate FROM T_Post ORDER BY modDate");
				
				while (rs.next()){
					Post p = new Post();
					p.setId(rs.getInt("P_ID"));
					p.setOwnerId(rs.getInt("creator"));
					p.setContent(rs.getString("content"));
					p.setCreateDate(rs.getTimestamp("createDate"));
					p.setModDate(rs.getTimestamp("modDate"));
					result.addElement(p);
				}		
			}catch(SQLException e2){
				e2.printStackTrace();
			}
			return result;
		}
		
		/**
		 * Sucht nach der höchsten P_ID um diese um eins zu erhöhen und als neue P_ID zu nutzen
		 * Befüllt T_Post mit P_ID, creator, content, createDate und modDate
		 * Ein Post Objekt wird zurückgegeben.
		 *
		 * @author Egor Krämer
		 * @param post übergebenes Post Objekt mit allen Attributen
		 * @return Ein vollständiges Post Objekt
		 */
		public Post insert(Post post){
			Connection con = DBConnection.connection();
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			
			String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts);
			
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT MAX(P_ID) AS maxpid FROM T_Post");
				if (rs.next()){
					
					post.setId(rs.getInt("maxpid")+1);
					Statement stmt2 = con.createStatement();
					stmt2.executeUpdate("INSERT INTO T_Post (P_ID, creator, content, createDate, modDate)"
					+ " VALUES ("
					+ post.getId() 
					+ ", " 
					+ post.getOwnerId()
					+ ", '" 
					+ post.getContent() 
					+ "', '" 
					+ s 
					+ "', '" 
					+ s
					+ "')") ;
							
					return post;	
					
				}
			}
			catch (SQLException e2){
				e2.printStackTrace();
				return post;
			}
			return post;}
		
		/**
		 *  Befüllt T_Post mit P_ID, creator, content, createDate und modDate, falls sich was geändert hat
		 * Ein Post Objekt wird zurückgegeben.
		 *
		 * @author Egor Krämer
		 * @param post übergebenes Post Objekt mit Attributen P_ID und type
		 * @return Ein vollständiges Post Objekt
		 */

		public Post update(Post post){
			Connection con = DBConnection.connection();
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			
			String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts);
			
			try{
				Statement stmt = con.createStatement();
				stmt.executeUpdate("UPDATE T_Post SET P_ID ="
				+post.getId()
				+", creator =" 
				+ post.getOwnerId()
				+ ", content='" 
				+ post.getContent() 
//				+"', createDate="
//				+ post.getCreateDate() 
				+"', modDate='"
				+ s 
				+ "' WHERE P_ID=" 
				+ post.getId());
			}

		catch (SQLException e2){
			e2.printStackTrace();
			return post;
		}
		return post;}
		
		/**
		 * Entfernt alles aus T_Post wo die P_ID der ID des übergebenen Objekts entspricht.
		 *
		 * @author Egor Krämer
		 * @param post übergebenes Post Objekt mit Attribut P_ID
		 */
		public void delete (Post post){
			Connection con = DBConnection.connection();
						
						try{
							
							Statement stmt = con.createStatement();
							stmt.executeUpdate("DELETE FROM T_Post WHERE P_ID =" + post.getId());
						}
					
					catch (SQLException e2){
						e2.printStackTrace();
						}
					}
	}
