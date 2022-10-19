/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author DINHDAT
 */
public class DBContext {

    InitialContext init;
    Context context;
    String dbName, serverName, port, username, password;

    public Connection getConnection() {
        Connection con = null;
        try {
            init = new InitialContext();
            context = (Context) init.lookup("java:comp/env");

            serverName = context.lookup("serverName").toString();
            dbName = context.lookup("dbName").toString();
            port = context.lookup("port").toString();
            username = context.lookup("username").toString();
            password = context.lookup("password").toString();

            StringBuilder url = new StringBuilder("jdbc:sqlserver://");
            url.append(serverName);
            url.append(":");
            url.append(port);
            url.append(";databaseName=");
            url.append(dbName);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url.toString(), username, password);
        } catch (ClassNotFoundException | SQLException | NamingException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void closeConnection(Connection con, PreparedStatement ps, ResultSet rs) throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    public void closeConnection(Connection con, PreparedStatement ps) throws SQLException {
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

}
