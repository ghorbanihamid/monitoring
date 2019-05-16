/**
 * StatusReportType0.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soshiant.webservice.sms.adpdigital;

public class StatusReportType0  implements java.io.Serializable {
    private java.lang.String userName;

    private java.lang.String password;

    private java.lang.String shortNumber;

    private java.lang.String type;

    private long[] ids;

    private java.lang.String[] clientIds;

    public StatusReportType0() {
    }

    public StatusReportType0(
           java.lang.String userName,
           java.lang.String password,
           java.lang.String shortNumber,
           java.lang.String type,
           long[] ids,
           java.lang.String[] clientIds) {
           this.userName = userName;
           this.password = password;
           this.shortNumber = shortNumber;
           this.type = type;
           this.ids = ids;
           this.clientIds = clientIds;
    }


    /**
     * Gets the userName value for this StatusReportType0.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this StatusReportType0.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the password value for this StatusReportType0.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this StatusReportType0.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the shortNumber value for this StatusReportType0.
     * 
     * @return shortNumber
     */
    public java.lang.String getShortNumber() {
        return shortNumber;
    }


    /**
     * Sets the shortNumber value for this StatusReportType0.
     * 
     * @param shortNumber
     */
    public void setShortNumber(java.lang.String shortNumber) {
        this.shortNumber = shortNumber;
    }


    /**
     * Gets the type value for this StatusReportType0.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this StatusReportType0.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the ids value for this StatusReportType0.
     * 
     * @return ids
     */
    public long[] getIds() {
        return ids;
    }


    /**
     * Sets the ids value for this StatusReportType0.
     * 
     * @param ids
     */
    public void setIds(long[] ids) {
        this.ids = ids;
    }

    public long getIds(int i) {
        return this.ids[i];
    }

    public void setIds(int i, long _value) {
        this.ids[i] = _value;
    }


    /**
     * Gets the clientIds value for this StatusReportType0.
     * 
     * @return clientIds
     */
    public java.lang.String[] getClientIds() {
        return clientIds;
    }


    /**
     * Sets the clientIds value for this StatusReportType0.
     * 
     * @param clientIds
     */
    public void setClientIds(java.lang.String[] clientIds) {
        this.clientIds = clientIds;
    }

    public java.lang.String getClientIds(int i) {
        return this.clientIds[i];
    }

    public void setClientIds(int i, java.lang.String _value) {
        this.clientIds[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatusReportType0)) return false;
        StatusReportType0 other = (StatusReportType0) obj;
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
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.ids==null && other.getIds()==null) || 
             (this.ids!=null &&
              java.util.Arrays.equals(this.ids, other.getIds()))) &&
            ((this.clientIds==null && other.getClientIds()==null) || 
             (this.clientIds!=null &&
              java.util.Arrays.equals(this.clientIds, other.getClientIds())));
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
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIds(), i);
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatusReportType0.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", ">statusReport"));
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
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ids");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "ids"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientIds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "clientIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
