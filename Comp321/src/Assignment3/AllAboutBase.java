package Assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AllAboutBase {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int N  = sc.nextInt();
		sc.nextLine();//get rid of this line
		for(int i=0; i<N; i++){
			processExp(sc.nextLine());
		}
	}
	
	private static void processExp(String exp){
		String[] token = exp.split(" ");
		if(token.length > 4){
			String operant1 = token[0];
			String operator = token[1];
			String operant2 = token[2];
			String equalSign = token[3];
			String result = token[4];
			for(int i=getSmallestBasePossible(exp);i<=36; i++){
				Long o1 = Long.parseLong(operant1, i);
				Long o2 = Long.parseLong(operant2, i);
				Long r = Long.parseLong(result, i);
				if(operator.equals("+") && o1 + o2 == r){
					System.out.println(" "+i);
				}
				else if(operator.equals("-") && o1-o2 == r){
					
				}
				else if(operator.equals("*") && o1*o2 == r){
					
				}
				else if(operator.equals("/") && o1/o2 == r){
					
				}
			}
		}
	}
	
	/*
	 * Verified
	 */
	private static int getSmallestBasePossible(String exp){
		int max = 0;//the minimum is 42 so 0 is ok
		for(int i=0; i< exp.length(); i++){
			int cur = exp.charAt(i);
			if(cur == 61 || cur == 42 || cur == 43 || cur == 45 || cur == 47){ // Exclude all operators
				continue;
			}
			max = Math.max(cur,max);
		}
		if(max >= 48 && max <= 57){
			return max-47;
		}
		if(max >= 97 && max <= 122){
			return max-86;
		}
	return 0;
		
	}
}
