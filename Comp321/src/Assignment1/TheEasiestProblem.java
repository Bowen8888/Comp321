package Assignment1;
import java.util.Scanner;

public class TheEasiestProblem {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()){
            int value = sc.nextInt();
            if(value == 0){
                break;
            }
            int goal = sum(value);
            int k=11;
            while(sum(k*value) != goal){
                k++;
            }
            System.out.println(k);
        }
        
        
    }
    /*
     * sum digits
     */
    public static int sum(int value){
        int total = 0;
        
        while(value > 0){
            total += value % 10;
            value = value/10;
        }
        
        return total;
    }
}