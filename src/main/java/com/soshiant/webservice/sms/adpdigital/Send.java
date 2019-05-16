/**
 * Send.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soshiant.webservice.sms.adpdigital;

public class Send  implements java.io.Serializable {
    private java.lang.String userName;

    private java.lang.String password;

    private java.lang.String sourceNo;

    private java.lang.String[] destNo;

    private java.lang.String sourcePort;

    private java.lang.String destPort;

    private java.lang.String[] clientId;

    private short messageType;

    private short encoding;

    private boolean longSupported;

    private java.util.Calendar dueTime;

    private java.lang.String content;

    public Send() {
    }

    public Send(
           java.lang.String userName,
           java.lang.String password,
           java.lang.String sourceNo,
           java.lang.String[] destNo,
           java.lang.String sourcePort,
           java.lang.String destPort,
           java.lang.String[] clientId,
           short messageType,
           short encoding,
           boolean longSupported,
           java.util.Calendar dueTime,
           java.lang.String content) {
           this.userName = userName;
           this.password = password;
           this.sourceNo = sourceNo;
           this.destNo = destNo;
           this.sourcePort = sourcePort;
           this.destPort = destPort;
           this.clientId = clientId;
           this.messageType = messageType;
           this.encoding = encoding;
           this.longSupported = longSupported;
           this.dueTime = dueTime;
           this.content = content;
    }


    /**
     * Gets the userName value for this Send.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this Send.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the password value for this Send.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this Send.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the sourceNo value for this Send.
     * 
     * @return sourceNo
     */
    public java.lang.String getSourceNo() {
        return sourceNo;
    }


    /**
     * Sets the sourceNo value for this Send.
     * 
     * @param sourceNo
     */
    public void setSourceNo(java.lang.String sourceNo) {
        this.sourceNo = sourceNo;
    }


    /**
     * Gets the destNo value for this Send.
     * 
     * @return destNo
     */
    public java.lang.String[] getDestNo() {
        return destNo;
    }


    /**
     * Sets the destNo value for this Send.
     * 
     * @param destNo
     */
    public void setDestNo(java.lang.String[] destNo) {
        this.destNo = destNo;
    }

    public java.lang.String getDestNo(int i) {
        return this.destNo[i];
    }

    public void setDestNo(int i, java.lang.String _value) {
        this.destNo[i] = _value;
    }


    /**
     * Gets the sourcePort value for this Send.
     * 
     * @return sourcePort
     */
    public java.lang.String getSourcePort() {
        return sourcePort;
    }


    /**
     * Sets the sourcePort value for this Send.
     * 
     * @param sourcePort
     */
    public void setSourcePort(java.lang.String sourcePort) {
        this.sourcePort = sourcePort;
    }


    /**
     * Gets the destPort value for this Send.
     * 
     * @return destPort
     */
    public java.lang.String getDestPort() {
        return destPort;
    }


    /**
     * Sets the destPort value for this Send.
     * 
     * @param destPort
     */
    public void setDestPort(java.lang.String destPort) {
        this.destPort = destPort;
    }


    /**
     * Gets the clientId value for this Send.
     * 
     * @return clientId
     */
    public java.lang.String[] getClientId() {
        return clientId;
    }


    /**
     * Sets the clientId value for this Send.
     * 
     * @param clientId
     */
    public void setClientId(java.lang.String[] clientId) {
        this.clientId = clientId;
    }

    public java.lang.String getClientId(int i) {
        return this.clientId[i];
    }

    public void setClientId(int i, java.lang.String _value) {
        this.clientId[i] = _value;
    }


    /**
     * Gets the messageType value for this Send.
     * 
     * @return messageType
     */
    public short getMessageType() {
        return messageType;
    }


    /**
     * Sets the messageType value for this Send.
     * 
     * @param messageType
     */
    public void setMessageType(short messageType) {
        this.messageType = messageType;
    }


    /**
     * Gets the encoding value for this Send.
     * 
     * @return encoding
     */
    public short getEncoding() {
        return encoding;
    }


    /**
     * Sets the encoding value for this Send.
     * 
     * @param encoding
     */
    public void setEncoding(short encoding) {
        this.encoding = encoding;
    }


    /**
     * Gets the longSupported value for this Send.
     * 
     * @return longSupported
     */
    public boolean isLongSupported() {
        return longSupported;
    }


    /**
     * Sets the longSupported value for this Send.
     * 
     * @param longSupported
     */
    public void setLongSupported(boolean longSupported) {
        this.longSupported = longSupported;
    }


    /**
     * Gets the dueTime value for this Send.
     * 
     * @return dueTime
     */
    public java.util.Calendar getDueTime() {
        return dueTime;
    }


    /**
     * Sets the dueTime value for this Send.
     * 
     * @param dueTime
     */
    public void setDueTime(java.util.Calendar dueTime) {
        this.dueTime = dueTime;
    }


    /**
     * Gets the content value for this Send.
     * 
     * @return content
     */
    public java.lang.String getContent() {
        return content;
    }


    /**
     * Sets the content value for this Send.
     * 
     * @param content
     */
    public void setContent(java.lang.String content) {
        this.content = content;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Send)) return false;
        Send other = (Send) obj;
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
            ((this.sourceNo==null && other.getSourceNo()==null) || 
             (this.sourceNo!=null &&
              this.sourceNo.equals(other.getSourceNo()))) &&
            ((this.destNo==null && other.getDestNo()==null) || 
             (this.destNo!=null &&
              java.util.Arrays.equals(this.destNo, other.getDestNo()))) &&
            ((this.sourcePort==null && other.getSourcePort()==null) || 
             (this.sourcePort!=null &&
              this.sourcePort.equals(other.getSourcePort()))) &&
            ((this.destPort==null && other.getDestPort()==null) || 
             (this.destPort!=null &&
              this.destPort.equals(other.getDestPort()))) &&
            ((this.clientId==null && other.getClientId()==null) || 
             (this.clientId!=null &&
              java.util.Arrays.equals(this.clientId, other.getClientId()))) &&
            this.messageType == other.getMessageType() &&
            this.encoding == other.getEncoding() &&
            this.longSupported == other.isLongSupported() &&
            ((this.dueTime==null && other.getDueTime()==null) || 
             (this.dueTime!=null &&
              this.dueTime.equals(other.getDueTime()))) &&
            ((this.content==null && other.getContent()==null) || 
             (this.content!=null &&
              this.content.equals(other.getContent())));
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
        if (getSourceNo() != null) {
            _hashCode += getSourceNo().hashCode();
        }
        if (getDestNo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDestNo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDestNo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSourcePort() != null) {
            _hashCode += getSourcePort().hashCode();
        }
        if (getDestPort() != null) {
            _hashCode += getDestPort().hashCode();
        }
        if (getClientId() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getClientId());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getClientId(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getMessageType();
        _hashCode += getEncoding();
        _hashCode += (isLongSupported() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getDueTime() != null) {
            _hashCode += getDueTime().hashCode();
        }
        if (getContent() != null) {
            _hashCode += getContent().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Send.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", ">send"));
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
        elemField.setFieldName("sourceNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "sourceNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "destNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
        elemField.setFieldName("clientId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "clientId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "messageType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("encoding");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "encoding"));
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
        elemField.setFieldName("dueTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "dueTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("content");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.adpdigital.com/services/messaging", "content"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
