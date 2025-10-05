package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.Homepage;
import pageobjects.Myaccountpage;
import pageobjects.loginpage;
import testbase.baseclass;

public class Tc_002Accountlogin  extends baseclass {

	@Test(groups ={"sanity", "regression","master"} )
	public void testlogin()
	{
		logger.info("Test execution started");
		try{
		
		Homepage hm = new Homepage(driver);//homepage object is craeted and called clicklogin() method
		hm.clicklogin();
		
		
		loginpage lg = new loginpage(driver);
		lg.setemail(p.getProperty("email"));
		
		lg.setpassword(p.getProperty("password"));
		
		lg.clicklogin();
		
		 Myaccountpage mp = new  Myaccountpage(driver);
		boolean  targetpage= mp.isMyaccountpageExists();
		 Assert.assertTrue(targetpage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		 
		
	}
	
	
}
