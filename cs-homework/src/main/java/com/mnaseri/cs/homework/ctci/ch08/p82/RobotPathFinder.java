package com.mnaseri.cs.homework.ctci.ch08.p82;

import java.util.ArrayList;
import java.util.List;

public class RobotPathFinder {

    public static void main(String[] args) {
        System.out.println("------------------------------");
        int[][] input = new int[][]{{0, 0, 0}, {0, -1, 0}, {0, 0, 0}};
        RobotPathFinder finder = new RobotPathFinder();
        List<Point> output = finder.find(input);
        System.out.println("first output = " + output);
        System.out.println("------------------------------");
        input = new int[][]{{0, -1, 0},
                {0, 0, 0},
                {0, 0, 0}};
        output = finder.find(input);
        System.out.println("second output = " + output);
        System.out.println("------------------------------");
        input = new int[][]{{0, 0, 0, -1},
                {0, -1, 0, -1},
                {0, 0, 0, 0}};
        output = finder.find(input);
        System.out.println("third output = " + output);
        System.out.println("------------------------------");
    }

    public List<Point> find(int[][] input) {
        return find(input, 0, 0, new ArrayList<>());
    }

    private List<Point> find(int[][] input, int row, int col, List<Point> breadcrumb) {
        //row = 0, col = 0, breadcrumb= {}
        //row = 0, col = 1, breadcrumb= {Point(0,0)}
        //row = 0, col = 2, breadcrumb= {Point(0,0),Point(0,1)}
        //row = 0, col = 3, breadcrumb= {Point(0,0),Point(0,1),Point(0,2)} -->null
        //row = 1, col = 2, breadcrumb= {Point(0,0),Point(0,1)}
        //row = 1, col = 3, breadcrumb= {Point(0,0),Point(0,1), Point(1,2)} --> null
        //row = 2, col = 2, breadcrumb= {Point(0,0),Point(0,1), Point(1,2), Point(2,2)} --> THE BREADCRUMB
        if (!candidateIsInRange(input, row, col)) {
            return null;
        }
        if (input.length - 1 == row && input[0].length - 1 == col) {
            breadcrumb.add(new Point(row, col));
            return breadcrumb;
        }
        List<Point> newPath = new ArrayList<>(breadcrumb);
        newPath.add(new Point(row, col)); // {Point(0,0), Point(0,1)}
        List<Point> right = null;
        if (candidateIsInRange(input, row, col + 1)) { //Point(0,1),Point(0,2)
            right = find(input, row, col + 1, newPath);
            if (right != null) {
                return right;
            }
        } else if (candidateIsInRange(input, row + 1, col)) {
            newPath = new ArrayList<>(breadcrumb);
            newPath.add(new Point(row, col));
            return find(input, row + 1, col, newPath);
        }
        return null;
    }

    private boolean candidateIsInRange(int[][] input, int row, int col) {
        return (row < input.length && row >= 0 && col < input[0].length && col >= 0 && input[row][col] != -1);
    }
}
