package Other;

import java.util.ArrayList;
import java.util.List;

public class Practice {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		list.add(4);
		List<List<Integer>> subsets = getAllSubsetsWithSize(list,3);
		for(List<Integer> subset: subsets) {
			for(int i: subset) {
				System.out.print(i +" ");
			}
			System.out.println();
		}
	}
	
	private static List<List<Integer>> getAllSubsetsWithSize(List<Integer> inputSet, int length){
		List<List<Integer>> result = new ArrayList<>();
		if(length > inputSet.size() || length <= 0 || inputSet.size()==0) {
			return result;
		}
		if(length == 1) {
			for(int i: inputSet) {
				List<Integer> newList = new ArrayList<>();
				newList.add(i);
				result.add(newList);
			}
			return result;
		}
		int index = 0;
		for(int cur: inputSet) {
			List<Integer> setCopy = new ArrayList<>(inputSet);
			for(int i=0; i<=index; i++) {
				setCopy.remove(0);
			}
			
			List<List<Integer>> allSubsets = getAllSubsetsWithSize(setCopy, length-1);
			for(List<Integer> subset: allSubsets) {
				subset.add(cur);
				result.add(subset);
			}
			index++;
		}
		
		return result;
	}
}
