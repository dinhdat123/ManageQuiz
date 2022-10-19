/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AnswerDao;
import dao.QuestionDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Answer;
import model.Question;
import model.User;

/**
 *
 * @author DINHDAT
 */
@WebServlet(name = "EditQuiz", urlPatterns = {"/edit"})
public class EditQuiz extends HttpServlet {

     public boolean checkBoxValue(String value) {
        if (value == null) {
            //value of checkbox is null checked then return false
            return false;
        } else {
            //value of checkbox is not null checked then return true
            return value.equals("true");
        }
    }

    public boolean checkQuatityOfCheckBox(String op1, String op2, String op3, String op4) {
        int num = 0;
        if (checkBoxValue(op1)) {
            //check checkbox 1 is checked or not
            ++num;
        }
        if (checkBoxValue(op2)) {
            //check checkbox 2 is checked or not
            ++num;
        }
        if (checkBoxValue(op3)) {
            //check checkbox 3 is checked or not
            ++num;
        }
        if (checkBoxValue(op4)) {
            //check checkbox 4 is checked or not
            ++num;
        }
        return num >= 1 && num <= 3;
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
           int id = Integer.parseInt(request.getParameter("qid")) ;
           QuestionDao qDao = new QuestionDao();
           Question q = qDao.getQuestionsById(id);
           ArrayList<Answer> listAnswer = q.getAnswers();
           request.setAttribute("listAns", listAnswer);
           request.setAttribute("question", q);
           request.getRequestDispatcher("/view/editQuiz.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             try {
            QuestionDao qDao = new QuestionDao();
            AnswerDao aDao = new AnswerDao();

            String question = request.getParameter("question");
            String option1 = request.getParameter("option1");
            String option2 = request.getParameter("option2");
            String option3 = request.getParameter("option3");
            String option4 = request.getParameter("option4");

            int qId = Integer.parseInt(request.getParameter("id")) ;

            ArrayList<Answer> answers = new ArrayList<>();
            //initialize array list answer

            String op1 = request.getParameter("op1");
            String op2 = request.getParameter("op2");
            String op3 = request.getParameter("op3");
            String op4 = request.getParameter("op4");

            if (checkQuatityOfCheckBox(op1, op2, op3, op4)) {
                //check option at least 1 and maximun is 3
                answers.add(new Answer( option1.trim(), checkBoxValue(op1)));
                answers.add(new Answer( option2.trim(), checkBoxValue(op2)));
                answers.add(new Answer( option3.trim(), checkBoxValue(op3)));
                answers.add(new Answer( option4.trim(), checkBoxValue(op4)));

                long millis = System.currentTimeMillis();
                //get current time measured in milliseconds, between the current time and midnight, January 1, 1970 UTC
                Date date = new Date(millis);
                //init current date
                Question q = new Question(qId, question, date, answers);

                qDao.updateQuestion(q);
                request.setAttribute("mess", "Add Successful");

                request.getRequestDispatcher("/view/makeQuiz.jsp").forward(request, response);

                //response.sendRedirect("manage-quiz");
            } else {
                request.setAttribute("mess", "You have to check at least 1 option and maximum of 3 options");
                request.getRequestDispatcher("/view/makeQuiz.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
