import java.util.concurrent.ThreadLocalRandom;

//Заполните двумерный массив случайным числами и выведите максимальное, минимальное и среднее значение.

public class Main {

    public static void main(String[] args) {

        int[][] array = new int[5][5];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                int value = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
                array[i][j] = value;

                if (min > value) {
                    min = value;
                }

                if (max < value) {
                    max = value;
                }
            }
        }

        int middle = (max + max) / 2;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        System.out.printf("Минимальное значение - %d \n",  min);
        System.out.printf("Максимальное значение - %d \n", max);
        System.out.printf("Среднее значение - %d", max);
    }

}
