package com.niit.rtt.epowerbilling.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.rtt.epowerbilling.model.*;
import com.niit.rtt.epowerbilling.service.*;

@Controller
public class WebController {
	@Autowired
	BillService bservice;
	
	@Autowired
	LoginUserService loginService;
	
	@RequestMapping("/")
	public String viewHomePage()
	{	
		return "index";
	}
	
	@RequestMapping("/loginPage")
	public String showFormForLogin(ModelMap theModel) {
		//LoginUser theUser=new LoginUser();
		//theModel.addAttribute("user",theUser);
		return "login";
		
	}
	@RequestMapping("/regForm")
	public String showFormForAdd(ModelMap theModel) {
		User theUser=new User();
		theModel.addAttribute("user",theUser);
		return"registration";
	}
	
	@RequestMapping("/connDetails")
	public String viewConnDetailsPage(ModelMap theModel)
	{	ConnectionDetails theConn=new ConnectionDetails();
		theModel.addAttribute("conn", theConn);
		return "connectionDetails";
	}
	
	@RequestMapping("/billDetails")
	public String viewBillDetailsPage(ModelMap theModel)
	{	BillDetails theBill=new BillDetails();
		theModel.addAttribute("bill", theBill);
		return "billDetails";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	   public String index() {
		return "redirect:/";
	   }
	
	@RequestMapping(value="/paymentPage", method = RequestMethod.GET)
	public String showPaymentPage(@RequestParam("email") String email,@RequestParam("billId") String billId,ModelMap theModel)
	{
		User user=new User();
		int id=Integer.parseInt(billId);
		BillDetails bill=bservice.fetchBill(id);
		System.out.println(bill);
		user=loginService.checkUser(email);
		theModel.addAttribute("user", user);
		theModel.addAttribute("bill", bill);
		return "payment";
	}
	
	@RequestMapping(value="/updatePassword", method = RequestMethod.GET)
	public String showUpdatePasswordpage(@RequestParam("email") String email,ModelMap theModel)
	{
		User user=new User();
		user=loginService.checkUser(email);
		theModel.addAttribute("user", user);
		return "change-password";
	}
	
	@GetMapping("/logout")
	 public String logout(HttpServletRequest req) {
	  req.getSession().invalidate();
	  return "index";
	 }
	@RequestMapping(value = "/bill-list", method = RequestMethod.GET)
	public String showBillList(@RequestParam("email") String email, @RequestParam("mobNumber") String mobNumber,@RequestParam("addressLine1") String addressLine1,
			@RequestParam("addressLine2") String addressLine2, @RequestParam("city") String city, @RequestParam("conId") String conId, Model theModel) {
		
		User user=new User();
		
		theModel.addAttribute("email", email);
		theModel.addAttribute("mobNumber", mobNumber);
		theModel.addAttribute("addressLine1", addressLine1);
		theModel.addAttribute("addressLine2", addressLine2);
		theModel.addAttribute("city", city);
		theModel.addAttribute("conId",conId);
		
		theModel.addAttribute("user",user);
		user = loginService.checkUser(email);
		int uid= user.getuId();
		//System.out.println("UID"+uid);
	/*	 List<BillDetails> billList = billService.listAll();
		 mav.addObject("billList", billList);*/
		//List a< > =xyz(uid);
		List<BillDetails> list= bservice.fetchBillDetails(uid);
		theModel.addAttribute("billList", list);
		
		
		 return "user-bills";
	}
	
	
	@GetMapping("/admin")
	 public String admin() {
	  
	  return "admin";
	 }
	

}
	

