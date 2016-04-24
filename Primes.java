import java.util.Scanner;
public class Primes {
    public static synchronized boolean isPrime(int n) {
        for (int i = 2; i < n; i++)
            if (n % i == 0) 
                return false;
        return true;
    }
    public static synchronized int primes(int n) {
        int counter = 0;
        for (int i = 2; i <= n; i++)
            if (isPrime(i)) 
                counter++;
        return counter;
    }
    public static void main(String[] argv) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        long start = System.currentTimeMillis();
        long stop = System.currentTimeMillis();
        System.out.println(primes(n) );
    }
}
