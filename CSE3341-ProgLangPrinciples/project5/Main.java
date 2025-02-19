class Main {
	public static void main(String[] args) {
		// Initialize the scanner with the input file
		// Scanner S = new Scanner("Correct/14.code"); // Used for Java Debugging
		Scanner S = new Scanner(args[0]);
		Parser.scanner = S;
		
		Procedure p = new Procedure();
		
		p.parse();
		
		// Memory.data = new Scanner("Correct/14.data"); // Used for Java Debugging
		Memory.data = new Scanner(args[1]);

		
		p.execute();
	}
}