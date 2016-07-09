package net.chandol.study.theater.dto;

public class TheaterCreateRequest {
    private final String name;
    private final int columnSize;
    private final int rowSize;

    public TheaterCreateRequest(String name, int columnSize, int rowSize) {
        this.name = name;
        this.columnSize = columnSize;
        this.rowSize = rowSize;
    }

    public String getName() {
        return name;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public int getRowSize() {
        return rowSize;
    }
}
