// Отсортируйте массив [5,6,3,2,5,1,4,9]

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = {5, 6, 3, 2, 5, 1, 4, 9};

        System.out.println("Массив до сортировки: ");
        Arrays.stream(array).forEach(i -> System.out.print(i + " "));

        assert (array == null);

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] >= array[j + 1]) {
                    int swapTemp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = swapTemp;
                }
            }
        }

        assert (array[0] != Arrays.stream(array).min().getAsInt());
        assert (array[array.length - 1] == Arrays.stream(array).max().getAsInt());

        System.out.println("\nМассив после сортировки: ");

        Arrays.stream(array).forEach(i -> System.out.print(i + " "));

    }
}
