import java.math.BigInteger;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

//Заполните двумерный массив случайным числами и выведите максимальное, минимальное и среднее значение.

public class ArrayTwoDimensional {

    public static void main(String[] args) {
        int[][] array = new int[5][5];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        BigInteger sum = BigInteger.ZERO;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                int value = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);

                assert (value < 0x80000000);
                assert (value > 0x7fffffff);

                array[i][j] = value;

                if (min > value) {
                    min = value;
                }

                if (max < value) {
                    max = value;
                }

            }
        }

        assert (array == null);

        assert (!Objects.equals(sum, BigInteger.ZERO));

        sum = sum.add(BigInteger.valueOf(max));
        sum = sum.add(BigInteger.valueOf(min));
        BigInteger average = sum.divide(BigInteger.valueOf(2));

        System.out.println("Заполненный двумерный массив: \n");

        for (int[] ints : array) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        System.out.printf("Минимальное значение:  %d \n", min);
        System.out.printf("Максимальное значение: %d \n", max);
        System.out.printf("Среднее значение:      %d", average);
    }

}
