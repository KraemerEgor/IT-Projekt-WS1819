package de.hdm.itp.client;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextArea;
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
			this.setText("Schließen");
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
			this.setText("Schließen");
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
		final ScrollPanel scrollpanel = new ScrollPanel();
		final VerticalPanel panel = new VerticalPanel();
		final HorizontalPanel commentPanel = new HorizontalPanel();
		final HorizontalPanel buttonpanel = new HorizontalPanel();
		TextArea comment_box = new TextArea();
		Button submit_btn = new Button("Kommentieren");
		CloseButton close_btn = new CloseButton();
		static PinboardPanel pinboardPanel = new PinboardPanel();
		CommentDialogBox db;
		
		public Post currentPost = new Post();
		
		
		public CommentDialogBox(Post post, PinboardPanel pp) {
			pinboardPanel = pp;
			this.setStylePrimaryName("commentBox");
			db = this;
			currentPost = post;
			if (editorAdministration == null) {
				editorAdministration = ClientsideSettings.getAdministration();
			}
			//ich kriege die Gr��e der DialogBoxen nicht angepasst
			//this.setSize("200px", "200px");
			 user = ClientsideSettings.getUser();
			 editorAdministration.getPostById(post.getId(), new AsyncCallback<Post>() {

				@Override
				public void onFailure(Throwable caught) {
					ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());
					
				}

				@Override
				public void onSuccess(Post result) {
					close_btn.addClickHandler(new CloseDBClickHandler(db, result.getOwnerId()));
					
				}
				 
			 });
			 
			comment_box.setStylePrimaryName("commentInput");
			scrollpanel.setWidth("420px");
			scrollpanel.setHeight("350px");
			
			submit_btn.addClickHandler(new SubmitDBClickHandler(post.getId()));
			 editorAdministration.getCommentsOfPost(post, new AsyncCallback<Vector<Comment>>(){

				@Override
				public void onFailure(Throwable caught) {
					ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());
					
				}

				@Override
				public void onSuccess(Vector<Comment> result) {
					 panel.setHeight("700");
				     panel.setWidth("800");
				     panel.setSpacing(10);
				     panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
				     panel.setSize("700", "800");
				     scrollpanel.setAlwaysShowScrollBars(true);
				     if(!result.isEmpty()) {
					for(final Comment c: result) {
						editorAdministration.getUserById(c.getOwnerId(), new AsyncCallback<User>() {

							@Override
							public void onFailure(Throwable caught) {
								ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());
								
							}

							@Override
							public void onSuccess(User result) {
								panel.add(new StyleLabel("Kommentar von "+result.getFirstname()+" '"+result.getNickname()+"' "+result.getLastname()+": ","label"));
								panel.add(new StyleLabel(c.getText(),"label"));
							
								if(c.getOwnerId()==user.getId()) {
									HorizontalPanel ownButtonPanel = new HorizontalPanel();
									ownButtonPanel.add(new StyleLabel("Löschen", new DeleteCommentClickHandler(c),"changeLabel"));
									ownButtonPanel.add(new StyleLabel("Bearbeiten", new EditCommentClickHandler(c,db),"changeLabel"));
									panel.add(ownButtonPanel);
								}
								panel.add(new Label("--------------------------------------------"));
								
								commentPanel.add(comment_box);
								buttonpanel.add(submit_btn);
								submit_btn.setStylePrimaryName("commentButton");
								buttonpanel.add(close_btn);
								close_btn.setStylePrimaryName("commentButton");
								panel.add(commentPanel);
								panel.add(buttonpanel);
								buttonpanel.setStylePrimaryName("panelCommentBox");
								scrollpanel.add(panel);
								
							}
							
						});
						
					}
					
					
				}else{
					commentPanel.add(comment_box);
					buttonpanel.add(submit_btn);
					submit_btn.setStylePrimaryName("commentButton");
					buttonpanel.add(close_btn);
					close_btn.setStylePrimaryName("commentButton");
					panel.add(commentPanel);
					panel.add(buttonpanel);
					scrollpanel.add(panel);
					}
				}
			 
			
		});
			setWidget(scrollpanel);
			show();
			center();
		
	}
		
public void refresh(int postId) {
			this.hide();
			Post post = new Post();
			post.setId(postId);
			CommentDialogBox cbd = new CommentDialogBox(post, pinboardPanel);
			
			
		}

public class CloseDBClickHandler implements ClickHandler{
			
