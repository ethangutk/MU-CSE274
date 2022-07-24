import java.util.Arrays;

public class Driver {

	public static void main(String[] args) {
		System.out.println(15/10);
	
		
		
		WordList list1 = new WordList(3);
		System.out.println(list1.add("a"));
		System.out.println(list1.add("b"));
		System.out.println(list1.contains("a"));
		System.out.println(list1.contains("b"));
		System.out.println(list1.get(0));;
	}
}
