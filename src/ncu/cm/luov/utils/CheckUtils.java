package ncu.cm.luov.utils;

import ncu.cm.luov.entity.Sudoku;

import java.util.*;

public class CheckUtils {
    public static void CheckFillSudoku(Sudoku sudoku){

    }
    /**
     * 判定列表中是否有重复元素
     * @param sudo 列表
     * @return 真或假
     */
    public static boolean checkListRepeat(ArrayList sudo){
        int[] sudos=new int[9];
        int temp= 0;
        for(int i = 0 ; i < 9 ; i++) {
            sudos[i]= (int) sudo.get(i);
//            temp= (int) sudo.get(i);
//            if(temp!=0){
//                sudos[i]=temp;
//            }
        }
        return checkArrayRepeat(sudos);
    }
    /**
     * 判定数组中是否有重复元素
     * @param array 整形数组
     * @return 真或假
     */
    public static boolean checkArrayRepeat(int[] array){
        Set<Integer> set = new HashSet<>();
        int length=0;
        for(int num : array){
            if(num!=0){
                set.add(num);
                length++;
            }
        }
        return set.size() == length;
    }
}