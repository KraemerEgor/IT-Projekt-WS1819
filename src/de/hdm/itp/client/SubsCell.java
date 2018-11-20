package de.hdm.itp.client;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.itp.shared.bo.Subs;
import de.hdm.itp.shared.bo.User;

public class SubsCell extends AbstractCell<User> {
	
	public void render(Context context, User value, SafeHtmlBuilder sb) {
		if (value == null) {
			return;
		}
		
		
		sb.appendHtmlConstant("<div id =\"subsCell\">");
		
			
			if(value.getGender()=="m") {
				//wenn der User männlich ist
				sb.appendHtmlConstant("<img src=\"user_Symbol_male.png\" id= \"itemSymbol\">");	
				
			}
			if(value.getGender()=="f") {
				//wenn der User weiblich ist
				sb.appendHtmlConstant("<img src=\"userFemale_Symbol_female.png\" id= \"itemSymbol\">");	
				
			}
			if(value.getGender()=="o") {
				//wenn der User anders ist
				sb.appendHtmlConstant("<img src=\"userOther_Symbol_other.png\" id= \"itemSymbol\">");	
				
			}
		sb.appendEscaped(" ");
		sb.appendEscaped(value.getFirstname());
		sb.appendHtmlConstant(" ");
		sb.appendEscaped(value.getNickname());
		sb.appendHtmlConstant(" ");
		sb.appendEscaped(value.getLastname());
		sb.appendHtmlConstant("</div>");
		
	

}}
