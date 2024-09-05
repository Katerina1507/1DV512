package task3;

public class Receiver implements Runnable{

    private MessageQueue queue;


    public Receiver(MessageQueue queue) {
        this.queue=queue;

    }

    @Override
    public void run() {
        while (true){
            char c =queue.Recv();
            System.out.print(c);
        }

    }
}
