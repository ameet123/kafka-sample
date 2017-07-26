
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
    "hotelReservation"
})
public class Reservation {

    @JsonProperty("hotelReservation")
    private HotelReservation hotelReservation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("hotelReservation")
    public HotelReservation getHotelReservation() {
        return hotelReservation;
    }

    @JsonProperty("hotelReservation")
    public void setHotelReservation(HotelReservation hotelReservation) {
        this.hotelReservation = hotelReservation;
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
        return new HashCodeBuilder().append(hotelReservation).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Reservation) == false) {
            return false;
        }
        Reservation rhs = ((Reservation) other);
        return new EqualsBuilder().append(hotelReservation, rhs.hotelReservation).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
