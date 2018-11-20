package com.aws.codestar.projecttemplates.controller;

import java.util.LinkedHashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aws.codestar.projecttemplates.entities.UserInfo;
import com.aws.codestar.projecttemplates.model.GenerateOTP;

@RestController
@CrossOrigin("http://localhost:4200")
public class InfoUpdateController {
	
	private GenerateOTP generateOTP;
	
	private LinkedHashMap<String, UserInfo> userInfoDB = new LinkedHashMap<String, UserInfo>();
	private LinkedHashMap<String, UserInfo> userUpdateInfoDB = new LinkedHashMap<String, UserInfo>();
	private LinkedHashMap<String, String> otpMap = new LinkedHashMap<String, String>();

	public InfoUpdateController(GenerateOTP generateOTP) {
		// TODO Auto-generated constructor stub
		this.generateOTP=generateOTP;
	}
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello";
	}
	
	@RequestMapping(value="/verifyOTPCreate" , method = RequestMethod.POST)
	public String registerUser(@RequestParam("cid") String cid, @RequestParam("otp") String otp) {
		if((otpMap.get(cid)).equals(otp)) {
			otpMap.remove(cid);
			return "OTP verified";
		}
		else
		{
			if(otpMap.containsKey(cid)) {
				otpMap.remove(cid);
				userInfoDB.remove(cid);
			}						
			return "OTP verification Failed";
		}
	}

	@RequestMapping(value="/login" , method = RequestMethod.POST)
	public String loginUser(@RequestParam("cid") String cid,@RequestParam("uname") String uname ,@RequestParam("password") String password) {
		if((userInfoDB.get(cid).getCid()).equals(cid) && (userInfoDB.get(cid).getUname()).equals(uname) && (userInfoDB.get(cid).getPassword()).equals(password)) {
//			otpMap.remove(cid);
//			userInfoDB.put(cid,userUpdateInfoDB.get(cid));
			return "Login Successful";
		}
		else
		{
			return "Login Failed";
		}
	}

	
	
	@RequestMapping(value="/verifyOTPUpdate" , method = RequestMethod.POST)
	public String updateUser(@RequestParam("cid") String cid, @RequestParam("otp") String otp) {
		if((otpMap.get(cid)).equals(otp)) {
			otpMap.remove(cid);
			userInfoDB.put(cid,userUpdateInfoDB.get(cid));
			return "OTP verified";
		}
		else
		{
			if(otpMap.containsKey(cid)) {
				otpMap.remove(cid);
				userInfoDB.remove(cid);
			}						
			return "OTP verification Failed";
		}
	}
	

	@RequestMapping(value="/getInfo/{cid}" , method = RequestMethod.GET)
	public UserInfo getUserInfo(@PathVariable String cid) {
		UserInfo userInfo = userUpdateInfoDB.get(cid);
		return userInfo;
	}
	
	
	@RequestMapping(value="/updateInfo" , method = RequestMethod.POST)
	public UserInfo updateUserInfo(@RequestBody UserInfo userInfo) {
		userUpdateInfoDB.put(userInfo.getCid(), userInfo);
		String otp = generateOTP.sendOTP(userInfo.getContactnumber());
		otpMap.put(userInfo.getCid(), otp);
		return userInfo;
	}
	
	
	@RequestMapping(value="/register" , method = RequestMethod.POST)
	public UserInfo registerUser(@RequestBody UserInfo userInfo) {
		userInfoDB.put(userInfo.getCid(), userInfo);
		String otp = generateOTP.sendOTP(userInfo.getContactnumber());
		otpMap.put(userInfo.getCid(), otp);
		return userInfo;
	}

}
