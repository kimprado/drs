/**
 * CubeMetadataResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.cube.stubs.Cube;

public class CubeMetadataResponse  implements java.io.Serializable {
    private java.lang.String name;
    private org.globus.cube.stubs.Cube.FatoMetaData fatoMetaData;
    private org.globus.cube.stubs.Cube.CubeMetaData cubeMetaData;

    public CubeMetadataResponse() {
    }

    public CubeMetadataResponse(
           org.globus.cube.stubs.Cube.CubeMetaData cubeMetaData,
           org.globus.cube.stubs.Cube.FatoMetaData fatoMetaData,
           java.lang.String name) {
           this.name = name;
           this.fatoMetaData = fatoMetaData;
           this.cubeMetaData = cubeMetaData;
    }


    /**
     * Gets the name value for this CubeMetadataResponse.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this CubeMetadataResponse.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the fatoMetaData value for this CubeMetadataResponse.
     * 
     * @return fatoMetaData
     */
    public org.globus.cube.stubs.Cube.FatoMetaData getFatoMetaData() {
        return fatoMetaData;
    }


    /**
     * Sets the fatoMetaData value for this CubeMetadataResponse.
     * 
     * @param fatoMetaData
     */
    public void setFatoMetaData(org.globus.cube.stubs.Cube.FatoMetaData fatoMetaData) {
        this.fatoMetaData = fatoMetaData;
    }


    /**
     * Gets the cubeMetaData value for this CubeMetadataResponse.
     * 
     * @return cubeMetaData
     */
    public org.globus.cube.stubs.Cube.CubeMetaData getCubeMetaData() {
        return cubeMetaData;
    }


    /**
     * Sets the cubeMetaData value for this CubeMetadataResponse.
     * 
     * @param cubeMetaData
     */
    public void setCubeMetaData(org.globus.cube.stubs.Cube.CubeMetaData cubeMetaData) {
        this.cubeMetaData = cubeMetaData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CubeMetadataResponse)) return false;
        CubeMetadataResponse other = (CubeMetadataResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.fatoMetaData==null && other.getFatoMetaData()==null) || 
             (this.fatoMetaData!=null &&
              this.fatoMetaData.equals(other.getFatoMetaData()))) &&
            ((this.cubeMetaData==null && other.getCubeMetaData()==null) || 
             (this.cubeMetaData!=null &&
              this.cubeMetaData.equals(other.getCubeMetaData())));
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
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getFatoMetaData() != null) {
            _hashCode += getFatoMetaData().hashCode();
        }
        if (getCubeMetaData() != null) {
            _hashCode += getCubeMetaData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CubeMetadataResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">cubeMetadataResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fatoMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", "fatoMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">fatoMetaData"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cubeMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", "cubeMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">cubeMetaData"));
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
