// Connect to Access Database using JDBC-ODBC Bridge
// Displaying the contents of the table Garbage in database garbage_db.
// Java core packages
import java.awt.*;
import java.sql.*;
import java.util.*;
//Java extension packages
import javax.swing.*;
public class DbConnection extends JFrame{
	// constructor connects to database, queries database,
	// processes results and displayes results in window
	public DbConnection()
	{
		super( "Garbage Table from garbage_db" );
		// connect to database S_P_SP and query database
		try {
			// load database driver class
			Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver" ); // <--
			// connect  to database
			Connection connection = DriverManager.getConnection(
				"jdbc:odbc:garbage_db" ); // <--
			// create Statement to query database
			Statement statement = connection.createStatement();
			
			// query database
			ResultSet resultSet =
				statement.executeQuery( "SELECT * FROM AddressBook_DB" ); // <--
			// process query results
			StringBuffer results = new StringBuffer();
			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberOfColumns = metaData.getColumnCount();
			for ( int i = 1; i <= numberOfColumns; i++ ){
				results.append( metaData.getColumnName( i )
					+ "\t" );
			}
			results.append( "\n" );
			while ( resultSet.next() ) {
				for ( int i = 1; i <= numberOfColumns; i++ ){
					results.append( resultSet.getObject( i )
						+ "\t" );
				}
				results.append( "\n" );
			}
			// close statement and connection
			statement.close();
			connection.close();
			
			// set up GUI and display window <--
			
			AddrGUI newDisplay = new AddrGUI(); // uses new GUI
			
			// original GUI
/*			JTextArea textArea = new JTextArea(
				results.toString() );
			Container container = getContentPane();
			container.add( new JScrollPane( textArea) );
			setSize( 300, 100 ); // set window size
			setVisible( true ); // display window
*/		} // end try
		
		// detect problems interacting with the database
		catch ( SQLException sqlException ) {
			JOptionPane.showMessageDialog( null,
				sqlException.getMessage(), "Database Error",
				JOptionPane.ERROR_MESSAGE );
			System.exit( 1 );
		}
		// detect problems loading database driver
		catch ( ClassNotFoundException classNotFound ){
			JOptionPane.showMessageDialog( null,
				classNotFound.getMessage(), "Driver Not Found",
				JOptionPane.ERROR_MESSAGE );
			System.exit( 1 );
		}
	} // end DbConnection constructor definition
	// lanuch the application
	public static void main( String args[] )
	{
		DbConnection window = new DbConnection();
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
} // end class DbConnection