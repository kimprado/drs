/**
 * ExecuteQueryResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.cube.stubs.Cube;

public class ExecuteQueryResponse  implements java.io.Serializable {
    private org.globus.cube.stubs.Cube.ColumnResponse[] columnResponse;
    private java.lang.String[] resultColumnName;

    public ExecuteQueryResponse() {
    }

    public ExecuteQueryResponse(
           org.globus.cube.stubs.Cube.ColumnResponse[] columnResponse,
           java.lang.String[] resultColumnName) {
           this.columnResponse = columnResponse;
           this.resultColumnName = resultColumnName;
    }


    /**
     * Gets the columnResponse value for this ExecuteQueryResponse.
     * 
     * @return columnResponse
     */
    public org.globus.cube.stubs.Cube.ColumnResponse[] getColumnResponse() {
        return columnResponse;
    }


    /**
     * Sets the columnResponse value for this ExecuteQueryResponse.
     * 
     * @param columnResponse
     */
    public void setColumnResponse(org.globus.cube.stubs.Cube.ColumnResponse[] columnResponse) {
        this.columnResponse = columnResponse;
    }

    public org.globus.cube.stubs.Cube.ColumnResponse getColumnResponse(int i) {
        return this.columnResponse[i];
    }

    public void setColumnResponse(int i, org.globus.cube.stubs.Cube.ColumnResponse _value) {
        this.columnResponse[i] = _value;
    }


    /**
     * Gets the resultColumnName value for this ExecuteQueryResponse.
     * 
     * @return resultColumnName
     */
    public java.lang.String[] getResultColumnName() {
        return resultColumnName;
    }


    /**
     * Sets the resultColumnName value for this ExecuteQueryResponse.
     * 
     * @param resultColumnName
     */
    public void setResultColumnName(java.lang.String[] resultColumnName) {
        this.resultColumnName = resultColumnName;
    }

    public java.lang.String getResultColumnName(int i) {
        return this.resultColumnName[i];
    }

    public void setResultColumnName(int i, java.lang.String _value) {
        this.resultColumnName[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExecuteQueryResponse)) return false;
        ExecuteQueryResponse other = (ExecuteQueryResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.columnResponse==null && other.getColumnResponse()==null) || 
             (this.columnResponse!=null &&
              java.util.Arrays.equals(this.columnResponse, other.getColumnResponse()))) &&
            ((this.resultColumnName==null && other.getResultColumnName()==null) || 
             (this.resultColumnName!=null &&
              java.util.Arrays.equals(this.resultColumnName, other.getResultColumnName())));
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
        if (getColumnResponse() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getColumnResponse());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getColumnResponse(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getResultColumnName() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultColumnName());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultColumnName(), i);
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
        new org.apache.axis.description.TypeDesc(ExecuteQueryResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">executeQueryResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("columnResponse");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", "columnResponse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">columnResponse"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultColumnName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "resultColumnName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
