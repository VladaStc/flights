package com.ates.flights.utils;

public class WeightConverter {

    private WeightConverter() {
    }

    public static Float convertKgToLb(Float weight){
        return weight * 0.45f;
    }

    public static Float convertLbToKg(Float weight){
        return weight / 0.45f;
    }
}
