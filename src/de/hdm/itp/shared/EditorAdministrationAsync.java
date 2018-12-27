package de.hdm.itp.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itp.shared.bo.Comment;
import de.hdm.itp.shared.bo.Like;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.Subs;
import de.hdm.itp.shared.bo.User;

public interface EditorAdministrationAsync {
	
	void init(AsyncCallback<Void> callback);
	
	void setUser(User u, AsyncCallback<Void> callback);
	
	void createUser(String email, String firstname, String lastname, String nickname, String gender, AsyncCallback<User> callback);

	void getUserByNickname(String nickname, AsyncCallback<Vector<User>> callback);
	
	void getUserByEmail(String email, AsyncCallback<User> callback);
	
	void getUserById(int userId, AsyncCallback<User> callback);
	
	void getAllUser(AsyncCallback<Vector<User>> callback);
	
	void createPost(String content, AsyncCallback<Post> callback);
	
	void updatePost(Post p, String content, AsyncCallback<Post> callback);
	
	void deletePost(Post p, AsyncCallback<Void> callback);

	void getPostById(int postId, AsyncCallback<Post> callback);
	
	void getAllPostsOfUser(User u, AsyncCallback<Vector<Post>> callback);
	
	void getAllPosts(AsyncCallback<Vector<Post>> callback);
	
	void createLike(Post p, AsyncCallback<Like> callback);
	
	void deleteLike(Like l, AsyncCallback<Void> callback);

	void getLikeById(AsyncCallback<Like> callback);
	
	void getAllLikesOfPost(Post p, AsyncCallback<Vector<Like>> callback);
	
	void getAllLikesOfUser(User u, AsyncCallback<Vector<Like>> callback);
	
	void getAllLikes(AsyncCallback<Vector<Like>> callback);
	
	void createComment(Post p, String text, AsyncCallback<Comment> callback);
	
	void deleteComment(Comment c, AsyncCallback<Void> callback);
	
	void getCommentById(int commentId, AsyncCallback<Comment> callback);
	
	void getCommentsOfPost(Post p, AsyncCallback<Vector<Comment>> callback);
	
	void getCommentsOfUser(User u, AsyncCallback<Vector<Comment>> callback);
	
	void getAllComments(AsyncCallback<Vector<Comment>> callback);
	
	void createSubs(int currentUser, int targetUser, AsyncCallback<Subs> callback);
	
	void deleteSubs(Subs s, AsyncCallback<Void> callback);
	
	void getSubsOfCurrentUser(User u, AsyncCallback<Vector<Subs>> callback);
	
	void getSubsOfTargetUser(User u, AsyncCallback<Vector<Subs>> callback);
	
	void getAllSubs(AsyncCallback<Vector<Subs>> callback);
	
	void isUserKnown(String email, AsyncCallback<Boolean> callback);
	
	void getFullNameOfUser(User user, AsyncCallback<String> callback);

}
