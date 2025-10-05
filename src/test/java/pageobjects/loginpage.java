package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginpage  extends Basepage{

 public	loginpage(WebDriver driver) {
		super(driver);
 }
		
@FindBy(xpath="//input[@name ='email']")
WebElement txtemail;
@FindBy(xpath="//input[@name ='password']")
WebElement  txtpassword;
@FindBy(xpath="//input[@value='Login']")
WebElement btnlogin;
		
public void setemail(String mail)
{
	txtemail.sendKeys(mail);
}

public  void setpassword(String password)
{
	txtpassword.sendKeys(password);
}

public void clicklogin()
{
	btnlogin.click();
}
		
	}


