
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
    "cfNumber",
    "imageId",
    "pmsCfNumber",
    "committedSequenceNumber"
})
public class ReservationIds {

    @JsonProperty("cfNumber")
    private String cfNumber;
    @JsonProperty("imageId")
    private Integer imageId;
    @JsonProperty("pmsCfNumber")
    private String pmsCfNumber;
    @JsonProperty("committedSequenceNumber")
    private Integer committedSequenceNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("cfNumber")
    public String getCfNumber() {
        return cfNumber;
    }

    @JsonProperty("cfNumber")
    public void setCfNumber(String cfNumber) {
        this.cfNumber = cfNumber;
    }

    @JsonProperty("imageId")
    public Integer getImageId() {
        return imageId;
    }

    @JsonProperty("imageId")
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    @JsonProperty("pmsCfNumber")
    public String getPmsCfNumber() {
        return pmsCfNumber;
    }

    @JsonProperty("pmsCfNumber")
    public void setPmsCfNumber(String pmsCfNumber) {
        this.pmsCfNumber = pmsCfNumber;
    }

    @JsonProperty("committedSequenceNumber")
    public Integer getCommittedSequenceNumber() {
        return committedSequenceNumber;
    }

    @JsonProperty("committedSequenceNumber")
    public void setCommittedSequenceNumber(Integer committedSequenceNumber) {
        this.committedSequenceNumber = committedSequenceNumber;
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
        return new HashCodeBuilder().append(cfNumber).append(imageId).append(pmsCfNumber).append(committedSequenceNumber).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ReservationIds) == false) {
            return false;
        }
        ReservationIds rhs = ((ReservationIds) other);
        return new EqualsBuilder().append(cfNumber, rhs.cfNumber).append(imageId, rhs.imageId).append(pmsCfNumber, rhs.pmsCfNumber).append(committedSequenceNumber, rhs.committedSequenceNumber).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
