package com.qq986945193.davidbookstore.user.domain;


/******************************************************************************

 */

public class MailInfo {
	
	private String host ;
	private String formName ;
	private String formPassword ;
	
	private String replayAddress ;
	private String toAddress ;
	
	private String subject ;
	private String content ;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getFormPassword() {
		return formPassword;
	}
	public void setFormPassword(String formPassword) {
		this.formPassword = formPassword;
	}
	public String getReplayAddress() {
		return replayAddress;
	}
	public void setReplayAddress(String replayAddress) {
		this.replayAddress = replayAddress;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