			DialogBox db;
			PinboardPanel pp = new PinboardPanel();
			int postOwnerId;
			
	
			public CloseDBClickHandler(DialogBox db, int postOwnerId) {
				this.db=db;
				this.postOwnerId=postOwnerId;
			}
			public void onClick(ClickEvent event) {
				db.hide();
				User u= new User();
				u.setId(postOwnerId);
				pp.createPinboard(u);
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
		if(text1=="") {
			ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox("Leerer Kommentar kann nicht gepostet werden.");
		}else {
		editorAdministration.createComment(postid1, text1, user, new AsyncCallback<Comment>() {
			@Override
			public void onFailure(Throwable caught) {
				ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());
				
			}

			@Override
			public void onSuccess(Comment result) {
				refresh(result.getPostId());
				
			}
			
		});
	
	}}
}
public class DeleteCommentClickHandler implements ClickHandler{
	Comment comment = new Comment();
	public DeleteCommentClickHandler(Comment c) {
		comment=c;
	}

	@Override
	public void onClick(ClickEvent event) {
		editorAdministration.deleteComment(comment, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());
				
			}

			@Override
			public void onSuccess(Void result) {
				refresh(comment.getPostId());
				
			}
			
		});
		
	}
	
}
public class EditCommentClickHandler implements ClickHandler{
	Comment comment = new Comment();
	CommentDialogBox dialog;
	public EditCommentClickHandler(Comment c, CommentDialogBox dialog) {
		comment=c;
		this.dialog = dialog;
	}
	@Override
	public void onClick(ClickEvent event) {
		new UpdateCommentDialogBox(comment, dialog);
		//hier wollte ich einen neuen Popup einbauen, der deinen Kommentar verändern lässt
		//am besten mit einer Textbox, welche den aktuellen Text beinhaltet
		
	}
	
}
	}
	
	//TODO
	public static class UpdatePostDialogBox extends DialogBox{
		
		final ScrollPanel scrollPanel = new ScrollPanel();
		final VerticalPanel verticalPanel = new VerticalPanel();
		final HorizontalPanel buttonPanel = new HorizontalPanel();
		final HorizontalPanel commentPanel = new HorizontalPanel();
		StyleLabel header = new StyleLabel("Beitrag bearbeiten","search_lbl");
		TextArea updatePostBox = new TextArea();
		static PinboardPanel pinboardPanel = new PinboardPanel();
		
		Button doneBtn = new Button("Fertig");
		CloseButton closeBtn = new CloseButton();
		Post updatedPost = new Post();
		
		public TextArea getUpdatePostBox() {
			return updatePostBox;
		}
		public void setUpdatePostBox(TextArea updatePostBox) {
			this.updatePostBox = updatePostBox;
		}
		
		public UpdatePostDialogBox(Post post, PinboardPanel pp) {
			if (editorAdministration == null) {
				editorAdministration = ClientsideSettings.getAdministration();
			}
			pinboardPanel = pp;
			 user = ClientsideSettings.getUser();
			editorAdministration.updatePost(post, post.getContent(), new AsyncCallback<Post>() {

				@Override
				public void onFailure(Throwable caught) {
					ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());
					
				}

				@Override
				public void onSuccess(Post result) {
					updatePostBox.setValue(result.getContent());
					
				
					
				}
				
			});
			closeBtn.addClickHandler(new CloseDBClickHandler(this));
			closeBtn.setStylePrimaryName("commentButton");
			doneBtn.addClickHandler(new UpdatePostDBClickHandler(post, updatePostBox.getValue(),this, pp));
			doneBtn.setStylePrimaryName("commentButton");
			//updatePostBox.setStylePrimaryName("updatePostBox");
			 verticalPanel.add(header);

			 verticalPanel.add(updatePostBox);
			 updatePostBox.setStylePrimaryName("updatePostInput");
			 buttonPanel.add(doneBtn);
			 buttonPanel.add(closeBtn);
			 verticalPanel.add(buttonPanel);
			 scrollPanel.add(verticalPanel);
			 scrollPanel.setStylePrimaryName("scrollPanelPostUpdate");
			 
			setWidget(scrollPanel);
			this.setStylePrimaryName("updatePostBox");
			
			this.hide();
			show();
			center();
			
		}
		public class UpdatePostDBClickHandler implements ClickHandler{
			
			Post post;
			String updatedText; 
			UpdatePostDialogBox UpdatedPostDB;
			PinboardPanel pp = new PinboardPanel();
			
			
			public UpdatePostDBClickHandler(Post post, String updatedText, UpdatePostDialogBox UpdatedPostDB, PinboardPanel pp) {
				this.post=post;
				this.UpdatedPostDB=UpdatedPostDB;
				this.updatedText=updatedText;
				this.pp=pp;
			}

			@Override
			public void onClick(ClickEvent event) {
				if (editorAdministration == null) {
					editorAdministration = ClientsideSettings.getAdministration();
				}
				editorAdministration.updatePost(post, UpdatedPostDB.getUpdatePostBox().getValue(), new AsyncCallback<Post>() {

					@Override
					public void onFailure(Throwable caught) {
						ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());
					}

					@Override
					public void onSuccess(Post result) {
						
						UpdatedPostDB.hide();
						User u= new User();
						u.setId(result.getOwnerId());
						pp.createPinboard(u);
					}
					
				});
			}
			
		}
		public class CloseDBClickHandler implements ClickHandler{
			
			DialogBox db;
			DialogBox superdb;

			public CloseDBClickHandler(DialogBox db) {
				this.db=db;
			}
			public void onClick(ClickEvent event) {
				db.hide();
				superdb.show();
				
			}
		}
		
	}
	
	public static class UpdateCommentDialogBox extends DialogBox{
		final ScrollPanel scrollpanel = new ScrollPanel();
		final VerticalPanel panel = new VerticalPanel();
		final HorizontalPanel buttonpanel = new HorizontalPanel();
		StyleLabel header = new StyleLabel("Kommentar bearbeiten","search_lbl");
		TextArea commentBox = new TextArea();
		public TextArea getCommentBox() {
			return commentBox;
		}
		public void setCommentBox(TextArea commentBox) {
			this.commentBox = commentBox;
		}
		Button changeBtn = new Button("Fertig");
		CloseButton closeBtn = new CloseButton();
		
		Comment fullcomment = new Comment();
		
	public UpdateCommentDialogBox(Comment comment, CommentDialogBox dialogbox){
		if (editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
		}
		
		 user = ClientsideSettings.getUser();
		editorAdministration.getCommentById(comment.getId(), new AsyncCallback<Comment>() {

			@Override
			public void onFailure(Throwable caught) {
				ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());
				
			}

			@Override
			public void onSuccess(Comment result) {
				fullcomment = result;
				commentBox.setValue(fullcomment.getText());
				
			}
			
		});
		
		 closeBtn.addClickHandler(new CloseDBClickHandler(this, dialogbox));
		 closeBtn.setStylePrimaryName("commentButton");
		 commentBox.setStylePrimaryName("updatePostInput");
		 changeBtn.addClickHandler(new UpdateCommentDBClickHandler(comment, commentBox.getValue(),this, dialogbox));
		 changeBtn.setStylePrimaryName("commentButton");
		 panel.add(header);
		
		 panel.add(commentBox);
		 buttonpanel.add(changeBtn);
		 buttonpanel.add(closeBtn);
		 panel.add(buttonpanel);
		scrollpanel.add(panel);
		scrollpanel.setStylePrimaryName("scrollPanelPostUpdate");
		setWidget(scrollpanel);
		this.setStylePrimaryName("updatePostBox");
		dialogbox.hide();
		show();
		center();
		}
	public class CloseDBClickHandler implements ClickHandler{
		
		DialogBox db;
		DialogBox superdb;

		public CloseDBClickHandler(DialogBox db, DialogBox superdb) {
			this.db=db;
			this.superdb=superdb;
		}
		public void onClick(ClickEvent event) {
			db.hide();
			superdb.show();
			
		}
	}
