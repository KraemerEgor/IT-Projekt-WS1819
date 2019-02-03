package de.hdm.itp.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itp.shared.bo.Comment;
import de.hdm.itp.shared.bo.Like;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.Subs;
import de.hdm.itp.shared.bo.User;

/**
 * Interface der Asyncronen Editor Administration Klasse
 */
/**
 * Enthält die Methodenköpfe, die schon im Interface EditorAdministration definiert werden.
 * Diese haben aber jetzt den Rückgabewert void, damit das hier erstellte callback-Objekt nicht auf die Antwort des Servers warten muss.
 * Ermöglicht den asynchronen Methodenaufruf.
 *
 */
public interface EditorAdministrationAsync {
	
	/**
	 * Inits the.
	 *
	 * @param callback the callback
	 */
	/*
	 * Init Methode
	 */
	void init(AsyncCallback<Void> callback);
	
	/**
	 * Creates the user.
	 *
	 * @param email the email
	 * @param firstname the firstname
	 * @param lastname the lastname
	 * @param nickname the nickname
	 * @param gender the gender
	 * @param callback the callback
	 */
	void createUser(String email, String firstname, String lastname, String nickname, String gender, AsyncCallback<User> callback);

	/**
	 * Gets the user by nickname.
	 *
	 * @param nickname the nickname
	 * @param callback the callback
	 * @return the user by nickname
	 */
	void getUserByNickname(String nickname, AsyncCallback<Vector<User>> callback);
	
	/**
	 * Gets the user by email.
	 *
	 * @param email the email
	 * @param callback the callback
	 * @return the user by email
	 */
	void getUserByEmail(String email, AsyncCallback<User> callback);
	
	/**
	 * Gets the user by id.
	 *
	 * @param userId the user id
	 * @param callback the callback
	 * @return the user by id
	 */
	void getUserById(int userId, AsyncCallback<User> callback);
	
	/**
	 * Gets the all user.
	 *
	 * @param callback the callback
	 * @return the all user
	 */
	void getAllUser(AsyncCallback<Vector<User>> callback);
	
	/**
	 * Creates the post.
	 *
	 * @param content the content
	 * @param currentUsers the current users
	 * @param callback the callback
	 */
	void createPost(String content, User currentUsers, AsyncCallback<Post> callback);
	
	/**
	 * Update post.
	 *
	 * @param p the p
	 * @param content the content
	 * @param callback the callback
	 */
	void updatePost(Post p, String content, AsyncCallback<Post> callback);
	
	/**
	 * Delete post.
	 *
	 * @param p the p
	 * @param callback the callback
	 */
	void deletePost(Post p, AsyncCallback<Void> callback);

	/**
	 * Gets the post by id.
	 *
	 * @param postId the post id
	 * @param callback the callback
	 * @return the post by id
	 */
	void getPostById(int postId, AsyncCallback<Post> callback);
	
	/**
	 * Gets the all posts of user.
	 *
	 * @param u the u
	 * @param callback the callback
	 * @return the all posts of user
	 */
	void getAllPostsOfUser(User u, AsyncCallback<Vector<Post>> callback);
	
	/**
	 * Gets the all posts.
	 *
	 * @param callback the callback
	 * @return the all posts
	 */
	void getAllPosts(AsyncCallback<Vector<Post>> callback);
	
	/**
	 * Creates the like.
	 *
	 * @param p the p
	 * @param owner the owner
	 * @param callback the callback
	 */
	void createLike(Post p, User owner, AsyncCallback<Like> callback);
	
	/**
	 * Delete like.
	 *
	 * @param l the l
	 * @param callback the callback
	 */
	void deleteLike(Like l, AsyncCallback<Void> callback);

	/**
	 * Gets the like by id.
	 *
	 * @param callback the callback
	 * @return the like by id
	 */
	void getLikeById(AsyncCallback<Like> callback);
	
	/**
	 * Gets the all likes of post.
	 *
	 * @param p the p
	 * @param callback the callback
	 * @return the all likes of post
	 */
	void getAllLikesOfPost(Post p, AsyncCallback<Vector<Like>> callback);
	
