package itsgnegrao.ProjectSpringBoot.Utils;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DataUtils {

    public static boolean isValid(Date data){
        try{
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime from = LocalDateTime.parse(data.toString()+ " 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Duration period = Duration.between(from, today);
            return (period.toMinutes() > 0 );
        }catch(Exception e){
            return false;
        }
    }
}
