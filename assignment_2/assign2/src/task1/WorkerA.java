package task1;

import java.util.concurrent.Semaphore;

public class WorkerA implements Runnable{

 private Semaphore semA;
 private Semaphore semB;


	public WorkerA(Semaphore semA, Semaphore semB) {
		this.semA = semA;
		this.semB = semB;
	}

	public WorkerA(Semaphore semA, Semaphore semB, Semaphore semC, Semaphore semD) {
	}

	@Override
	public void run() {

		for (int i=0;i<10;i++) {
			try {
				semA.acquire();//
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.print("A");
			semB.release();// release

		}
	}

}
