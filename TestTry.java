public class TestTry{
    public static void main(String[] argv){
        int[] data = new int[100];
        try{
            for(int i = 1; i <= data.length; i++)
                data[i] = i;
        } catch(ArrayIndexOutOfBoundsException err){
            System.out.println("over border for array error");
            err.printStackTrace();
        }  catch(NullPointerException np){
            System.out.println("DONT POINT ME");
        } catch(Exception er){
            System.out.println("WRONG");
        } finally{
            System.out.println("finally must be run");
        }
    }
}
