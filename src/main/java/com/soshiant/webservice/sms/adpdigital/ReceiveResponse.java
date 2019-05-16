/**
 * ReceiveResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soshiant.webservice.sms.adpdigital;

public class ReceiveResponse  implements java.io.Serializable {
    private com.soshiant.webservice.sms.adpdigital.ReceiveResult receiveReturn;

    public ReceiveResponse() {
    }

    public ReceiveResponse(
           com.soshiant.webservice.sms.adpdigital.ReceiveResult receiveReturn) {
           this.receiveReturn = receiveReturn;
    }


    /**
     * Gets the receiveReturn value for this ReceiveResponse.
     * 
     * @return receiveReturn
     */
    public com.soshiant.webservice.sms.adpdigital.ReceiveResult getReceiveReturn() {
        return receiveReturn;
    }


    /**
     * Sets the receiveReturn value for this ReceiveResponse.
     * 
     * @param receiveReturn
     */
    public void setReceiveReturn(com.soshiant.webservice.sms.adpdigital.ReceiveResult receiveReturn) {
        this.receiveReturn = receiveReturn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReceiveResponse)) return false;
        ReceiveResponse other = (ReceiveResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.receiveReturn==null && other.getReceiveReturn()==null) || 
             (this.receiveReturn!=null &&
              this.receiveReturn.equals(other.getReceiveReturn())));
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
        if (getReceiveReturn() != null) {
            _hashCode += getReceiveReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReceiveResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", ">receiveResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiveReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "receiveReturn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "ReceiveResult"));
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
