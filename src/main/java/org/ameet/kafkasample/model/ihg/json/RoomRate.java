
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
    "roomTypeCode",
    "ratePlanCode",
    "startDate",
    "endDate",
    "rates",
    "guestCounts",
    "numberOfRooms",
    "distribInfo"
})
public class RoomRate {

    @JsonProperty("roomTypeCode")
    private String roomTypeCode;
    @JsonProperty("ratePlanCode")
    private String ratePlanCode;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("rates")
    private List<Rate> rates = new ArrayList<Rate>();
    @JsonProperty("guestCounts")
    private List<GuestCount> guestCounts = new ArrayList<GuestCount>();
    @JsonProperty("numberOfRooms")
    private Integer numberOfRooms;
    @JsonProperty("distribInfo")
    private DistribInfo_ distribInfo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    @JsonProperty("rates")
    public List<Rate> getRates() {
        return rates;
    }

    @JsonProperty("rates")
    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    @JsonProperty("guestCounts")
    public List<GuestCount> getGuestCounts() {
        return guestCounts;
    }

    @JsonProperty("guestCounts")
    public void setGuestCounts(List<GuestCount> guestCounts) {
        this.guestCounts = guestCounts;
    }

    @JsonProperty("numberOfRooms")
    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    @JsonProperty("numberOfRooms")
    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    @JsonProperty("distribInfo")
    public DistribInfo_ getDistribInfo() {
        return distribInfo;
    }

    @JsonProperty("distribInfo")
    public void setDistribInfo(DistribInfo_ distribInfo) {
        this.distribInfo = distribInfo;
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
        return new HashCodeBuilder().append(roomTypeCode).append(ratePlanCode).append(startDate).append(endDate).append(rates).append(guestCounts).append(numberOfRooms).append(distribInfo).append(additionalProperties).toHashCode();
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
        return new EqualsBuilder().append(roomTypeCode, rhs.roomTypeCode).append(ratePlanCode, rhs.ratePlanCode).append(startDate, rhs.startDate).append(endDate, rhs.endDate).append(rates, rhs.rates).append(guestCounts, rhs.guestCounts).append(numberOfRooms, rhs.numberOfRooms).append(distribInfo, rhs.distribInfo).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
