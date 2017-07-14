package org.ameet.kafkasample.model.ihg;

import java.util.Arrays;

/**
 * Created by ameet.chaubal on 7/14/2017.
 */
public class RoomType {
    private String isAccessible;
    private String propertyCode;
    private String sellStrategyOrder;
    private String brandCode;
    private String isHouseFlowThrough;
    private String isSpecialtyClass;
    private String isDisplayable;
    private String totalMaximumOccupancy;
    private String chainCode;
    private String name;
    private String isPremium;
    private String code;
    private String isFlowThrough;
    private String productClassCode;
    private Description[] descriptions;

    public String getIsAccessible() {
        return isAccessible;
    }

    public void setIsAccessible(String isAccessible) {
        this.isAccessible = isAccessible;
    }

    public String getPropertyCode() {
        return propertyCode;
    }

    public void setPropertyCode(String propertyCode) {
        this.propertyCode = propertyCode;
    }

    public String getSellStrategyOrder() {
        return sellStrategyOrder;
    }

    public void setSellStrategyOrder(String sellStrategyOrder) {
        this.sellStrategyOrder = sellStrategyOrder;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getIsHouseFlowThrough() {
        return isHouseFlowThrough;
    }

    public void setIsHouseFlowThrough(String isHouseFlowThrough) {
        this.isHouseFlowThrough = isHouseFlowThrough;
    }

    public String getIsSpecialtyClass() {
        return isSpecialtyClass;
    }

    public void setIsSpecialtyClass(String isSpecialtyClass) {
        this.isSpecialtyClass = isSpecialtyClass;
    }

    public String getIsDisplayable() {
        return isDisplayable;
    }

    public void setIsDisplayable(String isDisplayable) {
        this.isDisplayable = isDisplayable;
    }

    public String getTotalMaximumOccupancy() {
        return totalMaximumOccupancy;
    }

    public void setTotalMaximumOccupancy(String totalMaximumOccupancy) {
        this.totalMaximumOccupancy = totalMaximumOccupancy;
    }

    public String getChainCode() {
        return chainCode;
    }

    public void setChainCode(String chainCode) {
        this.chainCode = chainCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsPremium() {
        return isPremium;
    }

    public void setIsPremium(String isPremium) {
        this.isPremium = isPremium;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIsFlowThrough() {
        return isFlowThrough;
    }

    public void setIsFlowThrough(String isFlowThrough) {
        this.isFlowThrough = isFlowThrough;
    }

    public String getProductClassCode() {
        return productClassCode;
    }

    public void setProductClassCode(String productClassCode) {
        this.productClassCode = productClassCode;
    }

    public Description[] getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Description[] descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "isAccessible='" + isAccessible + '\'' +
                ", propertyCode='" + propertyCode + '\'' +
                ", sellStrategyOrder='" + sellStrategyOrder + '\'' +
                ", brandCode='" + brandCode + '\'' +
                ", isHouseFlowThrough='" + isHouseFlowThrough + '\'' +
                ", isSpecialtyClass='" + isSpecialtyClass + '\'' +
                ", isDisplayable='" + isDisplayable + '\'' +
                ", totalMaximumOccupancy='" + totalMaximumOccupancy + '\'' +
                ", chainCode='" + chainCode + '\'' +
                ", name='" + name + '\'' +
                ", isPremium='" + isPremium + '\'' +
                ", code='" + code + '\'' +
                ", isFlowThrough='" + isFlowThrough + '\'' +
                ", productClassCode='" + productClassCode + '\'' +
                ", descriptions=" + Arrays.toString(descriptions) +
                '}';
    }
}
