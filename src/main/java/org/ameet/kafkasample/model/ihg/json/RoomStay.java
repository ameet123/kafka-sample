
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
    "paymentInfo",
    "stayHolderId",
    "roomRates",
    "comments",
    "telephones",
    "actionStatus",
    "id"
})
public class RoomStay {

    @JsonProperty("paymentInfo")
    private PaymentInfo paymentInfo;
    @JsonProperty("stayHolderId")
    private Integer stayHolderId;
    @JsonProperty("roomRates")
    private List<RoomRate> roomRates = new ArrayList<RoomRate>();
    @JsonProperty("comments")
    private List<Comment> comments = new ArrayList<Comment>();
    @JsonProperty("telephones")
    private List<Telephone> telephones = new ArrayList<Telephone>();
    @JsonProperty("actionStatus")
    private String actionStatus;
    @JsonProperty("id")
    private Integer id;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("paymentInfo")
    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    @JsonProperty("paymentInfo")
    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    @JsonProperty("stayHolderId")
    public Integer getStayHolderId() {
        return stayHolderId;
    }

    @JsonProperty("stayHolderId")
    public void setStayHolderId(Integer stayHolderId) {
        this.stayHolderId = stayHolderId;
    }

    @JsonProperty("roomRates")
    public List<RoomRate> getRoomRates() {
        return roomRates;
    }

    @JsonProperty("roomRates")
    public void setRoomRates(List<RoomRate> roomRates) {
        this.roomRates = roomRates;
    }

    @JsonProperty("comments")
    public List<Comment> getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @JsonProperty("telephones")
    public List<Telephone> getTelephones() {
        return telephones;
    }

    @JsonProperty("telephones")
    public void setTelephones(List<Telephone> telephones) {
        this.telephones = telephones;
    }

    @JsonProperty("actionStatus")
    public String getActionStatus() {
        return actionStatus;
    }

    @JsonProperty("actionStatus")
    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
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
        return new HashCodeBuilder().append(paymentInfo).append(stayHolderId).append(roomRates).append(comments).append(telephones).append(actionStatus).append(id).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RoomStay) == false) {
            return false;
        }
        RoomStay rhs = ((RoomStay) other);
        return new EqualsBuilder().append(paymentInfo, rhs.paymentInfo).append(stayHolderId, rhs.stayHolderId).append(roomRates, rhs.roomRates).append(comments, rhs.comments).append(telephones, rhs.telephones).append(actionStatus, rhs.actionStatus).append(id, rhs.id).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
