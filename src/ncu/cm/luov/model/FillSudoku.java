package ncu.cm.luov.model;

import ncu.cm.luov.entity.Grid;
import ncu.cm.luov.entity.Sudoku;
import ncu.cm.luov.utils.ArrayUtils;

import java.util.*;

@SuppressWarnings("ALL")
public class FillSudoku {
    /**
     * 填写数独
     * 找寻数组表中第一个最小值的
     *
     * @param sudoku 数独
     */
    public static boolean DoFillSudoku(Sudoku sudoku) {
        AnalyseSudoku analyseSudoku = new AnalyseSudoku();
        Integer[] num = analyseSudoku.AnalyseSudoku(sudoku);//分析所有格子，将可填入的数值填写进格子的info中，得到每个格子可填数量的表
        Integer index = ArrayUtils.ArrayMin(num, 0);
        if (index != null) {
            Grid grid = sudoku.getGrids().get(index);
            if (ObviousFillSudoku(grid, sudoku)) {
                return true;
            } else if (HideFillGrid(grid, sudoku)) return true;
            else {
                for (int i = 0; i < grid.getInfo().size(); i++) {
                    SetGrid(grid, grid.getInfo().get(i));
                    if (DoFillSudoku(sudoku))
                        return true;
                    SetGrid(grid, null);
                }
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * 显示填写法
     * 传入数独和数量列表，直接遍历数量列表，若为1则说明只能填一个数，找到填入。
     *
     * @param sudoku 数独
     * @param grid   准备填写的格子
     * @return flag 修改了数量
     */
    public static boolean ObviousFillSudoku(Grid grid, Sudoku sudoku) {
        if (grid.getInfo().size() == 1) return SetGrid(grid, grid.getInfo().get(0));
        else return false;
    }


    /**
     * 隐式填写法
     * 若检查发现，格子值x在行或列或块中能且仅能出现一次，说明x合法
     *
     * @param sudoku 数独
     * @return 是否填写成功
     */
    public static boolean HideFillGrid(Grid grid, Sudoku sudoku) {
        if (grid.isEmpty()) {
            List<Grid> rowList = (List<Grid>) sudoku.getRow().get(grid.getRow());
            Integer rowFlag = CheckGridByHide(rowList);
            if (rowFlag != null) {
                return SetGrid(grid, rowFlag);
            } else {
                List<Grid> colList = (List<Grid>) sudoku.getCol().get(grid.getCol());
                Integer colFlag = CheckGridByHide(colList);
                if (colFlag != null) {
                    return SetGrid(grid, colFlag);
                } else {
                    List<Grid> blockList = (List<Grid>) sudoku.getBlock().get(grid.getBlock());
                    Integer blockFlag = CheckGridByHide(blockList);
                    if (blockFlag != null)
                        return SetGrid(grid, blockFlag);
                }
            }
        }
//        }
        return false;
    }

    /**
     * 对一个Grid列表，提取已填写值和未填写格子的可能值，作比较
     * 将已填值与可能值比较，得到删选后的可能填写的可能值
     *
     * @param list 待处理的列表
     * @return 填写值。若不唯一，则说明失败，返回null
     */
    private static Integer CheckGridByHide(List<Grid> list) {
        List<Integer> arr = GetSetValue(list);
        List<Integer> otherArr = GetSetInfo(list);
        List<Integer> result = ArrayUtils.ArrayComplement(arr, otherArr);
        if (result.size() == 1)
            return result.get(0);
        else return null;
    }


    /**
     * 将List中Grid值提取出来，转化为数组
     *
     * @param list 格子列表
     * @return 数组
     */
    private static List<Integer> GetSetValue(List<Grid> list) {
        List<Integer> tempList = new ArrayList<>();
        for (Grid aList : list) {
            if (!aList.isEmpty()) {
                tempList.add(aList.getValue());
            }
        }
        return tempList;
    }

    /**
     * 提取List中空格子的所有可填数
     *
     * @param list 待提取列表
     * @return 所有可填数，已去重
     */
    private static List<Integer> GetSetInfo(List<Grid> list) {
        Set<Integer> tempSet = new HashSet();
        for (Grid aList : list) {
            if (aList.isEmpty()) {
                ArrayList<Integer> info = aList.getInfo();
                tempSet.addAll(info);
            }
        }
        List<Integer> outList = new ArrayList<>(tempSet);
        return outList;
    }

    /**
     * 填写格子的方法
     *
     * @param grid  要填写的格子
     * @param value 格子的值
     * @return 设置值返回真，置空返回假
     */
    private static boolean SetGrid(Grid grid, Integer value) {
        if (value != null) {
            grid.setValue(value);
            grid.setEmpty(false);
            ArrayList<Integer> info = grid.getInfo();
            if (info.remove(value))
                grid.setInfo(info);
            return true;
        } else {
            grid.setValue(value);
            grid.setEmpty(true);
            return false;
        }
    }

}

