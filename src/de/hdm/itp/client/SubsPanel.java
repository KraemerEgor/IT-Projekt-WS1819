package de.hdm.itp.client;

import java.util.Vector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.bo.User;

public class SubsPanel extends VerticalPanel {
	SubsTreeViewModel stvm = null;
	private EditorAdministrationAsync editorAdministration = null;
	VerticalPanel vp = new VerticalPanel();
	Label header_lbl = new Label("Subs Panel:");
	Button btn_test = new Button("Test DB");
	
	
	public void onLoad() {
		super.onLoad();	
		  
	    if(editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
	    }
	  

		
		
		this.addStyleName("Subs");
		this.setStyleName("Subs");
		NewCHtest n = new NewCHtest();
		btn_test.addClickHandler(n);
		//this.add(stvm);
		this.add(btn_test);
		vp.add(header_lbl);
		this.add(vp);
		Window.alert("Subs geladen");
		
	}

	private class NewCHtest implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			editorAdministration.getAllUser(new AsyncCallback<Vector<User>>() {
				public void onFailure(Throwable t) {
					System.out.println("fail");
					Window.alert(t.getMessage());}		
				
				public void onSuccess(Vector<User> result) {
					System.out.println("soweit so gut");
					for(User u: result) {
						Window.alert(u.getFirstname()+" "+u.getLastname());
						System.out.println(u.getFirstname()+" "+u.getLastname());
						Label lbl = new Label(u.getFirstname()+" "+u.getLastname());
						vp.add(lbl);}}
			});
			
		}
		
		
	}
	
}
