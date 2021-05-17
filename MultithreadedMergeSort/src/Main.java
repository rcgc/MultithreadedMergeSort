import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {
	public static void main(String[] args) throws IOException {
		long start=0L, end=0L;
		Long n;
		
		int t, op = 0;
		int[] arr;
		
		double total = 0;
		
		Scanner teclado = new Scanner(System.in);
		MergeSort ms = new MergeSort();
		
		System.out.println("1) Run Single threaded merge sort");
		System.out.println("2) Run multithreaded merge sort");
		System.out.println("3) Exit");
		op = teclado.nextInt();
		
		switch(op) {
			case 1:
				System.out.println("How many integers: ");
				n = teclado.nextLong();
				
				arr = new Random().ints(n).toArray();
				
				start = System.nanoTime();
				ms.sort(arr, 0, arr.length-1);
				end = System.nanoTime();
				
				total =((double)end-start)/1000000;
				
				/*
				Writer wr = new FileWriter("sorted.txt");
				
				for(int i=0; i<n; i++) {
					wr.write(arr[i] + " ");
				}
				
				wr.close();
				*/
				
				System.out.println( n+" integers sorted in: "+total+" ms" );
				
			break;
			case 2:
				System.out.println("Physical available processors: " + Runtime.getRuntime().availableProcessors());
				System.out.println("How many integers: ");
				n = teclado.nextLong();
				
				System.out.println("How many threads: ");
				t = teclado.nextInt();
		        
				arr = new Random().ints(n).toArray();
				
				start = System.nanoTime();
		        final ForkJoinPool forkJoinPool = new ForkJoinPool(t);
				try {
					forkJoinPool.invoke(new ParallelMergeSort(arr, 0, arr.length - 1));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				end = System.nanoTime();
				
				total =((double)end-start)/1000000;
				
				/*
				Writer wr = new FileWriter("sorted.txt");
				
				for(int i=0; i<n; i++) {
					wr.write(arr[i] + " ");
				}
				
				wr.close();
				*/
				
				System.out.println( n+" integers sorted in: "+total+" ms" );
				System.out.println("with "+ t +" threads");
				
			break;
			case 3:
				System.out.println("Goodbye");
			break;
			default:
				System.out.println("Not a valid option");
			break;
		}
		teclado.close();
	}
}
