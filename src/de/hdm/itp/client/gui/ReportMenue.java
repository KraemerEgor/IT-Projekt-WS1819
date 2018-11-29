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

import java.util.Date;
import java.util.logging.Logger;

import de.hdm.itp.shared.*;

import de.hdm.itp.client.ClientsideSettings;
import de.hdm.itp.client.gui.report.AllCommentsFromUserReportForm;
import de.hdm.itp.client.gui.report.AllLikesFromUserReportForm;
import de.hdm.itp.client.gui.report.AllPostsFromUserReportForm;
import de.hdm.itp.client.gui.report.AllSubsFromUserReportForm;
import de.hdm.itp.client.gui.report.AllSubsOfUserReportForm;
import de.hdm.itp.shared.ReportGeneratorAsync;
import de.hdm.itp.shared.bo.User;
import de.hdm.itp.shared.report.*;

public class ReportMenue implements EntryPoint {

	private static ReportGeneratorAsync reportGenerator = null;

	private User u = new User();

	/*
	 * 5 button erstellen in einem drop down menue AllSubsFromUserReport
	 * AllSubsOfUserReport AllCommentsFromUserReport AllLikesFromUserReport
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

		reportGenerator.setUser(u, new setUserCallback());

		final ListBox listBox = new ListBox();
		final VerticalPanel VerticalPanel = new VerticalPanel();
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
		// checkBox.

		Button searchButton = new Button("Search");

		// datebox
		DateBox dateFrom = new DateBox();
		dateFrom.setValue(new Date());

		DateBox dateTill = new DateBox();
		dateTill.setValue(new Date());

		// checkbox
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

		// button
		searchButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				final int lbIndex = listBox.getSelectedIndex();
				if (lbIndex == 0) {

					VerticalPanel.add(new AllSubsFromUserReportForm(u));

				} else if (lbIndex == 1) {

					VerticalPanel.add(new AllSubsOfUserReportForm(u));

				} else if (lbIndex == 2) {

					VerticalPanel.add(new AllCommentsFromUserReportForm(u));

				} else if (lbIndex == 3) {
					VerticalPanel.add(new AllLikesFromUserReportForm(u));

				} else if (lbIndex == 4) {

					VerticalPanel.add(new AllPostsFromUserReportForm(u));

				} else {
					Window.alert("non of the selected ones");
				}
			}
		});

		/*
		 * RootPanel.get().add(lb); RootPanel.get().add(btn1);
		 * RootPanel.get("content").add(resultPanel);
		 */

		RootPanel.get().add(VerticalPanel);
		VerticalPanel.add(listBox);

		VerticalPanel.add(searchButton);

		VerticalPanel.add(checkBoxShowAll);
		VerticalPanel.add(checkBoxPickDate);

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
