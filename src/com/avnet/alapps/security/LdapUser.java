package com.avnet.alapps.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class LdapUser {

	private String avnetGlobalUserId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String genesisId;
	private List<String> groupNames = new ArrayList<String>();

	public String getAvnetGlobalUserId() {
    	return avnetGlobalUserId;
    }
	public void setAvnetGlobalUserId(String avnetGlobalUserId) {
    	this.avnetGlobalUserId = avnetGlobalUserId;
    }
	public String getFirstName() {
    	return firstName;
    }
	public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }
	public String getLastName() {
    	return lastName;
    }
	public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
	public String getEmailAddress() {
    	return emailAddress;
    }
	public void setEmailAddress(String emailAddress) {
    	this.emailAddress = emailAddress;
    }
	public List<String> getGroupNames() {
		return groupNames;
	}
	public void setGroupNames(List<String> groupNames) {
		this.groupNames = groupNames;
	}
	public String getGenesisId() {
		return genesisId;
	}
	public void setGenesisId(String genesisId) {
		this.genesisId = genesisId;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
