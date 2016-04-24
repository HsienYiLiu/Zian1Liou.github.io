import java.util.Scanner;
    public class Prime {
    public static int findMaxPrimeOverSieve (int n) {
        boolean[] list = new boolean[n+1];

        for (int i = 2; i <= n ; i++) {
            for (int j = i + i; j <= n; j = i+j) {
                list[j] = true;
            }
        }
        int totalPrime = 0;
        for (int i = n; i >= 2; i--) {
            if (list[i] == false) {
                totalPrime ++;
            }
        }
        return totalPrime;
    }

    public static int findMaxPrimeOverTest(int n){
        int totalPrime = 0;
        for (int i = 2; i <= n; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                totalPrime ++;
            }
        }
        return totalPrime;
    }
    public static void main(String[] argv){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        long start, end;

        System.out.print("測試法 ");
        start = System.currentTimeMillis();
        System.out.print(findMaxPrimeOverTest(n) + " ");
        end = System.currentTimeMillis();
        System.out.println(end - start);
						    
        System.out.print("刪除法 ");
        start = System.currentTimeMillis();
        System.out.print(findMaxPrimeOverSieve(n) + " ");
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
