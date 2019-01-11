package de.hdm.itp.client;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itp.client.ClientsideFunctions.InputDialogBox.CloseButton;
import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.bo.Comment;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.User;


/**
 * Ergänzt die Klasse {@link ClientsideSettings} um weitere Funktionen und Dienste,
 * die für alle Client-seitigen Klassen relevant sind.
 * 
 */
public abstract class ClientsideFunctions {
	
	/** Objekt zur Kommunikation mit dem Server-seitgen 
	 * Dienst namens editorAdministration*/
	private static EditorAdministrationAsync editorAdministration = ClientsideSettings.getEditorAdministration();
	
	/** Client-seitiges Nutzer-Object, repräsentiert den angemeldeten Nutzer. */
	private static User user = ClientsideSettings.getUser();
	
	/**
	 * Die Klasse InputDialogBox wird genutzt um Eingaben des Nutzers zu ermöglichen.
	 * Hierfür stehen einige GWT-Elemente zur Auswahl, welche für unterschiedliche Funktionen zu
	 * Fenstern zusammengesetzt werden.
	 */
	public static class InputDialogBox extends DialogBox{
		
		/** Diverse Attribute und GWT Elemente die zur Realisierung der InputDialogBox benötigt werden. */
		private String input;
		Label dialogBoxLabel = new Label();
        private TextBox firstnameTextBox;
        private TextBox lastnameTextBox;
        private TextBox nicknameTextBox;
        private TextBox emailTextBox;

        private ListBox listBox;
        private SuggestBox sb;
        private MultiWordSuggestOracle oracle;
		CloseButton close = new CloseButton(this);
        OkButton ok = new OkButton();
		
		/**
		 * Instanziert eine InputDialogBox, welche genutzt wird um einen neuen Nutzer anzulegen.
		 *
		 * @param userEmail Email des neuen Nutzers
		 */
		public InputDialogBox(String userEmail) {
			
//			setEmailTextBox(new TextBox());
//			getEmailTextBox().getElement().setPropertyString("placeholder", "Email...");
//
//			
			setFirstnameTextBox(new TextBox());
			getFirstnameTextBox().getElement().setPropertyString("placeholder", "Vorname...");
			getFirstnameTextBox().setText("");
			
			setLastnameTextBox(new TextBox());
			getLastnameTextBox().getElement().setPropertyString("placeholder", "Nachname...");
			getLastnameTextBox().setText("");
			
			setNicknameTextBox(new TextBox());
			getNicknameTextBox().getElement().setPropertyString("placeholder", "Nickname...");
			getNicknameTextBox().setText("");
			
			listBox = new ListBox();
			listBox.addItem("Männlich");
			listBox.addItem("Weiblich");
			listBox.addItem("Divers");
			
			ok.addStyleName("okbutton");
	        close.addStyleName("closebutton");

			setText("Eingabe");
			setdialogBoxLabel("Sie sind noch nicht registriert.\n Bitte geben Sie Ihre Informationen an: ");
			
			setAnimationEnabled(true);
			setGlassEnabled(true);
			
	        
			VerticalPanel panel = new VerticalPanel();
	        panel.setHeight("100");
	        panel.setWidth("600");
	        panel.setSpacing(10);
	        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
	        panel.add(dialogBoxLabel);
			HorizontalPanel hpanel=new HorizontalPanel();
			
			hpanel.add(close);
	        hpanel.add(ok);
//	        panel.add(emailTextBox);
	        panel.add(firstnameTextBox);
	        panel.add(lastnameTextBox);
	        panel.add(nicknameTextBox);
	        panel.add(listBox);
	        panel.add(hpanel);

	        setWidget(panel);
	        
	        show();
			center();
		}
		
		/**
		 * Getter für OkButton
		 *
		 * @return OkButton
		 */
		public OkButton getOKButton() {
			return this.ok;
		}
		
		/**
		 * Setter für OkButton
		 *
		 * @param b neuer OkButton
		 */
		public void setOKButton(OkButton b) {
			this.ok = b;
		}
		
