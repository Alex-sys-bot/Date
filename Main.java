package ru.sapteh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите дату экзамена \"dd/MM/yy\": ");
        String strExam = buffer.readLine();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Date dateExam = dateFormat.parse(strExam);
        int quantityD = quantityDay(dateExam,calendar.getTime());
        String result= switch (dateExam.compareTo(calendar.getTime())){
            case 0 -> "Экзамен сегодня";
            case 1 -> "До экзамена осталось " + quantityDay(dateExam,calendar.getTime()) + sortDays(quantityD);
            case -1 -> "Экзамент был " + Math.abs(quantityDay(dateExam,calendar.getTime())) + sortDays(quantityD) + "назад";
            default -> "Некорректные данные";
        };
        System.out.println(result);
    }

    public static int quantityDay(Date fristDate, Date secondDate){
        return (int)((fristDate.getTime() - secondDate.getTime())/1000/24/60/60);
    }

    public static String sortDays(int d){
        return switch (Math.abs(d)){
            case 1 -> "день ";
            case 2,3,4 -> "дня ";
            default -> "дней";
        };
    }
}

