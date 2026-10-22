import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        try {
            System.out.println("Вхідний файл:");
            File input = new File(in.nextLine());
            while (!input.exists()) {
                System.out.println("Файл не знайдено. Введіть шлях ще раз:");
                input = new File(in.nextLine());
            }

            ArrayList<Integer> integers = new ArrayList<>();
            ArrayList<Double> decimals = new ArrayList<>();
            int product = 1;
            double sum = 0;

            BufferedReader file = new BufferedReader(new FileReader(input));
            String line;
            while ((line = file.readLine()) != null) {
                if (line.matches("[0-9]+")) {
                    int number = Integer.parseInt(line);
                    integers.add(number);
                    product *= number;
                } else {
                    Scanner check = new Scanner(line);
                    if (check.hasNextDouble()) {
                        double number = check.nextDouble();
                        if (!check.hasNext() && Double.isFinite(number)) {
                            decimals.add(number);
                            sum += number;
                        }
                    }
                    check.close();
                }
            }
            file.close();

            Collections.sort(integers);
            Collections.sort(decimals);

            PrintWriter out = openOutputFile("Файл для цілих чисел:", in);
            for (int number : integers) {
                out.println(number);
            }
            out.close();

            out = openOutputFile("Файл для дробових чисел:", in);
            for (double number : decimals) {
                out.println(number);
            }
            out.close();

            System.out.println("Сума дробових чисел: " + sum);
            System.out.println("Добуток цілих чисел: " + product);
        } catch (IOException e) {
            System.out.println("Помилка роботи з файлом.");
        }
        in.close();
    }

    static PrintWriter openOutputFile(String prompt, Scanner in) {
        while (true) {
            System.out.println(prompt);
            File file = new File(in.nextLine());
            boolean append = false;

            if (!file.exists()) {
                System.out.println("Файлу немає. Створити? (так/ні)");
                if (!in.nextLine().equalsIgnoreCase("так")) {
                    continue;
                }
            } else {
                System.out.println("1 — перезаписати, 2 — дописати в кінець:");
                append = in.nextLine().equals("2");
            }

            try {
                return new PrintWriter(new BufferedWriter(new FileWriter(file, append)));
            } catch (IOException e) {
                System.out.println("Не вдалося відкрити файл. Введіть шлях ще раз.");
            }
        }
    }
}