package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.AccountRegistationpage;
import pageobjects.Homepage;
import testbase.baseclass;

public class Tc_001AccountRegistrationtest extends baseclass{

	

	
	@Test(groups ={"regression" ,"master" })
	public void Testregistration()
	{
		try {
		logger.info("starting Tc_001AccountRegistrationtest");
		Homepage hm = new Homepage(driver);
		hm.clickMyaccount();
		logger.info("Clicked on my account link");
		
		hm.clickRegister();
	logger.info("clicked on Registration link");
	
	AccountRegistationpage  Ar= new AccountRegistationpage(driver);
	logger.info("providing the customer details");
	Ar.setFirstName(randomestring().toUpperCase());
	Ar.setLastName(randomestring().toUpperCase());
	Ar.setMail(randomestring()+"@gmail.com");
String password = randomAlphanumeric();
	
	Ar.setPassword(password);
	Ar.setconfirmpassword(password);
	Ar.setTelephone(randomNumber());
	Ar.clickpolicy();
	Ar.clickcontinue();
	
	logger.info("validating exetected message..");
	String confmsg= Ar.getconfirmationmsg();
	
	if (confmsg.equals("Your Account Has Been Created!"))
	{
		Assert.assertTrue(true);
	}
	else
	{
		Assert.assertTrue(false);
		logger.error("Test case failed");
		logger.debug("Debug logs");
	}
		}
		catch(Exception e)
		{
		
			Assert.fail();
		}
		logger.info("finished Tc_001AccountRegistrationtest");
	}
	
}
