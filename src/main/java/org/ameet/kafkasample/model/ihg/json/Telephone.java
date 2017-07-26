
package org.ameet.kafkasample.model.ihg.json;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "phoneLocationType",
    "phoneNumber"
})
public class Telephone {

    @JsonProperty("phoneLocationType")
    private Integer phoneLocationType;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("phoneLocationType")
    public Integer getPhoneLocationType() {
        return phoneLocationType;
    }

    @JsonProperty("phoneLocationType")
    public void setPhoneLocationType(Integer phoneLocationType) {
        this.phoneLocationType = phoneLocationType;
    }

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(phoneLocationType).append(phoneNumber).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Telephone) == false) {
            return false;
        }
        Telephone rhs = ((Telephone) other);
        return new EqualsBuilder().append(phoneLocationType, rhs.phoneLocationType).append(phoneNumber, rhs.phoneNumber).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
