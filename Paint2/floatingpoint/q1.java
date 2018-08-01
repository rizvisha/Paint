package ca.utoronto.utm.floatingpoint;

public class q1 {
	public static void main(String[] args) {
		q1 p = new q1();
		System.out.println(p.solve711());
	}
	
	/**
	 * The number 0.01 can not be accurately represented in IEEE-754 format, which is used by computer languages. 0.01 can be represented by 1 * 10^-2 in base 10,
	 * however, a similar representation is not possible in base 2, i.e. X * 2^(-Y). No integer pair of X and Y give exactly 0.01. This means adding or multiplying
	 * 0.01 would accumulate the error in the result. This would mean we could 7.11 as a result when in fact a,b,c,d were not exactly 0.01 or we could not get
	 * 7.11 even if a solution exists.
	 * @return
	 */
	public String solve711() {
		float a, b, c, d;
		for (a = 0.00f; a < 7.11f; a = a + .01f) {
			for (b = 0.00f; b < 7.11f; b = b + .01f) {
				for (c = 0.00f; c < 7.11f; c = c + .01f) {
					for (d = 0.00f; d < 7.11f; d = d + .01f) {
						if (a * b * c * d == 7.11f && a + b + c + d == 7.11f) {
							return (a + " " + b + " " + c + " " + d);
						}
					}
				}
			}
		}
		return "";
	}
}