		/**
		 * Getter von input.
		 *
		 * @return den Input
		 */
		public String getInput() {
			return this.input;
		}
		
		/**
		 * Setter von input.
		 *
		 * @param input der Input
		 */
		public void setInput(String input) {
			this.input = input;
		}

		/**
		 * Getter für Label
		 *
		 * @return Label
		 */
		public Label getdialogBoxLabel () {
			return this.dialogBoxLabel;
		}
		
		/**
		 * Setter des Labels.
		 *
		 * @param labelText der Text des Labels.
		 */
		public void setdialogBoxLabel (String labelText) {
			this.dialogBoxLabel.setText(labelText);
		}
		
		/**
		 * Getter für MultiUseTextBox.
		 *
		 * @return MultiUseTextBox
		 */
		

		/**
		 * Getter für SuggestBox.
		 *
		 * @return SuggestBox
		 */
		public SuggestBox getSuggestBox() {
			return sb;
		}

		/**
		 * Setter für SuggestBox.
		 *
		 * @param sb neue SuggestBox
		 */
		public void setSuggestBox(SuggestBox sb) {
			this.sb = sb;
		}

		/**
		 * Getter für Oracle.
		 *
		 * @return Oracle
		 */
		public MultiWordSuggestOracle getOracle() {
			return oracle;
		}

		/**
		 * Setter für Oracle.
		 *
		 * @param oracle neues Oracle
		 */
		public void setOracle(MultiWordSuggestOracle oracle) {
			this.oracle = oracle;
		}

		/**
		 * Getter für NameTextBox.
		 *
		 * @return NameTextBox
		 */
		

		/**
		 * Setter für NickNameTextBox.
		 *
		 * @param nameTextBox neue NameTextBox
		 */
		public void setNicknameTextBox(TextBox nicknameTextBox) {
			this.nicknameTextBox = nicknameTextBox;
		}
		
		/**
		 * Getter für NickNameTextBox.
		 *
		 * @return NameTextBox
		 */
		public TextBox getNicknameTextBox() {
			return nicknameTextBox;
		}

		/**
		 * Setter für NameTextBox.
		 *
		 * @param nameTextBox neue NameTextBox
		 */

		/**
		 * Getter für ListBox.
		 *
		 * @return ListBox
		 */
		public ListBox getListBox() {
			return listBox;
		}

		/**
		 * Setter für ListBox.
		 *
		 * @param listBox neue ListBox
		 */
		public void setListBox(ListBox listBox) {
			this.listBox = listBox;
		}
	
	public TextBox getFirstnameTextBox() {
			return firstnameTextBox;
		}

		public void setFirstnameTextBox(TextBox firstnameTextBox) {
			this.firstnameTextBox = firstnameTextBox;
		}

	public TextBox getLastnameTextBox() {
			return lastnameTextBox;
		}

		public void setLastnameTextBox(TextBox lastnameTextBox) {
			this.lastnameTextBox = lastnameTextBox;
		}

	public TextBox getEmailTextBox() {
			return emailTextBox;
		}

		public void setEmailTextBox(TextBox emailTextBox) {
			this.emailTextBox = emailTextBox;
		}

	/**
	 * Die Klasse popUpBox ersetzt Window.alert() und dient zusätzlich zur Sicherheitsabfrage beim löschen.
	 */
	public static class popUpBox extends DialogBox {
		

		CloseButton closeButton = null;
		OkButton okButton = null;
		VerticalPanel panel = null;
		HorizontalPanel hpanel = null;
		Label dialogBoxLabel = new Label();
		
		/**
		 * Instanziert popUpBox, welche zur Sicherheitsabfrage beim löschen dient.
		 *
		 * @param dbLabel angezeigter Text
		 * @param ok OkButton
		 * @param close CloseButton
		 */
		public popUpBox(String dbLabel, OkButton ok, CloseButton close) {
			closeButton = close;
			okButton = ok;
			dialogBoxLabel.setText(dbLabel);
			
			this.setText("Meldung");
			panel = new VerticalPanel();
	        panel.setHeight("100");
	        panel.setWidth("300");
	        panel.setSpacing(10);
	        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
	        panel.add(dialogBoxLabel);
			hpanel = new HorizontalPanel();
			hpanel.add(closeButton);
	        hpanel.add(okButton);
	        panel.add(hpanel);
	        
	        setWidget(panel);
	        
	        show();
	        center();
		}
		
