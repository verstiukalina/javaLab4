import java.io.*;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Кількість файлів:");
            int n = in.nextInt();
            in.nextLine();

            for (int i = 0; i < n; i++) {
                System.out.println("Шлях до файлу:");
                File input = new File(in.nextLine());
                while (!input.exists()) {
                    System.out.println("Файл не знайдено. Введіть шлях ще раз:");
                    input = new File(in.nextLine());
                }

                BufferedReader file = new BufferedReader(new FileReader(input));
                int count = 0;
                int symbol;

                while ((symbol = file.read()) != -1) {
                    if (symbol != ' ' && symbol != '\r' && symbol != '\n' && symbol != '\t') {
                        count++;
                    }
                }
                file.close();

                System.out.println(input + ": " + count);
            }
        } catch (IOException e) {
            System.out.println("Помилка роботи з файлом.");
        }
        in.close();
    }
}