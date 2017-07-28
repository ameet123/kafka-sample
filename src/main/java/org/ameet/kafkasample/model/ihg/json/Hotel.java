
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
    "chainCode",
    "brandCode",
    "code",
    "propertyName"
})
public class Hotel {

    @JsonProperty("chainCode")
    private String chainCode;
    @JsonProperty("brandCode")
    private String brandCode;
    @JsonProperty("code")
    private String code;
    @JsonProperty("propertyName")
    private String propertyName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("chainCode")
    public String getChainCode() {
        return chainCode;
    }

    @JsonProperty("chainCode")
    public void setChainCode(String chainCode) {
        this.chainCode = chainCode;
    }

    @JsonProperty("brandCode")
    public String getBrandCode() {
        return brandCode;
    }

    @JsonProperty("brandCode")
    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("propertyName")
    public String getPropertyName() {
        return propertyName;
    }

    @JsonProperty("propertyName")
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
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
        return new HashCodeBuilder().append(chainCode).append(brandCode).append(code).append(propertyName).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Hotel) == false) {
            return false;
        }
        Hotel rhs = ((Hotel) other);
        return new EqualsBuilder().append(chainCode, rhs.chainCode).append(brandCode, rhs.brandCode).append(code, rhs.code).append(propertyName, rhs.propertyName).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
