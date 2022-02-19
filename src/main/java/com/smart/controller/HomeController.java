package com.smart.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.dao.UserRepo;
import com.smart.entities.User;
import com.smart.helper.Message;

@Controller
public class HomeController {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@GetMapping("/")
	public String home()
	{
		/*
		 * User us= new User(); us.setName("Aman Kumar"); us.setEmail("aman@gmail.com");
		 */
		//userRepo.save(us);
		return "home";
	}
	@GetMapping("/home")
	public String hom()
	{
		return "home";
	}
	
	@GetMapping("/signup")
	public String sign(Model m)
	{
		m.addAttribute("user", new User());
		return "signup";
	}
	
	@PostMapping("/do_register")
	public String postsign(@ModelAttribute("user") User user,@RequestParam( name = "check", required = false, defaultValue = "false") Boolean check, Model m,HttpSession session)
	{
		
		
		try {
			if(!check) {
				System.out.println("You have not check Terms & Condition");
				throw new Exception("You have not check Terms & Condition");
				//throw new Exception("You have not check Terms & Condition");
			}
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageurl("default.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			System.out.println(user);
			System.out.println(check);
			User save = this.userRepo.save(user);
			System.out.println(save);
			m.addAttribute("user", new User());
			 Message ms =new Message();
			    ms.setText("Successfully Signed up !!!");
			    ms.setError("alert-success");
			   // session.setAttribute("name", "Something went wrong");
			    session.setAttribute("name", ms);
			// session.setAttribute("name", "Successfully Signed up !!");
			return "signup";
			
		} catch (Exception e) {
			System.out.println("################################################");
		    e.printStackTrace();
		    Message ms =new Message();
		    ms.setText("Something Went Wrong !!!"+e.getMessage());
		    ms.setError("alert-danger");
		   // session.setAttribute("name", "Something went wrong");
		    session.setAttribute("name", ms);
		    m.addAttribute("user", user);
		   // session.removeAttribute("name");
		   // session.setAttribute("msg", new Message("Something Went Wrong","alert-danger"));
		    return "signup";
	
		}
		
		
	}
	
	@GetMapping("/signin")
	public String customlogin()
	{
		return "login";
	}
	
}
