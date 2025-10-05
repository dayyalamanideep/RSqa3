package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends Basepage {

	public Homepage(WebDriver driver)
	{
		super(driver);
	}
	
@FindBy(xpath= "//span[text()='My Account']")
WebElement  lnkMyaccount;
@FindBy(xpath="//a[text()='Register']")
WebElement lnkRegistration;
@FindBy(linkText ="Login")
WebElement lnklogin;

public void clickMyaccount()
{
	lnkMyaccount.click();
}

public void clickRegister()
{
	lnkRegistration.click();
}

public void clicklogin()
{
	lnklogin.click();
}
	
}
