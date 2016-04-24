/*
* 103213040 
* 蔡淨淇
*/
import java.util.Scanner;
public class PrimeHomework {
    //測試法
    public static int testprime(int n) {
        int total = 0;
        boolean[] x = new boolean[n+1];
        //測試是否有除了1和自己以外的因數 如果有就把它設為true(因為boolean初始值是false)
        for(int i = 2; i <= n; i++) {
            for(int j = 2; j < i; j++){
                if(i % j == 0) {
                    x[i] = true;
                    break;
                }
            }
            //當它是false時total就加一
            if(x[i] == false)
                total++;
        }
        return total;
    }
    //刪除法
    public static int sieveprime(int n) {
        boolean[] p = new boolean[n+1];
        //從2~n開始測試有沒有倍數
        for(int i = 2; i <= n; i++) {
            //找出倍數 有倍數就把它設成true(因為boolean初始值是false)
            for(int j = i + i; j <= n; j = j + i) {
                p[j] = true;
            }
        }
        //計算有幾個質數
        int total = 0;
        for(int k = n; k >= 2; k--) {
            if(p[k] == false) {
                total++;
            }
        }
        return total;
    }
    public static void main(String[] argv) {
        //輸入一個數代表這個數字底下有多少個質數
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        //印出使用測試法計算出的質數個數和時間
        long teststart = System.currentTimeMillis();
        System.out.print("測試法 "+testprime(n)+" ");
        long teststop = System.currentTimeMillis();
        System.out.println((teststop-teststart)+"ms");
        //印出使用刪除法計算出的質數個數和時間
        long sievestart = System.currentTimeMillis();
        System.out.print("刪除法 "+sieveprime(n)+" ");
        long sievestop = System.currentTimeMillis();
        System.out.print((sievestop-sievestart)+"ms");
    }
}

