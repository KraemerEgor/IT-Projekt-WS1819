package de.hdm.itp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class HeaderPanelReport  extends HorizontalPanel {
	
	private Button editorBtn = new Button("Feed");
	private Anchor editorLink = new Anchor("editor");
	
	Label header_lbl = new Label("Das ist ein Label vom HeaderPanel");
	
	public void onLoad() {
		
		super.onLoad();
		this.setStylePrimaryName("HeaderReport");
		
		
		
		editorBtn.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				editorLink.setHref(GWT.getHostPageBaseURL() + "IT_Projekt_Gruppe4_2.html");
				Window.open(editorLink.getHref(), "_blank", "");
				}
			});

		
	//	this.setWidth("100%");
	//	this.setHeight("220px");
	//	this.getElement().getStyle().setBackgroundColor("grey");
		
		header_lbl.addStyleName("label_test");
	//	header_lbl.setWidth("210px");
		this.add(editorBtn);
		this.add(header_lbl);
		this.add(header_lbl);
		
	
	}
}
