package task3;

public class Main {
    public static void main(String[] args) {

        MessageQueue messageQueue= new MessageQueue();
        Sender sender1 = new Sender(messageQueue, 'A' );
        Sender sender2 = new Sender(messageQueue, 'B' );
        Sender sender3 = new Sender(messageQueue, 'C' );
        Receiver receiver = new Receiver(messageQueue);
        Thread thread1=new Thread(sender1);
        Thread thread2= new Thread(sender2);
        Thread thread3=new Thread(sender3);
        Thread thread4 = new Thread(receiver);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}
