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

	private static int add(String numbers) {
		if (numbers.isEmpty()) {
			return 0;
		}
		numbers = numbers.replace(System.lineSeparator(), "");
		int sum = 0;
		String[] split = numbers.split(",");
		for (String number : split) {
			Integer num = Integer.parseInt(number);
			sum += num;
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
	}
}