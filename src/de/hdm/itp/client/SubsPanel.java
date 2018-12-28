package de.hdm.itp.client;

import java.util.Vector;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
	
	SubscCell subscCell = new SubscCell();
	CellList<User> cellList = new CellList<User>(subscCell);
	SingleSelectionModel<User> selectionModel = new SingleSelectionModel<User>();
	ListDataProvider<User> dataProvider = new ListDataProvider<User>();
	
	Vector<User> cellSubs = new Vector<User>();	
	User selectedUser = new User();
	User currentUser = new User();
	
	public void onLoad() {
		super.onLoad();	
		//erstellen einer Insatnz des Asyncronen Interfaces, des Servlets
		if(editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
	    }
		//dummy für currentUser
				currentUser.setId(10000001);
				currentUser.setFirstname("egor");
				currentUser.setLastname("krämer");
				currentUser.setNickname("kulak");
				currentUser.setGender("m");
				
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
										cellList.setRowCount(cellSubs.size(), true);
									    cellList.setRowData(0, cellSubs);
							      }
									
							    });
							   
								}
							
						    
						    
						   vp.add(cellList);
							
						}
					});		
				 cellList.addStyleDependentName("cellList");
					//final ListDataProvider<User> dataProvider = new ListDataProvider<User>();
				 dataProvider.setList(cellSubs);	
				 dataProvider.addDataDisplay(cellList);
			    cellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
				    //final SingleSelectionModel<User> selectionModel = new SingleSelectionModel<User>();
				    cellList.setSelectionModel(selectionModel);
				//dem Selection Model einen Handler geben
				selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
				      public void onSelectionChange(SelectionChangeEvent event) {
				    	  User selected = selectionModel.getSelectedObject();
				    	  selectedUser = selectionModel.getSelectedObject();
				    	  
				        if (selected != null) {
				        	Window.alert("die ID des Selected Users: "+selectedUser.getId());
				          Window.alert("Show Pinboard of: " + selected);
				          //PinboardPanel.createPost(selected);
				        }
					
				}	
			});
			//dem Panel die ergebnisse hinzufügen	
				this.add(vp);
	}
	public void addSub(User u) {
		//dummy current user
		User currentUser = new User();
		currentUser.setId(10000001);
	
		 if(editorAdministration == null) {
				editorAdministration = ClientsideSettings.getAdministration();
		    }
		 
		editorAdministration.createSubs(currentUser.getId(),u.getId(), new AsyncCallback<Subs>() {


			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());						
			}

		

			
			public void onSuccess(Subs result) {
				editorAdministration.getUserById(result.getTargetUser(), new AsyncCallback<User>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
						
					}

					@Override
					public void onSuccess(User result) {
						Window.alert("der User: "+result.getNickname()+" wurde hinzugefügt");
						cellSubs.add(result);
					    dataProvider.refresh();						
						
						//hier wird aber noch nciht automatisch in die Liste geschrieben
						
					}
					
				});
				
			}
			
		});		
		
	
	}
	public void removeSub() {
		User currentUser = new User();
		currentUser.setId(10000001);
	
		 if(editorAdministration == null) {
				editorAdministration = ClientsideSettings.getAdministration();
		    }
		 Subs subs = new Subs();
		 subs.setCurrentUser(currentUser.getId());
		subs.setTargetUser(selectedUser.getId());
		// Window.alert("id von target(Model):"+selectionModel.getSelectedObject().getId());
		// Window.alert("id vom target(Variable): "+ selectedUser.getId());
		//subs.setTargetUser(selectionModel.getSelectedObject().getId());
		 Window.alert("gelöscht: "+subs.getTargetUser()+" from: "+subs.getCurrentUser());
		 editorAdministration.deleteSubs(subs, new AsyncCallback<Void>() {

			
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				
			}

			
			public void onSuccess(Void result) {
				//Window.alert("der User: "+u.getFirstname()+" wurde aus der Subs Liste von "+ currentUser.getFirstname()+" gelöscht");
				//Window.alert("gelöscht: "+subs.getTargetUser()+" from: "+subs.getCurrentUser());
				Window.alert("tut");
			}
			 
		 });
		
	}
}
