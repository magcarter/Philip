/* 
 * Copyright 2020 Rene Bylander at WITC
 */

package edu.witc.utility;

/**
 * @author Rene Bylander
 * @created Jul 25, 2017
 * @updated Nov, 2020 - to include FormatStyle and ZonedDateTime. Removed ENUM 
 * and some other methods.
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;

/**
 * ofLocalizedDate and ofLocalizedDateTiime return a locale specific date/time format
 * ZonedDateTime needed with formatting a DateTime
 * Available FormatStyle constants: FULL, LONG, MEDIUM, SHORT.
 */
public class DateUtil {
     
    public static LocalDate getCurrentDate(){
        //example: 2020-03-28 (ISO_DATE)
        return LocalDate.now();
    }
    
    public static LocalDateTime getCurrentDateTime(){
        //example: 2020-11-09T10:30:45
        return LocalDateTime.now();
    }
    public static String getFormattedDate(LocalDate date, String pattern){
        //use this method for custom formats 
        //e.g. european date style is "dd.MM.yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }
    public static String getFormattedDateNowFull(){
        //formats the "now" date 
        //(e.g. Thursday, November 12, 2020)
       
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        return LocalDateTime.now().format(formatter);
    } 
    public static String getFormattedDateFull(LocalDate date){
        //formats an existing time and date 
        //(e.g. Thursday, November 12, 2020) 
        
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        return date.format(formatter);
    }
    public static String getFormattedDateTimeLong(LocalDateTime date){
        //formats an existing time and date 
        //(e.g. November 12, 2020 2:20:30 PM CST)
        
        ZonedDateTime zonedDateTime = ZonedDateTime.of(date, ZoneId.systemDefault());

        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(zonedDateTime);
    }
    
    public static boolean isLeapYear(LocalDate date){
        return date.isLeapYear();  
    }
    public static Month getDateMonth(LocalDate date){
        return date.getMonth();
    }
    public static int getDateDayOfMonth(LocalDate date){
        return date.getDayOfMonth();
    }
    public static int getDateYear(LocalDate date){
        return date.getYear();
    }
    
    public static LocalDate getDateFrom(int yearDate, Month monthDate, int dayDate ){
        return LocalDate.of(yearDate, monthDate,dayDate);
    }
    public static LocalDate getDateFrom(int yearDate, int monthDate, int dayDate ){
        return LocalDate.of(yearDate, monthDate,dayDate);
    }
    public static long getDaysBetween(LocalDate date1, LocalDate date2){
        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
        return daysBetween;
    }
    public static LocalDate addDaysToDate(LocalDate date, int days){
        LocalDate plusDays = date.plus(days, ChronoUnit.DAYS);
        return plusDays;
    }
    public static LocalDate addWeeksToDate(LocalDate date, int weeks){
        LocalDate plusWeeks = date.plus(weeks, ChronoUnit.WEEKS);
        return plusWeeks;
    }
    public static LocalDate addMonthsToDate(LocalDate date, int months){
        LocalDate plusMonths = date.plus(months, ChronoUnit.MONTHS);
        return plusMonths;
    }
    public static LocalDate addYearsToDate(LocalDate date, int years){
        LocalDate plusYears = date.plus(years, ChronoUnit.YEARS);
        return plusYears;
    }
    public static LocalDate addDecadesToDate(LocalDate date, int decades){
        LocalDate plusDecades = date.plus(decades, ChronoUnit.DECADES);
        return plusDecades;
    }
    /*substract timeframe from date*/
    public static LocalDate minusDaysFromDate(LocalDate date, int days){
        LocalDate minusDays = date.minus(days, ChronoUnit.DAYS);
        return minusDays;
    }
    public static LocalDate minusWeeksFromDate(LocalDate date, int weeks){
        LocalDate minusWeeks = date.minus(weeks, ChronoUnit.WEEKS);
        return minusWeeks;
    }
    public static LocalDate minusMonthsFromDate(LocalDate date, int months){
        LocalDate minusMonths = date.minus(months, ChronoUnit.MONTHS);
        return minusMonths;
    }
    public static LocalDate minusYearsFromDate(LocalDate date, int years){
        LocalDate minusYears = date.minus(years, ChronoUnit.YEARS);
        return minusYears;
    }
    public static LocalDate minusDecadesFromDate(LocalDate date, int decades){
        LocalDate minusDecades = date.minus(decades, ChronoUnit.DECADES);
        return minusDecades;
    }
    public static int getMonthValueInt(LocalDate date){
        return date.getMonthValue();
    }
   
    
    /**
     * Use if you want a SQL date based on current date
     * @return current java.sql.Date
     */
    public static java.sql.Date getSqlDate() {
        LocalDate today = getCurrentDate();
        java.sql.Date sqlDate = java.sql.Date.valueOf(today);
        return sqlDate;
    }
     /**
     * Use if you want a SQL date based on a specific LocalDate
     * @param date of type LocalDate
     * @return java.sql.Date
     */
    public static java.sql.Date getSqLDate(LocalDate date){ 
        java.sql.Date sqlDate = null;
        if(date != null){
            sqlDate = java.sql.Date.valueOf(date);
        } 
      return sqlDate;   
    }
    /**
     * Use if you have a SQL Date but need a LocalDate
     * @param sqlDate
     * @return LocalDate
     */
    public static LocalDate getLocalDateFromSQLDate(java.sql.Date sqlDate){
        LocalDate date = null;
        if(sqlDate != null){
            date = sqlDate.toLocalDate();
        }
        return date;
    }
/**
     * Use if you want a SQL datetime based on current date and time
     * @return current java.sql.Timestamp
     */
    public static java.sql.Timestamp getSqlTimestamp() {
        LocalDateTime today = getCurrentDateTime();
        java.sql.Timestamp sqlDateTime = java.sql.Timestamp.valueOf(today);
        return sqlDateTime;
    }
    /**
     * Use if you want a SQL datetime based on a specific LocalDateTime
     * @param now of type LocalDateTime
     * @return java.sql.Timestamp
     */
    public static java.sql.Timestamp getSqlTimestamp(LocalDateTime now){ 
        java.sql.Timestamp sqlTimestamp = null;
        if(now != null){
            sqlTimestamp = java.sql.Timestamp.valueOf(now);
        } 
      return sqlTimestamp;   
    }
    /**
     * Use if you have a SQL datetime but need a LocalDateTime
     * @param sqlTimestamp
     * @return LocalDateTime
     */
    public static LocalDateTime getLocalDateTime(java.sql.Timestamp sqlTimestamp){
        LocalDateTime now = null;
        if(sqlTimestamp != null){
            now = sqlTimestamp.toLocalDateTime();
        }
        return now;
    }


}//end of class
