package ResponseModels.SingleUserResponses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data",
        "support"
})

public class SignleUserResponse {

    @JsonProperty("data")
    public Data data;
    @JsonProperty("support")
    public Support support;

}