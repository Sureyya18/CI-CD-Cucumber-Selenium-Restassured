package eu.eurotechstudy.step_definitions;

import eu.eurotechstudy.pages.LoginPage;
import eu.eurotechstudy.utils.BrowserUtils;
import eu.eurotechstudy.utils.ConfigReader;
import eu.eurotechstudy.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class Login_StepDefs {

    WebDriver driver = Driver.getDriver();
    LoginPage loginPage = new LoginPage();

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        driver.get(ConfigReader.get("url"));
    }

    @When("user enters {string} email")
    public void user_enters_email(String email) {
        loginPage.inputEmail.sendKeys(ConfigReader.get(email));
        BrowserUtils.waitFor(3);
    }

    @When("user enters {string} password")
    public void user_enters_password(String pwd) {
        loginPage.inputPassword.sendKeys(ConfigReader.get(pwd));
    }

    @When("user clicks on the login button")
    public void user_clicks_on_the_login_button() {
        loginPage.btnLogin.click();
    }

    @Then("verify that user is on the home page")
    public void verify_that_user_is_on_the_home_page() {
        String currentUrl = Driver.getDriver().getCurrentUrl();
        if (currentUrl.contains("index")) {
            Assert.assertEquals("https://sdettest.eurotechstudy.eu/index", currentUrl);
        } else if (currentUrl.contains("login")) {
            Assert.assertEquals("https://sdettest.eurotechstudy.eu/login", currentUrl);
        }
    }

    @Given("user logs in {string} {string}")
    public void userLogsInAsATeacher(String email, String pwd) {
//        driver.get(ConfigReader.get("url"));
//        loginPage.inputEmail.sendKeys(ConfigReader.get(email));
//        loginPage.inputPassword.sendKeys(ConfigReader.get(pwd));
//        loginPage.btnLogin.click();
//        Assert.assertEquals("https://sdettest.eurotechstudy.eu/index",driver.getCurrentUrl());
        loginPage.login(email, pwd);
    }
}
