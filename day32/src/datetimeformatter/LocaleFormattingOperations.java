package datetimeformatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class LocaleFormattingOperations {

    public static void localeFormatting() {

        System.out.println("\n===== LOCALE-SPECIFIC FORMATTING =====");
        System.out.println("Format dates according to country/region\n");

        LocalDate date = LocalDate.of(2026, 9, 5);
        LocalDateTime dateTime = LocalDateTime.of(2026, 9, 5, 14, 30, 45);

        // Different locales
        System.out.println("=== Different Locales (Same Date) ===");
        System.out.println("Date: " + date);
        
        // US locale
        DateTimeFormatter usFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.US);
        System.out.println("US Format : " + date.format(usFormatter));

        // UK locale
        DateTimeFormatter ukFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.UK);
        System.out.println("UK Format : " + date.format(ukFormatter));

        // Indian locale
        DateTimeFormatter indianFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Indian Format : " + date.format(indianFormatter));

        // French locale
        DateTimeFormatter frenchFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRANCE);
        System.out.println("French Format : " + date.format(frenchFormatter));

        // German locale
        DateTimeFormatter germanFormatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy", Locale.GERMANY);
        System.out.println("German Format : " + date.format(germanFormatter));

        // Using FormatStyle
        System.out.println("\n\n=== Using FormatStyle ===");
        
        // SHORT style
        DateTimeFormatter shortFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        System.out.println("SHORT : " + date.format(shortFormatter));

        // MEDIUM style
        DateTimeFormatter mediumFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        System.out.println("MEDIUM : " + date.format(mediumFormatter));

        // LONG style
        DateTimeFormatter longFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        System.out.println("LONG : " + date.format(longFormatter));

        // FULL style
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        System.out.println("FULL : " + date.format(fullFormatter));

        // DateTime with locale
        System.out.println("\n\n=== DateTime with Locale ===");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        System.out.println("DateTime MEDIUM : " + dateTime.format(dateTimeFormatter));

        System.out.println("\n\nNote: Locale affects month names, day names, and date order");
        System.out.println("Use Locale for internationalization (i18n)");
    }
}
