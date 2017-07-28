
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
    "startDate",
    "endDate",
    "groupCode",
    "roomTypeCode",
    "ratePlanCode",
    "numberOfRooms",
    "rates",
    "ratesDefinitions",
    "total",
    "guestCounts",
    "specialRateInd"
})
public class RoomRate {

    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("groupCode")
    private String groupCode;
    @JsonProperty("roomTypeCode")
    private String roomTypeCode;
    @JsonProperty("ratePlanCode")
    private String ratePlanCode;
    @JsonProperty("numberOfRooms")
    private Integer numberOfRooms;
    @JsonProperty("rates")
    private List<Rate> rates = new ArrayList<Rate>();
    @JsonProperty("ratesDefinitions")
    private List<RatesDefinition> ratesDefinitions = new ArrayList<RatesDefinition>();
    @JsonProperty("total")
    private Total total;
    @JsonProperty("guestCounts")
    private List<GuestCount> guestCounts = new ArrayList<GuestCount>();
    @JsonProperty("specialRateInd")
    private String specialRateInd;
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

    @JsonProperty("groupCode")
    public String getGroupCode() {
        return groupCode;
    }

    @JsonProperty("groupCode")
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @JsonProperty("roomTypeCode")
    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    @JsonProperty("roomTypeCode")
    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }

    @JsonProperty("ratePlanCode")
    public String getRatePlanCode() {
        return ratePlanCode;
    }

    @JsonProperty("ratePlanCode")
    public void setRatePlanCode(String ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
    }

    @JsonProperty("numberOfRooms")
    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    @JsonProperty("numberOfRooms")
    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    @JsonProperty("rates")
    public List<Rate> getRates() {
        return rates;
    }

    @JsonProperty("rates")
    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    @JsonProperty("ratesDefinitions")
    public List<RatesDefinition> getRatesDefinitions() {
        return ratesDefinitions;
    }

    @JsonProperty("ratesDefinitions")
    public void setRatesDefinitions(List<RatesDefinition> ratesDefinitions) {
        this.ratesDefinitions = ratesDefinitions;
    }

    @JsonProperty("total")
    public Total getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Total total) {
        this.total = total;
    }

    @JsonProperty("guestCounts")
    public List<GuestCount> getGuestCounts() {
        return guestCounts;
    }

    @JsonProperty("guestCounts")
    public void setGuestCounts(List<GuestCount> guestCounts) {
        this.guestCounts = guestCounts;
    }

    @JsonProperty("specialRateInd")
    public String getSpecialRateInd() {
        return specialRateInd;
    }

    @JsonProperty("specialRateInd")
    public void setSpecialRateInd(String specialRateInd) {
        this.specialRateInd = specialRateInd;
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
        return new HashCodeBuilder().append(startDate).append(endDate).append(groupCode).append(roomTypeCode).append(ratePlanCode).append(numberOfRooms).append(rates).append(ratesDefinitions).append(total).append(guestCounts).append(specialRateInd).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RoomRate) == false) {
            return false;
        }
        RoomRate rhs = ((RoomRate) other);
        return new EqualsBuilder().append(startDate, rhs.startDate).append(endDate, rhs.endDate).append(groupCode, rhs.groupCode).append(roomTypeCode, rhs.roomTypeCode).append(ratePlanCode, rhs.ratePlanCode).append(numberOfRooms, rhs.numberOfRooms).append(rates, rhs.rates).append(ratesDefinitions, rhs.ratesDefinitions).append(total, rhs.total).append(guestCounts, rhs.guestCounts).append(specialRateInd, rhs.specialRateInd).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
