package com.tdd.practice.gol;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GolApplication {

	public static void main(String[] args) {
		int generations = 10;
		GOL gol = new GOL(10,"0000000000000000000000000000000000000000000010000000011100000000000000000000000000000000000000000000");
		while ( generations-- >0 ){
			System.out.println( gol.getBoard().replaceAll("/n","\n").replaceAll("1","X")+"\n");
			gol = gol.evolve();
		}
	}
}
