/**
 * Receive.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soshiant.webservice.sms.adpdigital;

public class Receive  implements java.io.Serializable {
    private java.lang.String userName;

    private java.lang.String password;

    private java.lang.String shortNumber;

    private long start;

    private int maxSize;

    public Receive() {
    }

    public Receive(
           java.lang.String userName,
           java.lang.String password,
           java.lang.String shortNumber,
           long start,
           int maxSize) {
           this.userName = userName;
           this.password = password;
           this.shortNumber = shortNumber;
           this.start = start;
           this.maxSize = maxSize;
    }


    /**
     * Gets the userName value for this Receive.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this Receive.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the password value for this Receive.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this Receive.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the shortNumber value for this Receive.
     * 
     * @return shortNumber
     */
    public java.lang.String getShortNumber() {
        return shortNumber;
    }


    /**
     * Sets the shortNumber value for this Receive.
     * 
     * @param shortNumber
     */
    public void setShortNumber(java.lang.String shortNumber) {
        this.shortNumber = shortNumber;
    }


    /**
     * Gets the start value for this Receive.
     * 
     * @return start
     */
    public long getStart() {
        return start;
    }


    /**
     * Sets the start value for this Receive.
     * 
     * @param start
     */
    public void setStart(long start) {
        this.start = start;
    }


    /**
     * Gets the maxSize value for this Receive.
     * 
     * @return maxSize
     */
    public int getMaxSize() {
        return maxSize;
    }


    /**
     * Sets the maxSize value for this Receive.
     * 
     * @param maxSize
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Receive)) return false;
        Receive other = (Receive) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.shortNumber==null && other.getShortNumber()==null) || 
             (this.shortNumber!=null &&
              this.shortNumber.equals(other.getShortNumber()))) &&
            this.start == other.getStart() &&
            this.maxSize == other.getMaxSize();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getShortNumber() != null) {
            _hashCode += getShortNumber().hashCode();
        }
        _hashCode += new Long(getStart()).hashCode();
        _hashCode += getMaxSize();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Receive.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", ">receive"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "userName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shortNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "shortNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("start");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "start"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "maxSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
