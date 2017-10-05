package ncu.cm.luov.model;


import ncu.cm.luov.entity.Grid;
import ncu.cm.luov.entity.Sudoku;
import ncu.cm.luov.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class AnalyseSudoku {

    public Integer[] AnalyseSudoku(Sudoku sudoku) {
        return AnalyseAllGridNum(sudoku);
    }

    /**
     * 分析所有的格子，将每个可以填写的数字填入格子的info中
     *
     * @param sudoku 数独
     * @return 每个格子中可填写数字的格数
     */
    private Integer[] AnalyseAllGridNum(Sudoku sudoku) {
        Integer[] num = new Integer[sudoku.getWidth() * sudoku.getWidth()];
        for (int i = 0; i < sudoku.getWidth() * sudoku.getWidth(); i++) {
            Grid grid = sudoku.getGrids().get(i);
            if (grid.isEmpty()) {
                //分别分析行、列或块，提取出可以填写的元素
                List<Integer> gridInfo = AnalyseGridInfo(grid, sudoku);
                grid.setInfo((ArrayList<Integer>) gridInfo);//将这个格子的待填入数写入格子。
                num[i] = gridInfo.size();
            }
        }
        return num;
    }
    public static List<Integer> AnalyseGridInfo(Grid grid, Sudoku sudoku){
        List<Integer> refer = new ArrayList<>();
        for (Integer j = 0; j < sudoku.getWidth() * sudoku.getWidth(); j++) {
            refer.add(j);
        }
        List<Grid> rowList = (List<Grid>) sudoku.getRow().get(grid.getRow());
        List<Integer> rowFillArr = AnalyseSudokuElement(rowList, refer);
        List<Grid> colList = (List<Grid>) sudoku.getCol().get(grid.getCol());
        List<Integer> colFillArr = AnalyseSudokuElement(colList, refer);
        List<Grid> blockList = (List<Grid>) sudoku.getBlock().get(grid.getBlock());
        List<Integer> blockFillArr = AnalyseSudokuElement(blockList, refer);
        List<Integer> bing = ArrayUtils.ArrayIntersect(rowFillArr, colFillArr);
        List<Integer> gridInfo = ArrayUtils.ArrayIntersect(bing, blockFillArr);//对三个数组取并
        return gridInfo;
    }

    /**
     * 分析行、列或块，长度为width^2
     *
     * @param gridsList 行、列或块列表
     * @param refer     做参照的数组，是合法写入数的全集
     * @return 可写入的数组
     */
    private static List<Integer> AnalyseSudokuElement(List<Grid> gridsList, List<Integer> refer) {
        List<Integer> girdsArr = new ArrayList<>();
        int p = 0, q = 0;
        ArrayList<Integer> sudoGridsArr;
        for (int i = 0; i < gridsList.size(); i++) {
            Integer temp = gridsList.get(i).getValue();
            girdsArr.add(temp);
        }
        sudoGridsArr = (ArrayList<Integer>) ArrayUtils.ArrayIntersect(girdsArr, refer);
        return sudoGridsArr;
    }
}
