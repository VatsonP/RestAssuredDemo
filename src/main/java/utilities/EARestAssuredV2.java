package utilities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.ExtensionMethod;

import java.util.Map;

public class EARestAssuredV2 {

    private RequestSpecBuilder builder = new RequestSpecBuilder();
    private String method;
    private String url;

    public EARestAssuredV2(String url, String method, String token) {
        //Formulate the API url
        this.url = APIConstant.StrUtils.addSegToPath(APIConstant.BaseURLstr, url);
        this.method = method;
        //Assuming we are using only one type of token across the framework, else we need to pass token as parameter to the constructor
        if (token != null)
            builder.addHeader("Authorization", "Bearer " + token);
    }

    private ResponseOptions<Response> ExecuteAPI() {

        RequestSpecification requestSpec = builder.build();
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.spec(requestSpec);

        Response response;
        if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.POST)) {
            response = request.post(this.url);
        } else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.DELETE)) {
            response = request.delete(this.url);
        } else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.GET)) {
            response = request.get(this.url);
        } else
            response = null;

        return response;
    }


    public String Authenticate(Object body) {
        builder.setBody(body);
        return ExecuteAPI().getBody().jsonPath().get("access_token");
    }

    public ResponseOptions<Response> ExecuteWithBody(Map<String, String> pathParams) {
        builder.addPathParams(pathParams);
        return ExecuteAPI();
    }

    public ResponseOptions<Response> ExecuteWithQueryParams(Map<String, String> queryPath) {
        builder.addQueryParams(queryPath);
        return ExecuteAPI();
    }

    public ResponseOptions<Response> ExecuteWithPathParams(Map<String, String> queryPath) {
        builder.addPathParams(queryPath);
        return ExecuteAPI();
    }

}
