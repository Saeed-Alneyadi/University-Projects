import java.util.Stack;
import java.util.Map;

public class Main {
	public static boolean idExistCheck(String name) {
		boolean result = false;
		Stack<Map<String, String>> temp = new Stack<>();

		if (!stack.empty()) {
			while (!stack.empty()) {
				Map<String, String> map = stack.pop();

				if (map.containsKey(name)) {
					result = true;

					temp.push(map);
					break;
				}

				temp.push(map);
			}

			while (!temp.empty()) {
				stack.push(temp.pop());
			}
		}

		return result;
	}

	public static boolean idTypeCheck(String name, String type) {
		boolean result = false;
		Stack<Map<String, String>> temp = new Stack<>();

		while (!stack.empty()) {
			Map<String, String> map = stack.pop();

			if (map.containsKey(name)) {
				String realType = map.get(name);

				if (type.equals(realType)) {
					result = true;
					temp.push(map);
					break;
				} else {
					result = false;
				}
			}

			temp.push(map);
		}

		while (!temp.empty()) {
			stack.push(temp.pop());
		}

		return result;
	}

	public static Stack<Map<String, String>> stack = new Stack<>();
	public static void main(String[] args) {
		// Initialize the scanner with the input file
		Scanner S = new Scanner(args[0]);
		Procedure P = new Procedure();
		P.parse(S);
		P.print(S);
		P.semanticCheck(S);
	}
}