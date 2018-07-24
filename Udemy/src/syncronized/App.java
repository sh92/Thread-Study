package syncronized;

public class App {
	private int count;

	public synchronized void increment() {
		count++;
	}

	public static void main(String[] args) {
		App app = new App();
		app.doWork();
	}

	private void doWork() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10000; i++)
					increment();
			}

		});

		t1.start();

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10000; i++)
					increment();
			}

		});

		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Count is " + count);

	}

}
