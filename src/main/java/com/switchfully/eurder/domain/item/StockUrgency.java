package com.switchfully.eurder.domain.item;

public enum StockUrgency {
    STOCK_LOW(1),
    STOCK_MEDIUM(2),
    STOCK_HIGH(3);

    private int importance;

    StockUrgency(int importance) {
        this.importance = importance;
    }

    public int getImportance() {
        return importance;
    }
}
