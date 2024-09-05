package task1;

import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Semaphore semA = new Semaphore(1);
		Semaphore semB = new Semaphore(0);
		
		WorkerA wa = new WorkerA(semA,semB);
		Thread ta = new Thread(wa);
		ta.start();
		
		WorkerB wb = new WorkerB(semA,semB);
		Thread tb = new Thread(wb);// create thread
		tb.start();
		
		ta.join();
		tb.join();

		System.out.println();

	}
}
