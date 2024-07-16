import java.util.Arrays;

class SelectionSort{
	public static void main(String[] args){
		int[] rawList = {100,91,82,73,64,55,46,37};
		System.out.print("Unsorted: ");
		displayList(rawList);
		
		int[] sorted = sortWithSelection(rawList);
		System.out.print("Sorted: ");
		displayList(sorted);
	}
	public static int[] sortWithSelection(int[] rawList){
		int[] list = Arrays.copyOf(rawList, rawList.length);
		
		for(int i=0;i<list.length;i++){
			int min = i;
			
			for (int j=i+1;j<list.length;j++){
				if(list[j]<list[min]){
					min = j;
				}
			}
			if (min != i){
				int temp = list[i];
				list[i] = list[min];
				list[min] = temp;
			}
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