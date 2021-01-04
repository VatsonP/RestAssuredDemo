package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class GETPostsBDDStyledSteps {

    // BDDStyledMethod

    @Given("^I perform GET operation for simpleBDDStyled$")
    public void iPerformGETOperationForSimpleBDDStyled() {
    }

    @And("^I perform GET for the post number \"([^\"]*)\"$")
    public void iPerformGETForThePostNumber(String postNumber) {
        BDDStyledMethod.SimpleGETPost(postNumber);
    }
    @Then("^I should see the author name for simpleBDDStyled$")
    public void iShouldSeeTheAuthorNameForSimpleBDDStyled() {
    }


    @Then("^I should see the author names for simpleBDDStyled$")
    public void iShouldSeeTheAuthorNames()  {
        BDDStyledMethod.PerformContainsCollection();
    }

    @Then("^I should see verify GET PathParameter$")
    public void iShouldSeeVerifyGETPathParameter()  {
        BDDStyledMethod.PerformPathParameter();
    }

    @Then("^I should see verify GET QueryParameter$")
    public void iShouldSeeVerifyGETQueryParameter()  {
        BDDStyledMethod.PerformQueryParameter();
    }

    @Then("^I should see verify POST WithBodyParameter$")
    public void iShouldSeeVerifyGETPOSTWithBodyParameter() {
        BDDStyledMethod.PerformPOSTWithBodyParameter();
    }

}
