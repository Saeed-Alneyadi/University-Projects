import java.util.ArrayList;

public class DeclSeq {
	Decl decl;
	DeclSeq ds;
	Function func;
	
	public void parse() {
		if (Parser.scanner.currentToken() != Core.PROCEDURE) {
			decl = new Decl();
			decl.parse();
		} else {
			func = new Function();
			func.parse();
		}

		if (Parser.scanner.currentToken() != Core.BEGIN) {
				ds = new DeclSeq();
				ds.parse();
		}
	}
	
	public void print(int indent) {
		if (decl != null) {
			decl.print(indent);
		} else {
			func.print(indent);
		}
		
		if (ds != null) {
			ds.print(indent);
		}
	}
	
	public void execute() {
		if (decl != null) {
			decl.execute();
		} else {
			func.execute();
		}
		
		if (ds != null) {
			ds.execute();
		}
	}
}