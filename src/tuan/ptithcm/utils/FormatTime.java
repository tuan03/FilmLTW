package ptithcm.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class FormatTime {

    public static String formatTimestamp(String timestamp) {
    	System.out.print(timestamp);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime dateTime = LocalDateTime.parse(timestamp, formatter);
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(dateTime, now);
        Period period = Period.between(dateTime.toLocalDate(), now.toLocalDate());

        if (duration.toMinutes() < 60) {
            return duration.toMinutes() + " phút trước";
        } else if (duration.toHours() < 24) {
            return duration.toHours() + " giờ trước";
        } else if (period.getDays() < 30) {
            return period.getDays() + " ngày trước";
        } else if (period.getYears() < 1) {
            return dateTime.format(DateTimeFormatter.ofPattern("dd/MM"));
        } else {
            return dateTime.format(DateTimeFormatter.ofPattern("MM/yyyy"));
        }
    }
}
