package de.hdm.itp.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.hdm.itp.shared.bo.Comment;
import de.hdm.itp.shared.bo.Like;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.User;

/**
 * The Class LikeMapper.
 */
public class LikeMapper {
	/**
	 * Konstruktor für den LikeMapper (Singleton) static weil Singleton. Einzige
	 * Instanz dieser Klasse
	 * 
	 * @author Egor Kr�mer
	 */
	private static LikeMapper likemapper = null;

	/**
	 * Falls noch kein LikeMapper existiert wird ein neuen LikeMapper erstellt und
	 * gibt ihn zur�ck.
	 *
	 * @author Egor Kr�mer
	 * @return erstmalig erstellter LikeMapper
	 */
	public static LikeMapper likeMapper() {
		if (likemapper == null) {
			likemapper = new LikeMapper();
		}
		return likemapper;
	}

	/**
	 * Gibt alle Like Objekte zur�ck welche mit post, currentUser, createDate
	 * bef�llt sind von einem spezifischen Post Hierf�r holen wir post, currentUser,
	 * createDate aus der T_Like Tabelle, die dem Post mit der id zugeteilt sind,
	 * und speichern diese in einem Like Objekt ab und f�gen diese dem Vector hinzu
	 * Diesen Vector bef�llt mit Likes geben wir zur�ck.
	 *
	 * @author Egor Krämer
	 * @param post the post
	 * @return Ein Vector voller Like Objekte welche bef�llt sind
	 */
	public Vector<Like> findAllByPID(Post post) {
		Connection con = DBConnection.connection();
		Vector<Like> result = new Vector<Like>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT post, currentUser, createDate FROM T_Like WHERE post ="
					+ post.getId() + " ORDER BY createDate");

			while (rs.next()) {
				Like l = new Like();
				l.setOwnerId(rs.getInt("currentUser"));
				l.setPostId(rs.getInt("post"));
				l.setCreateDate(rs.getTimestamp("createDate"));
				result.addElement(l);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Gibt alle Like Objekte zur�ck welche mit post, currentUser, createDate
	 * bef�llt sind von einem spezifischen User Hierf�r holen wir post, currentUser,
	 * createDate aus der T_Like Tabelle, die dem User mit der id zugeteilt sind,
	 * und speichern diese in einem Like Objekt ab und f�gen diese dem Vector hinzu
	 * Diesen Vector bef�llt mit Likes geben wir zur�ck.
	 *
	 * @author Egor Krämer
	 * @param user the user
	 * @return Ein Vector voller Like Objekte welche bef�llt sind
	 */
	public Vector<Like> findAllByUID(User user) {
		Connection con = DBConnection.connection();
		Vector<Like> result = new Vector<Like>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT post, currentUser, createDate FROM T_Like WHERE currentUser ="
					+ user.getId() + " ORDER BY createDate");

			while (rs.next()) {
				Like l = new Like();
				l.setOwnerId(rs.getInt("currentUser"));
				l.setPostId(rs.getInt("post"));
				l.setCreateDate(rs.getTimestamp("createDate"));
				result.addElement(l);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Gibt alle Like Objekte zur�ck welche mit post, currentUser, createDate
	 * bef�llt sind Hierf�r holen wir post, currentUser, createDate aus der T_Like
	 * Tabelle und speichern diese in einem Like Objekt ab und f�gen diese dem
	 * Vector hinzu Diesen Vector bef�llt mit Like geben wir zur�ck.
	 *
	 * @author Egor Kr�mer
	 * @return Ein Vector voller Like Objekte welche bef�llt sind
	 */
	public Vector<Like> findAll() {
		Connection con = DBConnection.connection();
		Vector<Like> result = new Vector<Like>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT post, currentUser, createDate FROM T_Like ORDER BY createDate");

			while (rs.next()) {
				Like l = new Like();		
				l.setOwnerId(rs.getInt("currentUser"));
				l.setPostId(rs.getInt("post"));
				l.setCreateDate(rs.getTimestamp("createDate"));
				result.addElement(l);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Befüllt T_Like mit post, currentUser und createDate Ein Like Objekt wird
	 * zurückgegeben.
	 *
	 * @author Egor Krämer
	 * @param like übergebenes Like Objekt mit allen Attributen
	 * @return Ein vollständiges Like Objekt
	 */
	public Like insert(Like like) {
		Connection con = DBConnection.connection();
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts);

		try {

			Statement stmt2 = con.createStatement();
			stmt2.executeUpdate("INSERT INTO T_Like (post, currentUser, createDate)" + " VALUES (" + like.getPostId()
					+ ", " + like.getOwnerId() + ", '" + s + "')");

			return like;

		} catch (SQLException e2) {
			e2.printStackTrace();
			return like;
		}
	}

	/**
	 * Entfernt alles aus T_Like wo die ID(post und currentUser) der ID des
	 * übergebenen Objekts entspricht.
	 *
	 * @author Egor Krämer
	 * @param like übergebenes Like Objekt mit Attribut ID(post und currentUser)
	 */
	public void delete(Like like) {
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			stmt.executeUpdate(
					"DELETE FROM T_Like WHERE currentUser =" + like.getOwnerId() + " AND post=" + like.getPostId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Entfernt alles aus T_Like wo die ID(post und currentUser) der ID des
	 * übergebenen Objekts entspricht.
	 *
	 * @author Egor Krämer
	 * @param post the post
	 */
	public void deleteAllByPID(Post post) {
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM T_Like WHERE post =" + post.getId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
}
