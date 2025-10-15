package eu.eurotechstudy.pages;

import eu.eurotechstudy.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyProfilePage extends BasePage {

    public WebElement getSectionByName(String sectionName) {
        String sectionLocator = "(//button[text()='" + sectionName + "'])[1]";
        return Driver.getDriver().findElement(By.xpath(sectionLocator));
    }

}
