package Requests;

import RequestModels.CreateUserRequest;
import RequestModels.LoginRequest;
import ResponseModels.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import groovyjarjarpicocli.CommandLine;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import Helper.Helper;
import java.util.HashMap;
import java.util.Map;

public class Requests {
    static String loginEndpoint="/login";
    static String baseURL="https://reqres.in/api";
    static String singleUserEndpoint ="/users";
    public static Response loginRequest(String userName, String password){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.email =userName;
        loginRequest.password =password;
        Response loginResponse = RestAssured.given().log().all().contentType("application/json").body(Helper.getObjectAsString(loginRequest)).post(baseURL + loginEndpoint);
        System.out.println(loginResponse.statusCode());
        loginResponse.prettyPrint(); //print the response
        return loginResponse;
    }
    public static Response listUserResponse(String token, String page){
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", token);
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("page",page);
        Response listUserResponses = RestAssured.given().log().all().headers(headers).queryParams(queryParams).get(baseURL+singleUserEndpoint);
        listUserResponses.prettyPrint();
        return listUserResponses;
    }
    public static Response singleUserRequest(String id){

        Response singleuser=RestAssured.given().log().all().get(baseURL+singleUserEndpoint+"/"+id);
        System.out.println(singleuser.statusCode());
        singleuser.prettyPrint(); //print the response3
        return singleuser;
    }
    public static Response createuserRequest (String Name, String job){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.name =Name;
       createUserRequest.job =job;
        Response createuser = RestAssured.given().log().all().contentType("application/json").body(Helper.getObjectAsString(createUserRequest)).post(baseURL + singleUserEndpoint);
        return createuser;
    }
}
