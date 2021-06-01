import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {
	public static void main(String[] args) throws IOException {
		int t, op = 0;
		int n;
		int[] arr;
		double total = 0;
		
		Long  start = 0L, end = 0L;
		
		Scanner teclado = new Scanner(System.in);
		Writer wr;
		
		MergeSort ms = new MergeSort();
		SortChecker sch;
		
		System.out.println("1) Run Single threaded merge sort");
		System.out.println("2) Run multithreaded merge sort");
		System.out.println("3) Exit");
		
		try {
			op = teclado.nextInt();
			
			switch(op) {
				case 1:
					System.out.println("How many integers: ");
					n = teclado.nextInt();
					
					if(n<=0 || n>100000000) {
						System.out.println("Integers amount should be in the range 1 - 100,000,000");
						break;
					}
					
					arr = new Random().ints(n).toArray();
					
					start = System.nanoTime();
					ms.sort(arr, 0, arr.length-1);
					end = System.nanoTime();
					
					total =((double)end-start)/1000000;
					
					wr = new FileWriter("sorted.txt");
					
					for(int i=0; i<n; i++) {
						wr.write(arr[i] + " ");
					}
					
					wr.close();
					
					sch = new SortChecker(n);
					sch.check();
					
					System.out.println( n+" integers sorted in: "+total+" ms" );
				break;
				
				case 2:
					System.out.println("Physical available processors: " + Runtime.getRuntime().availableProcessors());
					System.out.println("How many integers: ");
					n = teclado.nextInt();
					
					if(n<=0 || n>100000000) {
						System.out.println("Integers amount should be in the range 1 - 100,000,000");
						break;
					}
					
					System.out.println("How many threads: ");
					t = teclado.nextInt();
					
					if(t<=0 || t>50) {
						System.out.println("Threads amount should be in the range 1 - 50");
						break;
					}
			        
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
					
					wr = new FileWriter("sorted.txt");
					
					for(int i=0; i<n; i++) {
						wr.write(arr[i] + " ");
					}
					
					wr.close();
					
					sch = new SortChecker(n);
					sch.check();

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
		}catch(InputMismatchException ex) {
			System.out.println("Invalid string in argument. Integer expected!");
		}
		
		teclado.close();
	}
}
