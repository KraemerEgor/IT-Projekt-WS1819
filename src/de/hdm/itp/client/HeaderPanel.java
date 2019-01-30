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
	
	/** The header lbl. */
	private Label header_lbl = new Label("Das ist ein Label vom HeaderPanel");
	
	/** The report btn. */
	private Button reportBtn = new Button("Report-Generator");
	
	/** The report link. */
	private Anchor reportLink = new Anchor("Report");
	
	
	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onLoad()
	 */
	public void onLoad() {
		
		super.onLoad();
		this.addStyleName("Head");
		
		this.add(reportBtn);
		reportBtn.setStylePrimaryName("report");
		
		reportBtn.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				reportLink.setHref(GWT.getHostPageBaseURL() + "FeedReports.html");
				Window.open(reportLink.getHref(), "_blank", "");
				}
			});
		
		
	
		
		
	
		
	
	}

}
