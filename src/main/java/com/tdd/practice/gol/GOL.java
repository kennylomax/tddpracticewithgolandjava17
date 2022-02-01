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

    public int getIndexForXY(int x, int y){
        while (y<0)
            y+=edge;
        while (x<0)
            x+=edge;   
        return (y % edge) * edge + (x % edge);
    }

    public void po2d(){
        int c=1;
        for (int i=0;i<edge*edge;i++){
            board[i]=c;
            c=c*2;
        }
    }

    public GOL sumAllNeigbours(){
        GOL sums = new GOL(edge);        
        for (int x = 0;x<edge; x++)
            for (int y=0; y<edge;y++){
                int sum =0;                  
                for (int a = x-1; a<=x+1; a++){
                    for ( int b=y-1 ; b<=y+1; b++){
                        if ( !(a==x && b==y)){
                            sum += board[ getIndexForXY( a, b)];
                        }
                    }
                    sums.board[ getIndexForXY( x, y)] = sum;
                }
            }
        return sums;
    }

}
