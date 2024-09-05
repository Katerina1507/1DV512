package task2;

import java.util.concurrent.Semaphore;

public class WorkerD implements Runnable {

    private Semaphore semA;
    private Semaphore semB;
    private Semaphore semC;
    private Semaphore semD;

    public WorkerD(Semaphore semA, Semaphore semB, Semaphore semC, Semaphore semD) {
        this.semA = semA;
        this.semB = semB;
        this.semC = semC;
        this.semD = semD;
    }

    @Override
    public void run() {
        for (int i=0;i<5;i++){
            try {
                semD.acquire();//
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("D");
            semC.release();
        }
    }
}
