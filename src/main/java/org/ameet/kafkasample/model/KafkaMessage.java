package org.ameet.kafkasample.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "metadata",
        "rawData"
})
public class KafkaMessage {
    @JsonProperty("metadata")
    private MessageMetadata messageMetadata;
    @JsonProperty("rawData")
    private String rawData;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("metadata")
    public MessageMetadata getMessageMetadata() {
        return messageMetadata;
    }

    @JsonProperty("metadata")
    public void setMessageMetadata(MessageMetadata messageMetadata) {
        this.messageMetadata = messageMetadata;
    }

    @JsonProperty("rawData")
    public String getRawData() {
        return rawData;
    }

    @JsonProperty("rawData")
    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}