import java.util.HashMap;
import java.util.Stack;

public class Memory {
    static class Value {
        Core type;
        int intValue;
        int[] arrayValue;
    }
    
    static HashMap<String, Value> global;
    static Stack<HashMap<String, Value>> local;
    // Use this flag to keep track of when we finish the DeclSeq
    static boolean dsFlag;

    static void store(String var, int value) {
        // Look up based on var, change the intValue to the value passed in
    }

	/* Search of Variable from Nested Local Scopes first and get it refrence, if it doesn't exist, 
	 * then search it from Global and get it refrence.
	 */
    public static Memory.Value getValue(String name) {
		// Variable Initializations
		Memory.Value result = null;
		Stack<HashMap<String, Memory.Value>> temp = new Stack<>();

		// Searching in all nested Local Scopes
		while (!Memory.local.empty()) {
			HashMap<String, Memory.Value> map = Memory.local.pop();

			if (map.containsKey(name)) {
				result = map.get(name);

				temp.push(map);
				break;
			}

			temp.push(map);
		}

		while (!temp.empty()) {
			Memory.local.push(temp.pop());
		}
		
		// Searching in all Global Scopes
		if (result == null) {
            result = Memory.global.get(name);
        }

		// Return Statement
		return result;
	}
}