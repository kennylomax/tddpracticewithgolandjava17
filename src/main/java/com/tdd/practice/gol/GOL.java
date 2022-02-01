package com.tdd.practice.gol;

public class GOL {
    int[]board;
    int edge;

    GOL(int edge){
        this.edge = edge;
        board = new int[edge*edge];
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
