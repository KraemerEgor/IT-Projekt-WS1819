package de.hdm.itp.client;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.google.gwt.cell.client.TextCell;
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
import de.hdm.itp.shared.bo.User;

public class SubsPanel extends VerticalPanel {
	private EditorAdministrationAsync editorAdministration = null;
	VerticalPanel vp = new VerticalPanel();
	Label header_lbl = new Label("Subs Panel:");
	Button btn_test = new Button("Test DB");

	User u = new User();
	private Vector<String> users = new Vector<String>();
	
	private List<String> DAYS = Arrays.asList("Sunday", "Monday",
		      "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
	
	TextCell textCell = new TextCell();
	SubscCell subscCell = new SubscCell();
	CellList<User> cellList = new CellList<User>(subscCell);	
	
	
	public void onLoad() {
		super.onLoad();	
		  
	    if(editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
	    }
	    editorAdministration.getAllUser(new AsyncCallback<Vector<User>>() {
			public void onFailure(Throwable t) {
				System.out.println("fail");
				Window.alert(t.getMessage());}		
			
			public void onSuccess(Vector<User> result) {				
				for(User u: result) {
					//users.add(u.getFirstname()+" "+u.getNickname()+" "+u.getLastname());
					
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
				    cellList.setRowCount(result.size(), true);
				    cellList.setRowData(0, result);
			
				   vp.add(cellList);
					}
				
			}
		});
	  
		this.addStyleName("Subs");
		this.setStyleName("Subs");
		NewCHtest n = new NewCHtest();
		btn_test.addClickHandler(n);
		this.add(btn_test);
		vp.add(header_lbl);
		this.add(vp);
		
		u.setId(10000001);
		u.setFirstname("egor");
		u.setLastname("krämer");
		u.setNickname("kulak");
		u.setGender("m");
		
		
		
	   
	    
	}

	private class NewCHtest implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			editorAdministration.getAllUser(new AsyncCallback<Vector<User>>() {
				public void onFailure(Throwable t) {
					Window.alert(t.getMessage());}		
				
				public void onSuccess(Vector<User> result) {
					for(User u: result) {
						Window.alert(u.getFirstname()+" "+u.getNickname()+" "+u.getLastname()+" aus der Datenbank");
						}}
			});
			
		}
		
	}
	
}
