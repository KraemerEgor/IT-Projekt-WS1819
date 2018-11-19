package de.hdm.itp.client.gui;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.ListBox;
import java.util.logging.Logger;

import de.hdm.itp.shared.ReportGeneratorAsync;
import de.hdm.itp.shared.bo.User;
import de.hdm.itp.shared.report.*;

public class ReportMenue implements EntryPoint{

	private static ReportGeneratorAsync reportGenerator = null;

	
	private int userID;
	private int postID;
	
	/* 
	 * 5 button erstellen in einem drop down menue 
	 * AllSubsFromUserReport
	 * AllSubsOfUserReport
	 * AllCommentsFromUserReport
	 * AllLikesFromUserReport
	 * AllPostsFromUserReport
	 */
	
	private VerticalPanel resultPanel = new VerticalPanel();

	
	public void onModuleLoad() {
		
	/*	if (reportGenerator == null) {
			reportGenerator = ClientsideSettings.getReportGenerator();
		}
	*/	
		
		final ListBox lb = new ListBox();
		
		
		lb.addItem("AllSubsFromUserReport0");
		lb.addItem("AllSubsOfUserReport01");
		lb.addItem("AllCommentsFromUserReport02");
		lb.addItem("AllLikesFromUserReport03");
		lb.addItem("AllPostsFromUserReport04");

		
		Button btn1 = new Button("Search");
		
		btn1.addClickHandler(new ClickHandler(){
			
		public void onClick (ClickEvent event) {
			final int lbIndex =lb.getSelectedIndex();
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
			//	reportGenerator.createAllPostsFromUserReport(postID, userID, new createAllPostsFromUserReportCallback());
				
			}
			else {
				Window.alert("still not");	
			}
		}
		});
		
		RootPanel.get().add(lb);
		RootPanel.get().add(btn1);
		RootPanel.get("content").add(resultPanel);

		
		
	}}
	


	
	class createAllPostsFromUserReportCallback implements AsyncCallback<AllPostsFromUserReport> {

		

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehlgeschlagen");
			
		}

		@Override
		public void onSuccess(AllPostsFromUserReport report) {
			if(report != null) {
				HTMLReportWriter writer = new HTMLReportWriter();
				writer.process(report);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(new HTML(writer.getReportText()));
				
			
		}
	}
	
	
}

