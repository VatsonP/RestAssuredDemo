package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import lombokdemo.model.Address;
import lombokdemo.model.Location;
import lombokdemo.model.LoginBody;
import lombokdemo.model.Posts;
import utilities.APIConstant;
import utilities.EARestAssuredV2;
import utilities.RestAssuredExtension;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class GETPostsSteps {


    public static ResponseOptions<Response> response;
    public static String token;

    @Given("^I perform GET operation for \"([^\"]*)\"$")
    public void iPerformGETOperationFor(String url) {
        response = RestAssuredExtension.GetOps(url);
    }

    @Then("^I should see the author name as \"([^\"]*)\"$")
    public void iShouldSeeTheAuthorNameAs(String authorName) {
    //      Posts posts = Posts.builder().build();
    //      var   post = response.getBody().as(posts.getClass());
            Posts   post = response.getBody().as(Posts.class);

            assertThat(post.getAuthor(), equalTo(authorName));
    //      assertThat(posts[0].getAuthor(), equalTo("Karthik KK"));
    }

    @Then("^I should see the author names$")
    public void iShouldSeeTheAuthorNames() {
        //assertThat(response.getBody().jsonPath().get("author"), hasItem("Karthik KK"));
        assertThat(response.getBody().jsonPath().get("author"), containsInAnyOrder("Karthik KK", "Karthik KK", null));
    }


    @Given("^I perform GET Parameter operation for \"([^\"]*)\"$")
    public void iPerformGETParameterOperationFor(String url, DataTable table) {
        var data = table.raw();
        Map<String, String> pathParams = new HashMap<>();

        pathParams.put("postid", data.get(1).get(0));

        response = RestAssuredExtension.GetOpsWithPathParameter(url, pathParams);
    }

    @Then("^I should see verify GET Parameter$")
    public void iShouldSeeVerifyGETParameter(DataTable table) {
        var data = table.raw();

        Posts   post = response.getBody().as(Posts.class);

        String post_id  = Integer.toString(post.getId());
        assertThat(post_id, equalTo(data.get(1).get(0)));
    }

    //----------------------------------------------------------------------------------------------------------

    @Given("^I perform GET operation with token for \"([^\"]*)\"$")
    public void iPerformGETOperationWithTokenFor(String url) {
        response = RestAssuredExtension.GetOpsWithToken(url, token);
    }

    //Deserialize
    @Then("^I should see the author name as \"([^\"]*)\" with json validation$")
    public void iShouldSeeTheAuthorNameAsWithJsonValidation(String authorName) {

        var a = response.getBody().asString();

        assertThat(a, matchesJsonSchemaInClasspath("post.json"));

//        assertThat(response.getBody().jsonPath().get("author"), hasItem("Karthik KK"));
    }


    @Given("^I perform authentication operation for \"([^\"]*)\" with body$")
    public void iPerformAuthenticationOperationForWithBody(String url, DataTable table) {

        var data = table.raw();

//        HashMap<String, String> body = new HashMap<>();
//        body.put("email", data.get(1).get(0));
//        body.put("password", data.get(1).get(1));

        LoginBody loginBody = new LoginBody();
        loginBody.setEmail(data.get(1).get(0));
        loginBody.setPassword(data.get(1).get(1));

        EARestAssuredV2 eaRestAssuredV2 = new EARestAssuredV2(url, APIConstant.ApiMethods.POST, token);

        //token = eaRestAssuredV2.Authenticate(body);

        token = eaRestAssuredV2.Authenticate(loginBody);
    }


    // Feature: ComplexDataGet ----------------------------------------------------------------------------------
    @And("^I perform GET operation with path parameter for address \"([^\"]*)\"$")
    public void iPerformGETOperationWithPathParameterForAddress(String url, DataTable table) {
        var data = table.raw();

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("id", data.get(1).get(0));

        //response = RestAssuredExtension.GetWithQueryParamsWithToken(url, pathParams, response.getBody().jsonPath().get("access_token"));

        EARestAssuredV2 eaRestAssuredV2 = new EARestAssuredV2(url, APIConstant.ApiMethods.GET, token);
        response = eaRestAssuredV2.ExecuteWithQueryParams(queryParams);
    }

    @Then("^I should see the street name as \"([^\"]*)\"$")
    public void iShouldSeeTheStreetNameAs(String streetName) {

        var a = response.getBody().as(Location[].class);

        Address address = a[0].getAddress().stream().filter(x -> x.getType().equalsIgnoreCase("primary")).findFirst().orElse(null);

        assertThat(address.getStreet(), equalTo(streetName));


//        assertThat(a[0].getAddress().getStreet(), equalTo(streetName));
    }

    //----------------------------------------------------------------------------------------------------------
}
