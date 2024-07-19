import java.util.Random;

class MergeSort{
	public static void main(String[] args){
		
		int[] A = {23,5,112,9849,2030,201,4,-12,23,593,330,21,1913,12,34,0,57,31};
		System.out.println("Unsorted: ");
		displayList(A);
		MergeSort(A, 0,A.length-1);
		System.out.println("Sorted: ");
		displayList(A);
		
		System.out.println("\nAttempt to calculate time complexity\n\n");
		
		 // Generate arrays of different sizes
        int[] sizes = {100, 500, 1000, 5000, 10000}; // Example sizes
        Random random = new Random();

        for (int size : sizes) {
            int[] B = new int[size];
            for (int i = 0; i < size; i++) {
                B[i] = random.nextInt(10000); // Random values between 0 and 9999
            }

            long startTime = System.nanoTime();
            MergeSort(B, 0, B.length - 1);
            long endTime = System.nanoTime();
            
            long duration = endTime - startTime; // Time taken in nanoseconds

            System.out.println("Array size: " + size);
            System.out.println("Time taken: " + duration + " nanoseconds");
            System.out.println();
        }
		
	}
	public static void displayList(int[] list){
		for (int i=0;i<list.length;i++){
			System.out.print(list[i]+" ");
		}
		System.out.println();
	}
	public static void MergeSort(int[] A, int low,int high){
		if (low<high){
			int mid = (high+low)/2;
			MergeSort(A, low, mid);
			MergeSort(A, mid+1,high);
			Merge(A, low, mid, high);
		}
	}
	public static void Merge(int[] A, int low, int mid, int high){
		int[] B = new int[high-low+1];
		int h = low;
		int j = mid+1;
		int i = 0;
		
		while (h<=mid && j<=high){
			if (A[h]<=A[j]){
				B[i] = A[h];
				h++;
			}
			else{
				B[i] = A[j];
				j++;
			}
			i++;
		}
		
		if (h>mid){
			for (int k=j;k<=high;k++){
				B[i] = A[k];
				i++;
			}
		}
		else{
			for (int k=h;k<=mid;k++){
				B[i] = A[k];
				i++;
			}
		}
		
		int f = 0;
		for (int k=low;k<=high;k++){
			A[k] = B[f];
			f++;
		}
	}
}