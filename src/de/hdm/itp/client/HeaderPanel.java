package de.hdm.itp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * The Class HeaderPanel.
 */
public class HeaderPanel extends HorizontalPanel {
	
	/** The header Label. 
	 * A Label for styling and testing */
	private Label header_lbl = new Label("Das ist ein Label vom HeaderPanel");
	
	/** The report btn. 
	 * A Button which refers to the Report Generator*/
	private Button reportBtn = new Button("Report-Generator");
	
	/** The report link. 
	 * An Anchor which refers to the Report Generator*/
	private Anchor reportLink = new Anchor("Report");
	
	
	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onLoad()
	 */
	/** Diese Klasse wird automatisch aufgerufen, wenn das HeaderPanel instanziiert wird
	 * Darin werden den Buttons css-Styles hinzugefügt 
	 * ,der Link zum Reportgenerator hinzugefügt
	 * und ein ClickHandler welcher zum Report-Generator navigiert */
	public void onLoad() {
		
		super.onLoad();
		this.addStyleName("Head");
		
		this.add(reportBtn);
		reportBtn.setStylePrimaryName("report");
		reportBtn.setStyleDependentName("button", true);
		
		reportBtn.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				reportLink.setHref(GWT.getHostPageBaseURL() + "FeedReports.html");
				Window.open(reportLink.getHref(), "_blank", "");
				}
			});
		
		
	
		
		
	
		
	
	}

}
