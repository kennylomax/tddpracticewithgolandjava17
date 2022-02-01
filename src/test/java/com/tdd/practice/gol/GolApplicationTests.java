package com.tdd.practice.gol;


import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


@SpringBootTest
class GolApplicationTests {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@AfterEach
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@Test
	void testGOLGame() throws InterruptedException {
        GolApplication.main( new String[]{}) ;
		assertTrue( outContent.toString().contains(
			"0-0-0-0-0-0-0-0-0-0\n0-0-0-0-0-0-0-0-0-0\n0-0-0-X-X-X-0-0-0-0\n0-0-0-0-0-0-0-0-0-0\n0-X-0-0-0-0-0-X-0-0\n0-X-0-0-0-0-0-X-0-0\n0-X-0-0-0-0-0-X-0-0\n0-0-0-0-0-0-0-0-0-0\n0-0-0-X-X-X-0-0-0-0\n0-0-0-0-0-0-0-0-0-0\n" ));
    }

}
