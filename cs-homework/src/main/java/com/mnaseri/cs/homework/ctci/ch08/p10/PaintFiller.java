package com.mnaseri.cs.homework.ctci.ch08.p10;

public class PaintFiller {

    public enum Color {YELLOW, RED, GREEN}
    public void paint(Color[][] input, int row, int col, Color newColor){
        //row: 0 , col:0, newColor: G
            //row: 0 , col:1, newColor: G

        if(!inRange(input, row, col) && input[row][col]==newColor){
            return;
        }
        Color existing= input[row][col]; //R
        input[row][col]=newColor; //G
        if(inRange(input, row+1, col)&& input[row+1][col]==existing){ //Down
            paint(input, row+1, col, newColor);
        }
        if(inRange(input, row-1, col)&& input[row-1][col]==existing){ //Up
            paint(input, row-1, col, newColor);
        }
        if(inRange(input, row, col+1)&& input[row][col+1]==existing){ //Right
            paint(input, row, col+1, newColor);
        }
        if(inRange(input, row, col-1)&& input[row][col-1]==existing){ //Left
            paint(input, row, col-1, newColor);
        }
    }

    private boolean inRange(Color[][] input, int row, int col){
        return row>=0 && row<input.length && col>=0 && col < input[0].length ;
    }
}
