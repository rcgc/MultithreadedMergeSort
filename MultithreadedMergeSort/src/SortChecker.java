import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SortChecker {
	private int[] arr;
	private int n;
	
	private boolean flag = true;
	
	public SortChecker(int n) {
		this.n = n;
		arr = new int[n]; 
	}
	
	public void check() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("sorted.txt"));
		int i=0;
		
		while(scanner.hasNextInt()){
		     arr[i] = scanner.nextInt();
		     //System.out.print(arr[i]+" ");
		     i++;
		}
		
		for(i=1; i<n; i++) {
			if(arr[i]<arr[i-1]) {
				flag = false;
				break;
			}
		}
		
		if(flag == true) {
			System.out.println("The "+n+" integers into sorted.txt were sorted succesfully!\n");
			
		}else {
			System.out.println("The "+n+" integers into sorted.txt were not sorted ascending\n");

		}
	}
}
