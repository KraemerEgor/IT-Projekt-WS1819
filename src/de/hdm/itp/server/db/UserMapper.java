package de.hdm.itp.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.hdm.itp.shared.bo.User;

/**
 * The Class UserMapper.
 */
public class UserMapper {

	/**
	 * Konstruktor für den UserMapper (Singleton)
	 * static weil Singleton. Einzige Instanz dieser Klasse
	 * 
	 * @author Egor Krämer
	 */
	private static UserMapper  usermapper = null;
	
	/**
	 * Falls noch kein UserMapper existiert erstellt er ein neuen UserMapper und gibt ihn zurück.
	 *
	 * @author Egor Krämer
	 * @return erstmalig erstellter UserMapper
	 */
	public static UserMapper userMapper() {
		if (usermapper == null){
			usermapper = new UserMapper();
		}
		
		return usermapper;
		}
	
	/**
	 * Gibt alle User Objekte zurück welche mit U_ID und eMail befüllt sind
	 * Hierfür holen wir U_ID, firstname, lastname, nickname, email, gender und createDate aus der T_User Tabelle und speichern diese in einem User Objekt ab und fügen diese dem Vector hinzu
	 * Diesen Vector befüllt mit User geben wir zurück.
	 *
	 * @author Egor Krämer
	 * @return Ein Vector voller User Objekte welche befüllt sind
	 */
	public Vector<User> findAll(){
		Connection con = DBConnection.connection();
		Vector<User> result = new Vector<User>();
				
				try{
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT U_ID, firstname, lastname, nickname, email, gender, createDate FROM T_User ORDER BY U_ID");
					
					while (rs.next()){
						User u = new User();
						u.setId(rs.getInt("U_ID"));
						u.setFirstname(rs.getString("firstname"));
						u.setLastname(rs.getString("lastname"));
						u.setNickname(rs.getString("nickname"));
						u.setEmail(rs.getString("email"));
						u.setGender(rs.getString("gender"));
						u.setCreateDate(rs.getTimestamp("createDate"));
						result.addElement(u);
					}		
				}catch(SQLException e2){
					e2.printStackTrace();
				}
				return result;
			}
	
	/**
	 * Findet User durch eine U_ID und speichert die dazugehörigen Werte (U_ID, firstname, lastname, nickname, email, gender und createDate) in einem User Objekt ab und gibt dieses wieder.
	 *
	 * @author Egor Krämer
	 * @param user the user
	 * @return Ein vollständiges User Objekt
	 */
	public User findByID(User user){
		Connection con = DBConnection.connection();
		User u = new User();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT U_ID, firstname, lastname, nickname, email, gender, createDate FROM T_User WHERE U_ID ="+ user.getId() + " ORDER BY U_ID");
			
			if (rs.next()){
				
				u.setId(rs.getInt("U_ID"));
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setNickname(rs.getString("nickname"));
				u.setEmail(rs.getString("email"));
				u.setGender(rs.getString("gender"));
				u.setCreateDate(rs.getTimestamp("createDate"));
				
				return u;	
			}
		}
		
		catch (SQLException e){
			e.printStackTrace();
			return u;
		}
		
		return u;
	}
	
	/**
	 * Findet User durch eine EMail und speichert die dazugehörigen Werte (U_ID, firstname, lastname, nickname, email, gender und createDate) in einem User Objekt ab 
	 * und speichert dieses Objekt ab und gibt dieses wieder.
	 *
	 * @author Egor Krämer
	 * @param email übergebener String der eMail
	 * @return Ein vollständiges User Objekt
	 */
	public User findByEMail(String email){
		Connection con = DBConnection.connection();
		User u = new User();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT U_ID, firstname, lastname, nickname, email, gender, createDate FROM T_User WHERE email ='"+ email + "' ORDER BY U_ID");
			if (rs.next()){
				
				u.setId(rs.getInt("U_ID"));
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setNickname(rs.getString("nickname"));
				u.setEmail(rs.getString("email"));
				u.setGender(rs.getString("gender"));
				u.setCreateDate(rs.getTimestamp("createDate"));
	
				return u;	
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		return u;
		}
		
		return u;
	}
	
	/**
	 * Findet User durch einen Nickname und speichert die dazugehörigen Werte (U_ID, firstname, lastname, nickname, email, gender und createDate) in einem User Objekt ab 
	 * und speichert dieses Objekt ab und gibt dieses wieder.
	 *
	 * @author Egor Krämer
	 * @param nickname the nickname
	 * @return Ein vollständiges User Objekt
	 */
	public Vector<User> findByNickname(String nickname){
		Connection con = DBConnection.connection();
		Vector<User> result = new Vector<User>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT U_ID, firstname, lastname, nickname, email, gender, createDate FROM T_User WHERE nickname ='"+ nickname + "' ORDER BY U_ID");
			if (rs.next()){
				User u = new User();
				u.setId(rs.getInt("U_ID"));
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setNickname(rs.getString("nickname"));
				u.setEmail(rs.getString("email"));
				u.setGender(rs.getString("gender"));
				u.setCreateDate(rs.getTimestamp("createDate"));
				result.addElement(u);
				
			}
			return result;	
		}
		catch (SQLException e){
			e.printStackTrace();
		return result;
		}
		
	}
	
	/**
	 * Sucht nach der höchsten U_ID um diese um eins zu erhöhen und als neue U_ID zu nutzen
	 * Befüllt T_User mit U_ID, firstname, lastname, nickname, email, gender und createDate
	 * Ein User wird zurückgegeben.
	 *
	 * @author Egor Krämer
	 * @param user übergebenes User Objekt mit allen Attributen
	 * @return Ein vollständiges User Objekt
	 */
	public User insert(User user){
		Connection con = DBConnection.connection();
			Timestamp ts = new Timestamp(System.currentTimeMillis());	
		String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts);
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(U_ID) AS maxuid FROM T_User");
			if (rs.next()){
				
				user.setId(rs.getInt("maxuid")+1);
				Statement stmt2 = con.createStatement();
				stmt2.executeUpdate("INSERT INTO T_User (U_ID, firstname, lastname, nickname, email, gender, createDate)"
				+ " VALUES ("
				+ user.getId() 
				+ ", '" 
				+ user.getFirstname() 
				+ "', '" 
				+ user.getLastname() 
				+ "', '" 
				+ user.getNickname() 
				+ "', '" 
				+ user.getEmail()
				+ "', '" 
				+ user.getGender() 
				+ "', '" 
				+ s
				+ "')") ;
						
				return user;	
				
			}
		}
		catch (SQLException e2){
			e2.printStackTrace();
			return user;
		}
		return user;}
	
	
	
	/**
	 * Entfernt alles aus T_User wo die U_ID der ID des übergebenen Objekts entspricht.
	 *
	 * @author Egor Krämer
	 * @param user übergebenes User Objekt mit Attribut U_ID
	 */
	public void delete (User user){
		Connection con = DBConnection.connection();
					
					try{
						
						Statement stmt = con.createStatement();
						stmt.executeUpdate("DELETE FROM T_User WHERE U_ID =" + user.getId());
					}
				
				catch (SQLException e2){
					e2.printStackTrace();
					}
				}

	
}


