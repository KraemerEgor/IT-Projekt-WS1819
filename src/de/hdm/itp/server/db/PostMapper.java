package de.hdm.itp.server.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Vector;

import com.google.gwt.sample.itProjekt.shared.bo.Post;

//import com.google.cloud.sql.jdbc.Connection;

public class PostMapper {
	
	/**
	* Konstruktor für den PostMapper (Singleton) 
	* static weil Singleton. Einzige Instanz dieser Klasse
	* 
	* @author Egor Krämer
	*/
	private static PostMapper  postmapper = null;
	
	/**
	 * Falls noch kein PostMapper existiert wird ein neuen PostMapper erstellt und gibt ihn zurück
	 * 
	 * @return erstmalig erstellter PostMapper
	 * 
	 * @author Egor Krämer
	 */
	public static PostMapper contactMapper() {
		if (postmapper == null){
			postmapper = new PostMapper();
		}
		return postmapper;
		}
	
	/**
		 * Gibt alle Post Objekte zurück welche mit P_ID, creator, cintent, create_date und mod_date befüllt sind
		 * Hierfür holen wir P_ID, creator, cintent, create_date und mod_date aus der T_Post Tabelle und speichern diese in einem Post Objekt ab und fügen diese dem Vector hinzu
		 * Diesen Vector befüllt mit Posts geben wir zurück
		 * 
		 * @return Ein Vector voller Post Objekte welche befüllt sind
		 * 
		 * @author Egor Krämer
		 */
		public Vector<Post> findAll(){
	Connection con = DBConnection.connection();
	Vector<Post> result = new Vector<Post>();
			
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT P_ID, creator, content, create_date, mod_date FROM T_Post ORDER BY mod_date");
				
				while (rs.next()){
					Post p = new Post();
					p.setId(rs.getInt("P_ID"));
					p.setOwnerId(rs.getInt("creator"));
					p.setContent(rs.getString("content"));
					p.setCreateDate(rs.getTimestamp("create_date"));
					p.setModificationDate(rs.getTimestamp("mod_date"));
					result.addElement(p);
				}		
			}catch(SQLException e2){
				e2.printStackTrace();
			}
			return result;
		}

	/**
		 * Gibt alle Posts Objekte zurück welche mit P_ID, creator, cintent, create_date und mod_date befüllt sind und die spezifische U_ID haben.
		 * Hierfür holen wir P_ID, creator, cintent, create_date und mod_date aus der T_Post Tabelle und speichern diese in einem Post Objekt ab und fügen diese dem Vector hinzu
		 * Diesen Vector befüllt mit Posts geben wir zurück
		 * 
		 * @return Ein Vector voller Post Objekte welche befüllt sind und zum übergebenen User gehören
		 * 
		 * @author Egor Krämer
		 * @author Robert Mattheis
		 */
		public Vector<Post> findPostsOfUser(User u){
	Connection con = DBConnection.connection();
	Vector<Post> result = new Vector<Post>();
			
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT P_ID, creator, content, create_date, mod_date FROM T_Post WHERE creator ="+u.getId()+ " ORDER BY mod_date");
				
				while (rs.next()){
					Post p = new Post();
					p.setId(rs.getInt("P_ID"));
					p.setOwnerId(rs.getInt("creator"));
					p.setContent(rs.getString("content"));
					p.setCreateDate(rs.getTimestamp("create_date"));
					p.setModificationDate(rs.getTimestamp("mod_date"));
					result.addElement(p);
				}		
			}catch(SQLException e2){
				e2.printStackTrace();
			}
			return result;
		}}