		/**
		 * Instantiates neue popUpBox, dient als simples Feedback für gerade ausgeführte Aktion.
		 *
		 * @param dbLabel angezeigter Text
		 * @param ok OkButton
		 */
		public popUpBox(String dbLabel, OkButton ok) {
			okButton = ok;
			dialogBoxLabel.setText(dbLabel);
			
			this.setText("Meldung");
			panel = new VerticalPanel();
	        panel.setHeight("100");
	        panel.setWidth("300");
	        panel.setSpacing(10);
	        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
	        panel.add(dialogBoxLabel);
			hpanel = new HorizontalPanel();
	        hpanel.add(okButton);
	        panel.add(hpanel);
	        
	        setWidget(panel);
	        
	        show();
	        center();
		}
		
		/**
		 * Instantiates neue popUpBox, dient als simples Feedback für gerade ausgeführte Aktion.
		 *
		 * @param dbLabel angezeigter Text
		 * @param close CloseButton
		 */
		public popUpBox(String dbLabel, CloseButton close) {
			closeButton = close;
			dialogBoxLabel.setText(dbLabel);
			
			this.setText("Meldung");
			panel = new VerticalPanel();
	        panel.setHeight("100");
	        panel.setWidth("300");
	        panel.setSpacing(10);
	        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
	        panel.add(dialogBoxLabel);
			hpanel = new HorizontalPanel();
	        hpanel.add(closeButton);
	        panel.add(hpanel);
	        
	        setWidget(panel);
	        
	        show();
	        center();
		}
		
		/**
		 * Getter für CloseButton.
		 *
		 * @return CloseButton
		 */
		public CloseButton getCloseButton() {
			return this.closeButton;
		}
		
		/**
		 * Setter für CloseButton.
		 *
		 * @param b neuer CloseButton
		 */
		public void setCloseButton(CloseButton b) {
			this.closeButton = b;
		}
		
		/**
		 * Getter für OkButton.
		 *
		 * @return OkButton
		 */
		public OkButton getOkButton() {
			return this.okButton;
		}
		
		/**
		 * Setter für OkButton
		 *
		 * @param b neuer OkButton
		 */
		public void setOkButton(OkButton b) {
			this.okButton = b;
		}
	}
	
	/**
	 * Die Klasse CloseButton erweitert Button um einen Style und standard Clickhandler.
	 */
	public static class CloseButton extends Button{
		
		/** zu schließende DialogBox */
		DialogBox db;
		
		/**
		 * Instanziert CloseButton, übergabe enthält zu schließende DialogBox.
		 *
		 * @param db zu schließende DialogBox
		 */
		public CloseButton(DialogBox db) {
			this.db = db;
			this.addClickHandler(new CloseDBClickHandler(db)); 
			this.setText("Abbrechen");
			this.addStyleName("closebutton");
		}
		
		/**
		 * Instanziert CloseButton mit verändertem Text.
		 *
		 * @param text neuer Text des CloseButtons
		 */
		public CloseButton(String text) {
			this.setText(text);
			this.addStyleName("closebutton");
		}
		
		/**
		 * Instanziert CloseButton.
		 */
		public CloseButton() {
			this.setText("Abbrechen");
			this.addStyleName("closebutton");
		}
		
		/**
		 * Fügt standard ClickHandler zum schließen einer DialogBox zum Button hinzu.
		 *
		 * @param db DialogBox
		 */
		public void addCloseDBClickHandler(DialogBox db) {
			this.addClickHandler(new CloseDBClickHandler(db));
		}
		
		/**
		 * Standard ClickHandler zum schließen einer DialogBox.
		 */
		public class CloseDBClickHandler implements ClickHandler{
			
			DialogBox db;
	
			public CloseDBClickHandler(DialogBox db) {
				this.db=db;
			}
			public void onClick(ClickEvent event) {
				db.hide();
			}
		}
	}
	
