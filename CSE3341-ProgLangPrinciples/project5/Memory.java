import java.util.*;

class Memory {
	//scanner is stored here as a static field so it is avaiable to the execute method for factor
	public static Scanner data;
	
	// Class and data structures to represent variables
	static class Value {
		Core type;
		int integerVal;
		int[] arrayVal;
		RefCount refCount = new RefCount();
		Value(Core t) {
			this.type = t;
		}
	}

	// Class and data structure to allow the store of the reference of integer
	static class RefCount {
		int refCount = 0;
	}
	
	public static HashMap<String, Value> global;
	public static Stack<Stack<HashMap<String, Value>>> local;

	public static int totalObjCount = 0;

	public static HashMap<String, Function> funcMap;
	
	// Helper methods to manage memory

	/* This function allow the decrementation of object reference count of the 
	 * variables inside the scope
	 */
	public static void decRefCounts(HashMap<String, Value> scope) {
		for (Map.Entry<String, Value> entry : scope.entrySet()) {
			Value val = entry.getValue();
			if (val.type == Core.ARRAY && val.arrayVal != null) {
				val.refCount.refCount--; // Object Reference Count Variable Decrementation
				if (val.refCount.refCount == 0) { // Do if Object Refrence Count reaches 0 (object become unreachable).
					totalObjCount--; // Total Object Count Variable Decrementation
					System.out.println("gc:" + totalObjCount); // Print the Total Object Count Variable
				}
			}
		}
	}
	
	// This inializes the global memory structure
	// Called before executing the DeclSeq
	public static void initializeGlobal() {
		global = new HashMap<String, Value>();
		funcMap = new HashMap<String, Function>();
	}
	
	// Initializes the local data structure
	// Called before executing the main StmtSeq
	public static void initializeLocal() {
		local = new Stack<Stack<HashMap<String, Value>>>();
		local.push(new Stack<HashMap<String, Value>>());
		local.peek().push(new HashMap<String, Value>());
	}
	
	// Pushes a "scope" for if/loop stmts
	public static void pushScope() {
		local.peek().push(new HashMap<String, Value>());
	}
	
	// Pops a "scope"
	public static void popScope() {
		HashMap<String, Value> scope = local.peek().pop(); 
		decRefCounts(scope); // Calling decRefCounts with popped scope as argument
	}
	
	// Handles decl integer
	public static void declareInteger(String id) {
		Value v = new Value(Core.INTEGER);
		if (local != null) {
			local.peek().peek().put(id, v);
		} else {
			global.put(id, v);
		}
	}
	
	// Handles decl record
	public static void declareArray(String id) {
		Value v = new Value(Core.ARRAY);
		if (local != null) {
			local.peek().peek().put(id, v);
		} else {
			global.put(id, v);
		}
	}
	
	// Retrives a value from memory (integer or record at index 0
	public static int load(String id) {
		int value;
		Value v = getLocalOrGlobal(id);
		if (v.type == Core.INTEGER) {
			value = v.integerVal;
		} else {
			value = v.arrayVal[0];
		}
		return value;
	}
	
	// Retrieves a record value from the index
	public static int load(String id, int index) {
		Value v = getLocalOrGlobal(id);
		return v.arrayVal[index];
	}
	
	// Stores a value (integer or record at index 0
	public static void store(String id, int value) {
		Value v = getLocalOrGlobal(id);
		if (v.type == Core.INTEGER) {
			v.integerVal = value;
		} else {
			v.arrayVal[0] = value;
		}
	}
	
	// Stores a value at record index
	public static void store(String id, int index, int value) {
		Value v = getLocalOrGlobal(id);
		v.arrayVal[index] = value;
	}
	
	// Handles "new record" assignment
	public static void allocate(String id, int index) {
		Value v = getLocalOrGlobal(id);
		if (v.arrayVal != null) { // Handles the case when @v.arrayVal points to an old array
			v.refCount.refCount--;
			v.refCount = new RefCount();
		}
		v.arrayVal = new int[index];
		v.refCount.refCount++; // Incrementing the refCount of @v since it points to a new create array.
		totalObjCount++; // Total Array Count Variable Incrementation
		System.out.println("gc:" + totalObjCount); // Print the Total Array Count Variable
	}
	
	// Handles "id := record id" assignment
	public static void alias(String lhs, String rhs) {
		Value v1 = getLocalOrGlobal(lhs);
		Value v2 = getLocalOrGlobal(rhs);
		if (v1.arrayVal != null) { // Handle the case for when @v1.arrayVal points to an old array
			v1.refCount.refCount--;
			if (v1.refCount.refCount == 0) {
				totalObjCount--; // Total Object Count Variable Decrementation
				System.out.println("gc:" + totalObjCount); // Print the Total Object Count Variable
			}
		}
		v1.arrayVal = v2.arrayVal;
		v1.refCount = v2.refCount; // @v1 holds the refrence count of @v2
		// Increment Object's Reference Count if 
		if (v2.arrayVal != null) { 
			v2.refCount.refCount++; // Incermenting @v2 refrence count of the object
		}
	}
	
	// Looks up value of the variables, searches local then global
	private static Value getLocalOrGlobal(String id) {
		Value result;
		if (local.peek().size() > 0) {
			if (local.peek().peek().containsKey(id)) {
				result = local.peek().peek().get(id);
			} else {
				HashMap<String, Value> temp = local.peek().pop();
				result = getLocalOrGlobal(id);
				local.peek().push(temp);
			}
		} else {
			result = global.get(id);
		}
		return result;
	}
	
	
	/*
	 *
	 * New mothods for pushing/popping frames
	 *
	 */

	public static void pushFrameAndExecute(String name, Parameter args) {
		Function f = funcMap.get(name);
		
		ArrayList<String> formals = f.param.execute();
		ArrayList<String> arguments = args.execute();
		
		Stack<HashMap<String, Value>> frame = new Stack<HashMap<String, Value>>();
		frame.push(new HashMap<String, Value>());
		
		for (int i=0; i<arguments.size(); i++) {
			Value v1 = getLocalOrGlobal(arguments.get(i));
			Value v2 = new Value(Core.ARRAY);
			v2.arrayVal = v1.arrayVal;
			v2.refCount = v1.refCount;
			v1.refCount.refCount++; // Incrmenting Object's Refrence Count since formal parameter hold its refrence
			frame.peek().put(formals.get(i), v2);
		}
		
		local.push(frame);
		
		f.ss.execute();
	}
	 
	public static void popFrame() {
		HashMap<String, Value> funcScope = local.pop().peek();
		decRefCounts(funcScope); // Calling decRefCounts with popped scope as argument
	}
}