
public class Task1 {

	public static void main(String[] args) {
		int[] x = { 5, 6, 9, 8, 0, 9 };
		int[] y = { 9, 8, 6, 3, 5 };
		int[] z = sumArrays(x, y);
		displayArray(z);
	}

	public static double average(int[] array) {
		double sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum / array.length;
	}

	public static int[] sumArrays(int[] arrayIntOne, int[] arrayIntTwo) {
		int[] target = new int[arrayIntOne.length + arrayIntTwo.length];
		for (int i = 0; i < target.length; i++) {
			if (i < arrayIntOne.length)
				target[i] = arrayIntOne[i];
			else
				target[i] = arrayIntTwo[i - arrayIntOne.length];
		}
		return target;

	}

	public static void displayArray(int[] arrayInt) {
		for (int i = 0; i < arrayInt.length; i++) {
			System.out.print(arrayInt[i] + " ");
		}
	}

}
