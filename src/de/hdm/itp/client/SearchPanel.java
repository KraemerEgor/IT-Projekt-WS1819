package de.hdm.itp.client;

import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Pattern;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.bo.User;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;

/**
 * The Class SearchPanel.
 */
public class SearchPanel extends FlowPanel {
	
	/** The admin. */
	private EditorAdministrationAsync admin = null;
	
	/** The result panel. */
	VerticalPanel resultPanel = new VerticalPanel();
	
	/** The report link. */
	private Anchor reportLink = new Anchor("Report");
	
	/** The header lbl. */
	private Label header_lbl = new Label("Navigation"); 
	
	/** The profile btn. */
	private Button profileBtn = new Button("Mein Profil");
	
	/** The add btn. */
	private Button addBtn = new Button("Hinzufügen");
	
	/** The dlt btn. */
	private Button dltBtn = new Button("Löschen");
	
	/** The box. */
	public Vector<User> box = new Vector<User>();
	
	/** The oracle. */
	MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	
	/** The suggestbox. */
	SuggestBox suggestbox = new SuggestBox(oracle);
	
	/** The sp. */
	SubsPanel sp = new SubsPanel();
	
	/** The pp. */
	PinboardPanel pp = new PinboardPanel();
	
	/** The current user. */
	User currentUser = new User();
	
	/** The btn panel. */
	private VerticalPanel btnPanel = new VerticalPanel();
	
	
	
	
	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onLoad()
	 */
	public void onLoad() {
		
		super.onLoad();
		 if(admin == null) {
				admin = ClientsideSettings.getAdministration();
		    }		
		currentUser = ClientsideSettings.getUser();
		   
		   
		this.setStylePrimaryName("Search");
		
		header_lbl.setStylePrimaryName("Header");
		this.add(header_lbl);
			
		profileBtn.setStylePrimaryName("submit");
		btnPanel.add(profileBtn);
		
		
		addBtn.setStylePrimaryName("submit");
		btnPanel.add(addBtn);
		
		dltBtn.setStylePrimaryName("submit");
		btnPanel.add(dltBtn);
		
		btnPanel.setStylePrimaryName("btnPanel");
		this.add(btnPanel);
		
		
		admin.getAllUser(new AsyncCallback<Vector<User>>() {
			public void onFailure(Throwable t) {
				
				Window.alert(t.getMessage());}		
			
			public void onSuccess(Vector<User> result) {
				
				for(User u: result) {
					oracle.add(u.getFirstname()+" ' "+u.getNickname()+" ' "+u.getLastname()+" - "+u.getEmail());
					}
				suggestbox.setStylePrimaryName("suggestbox");
				
				resultPanel.add(suggestbox);}
			
		});
		
		this.add(resultPanel);
		
		
		
		
		profileBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {		
				//hier soll die eigene Pinnwand angezeigt werden
				Window.alert("Show own Pinboard");
				
				currentUser = ClientsideSettings.getUser();
				pp.createPinboard(currentUser);
				
				
				
				}
			});
		
		dltBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {		
				
				
				
				}
			});
			
		addBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(!suggestbox.getValue().isEmpty()) {
				String inhalt = suggestbox.getValue();
				String[] split = inhalt.split("- ");						
				admin.getUserByEmail(split[1], new AsyncCallback<User>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());						
					}

					@Override
					public void onSuccess(User result) {
						//TODO: hier funktioniert die IF nicht
						if(result == null) {
							Window.alert("ungültiger User");
						}if(result != null) {
						sp.addSub(result);
						}
					}
					
				});
				
				
				
				}if(suggestbox.getValue().isEmpty()){
				Window.alert("Bitte wähle ein Nutzer zum Abonieren aus");	
				}
				}
			});
		
				
		
		
		

	}
		
	
	
}



