package Assignment1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class GuessDataStructure {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()){
            int amount = sc.nextInt();

            boolean isStack = true;
            boolean isQueue = true;
            boolean isPriorityQueue = true;
            Stack<Integer> stack = new Stack<>();
            List<Integer> queue = new ArrayList<>();
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            //process operations
            for(int i=0; i<amount; i++){
                int operation = sc.nextInt();
                int value = sc.nextInt();
                if(operation == 1){
                    if(isStack){
                        stack.push(value);
                    }
                    if(isQueue){
                        queue.add(value);
                    }
                    if(isPriorityQueue){
                        priorityQueue.add(value);
                    }
                }
                else if(operation == 2){
                    if(isStack){
                        isStack = (stack.isEmpty())? false: stack.pop() == value;
                    }
                    if(isQueue){
                        isQueue = (queue.size()>0)?queue.remove(0) == value: false;
                    }
                    if(isPriorityQueue){
                        isPriorityQueue = (priorityQueue.isEmpty())? false : priorityQueue.poll() == value;
                    }
                }
            }
            //process result
            if((isStack && isQueue) || (isStack && isPriorityQueue) || (isQueue && isPriorityQueue)){
                System.out.println("not sure");
            }
            else if(isStack){
                System.out.println("stack");
            }
            else if(isQueue){
                System.out.println("queue");
            }
            else if(isPriorityQueue){
                System.out.println("priority queue");
            }
            else{
                System.out.println("impossible");
            }
            
        }
    }
    
}