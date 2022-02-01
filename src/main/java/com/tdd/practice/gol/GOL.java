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

    public static int applyRuleToCell( int state1, int liveNeighbours){
        if(state1==0 && liveNeighbours==3)
            return 1;  
        if (state1==1 && liveNeighbours<2)
            return 0;
        if (state1==1 && (liveNeighbours==2 || liveNeighbours==3))
            return 1;
        return 0;      
    }

}
