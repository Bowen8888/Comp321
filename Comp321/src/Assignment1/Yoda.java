package Assignment1;

import java.util.Scanner;

public class Yoda {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int amount = 0;
        int v1 = 0;
        int v2 = 0;
        while(sc.hasNextInt()){
            int value = sc.nextInt();
            amount++;
            if(amount == 1){
                v1 = value;
            }
            else if(amount == 2){
                v2 = value;
                break;
            }
        }

        process(v1,v2);
    
    }
    
    private static void process(int v1, int v2){
        String r1 = "";
        String r2 = "";
        while(v1>0 && v2>0){
            int i1 = v1 % 10;
            int i2 = v2 % 10;
            if(i1 == i2){
                r1 = i1 + r1;
                r2 = i2 + r2;
            }
            else if(i1 > i2){
                r1 = i1 + r1;
            }
            else{
                r2 = i2 + r2;
            }
            v1 /= 10;
            v2 /= 10;
        }
        
        if(v1 >0){
            r1 = v1 + r1;
        }
        if (v2 >0){
            r2 = v2 + r2;
        }
        if(r1 == ""){
            System.out.println("YODA");
        }
        else{
            System.out.println(Integer.parseInt(r1));
        }
        if(r2 == ""){
            System.out.println("YODA");
        }
        else{
            System.out.println(Integer.parseInt(r2));
        }
        
    }
}