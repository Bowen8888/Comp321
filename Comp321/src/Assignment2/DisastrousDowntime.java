package Assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DisastrousDowntime {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		if(sc.hasNextInt()){
			//get params
			int n = sc.nextInt();
			double k = sc.nextInt();
			//initialize needed data structure and variables
			List<Integer> queue = new ArrayList<Integer>();
			int maxProcessAmountPerSecond = 1;
			for(int i=0;i<n;i++){
				int nextProcessArrivalTime = sc.nextInt();
				queue.add(nextProcessArrivalTime);
				//keep all process within 1 sec in the queue
				while(i>0 && queue.get(queue.size()-1)-queue.get(0) > 999){
					queue.remove(0);
				}
				//record max within 1 sec
				maxProcessAmountPerSecond = Math.max(maxProcessAmountPerSecond, queue.size());
			}
			System.out.println((int)Math.ceil(maxProcessAmountPerSecond/k));
		}
	}
}
