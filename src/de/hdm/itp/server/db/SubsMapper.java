package de.hdm.itp.server.db;

public class SubsMapper {
	/**
	* Konstruktor f�r den SubsMapper (Singleton) 
	* static weil Singleton. Einzige Instanz dieser Klasse
	* 
	* @author Egor Kr�mer
	*/
	private static SubsMapper  subsmapper = null;
	
	/**
	 * Falls noch kein PostMapper existiert wird ein neuen PostMapper erstellt und gibt ihn zur�ck
	 * 
	 * @return erstmalig erstellter PostMapper
	 * 
	 * @author Egor Kr�mer
	 */
	public static SubsMapper subsMapper() {
		if (subsmapper == null){
			subsmapper = new SubsMapper();
		}
		return subsmapper;
		}
}
