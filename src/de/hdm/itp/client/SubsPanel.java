package de.hdm.itp.client;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;


import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.bo.Subs;
import de.hdm.itp.shared.bo.User;

public class SubsPanel extends VerticalPanel {
	private EditorAdministrationAsync editorAdministration = null;
	VerticalPanel vp = new VerticalPanel();
	
	User currentUser = new User();
	Vector<User> cellSubs = new Vector<User>();
		
	SubscCell subscCell = new SubscCell();
	CellList<User> cellList = new CellList<User>(subscCell);	
	
	
	public void onLoad() {
		super.onLoad();
		
		//dummy für currentUser
		currentUser.setId(10000001);
		currentUser.setFirstname("egor");
		currentUser.setLastname("krämer");
		currentUser.setNickname("kulak");
		currentUser.setGender("m");
		  
	    if(editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
	    }
	    editorAdministration.getSubsOfCurrentUser(currentUser, new AsyncCallback<Vector<Subs>>() {
			public void onFailure(Throwable t) {
				Window.alert(t.getMessage());}		
			
			public void onSuccess(Vector<Subs> result) {
				for(Subs s: result) {
					editorAdministration.getUserById(s.getTargetUser(),new AsyncCallback<User>() {
						public void onFailure(Throwable t) {
							Window.alert(t.getMessage());}

						@Override
						public void onSuccess(User result) {
							cellSubs.add(result);
							cellList.addStyleDependentName("cellList");
							cellList.addStyleName("cellList");
							
							final ListDataProvider<User> dataProvider = new ListDataProvider<User>();
							dataProvider.addDataDisplay(cellList);
						    cellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
						    final SingleSelectionModel<User> selectionModel = new SingleSelectionModel<User>();
						    cellList.setSelectionModel(selectionModel);
						    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						      public void onSelectionChange(SelectionChangeEvent event) {
						    	  User selected = selectionModel.getSelectedObject();
						        if (selected != null) {
						          Window.alert("Hier sollte der Aufruf der Main passieren für die Posts von: " + selected);
						          //PinboardPanel.createPost(selected);
						        }
							
						}	
					});
						    cellList.setRowCount(cellSubs.size(), true);
						    cellList.setRowData(0, cellSubs);
					
						   vp.add(cellList);
					
				      }
				    });
				   
					}
				
			}
		});

	    this.add(vp);
			    
	}

	public void addSub(User u) {
		//dummy current user
		User currentUser = new User();
		currentUser.setId(10000001);
		
		Window.alert("kommt in add");
		Window.alert("ID vom currentUser: "+currentUser.getId());
		Window.alert("ID vom targetUser: "+u.getId());
		 if(editorAdministration == null) {
				editorAdministration = ClientsideSettings.getAdministration();
		    }
		 
		editorAdministration.createSubs(currentUser.getId(),u.getId(), new AsyncCallback<Subs>() {


			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());						
			}

		

			
			public void onSuccess(Subs result) {
				Window.alert("CurrentUser: "+result.getCurrentUser());
				editorAdministration.getUserById(result.getTargetUser(), new AsyncCallback<User>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
						
					}

					@Override
					public void onSuccess(User result) {
						Window.alert("der User heißt: "+result.getNickname());
						//hier wird aber noch nciht automatisch in die Liste geschrieben
						
					}
					
				});
				
			}
			
		});		
		
	
	}
	
	
}
