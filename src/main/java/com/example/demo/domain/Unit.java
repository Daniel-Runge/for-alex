package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Unit {
    GRAM("g"),
    LITRE("L"),
    STK("stk");

    private final String value;
}
