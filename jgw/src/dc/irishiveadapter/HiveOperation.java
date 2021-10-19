package dc.irishiveadapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

			if (type.equalsIgnoreCase("DDL")) {
				stmt.execute(sql);
			} else {
				ResultSet rs = stmt.executeQuery(sql);
				JSONArray resp = convert(rs);
				ensResp = resp.toString(1);
			}

		} catch (Exception e) {
			ensResp = e.getMessage();
			logger.severe(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				ensResp = e.getMessage();
				e.printStackTrace();
			}
		}

		IRISObject response = null;

		if (type.equalsIgnoreCase("DDL")) {
			response = (IRISObject) (iris.classMethodObject("Ens.StringContainer", "%New", "OK"));
		} else {
			response = (IRISObject) (iris.classMethodObject("Ens.StringContainer", "%New", ensResp));
		}

		return response;

	}
	
	public JSONArray convert(ResultSet rs) throws SQLException, JSONException {
		JSONArray json = new JSONArray();
		ResultSetMetaData rsmd = rs.getMetaData();

		while (rs.next()) {
			int numColumns = rsmd.getColumnCount();
			JSONObject obj = new JSONObject();

			for (int i = 1; i < numColumns + 1; i++) {
				String column_name = rsmd.getColumnName(i);

				switch (rsmd.getColumnType(i)) {
				case java.sql.Types.ARRAY:
					obj.put(column_name, rs.getArray(column_name));
					break;
				case java.sql.Types.BIGINT:
					obj.put(column_name, rs.getInt(column_name));
					break;
				case java.sql.Types.BOOLEAN:
					obj.put(column_name, rs.getBoolean(column_name));
					break;
				case java.sql.Types.BLOB:
					obj.put(column_name, rs.getBlob(column_name));
					break;
				case java.sql.Types.DOUBLE:
					obj.put(column_name, rs.getDouble(column_name));
					break;
				case java.sql.Types.FLOAT:
					obj.put(column_name, rs.getFloat(column_name));
					break;
				case java.sql.Types.INTEGER:
					obj.put(column_name, rs.getInt(column_name));
					break;
				case java.sql.Types.NVARCHAR:
					obj.put(column_name, rs.getNString(column_name));
					break;
				case java.sql.Types.VARCHAR:
					obj.put(column_name, rs.getString(column_name));
					break;
				case java.sql.Types.TINYINT:
					obj.put(column_name, rs.getInt(column_name));
					break;
				case java.sql.Types.SMALLINT:
					obj.put(column_name, rs.getInt(column_name));
					break;
				case java.sql.Types.DATE:
					obj.put(column_name, rs.getDate(column_name));
					break;
				case java.sql.Types.TIMESTAMP:
					obj.put(column_name, rs.getTimestamp(column_name));
					break;
				default:
					obj.put(column_name, rs.getObject(column_name));
					break;
				}
			}

			json.put(obj);
		}

		return json;
	}


}
