public class ReportAudit {

    static void auditProfit(MonthReport monthReport, YearReport yearReport) { // сверка доходов
        boolean flag = true;
        for (int i = 1; i < 4; i++) {
            int yearSum = yearReport.profitByMonth.get(i);
            int monthSum = monthReport.profitByMonth.get(i);
            String month = null;
            if (i == 1) {
                month = "Январь";
            } else if (i == 2) {
                month = "Февраль";
            } else if (i == 3) {
                month = "Март";
            }
            if (yearSum != monthSum) {
                System.out.println(month + " - месяц, в котором было обнаружено несоответствие по доходам");
                flag = false;
            }
        }
        if (flag) {
            System.out.println("Операция сверки доходов по месяцам завершена успешно. Все сходится!");
        }
    }

    static void auditExpense(MonthReport monthReport, YearReport yearReport) { // сверка расходов
        boolean flag = true;
        for (int i = 1; i < 4; i++) {
            int yearSum = yearReport.expenseByMonth.get(i);
            int monthSum = monthReport.expenseByMonth.get(i);
            String month = null;
            if (i == 1) {
                month = "Январь";
            } else if (i == 2) {
                month = "Февраль";
            } else if (i == 3) {
                month = "Март";
            }
            if (yearSum != monthSum) {
                System.out.println(month + " - месяц, в котором было обнаружено несоответствие по расходам");
                flag = false;
            }
        }
        if (flag) {
            System.out.println("Операция сверки расходов по месяцам завершена успешно. Все сходится!");
        }
    }
}
