package task3;

public class Sender implements Runnable{
    private MessageQueue queue;
    private char message;

    public Sender(MessageQueue queue, char message) {
        this.queue=queue;
        this.message=message;
    }

    @Override
    public void run() {
        while (true){
            queue.Send(message);
        }

    }
}
