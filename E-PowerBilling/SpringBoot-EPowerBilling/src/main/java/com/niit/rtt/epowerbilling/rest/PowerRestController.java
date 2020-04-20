package com.niit.rtt.epowerbilling.rest;


import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.niit.rtt.epowerbilling.service.*;
import com.niit.rtt.epowerbilling.exception.*;
import com.niit.rtt.epowerbilling.model.*;
import com.niit.rtt.epowerbilling.repository.*;

@RestController  
@CrossOrigin(origins="http://localhost:4200")  
@RequestMapping(value="/api") 
public class PowerRestController {
	@Autowired
    private LoginUserRepository urepo;
	
	@Autowired
    private LoginUserService lService;
	
	@Autowired
	private ConnectionRepository crepo;
	
	@Autowired
	private ConnectionService cService;
	
	@Autowired
	private BillRepository brepo;
	
	@Autowired
	private BillService bservice;
	
	@PostMapping("/loginUser")
	public String loginUser(@Valid @RequestBody UserDetails user) {
		String email=user.getEmail();
		String pass=user.getPassword();

		StringTokenizer st = new StringTokenizer(email, "@");
		String s2 = st.nextToken();

		User u = lService.checkUser(email);

		if(u==null) {
			return "error User Doesn't Exists";
		}
		else if(email.equals("urvashitomar02@gmail.com") && pass.equals("123"))
		{
			return "admin";
			
		}
		else  if(email.equals(u.getEmail()) && pass.equals(u.getPassword()))
		{
			return "user";
		} 

		else
		{return "error  Invalid Username or Password";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//		Get List of all user
	 @GetMapping("/user")  
	    public List<UserDetails> getAllUser() {  
		 
		 List<User> userlist=(List<User>)urepo.findAll(); 
		 
		 
	     List<UserDetails> list=new ArrayList<UserDetails>();    
		 
		 Iterator<User> itr= userlist.iterator();
		 while(itr.hasNext())
		 {	
			 list.add(convertUserToUserDetails(itr.next()));
		 }
		 return list;
	    }  
	 
//	 convertUserToUserDetails
	 public UserDetails convertUserToUserDetails(User user)
	 {
		 UserDetails userDetails=new UserDetails();
		 userDetails.setuId(user.getuId());
		 userDetails.setFirstName(user.getFirstName());
		 userDetails.setLastName(user.getLastName());
		 userDetails.setGender(user.getGender());
		 userDetails.setMobNumber(user.getMobNumber());
		 userDetails.setEmail(user.getEmail());
		 userDetails.setPassword(user.getPassword());
		 userDetails.setAadhaar(user.getAadhaar());
		 userDetails.setStatus(user.getStatus());
		 Address a=user.getAddress();
		 
		 userDetails.setAddressLine1(a.getAddressLine1());
		 userDetails.setAddressLine2(a.getAddressLine2());
		 userDetails.setCity(a.getCity());
		 userDetails.setCountry(a.getCountry());
		 userDetails.setState(a.getState());
		 userDetails.setPincode(a.getPincode());
		 
		 return userDetails;
	 }
	 /** 
	  * ResponseEntity represents an HTTP response, including headers, body, and status.
	  */
	 
//	 Getting user details by id
	 @GetMapping("/user/{id}")
		public ResponseEntity<UserDetails> getUserById(@PathVariable(value = "id") Integer uId)
				throws ResourceNotFound {

			User user = urepo.findById(uId)
					.orElseThrow(() -> new ResourceNotFound("User not found for this id :: " + uId));
			UserDetails userDetails=convertUserToUserDetails(user);
			return ResponseEntity.ok().body(userDetails);
		}
//	 Save user details in database
	 @PostMapping("/user")  
	    public User saveUser(@Valid @RequestBody UserDetails userdetails) {  
		 //user.setAddress(address);
		  User user= convertUserToSave(userdetails);
		// System.out.println(a);
		 return urepo.save(user) ;
	       	          
	    }  
	 public User convertUserToSave(UserDetails userdetails)
	 {
		 User u=new User();
		 Address a=new Address();
		 u.setFirstName(userdetails.getFirstName());
		 u.setLastName(userdetails.getLastName());
		 u.setGender(userdetails.getGender());
		 u.setMobNumber(userdetails.getMobNumber());
		 u.setEmail(userdetails.getEmail());
		 u.setPassword(userdetails.getPassword());
		 u.setAadhaar(userdetails.getAadhaar());
		 
		 a.setAddressLine1(userdetails.getAddressLine1());
		 a.setAddressLine2(userdetails.getAddressLine2());
		 a.setCity(userdetails.getCity());
		 a.setCountry(userdetails.getCountry());
		 a.setState(userdetails.getState());
		 a.setPincode(userdetails.getPincode());
		 
		 u.setAddress(a);
		 a.setUser(u);
		 return u;
	 }
	 
	 
	 
	 @DeleteMapping("/user/{id}")
	    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Integer uId) 
	    		throws ResourceNotFound{
		 User user = urepo.findById(uId)
					.orElseThrow(() -> new ResourceNotFound("Product not found for this id :: " + uId));
	        urepo.delete(user);
	        
	        Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
	 }
	 
	 @PutMapping("/user/{id}")
	    public ResponseEntity<UserDetails> updateUser(@PathVariable(value = "id") Integer uId,
				@Valid @RequestBody UserDetails u) throws ResourceNotFound {
	     
		 User user = urepo.findById(uId)
					.orElseThrow(() -> new ResourceNotFound("User not found for this id :: " + uId));
		 
		    user.setFirstName(u.getFirstName());
		    user.setLastName(u.getLastName());
		    user.setuId(u.getuId());
		    user.setPassword(u.getPassword());
		    user.setGender(u.getGender());
		    user.setMobNumber(u.getMobNumber());
		    user.setAadhaar(u.getAadhaar());
		    user.setEmail(u.getEmail());
		    user.setStatus(u.getStatus());
		    final UserDetails updatedUser = convertUserToUserDetails(urepo.save(user));
			return ResponseEntity.ok(updatedUser);
	    }
	 
	 
	 
//	 connection methods
	 @PostMapping("/conn")  
	    public ConnectionDetails saveConnection(@Valid @RequestBody ConnectionDummy conn) {  
		 //user.setAddress(address);
		 int Userid=conn.getUserId();		
			User u=lService.find(Userid);		
		 ConnectionDetails connDetails= convertConnectionToSave(conn);
		 connDetails.setUser(u);
		// System.out.println(a);
		 return crepo.save(connDetails) ;
		 	          
	    }  
	 public ConnectionDetails convertConnectionToSave(ConnectionDummy conn)
	 {
		 ConnectionDetails connDetails=new ConnectionDetails();
		 connDetails.setConnectionName(conn.getConnectionName());
		 connDetails.setConnectionType(conn.getConnectionType());
		 connDetails.setConnectionStartDate(conn.getConnectionStartDate());
		 return connDetails;
	 }
	 @PostMapping("/bill")  
	    public BillDetails saveBill(@Valid @RequestBody ConnectionDummy conn) {  
		 //user.setAddress(address);
		 int conid=conn.getConnectionId();	
		ConnectionDetails con=cService.checkConn(conid);
		 BillDetails billDetails= convertBillToSave(conn);
		 billDetails.setUser(con.getUser());
		 billDetails.setConnection(con);
		// System.out.println(a);
		 return brepo.save(billDetails) ;
		 	          
	    }  
	 
	 public BillDetails convertBillToSave(ConnectionDummy conn)
	 {
		 BillDetails billDetails=new BillDetails();
		 billDetails.setBillAmount(conn.getBillAmount());
		 billDetails.setConsumedUnits(conn.getConsumedUnits());
		 billDetails.setDueDate(conn.getDueDate());
		 billDetails.setGenerationDate(conn.getGenerationDate());
		 billDetails.setStatus(conn.getStatus());
		 return billDetails;
	 }
	 
	 
	 
	 @GetMapping("/billList/{billId}")
		public List<ConnectionDummy> getBills(@PathVariable(value = "billId") Integer bId) {
			
			List<BillDetails> billList= bservice.fetchBillDetails(bId);
			List<ConnectionDummy> list=new ArrayList<ConnectionDummy>();    
			 
			 Iterator<BillDetails> itr= billList.iterator();
			 while(itr.hasNext())
			 {	
				 list.add(convertBillToDummy(itr.next()));
			 }
			
			 return list;
		}
	 
	 public ConnectionDummy convertBillToDummy(BillDetails bill)
	 {
		 ConnectionDummy billDetails=new ConnectionDummy();
		 billDetails.setBillId(bill.getBillId());
		 billDetails.setBillAmount(bill.getBillAmount());
		 billDetails.setConsumedUnits(bill.getConsumedUnits());
		 billDetails.setDueDate(bill.getDueDate());
		 billDetails.setGenerationDate(bill.getGenerationDate());
		 billDetails.setStatus(bill.getStatus());
		 return billDetails;
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
