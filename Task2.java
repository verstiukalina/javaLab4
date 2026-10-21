import java.io.*;

public class Task2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter console = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
        console.println("Кількість файлів:");
        int n = Integer.parseInt(in.readLine());

        for (int i = 0; i < n; i++) {
            console.println("Шлях до файлу:");
            File input = new File(in.readLine());
            while (!input.exists()) {
                console.println("Файл не знайдено. Введіть шлях ще раз:");
                input = new File(in.readLine());
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

            console.println(input + ": " + count);
        }
        in.close();
        console.close();
    }
}