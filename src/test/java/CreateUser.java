import Requests.Requests;
import ResponseModels.LoginErrorResponse;
import ResponseModels.LoginResponse;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateUser {
    static String Name= "menna";
    static  String job ="worker";
    static String userName="eve.holt@reqres.in";
    static String password ="cityslicka";
    static String token ="";

    @BeforeClass //preconditions
    public void loginSuccess(){
        Response loginResponse = Requests.loginRequest(userName,password);
        token = loginResponse.jsonPath().getString("token");
    }
    @Test
    public void createusersuccess(){
        Response createuserRequest= Requests.createuserRequest(Name,job);
        // JsonPath loginPath = login.jsonPath();
        CreateUser createUser =createuserRequest.as(CreateUser.class);
        Assert.assertEquals(createuserRequest.statusCode(),200,"Fail");
       // Assert.assertEquals(createUser;
    }

