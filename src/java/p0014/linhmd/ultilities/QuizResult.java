/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0014.linhmd.ultilities;

import java.io.Serializable;
import java.util.HashMap;
import p0014.linhmd.dto.Question;
import p0014.linhmd.dto.User;

/**
 *
 * @author USER
 */
public class QuizResult extends HashMap<Question, Character> implements Serializable{
    private User quizTaker;

    
    public QuizResult(User quizTaker){
        this.quizTaker = quizTaker;
    }
    
    public User getQuizTaker() {
        return quizTaker;
    }

    public void setQuizTaker(User quizTaker) {
        this.quizTaker = quizTaker;
    }
    
    public int getNumCorrectAns(){
        int count = 0;
        count = this.keySet().stream().filter((question) -> (question.getCorrect() == this.get(question))).map(q -> 1).reduce(count, Integer::sum);
        return count;
    }
    
    public double getPoint(){
        return (getNumCorrectAns() / this.keySet().size()) * 10;
    }
    
}
