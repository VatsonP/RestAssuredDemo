package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo.Posts;
import utilities.RestAssuredExtension;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
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
        System.out.println("post_id= "+post_id);
        assertThat(post_id, equalTo(data.get(1).get(0)));

        String author   = post.getAuthor();
        System.out.println("author= "+author);
        assertThat(author, equalTo(data.get(1).get(1)));

        var title = post.getTitle();
        System.out.println("title= "+title);
    }

}
