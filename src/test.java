
public class test {

	public static int a(int x, int y) {
		int result;
		result = x + y;
		if (y == 0) {
			return 0;
		}
		result += a(b(result), y-1);
		
		return result;
	}
	
	public static int b(int z) {
		int result;
		result = z*2;
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(a(1,2));
	}

}