public class UpdateCommentDBClickHandler implements ClickHandler{
		
	UpdateCommentDialogBox db;
		CommentDialogBox superdb;
		Comment comment;
		String text;
		

		public UpdateCommentDBClickHandler(Comment comment, String text, UpdateCommentDialogBox db, CommentDialogBox superdb) {
			this.db=db;
			this.superdb=superdb;
			this.comment=comment;
			this.text=text;
		}
		public void onClick(ClickEvent event) {
			if (editorAdministration == null) {
				editorAdministration = ClientsideSettings.getAdministration();
			}
			editorAdministration.updateComment(comment, db.getCommentBox().getValue(), new AsyncCallback<Comment>(){

				@Override
				public void onFailure(Throwable caught) {
					ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());
					
				}

				@Override
				public void onSuccess(Comment result) {
					db.hide();
					superdb.show();
					superdb.refresh(result.getPostId());
					
					
				}
				
			});
			
		}
	}
	
	}
public static class AlertDialogBox extends DialogBox{
		
		/** Diverse Attribute und GWT Elemente die zur Realisierung der AlertDialogBox benötigt werden. */
		Label textLbl = new Label();
		CloseButton okBtn = new CloseButton(this); 
		VerticalPanel panel = new VerticalPanel();
		
		/**
		 * Instanziert eine AlertDialogBox, welche genutzt wird um einen neuen Nutzer anzulegen.
		 *
		 * @param text String Inhant von dem was angezeigt werden soll
		 */
		public AlertDialogBox(String text) {
			textLbl.setText(text);
			panel.add(textLbl);
			okBtn.addClickHandler(new CloseDBClickHandler(this));
			panel.add(okBtn);
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
}
	}
