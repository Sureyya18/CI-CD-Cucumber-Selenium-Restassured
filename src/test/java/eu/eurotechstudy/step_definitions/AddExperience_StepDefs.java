package eu.eurotechstudy.step_definitions;

import eu.eurotechstudy.pages.AddExperiencePage;
import eu.eurotechstudy.pages.MyProfilePage;
import eu.eurotechstudy.pages.OverviewPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AddExperience_StepDefs {

    MyProfilePage myProfilePage = new MyProfilePage();
    AddExperiencePage addExperiencePage = new AddExperiencePage();
    OverviewPage overviewPage = new OverviewPage();

    @When("user clicks on the {string} section")
    public void user_clicks_on_the_section(String section) {
        myProfilePage.getSectionByName(section).click();
    }
    @When("user enters {string} {string} in Add Experience")
    public void user_enters_in_add_experience(String field, String value) {
        addExperiencePage.setFieldByName(field,value);
    }
    @When("user clicks on the Add Experience Button")
    public void user_clicks_on_the_add_experience_button() {
        addExperiencePage.addExperienceButton.click();
    }
    @Then("verify that user sees experience details on the Overview section")
    public void verify_that_user_sees_experience_details_on_the_overview_section() {
        Assert.assertEquals("SDET",overviewPage.firstJobTitle.getText());
    }
}
