package ca.utoronto.utm.floatingpoint;

import java.util.ArrayList;

public class q2 {
	public static void main(String[] args) {
		q2 p = new q2();
		System.out.println(p.solve711());
	}
	public String solve711() {
		ArrayList<Float> array = new ArrayList<Float>();
		String s = "";
		for(float a=79; a<711; a+=79) { 
			float B = 711-a; 
			for(float b = 1; b < B; b++) { 
				float C = B-b; 
				for(float c=1; c < C; c++) { 
					float d = C-c; 
					float sum = a+b+c+d;
					if((a*b*c*d == 711000000) && sum==711) {
						a=a/100;
						b=b/100;
						c=c/100;
						d=d/100;
						if (array.isEmpty()) {
							array.add(a);
							array.add(b);
							array.add(c);
							array.add(d);
						} else {
							if (array.contains(a) && array.contains(b) && array.contains(c) && array.contains(d)) {
								
							} else {
								array.add(a);
								array.add(b);
								array.add(c);
								array.add(d);
							}
						}
					} 
				} 
			} 
		}
		for (int n =0; n<array.size(); n+=4) {
			s = ("a"+n+"= "+array.get(n)+" b"+n+"= "+array.get(n+1)+" c"+n+"= "+array.get(n+2)+" d"+n+"= "+array.get(n+3));
		}
		return s;
	}
}
