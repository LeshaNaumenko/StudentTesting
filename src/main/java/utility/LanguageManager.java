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
        exist(defLoc);
        resourceBundle = ResourceBundle.getBundle("pagecontent.messages", locale);
    }

    public void setLocale(String locale) {
        exist(locale);
        resourceBundle = ResourceBundle.getBundle("pagecontent.messages", this.locale);
    }

    public String getMessage(String key) {
        return resourceBundle.getString(key);
    }

    public Locale getLocale() {
        return locale;
    }
    private void exist(Locale defLoc) {
        if (Stream.of(Language.values()).anyMatch((o) -> defLoc.equals(o.getLocale())))
            this.locale = defLoc;
    }
    private void exist(String lang) {
        if (Stream.of(Language.values()).anyMatch((o) -> lang.equals(o.name())))
            this.locale = Language.valueOf(lang).getLocale();
        else
            this.locale = Locale.getDefault();
    }

}
