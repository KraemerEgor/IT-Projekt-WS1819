package de.hdm.itp.client;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safecss.shared.SafeStyles;
import com.google.gwt.safecss.shared.SafeStylesUtils;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import de.hdm.itp.shared.bo.User;

	public class SubscCell extends AbstractCell<User> {
   
	    public void render(Context context, User value, SafeHtmlBuilder sb) {
	    
	      if (value == null) {
	        return;
	      }
	      
	      sb.appendHtmlConstant("<div class=\"subs\" style=\"background-color: white; padding: 5px; padding-right: 10px;  margin-top: 3px; color:brown ;border: none; font-size:16px;font-family: 'Arial', sans-serif;\">");
	      if(value.getGender()=="m") {
				//wenn der User männlich ist
				sb.appendHtmlConstant("<img src=\"man.png\" style=\"margin-right: 10px;\" height=\"20\" width=\"20\">");	
				
			}
			if(value.getGender()=="f") {
				//wenn der User weiblich ist
				sb.appendHtmlConstant("<img src=\"girl.png\" style=\"margin-right: 10px;\" height=\"20\" width=\"20\">");	
				
			}
			if(value.getGender()=="o") {
				//wenn der User anders ist
				sb.appendHtmlConstant("<img src=\"user_Symbol_other.png\" style=\"margin-right: 10px;\" height=\"20\" width=\"08\" >");	
				
			}
	      sb.appendHtmlConstant(value.getFirstname()+" '"+value.getNickname()+"' "+value.getLastname());
	      sb.appendHtmlConstant("</div>");
	      
	      
	      
	    }
	}

		



