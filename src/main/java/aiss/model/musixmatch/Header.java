
package aiss.model.musixmatch;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status_code",
    "execute_time"
})
public class Header {

    @JsonProperty("status_code")
    private Integer statusCode;
    @JsonProperty("execute_time")
    private Double executeTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("status_code")
    public Integer getStatusCode() {
        return statusCode;
    }

    @JsonProperty("status_code")
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @JsonProperty("execute_time")
    public Double getExecuteTime() {
        return executeTime;
    }

    @JsonProperty("execute_time")
    public void setExecuteTime(Double executeTime) {
        this.executeTime = executeTime;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
