package Assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VegetableBaskets {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); //Number of numbers in the next raw
		List<Integer> initialSet = new ArrayList<>();
		for(int i = 0; i<n;i++){
			initialSet.add(sc.nextInt());
		}
		int total = 0;
		for(List<Integer> subset: powerSet(initialSet)){
			int sum = sum(subset);
			if(sum>=200){//count only if heavy enough
				total+= sum;
			}
		}
		System.out.println(total);
	}
	
	/*
	 * Get power set of input set
	 */
	private static List<List<Integer>> powerSet(List<Integer> inputSet){
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		//return set of empty set when empty
		if(inputSet.isEmpty()){
			result.add(new ArrayList<>());
			return result;
		}
		List<Integer> copy = new ArrayList<Integer>(inputSet);
		int first = copy.remove(0);
		//get subset with and without each element recursively gives 2^n sets
		for(List<Integer> subset: powerSet(copy)){
			List<Integer> subsetWithFirst = new ArrayList<>();
			subsetWithFirst.add(first);
			subsetWithFirst.addAll(subset);
			result.add(subset);
			result.add(subsetWithFirst);
		}
		return result;
	}
	
	/*
	 * Sum up the weights
	 */
	private static int sum(List<Integer> inputSet){
		int total = 0;
		for(int i: inputSet){
			total+=i;
		}
		return total;
	}
}
