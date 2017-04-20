package dataStructure;

public class SearchInsertDeleteUnsortedArray {

	public static int search(int[] arrayForSearch, int numberToSearch) {
		for (int i = 0; i < arrayForSearch.length; i++) {
			if (arrayForSearch[i] == numberToSearch) {
				return i;
			}

		}
		return -1;

	}

	public static void insert(int[] arrayForInsert, int numberToInsert) {
		int lastPosition = arrayForInsert.length;
		arrayForInsert[lastPosition - 1] = numberToInsert;
		System.out.println(arrayForInsert[lastPosition - 1]);

	}

	public static void delete(int [] arrayToDelete,int numberTodelete){
		int pos=search(arrayToDelete,numberTodelete);
		for(int i=pos;i<arrayToDelete.length-1;i++){
			arrayToDelete[i]=arrayToDelete[i+1];
						
			}
		for (int ii : arrayToDelete) {
			System.out.println(ii);
		}
		
		
	}

	public static void main(String[] args) {

		int[] a = { 5, 9, 7, 8, 9, 4, 76, 34 };

		int numberToSearch = 8;
		int x = search(a, numberToSearch);
		if (x != -1) {
			System.out.println("search element found and positionis:" + x);
		} else
			System.out.println("not found");
		int numberToInsert = 9;
		int b[] = new int[10];
		insert(b, numberToInsert);
		insert(b,9);
		delete(a,9);
	}

}
