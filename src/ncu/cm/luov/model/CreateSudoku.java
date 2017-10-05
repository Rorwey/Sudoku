package ncu.cm.luov.model;
import ncu.cm.luov.entity.Grid;
import ncu.cm.luov.entity.Sudoku;
import ncu.cm.luov.utils.FileUtils;
import ncu.cm.luov.utils.InputUtils;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateSudoku {
    public Sudoku ComprehensiveCreateSudoku(){
//        int width = InputUtils.inputInt("请输入要解得数独的最小格数\n如，9*9数独，单位格数为3*3，则输入3");
        int width = 3;
        System.out.println("将填写一个单元为3，总9*9的数独");
        String str = readFile();
        Sudoku sudoku = CreateSudoku(str, width);
        return sudoku;
    }
    /**
     * 要求输入文件路径，根据其去读取二维数独文件
     *
     * @return 读取后的数据，以字符串形式保存
     */
    private String readFile() {
//        File file = InputUtils.inputFile("请输入文件绝对路径");
        File file = new File("EasySudoku.txt");//临时供测试用
        System.out.println("读取数独文件：EasySudoku.txt");
        return FileUtils.readTextFromFile(file);
    }

    /**
     * 根据读入的字符串创建数独二维数组
     *
     * @param sudo 读入的字符串
     * @return 数独二维数组
     */
    private Integer[][] Create2thArray(String sudo) {
        Integer[][] sudoArray = new Integer[9][9];
        String[] sudoRow = sudo.split("\r\n");//分割为一行
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                i = Integer.parseInt(sudoRow[j]);
                if (i != 0) {
                    sudoArray[i][j] = i;
                }else {
                    sudoArray[i][j] = null;
                }
            }
        }
        return sudoArray;
    }

    /**
     *
     * @param sudo 储存信息的字符串
     * @param width 一个block的长度
     * @return 数独
     */
    private Sudoku CreateSudoku(String sudo,int width){
        ArrayList<Grid>grids = new ArrayList<>();
        String[] sudoRow = sudo.split("\r\n");//分割为一行
        boolean isEmpty;
        Integer value;
        Integer flag = 0;
        for (int i = 0; i < width*width; i++) {
            for (int j = 0; j < width*width; j++) {
               Integer temp = Integer.parseInt(String.valueOf(sudoRow[i].toCharArray()[j]));
                if (temp != 0) {
                    value=temp;
                    isEmpty=false;
                }else {
                    value=null;
                    isEmpty=true;
                }
                flag++;
                Grid grid = new Grid(value, isEmpty, flag);
                grids.add(grid);
            }
        }
        Map<Integer, List<Grid>> row = CreateSudokuRow(grids, width);
        Map<Integer, List<Grid>> col=CreateSudokuCol(grids,width);
        Sudoku sudoku = new Sudoku(grids, width, row, col);//在这里创建数独对象
        Map<Integer, List> block = CreateSudokuBlock(sudoku, width);
        sudoku.setBlock(block);
        return sudoku;
    }

    private Map<Integer, List<Grid>> CreateSudokuRow(ArrayList<Grid> grids, int width) {
        Map<Integer, List<Grid>> row=new HashMap<>();
        int broad=width * width;
        for (int i = 0; i <broad ; i++) {
            List<Grid> rowList = new ArrayList<>();
            for (int j = 0; j < broad; j++) {
                Grid grid = grids.get(i * broad + j);
                rowList.add(grid);
                grid.setRow(i);
            }
            row.put(i, rowList);

        }
        return row;
    }
    private Map<Integer, List<Grid>> CreateSudokuCol (ArrayList<Grid> grids, int width) {
        Map<Integer, List<Grid>> col=new HashMap<>();
        int broad=width * width;
        for (int i = 0; i < broad; i++) {
            List<Grid> colList = new ArrayList<>();
            for (int j = 0; j < broad; j++) {
                Grid grid = grids.get(j * broad + i);
                colList.add(grid);
                grid.setCol(i);
            }
            col.put(i, colList);
        }
        return col;
    }
    private Map<Integer, List> CreateSudokuBlock(Sudoku sudoku,int width) {
        Map<Integer, List> block=new HashMap<>();
        int broad=width * width,flag=0;

        for (int p = 0; p <width; p++) {//第几大块，行
            for (int q=0;q<width;q++){//第几大块，行中第几个块
                List<Object> blockList = new ArrayList<>();
                for (int i = p * width; i < width+p*width; i++) {
                    Map row = sudoku.getRow();
                    ArrayList<Grid> rowList = (ArrayList<Grid>) row.get(i);
                    for (int j = q * width; j < width+q*width; j++) {
                        Grid grid = rowList.get(j);
                        blockList.add(grid);
                        grid.setBlock(flag);
                    }
                }
                block.put(flag,blockList);
                flag=flag+1;
            }
        }
        return block;
    }
}
