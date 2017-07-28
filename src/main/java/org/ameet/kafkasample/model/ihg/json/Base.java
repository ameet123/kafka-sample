
package org.ameet.kafkasample.model.ihg.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "amount",
    "overrideInd",
    "originalBaseRates"
})
public class Base {

    @JsonProperty("amount")
    private String amount;
    @JsonProperty("overrideInd")
    private Boolean overrideInd;
    @JsonProperty("originalBaseRates")
    private List<OriginalBaseRate> originalBaseRates = new ArrayList<OriginalBaseRate>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("amount")
    public String getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(String amount) {
        this.amount = amount;
    }

    @JsonProperty("overrideInd")
    public Boolean getOverrideInd() {
        return overrideInd;
    }

    @JsonProperty("overrideInd")
    public void setOverrideInd(Boolean overrideInd) {
        this.overrideInd = overrideInd;
    }

    @JsonProperty("originalBaseRates")
    public List<OriginalBaseRate> getOriginalBaseRates() {
        return originalBaseRates;
    }

    @JsonProperty("originalBaseRates")
    public void setOriginalBaseRates(List<OriginalBaseRate> originalBaseRates) {
        this.originalBaseRates = originalBaseRates;
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
        return new HashCodeBuilder().append(amount).append(overrideInd).append(originalBaseRates).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Base) == false) {
            return false;
        }
        Base rhs = ((Base) other);
        return new EqualsBuilder().append(amount, rhs.amount).append(overrideInd, rhs.overrideInd).append(originalBaseRates, rhs.originalBaseRates).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
