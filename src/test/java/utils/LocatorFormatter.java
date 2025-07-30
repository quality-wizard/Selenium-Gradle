package utils;

public class LocatorFormatter {

    public static String formatXpath(String template, String input) {
        // Limpia comillas simples duplicadas y externas
        String cleaned = input.replace("'", "").trim();
        return String.format(template, cleaned);
    }
}
