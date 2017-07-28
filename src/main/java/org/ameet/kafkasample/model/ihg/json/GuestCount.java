
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
    "ageQualifyingCode",
    "count",
    "age"
})
public class GuestCount {

    @JsonProperty("ageQualifyingCode")
    private Integer ageQualifyingCode;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("age")
    private Integer age;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ageQualifyingCode")
    public Integer getAgeQualifyingCode() {
        return ageQualifyingCode;
    }

    @JsonProperty("ageQualifyingCode")
    public void setAgeQualifyingCode(Integer ageQualifyingCode) {
        this.ageQualifyingCode = ageQualifyingCode;
    }

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("age")
    public Integer getAge() {
        return age;
    }

    @JsonProperty("age")
    public void setAge(Integer age) {
        this.age = age;
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
        return new HashCodeBuilder().append(ageQualifyingCode).append(count).append(age).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GuestCount) == false) {
            return false;
        }
        GuestCount rhs = ((GuestCount) other);
        return new EqualsBuilder().append(ageQualifyingCode, rhs.ageQualifyingCode).append(count, rhs.count).append(age, rhs.age).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
