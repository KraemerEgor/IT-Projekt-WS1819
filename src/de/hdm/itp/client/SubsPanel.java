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
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.Subs;
import de.hdm.itp.shared.bo.User;

public class SubsPanel extends VerticalPanel {
	Label header = new Label();
	Label header_user = new Label();
	
	private EditorAdministrationAsync editorAdministration = null;
	VerticalPanel vp = new VerticalPanel();	
	
	SubscCell subscCell = new SubscCell();
	CellList<User> cellList = new CellList<User>(subscCell);
	SingleSelectionModel<User> selectionModel = new SingleSelectionModel<User>();
	ListDataProvider<User> dataProvider = new ListDataProvider<User>();
	
	Vector<User> cellSubs = new Vector<User>();	
	User selectedUser = new User();
	User currentUser = new User();
	
	private Button profileBtn = new Button("Mein Profil");
	private Button addBtn = new Button("Hinzufügen");
	private Button dltBtn = new Button("Löschen");
	
	private VerticalPanel btnPanel = new VerticalPanel();
	
	public Vector<User> box = new Vector<User>();
	MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	SuggestBox suggestbox = new SuggestBox(oracle);
	
	Label lbl = new Label();
	PinboardPanel pp = new PinboardPanel();
	
	
	public void onLoad() {
		super.onLoad();	
		//erstellen einer Insatnz des Asyncronen Interfaces, des Servlets
		if(editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
	    }
				currentUser = ClientsideSettings.getUser();
				 editorAdministration.getSubsOfCurrentUser(currentUser, new AsyncCallback<Vector<Subs>>() {
					 public void onFailure(Throwable t) {
							Window.alert(t.getMessage());}	
					 public void onSuccess(Vector<Subs> result) {
						 if(result.isEmpty()) {
								Window.alert("du hast keine Subs, "+currentUser.getFirstname());
								buildList();
								
							}else {
								buildList();
							}
					 }	
				 });
				 header.setText("Navigationsbereich von: ");
				 header_user.setText(currentUser.getFirstname()+" "+currentUser.getLastname());
				 
				 header.setStylePrimaryName("Header");
				 header_user.setStylePrimaryName("Header_user");
				 btnPanel.add(header);
				 btnPanel.add(header_user);
				 //Buttons mit ClickHandlern ausstatten
				 profileBtn.addClickHandler(new MyProfileClickHandler());
				 addBtn.addClickHandler(new AddClickHandler());
				 dltBtn.addClickHandler(new DeleteClickHandler());
				 
				 //Buttons dem css zuordnen
				    profileBtn.setStylePrimaryName("submit");
					btnPanel.add(profileBtn);
					
					
					addBtn.setStylePrimaryName("submit");
					btnPanel.add(addBtn);
					
					dltBtn.setStylePrimaryName("submit");
					btnPanel.add(dltBtn);
					
					btnPanel.setStylePrimaryName("btnPanel");
					
					
					editorAdministration.getAllUser(new AsyncCallback<Vector<User>>() {
						public void onFailure(Throwable t) {
							
							Window.alert(t.getMessage());}		
						
						public void onSuccess(Vector<User> result) {
							
							for(User u: result) {
								oracle.add(u.getFirstname()+" ' "+u.getNickname()+" ' "+u.getLastname()+" - "+u.getEmail());
								}
							suggestbox.setStylePrimaryName("suggestbox");
							btnPanel.add(suggestbox);}
						
					});		
			
			//dem Panel die ergebnisse hinzufügen	
					this.add(btnPanel);
					this.add(vp);
	}
	private void buildList() {
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
		        	//Window.alert("die ID des Selected Users: "+selectedUser.getId());
		          //Window.alert("Show Pinboard of: " + selected);
		          //pp.createPinboard(selected);
		        	
		        	if(editorAdministration == null) {
		    			editorAdministration = ClientsideSettings.getAdministration();
		    		}		
		    		
		    		editorAdministration.getAllPostsOfUser(selectedUser, new AsyncCallback<Vector<Post>>() {
		    			public void onFailure(Throwable t) {
		    				Window.alert(t.getMessage());}		
		    			public void onSuccess(Vector<Post> result) {
		    					for(Post p: result) {
		    						//dies ist nur zum Testen drin
		    						lbl.setText("Post von "+ selectedUser.getFirstname()+": \n"+p.getContent());
		    						//post.add(postpanel.createPost(p));
		    						
		    					
		    					}}
		    		});
		    		
		        }
			
		}	
	});
		this.add(lbl);
		
	}
	public void addSub(User u) {
		
		currentUser = ClientsideSettings.getUser();
	
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
						cellSubs.add(result);
					    dataProvider.refresh();						
						
					}
					
				});
				
			}
			
		});		
		
	
	}
	private class DeleteClickHandler implements ClickHandler{
		public void onClick(ClickEvent event) {
			User currentUser = new User();
			currentUser = ClientsideSettings.getUser();
		
			 if(editorAdministration == null) {
					editorAdministration = ClientsideSettings.getAdministration();
			    }
			 Subs subs = new Subs();
			 subs.setCurrentUser(currentUser.getId());
			 subs.setTargetUser(selectionModel.getSelectedObject().getId());
			 cellSubs.remove(selectionModel.getSelectedObject());
			 editorAdministration.deleteSubs(subs, new AsyncCallback<Void>() {

				
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
					
				}

				
				public void onSuccess(Void result) {	
					cellList.setRowCount(cellSubs.size(), true);
				    cellList.setRowData(0, cellSubs);
				    dataProvider.refresh();
				}
				 
			 });
			
		}
	}
	private class AddClickHandler implements ClickHandler{
		public void onClick(ClickEvent event) {
			if(!suggestbox.getValue().isEmpty()) {
			String inhalt = suggestbox.getValue();
			String[] split = inhalt.split("- ");						
			editorAdministration.getUserByEmail(split[1], new AsyncCallback<User>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());						
				}

				@Override
				public void onSuccess(User result) {
					if(result == null) {
						Window.alert("ungültiger User");
					}if(result != null) {
						boolean toAdd = true;
						if (result.getId()==currentUser.getId()) {
							Window.alert("Sie können sich nicht selbst folgen");
							toAdd = false;
						}
						for(User u: cellSubs) {
							if(result.getId()==u.getId()) {
								Window.alert("Der Nutzer "+result.getFirstname()+" "+ result.getLastname()+ " befindet sich bereits unter den Abonenten");
								toAdd = false;
							}
							
							
						}
						if(toAdd) {
					addSub(result);}
					}
					suggestbox.setText(" ");
				}
				
			});
			
			
			
			}if(suggestbox.getValue().isEmpty()){
			Window.alert("Bitte wähle ein Nutzer zum Abonieren aus");	
			}
			}
	}
	private class MyProfileClickHandler implements ClickHandler{
		public void onClick(ClickEvent event) {		
			//hier soll die eigene Pinnwand angezeigt werden
			Window.alert("Show own Pinboard of "+currentUser.getFirstname()+" "+currentUser.getLastname());
			pp.createPinboard(currentUser);			
			
			}
	}
}
