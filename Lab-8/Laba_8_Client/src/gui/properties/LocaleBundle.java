package gui.properties;

import gui.properties.exceptions.InvalidParameterException;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleBundle {
    private static final String path = "gui.properties.";
    private static final ResourceBundle bundleRu = ResourceBundle.getBundle(path + "resources_ru_RU", new Locale("ru", "RU"));
    private static final ResourceBundle bundleEn = ResourceBundle.getBundle(path + "resources_en_NZ", new Locale("en", "NZ"));
    private static final ResourceBundle bundleCs = ResourceBundle.getBundle(path + "resources_cs_CZ");
    private static final ResourceBundle bundleHr = ResourceBundle.getBundle(path + "resources_hr_HR");

    private static String currentLanguage = "English";

    public static ResourceBundle getBundle(String language) {
        switch (language) {
            case "English":
                return bundleEn;
            case "Русский":
                return bundleRu;
            case "Česko":
                return bundleCs;
            case "Hrvatski":
                return bundleHr;
        }
        throw new InvalidParameterException();
    }

    public static ResourceBundle getCurrentBundle() {
        return getBundle(currentLanguage);
    }

    public static void setCurrentLanguage(String language) {
        currentLanguage = language;
    }

    public static String getValue(String key) {
        return new String(getBundle(currentLanguage).getString(key).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    }
}
