package com.example.practice;

public class TwoThreadsOddEven {

	// Starting counter
	static int counter = 1;

	static int N;

	// Function to print odd numbers
	public void printOddNumber() {
		synchronized (this) {
			while (counter < N) {
				if (counter % 2 == 0) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.print(counter + " ");
				counter++;
				notify();
			}
		}
	}

	public void printEvenNumber() {
		synchronized (this) {
			while (counter < N) {
				if (counter % 2 == 1) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.print(counter + " ");
				counter++;
				notify();
			}
		}
	}

	public static void main(String[] args) {
		N = 100;
		TwoThreadsOddEven obj = new TwoThreadsOddEven();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				obj.printEvenNumber();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				obj.printOddNumber();
			}
		});

		t1.start();
		t2.start();
	}
}
