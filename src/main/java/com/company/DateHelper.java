package com.company;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import static java.lang.Math.abs;


/**
 * Created by tyuly on 21.11.2016.
 * work with time
 */
public class DateHelper {
    private LocalDateTime startDate;
    private LocalDateTime finalDate;
    private DateTimeFormatter dateTimeFormatter;
    private int k = 1;
    static final int SKIP = 45;

    DateHelper() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyy HH:mm");
    }

    /**
     * method for get formatted date
     * @return SimpleFormatDate startDate
     */
    public String getFormattedStartData() {
        startDate = LocalDateTime.now().minusYears(1500);
        return dateTimeFormatter.format(startDate);
    }

    /**
     * method to skip time
     */
    public void skipTime() {
        finalDate = startDate.plusMinutes(SKIP*k);
        k++;
    }

    /**
     * method to get final time
     * @return SimpleFormatDate finalDate
     */
    public String getFormattedFinalData() {
        return dateTimeFormatter.format(finalDate);
    }

    /**
     * return difference between start date and final date
     * @return String difference
     */
     public String getFormattedDiff () {
         Period period = Period.between(startDate.toLocalDate(),finalDate.toLocalDate());
         long time[] = getTime(startDate,finalDate);
         return period.getYears() + " years " + period.getMonths() + " months " + period.getDays() + " days " +
                 time[0] + " hours " + time[1] + " minutes " + time[2] + " seconds";
     }

    /**
     *
     * @param date1 start date
     * @param date2 final date
     * @return long[] hours minutes seconds
     */
    public long[] getTime(LocalDateTime date1, LocalDateTime date2) {
        LocalDateTime today = LocalDateTime.of(date2.getYear(),
                date2.getMonthValue(), date2.getDayOfMonth(), date1.getHour(), date1.getMinute(), date1.getSecond());
        Duration duration = Duration.between(today, date2);
        long seconds = duration.getSeconds();
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long sec = seconds % 60;
        return new long[]{hours, minutes, sec};
    }
}
