/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;

/**
 *
 * @author DINHDAT
 */
public class UserDao extends DBContext {

    /**
     *
     * @param userName
     * @param password
     * @return user
     * @throws java.lang.Exception
     */
    public User getUser(String userName, String password) throws Exception {

        User user = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM [User] WHERE [userName]= ? AND [password]= ?";
            con = getConnection();
            st = con.prepareStatement(sql);
            st.setString(1, userName);
            st.setString(2, password);
            rs = st.executeQuery();
            while (rs.next()) {
                //loop to each item of the result set
                user = new User(rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getBoolean("isTeacher"));
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(con, st, rs);
        }
        return user;
    }

    /**
     *
     * @param userName
     * @return userName is existed or not
     * @throws java.lang.Exception
     */
    public boolean checkUsernameExist(String userName) throws Exception {

        boolean result = false;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * from [User] where [userName]= ?";
            con = getConnection();
            st = con.prepareStatement(sql);
            st.setString(1, userName);
            rs = st.executeQuery();
            while (rs.next()) {
                //loop to each item of the result set
                result = true;
            }
        } catch (Exception ex) {
           throw ex;
        } finally {
            closeConnection(con, st, rs);
        }

        return result;
    }

    /**
     * 
     * @param u 
     * @throws java.lang.Exception 
     */
    public void insertUser(User u) throws Exception{

        Connection con = null;
        PreparedStatement st = null;

        try {
            String sql = "INSERT INTO [dbo].[User]\n"
                    + "           ([userName]\n"
                    + "           ,[password]\n"
                    + "           ,[email]\n"
                    + "           ,[isTeacher])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            con = getConnection();
            st = con.prepareStatement(sql);
            st.setString(1, u.getUserName());
            st.setString(2, u.getPassword());
            st.setString(3, u.getEmail());
            st.setBoolean(4, u.isIsTeacher());
            st.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(con, st);
        }

    }

    /**
     * 
     * @param username
     * @param password
     * @return check username and password
     *  @throws java.lang.Exception 
     */
    public String checkUserAccount(String username, String password) throws Exception {
        if (!checkUsernameExist(username)) {
            //username is incorrect then
            return "Account is incorrect!";
        } else if (getUser(username, password) == null) {
            //password is incorrext
            return "Wrong password";
        }
        //username and password is correct
        return "";
    }

}
