package de.hdm.itp.client;

import java.util.Vector;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.User;

public interface PinboardPanelAsync {
   void getAllPosts(User u, AsyncCallback<Vector<Post>> callback) throws IllegalArgumentException;
}