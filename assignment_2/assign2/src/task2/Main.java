package task2;



import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Semaphore semA = new Semaphore(1);
		Semaphore semB = new Semaphore(0);
		Semaphore semC = new Semaphore(0);
		Semaphore semD = new Semaphore(0);
		
		WorkerA wa = new WorkerA(semA,semB,semC,semD);
		Thread ta = new Thread(wa);
		ta.start();
		
		WorkerB wb = new WorkerB(semA,semB,semC,semD);
		Thread tb = new Thread(wb);
		tb.start();

		WorkerC wc = new WorkerC(semA,semB,semC,semD);
		Thread tc = new Thread(wc);
		tc.start();

		WorkerD wd = new WorkerD(semA,semB,semC,semD);
		Thread td = new Thread(wd);
		td.start();

		ta.join();
		tb.join();
		tc.join();
		td.join();

		System.out.println();

	}
}
