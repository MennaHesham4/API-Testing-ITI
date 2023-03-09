import Requests.Requests;
import ResponseModels.SingleUserResponses.SignleUserResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SingleUser {
    @Test
    public void singleuserSuccess(){
        Response single=Requests.singleUserRequest("2");
        SignleUserResponse ss=single.as(SignleUserResponse.class);
        Assert.assertEquals(single.statusCode(),200,"Fail");
        Assert.assertNotNull(ss.data);
        Assert.assertEquals(ss.data.id,2);
    }
}
