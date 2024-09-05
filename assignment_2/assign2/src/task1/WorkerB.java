package task1;

import java.util.concurrent.Semaphore;

public class WorkerB implements Runnable{

	private Semaphore semA;
	private Semaphore semB;

	public WorkerB(Semaphore semA, Semaphore semB) {
		this.semA = semA;
		this.semB = semB;
	}

	@Override
	public void run() {
		
		for (int i=0;i<10;i++){
			try {
				semB.acquire();//
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("B");

			semA.release();

		}

		}
		
	}


