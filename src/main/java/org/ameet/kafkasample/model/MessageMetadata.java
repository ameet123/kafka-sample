package org.ameet.kafkasample.model;

/**
 * Created by ameet.chaubal on 7/20/2017.
 */

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "operationName",
        "hotelCodes",
        "serviceName",
        "transactionId",
        "channelId",
        "requestorId",
        "createdTimestamp",
        "targetSystem",
        "type",
        "routing",
        "messageId"
})
@Entity
//@Table(name = "message_metadata")
public class MessageMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;
    @JsonProperty("operationName")
    private String operationName;
    @JsonProperty("hotelCodes")
    @Transient
    private List<String> hotelCodes = null;
    @JsonProperty("serviceName")
    private String serviceName;
    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("channelId")
    private String channelId;
    @JsonProperty("requestorId")
    private String requestorId;
    @JsonProperty("createdTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTimestamp;
    @JsonProperty("targetSystem")
    private String targetSystem;
    @JsonProperty("type")
    private String type;
    @JsonProperty("routing")
    private String routing;
    @JsonProperty("messageId")
    private String messageId;
    @JsonIgnore
    @Transient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("operationName")
    public String getOperationName() {
        return operationName;
    }

    @JsonProperty("operationName")
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    @JsonProperty("hotelCodes")
    public List<String> getHotelCodes() {
        return hotelCodes;
    }

    @JsonProperty("hotelCodes")
    public void setHotelCodes(List<String> hotelCodes) {
        this.hotelCodes = hotelCodes;
    }

    @JsonProperty("serviceName")
    public String getServiceName() {
        return serviceName;
    }

    @JsonProperty("serviceName")
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @JsonProperty("transactionId")
    public String getTransactionId() {
        return transactionId;
    }

    @JsonProperty("transactionId")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @JsonProperty("channelId")
    public String getChannelId() {
        return channelId;
    }

    @JsonProperty("channelId")
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @JsonProperty("requestorId")
    public String getRequestorId() {
        return requestorId;
    }

    @JsonProperty("requestorId")
    public void setRequestorId(String requestorId) {
        this.requestorId = requestorId;
    }

    @JsonProperty("createdTimestamp")
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    @JsonProperty("createdTimestamp")
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    @JsonProperty("targetSystem")
    public String getTargetSystem() {
        return targetSystem;
    }

    @JsonProperty("targetSystem")
    public void setTargetSystem(String targetSystem) {
        this.targetSystem = targetSystem;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("routing")
    public String getRouting() {
        return routing;
    }

    @JsonProperty("routing")
    public void setRouting(String routing) {
        this.routing = routing;
    }

    @JsonProperty("messageId")
    public String getMessageId() {
        return messageId;
    }

    @JsonProperty("messageId")
    public void setMessageId(String messageId) {
        this.messageId = messageId;
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
    public String toString() {
        return "MessageMetadata{" +
                "id=" + id +
                ", operationName='" + operationName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", channelId='" + channelId + '\'' +
                ", requestorId='" + requestorId + '\'' +
                ", createdTimestamp=" + createdTimestamp +
                ", targetSystem='" + targetSystem + '\'' +
                ", type='" + type + '\'' +
                ", routing='" + routing + '\'' +
                ", messageId='" + messageId + '\'' +
                '}';
    }
}