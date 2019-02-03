package de.hdm.itp.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.VerticalPanel;


/**
 * The Class DefaultVerticalPanel.
 * Dies ist eine Helferklasse, welche erstellt wurde, um im Debug Mode für klarheit zu sorgen,
 * diese Erfüllt keinen spzifischen Zweck mehr
 * Dieses Panel enthält ein Vertical Panel
 */
public class DefaultVerticalPanel extends VerticalPanel{
	
	/** The newvp. Ein Vertical Panel */
	VerticalPanel newvp = new VerticalPanel();
	
	/**
	 * Instantiates a new default vertical panel.
	 *
	 * @param vp the VerticalPanel inside the DefaultPanel
	 */
	public DefaultVerticalPanel(VerticalPanel vp) {
		newvp = vp;
		//this.add(newvp);
		
	}

}
