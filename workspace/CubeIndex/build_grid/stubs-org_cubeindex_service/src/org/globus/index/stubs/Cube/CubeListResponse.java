/**
 * CubeListResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.index.stubs.Cube;

public class CubeListResponse  implements java.io.Serializable {
    private org.globus.index.stubs.Cube.CubeEntry[] cubeEntry;

    public CubeListResponse() {
    }

    public CubeListResponse(
           org.globus.index.stubs.Cube.CubeEntry[] cubeEntry) {
           this.cubeEntry = cubeEntry;
    }


    /**
     * Gets the cubeEntry value for this CubeListResponse.
     * 
     * @return cubeEntry
     */
    public org.globus.index.stubs.Cube.CubeEntry[] getCubeEntry() {
        return cubeEntry;
    }


    /**
     * Sets the cubeEntry value for this CubeListResponse.
     * 
     * @param cubeEntry
     */
    public void setCubeEntry(org.globus.index.stubs.Cube.CubeEntry[] cubeEntry) {
        this.cubeEntry = cubeEntry;
    }

    public org.globus.index.stubs.Cube.CubeEntry getCubeEntry(int i) {
        return this.cubeEntry[i];
    }

    public void setCubeEntry(int i, org.globus.index.stubs.Cube.CubeEntry _value) {
        this.cubeEntry[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CubeListResponse)) return false;
        CubeListResponse other = (CubeListResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cubeEntry==null && other.getCubeEntry()==null) || 
             (this.cubeEntry!=null &&
              java.util.Arrays.equals(this.cubeEntry, other.getCubeEntry())));
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
        if (getCubeEntry() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCubeEntry());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCubeEntry(), i);
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
        new org.apache.axis.description.TypeDesc(CubeListResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">cubeListResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cubeEntry");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", "cubeEntry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">cubeEntry"));
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
