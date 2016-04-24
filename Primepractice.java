/*java -Xmx Primepractice*/
import java.util.*;
public class Primepractice{
    //sieve
    public static int sieve(int n ){
        boolean[] list = new boolean[n+1];
        /*tag*/
    
        //from where to search
        for(int i = 2; i <= n; i++){
    //find every i brother
         for(int j = 2;i* j <= n ; j++){
             list[i*j] = true;
         }
     }
        int count = 0;
        //catch our tag find if it is prime
        for(int i = n; i >= 2 ; i--){
            if(list[i] == false){
                count++;	
            }
         }	
        return count;
    }
    //test
    public static int test(int n ){
        int count = 0;
        //from 2~n who are prime
        for(int i = 2 ; i <= n ; i++){
            boolean isprime = true;
            //test is it really prime
            for(int j = 2; j < i; j++){
                if( i % j == 0){
                    isprime = false;
                    break;
                }    
            }
            if(isprime != false){
                count++;
            }
        }
        return count;
    }
   /* public static long p2(long n){
        int maxLen = 400000000;
        boolean[][] deleted = new boolean[(int)(n-1)/2][n/maxLen+1][maxLen];
        long counter = 1;
        for (long i = 3; i * i <= n; i+=2){
            if (!deleted[(int)(i/2/maxLen)][(int)(i/2%]{
                counter++
            
                for(long j = 3*i; j <=n ; j += 2*i ){
                    deleted[(int)(j/2)] = true;
                }
            }
        }
        return counter;
    }*/
    public static void main(String[] argv){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println(test(n));
        
        System.out.println(sieve(n));
    }
}
