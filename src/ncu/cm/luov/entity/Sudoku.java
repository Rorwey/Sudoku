package ncu.cm.luov.entity;

import java.util.ArrayList;
import java.util.Map;

public class Sudoku {
    private ArrayList<Grid> grids;
    private int width;//一个block的长度
    private Map row;
    private Map col;
    private Map block;

    public Sudoku(ArrayList<Grid> grids, int width, Map row, Map col) {
        this.grids = grids;
        this.width = width;
        this.row = row;
        this.col = col;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


    public Sudoku(ArrayList<Grid> grids, int width) {
        this.grids = grids;
        this.width = width;
    }

    public Sudoku() {
    }

    public ArrayList<Grid> getGrids() {
        return grids;
    }

    public void setGrids(ArrayList<Grid> grids) {
        this.grids = grids;
    }

    public Map getRow() {
        return row;
    }


    public void setRow(Map row) {
        this.row = row;
    }

    public void setCol(Map col) {
        this.col = col;
    }

    public void setBlock(Map block) {
        this.block = block;
    }

    public Map getCol() {
        return col;
    }


    public Map getBlock() {
        return block;
    }


    public Sudoku(ArrayList<Grid> grids) {
        this.grids = grids;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        String tab = "\t";
        String enter = "\n";
        for (int i = 0; i < width*width; i++) {
            ArrayList<Grid> rowList = (ArrayList<Grid>) row.get(i);
            for (int j = 0; j < rowList.size(); j++) {
                Integer value = rowList.get(j).getValue();
                if (value != null) {
                    str.append(value.toString());
                } else {
                    str.append("0");
                }
                str.append(tab);
            }
            str.append(enter);
        }
        return "一个" + width * width + "×" + width * width + "的数独" + enter + str.toString();
    }
}
