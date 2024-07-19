package com.bank.enums.accounts;

import lombok.Getter;

@Getter
public enum Currencies {
    Rial("R"),
    Dollar("$");

    private final String symbol;

    Currencies(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}

