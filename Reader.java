public class Reader extends Thread{
    private Queue q;
    private String name;
    public Reader(Queue q, String n){
        this.q = q;
        name = n;
    }
    public void run(){
        for(;;){
            if(!q.isEmpty){
                System.out.println(q.delete());
            }
        }
    }
}
