package Assignment3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Sofa {

	private static Map<Integer, Set<Integer>> styleToColorMap = new HashMap<>();
	private static Map<Integer, Set<Integer>> colorToStyleMap = new HashMap<>();

	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int m = sc.nextInt();
		for(int z=0; z<m; z++) {
			eval();
		}
	}
	
	private static void eval() {
		int n = sc.nextInt();
		//get maximum k value possible
		int maxK = (int) Math.floor(Math.sqrt(n));
		//get all styles and colors of Mazo sofas
		Set<Integer> styles = new HashSet<>();
		Set<Integer> colors = new HashSet<>();
		for(int i=0; i< n; i++) {
			int style = sc.nextInt();
			int color = sc.nextInt();
			styles.add(style);
			colors.add(color);
			if(!styleToColorMap.containsKey(style)) {
				styleToColorMap.put(style, new HashSet<>());
			}
			styleToColorMap.get(style).add(color);
			if(!colorToStyleMap.containsKey(color)) {
				colorToStyleMap.put(color, new HashSet<>());
			}
			colorToStyleMap.get(color).add(style);
		}
		//try from largest k
		for(int k = maxK; k>0 ; k--) {
			//get all possible styles and colors for current k
			Set<Integer> possibleStyles = new HashSet<>();
			for(int style: styles) {
				if(styleToColorMap.get(style).size() < k) {
					continue;
				}
				else {
					possibleStyles.add(style);
				}
			}

			Set<Integer> remainingColors = new HashSet<>();
			for(int color: colors) {
				Set<Integer> intersection = new HashSet<>(colorToStyleMap.get(color));
				intersection.retainAll(possibleStyles);//this gives the amount of links from this color to the possible styles
				if(intersection.size() < k) {
					continue;
				}
				else {
					remainingColors.add(color);	
				}
			}
			//go to next k if does not have sufficient amount
			if(remainingColors.size() < k) {
				continue;
			}
			
			Set<Integer> remainingStyles = new HashSet<>();
			for(int style: possibleStyles) {
				Set<Integer> intersection = new HashSet<>(styleToColorMap.get(style));
				intersection.retainAll(remainingColors);
				if(intersection.size() >= k) {
					remainingStyles.add(style);
				}
			}
			
			if(remainingStyles.size() < k) {
				continue;
			}
			//Now the set is much smaller, do brute force
			//get all subset of size k of each set
			List<List<Integer>> allStyleSubsetsOfSizeK = getAllSubsetsWithSize(new ArrayList<>(remainingStyles),k);
			List<List<Integer>> allColorSubsetsOfSizeK = getAllSubsetsWithSize(new ArrayList<>(remainingColors),k);
			//check for validation
			for(List<Integer> sSub: allStyleSubsetsOfSizeK) {
				for(List<Integer> cSub: allColorSubsetsOfSizeK) {
					if(isValid(sSub, cSub)) {
						System.out.println(k);
						return;
					}
				}
			}
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

	public static boolean isValid(List<Integer> sSub, List<Integer> cSub) {
		if(sSub.size() != cSub.size()) {
			return false;
		}
		//check existence of all links
		for(int style: sSub) {
			Set<Integer> colors = styleToColorMap.get(style);
			if(colors == null || !colors.containsAll(cSub)) {
				return false;
			}
		}
		
		for(int color: cSub) {
			Set<Integer> styles = colorToStyleMap.get(color);
			if(styles == null || !styles.containsAll(sSub)) {
				return false;
			}
		}
		
		return true;
	}
}
