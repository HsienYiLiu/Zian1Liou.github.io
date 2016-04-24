/*
* 103213040 
* ���b�N
*/
import java.util.Scanner;
public class PrimeHomework {
    //���ժk
    public static int testprime(int n) {
        int total = 0;
        boolean[] x = new boolean[n+1];
        //���լO�_�����F1�M�ۤv�H�~���]�� �p�G���N�⥦�]��true(�]��boolean��l�ȬOfalse)
        for(int i = 2; i <= n; i++) {
            for(int j = 2; j < i; j++){
                if(i % j == 0) {
                    x[i] = true;
                    break;
                }
            }
            //���Ofalse��total�N�[�@
            if(x[i] == false)
                total++;
        }
        return total;
    }
    //�R���k
    public static int sieveprime(int n) {
        boolean[] p = new boolean[n+1];
        //�q2~n�}�l���զ��S������
        for(int i = 2; i <= n; i++) {
            //��X���� �����ƴN�⥦�]��true(�]��boolean��l�ȬOfalse)
            for(int j = i + i; j <= n; j = j + i) {
                p[j] = true;
            }
        }
        //�p�⦳�X�ӽ��
        int total = 0;
        for(int k = n; k >= 2; k--) {
            if(p[k] == false) {
                total++;
            }
        }
        return total;
    }
    public static void main(String[] argv) {
        //��J�@�ӼƥN��o�ӼƦr���U���h�֭ӽ��
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        //�L�X�ϥδ��ժk�p��X����ƭӼƩM�ɶ�
        long teststart = System.currentTimeMillis();
        System.out.print("���ժk "+testprime(n)+" ");
        long teststop = System.currentTimeMillis();
        System.out.println((teststop-teststart)+"ms");
        //�L�X�ϥΧR���k�p��X����ƭӼƩM�ɶ�
        long sievestart = System.currentTimeMillis();
        System.out.print("�R���k "+sieveprime(n)+" ");
        long sievestop = System.currentTimeMillis();
        System.out.print((sievestop-sievestart)+"ms");
    }
}

