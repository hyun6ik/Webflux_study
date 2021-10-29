package com.example.webflux_study.pubsubpattern;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Temperature {

    private double value;

    public Temperature(double value) {
        this.value = value;
    }

    public static Temperature of(double value) {
        return new Temperature(value);
    }
}
