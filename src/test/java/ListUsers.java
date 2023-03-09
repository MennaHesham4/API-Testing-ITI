import Requests.Requests;
import ResponseModels.ListUsersResponse.ListUserResponse;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ListUsers {
    static String userName= "eve.holt@reqres.in";
    static  String password ="cityslicka";
    static String baseURL="https://reqres.in/api";


    String token="";

    @BeforeClass //preconditions
    public void loginSuccess(){
        Response loginResponse = Requests.loginRequest(userName,password);
        token = loginResponse.jsonPath().getString("token");
    }
    @Test
    public void listUsesSuccess(){
        Response listUser_Response=Requests.listUserResponse(token,"2");
        //JsonPath listUserPath =listUser_Response .jsonPath();
        ListUserResponse listuser_res = listUser_Response.as(ListUserResponse.class);
        Assert.assertEquals(listUser_Response.statusCode(),200,"fail");
        Assert.assertNotNull(listuser_res.perPage,"Null");
    }
    @Test
    public void listUsesSuccess2(){
        Response listUser_Response=Requests.listUserResponse(token,"2");
        JsonPath listUserPath = listUser_Response.jsonPath();
       // ListUserResponse list_res=listUser_Response.as(ListUserResponse.class);
        Assert.assertEquals(listUser_Response.statusCode(),200,"fail");
        //Assert.assertNotNull(listUserPath.getString("page"),"Null");
        //Assert.assertEquals(listUserPath.getString("total_pages"),"2");
        //int le=listUserPath.getList("data").size();
        //Assert.assertEquals(le,listUserPath.getInt("per_page"));

        for (int i=0;i<listUserPath.getList("data").size();i++){
            Assert.assertNotNull(listUserPath.getString("data["+i+"].first_name"));
        }
    }
}
