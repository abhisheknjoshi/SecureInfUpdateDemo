package com.aws.codestar.projecttemplates.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class UserInfo {
	
	public String cid;
	public String fname;
	public String lname;
	public String uname;
	public String password;
	public String contactnumber;
	public String email;
	public String address;
	public String thumb;

	
	public String getCid() {
		return cid;
	}
	
	@JsonSetter
	public void setCid(@JsonProperty("cid") String cid) {
		this.cid = cid;
	}
	
	public String getFname() {
		return fname;
	}
	
	@JsonSetter
	public void setFname(@JsonProperty("fname") String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}
	
	@JsonSetter
	public void setLname(@JsonProperty("lname") String lname) {
		this.lname = lname;
	}
	
	public String getUname() {
		return uname;
	}
	
	@JsonSetter
	public void setUname(@JsonProperty("uname") String uname) {
		this.uname = uname;
	}
	
	public String getPassword() {
		return password;
	}
	
	@JsonSetter
	public void setPassword(@JsonProperty("password") String password) {
		this.password = password;
	}
	
	public String getContactnumber() {
		return contactnumber;
	}
	
	@JsonSetter
	public void setContactnumber(@JsonProperty("contactnumber") String contactnumber) {
		this.contactnumber = contactnumber;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	@JsonSetter
	public void setEmail(@JsonProperty("email") String email) {
		this.email = email;
	}
	
	
	public String getAddress() {
		return address;
	}
	
	@JsonSetter
	public void setAddress(@JsonProperty("address") String address) {
		this.address = address;
	}
	
	
	public String getThumb() {
		return thumb;
	}
	
	@JsonSetter
	public void setThumb(@JsonProperty("thumb") String thumb) {
		this.thumb = thumb;
	}
	
}
