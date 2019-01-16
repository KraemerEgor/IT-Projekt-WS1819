package de.hdm.itp.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itp.shared.bo.Comment;
import de.hdm.itp.shared.bo.Like;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.Subs;
import de.hdm.itp.shared.bo.User;

/*
 * Interface der Asyncronen Editor Administration Klasse
 */
public interface EditorAdministrationAsync {
	
	/*
	 * Init Methode
	 */
	void init(AsyncCallback<Void> callback);
	
	/*
	 * Erstellung eines Users
	 */
	void createUser(String email, String firstname, String lastname, String nickname, String gender, AsyncCallback<User> callback);

	/*
	 * Abfrage eines Users basierend auf seinem Nickname
	 */
	void getUserByNickname(String nickname, AsyncCallback<Vector<User>> callback);
	
	/*
	 * Abfrage eines Users basierend auf seiner Email Adresse
	 */
	void getUserByEmail(String email, AsyncCallback<User> callback);
	
	/*
	 * Abfrage eines Users basierend auf seiner ID
	 */
	void getUserById(int userId, AsyncCallback<User> callback);
	
	/*
	 * Abfrage aller User
	 */
	void getAllUser(AsyncCallback<Vector<User>> callback);
	
	/*
	 * Erstellung eines Posts
	 */
	void createPost(String content, User currentUsers, AsyncCallback<Post> callback);
	
	/*
	 * Bearbeiten eines Posts
	 */
	void updatePost(Post p, String content, AsyncCallback<Post> callback);
	
	/*
	 * Löschen eines Posts
	 */
	void deletePost(Post p, AsyncCallback<Void> callback);

	/*
	 * Abfrage eines Postes basierend auf seiner ID
	 */
	void getPostById(int postId, AsyncCallback<Post> callback);
	
	/*
	 * Abfrage aller Posts eines Users
	 */
	void getAllPostsOfUser(User u, AsyncCallback<Vector<Post>> callback);
	
	/*
	 * Abfrage aller erstellten Posts
	 */
	void getAllPosts(AsyncCallback<Vector<Post>> callback);
	
	/*
	 * Setzen eines Likes
	 */
	void createLike(Post p, User owner, AsyncCallback<Like> callback);
	
	/*
	 * Löschen eines gesetzten Likes
	 */
	void deleteLike(Like l, AsyncCallback<Void> callback);

	/*
	 * Abfrage eines gestzten Likes basierend auf der ID
	 */
	void getLikeById(AsyncCallback<Like> callback);
	
	/*
	 * Abfrage der Anzahl aller Likes welche auf einen Post gestzt worden sind
	 */
	void getAllLikesOfPost(Post p, AsyncCallback<Vector<Like>> callback);
	
	/*
	 * Abfrage aller Likes eines Users
	 */
	void getAllLikesOfUser(User u, AsyncCallback<Vector<Like>> callback);
	
	/*
	 * Abfrage aller Likes auf allen Posts
	 */
	void getAllLikes(AsyncCallback<Vector<Like>> callback);
	
	/*
	 * Erstellen eines Kommentars
	 */
	void createComment(int postID, String text, User currentUser, AsyncCallback<Comment> callback);
	
	/*
	 * Bearbeiten eines Kommentars
	 */
	void updateComment(Comment c, String text,AsyncCallback<Comment> callback);
	
	/*
	 * Löschen eines Kommenats
	 */
	void deleteComment(Comment c, AsyncCallback<Void> callback);
	
	/*
	 * Abfrage eines Kommentars basierend auf seiner ID
	 */
	void getCommentById(int commentId, AsyncCallback<Comment> callback);
	
	/*
	 * Abfrage von Kommentaren eines spezifischen Posts
	 */
	void getCommentsOfPost(Post p, AsyncCallback<Vector<Comment>> callback);
	
	/*
	 * Abfrage von Kommentaren eines Users
	 */
	void getCommentsOfUser(User u, AsyncCallback<Vector<Comment>> callback);
	
	/*
	 * Abfrage aller Kommentare
	 */
	void getAllComments(AsyncCallback<Vector<Comment>> callback);
	
	/*
	 * Erstellen einer Abonnenten Beziehung
	 */
	void createSubs(int currentUser, int targetUser, AsyncCallback<Subs> callback);
	
	/*
	 * Löschen einer Abonnenten Beziehung
	 */
	void deleteSubs(Subs s, AsyncCallback<Void> callback);
	
	/*
	 * Abfrage aller Abonnenten Beziehungen des aktuellen Users
	 */
	void getSubsOfCurrentUser(User u, AsyncCallback<Vector<Subs>> callback);
	
	/*
	 * Abfrage aller Abonennten Beziehungen eines spezifischen Users
	 */
	void getSubsOfTargetUser(User u, AsyncCallback<Vector<Subs>> callback);
	
	/*
	 * Abfrage aller gestzten Abonnenten Beziehungen
	 */
	void getAllSubs(AsyncCallback<Vector<Subs>> callback);
	
	/*
	 * Abfrage ob der User bereits vorhanden ist
	 */
	void isUserKnown(String email, AsyncCallback<Boolean> callback);
	
	/*
	 * Abfrage des vollen Namens eines Users
	 */
	void getFullNameOfUser(User user, AsyncCallback<String> callback);

}
