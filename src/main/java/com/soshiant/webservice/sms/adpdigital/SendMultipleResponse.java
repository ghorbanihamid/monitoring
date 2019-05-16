/**
 * SendMultipleResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soshiant.webservice.sms.adpdigital;

public class SendMultipleResponse  implements java.io.Serializable {
    private com.soshiant.webservice.sms.adpdigital.SendResult sendMultipleReturn;

    public SendMultipleResponse() {
    }

    public SendMultipleResponse(
           com.soshiant.webservice.sms.adpdigital.SendResult sendMultipleReturn) {
           this.sendMultipleReturn = sendMultipleReturn;
    }


    /**
     * Gets the sendMultipleReturn value for this SendMultipleResponse.
     * 
     * @return sendMultipleReturn
     */
    public com.soshiant.webservice.sms.adpdigital.SendResult getSendMultipleReturn() {
        return sendMultipleReturn;
    }


    /**
     * Sets the sendMultipleReturn value for this SendMultipleResponse.
     * 
     * @param sendMultipleReturn
     */
    public void setSendMultipleReturn(com.soshiant.webservice.sms.adpdigital.SendResult sendMultipleReturn) {
        this.sendMultipleReturn = sendMultipleReturn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SendMultipleResponse)) return false;
        SendMultipleResponse other = (SendMultipleResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sendMultipleReturn==null && other.getSendMultipleReturn()==null) || 
             (this.sendMultipleReturn!=null &&
              this.sendMultipleReturn.equals(other.getSendMultipleReturn())));
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
        if (getSendMultipleReturn() != null) {
            _hashCode += getSendMultipleReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SendMultipleResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", ">sendMultipleResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendMultipleReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "sendMultipleReturn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "SendResult"));
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
