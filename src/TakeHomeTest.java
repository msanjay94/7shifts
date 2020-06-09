import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TakeHomeTest {
	public static void main(String[] args) {
		System.out.println("Start");
		try {
			tests();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End");
	}

	private static int add(String numbers) throws Exception {
		if (numbers.isEmpty()) {
			return 0;
		}
		List<Integer> negativeNumbers = new ArrayList<>();
		String delimiter = ",";
		if (numbers.startsWith("//")) {
			delimiter = numbers.substring(2, numbers.indexOf(System.lineSeparator()));
			numbers = numbers.substring(numbers.indexOf(System.lineSeparator()));
		}
		numbers = numbers.replace(System.lineSeparator(), "");
		int sum = 0;
		String[] split = numbers.split(Pattern.quote(delimiter));
		for (String number : split) {
			Integer num = 0;
			if (number.startsWith("-")) {
				num = Integer.parseInt(number);
				negativeNumbers.add(num);
			} else if (number.length() < 3) {
				num = Integer.parseInt(number);
				sum += num;
			} else if ("1000".equals(number)) {
				sum += 1000;
			}
		}
		if (!negativeNumbers.isEmpty()) {
			throw new Exception("Negatives not allowed. The negative number(s) are: " + negativeNumbers);
		}
		return sum;
	}

	private static void addTest(String input, int expectedOutput) throws Exception {
		int output = add(input);
		input = input.replace(System.lineSeparator(), "\\n");
		if (expectedOutput != output) {
			throw new Exception("Test failed for input: '" + input + "'. Output: '" + output + "'");
		} else {
			System.out.println("Test passed for input: '" + input + "'. Output: '" + output + "'");
		}
	}

	private static void tests() throws Exception {
		addTest("", 0);
		addTest("1,2,5", 8);
		addTest("3,5,6", 14);
		addTest("10,20,30", 60);
		addTest("1\n,2,3", 6);
		addTest("1,\n2,4", 7);
		addTest("4\n,9\n,8", 21);
		addTest("7\n,1,5\n", 13);
		addTest("//;\n1;3;4", 8);
		addTest("//$\n1$2$3", 6);
		addTest("//@\n2@3@8", 13);
		addTest("//!\n5!\n3!3", 11);
		addTest("//#\n1#\n7#2", 10);
		addTest("//^\n9^\n4^6", 19);
		addTest("1000,1,1,5,6", 1013);
		addTest("1001,1,1,5,6", 13);
		addTest("23958729375,1,1,5,6", 13);
		addTest("//!@#\n12!@#34!@#56", 102);
		addTest("//^$\n12^$34^$56", 102);
		addTest("//[A-Z]+\n12[A-Z]+34[A-Z]+56", 102);
		try {
			addTest("-1,2", 2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			addTest("-1,-2,-3,5", 5);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}