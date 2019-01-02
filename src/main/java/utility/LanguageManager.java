package utility;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public enum LanguageManager {
    INSTANCE;
    private ResourceBundle resourceBundle;
    private Locale locale = new Locale("en", "UK");

    LanguageManager() {
        Locale defLoc = Locale.getDefault();
        if (Stream.of(Language.values()).anyMatch((o) -> defLoc.equals(o.getLocale())))
            locale = defLoc;
        resourceBundle = ResourceBundle.getBundle("pagecontent.messages", locale);
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
        resourceBundle = ResourceBundle.getBundle("pagecontent.messages", this.locale);
    }

    public String getMessage(String key) {
        return resourceBundle.getString(key);
    }

    public Locale getLocale() {
        return locale;
    }
}
