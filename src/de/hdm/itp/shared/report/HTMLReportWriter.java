package de.hdm.itp.shared.report;

import java.util.Vector;

/**
 */

public class HTMLReportWriter extends ReportWriter {

	private String reportText = "";

	/**
	 * Zurücksetzen der Variable ReportText.
	 */

	public void resetReportText() {
		this.reportText = "";
	}

	/**
	 * Paragraph Objekt in HTML konvertieren.
	 * 
	 * @param p der Paragraph
	 * 
	 * @return HTML-Text
	 */
	public String paragraph2HTML(Paragraph p) {
		if (p instanceof CompositeParagraph) {
			return this.paragraphToHTML((CompositeParagraph) p);
		} else {
			return this.paragraphToHTML((SimpleParagraph) p);
		}
	}

	/**
	 * CompositeParagraph Objekt in HTML konvertieren.
	 * 
	 * @param p CompositeParagraph
	 * 
	 * @return HTML-Text
	 */
	public String paragraphToHTML(CompositeParagraph p) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < p.getParagraphsSize(); i++) {
			result.append("<p>" + p.getParagraphByIndex(i) + "</p>");
		}
		return result.toString();
	}

	/**
	 * SimpleParagraph Objekt in HTML konvertieren.
	 * 
	 * @param p SimpleParagraph
	 * 
	 * @return HTML-Text
	 */
	public String paragraphToHTML(SimpleParagraph p) {
		return "<p>" + p.toString() + "</p>";
	}

	/**
	 * HTML-Header-Text erstellen.
	 * 
	 * @return HTML-Text
	 */
	public String getHeader() {
		StringBuffer result = new StringBuffer();

		result.append(
				"<html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"><title></title></head><body>");
		return result.toString();
	}

	/**
	 * HTML-Trailer-Text erstellen.
	 * 
	 * @return HTML-Text
	 */
	public String getTrailer() {
		return "</body></html>";
	}

	/**
	 * 
	 * @return a String in HTML-Format
	 */
	public String getReportText() {
		return this.getHeader() + this.reportText + this.getTrailer();
	}

	/**
	 *	 * @param r processing Report
	 * 
	 */
	public void process(AllSubsFromUserReport r) {
		// delete the result
		this.resetReportText();

		/**
		 */
		StringBuffer result = new StringBuffer();

		/**
		 */
		result.append("<H2>" + r.getTitel() + "</H2>");
		result.append("<H3>" + r.getCreateDate().toString() + "</H3>");

		Vector<Row> rows = r.getRows();
		result.append("<table style=\"width:auto\">");

		for (int i = 0; i < rows.size(); i++) {
			Row row = rows.elementAt(i);
			result.append("<tr>");
			for (int k = 0; k < row.getColumnsSize(); k++) {
				if (i == 0) {
					result.append(
							"<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k) + "</td>");
				} else {
					if (i >= 1) {
						if (k == 1) {
							Column col = row.getColumnByIndex(k);
							result.append(
									"<td style=\"border-top:1px solid silver\">" + this.processSubTable(col) + "</td>");
						} else {
							result.append(
									"<td style=\"border-top:1px solid silver\">" + row.getColumnByIndex(k) + "</td>");
						}
					} else {
						result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");

					}
				}
			}
			result.append("</tr>");
		}

		result.append("</table>");

		/**
		 */
		this.reportText = result.toString();
	}

	public void process(AllSubsOfUserReport r) {

		this.resetReportText();

		/**
		 * Step by step our results are written into this buffer
		 */
		StringBuffer result = new StringBuffer();

		/**
		 * The individual components of the Reports read and translated into HTML format
		 */
		result.append("<H2>" + r.getTitel() + "</H2>");
		result.append("<H3>" + r.getCreateDate().toString() + "</H3>");

		Vector<Row> rows = r.getRows();
		result.append("<table style=\"width:auto\">");

		for (int i = 0; i < rows.size(); i++) {
			Row row = rows.elementAt(i);
			result.append("<tr>");
			for (int k = 0; k < row.getColumnsSize(); k++) {
				if (i == 0) {
					result.append(
							"<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k) + "</td>");
				} else {
					if (i >= 1) {
						if (k == 1) {
							Column col = row.getColumnByIndex(k);
							result.append(
									"<td style=\"border-top:1px solid silver\">" + this.processSubTable(col) + "</td>");
						} else {
							result.append(
									"<td style=\"border-top:1px solid silver\">" + row.getColumnByIndex(k) + "</td>");
						}
					} else {
						result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");

					}
				}
			}
			result.append("</tr>");
		}

		result.append("</table>");

		this.reportText = result.toString();
	}

	public void process(AllCommentsFromUserReport r) {

		StringBuffer result = new StringBuffer();

		result.append("<H2>" + r.getTitel() + "</H2>");
		result.append("<H3>" + r.getCreateDate().toString() + "</H3>");

		Vector<Row> rows = r.getRows();
		result.append("<table style=\"width:auto\">");

		for (int i = 0; i < rows.size(); i++) {
			Row row = rows.elementAt(i);
			result.append("<tr>");
			for (int k = 0; k < row.getColumnsSize(); k++) {
				if (i == 0) {
					result.append(
							"<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k) + "</td>");
				} else {
					if (i >= 1) {
						if (k == 1) {
							Column col = row.getColumnByIndex(k);
							result.append(
									"<td style=\"border-top:1px solid silver\">" + this.processSubTable(col) + "</td>");
						} else {
							result.append(
									"<td style=\"border-top:1px solid silver\">" + row.getColumnByIndex(k) + "</td>");
						}
					} else {
						result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");

					}
				}
			}
			result.append("</tr>");
		}

		result.append("</table>");

		this.reportText = result.toString();
	}

	public void process(AllLikesFromUserReport r) {

		this.resetReportText();

		/**
		 */
		StringBuffer result = new StringBuffer();

		/**
		 */
		result.append("<H2>" + r.getTitel() + "</H2>");
		result.append("<H3>" + r.getCreateDate().toString() + "</H3>");

		Vector<Row> rows = r.getRows();
		result.append("<table style=\"width:auto\">");

		for (int i = 0; i < rows.size(); i++) {
			Row row = rows.elementAt(i);
			result.append("<tr>");
			for (int k = 0; k < row.getColumnsSize(); k++) {
				if (i == 0) {
					result.append(
							"<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k) + "</td>");
				} else {
					if (i >= 1) {
						if (k == 1) {
							Column col = row.getColumnByIndex(k);
							result.append(
									"<td style=\"border-top:1px solid silver\">" + this.processSubTable(col) + "</td>");
						} else {
							result.append(
									"<td style=\"border-top:1px solid silver\">" + row.getColumnByIndex(k) + "</td>");
						}
					} else {
						result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");

					}
				}
			}
			result.append("</tr>");
		}

		result.append("</table>");

		this.reportText = result.toString();
	}

	public void process(AllPostsFromUserReport r) {

		this.resetReportText();

		/**
		 */
		StringBuffer result = new StringBuffer();

		/**
		 */
		result.append("<H2>" + r.getTitel() + "</H2>");
		result.append("<H3>" + r.getCreateDate().toString() + "</H3>");

		Vector<Row> rows = r.getRows();
		result.append("<table style=\"width:auto\">");

		for (int i = 0; i < rows.size(); i++) {
			Row row = rows.elementAt(i);
			result.append("<tr>");
			for (int k = 0; k < row.getColumnsSize(); k++) {
				if (i == 0) {
					result.append(
							"<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k) + "</td>");
				} else {
					if (i >= 1) {
						if (k == 1) {
							Column col = row.getColumnByIndex(k);
							result.append(
									"<td style=\"border-top:1px solid silver\">" + this.processSubTable(col) + "</td>");
						} else {
							result.append(
									"<td style=\"border-top:1px solid silver\">" + row.getColumnByIndex(k) + "</td>");
						}
					} else {
						result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");

					}
				}
			}
			result.append("</tr>");
		}

		result.append("</table>");

		this.reportText = result.toString();
	}

	public StringBuffer processSubTable(Column c) {
		StringBuffer result = new StringBuffer();

		Vector<Row> rowVec = c.getSubRow();

		result.append("<table style=\"width:400px\">");

		for (int i = 0; i < rowVec.size(); i++) {
			Row row = rowVec.elementAt(i);
			result.append("<tr>");
			for (int k = 0; k < row.getColumnsSize(); k++) {
				if (i == 0) {
					result.append(
							"<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k) + "</td>");
				} else {
					if (i > 1) {
						result.append("<td style=\"border-top:1px solid silver\">" + row.getColumnByIndex(k) + "</td>");

					} else {
						result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
					}
				}
			}
			result.append("</tr>");
		}

		result.append("</table>");

		return result;
	}

}
