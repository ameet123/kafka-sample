
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
    "currency",
    "resStatus",
    "lastUpdateDateTime",
    "lastUpdateDateTimeHotelLocalTime",
    "imageStatus",
    "createDateTime",
    "isPaidByPoints",
    "reservationIds",
    "isConfirmed",
    "hotel",
    "groupContract",
    "ratePlans",
    "roomTypes",
    "roomStays",
    "guests",
    "resHolderId",
    "total",
    "channelPreferredRate",
    "channelExtraPersonChargeIncluded",
    "taxDefinitions"
})
public class HotelReservation {

    @JsonProperty("currency")
    private String currency;
    @JsonProperty("resStatus")
    private String resStatus;
    @JsonProperty("lastUpdateDateTime")
    private String lastUpdateDateTime;
    @JsonProperty("lastUpdateDateTimeHotelLocalTime")
    private String lastUpdateDateTimeHotelLocalTime;
    @JsonProperty("imageStatus")
    private String imageStatus;
    @JsonProperty("createDateTime")
    private String createDateTime;
    @JsonProperty("isPaidByPoints")
    private Boolean isPaidByPoints;
    @JsonProperty("reservationIds")
    private ReservationIds reservationIds;
    @JsonProperty("isConfirmed")
    private Boolean isConfirmed;
    @JsonProperty("hotel")
    private Hotel hotel;
    @JsonProperty("groupContract")
    private GroupContract groupContract;
    @JsonProperty("ratePlans")
    private List<RatePlan> ratePlans = new ArrayList<RatePlan>();
    @JsonProperty("roomTypes")
    private List<RoomType> roomTypes = new ArrayList<RoomType>();
    @JsonProperty("roomStays")
    private List<RoomStay> roomStays = new ArrayList<RoomStay>();
    @JsonProperty("guests")
    private List<Guest> guests = new ArrayList<Guest>();
    @JsonProperty("resHolderId")
    private Integer resHolderId;
    @JsonProperty("total")
    private Total__ total;
    @JsonProperty("channelPreferredRate")
    private String channelPreferredRate;
    @JsonProperty("channelExtraPersonChargeIncluded")
    private String channelExtraPersonChargeIncluded;
    @JsonProperty("taxDefinitions")
    private List<TaxDefinition> taxDefinitions = new ArrayList<TaxDefinition>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("resStatus")
    public String getResStatus() {
        return resStatus;
    }

    @JsonProperty("resStatus")
    public void setResStatus(String resStatus) {
        this.resStatus = resStatus;
    }

    @JsonProperty("lastUpdateDateTime")
    public String getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    @JsonProperty("lastUpdateDateTime")
    public void setLastUpdateDateTime(String lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    @JsonProperty("lastUpdateDateTimeHotelLocalTime")
    public String getLastUpdateDateTimeHotelLocalTime() {
        return lastUpdateDateTimeHotelLocalTime;
    }

    @JsonProperty("lastUpdateDateTimeHotelLocalTime")
    public void setLastUpdateDateTimeHotelLocalTime(String lastUpdateDateTimeHotelLocalTime) {
        this.lastUpdateDateTimeHotelLocalTime = lastUpdateDateTimeHotelLocalTime;
    }

    @JsonProperty("imageStatus")
    public String getImageStatus() {
        return imageStatus;
    }

    @JsonProperty("imageStatus")
    public void setImageStatus(String imageStatus) {
        this.imageStatus = imageStatus;
    }

    @JsonProperty("createDateTime")
    public String getCreateDateTime() {
        return createDateTime;
    }

    @JsonProperty("createDateTime")
    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    @JsonProperty("isPaidByPoints")
    public Boolean getIsPaidByPoints() {
        return isPaidByPoints;
    }

    @JsonProperty("isPaidByPoints")
    public void setIsPaidByPoints(Boolean isPaidByPoints) {
        this.isPaidByPoints = isPaidByPoints;
    }

    @JsonProperty("reservationIds")
    public ReservationIds getReservationIds() {
        return reservationIds;
    }

    @JsonProperty("reservationIds")
    public void setReservationIds(ReservationIds reservationIds) {
        this.reservationIds = reservationIds;
    }

    @JsonProperty("isConfirmed")
    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    @JsonProperty("isConfirmed")
    public void setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    @JsonProperty("hotel")
    public Hotel getHotel() {
        return hotel;
    }

    @JsonProperty("hotel")
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @JsonProperty("groupContract")
    public GroupContract getGroupContract() {
        return groupContract;
    }

    @JsonProperty("groupContract")
    public void setGroupContract(GroupContract groupContract) {
        this.groupContract = groupContract;
    }

    @JsonProperty("ratePlans")
    public List<RatePlan> getRatePlans() {
        return ratePlans;
    }

    @JsonProperty("ratePlans")
    public void setRatePlans(List<RatePlan> ratePlans) {
        this.ratePlans = ratePlans;
    }

    @JsonProperty("roomTypes")
    public List<RoomType> getRoomTypes() {
        return roomTypes;
    }

    @JsonProperty("roomTypes")
    public void setRoomTypes(List<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }

    @JsonProperty("roomStays")
    public List<RoomStay> getRoomStays() {
        return roomStays;
    }

    @JsonProperty("roomStays")
    public void setRoomStays(List<RoomStay> roomStays) {
        this.roomStays = roomStays;
    }

    @JsonProperty("guests")
    public List<Guest> getGuests() {
        return guests;
    }

    @JsonProperty("guests")
    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    @JsonProperty("resHolderId")
    public Integer getResHolderId() {
        return resHolderId;
    }

    @JsonProperty("resHolderId")
    public void setResHolderId(Integer resHolderId) {
        this.resHolderId = resHolderId;
    }

    @JsonProperty("total")
    public Total__ getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Total__ total) {
        this.total = total;
    }

