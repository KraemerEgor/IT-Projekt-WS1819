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

/**
 * The Class SubsPanel.
 */
public class SubsPanel extends VerticalPanel {
	
	/** The header. */
	Label header = new Label();
	
	/** The header user. */
	Label header_user = new Label();
	
	/** The editor administration. */
	private EditorAdministrationAsync editorAdministration = null;
	
	/** The vp. */
	VerticalPanel vp = new VerticalPanel();	
	
	/** The subsc cell. */
	SubscCell subscCell = new SubscCell();
	
	/** The cell list. */
	CellList<User> cellList = new CellList<User>(subscCell);
	
	/** The selection model. */
	SingleSelectionModel<User> selectionModel = new SingleSelectionModel<User>();
	
	/** The data provider. */
	ListDataProvider<User> dataProvider = new ListDataProvider<User>();
	
	/** The cell subs. */
	Vector<User> cellSubs = new Vector<User>();	
	
	/** The selected user. */
	User selectedUser = new User();
	
	/** The current user. */
	User currentUser = new User();
	
	/** The profile btn. */
	private Button profileBtn = new Button("Mein Profil");
	
	/** The add btn. */
	private Button addBtn = new Button("Hinzufügen");
	
	/** The dlt btn. */
	private Button dltBtn = new Button("Löschen");
	
	/** The btn panel. */
	private VerticalPanel btnPanel = new VerticalPanel();
	
	/** The box. */
	public Vector<User> box = new Vector<User>();
	
	/** The oracle. */
	MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	
	/** The suggestbox. */
	SuggestBox suggestbox = new SuggestBox(oracle);
	
	/** The testpanel. */
	VerticalPanel testpanel = new VerticalPanel();
	
	/** The lbl. */
	Label lbl = new Label();
	
	/** The main panel. */
	MainPanel mainPanel = new MainPanel();
	
	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onLoad()
	 */
	public void onLoad() {
		super.onLoad();	
		//erstellen einer Insatnz des Asyncronen Interfaces, des Servlets
		if(editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
	    }
				currentUser = ClientsideSettings.getUser();
				 editorAdministration.getSubsOfCurrentUser(currentUser, new AsyncCallback<Vector<Subs>>() {
					 public void onFailure(Throwable t) {
						 ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(t.getMessage());
							}	
					 public void onSuccess(Vector<Subs> result) {
						 if(result.isEmpty()) {					
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
							
							ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(t.getMessage());
							}		
						
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
	
	/**
	 * Builds the list.
	 */
	private void buildList() {
		if(editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
	    }
		
		
		 editorAdministration.getSubsOfCurrentUser(currentUser, new AsyncCallback<Vector<Subs>>() {
				public void onFailure(Throwable t) {
					ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(t.getMessage());	
				}		
				
				public void onSuccess(Vector<Subs> result) {
					for(Subs s: result) {
						editorAdministration.getUserById(s.getTargetUser(),new AsyncCallback<User>() {
							public void onFailure(Throwable t) {
								ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(t.getMessage());
								}

							@Override
							public void onSuccess(User result) {
							
								cellSubs.add(result);
								cellList.setRowCount(cellSubs.size(), true);
							    cellList.setRowData(0, cellSubs);
							    cellList.setStylePrimaryName("subslist");
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
		    	  selectedUser = selectionModel.getSelectedObject();
		    	  mainPanel.createPinnboard(selectedUser);
		    	  
		        if (selectedUser != null) {
		        			        	
		        	if(editorAdministration == null) {
		    			editorAdministration = ClientsideSettings.getAdministration();
		    		}		
		    		
		    		
		        }
			
		}	
	});
		this.add(testpanel);
		
	}
	
	/**
	 * Adds the sub.
	 *
	 * @param u the u
	 */
	public void addSub(User u) {
		
		currentUser = ClientsideSettings.getUser();
	
		 if(editorAdministration == null) {
				editorAdministration = ClientsideSettings.getAdministration();
		    }
		 
		editorAdministration.createSubs(currentUser.getId(),u.getId(), new AsyncCallback<Subs>() {


			public void onFailure(Throwable caught) {
				ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());				
			}

		

			
			public void onSuccess(Subs result) {
				editorAdministration.getUserById(result.getTargetUser(), new AsyncCallback<User>() {

					@Override
					public void onFailure(Throwable caught) {
						ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());
						
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
	
	/**
	 * The Class DeleteClickHandler.
	 */
	private class DeleteClickHandler implements ClickHandler{
		
		/* (non-Javadoc)
		 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
		 */
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
					ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());
					
				}

				
				public void onSuccess(Void result) {	
					cellList.setRowCount(cellSubs.size(), true);
				    cellList.setRowData(0, cellSubs);
				    dataProvider.refresh();
				}
				 
			 });
			
		}
	}
	
	/**
	 * The Class AddClickHandler.
	 */
	private class AddClickHandler implements ClickHandler{
		
		/* (non-Javadoc)
		 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
		 */
		public void onClick(ClickEvent event) {
			if(!suggestbox.getValue().isEmpty()) {
			String inhalt = suggestbox.getValue();
			String[] split = inhalt.split("- ");						
			editorAdministration.getUserByEmail(split[1], new AsyncCallback<User>() {

				@Override
				public void onFailure(Throwable caught) {
					ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());
				}

				@Override
				public void onSuccess(User result) {
					if(result.getFirstname() == null) {
						ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox("Ungültiger User.");
					}
					if(result != null) {
						boolean toAdd = true;
						if (result.getId()==currentUser.getId()) {
							ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox("Sie können sich nicht selbst folgen.");
							toAdd = false;
						}
						for(User u: cellSubs) {
							if(result.getId()==u.getId()) {
								ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox("Der Nutzer "+result.getFirstname()+" "+ result.getLastname()+ " befindet sich bereits unter den Abonnenten");
								toAdd = false;
							}
							
						}
						if(toAdd && result.getFirstname()!=null) {
					addSub(result);}
					}
				}
				
			});
			
			
			
			}if(suggestbox.getValue().isEmpty()){
			ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox("Bitte wähle ein Nutzer zum Abonieren aus");
			}
			}
	}
	
	/**
	 * The Class MyProfileClickHandler.
	 */
	private class MyProfileClickHandler implements ClickHandler{
		
		/* (non-Javadoc)
		 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
		 */
		public void onClick(ClickEvent event) {		
			
			//selectionModel.setSelected(selectedUser, false);
			mainPanel.createPinnboard(currentUser);			
			
			}
	}
	
	/**
	 * Gets the main panel.
	 *
	 * @return the main panel
	 */
	public MainPanel getMainPanel() {
		return mainPanel;
	}
	
	/**
	 * Sets the main panel.
	 *
	 * @param mainPanel the new main panel
	 */
	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
}
