
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
    "cardCode",
    "expireDate",
    "cardHolderName",
    "cardNumber"
})
public class PaymentCard {

    @JsonProperty("cardCode")
    private String cardCode;
    @JsonProperty("expireDate")
    private String expireDate;
    @JsonProperty("cardHolderName")
    private String cardHolderName;
    @JsonProperty("cardNumber")
    private CardNumber cardNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("cardCode")
    public String getCardCode() {
        return cardCode;
    }

    @JsonProperty("cardCode")
    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    @JsonProperty("expireDate")
    public String getExpireDate() {
        return expireDate;
    }

    @JsonProperty("expireDate")
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    @JsonProperty("cardHolderName")
    public String getCardHolderName() {
        return cardHolderName;
    }

    @JsonProperty("cardHolderName")
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    @JsonProperty("cardNumber")
    public CardNumber getCardNumber() {
        return cardNumber;
    }

    @JsonProperty("cardNumber")
    public void setCardNumber(CardNumber cardNumber) {
        this.cardNumber = cardNumber;
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
        return new HashCodeBuilder().append(cardCode).append(expireDate).append(cardHolderName).append(cardNumber).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PaymentCard) == false) {
            return false;
        }
        PaymentCard rhs = ((PaymentCard) other);
        return new EqualsBuilder().append(cardCode, rhs.cardCode).append(expireDate, rhs.expireDate).append(cardHolderName, rhs.cardHolderName).append(cardNumber, rhs.cardNumber).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
