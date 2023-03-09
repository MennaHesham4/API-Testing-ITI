import Requests.Requests;
import ResponseModels.LoginErrorResponse;
import ResponseModels.LoginResponse;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testLogin {
    static String userName= "eve.holt@reqres.in";
    static  String password ="cityslicka";
    static String baseURL="https://reqres.in/api";
    static String singleUserEndpoint ="/users";


    @Test//hard assert
    public void loginSuccess(){
        Response login= Requests.loginRequest(userName,password);
       // JsonPath loginPath = login.jsonPath();
        LoginResponse lr=login.as(LoginResponse.class);
        Assert.assertEquals(login.statusCode(),200,"Fail");
        Assert.assertNotNull(lr.token,"token is null");
    }
    @Test//hard assert
    public void loginFailUsername(){
        Response login= Requests.loginRequest("",password);
       // JsonPath loginPath = login.jsonPath();
        LoginErrorResponse er=login.as(LoginErrorResponse.class);
        Assert.assertEquals(login.statusCode(),400,"Fail");
        Assert.assertEquals(er.error,"Missing email or username");
    }
    @Test//hard assert
    public void loginFailPassword(){
        Response login= Requests.loginRequest(userName,"");
        JsonPath loginPath = login.jsonPath();

        Assert.assertEquals(login.statusCode(),400,"Fail");
        Assert.assertEquals(loginPath.getString("error"),"Missing password");
    }
    @Test//soft assert
    public void softloginSuccess(){
        Response login= Requests.loginRequest(userName,password);
        JsonPath loginPath = login.jsonPath();
        SoftAssert softassert = new SoftAssert();
        softassert.assertEquals(login.statusCode(),200,"Fail");
        softassert.assertNotNull(loginPath.getString("token"),"token is null");
        softassert.assertAll();
    }
}
