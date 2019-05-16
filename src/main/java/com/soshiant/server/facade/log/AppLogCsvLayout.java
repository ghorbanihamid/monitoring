package com.soshiant.server.facade.log;

import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * SimpleLayout consists of the level of the log statement, followed by " - "
 * and then the log message itself. For example,
 * {@link PatternLayout} offers a much more powerful alternative.
 */

public class AppLogCsvLayout extends Layout implements OptionHandler {

    private static final Logger LOGGER = Logger.getLogger(AppLogCsvLayout.class);

    /* Configurations from the log4j.properties */
    private String conversionPattern;
    private String separator = ";";
    private String outputHeader = "false";

    protected List attributes;
    protected String modelClass;
    protected Boolean outputCsvHeader;

    private Boolean csvHeaderPrinted = new Boolean(false);

    public AppLogCsvLayout() {

        super();
    }

    /**
     * Name of the attribute for the dn
     */

    private final static String DN_STRING = "dn";

    /**
     * WARN : We only write the first value of each attribute because we write
     * in a 2 dimensional format
     *
     * @see org.apache.log4j.PatternLayout#format(org.apache.log4j.spi.LoggingEvent)
     */
    public String format(LoggingEvent event) {

        Object message = event.getMessage();
        String result = "";

        if (message != null) {

            Class tempModelClass = message.getClass();
            Method[] getterMethods = getGettersMethods(tempModelClass);
            Method[] sortedGetterMethods = sortMethodsAccordingAttribs(getterMethods);
            StringBuffer sb = new StringBuffer(1024);

            Object[] params = null;
            for (int i = 0; i < sortedGetterMethods.length; i++) {
                Class retType;
                try {
                    if (sortedGetterMethods[i] != null) {
                        retType = sortedGetterMethods[i].getReturnType();
                        if ((retType.isPrimitive())
                                | (retType.getName().equals("java.lang.String"))) {
                            if (i != 0)
                                sb.append(this.getSeparator());
                            sb.append(sortedGetterMethods[i].invoke(message,
                                    params));
                        }
                        result = sb.toString();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            sb.append(LINE_SEP);
            result = sb.toString();
        }

        // if (outputCsvHeader && !csvHeaderPrinted) {
        //
        // result = attrs + "\n" + result;
        // csvHeaderPrinted = true;
        //
        // }
        return result;

    }

    private Method[] getGettersMethods(Class dtoClass) {
        Method[] methods = dtoClass.getMethods();
        ArrayList getters = new ArrayList();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().startsWith("get"))
                getters.add(methods[i]);
        }
        if (getters.size() > 0) {
            Object[] getArr = getters.toArray();
            Method[] gettersArray = new Method[getArr.length];
            for (int i = 0; i < getArr.length; i++)
                gettersArray[i] = (Method) getArr[i];
            return gettersArray;
        }
        return null;
    }

    private Method[] sortMethodsAccordingAttribs(Method[] methods) {
        Method[] configuredMethods = new Method[attributes.size()];
        Iterator it = attributes.iterator();
        int j = 0;
        while (it.hasNext()) {
            String attrib = (String) it.next();
            for (int i = 0; i < methods.length; i++) {
                String str = methods[i].getName().substring(3);
                if (str.length() >= attrib.length())
                    str = str.substring(0, attrib.length());
                if (attrib.equalsIgnoreCase(str)) {
                    configuredMethods[j] = methods[i];
                    j++;
                    break;
                }

            }

        }

        return configuredMethods;
    }

    /**
     * Parse options
     *
     * @see org.apache.log4j.Layout#activateOptions()
     */

    public void activateOptions() {

        /* Parse attributes to log */
        attributes = new ArrayList();
        String convPattern = this.getConversionPattern();
        if (convPattern != null) {
            String[] st = convPattern.split(this.getSeparator());
            String fieldName;
            for (int i = 0; i < st.length; i++) {
                fieldName = st[i].toLowerCase();
                attributes.add(fieldName);
            }
        } else {
            LOGGER
                    .warn("There is no attributes to write in the CSV file.\nSet the "
                            + "log4j.appender.NAME.layout.conversionPattern property.");
        }

        if (modelClass == null)
            LOGGER.warn("There is no Model class name .\nSet the "
                    + "log4j.appender.NAME.layout.ModelName property. ");

        outputCsvHeader = Boolean.valueOf(outputHeader);
    }

    /**
     * We do not ignore Throwable
     *
     * @see org.apache.log4j.Layout#ignoresThrowable()
     */

    public boolean ignoresThrowable() {

        return false;
    }

    public String getModelClass() {
        return modelClass;
    }

    public void setModelClass(String modelClass) {
        this.modelClass = modelClass;
    }

    public String getConversionPattern() {

        return conversionPattern;
    }

    public void setConversionPattern(String conversionPattern) {

        this.conversionPattern = conversionPattern;
    }

    public String getSeparator() {

        return separator;
    }

    public void setSeparator(String separator) {

        this.separator = separator;
    }

    public String getOutputHeader() {

        return outputHeader;
    }

    public void setOutputHeader(String outputHeader) {

        this.outputHeader = outputHeader;
    }

    public String getHeader() {

        return null;
    }
}