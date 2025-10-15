package eu.eurotechstudy.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OverviewPage extends BasePage {
    @FindBy(css = "[id^='itemexp-']>td~td>span")
    public WebElement firstJobTitle;
}
