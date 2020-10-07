package itsgnegrao.ProjectSpringBoot.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtils {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,11}$", Pattern.CASE_INSENSITIVE);

    public static boolean isValid(String emailStr) {
        if (emailStr == null || emailStr.isEmpty()) {
            return true;
        }
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
