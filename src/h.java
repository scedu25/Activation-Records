import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Eric Du
 * IT 327-001
 */

public class h {
	
	static List<ActivationRecord> list = new ArrayList<ActivationRecord>();
	static int stackIndex = 0;
	
	public class ActivationRecord {
		private String functionName;
		private Map<Character, Integer> localVariables;
		private String returnAddress;
		private ActivationRecord previousRecord;
		private ActivationRecord nestingLink;
		private int result;
		
		public String getFunctionName() {
			return functionName;
		}
		public void setFunctionName(String functionName) {
			this.functionName = functionName;
		}
		public Map<Character, Integer> getLocalVariables() {
			return localVariables;
		}
		public void setLocalVariables(Map<Character, Integer> localVariables) {
			this.localVariables = new HashMap<Character, Integer>(localVariables);
		}
		public String getReturnAddress() {
			return returnAddress;
		}
		public void setReturnAddress(String returnAddress) {
			this.returnAddress = returnAddress;
		}
		public ActivationRecord getPreviousRecord() {
			return previousRecord;
		}
		public void setPreviousRecord(ActivationRecord previousRecord) {
			this.previousRecord = previousRecord;
		}
		public ActivationRecord getNestingLink() {
			return nestingLink;
		}
		public void setNestingLink(ActivationRecord nestingLink) {
			this.nestingLink = nestingLink;
		}
		public int getResult() {
			return result;
		}
		public void setResult(int result) {
			this.result = result;
		}
	
	}
	
	public int function_h(int x, int y) {
		int result = 0;
		ActivationRecord h = new ActivationRecord();
		h.setFunctionName("h");
		int z = x + 1;
		Map<Character, Integer> var = new HashMap<Character, Integer>();
		var.put('z', z);
		var.put('x', x);
		var.put('y', y);
		h.setLocalVariables(var);
		h.setReturnAddress(null);
		h.setPreviousRecord(null);
		h.setNestingLink(null);
		list.add(h);
		stackIndex++;
		
		if (x == 0)
			result = function_g(y, h);
		else
			result = z + function_g(function_h(x-1, y), null);
		
		return result;
	}
		
	int function_g(int w, ActivationRecord h) {
		int result = 0;
		int y = 0;
		ActivationRecord g = new ActivationRecord();
		g.setFunctionName("g");
		g.setReturnAddress(null);
		g.setPreviousRecord(list.get(stackIndex-1));
		if (h == null) {
			g.setNestingLink(g.getPreviousRecord());
			y = g.getNestingLink().getLocalVariables().get('y');
		}
		else if (h.getFunctionName().equals("f")) {
			g.setNestingLink(g.getPreviousRecord().getNestingLink().getNestingLink());
			y = g.getNestingLink().getLocalVariables().get('y');
		}
		else {
			g.setNestingLink(h);
			y = g.getNestingLink().getLocalVariables().get('y');
		}
		int z = y + 1;
		Map<Character, Integer> var = new HashMap<Character, Integer>();
		var.put('w', w);
		var.put('z', z);
		g.setLocalVariables(var);
		list.add(g);
		stackIndex++;
		
		if (w == 0) {
			if (h == null) {
				System.out.println(g.getNestingLink().getLocalVariables().size());
			}
			else if (h.getFunctionName() == "f") {
				System.out.println(g.getNestingLink().getLocalVariables().size());
			}
			else
				result = g.getNestingLink().getLocalVariables().get('x');
		}
		else {
			result = z + function_f(w-1, g);
		}
		list.remove(stackIndex-1);
		stackIndex--;
		
		return result;
	}
	
	int function_f(int x, ActivationRecord g) {
		int result = 0;
		ActivationRecord f = new ActivationRecord();
		f.setFunctionName("f");
		f.setReturnAddress(null);
		f.setPreviousRecord(list.get(stackIndex-1));
		f.setNestingLink(g);
		Map<Character, Integer> var = new HashMap<Character, Integer>();
		var.put('x', x);
		f.setLocalVariables(var);
		list.add(f);
		stackIndex++;
		
		if (x == 0) {
			result = 0;
			list.remove(stackIndex-1);
			stackIndex--;
		}
		else {
			int z = g.getLocalVariables().get('z');
			int w = g.getLocalVariables().get('w');
			result = z + x + function_g(w-1, f);
		}
		list.remove(stackIndex-1);
		stackIndex--;
		
		return result;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		h test = new h();
		int result = test.function_h(x, y);
		System.out.println("h(" + x + "," + y + ")=" + result);
	}

}
