import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class MonthReport {
    HashMap<Integer, HashMap<Integer, Stat>> monthDataByMonth = new HashMap<>();
    boolean flag = false;

    void readReports() {
        for (int i = 1; i < 4; i++) {
            String reportPath = "./resources/m.20210" + i + ".csv";
            String report = readFileContentsOrNull(reportPath);
            String[] lines = report.split("\r?\n"); // разбитие на строки
            HashMap<Integer, Stat> monthData = new HashMap<>();
            monthDataByMonth.put(i, monthData);
            for (int j = 1; j < lines.length; j++) {
                String line = lines[j];
                String[] lineContents = line.split(","); // разбитие по запятой
                String name = lineContents[0]; // название товара
                boolean isExpense = Boolean.parseBoolean(lineContents[1]); // трата (true) или доход (false)
                int quantity = Integer.parseInt(lineContents[2]); // кол-во
                int price = Integer.parseInt(lineContents[3]); // цена за штуку
                int amount = quantity * price; // сумма
                monthData.put(j, new Stat());
                Stat stat = monthData.get(j);
                if (isExpense) {
                    stat.expenses += amount;
                    stat.name = name;
                } else {
                    stat.profits += amount;
                    stat.name = name;
                }
            }
            System.out.println(report);
        }
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

    void printInfo(String month) {
        System.out.println("Информация из отчета за " + month);
    }

    void printMaxProfitByMonth(int month) {
        HashMap<Integer, Stat> monthData = monthDataByMonth.get(month);
        int maxProfit = 0;
        String name = null;
        for (int i = 1; i < 10; i++) {
            if (monthData.containsKey(i)) {
                Stat stat = monthData.get(i);
                if (stat.profits > maxProfit) {
                    maxProfit = stat.profits;
                    name = stat.name;
                }
            }
        }
        System.out.println("Самая прибыльная позиция этого месяца: " + name + ". Прибыль составила - " + maxProfit);
    }

    void printMaxExpenseByMonth(int month) {
        HashMap<Integer, Stat> monthData = monthDataByMonth.get(month);
        int maxExpense = 0;
        String name = null;
        for (int i = 1; i < 10; i++) {
            if (monthData.containsKey(i)) {
                Stat stat = monthData.get(i);
                if (stat.expenses > maxExpense) {
                    maxExpense = stat.expenses;
                    name = stat.name;
                }
            }
        }
        System.out.println("Самая большая трата этого месяца: " + name + ". Затраты составили - " + maxExpense);
    }
}