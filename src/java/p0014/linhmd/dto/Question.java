/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0014.linhmd.dto;

import java.io.Serializable;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    private String ansA;
    private String ansB;
    private String ansC;
    private String ansD;
    private char correct;
    private Date createDate;
    private String subjectID;
    
    public Question(int id){
        this.id = id;
    }

    public Question(int id, String content, String ansA, String ansB, String ansC, String ansD, char correct, String subjectID) {
        this.id = id;
        this.content = content;
        this.ansA = ansA;
        this.ansB = ansB;
        this.ansC = ansC;
        this.ansD = ansD;
        this.correct = correct;
        this.createDate = Calendar.getInstance().getTime();
        this.subjectID = subjectID;
    }

    public Question(List<String> data) {
        
        this.id = Integer.parseInt(data.get(0));
        this.content = data.get(1);
        this.ansA = data.get(2);
        this.ansB = data.get(3);
        this.ansC = data.get(4);
        this.ansD = data.get(5);
        this.correct = data.get(6).charAt(0);

        try {
            this.createDate = SQL_DATE_FORMAT.parse(data.get(7));
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
        }

        this.subjectID = data.get(8);
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

    public String getAnsA() {
        return ansA;
    }

    public void setAnsA(String ansA) {
        this.ansA = ansA;
    }

    public String getAnsB() {
        return ansB;
    }

    public void setAnsB(String ansB) {
        this.ansB = ansB;
    }

    public String getAnsC() {
        return ansC;
    }

    public void setAnsC(String ansC) {
        this.ansC = ansC;
    }

    public String getAnsD() {
        return ansD;
    }

    public void setAnsD(String ansD) {
        this.ansD = ansD;
    }

    public char getCorrect() {
        return correct;
    }

    public void setCorrect(char correct) {
        this.correct = correct;
    }

    public String getCreateDate() {
        return DATE_FORMAT.format(createDate);
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
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", a='" + ansA + '\'' +
                ", b='" + ansB + '\'' +
                ", c='" + ansC + '\'' +
                ", d='" + ansD + '\'' +
                ", correct=" + correct +
                ", createDate=" + createDate +
                ", subjectID='" + subjectID + '\'' +
                '}';
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
    
    
}
