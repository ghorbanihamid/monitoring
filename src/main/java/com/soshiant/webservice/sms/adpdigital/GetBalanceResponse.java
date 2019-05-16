/**
 * GetBalanceResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soshiant.webservice.sms.adpdigital;

public class GetBalanceResponse  implements java.io.Serializable {
    private com.soshiant.webservice.sms.adpdigital.BalanceResult getBalanceReturn;

    public GetBalanceResponse() {
    }

    public GetBalanceResponse(
           com.soshiant.webservice.sms.adpdigital.BalanceResult getBalanceReturn) {
           this.getBalanceReturn = getBalanceReturn;
    }


    /**
     * Gets the getBalanceReturn value for this GetBalanceResponse.
     * 
     * @return getBalanceReturn
     */
    public com.soshiant.webservice.sms.adpdigital.BalanceResult getGetBalanceReturn() {
        return getBalanceReturn;
    }


    /**
     * Sets the getBalanceReturn value for this GetBalanceResponse.
     * 
     * @param getBalanceReturn
     */
    public void setGetBalanceReturn(com.soshiant.webservice.sms.adpdigital.BalanceResult getBalanceReturn) {
        this.getBalanceReturn = getBalanceReturn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetBalanceResponse)) return false;
        GetBalanceResponse other = (GetBalanceResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getBalanceReturn==null && other.getGetBalanceReturn()==null) || 
             (this.getBalanceReturn!=null &&
              this.getBalanceReturn.equals(other.getGetBalanceReturn())));
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
        if (getGetBalanceReturn() != null) {
            _hashCode += getGetBalanceReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetBalanceResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", ">getBalanceResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getBalanceReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "getBalanceReturn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "BalanceResult"));
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
