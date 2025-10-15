package eu.eurotechstudy.pages;

import eu.eurotechstudy.utils.ConfigReader;
import eu.eurotechstudy.utils.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = "#email")
    public WebElement inputEmail;

    @FindBy(css = "#yourPassword")
    public WebElement inputPassword;

    @FindBy(xpath = "//div[@class=\"col-12\"]/button")
    public WebElement btnLogin;

    public void login(String email, String password){
        Driver.getDriver().get(ConfigReader.get("url"));
        inputEmail.sendKeys(ConfigReader.get(email));
        inputPassword.sendKeys(ConfigReader.get(password));
        btnLogin.click();
        Assert.assertEquals("https://sdettest.eurotechstudy.eu/index",Driver.getDriver().getCurrentUrl());
    }


}
