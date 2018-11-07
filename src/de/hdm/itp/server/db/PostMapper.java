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
	* Konstruktor f�r den PostMapper (Singleton) 
	* static weil Singleton. Einzige Instanz dieser Klasse
	* 
	* @author Egor Kr�mer
	*/
	private static PostMapper  postmapper = null;
	
	/**
	 * Falls noch kein PostMapper existiert wird ein neuen PostMapper erstellt und gibt ihn zur�ck
	 * 
	 * @return erstmalig erstellter PostMapper
	 * 
	 * @author Egor Kr�mer
	 */
	public static PostMapper contactMapper() {
		if (postmapper == null){
			postmapper = new PostMapper();
		}
		return postmapper;
		}
	
	/**
		 * Gibt alle Post Objekte zur�ck welche mit P_ID, creator, cintent, create_date und mod_date bef�llt sind
		 * Hierf�r holen wir P_ID, creator, cintent, create_date und mod_date aus der T_Post Tabelle und speichern diese in einem Post Objekt ab und f�gen diese dem Vector hinzu
		 * Diesen Vector bef�llt mit Posts geben wir zur�ck
		 * 
		 * @return Ein Vector voller Post Objekte welche bef�llt sind
		 * 
		 * @author Egor Kr�mer
		 */
		public Vector<Post> findAll(){
	Connection con = DBConnection.connection();
	Vector<Post> result = new Vector<Post>();
			
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT P_ID, creator, content, createDate, modDate FROM T_Post ORDER BY mod_date");
				
				while (rs.next()){
					Post p = new Post();
					p.setId(rs.getInt("P_ID"));
					p.setOwnerId(rs.getInt("creator"));
					p.setContent(rs.getString("content"));
					p.setCreateDate(rs.getTimestamp("createDate"));
					p.setModificationDate(rs.getTimestamp("modDate"));
					result.addElement(p);
				}		
			}catch(SQLException e2){
				e2.printStackTrace();
			}
			return result;
		}

	/**
		 * Gibt alle Posts Objekte zur�ck welche mit P_ID, creator, cintent, create_date und mod_date bef�llt sind und die spezifische U_ID haben.
		 * Hierf�r holen wir P_ID, creator, cintent, create_date und mod_date aus der T_Post Tabelle und speichern diese in einem Post Objekt ab und f�gen diese dem Vector hinzu
		 * Diesen Vector bef�llt mit Posts geben wir zur�ck
		 * 
		 * @return Ein Vector voller Post Objekte welche bef�llt sind und zum �bergebenen User geh�ren
		 * 
		 * @author Egor Kr�mer
		 * @author Robert Mattheis
		 */
		public Vector<Post> findPostsOfUser(User u){
	Connection con = DBConnection.connection();
	Vector<Post> result = new Vector<Post>();
			
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT P_ID, creator, content, createDate, modDate FROM T_Post WHERE creator ="+u.getId()+ " ORDER BY modDate");
				
				while (rs.next()){
					Post p = new Post();
					p.setId(rs.getInt("P_ID"));
					p.setOwnerId(rs.getInt("creator"));
					p.setContent(rs.getString("content"));
					p.setCreateDate(rs.getTimestamp("createDate"));
					p.setModificationDate(rs.getTimestamp("modDate"));
					result.addElement(p);
				}		
			}catch(SQLException e2){
				e2.printStackTrace();
			}
			return result;
		}}
