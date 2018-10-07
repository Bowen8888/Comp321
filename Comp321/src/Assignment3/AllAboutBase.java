package Assignment3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AllAboutBase {
    private final static BigInteger UpperBound = BigInteger.valueOf(2).pow(32).subtract(BigInteger.ONE);
    private final static BigInteger LowerBound = BigInteger.ONE;
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		while (N-- > 0) {
			String operant1 = sc.next();
			String operator = sc.next();
			String operant2 = sc.next();
			sc.next();
			String result = sc.next();
			
			processExp(operant1,operator,operant2,result);
		}
	}
	
	private static boolean containsOneOnly(String exp) {
		String newExp = exp.replaceAll("1", "");
		return newExp.isEmpty();
	}
	
	private static void processExp(String operant1, String operator,String operant2,String result){
		boolean found = false;
		for(int i=1;i<=36; i++){
			BigInteger o1, o2, r;
			if(i==1) {
				if(!containsOneOnly(operant1) || !containsOneOnly(operant2) || !containsOneOnly(result)) {
					continue;
				}
				o1 = BigInteger.valueOf(operant1.length());
				o2 = BigInteger.valueOf(operant2.length());
				r = BigInteger.valueOf(result.length());
			}
			else {
				if(i < getSmallestBasePossible(operant1,operant2,result)) {
					continue;
				}
				o1 = new BigInteger(operant1,i);
				o2 = new BigInteger(operant2,i);
				r = new BigInteger(result,i);
			}
				
			if(!isValid(o1) || !isValid(o2) || !isValid(r)) {
				continue;
			}
			
			if( (operator.equals("+") && o1.add(o2).compareTo(r) == 0) 
					|| (operator.equals("-") && o1.subtract(o2).compareTo(r) == 0)
					||(operator.equals("*") && o1.multiply(o2).compareTo(r) == 0)
					||(operator.equals("/") && o1.divideAndRemainder(o2)[1].compareTo(BigInteger.ZERO)==0
					&& o1.divideAndRemainder(o2)[0].compareTo(r) == 0)){
				printBase(i);
				found = true;
			}
		}
		if(!found) {
			System.out.println("invalid");
		}
		else {
			System.out.println();
		}
	}
	
	private static boolean isValid(BigInteger number) {
		return number.compareTo(UpperBound) <= 0 && number.compareTo(LowerBound) >= 0;
	}
	
	private static void printBase(int base) {
		if(base < 10) {
			System.out.print(base);
		}
		else if(base == 36) {
			System.out.println(0);
		}
		else {
			System.out.print((char)(base+87));
		}
	}
	
	/*
	 * Verified
	 */
	private static int getSmallestBasePossible(String operant1, String operant2,String result){
		String exp = operant1 + operant2 + result;
		int max = 0;//the minimum is 42 so 0 is ok
		for(int i=0; i< exp.length(); i++){
			int cur = exp.charAt(i);
			if(cur == 61 || cur == 42 || cur == 43 || cur == 45 || cur == 47){ // Exclude all operators
				continue;
			}
			max = Math.max(cur,max);
		}
		if(max<49) {
			return 1;
		}
		if(max >= 49 && max <= 57){
			return max-47;
		}
		if(max >= 97 && max <= 122){
			return max-86;
		}
	return 100;
		
	}
}
