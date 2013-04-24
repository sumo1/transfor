package JustForFun;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


/**
 * User: yunshu.xw
 * Date: 13-1-8
 * Time: 下午2:50
 */
public class JustForTest {
    public static void main(String argus[]) throws IOException {
        int year, count, month = 1, index;
        System.out.println("输入你想查询的年份，我将输出这一年的日历，嘿嘿");
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        year = Integer.valueOf(reader.readLine());
        System.out.println(year + "年的日历：");
        index = spaceCount(year)+1;
        while (month <= 12) {
            System.out.println(month + "月：");
            System.out.println("周日 周一 周二 周三 周四 周五 周六");
            count = daysMonth(month, year);
            index = printMonth(index, count);
            month++;
        }
    }

    //打印当前月的日历
    private static int printMonth(int index, int days){
        int now = 1;
        index = (index == 7) ? 0 : index;
        int count = 7-index;
        while (now <= days){
            index = printWeek(index, now, count);
            now = now + count;
            count = (days - now + 1) > 7 ? 7:(days - now + 1);
        }
        return index;
    }

    //打印当前周的日历
    private static int printWeek(int index, int begin, int days){
        printSpace(index);
        int indexM = (days == 7 || index !=0) ? 0 : days;
        while (days > 0){
            dayFormatPrint(begin);
            days--;
            begin++;
        }
        System.out.println();
        return indexM;
    }

    //格式化天的样式，保持对齐
    private static void dayFormatPrint(int day){
        System.out.print(day + ((day < 10) ? "    " : "   "));
    }

    //计算year的i月有多少天
    private static int daysMonth(int i, int year) {
        int[] month31 = {1, 3, 5, 7, 8, 10, 12};
        int[] month30 = {4, 6, 9, 11};
        for (int aMonth31 : month31) {
            if (i == aMonth31)
                return 31;
        }
        for (int aMonth30 : month30) {
            if (i == aMonth30)
                return 30;
        }
        if (daysCount(year) == 365)
            return 28;
        return 29;
    }
    //每个月从周几开始
    private static void printSpace(int count) {
        for (int i = 0; i < count; i++)
            System.out.print("     ");
    }
    //计算year有多少天
    private static int daysCount(int year) {
        if (year % 400 == 0)
            return 366;
        if (year % 100 == 0)
            return 365;
        if (year % 4 == 0)
            return 366;
        return 365;
    }
    //输入年份的一月从周几开始
    private static int spaceCount(int year) {
        int years = year - 1950;
        int days = 0;
        for (int i = 0; i < years; i++) {
            days += daysCount(1950 + i);
        }
        return days % 7;
    }
}
