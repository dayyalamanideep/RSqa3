package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.Homepage;
import pageobjects.Myaccountpage;
import pageobjects.loginpage;
import testbase.baseclass;
import utilites.DataProviders;

public class Tc_003Logindatadriventestcase extends baseclass {

	
	@Test (dataProvider ="LoginData" , dataProviderClass=DataProviders.class, groups = "Datadriven")
	public void login(String Mail, String psw, String exp )
	{
		//Homepage
		try {
		Homepage hm = new Homepage(driver);
		hm.clickMyaccount();
		hm.clicklogin();
		
		loginpage  lg = new loginpage(driver);
		lg.setemail(Mail);
		lg.setpassword(psw);
		lg.clicklogin();
		
		
		 Myaccountpage ma = new  Myaccountpage(driver);
			boolean targetpage  = 	 ma.isMyaccountpageExists();
		
		 
		//if data is valid - login success --test pass -logot
		//                   login failed ---- test failed 
			
			
		// if data is invaild - login  failed ----- test passsed --
	   //		                 login  passed ------- test failed --   
			
			if (exp.equalsIgnoreCase("valid"))
			{
				if(targetpage == true)
				{
					ma.clickLogout();
					Assert.assertTrue(true);
				}
			}
			else
			{
				Assert.assertTrue(false);
			}
			
			if (exp.equalsIgnoreCase("Invalid"))
			{
				if(targetpage == true)
				{
					ma.clickLogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}}
				catch(Exception e)
				{
					Assert.fail();
				}
		}
	}

