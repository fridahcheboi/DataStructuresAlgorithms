import java.util.Arrays;

class BubbleSort{
	public static void main(String[] args){
		int[] rawList = {100,60,20,-1,0,105,-2};

		System.out.print("Unsorted: ");
		displayList(rawList);
		
		int[] sorted = sortWithBubble(rawList);
		System.out.print("Sorted: ");
		displayList(sorted);
	}
	public static int[] sortWithBubble(int[] rawList){
		boolean swapped = true;
		int[] list = Arrays.copyOf(rawList, rawList.length);
		
		while (swapped == true){
			swapped = false;
			for (int i=0;i<list.length-1;i++){
				if (list[i]>list[i+1]){
					int temp = list[i];
					list[i] = list[i+1];
					list[i+1] = temp;
					swapped = true;
				}
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