/**
 * SendMultiple.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soshiant.webservice.sms.adpdigital;

public class SendMultiple  implements java.io.Serializable {
    private java.lang.String userName;

    private java.lang.String password;

    private java.lang.String shortNumber;

    private java.lang.String sourcePort;

    private java.lang.String destPort;

    private short messageType;

    private boolean longSupported;

    private com.soshiant.webservice.sms.adpdigital.MultiAddressMessageObject[] messages;

    public SendMultiple() {
    }

    public SendMultiple(
           java.lang.String userName,
           java.lang.String password,
           java.lang.String shortNumber,
           java.lang.String sourcePort,
           java.lang.String destPort,
           short messageType,
           boolean longSupported,
           com.soshiant.webservice.sms.adpdigital.MultiAddressMessageObject[] messages) {
           this.userName = userName;
           this.password = password;
           this.shortNumber = shortNumber;
           this.sourcePort = sourcePort;
           this.destPort = destPort;
           this.messageType = messageType;
           this.longSupported = longSupported;
           this.messages = messages;
    }


    /**
     * Gets the userName value for this SendMultiple.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this SendMultiple.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the password value for this SendMultiple.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this SendMultiple.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the shortNumber value for this SendMultiple.
     * 
     * @return shortNumber
     */
    public java.lang.String getShortNumber() {
        return shortNumber;
    }


    /**
     * Sets the shortNumber value for this SendMultiple.
     * 
     * @param shortNumber
     */
    public void setShortNumber(java.lang.String shortNumber) {
        this.shortNumber = shortNumber;
    }


    /**
     * Gets the sourcePort value for this SendMultiple.
     * 
     * @return sourcePort
     */
    public java.lang.String getSourcePort() {
        return sourcePort;
    }


    /**
     * Sets the sourcePort value for this SendMultiple.
     * 
     * @param sourcePort
     */
    public void setSourcePort(java.lang.String sourcePort) {
        this.sourcePort = sourcePort;
    }


    /**
     * Gets the destPort value for this SendMultiple.
     * 
     * @return destPort
     */
    public java.lang.String getDestPort() {
        return destPort;
    }


    /**
     * Sets the destPort value for this SendMultiple.
     * 
     * @param destPort
     */
    public void setDestPort(java.lang.String destPort) {
        this.destPort = destPort;
    }


    /**
     * Gets the messageType value for this SendMultiple.
     * 
     * @return messageType
     */
    public short getMessageType() {
        return messageType;
    }


    /**
     * Sets the messageType value for this SendMultiple.
     * 
     * @param messageType
     */
    public void setMessageType(short messageType) {
        this.messageType = messageType;
    }


    /**
     * Gets the longSupported value for this SendMultiple.
     * 
     * @return longSupported
     */
    public boolean isLongSupported() {
        return longSupported;
    }


    /**
     * Sets the longSupported value for this SendMultiple.
     * 
     * @param longSupported
     */
    public void setLongSupported(boolean longSupported) {
        this.longSupported = longSupported;
    }


    /**
     * Gets the messages value for this SendMultiple.
     * 
     * @return messages
     */
    public com.soshiant.webservice.sms.adpdigital.MultiAddressMessageObject[] getMessages() {
        return messages;
    }


    /**
     * Sets the messages value for this SendMultiple.
     * 
     * @param messages
     */
    public void setMessages(com.soshiant.webservice.sms.adpdigital.MultiAddressMessageObject[] messages) {
        this.messages = messages;
    }

    public com.soshiant.webservice.sms.adpdigital.MultiAddressMessageObject getMessages(int i) {
        return this.messages[i];
    }

    public void setMessages(int i, com.soshiant.webservice.sms.adpdigital.MultiAddressMessageObject _value) {
        this.messages[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SendMultiple)) return false;
        SendMultiple other = (SendMultiple) obj;
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
            ((this.sourcePort==null && other.getSourcePort()==null) || 
             (this.sourcePort!=null &&
              this.sourcePort.equals(other.getSourcePort()))) &&
            ((this.destPort==null && other.getDestPort()==null) || 
             (this.destPort!=null &&
              this.destPort.equals(other.getDestPort()))) &&
            this.messageType == other.getMessageType() &&
            this.longSupported == other.isLongSupported() &&
            ((this.messages==null && other.getMessages()==null) || 
             (this.messages!=null &&
              java.util.Arrays.equals(this.messages, other.getMessages())));
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
        if (getSourcePort() != null) {
            _hashCode += getSourcePort().hashCode();
        }
        if (getDestPort() != null) {
            _hashCode += getDestPort().hashCode();
        }
        _hashCode += getMessageType();
        _hashCode += (isLongSupported() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMessages() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMessages());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMessages(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SendMultiple.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", ">sendMultiple"));
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
        elemField.setFieldName("sourcePort");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "sourcePort"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destPort");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "destPort"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "messageType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("longSupported");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "longSupported"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messages");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "messages"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "MultiAddressMessageObject"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
