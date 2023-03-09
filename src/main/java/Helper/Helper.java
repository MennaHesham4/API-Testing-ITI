package Helper;

import RequestModels.LoginRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.dynalink.beans.StaticClass;

public class Helper {

    public static String  getObjectAsString (Object object) {
        ObjectMapper objectMapper = new ObjectMapper(); //convert java obj to string
        String Payload = "";
       try {
           payload = objectMapper.writeValueAsString(object);
       }catch (JsonProcessingException e) {
           e.printStackTrace();
       }
        return Payload;

 }
}