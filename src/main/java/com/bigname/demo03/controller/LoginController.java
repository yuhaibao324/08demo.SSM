package com.bigname.demo03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bigname.demo03.core.Member;
import com.bigname.demo03.function.IMemberFunction;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class LoginController {

	@Autowired
	IMemberFunction iMemberFunc;
	
	@RequestMapping(value = "/hello")
	public String hello(){
		System.out.println("Hello");
		return "hi";
	}
	
	@RequestMapping(value = "/login")
	@ResponseBody
	public HashMap<String, Object> login(String name, String password){
		//check the parameter
		System.out.println("name:" + name + "\n");
		System.out.println("password:" + password + "\n");
		HashMap<String, Object> retMap = new HashMap<String, Object>();

		try {
			Member member  = iMemberFunc.login(name, password);
			retMap.put("name:", member.getName());
			retMap.put("passsword:", member.getPassword());
			System.out.println("member:"  + member.getName() );
			if(member == null){
				System.out.println("没有找到");
				retMap.put("status", "没有找到");
			}else {
				System.out.println("已经查找到");
                retMap.put("status", "已经查找到");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return retMap;

	}
}
