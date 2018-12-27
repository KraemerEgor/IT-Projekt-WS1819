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

public class DialogPanel implements ClickHandler {

	  private static class MyDialog extends DialogBox {

	    public MyDialog() {
	      // Set the dialog box's caption.
	      setText("My First Dialog");

	      // Enable animation.
	      setAnimationEnabled(true);

	      // Enable glass background.
	      setGlassEnabled(true);

	      // DialogBox is a SimplePanel, so you have to set its widget property to
	      // whatever you want its contents to be.
	      Button ok = new Button("OK");
	      ok.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	          MyDialog.this.hide();
	        }
	      });
	      setWidget(ok);
	    }
	  }

	  public void onModuleLoad() {
	    Button b = new Button("Click me");
	    b.addClickHandler(this);

	    RootPanel.get("Main").add(b);
	  }

	  public void onClick(ClickEvent event) {
	    // Instantiate the dialog box and show it.
	    new MyDialog().show();
	  }
	}