	/**
	 * Gets the all likes of user.
	 *
	 * @param u the u
	 * @param callback the callback
	 * @return the all likes of user
	 */
	void getAllLikesOfUser(User u, AsyncCallback<Vector<Like>> callback);
	
	/**
	 * Gets the all likes.
	 *
	 * @param callback the callback
	 * @return the all likes
	 */
	void getAllLikes(AsyncCallback<Vector<Like>> callback);
	
	/**
	 * Creates the comment.
	 *
	 * @param postID the post ID
	 * @param text the text
	 * @param currentUser the current user
	 * @param callback the callback
	 */
	void createComment(int postID, String text, User currentUser, AsyncCallback<Comment> callback);
	
	/**
	 * Update comment.
	 *
	 * @param c the c
	 * @param text the text
	 * @param callback the callback
	 */
	void updateComment(Comment c, String text,AsyncCallback<Comment> callback);
	
	/**
	 * Delete comment.
	 *
	 * @param c the c
	 * @param callback the callback
	 */
	void deleteComment(Comment c, AsyncCallback<Void> callback);
	
	/**
	 * Gets the comment by id.
	 *
	 * @param commentId the comment id
	 * @param callback the callback
	 * @return the comment by id
	 */
	void getCommentById(int commentId, AsyncCallback<Comment> callback);
	
	/**
	 * Gets the comments of post.
	 *
	 * @param p the p
	 * @param callback the callback
	 * @return the comments of post
	 */
	void getCommentsOfPost(Post p, AsyncCallback<Vector<Comment>> callback);
	
	/**
	 * Gets the comments of user.
	 *
	 * @param u the u
	 * @param callback the callback
	 * @return the comments of user
	 */
	void getCommentsOfUser(User u, AsyncCallback<Vector<Comment>> callback);
	
	/**
	 * Gets the all comments.
	 *
	 * @param callback the callback
	 * @return the all comments
	 */
	void getAllComments(AsyncCallback<Vector<Comment>> callback);
	
	/**
	 * Creates the subs.
	 *
	 * @param currentUser the current user
	 * @param targetUser the target user
	 * @param callback the callback
	 */
	void createSubs(int currentUser, int targetUser, AsyncCallback<Subs> callback);
	
	/**
	 * Delete subs.
	 *
	 * @param s the s
	 * @param callback the callback
	 */
	void deleteSubs(Subs s, AsyncCallback<Void> callback);
	
	/**
	 * Gets the subs of current user.
	 *
	 * @param u the u
	 * @param callback the callback
	 * @return the subs of current user
	 */
	void getSubsOfCurrentUser(User u, AsyncCallback<Vector<Subs>> callback);
	
	/**
	 * Gets the subs of target user.
	 *
	 * @param u the u
	 * @param callback the callback
	 * @return the subs of target user
	 */
	void getSubsOfTargetUser(User u, AsyncCallback<Vector<Subs>> callback);
	
	/**
	 * Gets the all subs.
	 *
	 * @param callback the callback
	 * @return the all subs
	 */
	void getAllSubs(AsyncCallback<Vector<Subs>> callback);
	
	/**
	 * Checks if is user known.
	 *
	 * @param email the email
	 * @param callback the callback
	 */
	void isUserKnown(String email, AsyncCallback<Boolean> callback);
	
	/**
	 * Gets the full name of user.
	 *
	 * @param user the user
	 * @param callback the callback
	 * @return the full name of user
	 */
	void getFullNameOfUser(User user, AsyncCallback<String> callback);
	
	

	/**
	 * Gets the all comments of post.
	 *
	 * @param p the p
	 * @param callback the callback
	 * @return the all comments of post
	 */
	void getAllCommentsOfPost( Post p, AsyncCallback<Vector<Comment>> callback);

	/**
	 * Gets the all comments of user.
	 *
	 * @param u the u
	 * @param callback the callback
	 * @return the all comments of user
	 */
	void getAllCommentsOfUser(User u, AsyncCallback<Vector<Comment>> callback);

	/**
	 * Gets the post by comment id.
	 *
	 * @param c the c
	 * @param callback the callback
	 * @return the post by comment id
	 */
	void getPostByCommentId(Comment c, AsyncCallback<Post> callback);


}
