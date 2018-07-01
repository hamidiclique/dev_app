package com.test.app.dto;

public class TrxnMonitor {

	private String relationshipID;
	private String transaction;
	private String currency;
	private String amount;
	private String date;
	private String time;
	private String stan;
	private String statusCode;
	private String statusDef;
	private String acquirer;
	private String authorizer;
	private String terminal;

	public TrxnMonitor(String relationshipID, String transaction, String currency, String amount, String date,
			String time, String stan, String statusCode, String statusDef, String acquirer, String authorizer,
			String terminal) {
		this.relationshipID = relationshipID;
		this.transaction = transaction;
		this.currency = currency;
		this.amount = amount;
		this.date = date;
		this.time = time;
		this.stan = stan;
		this.statusCode = statusCode;
		this.statusDef = statusDef;
		this.acquirer = acquirer;
		this.authorizer = authorizer;
		this.terminal = terminal;
	}

	public String getRelationshipID() {
		return relationshipID;
	}

	public void setRelationshipID(String relationshipID) {
		this.relationshipID = relationshipID;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStan() {
		return stan;
	}

	public void setStan(String stan) {
		this.stan = stan;
	}

	public String getAcquirer() {
		return acquirer;
	}

	public void setAcquirer(String acquirer) {
		this.acquirer = acquirer;
	}

	public String getAuthorizer() {
		return authorizer;
	}

	public void setAuthorizer(String authorizer) {
		this.authorizer = authorizer;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDef() {
		return statusDef;
	}

	public void setStatusDef(String statusDef) {
		this.statusDef = statusDef;
	}

}