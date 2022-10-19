/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QuestionDao;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.servlet.ServletException;
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
public class TakeQuizServlet extends HttpServlet {

    private Hashtable<String, Long> startTimes;
    private Hashtable<String, ArrayList<Question>> questions;
    private static long DELAYTIME = 500;
    //suppose that delaytime of server response to client and the client
    //response to server when taking quiz is 1000 milliseconds

    public TakeQuizServlet() {
        this.questions = new Hashtable<String, ArrayList<Question>>();
        this.startTimes = new Hashtable<String, Long>();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
        try {
            QuestionDao qDao = new QuestionDao();
            if (request.getParameter("number") == null) {
                //the number parameter is not requested then return to entering number quiz page
                request.setAttribute("maxQuiz", qDao.countQuestion());
                request.getRequestDispatcher("view/enterNumQuiz.jsp").forward(request, response);
            } else {
                //the number parameter is requested then return to taking quiz page
                int number = Integer.parseInt(request.getParameter("number"));
                if (number > qDao.countQuestion()) {
                    //return mess when number of question over total question in database 
                    request.setAttribute("mess", "Please enter number of question under " + qDao.countQuestion());
                    request.getRequestDispatcher("view/enterNumQuiz.jsp").forward(request, response);
                } else {
                HttpSession session = request.getSession(false);
                User u = (User) session.getAttribute("user");
                //set duration time measured in milliseconds is the limit time to take all quizzes for user
                //with that number of question

                if (session.getAttribute("duration") == null
                        ||(session.getAttribute("duration") != null 
                        && number != this.questions.get(u.getUserName()).size())){ 
                    //set all brand new questions when user take quiz for the first time
                    //or user request a difference quantity of question
                    long startTime = System.currentTimeMillis();
                    //start time when user taking the quiz is ticked
                    this.startTimes.put(u.getUserName(), startTime);
                    this.questions.put(u.getUserName(), qDao.getQuestions(number));
                    long duration = this.questions.get(u.getUserName()).size() * 60 * 1000;
                    session.setAttribute("duration", duration);
                    //set duration time for all questions
                    session.setAttribute("questions", this.questions.get(u.getUserName()));
                    request.setAttribute("labeltime", formartTimeLable(duration));
                    //set label time
                    request.getRequestDispatcher("view/takeQuiz.jsp").forward(request, response);
                } else {
                    //when user reload page the game state have to fixed.

                    long duration = (Long) session.getAttribute("duration");

                    long currentTime = System.currentTimeMillis();

                    long takedTime = currentTime - this.startTimes.get(u.getUserName()) - DELAYTIME;
                    //calculate the time has been taked before and minus the delaytime.

                    long remainTime = duration - takedTime;
                    //calculate the remain time of user

                    if (remainTime > 0) {
                        request.setAttribute("labeltime", formartTimeLable(remainTime));
                        //set label time
                        request.getRequestDispatcher("view/takeQuiz.jsp").forward(request, response);
                    } else {
                        //takingTime is greater than duration so that result is not accepted.
                        request.setAttribute("mess", "Your result has been rejected!");
                        request.getRequestDispatcher("view/result.jsp").forward(request, response);
                    }
                }
                }
            }
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

            HttpSession session = request.getSession(false);
            User u = (User) session.getAttribute("user");

            String result = request.getParameter("result");
            long currentTime = System.currentTimeMillis();
            long startTime = this.startTimes.get(u.getUserName());
            long duration = (Long) session.getAttribute("duration");

            //get current time when user sent result of quiz to server
            long takingTime = (currentTime - startTime) - DELAYTIME;
            //takingTime is the time of the user taking all of the quizzes
            if (takingTime > duration) {
                //takingTime is greater than duration so that result is not accepted.
                request.setAttribute("mess", "Your result has been rejected!");
                request.getRequestDispatcher("view/result.jsp").forward(request, response);
            } else {
                float score = (float) (calculateScore(result.split(" "), u.getUserName()) * 10.0);
                //calculate the score
                DecimalFormat df = new DecimalFormat("#.#");

                String formatted = df.format(score);
                request.setAttribute("mark", Float.parseFloat(formatted)); 
                request.setAttribute("percent", Float.parseFloat(formatted) * 10);
                //calculated the percent of the result
                String isPass = score < 5.0 ? "Not Pass" : "Pass";
                //is pass or not
                request.setAttribute("isPass", isPass);
                request.getRequestDispatcher("view/result.jsp").forward(request, response);
            }
            session.removeAttribute("duration");
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

    /**
     *
     * @param results
     * @param userName
     * @return score Purpose: calculate the score for user
     */
    public float calculateScore(String[] results, String userName) {
        int num = 0;
        ArrayList<Integer> trueAnswer = new ArrayList<>();
        ArrayList<Integer> falseAnswer = new ArrayList<>();

        for (Question question : this.questions.get(userName)) {
            //loop each question to check correct or not
            for (Answer answer : question.getAnswers()) {
                //loop each answer of each question to get each false and true answer
                if (answer.isIsTrue() == true) {
                    //get each true answer then push to the true answer arraylist
                    trueAnswer.add(answer.getId());
                } else {
                    //get each true answer then push to the false answer arraylist
                    falseAnswer.add(answer.getId());
                }
            }
            if (checkAnswer(results, trueAnswer, falseAnswer)) {
                //then check that answer whether correct or not in each corresponding question
                num++;
            }
            trueAnswer.removeAll(trueAnswer);
            falseAnswer.removeAll(falseAnswer);
        }

        return (float) num / this.questions.get(userName).size();
    }

    /**
     *
     * @param millis
     * @return
     */
    public String formartTimeLable(long millis) {
        long minutes = (millis / 1000) / 60;
        long seconds = (millis / 1000) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     *
     * @param results
     * @param trueAnswerString
     * @param falseAnswerString
     * @return boolean Purpose: to check each answer is correct or not in each question
     */
    public boolean checkAnswer(String[] results,
            ArrayList<Integer> trueAnswer,
            ArrayList<Integer> falseAnswer) {

        int numOfTrueAnswer = 0;

        for (int falseA : falseAnswer) {
            //loop each false answer of each question
            for (String result : results) {
                //loop each result string of all answer that client sent to server
                if (String.valueOf(falseA).equals(result)) {
                    //if one of all answer is in false answer of that question
                    // then return that answer of question is false
                    return false;
                }
            }
        }

        for (int trueA : trueAnswer) {
            //loop each true answer of each question
            for (String result : results) {
                //loop each result string of all answer that client sent to server
                if (String.valueOf(trueA).equals(result)) {
                    //if one of all answer is in false answer of that question
                    // then return numOfTrueAnswer increase to 1
                    numOfTrueAnswer++;
                }
            }
        }

        return numOfTrueAnswer == trueAnswer.size();
        //return whether quantity of true answer that question is enough or not
    }

}
