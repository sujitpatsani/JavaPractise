package interview_simple;

public class PrimeNo {
	public int a, count = 0;

	public void primeNo(int x) {
		a = x / 2;

		for (int i = a; i > 1; i--) {
			int rem = x % i;
			System.out.println(rem);
			if (rem == 0) {
				System.out.println("prime no ");
				break;
			} 

		}
	}

	public static void main(String[] args) {

		PrimeNo pm = new PrimeNo();
		pm.primeNo(-17);

	}

}
