package task2;

import java.util.concurrent.Semaphore;

public class WorkerA implements Runnable{

 private Semaphore semA;
 private Semaphore semB;
 private Semaphore semC;
 private Semaphore semD;

	public WorkerA(Semaphore semA, Semaphore semB,Semaphore semC, Semaphore semD) {
		this.semA = semA;
		this.semB = semB;
		this.semC = semC;
		this.semD = semD;
	}

	@Override
	public void run() {

		for (int i=0;i<5;i++) {
			try {
				semA.acquire();
				System.out.print("A");
				semB.release();
				semA.acquire();
				System.out.print("A");
				semC.release();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
