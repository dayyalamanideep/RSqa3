package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Myaccountpage extends  Basepage{
	public Myaccountpage(WebDriver driver)
	{
		super(driver);
	}

@FindBy( xpath="//h2[text()='My Account']")//my account page heading
WebElement msgheading; 
@FindBy(xpath ="//a[@class='list-group-item' and text()='Logout']")
WebElement logout;
 public boolean  isMyaccountpageExists()
 {
try
{
	return(msgheading.isDisplayed());
}
catch(Exception e)
{
	return false;
}
 }
public void clickLogout()
{
	logout.click();
}
}