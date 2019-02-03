package de.hdm.itp.client;

import java.util.Date;

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

import de.hdm.itp.client.gui.report.AllCommentsFromUserReportForm;
import de.hdm.itp.client.gui.report.AllCommentsOfAllPostsFromUserReportForm;
import de.hdm.itp.client.gui.report.AllLikesFromUserReportForm;
import de.hdm.itp.client.gui.report.AllPostsFromUserReportForm;
import de.hdm.itp.client.gui.report.AllSubsFromUserReportForm;
import de.hdm.itp.client.gui.report.AllSubsOfUserReportForm;
import de.hdm.itp.shared.ReportGeneratorAsync;
import de.hdm.itp.shared.bo.User;

/**
 * The Class NavPanelReport.
 * Diese Klasse beinhaltet die einzelnen Buttons zum Anzeigen der einzelnen Reports.
 * Außerdem werden die Checkboxes hinzugefügt, über die ausgewählt werden kann, ob alle Business Objects des Users
 * angezeigt werden sollen oder nur die aus einem bestimmten Zeitraum, der über die Dateboxes gewählt wird.
 * @author: Nils Kaper, Leonard Schwenk
 */
public class NavPanelReport extends VerticalPanel {
	
//	final VerticalPanel resultPanel = new VerticalPanel();
	
	
	/** The result panel. */
MainPanelReport resultPanel = new MainPanelReport();

	
	/** The report generator. */
	private static ReportGeneratorAsync reportGenerator = null;

	
	/** The u. */
	private User u = new User();
	
	
	
	/** The All subs from user report btn. */
	final Button AllSubsFromUserReportBtn = new Button("Alle Ihre Abonnenten");
	
	/** The All subs of user report btn. */
	final Button AllSubsOfUserReportBtn = new Button("Alle Ihre Abonnements");
	
	/** The All comments from user report btn. */
	final Button AllCommentsFromUserReportBtn = new Button("Alle Ihre Kommentare");
	
	/** The All likes from user report btn. */
	final Button AllLikesFromUserReportBtn = new Button("Alle Ihre Likes");
	
	/** The All posts from user report btn. */
	final Button AllPostsFromUserReportBtn = new Button("Alle Ihre Beiträge");

	/** The All subs from user between dates report btn. */
	final Button AllSubsFromUserBetweenDatesReportBtn = new Button("Alle Ihre Abonnenten im angegebenen Zeitraum");
	
	/** The All subs of user between dates report btn. */
	final Button AllSubsOfUserBetweenDatesReportBtn = new Button("Alle Ihre Abonnements im angegebenen Zeitraum");
	
	/** The All comments from user between dates report btn. */
	final Button AllCommentsFromUserBetweenDatesReportBtn = new Button("Alle Ihre Kommentare im angegebenen Zeitraum");
	
	/** The All likes from user between dates report btn. */
	final Button AllLikesFromUserBetweenDatesReportBtn = new Button("Alle Ihre Likes im angegebenen Zeitraum");
	
	/** The All posts from user between dates report btn. */
	final Button AllPostsFromUserBetweenDatesReportBtn = new Button("Alle Ihre Beiträge im angegebenen Zeitraum");
	
	/** The check box show all. */
	final CheckBox checkBoxShowAll = new CheckBox("Suchanfrage Alle");
	
	/** The check box pick date. */
	final CheckBox checkBoxPickDate = new CheckBox("Suchanfrage nach Zeitraum");
	
	/** The date from. */
	final DateBox dateFrom = new DateBox();
	
	/** The date till. */
	final DateBox dateTill = new DateBox();
	
	/** The lbl from. */
	final Label lblFrom = new Label("Von");
	
	/** The lbl till. */
	final Label lblTill = new Label("Bis");

	
	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onLoad()
	 */
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("NavReport");
		
		
		AllSubsFromUserReportBtn.setStylePrimaryName("submit");
		AllSubsOfUserReportBtn.setStylePrimaryName("submit");
		AllCommentsFromUserReportBtn.setStylePrimaryName("submit");
		AllLikesFromUserReportBtn.setStylePrimaryName("submit");
		AllPostsFromUserReportBtn.setStylePrimaryName("submit");
		
		
		AllSubsFromUserBetweenDatesReportBtn.setStylePrimaryName("submit");
		AllSubsFromUserBetweenDatesReportBtn.setStyleDependentName("time", true);
		
		AllSubsOfUserBetweenDatesReportBtn.setStylePrimaryName("submit");
		AllSubsOfUserBetweenDatesReportBtn.setStyleDependentName("time", true);
		
		AllCommentsFromUserBetweenDatesReportBtn.setStylePrimaryName("submit");
		AllCommentsFromUserBetweenDatesReportBtn.setStyleDependentName("time", true);
		
		AllLikesFromUserBetweenDatesReportBtn.setStylePrimaryName("submit");
		AllLikesFromUserBetweenDatesReportBtn.setStyleDependentName("time", true);

		AllPostsFromUserBetweenDatesReportBtn.setStylePrimaryName("submit");
		AllPostsFromUserBetweenDatesReportBtn.setStyleDependentName("time", true);

