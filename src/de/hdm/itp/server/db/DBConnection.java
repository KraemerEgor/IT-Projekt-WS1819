package de.hdm.itp.server.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.appengine.api.utils.SystemProperty;



/**
 * Verwalten einer Verbindung zur Datenbank.
 * <p>
 * <b>Vorteil:</b> Sehr einfacher Verbindungsaufbau zur Datenbank.
 * <p>
 * <b>Nachteil:</b> Durch die Singleton-Eigenschaft der Klasse kann nur auf eine
 * fest vorgegebene Datenbank zugegriffen werden.
 * <p>
 * In der Praxis kommen die meisten Anwendungen mit einer einzigen Datenbank
 * aus. Eine flexiblere Variante f�r mehrere gleichzeitige
 * Datenbank-Verbindungen w�re sicherlich leistungsf�higer. Dies w�rde
 * allerdings den Rahmen dieses Projekts sprengen bzw. die Software unn�tig
 * verkomplizieren, da dies f�r diesen Anwendungsfall nicht erforderlich ist.
 * 
 */
public class DBConnection {

   
	/**
     * Die Klasse DBConnection wird nur einmal instantiiert. Man spricht hierbei
     * von einem sogenannten <b>Singleton</b>.
     * <p>
     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
     * f�r s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
     * speichert die einzige Instanz dieser Klasse.
     * @author Thies
     */
    private static Connection con = null;

    /**
     * Diese statische Methode kann aufgrufen werden durch
     * <code>DBConnection.connection()</code>. Sie stellt die
     * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine
     * einzige Instanz von <code>DBConnection</code> existiert.
     * <p>
     * 
     * <b>Fazit:</b> DBConnection sollte nicht mittels <code>new</code>
     * instantiiert werden, sondern stets durch Aufruf dieser statischen
     * Methode.
     * <p>
     * 
     * <b>Nachteil:</b> Bei Zusammenbruch der Verbindung zur Datenbank - dies
     * kann z.B. durch ein unbeabsichtigtes Herunterfahren der Datenbank
     * ausgel�st werden - wird keine neue Verbindung aufgebaut, so dass die in
     * einem solchen Fall die gesamte Software neu zu starten ist. In einer
     * robusten L�sung w�rde man hier die Klasse dahingehend modifizieren, dass
     * bei einer nicht mehr funktionsf�higen Verbindung stets versucht w�rde,
     * eine neue Verbindung aufzubauen. Dies w�rde allerdings ebenfalls den
     * Rahmen dieses Projekts sprengen.
     * @author Thies
     * @return DAS <code>DBConncetion</code>-Objekt.
     * @see con
     */  
    public static Connection connection() {
       
        if (con == null) {
            
            try {
                if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
                	 // Load the class that provides the new
                    // "jdbc:google:mysql://" prefix.
                	// Google Driver
                    Class.forName("com.mysql.jdbc.GoogleDriver"); 
                    //dieser Link baut die DB Verbindung zu Google auf anhand des Instanznamen, des Usernames und des Passwords -> unsicher weil Password hard gecodet
                    con = DriverManager.getConnection("jdbc:google:mysql://it-projekt-gruppe4:europe-west1:itpdb/db1?user=root&password=root");
                    						                  
                    
                } else {
                	// Load the class that provides the new
                    // "jdbc:mysql://" prefix. for the local database
                	// Local MySQL Driver
                	Class.forName("com.mysql.jdbc.Driver");
                	//dieser Link baut die DB Verbindung zu einer lokalen Datenbank auf anhand des Instanznamen, des Usernames und des Passwords -> Erleichtert das Testen
                	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itpdb", "root", "root");
                                  
                }
                
                
            } catch (Exception e) {
                con = null;
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
                
            }
        }

        // Zur�ckgegeben der Verbindung
        return con;
    }
    /**
	 * Schlie�t das ResultSet, das Statement und die Connection.
	 * 
	 * @param rs
	 *            ResultSet
	 * @param stmt
	 *            Statement
	 * @param con
	 *            Datenbankverbindung
	 * @throws Exception
	 */
	public static void closeAll(ResultSet rs, Statement stmt, Connection con) throws IllegalArgumentException {

	}


}
