import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class YearReport {
    HashMap<Integer, Stat> yearDataByMonth = new HashMap<>();
    HashMap<Integer, Integer> profitByMonth = new HashMap<>();
    HashMap<Integer, Integer> expenseByMonth = new HashMap<>();
    boolean flag = false;

    void readReport() { // считывание и привидение отчета
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
        findProfitByMonth();
        findExpenseByMonth();
        flag = true;
    }

    void findProfitByMonth() { // заполнение таблицы доходов по месяцам
        Stat stat1 = yearDataByMonth.get(01);
        profitByMonth.put(1, stat1.profits);
        Stat stat2 = yearDataByMonth.get(02);
        profitByMonth.put(2, stat2.profits);
        Stat stat3 = yearDataByMonth.get(03);
        profitByMonth.put(3, stat3.profits);
    }

    void findExpenseByMonth() { // заполнение таблицы расходов по месяцам
        Stat stat1 = yearDataByMonth.get(01);
        expenseByMonth.put(1, stat1.expenses);
        Stat stat2 = yearDataByMonth.get(02);
        expenseByMonth.put(2, stat2.expenses);
        Stat stat3 = yearDataByMonth.get(03);
        expenseByMonth.put(3, stat3.expenses);
    }

    String readFileContentsOrNull(String path) { // утилита для считывания .csv
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

    void printProfitByMonth() { // прибыль по месяцам
        Stat stat1 = yearDataByMonth.get(01);
        System.out.println("Прибыль за январь составила - " + stat1.profits);
        Stat stat2 = yearDataByMonth.get(02);
        System.out.println("Прибыль за февраль составила - " + stat2.profits);
        Stat stat3 = yearDataByMonth.get(03);
        System.out.println("Прибыль за март составила - " + stat3.profits);
    }

    void printAverageExpenseByMonths() { // средний расход за все месяцы
        Stat stat1 = yearDataByMonth.get(01);
        Stat stat2 = yearDataByMonth.get(02);
        Stat stat3 = yearDataByMonth.get(03);
        int average = (stat1.expenses + stat2.expenses + stat3.expenses) / 3;
        System.out.println("Средний расход за все месяцы составил - " + average);
    }

    void printAverageProfitByMonths() { // средний доход за все месяцы
        Stat stat1 = yearDataByMonth.get(01);
        Stat stat2 = yearDataByMonth.get(02);
        Stat stat3 = yearDataByMonth.get(03);
        int average = (stat1.profits + stat2.profits + stat3.profits) / 3;
        System.out.println("Средний доход за все месяцы составил - " + average);
    }
}