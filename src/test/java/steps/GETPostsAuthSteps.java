package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo.Address;
import pojo.Location;
import pojo.Posts;
import lombokdemo.model.LoginBody;
import org.hamcrest.core.Is;
import utilities.APIConstant;
import utilities.EARestAssuredV2;
import utilities.RestAssuredExtension;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class GETPostsAuthSteps {


    private static ResponseOptions<Response> response;
    private static String token;


    @Given("^I perform authentication operation for \"([^\"]*)\" with body$")
    public void iPerformAuthenticationOperationForWithBody(String url, DataTable table) {

        var data = table.raw();

        HashMap<String, String> body = new HashMap<>();
        body.put("email", data.get(1).get(0));
        body.put("password", data.get(1).get(1));

        // standard realization (V1), like call for POST request method
        response = RestAssuredExtension.PostOpsWithBody(url, body);
        token    = response.getBody().jsonPath().get("access_token");

        // new V2a
        //EARestAssuredV2 eaRestAssuredV2 = new EARestAssuredV2(url, APIConstant.ApiMethods.POST, token);
        //token = eaRestAssuredV2.Authenticate(body);

     /* // new V2b
        LoginBody loginBody = new LoginBody();
        loginBody.setEmail(data.get(1).get(0));
        loginBody.setPassword(data.get(1).get(1));

        EARestAssuredV2 eaRestAssuredV2 = new EARestAssuredV2(url, APIConstant.ApiMethods.POST, token);
        token = eaRestAssuredV2.Authenticate(loginBody);
    */
    }

    @Given("^I perform GET operation with token for \"([^\"]*)\"$")
    public void iPerformGETOperationWithTokenFor(String url) {
        response = RestAssuredExtension.GetOpsWithToken(url, token);
    }


    @Then("^I should check the author names like \"([^\"]*)\"$")
    public void iShouldCheckTheAuthorNamesLike(String authorName)  {
        System.out.println("authorName= " + authorName);

        assertThat(response.getBody().jsonPath().get("author"), containsInAnyOrder(authorName, authorName, null));
    }


    @Given("^I perform GET operation with queryParam and token for \"([^\"]*)\"$")
    public void iPerformGETOperationWithQueryParamAndTokenFor(String url, DataTable table) {

        var data = table.raw();

        Map<String, String> pathParam = new HashMap<>();
        pathParam.put(data.get(0).get(0), data.get(1).get(0));

        response = RestAssuredExtension.GetWithQueryParamsWithToken(url, pathParam, token);
    }

    @Then("^I should check the author name as \"([^\"]*)\"$")
    public void iShouldCheckTheAuthorNameAs(String authorName)  {
        var responseAuthorName = response.getBody().jsonPath().getString("author");

        System.out.println("responseAuthor= "+ responseAuthorName);

        assertThat(response.getBody().jsonPath().get("author"), hasItem(authorName));
    }

    //Deserialize
    @Then("^I should see the author name as \"([^\"]*)\" with json validation$")
    public void iShouldSeeTheAuthorNameAsWithJsonValidation(String authorName) {
        // V1
        Posts   post = response.getBody().as(Posts.class);
        assertThat(post.getAuthor(), equalTo(authorName));

    /*  // V2
        var respStr = response.getBody().asString();
        assertThat(respStr, matchesJsonSchemaInClasspath("post.json"));
    */
    }


    // Feature: ComplexDataGet ----------------------------------------------------------------------------------

    @And("^I perform GET operation with with queryParam for address \"([^\"]*)\"$")
    public void iPerformGETOperationWithPathParameterForAddress(String url, DataTable table) {
        var data = table.raw();

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("id", data.get(1).get(0));

        // V1
        response = RestAssuredExtension.GetWithQueryParamsWithToken(url, queryParams, response.getBody().jsonPath().get("access_token"));

     /* // V2
        EARestAssuredV2 eaRestAssuredV2 = new EARestAssuredV2(url, APIConstant.ApiMethods.GET, token);
        response = eaRestAssuredV2.ExecuteWithQueryParams(queryParams);
     */
    }

    @Then("^I should see the street name as \"([^\"]*)\"$")
    public void iShouldSeeTheStreetNameAs(String streetName) {

        var a = response.getBody().as(Location[].class);
        // V1
        assertThat(a[0].getAddress().listIterator().next().getStreet(), equalTo(streetName));

    /* // V2
        Address address = a[0].getAddress().stream().filter(x -> x.getType().equalsIgnoreCase("primary")).findFirst().orElse(null);

        assertThat(address.getStreet(), equalTo(streetName));
     */
    }

}
