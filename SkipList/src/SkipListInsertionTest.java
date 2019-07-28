import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class SkipListInsertionTest {

	@Test
	public void testInsert() {
		SkipList test=new SkipList(100000000);
	    int result=test.insert(1);
		assertEquals(1, result);
	}

}
