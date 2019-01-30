package de.hdm.itp.client;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.bo.Subs;
import de.hdm.itp.shared.bo.User;

/**
 * The Class SubsTreeViewModel.
 */
public class SubsTreeViewModel implements TreeViewModel {
	
	/** The value results. */
	private Vector<User> valueResults = null;
	
	/** The editor administration. */
	private EditorAdministrationAsync editorAdministration = null;
	
	/** The current user. */
	private User currentUser = new User(); 
	
	

	/* (non-Javadoc)
	 * @see com.google.gwt.view.client.TreeViewModel#getNodeInfo(java.lang.Object)
	 */
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
						valueResults.add(result);										
									
					}	
				});}
					
							
			}	
		});
		// TODO Node wiedergeben
		return null;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.view.client.TreeViewModel#isLeaf(java.lang.Object)
	 */
	@Override
	public boolean isLeaf(Object value) {
		return (value instanceof User);
	}

}
