package com.niit.rtt.epowerbilling;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.niit.rtt.epowerbilling.controller.UserController;
import com.niit.rtt.epowerbilling.controller.WebController;
import com.niit.rtt.epowerbilling.service.BillService;
import com.niit.rtt.epowerbilling.service.LoginUserService;
@RunWith(SpringRunner.class)
@SpringBootTest(classes= {UserController.class,WebController.class})
public class LoginControllerTest {
	//@InjectMocks
	//UserController userController;
	
	@InjectMocks
	WebController webController;
//	
	@MockBean
	BillService bservice;
	
	@MockBean
	LoginUserService loginService;
//	@Mock
//	private UserService userService;
//	@Mock
//	private LoginUserService lService;
//	@Mock
//	private BillService billService;
//	@Mock
//	private ConnectionService conService;
//	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void viewHomePageTest()
	{
		Assert.assertEquals(webController.viewHomePage(),"index");
	}
	
//	@Test
//	public void saveUserTest()
//	{	
//		User d=new User();
//		d.setuId(10);
//		d.setEmail("raj@test.com");
//		d.setFirstName("Raj");
//		d.setLastName("gs");
//		d.setPassword("123");
//		d.setGender("male");
//		d.setAadhaar("321456987");
//		d.setStatus("unapproved");
//		d.setMobNumber("2222222");
//		
//		Address a=new Address();
//		a.setState("UP");
//		a.setCity("Delhi");
//		a.setUser(d);
//		a.setPincode(201206);
//		
//		d.setAddress(a);
//		lService.save(d);
//		verify(lService,times(1)).save(d);
//		
//	}
}
