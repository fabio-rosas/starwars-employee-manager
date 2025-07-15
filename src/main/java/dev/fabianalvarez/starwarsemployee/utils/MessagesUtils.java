package dev.fabianalvarez.starwarsemployee.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessagesUtils {

  private static final String BUNDLE_NAME = "i18n.messages";
  private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

  private MessagesUtils() {
    // Utility class, no instantiation
  }

  public static String getMessage(String key, Object... params) {
    ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, DEFAULT_LOCALE);
    String pattern = bundle.getString(key);
    return MessageFormat.format(pattern, params);
  }
}