    @JsonProperty("channelPreferredRate")
    public String getChannelPreferredRate() {
        return channelPreferredRate;
    }

    @JsonProperty("channelPreferredRate")
    public void setChannelPreferredRate(String channelPreferredRate) {
        this.channelPreferredRate = channelPreferredRate;
    }

    @JsonProperty("channelExtraPersonChargeIncluded")
    public String getChannelExtraPersonChargeIncluded() {
        return channelExtraPersonChargeIncluded;
    }

    @JsonProperty("channelExtraPersonChargeIncluded")
    public void setChannelExtraPersonChargeIncluded(String channelExtraPersonChargeIncluded) {
        this.channelExtraPersonChargeIncluded = channelExtraPersonChargeIncluded;
    }

    @JsonProperty("taxDefinitions")
    public List<TaxDefinition> getTaxDefinitions() {
        return taxDefinitions;
    }

    @JsonProperty("taxDefinitions")
    public void setTaxDefinitions(List<TaxDefinition> taxDefinitions) {
        this.taxDefinitions = taxDefinitions;
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
        return new HashCodeBuilder().append(currency).append(resStatus).append(lastUpdateDateTime).append(lastUpdateDateTimeHotelLocalTime).append(imageStatus).append(createDateTime).append(isPaidByPoints).append(reservationIds).append(isConfirmed).append(hotel).append(groupContract).append(ratePlans).append(roomTypes).append(roomStays).append(guests).append(resHolderId).append(total).append(channelPreferredRate).append(channelExtraPersonChargeIncluded).append(taxDefinitions).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof HotelReservation) == false) {
            return false;
        }
        HotelReservation rhs = ((HotelReservation) other);
        return new EqualsBuilder().append(currency, rhs.currency).append(resStatus, rhs.resStatus).append(lastUpdateDateTime, rhs.lastUpdateDateTime).append(lastUpdateDateTimeHotelLocalTime, rhs.lastUpdateDateTimeHotelLocalTime).append(imageStatus, rhs.imageStatus).append(createDateTime, rhs.createDateTime).append(isPaidByPoints, rhs.isPaidByPoints).append(reservationIds, rhs.reservationIds).append(isConfirmed, rhs.isConfirmed).append(hotel, rhs.hotel).append(groupContract, rhs.groupContract).append(ratePlans, rhs.ratePlans).append(roomTypes, rhs.roomTypes).append(roomStays, rhs.roomStays).append(guests, rhs.guests).append(resHolderId, rhs.resHolderId).append(total, rhs.total).append(channelPreferredRate, rhs.channelPreferredRate).append(channelExtraPersonChargeIncluded, rhs.channelExtraPersonChargeIncluded).append(taxDefinitions, rhs.taxDefinitions).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
