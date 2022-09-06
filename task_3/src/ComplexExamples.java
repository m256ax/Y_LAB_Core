import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class ComplexExamples {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };
        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */

    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();

        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться Key: Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */



        for (Map.Entry<String, Long> e : getMap(RAW_DATA).entrySet()) {
            System.out.println("Key: " + e.getKey() + "\n" +
                    "Value: " + e.getValue() + "\n   ");
        }

        /*
        Task2

            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */

        Integer[] array = new Integer[]{3, 4, 2, 7};
        int sum = 10;

        System.out.println(Arrays.toString(getTerms(array, sum)));

        /*
        Task3
            Реализовать функцию нечеткого поиска

                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
         */

        System.out.println(fuzzySearch("car", "ca6$$#_rtwheel")); // true
        System.out.println(fuzzySearch("cwhl", "cartwheel")); // true
        System.out.println(fuzzySearch("cwhee", "cartwheel")); // true
        System.out.println(fuzzySearch("cartwheel", "cartwheel")); // true
        System.out.println(fuzzySearch("cwheeel", "cartwheel")); // false
        System.out.println(fuzzySearch("lw", "cartwheel")); // false

    }

    /*
        Метод для Task1
     */

    static  Map<String, Long> getMap (Person[] data) {

        return Arrays.stream(data)
                .distinct()
                .sorted(Comparator.comparingInt(Person::getId))
                .collect(groupingBy(Person::getName, counting()));
    }

    /*
        Метод для Task2
     */
    static Integer[] getTerms(Integer[] arr, int d) {

        List<Integer> integers = Arrays.asList(arr);

        return integers.stream()
                .flatMap(a ->
                        integers.stream()
                                .filter(b -> a != b && a + b == d)
                                .map(b -> new Integer[]{a, b}))
                .findFirst()
                .get();

    }

    /*
        Метод для Task3
     */
    private static boolean fuzzySearch(String word, String lotsOf) {
        char[] firstWord = word.toCharArray();
        List<Character> characters = lotsOf.chars()
                                            .mapToObj(c -> (char) c)
                                            .collect(Collectors.toList());

        int countDownLength = firstWord.length;
        int previousCharPosition = 0;

        for (int i = 0; i < firstWord.length; i++) {

            for (int currentCharPosition = 0; currentCharPosition < characters.size(); currentCharPosition++) {
                if (firstWord[i] == characters.get(currentCharPosition)) {

                    if (previousCharPosition > currentCharPosition) {
                        return false;
                    }
                    previousCharPosition = currentCharPosition;
                    characters.remove(currentCharPosition);
                    countDownLength--;

                    if (countDownLength == 0) {
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }
}
