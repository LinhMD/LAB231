/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0014.linhmd.dto;

import org.apache.log4j.Logger;
import p0014.linhmd.dao.QuestionDAO;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author USER
 */
public class QuizResult extends HashMap<Question, Character> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(QuizResult.class);

    public static SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private int id;
    private String quizTaker;
    private String subject;
    private Date date;

    public QuizResult(int id, String quizTaker, String subject, Date date){
        this.id = id;
        this.quizTaker = quizTaker;
        this.subject = subject;
        this.date = date;
    }
    public QuizResult(Vector<String> data){
        this.id = Integer.parseInt(data.get(0));
        this.quizTaker = data.get(1);
        this.subject = data.get(2);
        try {
            this.date = SQL_DATE_FORMAT.parse(data.get(3));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    public void addAllResult(List<Vector<String>> vectors){
        QuestionDAO questionDAO = new QuestionDAO();
        for (Vector<String> vector : vectors) {
            try {
                this.put(questionDAO.getQuestionByID(Integer.parseInt(vector.get(0))), vector.get(1).charAt(0));
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }



    public String getDate() {
        return SQL_DATE_FORMAT.format(this.date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumCorrectAns(){
        int count = 0;
        count = this.keySet()
                .stream()
                .filter((question) -> (question.getCorrect() == this.get(question)))
                .map(q -> 1)
                .reduce(count, Integer::sum);
        return count;
    }

    public double getPoint(){
        return ((float) getNumCorrectAns() / this.keySet().size()) * 10;
    }

    public String getQuizTaker() {
        return quizTaker;
    }

    public void setQuizTaker(String quizTaker) {
        this.quizTaker = quizTaker;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