	/**
	 * Die Klasse OkButton erweitert Button um einen Style und standard Clickhandler.
	 */
	public static class OkButton extends Button{

		
		/**
		 * Instanziert OkButton mit verändertem Text.
		 *
		 * @param text der neue Text des Buttons
		 */
		public OkButton(String text) {
			this.setText(text);
			this.addStyleName("okbutton");
		}
		
		/**
		 * Instanziert OkButton.
		 */
		public OkButton() {
			this.setText("OK");
			this.addStyleName("okbutton");
		}
		
		/**
		 * Fügt standard ClickHandler zum schließen einer DialogBox zum Button hinzu.
		 *
		 * @param db DialogBox
		 */
		public void addCloseDBClickHandler(DialogBox db) {
			this.addClickHandler(new CloseDBClickHandler(db));
		}
		
		/**
		 * Standard ClickHandler zum schließen einer DialogBox.
		 */
		private class CloseDBClickHandler implements ClickHandler{
			
			DialogBox db;
	
			public CloseDBClickHandler(DialogBox db) {
				this.db=db;
			}
			public void onClick(ClickEvent event) {
				db.hide();
			}
		}
	}
}
	public static class CommentDialogBox extends DialogBox{
		final VerticalPanel panel = new VerticalPanel();
		final HorizontalPanel buttonpanel = new HorizontalPanel();
		TextBox comment_box = new TextBox();
		Button submit_btn = new Button("Kommentieren");
		CloseButton close_btn = new CloseButton();
		
		
		public CommentDialogBox(Post post) {
			if (editorAdministration == null) {
				editorAdministration = ClientsideSettings.getAdministration();
			}
			 user = ClientsideSettings.getUser();
			 close_btn.addClickHandler(new CloseDBClickHandler(this));
			 comment_box.setWidth("100");
			submit_btn.addClickHandler(new SubmitDBClickHandler(post.getId()));
			 editorAdministration.getCommentsOfPost(post, new AsyncCallback<Vector<Comment>>(){

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
					
				}

				@Override
				public void onSuccess(Vector<Comment> result) {
					 panel.setHeight("100");
				     panel.setWidth("600");
				     panel.setSpacing(10);
				     panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
				     
					for(final Comment c: result) {
						//hier noch Namen holen
						editorAdministration.getUserById(c.getOwnerId(), new AsyncCallback<User>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert(caught.getMessage());
								
							}

							@Override
							public void onSuccess(User result) {
								panel.add(new StyleLabel("Kommentar von "+result.getFirstname()+" '"+result.getNickname()+"' "+result.getLastname()+": ","search_lbl"));
								panel.add(new StyleLabel(c.getText(),"search_lbl"));
								panel.add(new Label("--------------------------------------------"));
								//TODO if your own, delete or modify
								
								buttonpanel.add(comment_box);
								buttonpanel.add(submit_btn);
								buttonpanel.add(close_btn);
								panel.add(buttonpanel);
								
							}
							
						});
						
					}
					
					
				}
			 
			
		});
			setWidget(panel);
			show();
			center();
		
		
	}
public class CloseDBClickHandler implements ClickHandler{
			
			DialogBox db;
	
			public CloseDBClickHandler(DialogBox db) {
				this.db=db;
			}
			public void onClick(ClickEvent event) {
				db.hide();
			}
		}
public class SubmitDBClickHandler implements ClickHandler{
	
	String text1 = new String();
	int postid1;
	

	public SubmitDBClickHandler(int postid) {
		
	
		postid1=postid;
	}
	public void onClick(ClickEvent event) {
		text1 = comment_box.getValue();
		Window.alert(text1+" "+postid1+" "+user.getId());
		if(text1==null) {
			Window.alert("Leerer Kommentar kann nciht gepostet werden!");
		}else {
		
		editorAdministration.createComment(postid1, text1, user, new AsyncCallback<Comment>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				
			}

			@Override
			public void onSuccess(Comment result) {
				panel.add(new Label("success"));
				
			}
			
		});
	
	}}
}
	}
	}
