package com.switchfully.eurder.domain.user;

import com.switchfully.eurder.domain.Feature;

import java.util.List;

public enum Role {
    CUSTOMER(List.of(Feature.ORDER_ITEMS)),
    ADMIN(List.of(Feature.ORDER_ITEMS,Feature.ADD_NEW_ITEM,Feature.REGISTER_ADMIN,Feature.CHECK_CUSTOMERS,Feature.CHECK_SINGLE_CUSTOMER));

    private List<Feature> featureList;

    Role(List<Feature> featureList) {
        this.featureList = featureList;
    }
    public boolean containsFeature(Feature feature) {
        return featureList.contains(feature);
    }
}

