package eu.eurotechstudy.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {
    @FindBy(xpath = "//div[.='Email']/following-sibling::div")
    public WebElement email;


}
