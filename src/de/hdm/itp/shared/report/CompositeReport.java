package de.hdm.itp.shared.report;

	import java.util.Vector;

	/**
	 //TODO Klassenbeschreibung CompositeReport
	 */
	public class CompositeReport extends Report {

		/**
		 *  Initialisierung einer serialVersionUID..
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Teil-Reports in ein Vektor Objekt speichern.
		 */
		private Vector<Report> subReports = new Vector<Report>();
		
		/**
		 * Hinzufügen eines Teil-Reports.
		 */
		public void addSubReport (Report r){
			this.subReports.addElement(r);
		}
		
		/**
		 * Entfernen eines Teil-Reports
		 */
		public void removeSuReport (Report r){
			this.subReports.remove(r);
		}
		
		/**
		 * Nummer der Teil-Reports auslesen
		 */
		public int getSubReportsSize(){
			return this.subReports.size();
		}
		
		/**
		 * Auslesen eines bestimmten Teil-Reports
		 * 
		 */
		public Report getSubReportByIndex(int index){
			return this.subReports.elementAt(index);
		}
	}
