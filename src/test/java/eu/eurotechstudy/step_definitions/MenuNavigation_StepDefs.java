package eu.eurotechstudy.step_definitions;

import eu.eurotechstudy.pages.ChartsPage;
import eu.eurotechstudy.pages.DevelopersPage;
import eu.eurotechstudy.utils.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class MenuNavigation_StepDefs {
    DevelopersPage developersPage =new DevelopersPage();
    ChartsPage chartsPage = new ChartsPage();
    @When("user clicks on the {string} menu")
    public void user_clicks_on_the_menu(String menuName) {
        developersPage.getMenuByName(menuName).click();
    }
    @Then("verify that user is on the {string} page")
    public void verify_that_user_is_on_the_page(String pageName) {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(pageName));
    }

    @When("user clicks on the {string} menu and {string} sub-menu")
    public void userClicksOnTheMenuAndSubMenu(String menuName, String subMenuName) {
        chartsPage.getMenuByName(menuName).click();
        chartsPage.getMenuByName(subMenuName).click();
    }
}
