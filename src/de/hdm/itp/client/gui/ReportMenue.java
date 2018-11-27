package de.hdm.itp.client.gui;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.client.ui.ListBox;
import de.hdm.itp.shared.report.AllPostsFromUserReport;


import java.util.Date;
import java.util.logging.Logger;

import de.hdm.itp.client.ClientsideSettings;
import de.hdm.itp.shared.ReportGeneratorAsync;
import de.hdm.itp.shared.bo.User;
import de.hdm.itp.shared.report.*;

public class ReportMenue implements EntryPoint{

	private static ReportGeneratorAsync reportGenerator = null;
	
	
	private User u = new User();
	
	/* 
	 * 5 button erstellen in einem drop down menue 
	 * AllSubsFromUserReport
	 * AllSubsOfUserReport
	 * AllCommentsFromUserReport
	 * AllLikesFromUserReport
	 * AllPostsFromUserReport
	 */
	
	public void onModuleLoad() {
		
		u.setId(10000001);
		
	/*	
	 * 
	 */
	  if (reportGenerator == null) {
	 
			reportGenerator = ClientsideSettings.getReportGenerator();
		}
	
	  reportGenerator.setUser(u,new setUserCallback());
		
		
		final ListBox listBox = new ListBox();
		final HorizontalPanel HorizontalPanel = new HorizontalPanel();
	    final Label text = new Label();



		final CheckBox checkBoxShowAll = new CheckBox("ShowAll");
		final CheckBox checkBoxPickDate = new CheckBox("PickDate");
		
		final DatePicker datePicker = new DatePicker();



		listBox.addItem("AllSubsFromUserReport0");
		listBox.addItem("AllSubsOfUserReport01");
		listBox.addItem("AllCommentsFromUserReport02");
		listBox.addItem("AllLikesFromUserReport03");
		listBox.addItem("AllPostsFromUserReport04");
		
		
		checkBoxShowAll.setValue(true);
		//checkBox.
		
		Button btn1 = new Button("Search");
		
		
		
		//datebox
		DateBox dateFrom = new DateBox();
		dateFrom.setValue(new Date());
		
		DateBox dateTill = new DateBox();
		dateTill.setValue(new Date());
		
		//checkbox
		checkBoxShowAll.addClickHandler(new ClickHandler() {
		      
		      public void onClick(ClickEvent event) {
		        boolean checked = ((CheckBox) event.getSource()).getValue();
		        
		        Window.alert("It is " + (checked ? "" : "not ") + "checked");
		      }
		    });
		checkBoxPickDate.addClickHandler(new ClickHandler() {
			
		      public void onClick(ClickEvent event) {
		        boolean checked = ((CheckBox) event.getSource()).getValue();
		        Window.alert("It is " + (checked ? "" : "not ") + "checked");
		      }
		    });
		
		//button
		btn1.addClickHandler(new ClickHandler(){
			
		public void onClick (ClickEvent event) {
			final int lbIndex =listBox.getSelectedIndex();
			if(lbIndex == 0) {
				Window.alert("This is 0");
				
	
			}
			else if (lbIndex == 1) {
				Window.alert("This is 1");
				

			}
			else if (lbIndex == 2) {
				Window.alert("This is 2");

			}
			else if (lbIndex == 3) {
				Window.alert("This is 3");

			}
			else if (lbIndex == 4) {
				
				Window.alert("???");	

				reportGenerator.createAllCommentsFromUserReport(u, new createAllCommentsFromUserReportCallback());// .createAllPostsFromUserReport(postID, userID, new createAllPostsFromUserReportCallback());
				
				Window.alert("???");	

			}
			else {
				Window.alert("still not");	
			}
		}
		});
	
		/*
		 * RootPanel.get().add(lb);
		 * RootPanel.get().add(btn1);
		 * RootPanel.get("content").add(resultPanel);
		 */
		
		RootPanel.get().add(HorizontalPanel);
		HorizontalPanel.add(listBox);

		HorizontalPanel.add(btn1);
		

		HorizontalPanel.add(checkBoxShowAll);
		HorizontalPanel.add(checkBoxPickDate);
		
	    RootPanel.get().add(text);
	    
	    HorizontalPanel.add(dateFrom);
	    
	    HorizontalPanel.add(dateTill);
	
		
		
	}}
class setUserCallback implements AsyncCallback<Void> {

	
	public void onFailure(Throwable caught) {
		/*
		 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message
		 * aus.
		 */
		ClientsideSettings.getLogger().severe(
				"Setzen der Bank fehlgeschlagen!");
	}

	@Override
	public void onSuccess(Void result) {
		/*
		 * Wir erwarten diesen Ausgang, wollen aber keine Notifikation
		 * ausgeben.
		 */
		Window.alert("hier in der set Bank Classe");

	}

}

class createAllCommentsFromUserReportCallback implements AsyncCallback<AllCommentsFromUserReport> {

	@Override
	public void onFailure(Throwable caught) {
		
		ClientsideSettings.getLogger().severe("Erzeugen des Reports fehlgeschlagen!");
		Window.alert("Fehlgeschlagen");
		Window.alert(caught.getMessage());
		
	}

	public void onSuccess(AllCommentsFromUserReport report) {
		
		Window.alert("Klappt");

		if(report != null) {
			
			HTMLReportWriter writer = new HTMLReportWriter();
			writer.process(report);
			RootPanel.get().clear();
			RootPanel.get().add(new HTML(writer.getReportText()));
			
			Window.alert("Klappt");

			
		
	}
}

}
	
	class createAllPostsFromUserReportCallback implements AsyncCallback<AllPostsFromUserReport> {

		

		@Override
		public void onFailure(Throwable caught) {
			
			ClientsideSettings.getLogger().severe("Erzeugen des Reports fehlgeschlagen!");
			Window.alert("Fehlgeschlagen");
			
		}

		@Override
		public void onSuccess(AllPostsFromUserReport report) {
			
			Window.alert("Klappt");

			if(report != null) {
				
				HTMLReportWriter writer = new HTMLReportWriter();
				writer.process(report);
				RootPanel.get().clear();
				RootPanel.get().add(new HTML(writer.getReportText()));
				
				Window.alert("Klappt");

				
			
		}
	}
		
		
		}