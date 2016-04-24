
public class Queue{
    private int[] data = new int[100];
    int head, tail, size;
    public synchronized void add (int x){
        //must have site
        while(size == data.length){
            try{
                wait();
            }catch(Exception error){

            }
        }
        data[tail++] = x;
        size++;
        //use to find where is it
        tail = tail % data.length ;
        if(size ==1){
            notifyAll();
        }
    }
    public synchronized int remove(){
        while (size == 0){
            try{
                //sleep until other thread notify me
                wait();
            }catch(Exception error){

            }
        }
        int tmp = data[head++];
        size--
        head = head % data.length;
        //if any thread needs me to notify then?
        if(size == data.length -1){
            notifyAll();
        }
        return tmp;
    }
    /*public boolean isEmpty(){
        return size;
    }*/
}
