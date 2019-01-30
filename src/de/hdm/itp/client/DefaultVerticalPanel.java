package de.hdm.itp.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.VerticalPanel;


/**
 * The Class DefaultVerticalPanel.
 */
public class DefaultVerticalPanel extends VerticalPanel{
	
	/** The newvp. */
	VerticalPanel newvp = new VerticalPanel();
	
	/**
	 * Instantiates a new default vertical panel.
	 *
	 * @param vp the vp
	 */
	public DefaultVerticalPanel(VerticalPanel vp) {
		newvp = vp;
		//this.add(newvp);
		
	}

}
