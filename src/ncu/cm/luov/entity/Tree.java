package ncu.cm.luov.entity;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private ArrayList<Integer> tree;

    public Tree(ArrayList<Integer> tree) {
        this.tree = tree;
    }

    public ArrayList<Integer> getTree() {
        return tree;
    }

    public void setTree(ArrayList<Integer> tree) {
        this.tree = tree;
    }

    @Override
    public String toString() {
        return "走的步数" +
                "tree=" + tree +
                '}';
    }
}
