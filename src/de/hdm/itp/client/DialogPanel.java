package de.hdm.itp.client;

import java.util.Vector;
import de.hdm.itp.server.EditorAdministrationImpl;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.bo.Comment;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.User;

public class DialogPanel extends DialogBox implements ClickHandler {

	    public String CommentDialog() {
		      // Dialog-Name
		      setText("Kommentar verfassen");
			  new DialogPanel().show();
		      
		      Button ok = new Button("OK");
		      ok.addClickHandler(new ClickHandler() {
		        public void onClick(ClickEvent event) {
		          DialogPanel.this.hide();
		        }
		      });
		      setWidget(ok);
		      
		      return "Neuer Kommentar";
		      
		    }
	
	
	    public String PostDialog() {
		      // Dialog-Name
		      setText("Post verfassen");
			  new DialogPanel().show();
		      
		      Button ok = new Button("OK");
		      ok.addClickHandler(new ClickHandler() {
		        public void onClick(ClickEvent event) {
		          DialogPanel.this.hide();
		        }
		      });
		      setWidget(ok);
		      
		      return "Neuer Post";
	      
	    }


	  public void onModuleLoad() {
		  super.onLoad();
	  }

	  public void onClick(ClickEvent event) {
	    // Instantiate the dialog box and show it.
	    new DialogPanel().show();
	  }
	}