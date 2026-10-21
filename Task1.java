import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter console = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);

        console.println("Вхідний файл:");
        File input = new File(in.readLine());
        while (!input.exists()) {
            console.println("Файл не знайдено. Введіть шлях ще раз:");
            input = new File(in.readLine());
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

        PrintWriter out = openOutputFile("Файл для цілих чисел:", in, console);
        for (int number : integers) {
            out.println(number);
        }
        out.close();

        out = openOutputFile("Файл для дробових чисел:", in, console);
        for (double number : decimals) {
            out.println(number);
        }
        out.close();

        console.println("Сума дробових чисел: " + sum);
        console.println("Добуток цілих чисел: " + product);
        in.close();
        console.close();
    }

    static PrintWriter openOutputFile(String prompt, BufferedReader in, PrintWriter console) throws IOException {
        while (true) {
            console.println(prompt);
            File file = new File(in.readLine());
            boolean append = false;

            if (!file.exists()) {
                console.println("Файлу немає. Створити? (так/ні)");
                if (!in.readLine().equalsIgnoreCase("так")) {
                    continue;
                }
            } else {
                console.println("1 — перезаписати, 2 — дописати в кінець:");
                append = in.readLine().equals("2");
            }

            return new PrintWriter(new BufferedWriter(new FileWriter(file, append)));
        }
    }
}