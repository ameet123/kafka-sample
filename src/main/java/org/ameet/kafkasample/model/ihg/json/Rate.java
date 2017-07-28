
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
    "startDate",
    "endDate",
    "rateTimeUnit",
    "roomPricingType",
    "unitMultiplier",
    "base",
    "dailyAmounts"
})
public class Rate {

    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("rateTimeUnit")
    private String rateTimeUnit;
    @JsonProperty("roomPricingType")
    private String roomPricingType;
    @JsonProperty("unitMultiplier")
    private Integer unitMultiplier;
    @JsonProperty("base")
    private Base base;
    @JsonProperty("dailyAmounts")
    private DailyAmounts dailyAmounts;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("endDate")
    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("rateTimeUnit")
    public String getRateTimeUnit() {
        return rateTimeUnit;
    }

    @JsonProperty("rateTimeUnit")
    public void setRateTimeUnit(String rateTimeUnit) {
        this.rateTimeUnit = rateTimeUnit;
    }

    @JsonProperty("roomPricingType")
    public String getRoomPricingType() {
        return roomPricingType;
    }

    @JsonProperty("roomPricingType")
    public void setRoomPricingType(String roomPricingType) {
        this.roomPricingType = roomPricingType;
    }

    @JsonProperty("unitMultiplier")
    public Integer getUnitMultiplier() {
        return unitMultiplier;
    }

    @JsonProperty("unitMultiplier")
    public void setUnitMultiplier(Integer unitMultiplier) {
        this.unitMultiplier = unitMultiplier;
    }

    @JsonProperty("base")
    public Base getBase() {
        return base;
    }

    @JsonProperty("base")
    public void setBase(Base base) {
        this.base = base;
    }

    @JsonProperty("dailyAmounts")
    public DailyAmounts getDailyAmounts() {
        return dailyAmounts;
    }

    @JsonProperty("dailyAmounts")
    public void setDailyAmounts(DailyAmounts dailyAmounts) {
        this.dailyAmounts = dailyAmounts;
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
        return new HashCodeBuilder().append(startDate).append(endDate).append(rateTimeUnit).append(roomPricingType).append(unitMultiplier).append(base).append(dailyAmounts).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Rate) == false) {
            return false;
        }
        Rate rhs = ((Rate) other);
        return new EqualsBuilder().append(startDate, rhs.startDate).append(endDate, rhs.endDate).append(rateTimeUnit, rhs.rateTimeUnit).append(roomPricingType, rhs.roomPricingType).append(unitMultiplier, rhs.unitMultiplier).append(base, rhs.base).append(dailyAmounts, rhs.dailyAmounts).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
