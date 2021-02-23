package p0014.linhmd.dto;

import java.io.Serializable;
import java.util.List;

public class Answer implements Serializable {
	private int id;
	private String content;
	private Question question;
	private boolean correct;

	public Answer(int id, String content, Question question, boolean correct) {
		this.id = id;
		this.content = content;
		this.question = question;
		this.correct = correct;
	}

	public Answer(List<String> data){
		this.id = Integer.parseInt(data.get(0));
		this.content = data.get(1);
		this.question = new Question(Integer.parseInt(data.get(2)));
		this.correct = data.get(3).equals("1");
	}

	public Answer (int id){
		this.id = id;
	}

	public Answer(String content, boolean correct, Question question){
		this.content = content;
		this.correct = correct;
		this.question = question;
	}

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public Question getQuestion() {
		return question;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Answer setQuestion(Question question) {
		this.question = question;
		return this;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Answer)) return false;

		Answer answer = (Answer) o;

		return id == answer.id;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public String toString() {
		return id + "";
	}
}
