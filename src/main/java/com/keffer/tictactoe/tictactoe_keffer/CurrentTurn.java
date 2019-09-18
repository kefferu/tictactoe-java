package com.keffer.tictactoe.tictactoe_keffer;

public enum CurrentTurn {

    NOUGHT(1),
    CIRCLE(2);

    private int value;

    CurrentTurn(int valueDigit) {
        this.value = valueDigit;
    }

    public int getValue() {
        return value;
    }
}
