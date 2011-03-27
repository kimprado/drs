/**
 * CubeCollResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.cube.stubs.Cube;

public class CubeCollResponse  implements java.io.Serializable {
    private java.lang.String[] cubeName;
    private java.lang.String[] cubeIndex;
    private java.lang.String[] cubeServer;

    public CubeCollResponse() {
    }

    public CubeCollResponse(
           java.lang.String[] cubeIndex,
           java.lang.String[] cubeName,
           java.lang.String[] cubeServer) {
           this.cubeName = cubeName;
           this.cubeIndex = cubeIndex;
           this.cubeServer = cubeServer;
    }


    /**
     * Gets the cubeName value for this CubeCollResponse.
     * 
     * @return cubeName
     */
    public java.lang.String[] getCubeName() {
        return cubeName;
    }


    /**
     * Sets the cubeName value for this CubeCollResponse.
     * 
     * @param cubeName
     */
    public void setCubeName(java.lang.String[] cubeName) {
        this.cubeName = cubeName;
    }

    public java.lang.String getCubeName(int i) {
        return this.cubeName[i];
    }

    public void setCubeName(int i, java.lang.String _value) {
        this.cubeName[i] = _value;
    }


    /**
     * Gets the cubeIndex value for this CubeCollResponse.
     * 
     * @return cubeIndex
     */
    public java.lang.String[] getCubeIndex() {
        return cubeIndex;
    }


    /**
     * Sets the cubeIndex value for this CubeCollResponse.
     * 
     * @param cubeIndex
     */
    public void setCubeIndex(java.lang.String[] cubeIndex) {
        this.cubeIndex = cubeIndex;
    }

    public java.lang.String getCubeIndex(int i) {
        return this.cubeIndex[i];
    }

    public void setCubeIndex(int i, java.lang.String _value) {
        this.cubeIndex[i] = _value;
    }


    /**
     * Gets the cubeServer value for this CubeCollResponse.
     * 
     * @return cubeServer
     */
    public java.lang.String[] getCubeServer() {
        return cubeServer;
    }


    /**
     * Sets the cubeServer value for this CubeCollResponse.
     * 
     * @param cubeServer
     */
    public void setCubeServer(java.lang.String[] cubeServer) {
        this.cubeServer = cubeServer;
    }

    public java.lang.String getCubeServer(int i) {
        return this.cubeServer[i];
    }

    public void setCubeServer(int i, java.lang.String _value) {
        this.cubeServer[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CubeCollResponse)) return false;
        CubeCollResponse other = (CubeCollResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cubeName==null && other.getCubeName()==null) || 
             (this.cubeName!=null &&
              java.util.Arrays.equals(this.cubeName, other.getCubeName()))) &&
            ((this.cubeIndex==null && other.getCubeIndex()==null) || 
             (this.cubeIndex!=null &&
              java.util.Arrays.equals(this.cubeIndex, other.getCubeIndex()))) &&
            ((this.cubeServer==null && other.getCubeServer()==null) || 
             (this.cubeServer!=null &&
              java.util.Arrays.equals(this.cubeServer, other.getCubeServer())));
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
        if (getCubeName() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCubeName());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCubeName(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCubeIndex() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCubeIndex());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCubeIndex(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCubeServer() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCubeServer());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCubeServer(), i);
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
        new org.apache.axis.description.TypeDesc(CubeCollResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">cubeCollResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cubeName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cubeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cubeIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cubeIndex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cubeServer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cubeServer"));
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
