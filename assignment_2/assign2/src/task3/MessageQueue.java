package task3;

import java.util.concurrent.Semaphore;

public class MessageQueue implements  IMessageQueue{
    private static final int MAX_SIZE=5;
    private char [] buffer;
    private  Semaphore semA = new Semaphore(0);
    private  Semaphore semB = new Semaphore(MAX_SIZE);
    private  Semaphore semBlockSend = new Semaphore(1);
    private  Semaphore semBlockRecv = new Semaphore(1);
    private int start=0;
    private int finish=0;


    // finish,the position is being moved
    private int getNext( int p){
        p++;
        if(p==MAX_SIZE){
            p=0;

        }
        return p;

    }

    public MessageQueue() {
        buffer = new char[MAX_SIZE+1];
    }

    public  boolean isEmpty(){
        if(start==finish){
            return  true;
        }
        return false;
    }

    @Override
    // sending
    public boolean Send(char msg) {
        if(semB.tryAcquire()){
            try {
                semBlockSend.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buffer [finish] = msg;
            finish=getNext(finish);
            semBlockSend.release();
            semA.release();
            return true;
        }
        return false;
    }

    @Override
    public char Recv() {
        try {
            semA.acquire();
            semBlockRecv.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        char c = buffer[start];
        start=getNext(start);
        semBlockRecv.release();
        semB.release();
        return c;
    }
}
