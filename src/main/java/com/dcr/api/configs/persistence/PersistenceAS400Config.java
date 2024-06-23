/*package com.dcr.api.configs.persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class PersistenceAS400Config {

	private static final Logger logger = LoggerFactory.getLogger(PersistenceAS400Config.class);
	
	public static Connection getConnectionAS400Db2() {


		
		String ds_dialect = "as400.datasource.database-platform";
		String ds_ddlauto = "as400.datasource.hibernate.ddl-auto";  



		// # PRODUÇÃO #
			String url = "jdbc:as400://hsa0014;translate binary=true;user=SB037635;password=SB037635;driverClassName=com.ibm.as400.access.AS400JDBCDriver;hibernate.ddl-auto=validatelibraries=,HD4DCDHH;";
		// # HOMOLOGAÇÃO #
		//	String url = "jdbc:as400://hsa0014;translate binary=true;user=SBS00020;password=manaus@01;driverClassName=com.ibm.as400.access.AS400JDBCDriver";
				
		try {
			Class.forName("com.ibm.as400.access.AS400JDBCDriver");
			Connection con = DriverManager.getConnection(url);						
			return con;
		} catch (SQLException e) {
			String msg = "Ocorreu um erro na API JDBC: "
					+  "=> " + e.getMessage();
			logger.error(msg);
			//e.printStackTrace();
			//System.out.println(msg);
			//throw new RuntimeException(msg);
			return null;
		} catch (ClassNotFoundException e) {
			String msg = "Ocorreu um erro na API JDBC: "
					+  "=> " + e.getMessage();
			logger.error(msg);
			//e.printStackTrace();
			//System.out.println(msg);
			//throw new RuntimeException(msg);
			return null;
		}
	}

}
*/