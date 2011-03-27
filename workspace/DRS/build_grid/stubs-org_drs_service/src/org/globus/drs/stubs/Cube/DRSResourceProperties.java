/**
 * DRSResourceProperties.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.drs.stubs.Cube;

public class DRSResourceProperties  implements java.io.Serializable {
    private java.lang.String cubeURI;

    public DRSResourceProperties() {
    }

    public DRSResourceProperties(
           java.lang.String cubeURI) {
           this.cubeURI = cubeURI;
    }


    /**
     * Gets the cubeURI value for this DRSResourceProperties.
     * 
     * @return cubeURI
     */
    public java.lang.String getCubeURI() {
        return cubeURI;
    }


    /**
     * Sets the cubeURI value for this DRSResourceProperties.
     * 
     * @param cubeURI
     */
    public void setCubeURI(java.lang.String cubeURI) {
        this.cubeURI = cubeURI;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DRSResourceProperties)) return false;
        DRSResourceProperties other = (DRSResourceProperties) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cubeURI==null && other.getCubeURI()==null) || 
             (this.cubeURI!=null &&
              this.cubeURI.equals(other.getCubeURI())));
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
        if (getCubeURI() != null) {
            _hashCode += getCubeURI().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DRSResourceProperties.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">DRSResourceProperties"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cubeURI");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", "cubeURI"));
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
