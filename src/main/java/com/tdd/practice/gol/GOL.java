package com.tdd.practice.gol;

public class GOL {
    int[]board;
    int edge;

    GOL(int edge){
        this.edge = edge;
        board = new int[edge*edge];
    }

    GOL(int edge, String cells){
        this.edge = edge;
        board = new int[edge*edge];
        String emptyRow = "0".repeat(edge);
        cells = cells.replaceAll("-", emptyRow).replaceAll(" ","");
        for (int c = 0; c< cells.length(); c++)
           board [c] = cells.charAt(c)-'0';
    }

    public String getBoard(){
        StringBuilder sb = new StringBuilder();
        int n = 0;
        for (int y=0;y<edge;y++){
            for (int x=0;x<edge;x++){
                sb.append(board[n++]);
                if (x==edge-1)
                    sb.append("\n");
                else   
                    sb.append("-");
            }
        }
        return sb.toString();
    }

}
