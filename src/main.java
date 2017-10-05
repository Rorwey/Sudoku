import ncu.cm.luov.entity.Sudoku;
import ncu.cm.luov.model.CreateSudoku;

import static ncu.cm.luov.model.FillSudoku.DoFillSudoku;
// G:\SudoEasy.txt

public class main {
    public static void main(String[] args) {
        CreateSudoku createSudoku = new CreateSudoku();
        Sudoku sudoku = createSudoku.ComprehensiveCreateSudoku();//根据文件，生成数独对象
        System.out.println("题目读取完毕");
        System.out.println(sudoku.toString());
        DoFillSudoku(sudoku);
        System.out.println(sudoku.toString());
    }
}