		/**
		 * hinzufügen der CheckBoxes zum NavPanel
		 * @author Leonard Schwenk
		 */
		
		this.add(checkBoxShowAll);
		this.add(checkBoxPickDate);
		
		this.add(lblFrom);
		this.add(dateFrom);
		this.add(lblTill);
		this.add(dateTill);
		
		this.add(AllSubsFromUserReportBtn);
		this.add(AllSubsOfUserReportBtn);
		this.add(AllCommentsFromUserReportBtn);
		this.add(AllLikesFromUserReportBtn);
		this.add(AllPostsFromUserReportBtn);

		this.add(AllSubsFromUserBetweenDatesReportBtn);
		this.add(AllSubsOfUserBetweenDatesReportBtn);
		this.add(AllCommentsFromUserBetweenDatesReportBtn);
		this.add(AllLikesFromUserBetweenDatesReportBtn);
		this.add(AllPostsFromUserBetweenDatesReportBtn);

		
		if (reportGenerator == null) {

			reportGenerator = ClientsideSettings.getReportGenerator();
		}

	
		u = ClientsideSettings.getUser();
		
		reportGenerator.setUser(u, new setUserCallback());

		/**
		 * Die  checkBoxShowAll wird bei laden des NavPanels per default gesetzt und somit werden die DateBoxes inital NULL gesetzt.
		 * Bis die checkBoxPickDate ausgewält und die DateBoxes per default mit einem Timestamp besetzt werden.  
		 * @author Leonard Schwenk
		 */

		checkBoxShowAll.setValue(true);
		
		/**
		 * setzen des dateFormats
		 * @author Leonard Schwenk
		 */
		
		dateFrom.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("dd.MM.yyyy"))); 
		dateTill.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("dd.MM.yyyy"))); 


		dateFrom.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
			}
		});

		
		dateTill.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
			}
		});
		
		lblFrom.setVisible(false);
		lblTill.setVisible(false);
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
				lblFrom.setVisible(false);
				lblTill.setVisible(false);
				dateFrom.setVisible(false);
				dateTill.setVisible(false);
				
				dateFrom.setValue(null);
				dateTill.setValue(null);

				
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
				
				
				// setting default value for DateFrom and DateTill

				lblFrom.setVisible(true);
				lblTill.setVisible(true);
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

		/**
		 * Hinzufügen der AllReportsButtons mit dem zugehörtigen ClickHanlder
		 * @author Leonard Schwenk
		 */
		
		
		
		AllSubsFromUserReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllSubsFromUserReportForm(u,  dateFrom.getValue(),  dateTill.getValue()));
				RootPanel.get().add(resultPanel);
				
			}
		});
		AllSubsOfUserReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllSubsOfUserReportForm(u,  dateFrom.getValue(),  dateTill.getValue()));

				RootPanel.get().add(resultPanel);
				
			}
		});
		AllCommentsFromUserReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				resultPanel.clear();
				resultPanel.add(new AllCommentsOfAllPostsFromUserReportForm(u, dateFrom.getValue(), dateTill.getValue())); 
				RootPanel.get().add(resultPanel);	
				
			}
			
		});
	
		AllLikesFromUserReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
			
				
				resultPanel.clear();
				resultPanel.add(new AllLikesFromUserReportForm(u,  dateFrom.getValue(),  dateTill.getValue()));
				RootPanel.get().add(resultPanel);
				
			}
		});
		AllPostsFromUserReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllPostsFromUserReportForm(u,  dateFrom.getValue(),  dateTill.getValue()));
				RootPanel.get().add(resultPanel);

				
			}
		});
		
		/**
		 * Hinzufügen der AllReportsBetweenButtons mit dem zugehörtigen ClickHanlder, hier wird das ausgewählte date der DateBoxen hinzugefügt.
		 * @author Leonard Schwenk
		 */
		
		AllSubsFromUserBetweenDatesReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllSubsFromUserReportForm(u,  dateFrom.getValue(),  dateTill.getValue()));
				RootPanel.get().add(resultPanel);
				
			}
		});
		AllSubsOfUserBetweenDatesReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllSubsOfUserReportForm(u,  dateFrom.getValue(),  dateTill.getValue()));

				RootPanel.get().add(resultPanel);
				
			}
		});
		AllCommentsFromUserBetweenDatesReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllCommentsOfAllPostsFromUserReportForm(u, dateFrom.getValue(), dateTill.getValue()));  //new AllCommentsFromUserReportForm(u, dateFrom.getValue(), dateTill.getValue()));
				RootPanel.get().add(resultPanel);
				
			}
		});
		AllLikesFromUserBetweenDatesReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllLikesFromUserReportForm(u,  dateFrom.getValue(),  dateTill.getValue()));
				RootPanel.get().add(resultPanel);
				
			}
		});
		AllPostsFromUserBetweenDatesReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllPostsFromUserReportForm(u,  dateFrom.getValue(),  dateTill.getValue()));
				RootPanel.get().add(resultPanel);

				
			}
		});
		
		
		
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

	}

}

