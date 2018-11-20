package de.hdm.itp.client;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.bo.Subs;
import de.hdm.itp.shared.bo.User;

public class SubsTreeViewModel implements TreeViewModel {
	private Vector<User> valueResults = null;
	private EditorAdministrationAsync editorAdministration = null;
	private User currentUser = new User(); 
	
	

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		currentUser = ClientsideSettings.getUser();
		editorAdministration.getSubsOfCurrentUser(currentUser, new AsyncCallback<Vector<Subs>>() {			
			public void onFailure(Throwable t) {
				System.out.println(t.getMessage());
			}

			public void onSuccess(Vector<Subs> result) {
				for (Subs s : result) {
				editorAdministration.getUserById(s.getTargetUser(), new AsyncCallback<User>() {			
					public void onFailure(Throwable t) {
						System.out.println(t.getMessage());
					}

					public void onSuccess(User result) {
						//TODO: hier stecke ich den User irgendwie in eine cell	
											
									
					}	
				});}
					
							
			}	
		});
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf(Object value) {
		return (value instanceof User);
	}

}
