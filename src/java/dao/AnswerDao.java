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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import model.Answer;

/**
 *
 * @author DINHDAT
 */
public class AnswerDao extends DBContext {

    /**
     *
     * @param qid
     * @return Purpose: get all answer of corresponding question
     * @throws java.lang.Exception
     */
    public ArrayList<Answer> getAnswers(int qid) throws Exception{

        ArrayList<Answer> list = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            list = new ArrayList<>();
            String sql = "SELECT * FROM Answer WHERE qid= ?";
            con = getConnection();
            st = con.prepareStatement(sql);
            st.setInt(1, qid);
            rs = st.executeQuery();
            while (rs.next()) {
                //loop to each item of the result set
                list.add(new Answer(rs.getInt("aid"),
                        rs.getString(3),
                        rs.getBoolean("isTrue")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(con, st, rs);
        }
        return list;
    }

    /**
     *
     * @param qid
     * @throws java.lang.Exception
     */
    public void deleteAnswer(int qid) throws Exception{

        Connection con = null;
        PreparedStatement st = null;

        try {
            String sql = "DELETE FROM Answer WHERE qid = ?";
            con = getConnection();
            st = con.prepareStatement(sql);
            st.setInt(1, qid);
            st.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(con, st);
        }

    }

    /**
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
//    public boolean checkAnswerId(String id) throws Exception {
//
//        boolean result = false;
//        Connection con = null;
//        PreparedStatement st = null;
//        ResultSet rs = null;
//
//        try {
//            String sql = "SELECT * FROM Answer WHERE aid = ?\n";
//            con = getConnection();
//            st = con.prepareStatement(sql);
//            st.setString(1, id);
//            rs = st.executeQuery();
//            while (rs.next()) {
//                //loop to each item of the result set
//                result = true;
//            }
//        } catch (Exception ex) {
//            throw ex;
//        } finally {
//            closeConnection(con, st, rs);
//        }
//        return result;
//    }

    /**
     *
     * @param a
     * @param qid
     * @throws java.lang.Exception
     */
    public void insertAnswer(Answer a, int qid) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        try {
            String sql = "INSERT INTO [dbo].[Answer]\n"
                    + "           ([qid]\n"
                    + "           ,[content]\n"
                    + "           ,[isTrue])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            con = getConnection();
            st = con.prepareStatement(sql);
            st.setInt(1, qid);
            st.setString(2, a.getContent());
            st.setBoolean(3, a.isIsTrue());
            st.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(con, st);
        }
    }
   
}
