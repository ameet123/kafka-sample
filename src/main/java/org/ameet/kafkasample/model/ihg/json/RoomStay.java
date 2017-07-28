
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
    "synchroNumber",
    "id",
    "startDate",
    "endDate",
    "stayHolderId",
    "actionStatus",
    "paymentInfo",
    "roomRates",
    "total",
    "totalBaseOccupancy",
    "totalExtraOccupancy",
    "factLines",
    "consolidatedRoomStayStatus"
})
public class RoomStay {

    @JsonProperty("synchroNumber")
    private Integer synchroNumber;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("stayHolderId")
    private Integer stayHolderId;
    @JsonProperty("actionStatus")
    private String actionStatus;
    @JsonProperty("paymentInfo")
    private PaymentInfo paymentInfo;
    @JsonProperty("roomRates")
    private List<RoomRate> roomRates = new ArrayList<RoomRate>();
    @JsonProperty("total")
    private Total_ total;
    @JsonProperty("totalBaseOccupancy")
    private TotalBaseOccupancy totalBaseOccupancy;
    @JsonProperty("totalExtraOccupancy")
    private TotalExtraOccupancy totalExtraOccupancy;
    @JsonProperty("factLines")
    private List<FactLine> factLines = new ArrayList<FactLine>();
    @JsonProperty("consolidatedRoomStayStatus")
    private String consolidatedRoomStayStatus;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("synchroNumber")
    public Integer getSynchroNumber() {
        return synchroNumber;
    }

    @JsonProperty("synchroNumber")
    public void setSynchroNumber(Integer synchroNumber) {
        this.synchroNumber = synchroNumber;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
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

    @JsonProperty("stayHolderId")
    public Integer getStayHolderId() {
        return stayHolderId;
    }

    @JsonProperty("stayHolderId")
    public void setStayHolderId(Integer stayHolderId) {
        this.stayHolderId = stayHolderId;
    }

    @JsonProperty("actionStatus")
    public String getActionStatus() {
        return actionStatus;
    }

    @JsonProperty("actionStatus")
    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }

    @JsonProperty("paymentInfo")
    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    @JsonProperty("paymentInfo")
    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    @JsonProperty("roomRates")
    public List<RoomRate> getRoomRates() {
        return roomRates;
    }

    @JsonProperty("roomRates")
    public void setRoomRates(List<RoomRate> roomRates) {
        this.roomRates = roomRates;
    }

    @JsonProperty("total")
    public Total_ getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Total_ total) {
        this.total = total;
    }

    @JsonProperty("totalBaseOccupancy")
    public TotalBaseOccupancy getTotalBaseOccupancy() {
        return totalBaseOccupancy;
    }

    @JsonProperty("totalBaseOccupancy")
    public void setTotalBaseOccupancy(TotalBaseOccupancy totalBaseOccupancy) {
        this.totalBaseOccupancy = totalBaseOccupancy;
    }

    @JsonProperty("totalExtraOccupancy")
    public TotalExtraOccupancy getTotalExtraOccupancy() {
        return totalExtraOccupancy;
    }

    @JsonProperty("totalExtraOccupancy")
    public void setTotalExtraOccupancy(TotalExtraOccupancy totalExtraOccupancy) {
        this.totalExtraOccupancy = totalExtraOccupancy;
    }

    @JsonProperty("factLines")
    public List<FactLine> getFactLines() {
        return factLines;
    }

    @JsonProperty("factLines")
    public void setFactLines(List<FactLine> factLines) {
        this.factLines = factLines;
    }

    @JsonProperty("consolidatedRoomStayStatus")
    public String getConsolidatedRoomStayStatus() {
        return consolidatedRoomStayStatus;
    }

    @JsonProperty("consolidatedRoomStayStatus")
    public void setConsolidatedRoomStayStatus(String consolidatedRoomStayStatus) {
        this.consolidatedRoomStayStatus = consolidatedRoomStayStatus;
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
        return new HashCodeBuilder().append(synchroNumber).append(id).append(startDate).append(endDate).append(stayHolderId).append(actionStatus).append(paymentInfo).append(roomRates).append(total).append(totalBaseOccupancy).append(totalExtraOccupancy).append(factLines).append(consolidatedRoomStayStatus).append(additionalProperties).toHashCode();
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
        return new EqualsBuilder().append(synchroNumber, rhs.synchroNumber).append(id, rhs.id).append(startDate, rhs.startDate).append(endDate, rhs.endDate).append(stayHolderId, rhs.stayHolderId).append(actionStatus, rhs.actionStatus).append(paymentInfo, rhs.paymentInfo).append(roomRates, rhs.roomRates).append(total, rhs.total).append(totalBaseOccupancy, rhs.totalBaseOccupancy).append(totalExtraOccupancy, rhs.totalExtraOccupancy).append(factLines, rhs.factLines).append(consolidatedRoomStayStatus, rhs.consolidatedRoomStayStatus).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
