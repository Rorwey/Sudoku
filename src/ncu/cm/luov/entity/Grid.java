package ncu.cm.luov.entity;

import java.util.ArrayList;

public class Grid {
    private Integer value;
    private boolean isEmpty;
    private ArrayList<Integer> info;//还可以填入的数值
    private int row;//所在行
    private int col;//所在列
    private int block;//所在块

    public Grid(Integer value, boolean isEmpty, ArrayList<Integer> info, int row, int col, int block) {
        this.value = value;
        this.isEmpty = isEmpty;
        this.info = info;
        this.row = row;
        this.col = col;
        this.block = block;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public Grid(Integer value, boolean isEmpty) {
        this.value = value;
        this.isEmpty = isEmpty;
    }

    public Grid(Integer value, boolean isEmpty, ArrayList<Integer> info) {

        this.value = value;
        this.isEmpty = isEmpty;
        this.info = info;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public ArrayList<Integer> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<Integer> info) {
        this.info = info;
    }

    @Override
    public String toString() {
        if(isEmpty) return "0";
        else return String.valueOf(value);
    }
}
