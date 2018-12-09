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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.client.ui.ListBox;

import java.util.Date;

import de.hdm.itp.client.ClientsideSettings;
import de.hdm.itp.client.gui.report.AllCommentsFromUserReportForm;
import de.hdm.itp.client.gui.report.AllLikesFromUserReportForm;
import de.hdm.itp.client.gui.report.AllPostsFromUserReportForm;
import de.hdm.itp.client.gui.report.AllSubsFromUserReportForm;
import de.hdm.itp.client.gui.report.AllSubsOfUserReportForm;
import de.hdm.itp.shared.ReportGeneratorAsync;
import de.hdm.itp.shared.bo.User;

public class ReportMenue implements EntryPoint {

	private static ReportGeneratorAsync reportGenerator = null;

	private User u = new User();


	public void onModuleLoad() {

		u.setId(10000001);

		if (reportGenerator == null) {

			reportGenerator = ClientsideSettings.getReportGenerator();
		}

		reportGenerator.setUser(u, new setUserCallback());
		
		final Button AllSubsFromUserReportBtn = new Button("AllSubsFromUserReport");
		final Button AllSubsOfUserReportBtn = new Button("AllSubsOfUserReport");
		final Button AllCommentsFromUserReportBtn = new Button("AllCommentsFromUserReport");
		final Button AllLikesFromUserReportBtn = new Button("AllLikesFromUserReport");
		final Button AllPostsFromUserReportBtn = new Button("AllPostsFromUserReport");

		final Button AllSubsFromUserBetweenDatesReportBtn = new Button("SubsFromUserBetweenDates");
		final Button AllSubsOfUserBetweenDatesReportBtn = new Button("SubsOfUserBetweenDates");
		final Button AllCommentsFromUserBetweenDatesReportBtn = new Button("CommentsFromUserBetweenDates");
		final Button AllLikesFromUserBetweenDatesReportBtn = new Button("LikesFromUserBetweenDates");
		final Button AllPostsFromUserBetweenDatesReportBtn = new Button("PostsFromUserBetweenDates");

		
		final VerticalPanel VerticalPanel = new VerticalPanel();
		final VerticalPanel resultPanel = new VerticalPanel();

		final Label text = new Label();

		final CheckBox checkBoxShowAll = new CheckBox("ShowAll");
		final CheckBox checkBoxPickDate = new CheckBox("PickDate");


		checkBoxShowAll.setValue(true);
		// checkBox.


		// datebox
		final DateBox dateFrom = new DateBox();
		final DateBox dateTill = new DateBox();
		
		
		dateFrom.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat(" dd, MMMM, yyyy"))); 
		dateTill.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat(" dd, MMMM, yyyy"))); 


		dateFrom.setValue(new Date());

		dateFrom.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
//			        Window.alert(dateFrom.getValue().toString());
			}
		});

		dateTill.setValue(new Date());

		dateTill.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
