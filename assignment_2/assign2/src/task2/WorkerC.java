package task2;

import java.util.concurrent.Semaphore;

public class WorkerC implements Runnable {

    private Semaphore semA;
    private Semaphore semB;
    private Semaphore semC;
    private Semaphore semD;

    public WorkerC(Semaphore semA, Semaphore semB, Semaphore semC, Semaphore semD) {
        this.semA = semA;
        this.semB = semB;
        this.semC = semC;
        this.semD = semD;
    }

    @Override
    public void run() {


        for (int i=0;i<5;i++){
            try {
                semC.acquire();//
                System.out.print("C");
                semD.release();
                semC.acquire();
                System.out.print("C");
                semA.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

