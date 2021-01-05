package steps;

import cucumber.api.java.Before;
import lombok.extern.slf4j.Slf4j;
import utilities.RestAssuredExtension;
import utilities.APIConstant;

public class TestInitialize {

    @Before
    public void TestSetup(){

        BDDStyledMethod.setBaseURIstr(APIConstant.BaseURIstr);

        RestAssuredExtension restAssuredExtension = new RestAssuredExtension(APIConstant.BaseURIstr);
    }
}
