package utilities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
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

    /**
     * RestAssuredExtensionv2 constructor to pass the initial settings for the the following method
     * @param url
     * @param method
     * @param token
     */
    public EARestAssuredV2(String url, String method, String token) {
        //Formulate the API url
        this.url = APIConstant.StrUtils.addSegToPath(APIConstant.BaseURLstr, url);
        this.method = method;
        //Assuming we are using only one type of token across the framework, else we need to pass token as parameter to the constructor
        if (token != null)
            builder.addHeader("Authorization", "Bearer " + token);
    }

    /**
     * ExecuteAPI to execute the API for GET/POST/DELETE
     * @return ResponseOptions<Response>
     */
    private ResponseOptions<Response> ExecuteAPI() {

        RequestSpecification requestSpec = builder.build();
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.spec(requestSpec);

        if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.POST)) {
            return request.post(this.url);
        } else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.DELETE)) {
            return request.delete(this.url);
        } else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.GET)) {
            return request.get(this.url);
        }

        return null;
    }

    /**
     * Authenticate to get the token variable
     * @param body
     * @return string token
     */
    public String Authenticate(Object body) {
        builder.setBody(body);
        return ExecuteAPI().getBody().jsonPath().get("access_token");
    }

    /**
     * Executing API
     * @return ResponseOptions<Response>
     */
    public ResponseOptions<Response> Execute() {
        return ExecuteAPI();
    }

    /**
     * Executing API : ExecuteWithBody(...)
     * @param body
     * @return
     */
    public ResponseOptions<Response> ExecuteWithBody(Object body) {
        builder.setBody(body);
        return ExecuteAPI();
    }

    /**
     * Executing API with queryParams being passed as the input of it
     * @param  queryParams
     * @return ResponseOptions<Response>
     */
    public ResponseOptions<Response> ExecuteWithQueryParams(Map<String, String> queryParams) {
        builder.addQueryParams(queryParams);
        return ExecuteAPI();
    }

    /**
     * Executing API with with PathParams being passed as the input of it
     * @param  pathParams
     * @return ResponseOptions<Response>
     */
    public ResponseOptions<Response> ExecuteWithPathParams(Map<String, String> pathParams) {
        builder.addPathParams(pathParams);
        return ExecuteAPI();
    }

    /**
     * Executing API : ExecuteWithBodyAndPathAndQueryParams(...)
     * @param @NotNull  body
     * @param @Nullable pathParams
     * @param @Nullable queryParams
     * @return ResponseOptions<Response>
     */
    public ResponseOptions<Response> ExecuteWithBodyAndPathAndQueryParams(@NotNull  Object body,
                                                                          @Nullable Map<String, String> pathParams,
                                                                          @Nullable Map<String, String> queryParams) {
        if( !(pathParams == null) && !pathParams.isEmpty())
            builder.addPathParams(pathParams);

        if( !(queryParams == null) && !queryParams.isEmpty())
           builder.addQueryParams(queryParams);

        return ExecuteWithBody(body);
    }

}
