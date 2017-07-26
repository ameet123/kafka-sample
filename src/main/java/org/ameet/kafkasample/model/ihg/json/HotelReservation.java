
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
    "isForcedSell",
    "resHolderId",
    "reservationIds",
    "hotel",
    "roomStays",
    "guests",
    "isPaidByPoint"
})
public class HotelReservation {

    @JsonProperty("isForcedSell")
    private Boolean isForcedSell;
    @JsonProperty("resHolderId")
    private Integer resHolderId;
    @JsonProperty("reservationIds")
    private ReservationIds reservationIds;
    @JsonProperty("hotel")
    private Hotel hotel;
    @JsonProperty("roomStays")
    private List<RoomStay> roomStays = new ArrayList<RoomStay>();
    @JsonProperty("guests")
    private List<Guest> guests = new ArrayList<Guest>();
    @JsonProperty("isPaidByPoint")
    private Boolean isPaidByPoint;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("isForcedSell")
    public Boolean getIsForcedSell() {
        return isForcedSell;
    }

    @JsonProperty("isForcedSell")
    public void setIsForcedSell(Boolean isForcedSell) {
        this.isForcedSell = isForcedSell;
    }

    @JsonProperty("resHolderId")
    public Integer getResHolderId() {
        return resHolderId;
    }

    @JsonProperty("resHolderId")
    public void setResHolderId(Integer resHolderId) {
        this.resHolderId = resHolderId;
    }

    @JsonProperty("reservationIds")
    public ReservationIds getReservationIds() {
        return reservationIds;
    }

    @JsonProperty("reservationIds")
    public void setReservationIds(ReservationIds reservationIds) {
        this.reservationIds = reservationIds;
    }

    @JsonProperty("hotel")
    public Hotel getHotel() {
        return hotel;
    }

    @JsonProperty("hotel")
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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

    @JsonProperty("isPaidByPoint")
    public Boolean getIsPaidByPoint() {
        return isPaidByPoint;
    }

    @JsonProperty("isPaidByPoint")
    public void setIsPaidByPoint(Boolean isPaidByPoint) {
        this.isPaidByPoint = isPaidByPoint;
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
        return new HashCodeBuilder().append(isForcedSell).append(resHolderId).append(reservationIds).append(hotel).append(roomStays).append(guests).append(isPaidByPoint).append(additionalProperties).toHashCode();
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
        return new EqualsBuilder().append(isForcedSell, rhs.isForcedSell).append(resHolderId, rhs.resHolderId).append(reservationIds, rhs.reservationIds).append(hotel, rhs.hotel).append(roomStays, rhs.roomStays).append(guests, rhs.guests).append(isPaidByPoint, rhs.isPaidByPoint).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
