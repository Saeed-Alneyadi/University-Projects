class Main {
	public static void main(String[] args) {
		// Initialize the scanner with the input file
		Scanner S = new Scanner(args[0]);
		Scanner inS = new Scanner(args[1]);
		Parser.scanner = S;
		Parser.inScanner = inS;
		
		Procedure p = new Procedure();
		
		p.parse();
		p.execute(); // Procedure Execution
	}
}