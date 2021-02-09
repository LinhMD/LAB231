package p0014.linhmd.ultilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;

public class SQLQuery {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SQLQuery.class);
    public static boolean executeNonQuery(String sqlStatement, Object... values) throws SQLException  {
        boolean result = false;
        int index = 1;
        try (Connection cnn = DBHelpers.makeConnection(); PreparedStatement ps = cnn.prepareStatement(sqlStatement)) {
            for (index = 0; index < values.length; index++) {
                ps.setObject(index + 1, values[index]);
            }
            result = (ps.executeUpdate() > 0);
        } catch (ClassNotFoundException | NamingException ex) { 
            LOGGER.fatal(ex.getMessage());
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }

    public static Vector<Vector<String>> executeQuery(String sqlStatement, Object... values) throws SQLException  {
        Connection cnn = null;
        PreparedStatement pre;
        Vector<Vector<String>> dataTable = new Vector<>();
        int index, columnsNumber;
        try {
            cnn = DBHelpers.makeConnection();

            pre = cnn.prepareStatement(sqlStatement);
            for (index = 0; index < values.length; index++) {
                pre.setObject(index + 1, values[index]);
            }
            ResultSet rs = pre.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                Vector<String> rowData = new Vector<>();
                for (index = 1; index <= columnsNumber; index++) rowData.add(rs.getString(index));
                dataTable.add(rowData);
            }
        } catch (SQLException ex) {
            throw ex;
        }catch (ClassNotFoundException | NamingException ex) { 
            LOGGER.fatal(ex.getMessage());
        }finally {
            if (cnn != null) cnn.close();
        }
        return dataTable;
    }
}
