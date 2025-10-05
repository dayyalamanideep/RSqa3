package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistationpage  extends  Basepage {

	public AccountRegistationpage( WebDriver driver)
	{
		super(driver);
	}
	
@FindBy(xpath="//input[@name='firstname']")
WebElement txtFirstname;
@FindBy(xpath="//input[@name='lastname']")
WebElement txtLastname;
@FindBy(xpath ="//input[@name='email']")
WebElement txtMail;
@FindBy(xpath= "//input[@name='telephone']")
WebElement txtTelephone;
@FindBy(xpath="//input[@name='password']")
WebElement txtpassword;
@FindBy(xpath="//input[@name='confirm']")
WebElement txtconfrimpassword;
@FindBy(xpath ="//input[@name='agree']")
WebElement chkpolicy;
@FindBy(xpath ="//input[@value='Continue']")
WebElement btncontinue;
@FindBy(xpath ="//h1[text()='Your Account Has Been Created!']")
WebElement Messageconformation;

public void setFirstName(String fname)
{
	txtFirstname.sendKeys(fname);
}

public void setLastName(String lname)
{
txtLastname.sendKeys(lname);
}
public void  setMail(String Mail)
{
	txtMail.sendKeys(Mail);
	
}
public void setTelephone(String Telephone)
{
	txtTelephone.sendKeys(Telephone);
	
}
public void setPassword (String password)
{
	txtpassword.sendKeys(password);
}
public void setconfirmpassword(String password)
{
	txtconfrimpassword.sendKeys(password);
}
public void clickpolicy ()
{
	chkpolicy.click();
}
public void clickcontinue()
{
	btncontinue.click();
}
 public String getconfirmationmsg()
 {
	 try
	 {
	 return (Messageconformation.getText());
	 }
	 catch(Exception e)
	 {
		return  (e.getMessage());
	 }
 }
}