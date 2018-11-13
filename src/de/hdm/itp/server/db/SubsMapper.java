package de.hdm.itp.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.hdm.itp.shared.bo.Like;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.Subs;
import de.hdm.itp.shared.bo.User;

public class SubsMapper {
	/**
	* Konstruktor f�r den SubsMapper (Singleton) 
	* static weil Singleton. Einzige Instanz dieser Klasse
	* 
	* @author Egor Kr�mer
	*/
	private static SubsMapper  subsmapper = null;
	
	/**
	 * Falls noch kein PostMapper existiert wird ein neuen PostMapper erstellt und gibt ihn zur�ck
	 * 
	 * @return erstmalig erstellter PostMapper
	 * 
	 * @author Egor Kr�mer
	 */
	public static SubsMapper subsMapper() {
		if (subsmapper == null){
			subsmapper = new SubsMapper();
		}
		return subsmapper;
		}
	/**
	 * Gibt alle Subs Objekte zur�ck welche mit currentUser, targetUser, createDate bef�llt sind
	 * Hierf�r holen wir currentUser, targetUser, createDate aus der T_Subs Tabelle und speichern diese in einem Subs Objekt ab und f�gen diese dem Vector hinzu
	 * Diesen Vector bef�llt mit Subs geben wir zur�ck
	 * 
	 * @return Ein Vector voller Subs Objekte welche bef�llt sind
	 * 
	 * @author Egor Kr�mer
	 */
	public Vector<Subs> findAll(){
Connection con = DBConnection.connection();
Vector<Subs> result = new Vector<Subs>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT currentUser, targetUser, createDate FROM T_Subs ORDER BY createDate");
			
			while (rs.next()){
				Subs s = new Subs();
				//TODO: check if this works
				s.setId(rs.getInt("currentUser")*100000000+rs.getInt("targetUser"));
				s.setCurrentUser(rs.getInt("currentUser"));
				s.setTargetUser(rs.getInt("targetUser"));
				s.setCreateDate(rs.getTimestamp("createDate"));
				result.addElement(s);
			}		
		}catch(SQLException e2){
			e2.printStackTrace();
		}
		return result;
	}
	/**
	 * Gibt alle Subs Objekte zur�ck welche mit currentUser, targetUser, createDate bef�llt sind von dem aktuellen Benutzer
	 * Hierf�r holen wir currentUser, targetUser, createDate  aus der T_Subs Tabelle, die dem User mit der id zugeteilt sind, und speichern diese in einem Subs Objekt ab und f�gen diese dem Vector hinzu
	 * Diesen Vector bef�llt mit Subs geben wir zur�ck
	 * 
	 * @return Ein Vector voller Subs Objekte welche bef�llt sind
	 * 
	 * @author Egor Krämer
	 */
	public Vector<Subs> findAllByCurrentUserId(User user){
Connection con = DBConnection.connection();
Vector<Subs> result = new Vector<Subs>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT currentUser, targetUser, createDate FROM T_Subs WHERE currentUser =" +user.getId()+" ORDER BY createDate");
			
			while (rs.next()){
				Subs s = new Subs();
				//TODO: check if this works
				s.setId(rs.getInt("currentUser")*100000000+rs.getInt("targetUser"));
				s.setCurrentUser(rs.getInt("currentUser"));
				s.setTargetUser(rs.getInt("targetUser"));
				s.setCreateDate(rs.getTimestamp("createDate"));
				result.addElement(s);
			}		
		}catch(SQLException e2){
			e2.printStackTrace();
		}
		return result;
	}
	/**
	 * Gibt alle Subs Objekte zur�ck welche mit currentUser, targetUser, createDate bef�llt sind von dem zu abonierenden Benutzer
	 * Hierf�r holen wir currentUser, targetUser, createDate  aus der T_Subs Tabelle, die dem User mit der id zugeteilt sind, und speichern diese in einem Subs Objekt ab und f�gen diese dem Vector hinzu
	 * Diesen Vector bef�llt mit Subs geben wir zur�ck
	 * 
	 * @return Ein Vector voller Subs Objekte welche bef�llt sind
	 * 
	 * @author Egor Krämer
	 */
	public Vector<Subs> findAllByTargetUserId(User user){
Connection con = DBConnection.connection();
Vector<Subs> result = new Vector<Subs>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT currentUser, targetUser, createDate FROM T_Subs WHERE targetUser =" +user.getId()+" ORDER BY createDate");
			
			while (rs.next()){
				Subs s = new Subs();
				//TODO: check if this works
				s.setId(rs.getInt("currentUser")*100000000+rs.getInt("targetUser"));
				s.setCurrentUser(rs.getInt("currentUser"));
				s.setTargetUser(rs.getInt("targetUser"));
				s.setCreateDate(rs.getTimestamp("createDate"));
				result.addElement(s);
			}		
		}catch(SQLException e2){
			e2.printStackTrace();
		}
		return result;
	}
	/**
	  * Befüllt T_Subs mit currentUser, targetUser und createDate
	 * Ein Subs Objekt wird zurückgegeben
	 *
	 * @param subs übergebenes Subs Objekt mit allen Attributen
	 * @return Ein vollständiges Subs Objekt
	 * 
	 * @author Egor Krämer
	 */
	public Subs insert(Subs subs){
		Connection con = DBConnection.connection();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
		String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts);
		
		try{
			
				Statement stmt2 = con.createStatement();
				stmt2.executeUpdate("INSERT INTO T_Subs (currentUser, targetUser, createDate)"
				+ " VALUES ("
				+ subs.getCurrentUser()
				+ ", " 
				+ subs.getTargetUser()
				+ ", '" 
				+ s	
				+ "')") ;
						
				return subs;	
				
		}
		catch (SQLException e2){
			e2.printStackTrace();
			return subs;
		}}
	/**
	 * Entfernt alles aus T_Subs wo die ID(currentUser und targetUser) der ID des übergebenen Objekts entspricht
	 * 
	 * @param subs übergebenes Subs Objekt mit Attribut ID(currentUser und targetUser)
	 * 
	 * @author Egor Krämer
	 */
	public void delete (Subs subs){
		Connection con = DBConnection.connection();
					
					try{
						
						Statement stmt = con.createStatement();
						stmt.executeUpdate("DELETE FROM T_Subs WHERE currentUser =" + subs.getCurrentUser()+ " AND targetUser="+subs.getTargetUser());
					}
				
				catch (SQLException e2){
					e2.printStackTrace();
					}
				}
}
