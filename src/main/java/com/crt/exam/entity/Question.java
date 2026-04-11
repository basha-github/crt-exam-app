package com.crt.exam.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Question {
	
	@Id
	@Column(length = 1000, updatable = false, nullable = false)
    private String qno;
	private String topic;
	@Column(length = 1000)
    private String content;
	private String opt1;
	private String opt2;
	private String opt3;
	private String opt4;
	private String ans;
	@Column(length = 1000)
    private String explaination;
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Question(String qno, String topic, String content, String opt1, String opt2, String opt3, String opt4,
			String ans, String explaination) {
		super();
		this.qno = qno;
		this.topic = topic;
		this.content = content;
		this.opt1 = opt1;
		this.opt2 = opt2;
		this.opt3 = opt3;
		this.opt4 = opt4;
		this.ans = ans;
		this.explaination = explaination;
	}
	public Question(String topic, String content, String opt1, String opt2, String opt3, String opt4,
			String ans, String explaination) {
		super();
		this.topic = topic;
		this.content = content;
		this.opt1 = opt1;
		this.opt2 = opt2;
		this.opt3 = opt3;
		this.opt4 = opt4;
		this.ans = ans;
		this.explaination = explaination;
	}
	public String getQno() {
		return qno;
	}
	public void setQno(String qno) {
		this.qno = qno;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOpt1() {
		return opt1;
	}
	public void setOpt1(String opt1) {
		this.opt1 = opt1;
	}
	public String getOpt2() {
		return opt2;
	}
	public void setOpt2(String opt2) {
		this.opt2 = opt2;
	}
	public String getOpt3() {
		return opt3;
	}
	public void setOpt3(String opt3) {
		this.opt3 = opt3;
	}
	public String getOpt4() {
		return opt4;
	}
	public void setOpt4(String opt4) {
		this.opt4 = opt4;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	public String getExplaination() {
		return explaination;
	}
	public void setExplaination(String explaination) {
		this.explaination = explaination;
	}
	@Override
	public String toString() {
		return "Question [qno=" + qno + ", topic=" + topic + ", content=" + content + ", opt1=" + opt1 + ", opt2="
				+ opt2 + ", opt3=" + opt3 + ", opt4=" + opt4 + ", ans=" + ans + ", explaination=" + explaination + "]";
	}	
	
	

}
