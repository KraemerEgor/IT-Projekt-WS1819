package de.hdm.itp.client.gui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
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
import de.hdm.itp.client.gui.report.ReportResultPanel;
import de.hdm.itp.shared.ReportGeneratorAsync;
import de.hdm.itp.shared.bo.User;
import de.hdm.itp.shared.report.AllCommentsFromUserReport;
import de.hdm.itp.shared.report.HTMLReportWriter;

public class ReportMenue implements EntryPoint {

	private static ReportGeneratorAsync reportGenerator = null;

	private User u = new User();


	public void onModuleLoad() {

		u.setId(10000001);

		if (reportGenerator == null) {

			reportGenerator = ClientsideSettings.getReportGenerator();
		}

		reportGenerator.setUser(u, new setUserCallback());

		final ListBox searchAllListBox = new ListBox();
		final ListBox pickDateListBox = new ListBox();

		final VerticalPanel VerticalPanel = new VerticalPanel();
		final VerticalPanel resultPanel = new VerticalPanel();

		final Label text = new Label();

		final CheckBox checkBoxShowAll = new CheckBox("ShowAll");
		final CheckBox checkBoxPickDate = new CheckBox("PickDate");

		searchAllListBox.addItem("AllSubsFromUserReport0");
		searchAllListBox.addItem("AllSubsOfUserReport01");
		searchAllListBox.addItem("AllCommentsFromUserReport02");
		searchAllListBox.addItem("AllLikesFromUserReport03");
		searchAllListBox.addItem("AllPostsFromUserReport04");

		pickDateListBox.addItem("AllSubsFromUserReportBetween");
		pickDateListBox.addItem("AllSubsOfUserReportBetween");
		pickDateListBox.addItem("AllCommentsFromUserReportBetween");
		pickDateListBox.addItem("AllLikesFromUserReportBetween");
		pickDateListBox.addItem("AllPostsFromUserReportBetween");

		checkBoxShowAll.setValue(true);
		// checkBox.

		Button searchButton = new Button("Search");

		// datebox
		final DateBox dateFrom = new DateBox();
		dateFrom.setValue(new Date());

		dateFrom.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
//			        Window.alert(dateFrom.getValue().toString());
			}
		});

		DateBox dateTill = new DateBox();
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

		// checkbox

		checkBoxShowAll.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				checkBoxShowAll.setValue(true);
				pickDateListBox.setVisible(false);
				searchAllListBox.setVisible(true);
				dateFrom.setVisible(false);
				dateTill.setVisible(false);
				dateFrom.setValue(null);
				dateTill.setValue(null);
				
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
				pickDateListBox.setVisible(true);
				searchAllListBox.setVisible(false);
				VerticalPanel.add(pickDateListBox);
				dateFrom.setVisible(true);
				dateTill.setVisible(true);
	
				
				if (checkBoxShowAll.getValue() == true) {
					checkBoxShowAll.setValue(false);

				} else {
					Window.alert("You can just enable one Checkbox");

				}
			}
		});

		// button
		searchButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				final int lbIndex = searchAllListBox.getSelectedIndex();
				if (lbIndex == 0) {

					resultPanel.clear();
					resultPanel.add(new AllSubsFromUserReportForm(u));
					RootPanel.get().add(resultPanel);
					
					
					Window.alert(dateFrom.getValue() + "dateFrom hat folgenden Wert");

				} else if (lbIndex == 1) {
					resultPanel.clear();
					resultPanel.add(new AllSubsOfUserReportForm(u));

					RootPanel.get().add(resultPanel);

				} else if (lbIndex == 2) {
					resultPanel.clear();
			//		resultPanel.add(new AllCommentsFromUserReportForm(u));
					RootPanel.get().add(resultPanel);

				} else if (lbIndex == 3) {
					resultPanel.clear();
					resultPanel.add(new AllLikesFromUserReportForm(u));
					RootPanel.get().add(resultPanel);

				} else if (lbIndex == 4) {
					resultPanel.clear();
					resultPanel.add(new AllPostsFromUserReportForm(u));
					RootPanel.get().add(resultPanel);

				} else {
					Window.alert("non of the selected ones");
				}
			}
		});
		
		
		//pickDateListBox
//		searchButton.addClickHandler(new ClickHandler() {
//
//			public void onClick(ClickEvent event) {
//				final int lbIndex = pickDateListBox.getSelectedIndex();
//				if (lbIndex == 0) {
//
//					resultPanel.clear();
////					resultPanel.add(new AllCommentsFromUserReportBetweenDatesForm(u, dateFrom.getValue(),dateTill.getValue()));
//					RootPanel.get().add(resultPanel);
//					Window.alert(dateFrom.getValue().toString());
//
//				} else if (lbIndex == 1) {
//					Window.alert("nothing here yet, try it later again!");
//
//				} else if (lbIndex == 2) {
//					Window.alert("nothing here yet, try it later again!");
//				} else if (lbIndex == 3) {
//					Window.alert("nothing here yet, try it later again!");
//				} else if (lbIndex == 4) {
//					Window.alert("nothing here yet, try it later again!");
//				} else {
//					Window.alert("non of the selected ones");
//				}
//			}
//		});

		/*
		 * RootPanel.get().add(lb); RootPanel.get().add(btn1);
		 * RootPanel.get("content").add(resultPanel);
		 */

		VerticalPanel.add(checkBoxShowAll);
		VerticalPanel.add(checkBoxPickDate);

		RootPanel.get().add(VerticalPanel);
		VerticalPanel.add(searchAllListBox);

		VerticalPanel.add(searchButton);

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
