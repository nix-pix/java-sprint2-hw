import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        YearReport yearReport = new YearReport();
        MonthReport monthReport = new MonthReport();

        System.out.println("ПРИЛОЖЕНИЕ ДЛЯ АВТОМАТИЗАЦИИ БУХГАЛТЕРИИ");
        printMenu();
        int userInput = scanner.nextInt();
        while (userInput != 0) {
            switch (userInput) {
                case 1:
                    monthReport.readReports();
                    System.out.println("Все месячные отчеты считаны и занесены в память программы для дальнейшей обработки.");
                    break;
                case 2:
                    yearReport.readReport();
                    System.out.println("Годовой отчет считан и занесен в память программы для дальнейшей обработки.");
                    break;
                case 3:
                    if (monthReport.flag && yearReport.flag) {
                        ReportAudit.auditProfit(monthReport, yearReport);
                        ReportAudit.auditExpense(monthReport, yearReport);
                    } else {
                        System.out.println("Вы не занесли отчеты в память программы. Сначала считайте все отчеты...");
                    }
                    break;
                case 4:
                    if (monthReport.flag) {
                        monthReport.printInfo("январь:");
                        monthReport.printMaxProfitByMonth(1);
                        monthReport.printMaxExpenseByMonth(1);
                        monthReport.printInfo("февраль:");
                        monthReport.printMaxProfitByMonth(2);
                        monthReport.printMaxExpenseByMonth(2);
                        monthReport.printInfo("март:");
                        monthReport.printMaxProfitByMonth(3);
                        monthReport.printMaxExpenseByMonth(3);
                    } else {
                        System.out.println("Вы не занесли месячные отчеты в память программы. Нажмите 1 для считывания...");
                    }
                    break;
                case 5:
                    if (yearReport.flag) {
                        yearReport.printInfo();
                        yearReport.printProfitByMonth();
                        yearReport.printAverageExpenseByMonths();
                        yearReport.printAverageProfitByMonths();
                    } else {
                        System.out.println("Вы не занесли годовой отчет в память программы. Нажмите 2 для считывания...");
                    }
                    break;
                default:
                    System.out.println("Извините, такой фукции пока нет.");
            }
            printMenu();
            userInput = scanner.nextInt();
        }
        System.out.println("Программа завершена");
    }

    public static void printMenu() { // основное меню приложения
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выйти из приложения");
        // выбор значения вне диапазона работает
        // напоминание о считывании отчета работает
    }
}

