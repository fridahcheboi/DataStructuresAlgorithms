import java.util.Arrays;

class InsertionSort{
	public static void main(String[] args){
		int[] rawList = {100,91,82,73,64,55,46,37};
		System.out.print("Unsorted: ");
		displayList(rawList);
		
		int[] sorted = sortWithInsertion(rawList);
		System.out.print("Sorted: ");
		displayList(sorted);		
	}
	public static int[] sortWithInsertion(int[] rawList){
		int[] list = Arrays.copyOf(rawList, rawList.length);
		
		for(int j=1;j<list.length;j++){
			int key = list[j];
			
			int i = j-1;
			while (i>=0 && list[i]>key){
				list[i+1] = list[i];
				i = i-1;
			}
			list[i+1] = key;
		}
		return list;
	}
	
	public static void displayList(int[] list){
		for (int i=0;i<list.length;i++){
			System.out.print(list[i]+" ");
		}
		System.out.println();
	}
}