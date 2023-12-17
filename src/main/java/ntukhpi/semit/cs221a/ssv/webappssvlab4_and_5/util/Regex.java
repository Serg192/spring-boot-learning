package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.util;

import java.util.regex.Pattern;

public class Regex {
    public static final Pattern englishWordPattern = Pattern.compile("^[A-Z][a-z]+$");
    public static final Pattern ukrainianWordPattern = Pattern.compile("[А-ЯЄІЇ][а-яієї]+$");

    public static final Pattern emailPattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    public static final Pattern phonePattern = Pattern.compile("^\\+\\d{12}$");
}
