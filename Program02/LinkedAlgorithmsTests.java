import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedAlgorithmsTests {

	@Test
	public void testConstructors() {
		
		
		String[] arrayToInput = { "5", "4", "3" };
		LinkedAlgorithms list1 = new LinkedAlgorithms(arrayToInput);
		LinkedAlgorithms list2 = new LinkedAlgorithms();
		LinkedAlgorithms list3 = new LinkedAlgorithms(list1);
		
		assertEquals( 3, list1.size());
		assertEquals( 0, list2.size());
		assertEquals( 3, list3.size());
		assertEquals( list1.toString(), list3.toString());
	}

	@Test
	public void testInsert() {
		//INSERT FIRST FUNCTION
		LinkedAlgorithms list1 = new LinkedAlgorithms();
		list1.insertFirst("2");
		list1.insertFirst("1");
		
		assertEquals( 2, list1.size());
		assertEquals( "[1 2]", list1.toString());
		
		
		//INSERT LAST FUNCTION
		list1 = new LinkedAlgorithms();
		list1.insertLast("1");
		list1.insertLast("2");
		
		assertEquals( 2, list1.size());
		assertEquals( "[1 2]", list1.toString());
		
		
		//INSERT AT FUNCTION
		String[] arrayToInput = { "1", "2", "3", "5", "6", "7", "9" };
		list1 = new LinkedAlgorithms(arrayToInput);
		
		list1.insertAt(0, "0");
		list1.insertAt(4, "4");
		list1.insertAt(8, "8");
		
		assertEquals( 10, list1.size());
		assertEquals( "[0 1 2 3 4 5 6 7 8 9]", list1.toString());
		
		
		//INSERT BEFORE
		list1 = new LinkedAlgorithms(arrayToInput);
		list1.insertBefore("0", "1");
		list1.insertBefore("4", "5");
		list1.insertBefore("8", "9");
		
		assertEquals( 10, list1.size());
		assertEquals( "[0 1 2 3 4 5 6 7 8 9]", list1.toString());
		
		
		//INSERT AFTER
		list1 = new LinkedAlgorithms(arrayToInput);
		list1.insertAfter("4", "3");
		list1.insertAfter("8", "7");
		list1.insertAfter("10", "9");
		
		assertEquals( 10, list1.size());
		assertEquals( "[1 2 3 4 5 6 7 8 9 10]", list1.toString());
	}
	
	@Test
	public void testToArray() {
		String[] arrayToInput = { "1", "2", "3", "5", "6", "7", "9" };
		LinkedAlgorithms list1 = new LinkedAlgorithms(arrayToInput);
		assertArrayEquals(arrayToInput, list1.toArray());
	}
	
	@Test
	public void testEquals() {
		String[] arrayToInput = { "1", "2", "3", "5", "6", "7", "9" };
		LinkedAlgorithms list1 = new LinkedAlgorithms(arrayToInput);
		LinkedAlgorithms list2 = new LinkedAlgorithms(list1);
		LinkedAlgorithms list3 = new LinkedAlgorithms();
		
		
		assertEquals( true , list1.equalsLinkedList(list2));
		assertEquals( false, list1.equalsLinkedList(list3));
	}
	
	@Test
	public void testGet() {
		String[] arrayToInput = { "1", "2", "3", "4", "5", "6", "7" };
		LinkedAlgorithms list1 = new LinkedAlgorithms(arrayToInput);
		
		
		assertEquals( "1" , list1.getFirst() );
		assertEquals( "7" , list1.getLast() );
		assertEquals( "1" , list1.getAt(0) );
		assertEquals( "5" , list1.getAt(4) );
		assertEquals( "7" , list1.getAt(6) );
	}
	
	@Test
	public void testReverse() {
		String[] arrayToInput = { "1", "2", "3", "4", "5", "6", "7" };
		LinkedAlgorithms list1 = new LinkedAlgorithms(arrayToInput);
		
		list1.reverse();
		
		assertEquals( "[7 6 5 4 3 2 1]" , list1.toString() );
	}
	
	@Test
	public void testToUpper() {
		String[] arrayToInput = { "e", "T", "h", "a", "n", "2", "0" };
		LinkedAlgorithms list1 = new LinkedAlgorithms(arrayToInput);
		
		list1.toUpper();
		
		assertEquals( "[E T H A N 2 0]" , list1.toString() );
	}
	
	@Test
	public void testGetConcatenation() {
		String[] arrayToInput = { "1", "2", "3", "4", "5", "6", "7" };
		LinkedAlgorithms list1 = new LinkedAlgorithms(arrayToInput);
		
		assertEquals( "1234567" , list1.getConcatenation() );
	}
	
	@Test
	public void testGetAlphabeticallyLast() {
		String[] arrayToInput = { "Z", "Y", "X", "G", "D", "B", "E" };
		LinkedAlgorithms list1 = new LinkedAlgorithms(arrayToInput);
		
		assertEquals( "Z" , list1.getAlphabeticallyLast() );
	}
	
	@Test
	public void testGetIndexAlphabeticallyLast() {
		String[] arrayToInput = { "Z", "Y", "X", "G", "D", "B", "E" };
		LinkedAlgorithms list1 = new LinkedAlgorithms(arrayToInput);
		
		assertEquals( 0 , list1.indexOfAlphabeticallyLast() );
	}
	
	@Test
	public void testAnagram() {
		String[] arrayToInput1 = { "Z", "Y", "X", "G", "D", "B", "E" };
		String[] arrayToInput2 = { "B", "X", "Y", "G", "D", "Z", "E" };
		LinkedAlgorithms list1 = new LinkedAlgorithms(arrayToInput1);
		LinkedAlgorithms list2 = new LinkedAlgorithms(arrayToInput2);
		LinkedAlgorithms list3 = new LinkedAlgorithms();
		
		
		assertEquals(true , list1.anagrams(list2) );
		assertEquals(false, list1.anagrams(list3) );
	}
	
	@Test
	public void testContainsAndFind() {
		String[] arrayToInput = { "1", "2", "3", "4", "5", "6", "7" };
		LinkedAlgorithms list1 = new LinkedAlgorithms(arrayToInput);
		
		assertEquals( 1, list1.find("2") );
		assertEquals( 6, list1.find("7") );
		assertEquals( 0, list1.find("1") );
		assertEquals( -1,list1.find("10") );
		
		assertEquals( true, list1.contains("2") );
		assertEquals( true, list1.contains("7") );
		assertEquals( true, list1.contains("1") );
		assertEquals( false,list1.contains("10") );
	}
	
	@Test
	public void testRemove() {
		String[] arrayToInput1 = { "1", "2", "3", "4", "5", "6", "7" };
		LinkedAlgorithms list1 = new LinkedAlgorithms(arrayToInput1);
		//REMOVE FIRST
		assertEquals( "1", list1.removeFirst() );
		
		//REMOVE LAST
		assertEquals( "7", list1.removeLast() );
		
		//REMOVE AT
		list1 = new LinkedAlgorithms(arrayToInput1);
		
		assertEquals( "7", list1.removeAt(6) );
		assertEquals( "1", list1.removeAt(0) );
		assertEquals( "4", list1.removeAt(2) );
		assertEquals( "2356", list1.getConcatenation() );
		
		//REMOVE FIRST OCCURANCE
		String[] arrayToInput2 = { "2", "2", "3", "4", "5", "6", "2", "10" };
		list1 = new LinkedAlgorithms(arrayToInput2);
		
		assertEquals( true, list1.removeFirstOccurrenceOf("2") );
		assertEquals( true, list1.removeFirstOccurrenceOf("3") );
		assertEquals( true, list1.removeFirstOccurrenceOf("10") );
		assertEquals( false, list1.removeFirstOccurrenceOf("11") );
		assertEquals( "24562", list1.getConcatenation() );
		
		//REMOVE ALL OCCURANCES
		String[] arrayToInput3 = { "2", "2", "3", "4", "5", "6", "2" };
		list1 = new LinkedAlgorithms(arrayToInput3);
		
		assertEquals( 3, list1.removeAllOccurrencesOf("2") );
		assertEquals( "3456", list1.getConcatenation() );
		
		
	}
}





