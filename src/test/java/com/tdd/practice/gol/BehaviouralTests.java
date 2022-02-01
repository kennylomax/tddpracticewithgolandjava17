package com.tdd.practice.gol;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class BehaviouralTests {
    
    @Order(1)    
    @ParameterizedTest(name = "A fresh GOL board of size {0} should look like {1}")
	@CsvSource({
        "1, '0\n'",
        "2, '0-0\n0-0\n'",
        "3, '0-0-0\n0-0-0\n0-0-0\n'",
		}
	)
    public void testFreshGOLBoards( int edge, String board){
       GOL gol = new GOL(edge);  
       assertEquals( board, gol.getBoard());
    }

    @Order(2)    
    @ParameterizedTest(name = "Populating a GOL board of size {0} with \"{1}\" should look like {2}")
	@CsvSource({
        "1, 0, 				'0\n'",
        "2, 0110, 			'0-1\n1-0\n'",
        "3, 101 010 101,	'1-0-1\n0-1-0\n1-0-1\n'",
        "3, - 010 -,		'0-0-0\n0-1-0\n0-0-0\n'",    }
	)

    void testPopulatingGOLBoards(int edge, String cells, String board){
        GOL gol = new GOL(edge, cells);
		assertEquals( board, gol.getBoard());
    }

    @Disabled
    @Order(3)    
    @ParameterizedTest(name = "A GOL board with {0} should evolve to {1}")
	@CsvSource({
		"0000000100001000010000000, '0-0-0-0-0\n0-0-0-0-0\n0-1-1-1-0\n0-0-0-0-0\n0-0-0-0-0\n'",
		}
	)
	void testGOLEvolution(String cells, String board){
        int edge = (int)Math.sqrt(cells.length());
        GOL gol = new GOL(edge, cells);
		//gol=gol.evolve();
		assertEquals(  board, gol.getBoard() );
	}   

    @Tag("internal")
    @Order(4)    
    @ParameterizedTest(name = "GOL rule: a cell in state {0} with {1} live neighbours -> state {2}")
	@CsvSource({
		// MyState, MyLiveNeighbours, MyNextSate
		// Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
		"0,3,1",
		// Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
		"1,0,0",
		"1,1,0",
		// Any live cell with two or three live neighbours lives on to the next generation.
		"1,2,1",
		"1,3,1",
		// Any live cell with more than three live neighbours dies, as if by overpopulation.	
		"1,4,0",
		"1,5,0",
		"1,6,0",
		"1,7,0",
		"1,8,0",
		}
	)
	void testGOLRules(int s1, int n, int s2){
		assertEquals(  s2, GOL.applyRuleToCell(s1,n) );
	}

}
