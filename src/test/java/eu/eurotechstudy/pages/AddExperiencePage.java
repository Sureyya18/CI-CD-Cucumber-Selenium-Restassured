package eu.eurotechstudy.pages;

import eu.eurotechstudy.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddExperiencePage extends BasePage {

    public void setFieldByName(String fieldName, String value) {
        String fieldLocator = "(//*[@placeholder='" + fieldName + "'])[1]";
        WebElement fieldInput = Driver.getDriver().findElement(By.xpath(fieldLocator));
        fieldInput.sendKeys(value);
    }

    @FindBy(xpath = "(//button[text()='Add Experience'])[2]")
    public WebElement addExperienceButton;
}
