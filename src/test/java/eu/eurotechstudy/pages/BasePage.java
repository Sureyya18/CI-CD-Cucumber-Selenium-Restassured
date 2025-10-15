package eu.eurotechstudy.pages;

import eu.eurotechstudy.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public WebElement getMenuByName(String menuName) {
        String menuLocator = "//span[text()='" + menuName + "']";
        return Driver.getDriver().findElement(By.xpath(menuLocator));
    }
}