//		        Window.alert(dateTill.getValue().toString());
			}
		});
		
		dateFrom.setVisible(false);
		dateTill.setVisible(false);
		
		dateFrom.setValue(null);
		dateTill.setValue(null);
		
		AllSubsFromUserBetweenDatesReportBtn.setVisible(false);
		AllSubsOfUserBetweenDatesReportBtn.setVisible(false);
		AllCommentsFromUserBetweenDatesReportBtn.setVisible(false);
		AllLikesFromUserBetweenDatesReportBtn.setVisible(false);
		AllPostsFromUserBetweenDatesReportBtn.setVisible(false);

		// checkbox
		
		

		checkBoxShowAll.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				checkBoxShowAll.setValue(true);
				dateFrom.setVisible(false);
				dateTill.setVisible(false);
				
				AllSubsFromUserReportBtn.setVisible(true);
				AllSubsOfUserReportBtn.setVisible(true);
				AllCommentsFromUserReportBtn.setVisible(true);
				AllLikesFromUserReportBtn.setVisible(true);
				AllPostsFromUserReportBtn.setVisible(true);
				
				AllSubsFromUserBetweenDatesReportBtn.setVisible(false);
				AllSubsOfUserBetweenDatesReportBtn.setVisible(false);
				AllCommentsFromUserBetweenDatesReportBtn.setVisible(false);
				AllLikesFromUserBetweenDatesReportBtn.setVisible(false);
				AllPostsFromUserBetweenDatesReportBtn.setVisible(false);

				
				
				
				if (checkBoxPickDate.getValue() == true) {
					checkBoxPickDate.setValue(false);

				} else {
					Window.alert("You can just enable one Checkbox");

				}
			}
		});
		checkBoxPickDate.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				checkBoxPickDate.setValue(true);
				dateFrom.setVisible(true);
				dateTill.setVisible(true);
				
				dateFrom.setValue(new Date());
				dateTill.setValue(new Date());
				
				AllSubsFromUserBetweenDatesReportBtn.setVisible(true);
				AllSubsOfUserBetweenDatesReportBtn.setVisible(true);
				AllCommentsFromUserBetweenDatesReportBtn.setVisible(true);
				AllLikesFromUserBetweenDatesReportBtn.setVisible(true);
				AllPostsFromUserBetweenDatesReportBtn.setVisible(true);
				
			
				AllSubsFromUserReportBtn.setVisible(false);
				AllSubsOfUserReportBtn.setVisible(false);
				AllCommentsFromUserReportBtn.setVisible(false);
				AllLikesFromUserReportBtn.setVisible(false);
				AllPostsFromUserReportBtn.setVisible(false);


				
				if (checkBoxShowAll.getValue() == true) {
					checkBoxShowAll.setValue(false);

				} else {
					Window.alert("You can just enable one Checkbox");

				}
			}
		});

		// button
		
		AllSubsFromUserReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllSubsFromUserReportForm(u));
				RootPanel.get().add(resultPanel);
				
			}
		});
		AllSubsOfUserReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllSubsOfUserReportForm(u));

				RootPanel.get().add(resultPanel);
				
			}
		});
		AllCommentsFromUserReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				
				 		//TODO Comment methode mit date schreiben 
						resultPanel.add(new AllCommentsFromUserReportForm(u, dateFrom.getValue(), dateTill.getValue()));	// dateFrom.getValue(), dateTill.getValue()));
						
						Window.alert(dateTill.getValue().toString());
						RootPanel.get().add(resultPanel);
				
			}
		});
		AllLikesFromUserReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllLikesFromUserReportForm(u));
				RootPanel.get().add(resultPanel);
				
			}
		});
		AllPostsFromUserReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllPostsFromUserReportForm(u));
				RootPanel.get().add(resultPanel);

				
			}
		});
		
		//#########between button
		
		AllSubsFromUserBetweenDatesReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllSubsFromUserReportForm(u));
				RootPanel.get().add(resultPanel);
				
			}
		});
		AllSubsOfUserBetweenDatesReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllSubsOfUserReportForm(u));

				RootPanel.get().add(resultPanel);
				
			}
		});
		AllCommentsFromUserBetweenDatesReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				//		resultPanel.add(new AllCommentsFromUserReportForm(u));
						resultPanel.add(new AllCommentsFromUserReportForm(u, dateFrom.getValue(), dateTill.getValue()));	// dateFrom.getValue(), dateTill.getValue()));
						
						Window.alert(dateTill.getValue().toString());
						RootPanel.get().add(resultPanel);
				
			}
		});
		AllLikesFromUserBetweenDatesReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllLikesFromUserReportForm(u));
				RootPanel.get().add(resultPanel);
				
			}
		});
		AllPostsFromUserBetweenDatesReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllPostsFromUserReportForm(u));
				RootPanel.get().add(resultPanel);

				
			}
		});
		
		



		VerticalPanel.add(checkBoxShowAll);
		VerticalPanel.add(checkBoxPickDate);

		RootPanel.get().add(VerticalPanel);
		
		VerticalPanel.add(AllPostsFromUserReportBtn);
		VerticalPanel.add(AllCommentsFromUserReportBtn);
		VerticalPanel.add(AllLikesFromUserReportBtn);
		VerticalPanel.add(AllSubsFromUserReportBtn);
		VerticalPanel.add(AllSubsOfUserReportBtn);
		
		VerticalPanel.add(AllSubsFromUserBetweenDatesReportBtn);
		VerticalPanel.add(AllSubsOfUserBetweenDatesReportBtn);
		VerticalPanel.add(AllCommentsFromUserBetweenDatesReportBtn);
		VerticalPanel.add(AllLikesFromUserBetweenDatesReportBtn);
		VerticalPanel.add(AllPostsFromUserBetweenDatesReportBtn);

		RootPanel.get().add(text);

		VerticalPanel.add(dateFrom);

		VerticalPanel.add(dateTill);

	}
}


class setUserCallback implements AsyncCallback<Void> {

	public void onFailure(Throwable caught) {
		/*
		 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
		 */
		ClientsideSettings.getLogger().severe("User wurde nicht gesetzt, etwas mit der DB stimmt nicht !");
	}

	@Override
	public void onSuccess(Void result) {
		/*
		 * Wir erwarten diesen Ausgang, wollen aber keine Notifikation ausgeben.
		 */
		Window.alert("User gesetzt und DB funktioniert!");

	}

}
