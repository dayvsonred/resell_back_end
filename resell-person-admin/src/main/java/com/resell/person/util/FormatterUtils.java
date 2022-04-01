package com.resell.person.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.util.Objects.isNull;

public class FormatterUtils {

    private FormatterUtils(){}


    public static String formatDateWithoutHour(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        return dateFormat.format(date);
    }

    public static String formatDateLocalDate(LocalDate date) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return  date.format(formatters);
    }

    public static LocalDate getDateByString(String date){
        if (isNull(date)) {
            return null;
        }

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDate.parse(date, format);
    }

}
