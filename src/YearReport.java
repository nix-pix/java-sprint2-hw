import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class YearReport {
    HashMap<Integer, Stat> yearDataByMonth = new HashMap<>();
    boolean flag = false;

    void readReport() {
        String reportPath = "./resources/y.2021.csv";
        String report = readFileContentsOrNull(reportPath);
        String[] lines = report.split("\r?\n"); // разбитие на строки
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] lineContents = line.split(","); // разбитие по запятой
            int month = Integer.parseInt(lineContents[0]); // месяц
            int amount = Integer.parseInt(lineContents[1]); // сумма
            boolean isExpense = Boolean.parseBoolean(lineContents[2]); // трата (true) или доход (false)
            if (!yearDataByMonth.containsKey(month)) {
                yearDataByMonth.put(month, new Stat());
            }
            Stat stat = yearDataByMonth.get(month);
            if (isExpense) {
                stat.expenses += amount;
            } else {
                stat.profits += amount;
            }
        }
        System.out.println(report);
        flag = true;
    }

    String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    void printInfo() {
        System.out.println("Информация из отчета за 2021 год");
    }

    void printProfitByMonth() {
        Stat stat1 = yearDataByMonth.get(01);
        System.out.println("Прибыль за январь составила - " + stat1.profits);
        Stat stat2 = yearDataByMonth.get(02);
        System.out.println("Прибыль за февраль составила - " + stat2.profits);
        Stat stat3 = yearDataByMonth.get(03);
        System.out.println("Прибыль за март составила - " + stat3.profits);
    }

    void printAverageExpenseByMonths() {
        Stat stat1 = yearDataByMonth.get(01);
        Stat stat2 = yearDataByMonth.get(02);
        Stat stat3 = yearDataByMonth.get(03);
        int average = (stat1.expenses + stat2.expenses + stat3.expenses) / 3;
        System.out.println("Средний расход за все месяцы составил - " + average);
    }

    void printAverageProfitByMonths() {
        Stat stat1 = yearDataByMonth.get(01);
        Stat stat2 = yearDataByMonth.get(02);
        Stat stat3 = yearDataByMonth.get(03);
        int average = (stat1.profits + stat2.profits + stat3.profits) / 3;
        System.out.println("Средний доход за все месяцы составил - " + average);
    }
}