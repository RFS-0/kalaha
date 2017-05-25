package test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

import game.Game;

public class NormalHouseDistributions {

	@Before
	public void setUp() throws Exception {
		
		int[] Houses1 = { 6, 0, 0, 0, 0, 0,
				          0, 0, 0, 0, 0, 0};
		
		int[] Houses2 = { 0, 6, 0, 0, 0, 0,
		                  0, 0, 0, 0, 0, 0};
		
		int[] Houses3 = { 0, 0, 6, 0, 0, 0,
		                  0, 0, 0, 0, 0, 0};
		
		int[] Houses4 = { 0, 0, 0, 6, 0, 0,
		                  0, 0, 0, 0, 0, 0};
		
		int[] Houses5 = { 0, 0, 0, 0, 6, 0,
		                  0, 0, 0, 0, 0, 0};
		
		int[] Houses6 = { 0, 0, 0, 0, 0, 6,
		                  0, 0, 0, 0, 0, 0};
		
		int[] Houses7 = { 0, 0, 0, 0, 0, 0,
                          6, 0, 0, 0, 0, 0};
		
		int[] Houses8 = { 0, 0, 0, 0, 0, 0,
                          0, 6, 0, 0, 0, 0};
		
		int[] Houses9 = { 0, 0, 0, 0, 0, 0,
                          0, 0, 6, 0, 0, 0};
		
		int[] Houses10 = { 0, 0, 0, 0, 0, 0,
                           0, 0, 0, 6, 0, 0};
		
		int[] Houses11 = { 0, 0, 0, 0, 0, 0,
                           0, 0, 0, 0, 6, 0};
		
		int[] Houses12 = { 0, 0, 0, 0, 0, 0,
                           0, 0, 0, 0, 0, 6};
		
	}
	
	@Test
	public void test0() {
		int[] expectedArray0 = { 6, 0, 0, 0, 0, 0,
		                        0, 0, 0, 0, 0, 0};
	    int[] Houses0 = { 6, 0, 0, 0, 0, 0,
				          0, 0, 0, 0, 0, 0};
		
	    assertArrayEquals(expectedArray0, Houses0);
	}

	@Test
	public void test1() {
		
		int[] Houses1 = { 6, 0, 0, 0, 0, 0,
		                  0, 0, 0, 0, 0, 0};
		
		Game game = new Game(Houses1);
		
				
	}
	
	@Test
	public void test2() {
		
	}

}
