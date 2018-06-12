import java.util.HashMap;

public class ScopeVars {

	private HashMap<String,Variable> variables;

	public ScopeVars(){}

	public void add(Variable var) throws Exception{
		if (variables.containsKey(var.getName()))
			throw Exception;
		else
			variables.put(var.getName(), var);
	}

	public int contains(Variable var){
		Variable ans = variables.get(var.getName);
		if ((ans != null) &&(ans.getType() == var.getType())){
			if (ans.isFinal() == false)
				return 1;
			else
				return 0;
		} else
			return -1;
	}
}
