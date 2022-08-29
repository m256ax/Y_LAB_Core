import java.math.BigInteger;
import java.util.*;

//Заполните двумерный массив случайным числами и выведите максимальное, минимальное и среднее значение.

public class ArrayTwoDimensional {

    public static void main(String[] args) {
        int[][] array = init(10);
        int[] result = calc(array);

        System.out.printf("Максимальное значение: %d \n", result[2]);
        System.out.printf("Минимальное значение:  %d \n", result[0]);
        System.out.printf("Среднее значение:      %d", result[1]);
    }

    private static int[] calc(int[][] array) {
        int[] result = {Integer.MAX_VALUE, 0, Integer.MIN_VALUE};

        BigInteger sum = BigInteger.ZERO;

        for (int[] ints : array) {
            for (int j = 0; j < array.length; j++) {
                int value = ints[j];

                if (result[0] > value) {
                    result[0] = value;
                }

                if (result[2] < value) {
                    result[2] = value;
                }

                sum = sum.add(BigInteger.valueOf(value));

            }
        }

        assert (!Objects.equals(sum, BigInteger.ZERO));

        result[1] = Integer.parseInt(sum
                .divide(BigInteger.valueOf(array.length))
                .toString());
        return result;
    }

    private static int randomizer(int previous) {

        long a = 25214903917L;
        long c = 11L;

        long r = a * previous + c * new Date().getTime();

        assert (r >= 0x80000000);
        assert (r <= 0x7fffffff);

        return (int) r;
    }

    private static int[][] init(int arrayLength) {

        int[][] array = new int[arrayLength][arrayLength];

        int previous = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {

                int value = randomizer(previous);

                array[i][j] = value;

                previous = value;

            }

        }
        return array;
    }

}
