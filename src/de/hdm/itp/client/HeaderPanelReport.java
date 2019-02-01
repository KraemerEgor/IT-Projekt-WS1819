package de.hdm.itp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * The Class HeaderPanelReport.
 */
public class HeaderPanelReport  extends HorizontalPanel {
	
	/** The editor btn. */
	private Button editorBtn = new Button("Feed");
	
	/** The editor link. */
	private Anchor editorLink = new Anchor("editor");
	
	private Image logo = new Image("logo_new.png");
	
	private Label headerLabel = new Label("Report-Generator");
	
	
	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onLoad()
	 */
	public void onLoad() {
		
		super.onLoad();
		this.setStylePrimaryName("header");
		
		
		editorBtn.setStylePrimaryName("feed");
		editorBtn.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				editorLink.setHref(GWT.getHostPageBaseURL() + "IT_Projekt_Gruppe4_2.html");
				Window.open(editorLink.getHref(), "_blank", "");
				}
			});
		
		
		logo.addStyleName("logo");
		this.add(logo);
		
		headerLabel.setStylePrimaryName("headerLbl");
		this.add(headerLabel);

		
	//	this.setWidth("100%");
	//	this.setHeight("220px");
	//	this.getElement().getStyle().setBackgroundColor("grey");
		
	//	header_lbl.setWidth("210px");
		this.add(editorBtn);
		
	
		
	
	}
}
