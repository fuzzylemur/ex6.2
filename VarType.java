package oop.ex6.main;

public enum VarType {
	INT ("int"),
	STRING ("string"),
	DOUBLE ("double"),
	CHAR ("char"),
	BOOLEAN ("boolean");

	private final String stringRep;

	VarType(String stringRep) {
		this.stringRep = stringRep;
	}

	static VarType getType(String str) {

		for (VarType type : VarType.values()){
			if (str.equals(type.stringRep))
				return type;
		}
		return null;
	}

	static boolean equals(VarType var1, VarType var2){

		if (var1 == var2)
			return true;

		else if (var1 == DOUBLE && var2 == INT)
			return true;

		else if (var1 == BOOLEAN && (var2 == INT || var2 == DOUBLE))
			return true;

		return false;
	}
}