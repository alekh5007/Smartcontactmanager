package com.smart.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smart.dao.UserRepo;
import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.helper.Message;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
   private  UserRepo userRepo;
	
	@ModelAttribute
	public void addcommondata(Model model,Principal principal)
	{
		String name = principal.getName();
		System.out.println(name);
		User user = userRepo.getUserByUserName(name);
		System.out.println(user);
		model.addAttribute("user", user);
	}
	
	@GetMapping("/user_dashboard")
	public String user_dashboard(Model model, Principal principal)
	{
		
		return"normal/user_dashboard";
	}
	
	@GetMapping("/addcontact")
	public String addContactform(Model m)
	{
		m.addAttribute("contact", new Contact());
		return "normal/addcontactform";
	}
	
	@PostMapping("/do_addconttac")
	public String do_addconttac(@ModelAttribute Contact contact, Principal principal,Model m,HttpSession session)
	{
		try {
			String name = principal.getName();
			  User user = this.userRepo.getUserByUserName(name);
			  
				/*
				 * if(file.isEmpty()) { System.out.println("file is empty"); }else {
				 * System.out.println("file is there");
				 * contact.setImage(file.getOriginalFilename()); File savefile = new
				 * ClassPathResource("static/img").getFile(); Path path =
				 * Paths.get(savefile.getAbsolutePath()+File.separator+file.getOriginalFilename(
				 * )); Files.copy(file.getInputStream(), path,
				 * StandardCopyOption.REPLACE_EXISTING); }
				 */
			  
			  contact.setUser(user);
			  user.getContact().add(contact);
			  this.userRepo.save(user);
			  
			  m.addAttribute("user", new User());
				 Message ms =new Message();
				    ms.setText("Successfully Added Contact !!!");
				    ms.setError("alert-success");
				   // session.setAttribute("name", "Something went wrong");
				    session.setAttribute("name", ms);
			  
			System.out.println("inside contact....++++");
			System.out.println(contact);
			
		} catch (Exception e) {
			System.out.println("ERROR"+e.getMessage());
			e.printStackTrace();
		}
		  
		return "normal/addcontactform";
	}
	
	@GetMapping("/viewcontact")
	public String viewcontact(Model model,Principal principal) {
		String name = principal.getName();
		User userByUserName = this.userRepo.getUserByUserName(name);
		List<Contact> contact = userByUserName.getContact();
		model.addAttribute("contact", contact);
		return "normal/viewcontact";
	}
	

}
