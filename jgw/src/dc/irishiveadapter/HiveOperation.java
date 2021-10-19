package dc.irishiveadapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

import org.json.JSONArray;

import com.intersystems.enslib.pex.BusinessOperation;
import com.intersystems.gateway.GatewayContext;
import com.intersystems.jdbc.IRIS;
import com.intersystems.jdbc.IRISObject;

public class HiveOperation extends BusinessOperation {

	// Connection to InterSystems IRIS
    private IRIS iris;
    
	private static final Logger logger = Logger.getLogger(HiveOperation.class.getCanonicalName());
	private static final String JDBC_DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";

	@Override
	public void OnInit() throws Exception {
		iris = GatewayContext.getIRIS();
	}
	
	@Override
	public Object OnMessage(Object request) throws Exception {
		
		IRISObject req = (IRISObject) request;
		String sql = req.getString("SQL");
		String connection = req.getString("Connection");
		String type = req.getString("Type");
		String ensResp = "[]";
		
		// Init Connection
		Connection con = null;

		try {
			// Set JDBC Hive Driver
			Class.forName(JDBC_DRIVER_NAME);
			// Connect to Hive
			con = DriverManager.getConnection(connection, "hdfs", "");
			// Init Statement
			Statement stmt = con.createStatement();
			
			if(type.equalsIgnoreCase("DDL")) {
				stmt.execute(sql);
			} else {				
				ResultSet rs = stmt.executeQuery(sql);
				JSONArray resp = ResultSetConverter.convert(rs);
				ensResp = resp.toString(1);
			}
			

		} catch (Exception e) {
			logger.severe(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		IRISObject response = null;
		
		if(type.equalsIgnoreCase("DDL")) {			
			response = (IRISObject)(iris.classMethodObject("Ens.StringContainer","%New","OK"));
		} else {
			response = (IRISObject)(iris.classMethodObject("Ens.StringContainer","%New", ensResp));
		}
        
		return response;
		
	}


}
