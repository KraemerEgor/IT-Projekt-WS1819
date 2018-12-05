package de.hdm.itp.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;





public class SearchPanel extends FlowPanel {
	
	Label header_lbl = new Label("Test"); 
	Button profile_btn = new Button("My Profile", new ClickHandler() {
		public void onClick(ClickEvent event) {
			Window.alert("Funktioniert!");
			}
		});
	Button add_btn = new Button("Add", new ClickHandler() {
		public void onClick(ClickEvent event) {
			Window.alert("Klappt!");
			}
		});

	
	
	
	public void onLoad() {
		
		super.onLoad();
		this.addStyleName("Search");			
			header_lbl.addStyleName("label_test");
		this.add(header_lbl);
		this.add(profile_btn);
		this.add(add_btn);
		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
		SuggestBox suggestbox = new SuggestBox(oracle);	
			oracle.add("John");
			oracle.add("Lisa");
			oracle.add("Kevin");
			oracle.add("Sarafina");
		
		this.add(suggestbox);
	}
	
}
