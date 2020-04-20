package com.niit.rtt.epowerbilling.controller;

import java.sql.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.niit.rtt.epowerbilling.model.*;
import com.niit.rtt.epowerbilling.service.*;


@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private LoginUserService lService;
	@Autowired
	private BillService billService;
	@Autowired
	private ConnectionService conService;

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(HttpServletRequest req,@ModelAttribute("user") User user) {
		String adl1=req.getParameter("addressLine1");
		String adl2=req.getParameter("addressLine2");
		String city=req.getParameter("city");
		String state=req.getParameter("state");
		String country=req.getParameter("country");
		int pin=Integer.parseInt(req.getParameter("pincode"));
		Address a=new Address();
		a.setAddressLine1(adl1);
		a.setAddressLine2(adl2);
		a.setCity(city);
		a.setCountry(country);
		a.setState(state);
		a.setPincode(pin);
		a.setUser(user);

		userService.save(a);

		return "index";
	}
	@PostMapping("/loginUser")
	public ModelAndView loginDealer(HttpServletRequest req,@ModelAttribute("user") User user) {
		String email=req.getParameter("email");
		String pass=req.getParameter("password");

		StringTokenizer st = new StringTokenizer(email, "@");
		String s2 = st.nextToken();

		ModelAndView mav=null;
		User u = lService.checkUser(email);

		if(u==null) {
			mav= new ModelAndView("login");
			mav.addObject("error", "User Doesn't Exists");
		}
		else if(email.equals("urvashitomar02@gmail.com") && pass.equals("123"))
		{
			req.getSession().setAttribute("user", s2);
			mav = new ModelAndView("admin");
			mav.addObject("user", u);
			List<User> listUser=lService.listAll();
			mav.addObject("listUser", listUser);
		}
		else  if(email.equals(u.getEmail()) && pass.equals(u.getPassword()))
		{
			int uid=u.getuId();
			req.getSession().setAttribute("user", s2);	
			ConnectionDetails con=conService.fetchConnectionDetails(uid);
			BillDetails userBill= billService.fetchBillById(uid,"unpaid");
			String status=userBill.getStatus();
			if(status.equals("unpaid") && userBill!=null) 
			{
				
				mav = new ModelAndView("home");
				mav.addObject("user", u);
				mav.addObject("userBill",userBill);
				mav.addObject("conection",con);
			}
			else if(status.equals("paid")) {
				mav = new ModelAndView("paid-home");
				mav.addObject("user", u);
			}
			else if(userBill.getStatus()==null) {
				mav = new ModelAndView("paid-home");
				mav.addObject("user", u);
			}
			//mav = new ModelAndView("home");
			//mav.addObject("user", u);
		} 

		else
		{mav= new ModelAndView("login");
		mav.addObject("error", "Invalid Username or Password");
		}

		return mav;
	}
	
	@RequestMapping(value="/update-password",method = RequestMethod.POST)
	public ModelAndView showUpdatePassword(HttpServletRequest req,@ModelAttribute("user") User user1)
	{
		String email=req.getParameter("email");
		ModelAndView mav=null;
		User user= new User(); 
		user=lService.checkUser(email);
		int id=user.getuId();
		System.out.println("id :"+id);
		String oldPass=req.getParameter("oldPassword");
		String newPass=req.getParameter("newPassword");
		System.out.println("old Pass");
		user=lService.find(id);
		String msg="Password Updated";
		String errorMsg="wrong Password";
		if(id==user.getuId()&&oldPass.equals(user.getPassword())) {
			mav=new ModelAndView("change-password");
			user.setPassword(newPass);
			lService.save(user);
			mav.addObject("msg",msg);
		}
		
		else {
			mav=new ModelAndView("change-password");
			mav.addObject(errorMsg, errorMsg);
			
		}
		return mav;
	
	}
	
	@RequestMapping(value = "/savePayment", method = RequestMethod.POST)
	public String savePayment(HttpServletRequest req,@ModelAttribute("bill") BillDetails billDetails) {
		String nameOnCard=req.getParameter("cardname");
		String cardNumber=req.getParameter("cardnumber");
		String expMonth=req.getParameter("expmonth");
		String expYear=req.getParameter("expyear");
		String amount=req.getParameter("amount");
		String cvv=req.getParameter("cvv");
		int id=Integer.parseInt(req.getParameter("billId"));
		
		BillDetails bill=billService.fetchBill(id);
		
		bill.setStatus("paid");
		
		Date date=new Date(System.currentTimeMillis());
		
		bill.setSubmitDate(date);
		
		Payment p=new Payment();
		p.setNameOnCard(nameOnCard);
		p.setCardNumber(cardNumber);
		p.setExpMonth(expMonth);
		p.setExpYear(expYear);
		p.setAmount(amount);
		p.setCvv(cvv);
		p.setBillDetails(bill);

		billService.save(p);

		return "index";
	}
	
}
