package com.soshiant.common.util;

import com.opensymphony.xwork2.ActionContext;
import org.apache.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: Hubert
 * Date: 9/19/11
 * Time: 12:58 PM
 */
public class BundleUtil {

    private static Logger log = Logger.getLogger(BundleUtil.class.getName());

    private static String bundleFileName = "ApplicationResources";

    private String language = null;

    private String country = null;

    private Locale locale;

    private ResourceBundle messages = null;

    public static BundleUtil instance;

    private void initBundle() {
        if (language == null && country == null)
            locale = new Locale("en", "US");
        else
            locale = new Locale(language, country);
        try {
            messages = ResourceBundle.getBundle(bundleFileName, locale);
        } catch (Exception e) {
            log.error("Exception in initBundle :" + e.getMessage());
        }
    }

    private BundleUtil(String language, String country) {
        this.language = language;
        this.country = country;
        initBundle();
    }

    public static BundleUtil getInstance(String language, String country) {
        instance = new BundleUtil(language, country);
        return instance;
    }

    public static BundleUtil getInstance() {

        if (instance == null) {
            instance = new BundleUtil(ActionContext.getContext().getLocale().getLanguage(),
                    ActionContext.getContext().getLocale().getCountry());
        }
        return instance;
    }

    public static BundleUtil getInstance(Locale locale) {
        if (locale != null)
            instance = new BundleUtil(locale.getLanguage(), locale.getCountry());
        else
            instance = new BundleUtil(ActionContext.getContext().getLocale().getLanguage(),
                    ActionContext.getContext().getLocale().getCountry());
        return instance;
    }

    public String getMessage(String key) {
        String localizedMessage;
        try {
            localizedMessage = messages.getString(key);
        } catch (Exception e) {
            localizedMessage = key;
            log.error("Exception in getMessage(" + key + "):" + e.getMessage());
        }
        return localizedMessage;
    }

    public String getMessage(String key, String message) {
        String localizedMessage;
        try {
            localizedMessage = messages.getString(key);
        } catch (Exception e) {
            localizedMessage = message;
            log.error("Exception in BundleUtil.getMessage(key:'" + key + "') " + e.getMessage());
        }
        return localizedMessage;
    }

    public String toString() {
        return "\nBundleUtil{" +
                "\n  language='" + language + "'" +
                "\n  country='" + country + "'" +
                "\n  bundleFileName='" + bundleFileName + "'" +
                "\n  locale='" + locale + "'" +
                "\n  messages='" + messages + "'" +
                "\n}\n";
    }
}
