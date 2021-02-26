/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0014.linhmd.dto;

import java.io.Serializable;
import org.apache.log4j.Logger;
import p0014.linhmd.dao.AnswerDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author USER
 */
public class Question implements Serializable{
    public static SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger LOGGER = Logger.getLogger(Question.class);

    private int id;
    private String content;
    private List<Answer> answers;

    private Date createDate;
    private String subjectID;
    
    public Question(int id){
        this.id = id;
    }

    public Question(int id, String content, String ansA, String ansB, String ansC, String ansD, char correct, String subjectID) {
        this.id = id;
        this.content = content.trim();
        this.answers = new ArrayList<>();
        this.answers.add(new Answer(ansA.trim(), false, this));
        this.answers.add(new Answer(ansB.trim(), false, this));
        this.answers.add(new Answer(ansC.trim(), false, this));
        this.answers.add(new Answer(ansD.trim(), false, this));

        this.answers.get(correct - 'a').setCorrect(true);

        this.createDate = Calendar.getInstance().getTime();
        this.subjectID = subjectID;
    }

    public Question(List<String> data) {
        this.id = Integer.parseInt(data.get(0));
        this.content = data.get(1).trim();

        try {
            this.answers = new AnswerDAO().getAnswersOfQuestion(this);
            this.createDate = SQL_DATE_FORMAT.parse(data.get(2));
        } catch (ParseException |SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }

        this.subjectID = data.get(3);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        try {
            this.createDate = DATE_FORMAT.parse(createDate);
        } catch (ParseException ignore) {

        }
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Question other = (Question) obj;
        return Objects.equals(this.id, other.id);
    }


    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", answers=" + answers +
                ", createDate=" + createDate +
                ", subjectID='" + subjectID + '\'' +
                '}';
    }
}
