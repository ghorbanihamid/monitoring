/**
 * MultiAddressMessageObject.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soshiant.webservice.sms.adpdigital;

public class MultiAddressMessageObject  implements java.io.Serializable {
    private java.lang.String content;

    private short encoding;

    private java.lang.String[] phoneNumbers;

    private java.lang.String[] clientIds;

    private java.util.Calendar dueTime;

    public MultiAddressMessageObject() {
    }

    public MultiAddressMessageObject(
           java.lang.String content,
           short encoding,
           java.lang.String[] phoneNumbers,
           java.lang.String[] clientIds,
           java.util.Calendar dueTime) {
           this.content = content;
           this.encoding = encoding;
           this.phoneNumbers = phoneNumbers;
           this.clientIds = clientIds;
           this.dueTime = dueTime;
    }


    /**
     * Gets the content value for this MultiAddressMessageObject.
     * 
     * @return content
     */
    public java.lang.String getContent() {
        return content;
    }


    /**
     * Sets the content value for this MultiAddressMessageObject.
     * 
     * @param content
     */
    public void setContent(java.lang.String content) {
        this.content = content;
    }


    /**
     * Gets the encoding value for this MultiAddressMessageObject.
     * 
     * @return encoding
     */
    public short getEncoding() {
        return encoding;
    }


    /**
     * Sets the encoding value for this MultiAddressMessageObject.
     * 
     * @param encoding
     */
    public void setEncoding(short encoding) {
        this.encoding = encoding;
    }


    /**
     * Gets the phoneNumbers value for this MultiAddressMessageObject.
     * 
     * @return phoneNumbers
     */
    public java.lang.String[] getPhoneNumbers() {
        return phoneNumbers;
    }


    /**
     * Sets the phoneNumbers value for this MultiAddressMessageObject.
     * 
     * @param phoneNumbers
     */
    public void setPhoneNumbers(java.lang.String[] phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }


    /**
     * Gets the clientIds value for this MultiAddressMessageObject.
     * 
     * @return clientIds
     */
    public java.lang.String[] getClientIds() {
        return clientIds;
    }


    /**
     * Sets the clientIds value for this MultiAddressMessageObject.
     * 
     * @param clientIds
     */
    public void setClientIds(java.lang.String[] clientIds) {
        this.clientIds = clientIds;
    }


    /**
     * Gets the dueTime value for this MultiAddressMessageObject.
     * 
     * @return dueTime
     */
    public java.util.Calendar getDueTime() {
        return dueTime;
    }


    /**
     * Sets the dueTime value for this MultiAddressMessageObject.
     * 
     * @param dueTime
     */
    public void setDueTime(java.util.Calendar dueTime) {
        this.dueTime = dueTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MultiAddressMessageObject)) return false;
        MultiAddressMessageObject other = (MultiAddressMessageObject) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.content==null && other.getContent()==null) || 
             (this.content!=null &&
              this.content.equals(other.getContent()))) &&
            this.encoding == other.getEncoding() &&
            ((this.phoneNumbers==null && other.getPhoneNumbers()==null) || 
             (this.phoneNumbers!=null &&
              java.util.Arrays.equals(this.phoneNumbers, other.getPhoneNumbers()))) &&
            ((this.clientIds==null && other.getClientIds()==null) || 
             (this.clientIds!=null &&
              java.util.Arrays.equals(this.clientIds, other.getClientIds()))) &&
            ((this.dueTime==null && other.getDueTime()==null) || 
             (this.dueTime!=null &&
              this.dueTime.equals(other.getDueTime())));
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
        if (getContent() != null) {
            _hashCode += getContent().hashCode();
        }
        _hashCode += getEncoding();
        if (getPhoneNumbers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPhoneNumbers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPhoneNumbers(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getClientIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getClientIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getClientIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDueTime() != null) {
            _hashCode += getDueTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MultiAddressMessageObject.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "MultiAddressMessageObject"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("content");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "content"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("encoding");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "encoding"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phoneNumbers");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "phoneNumbers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientIds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "clientIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dueTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "dueTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
