/**
 * SetChavePrimaria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.cube.stubs.Cube;

public class SetChavePrimaria  implements java.io.Serializable {
    private int cube;
    private org.globus.cube.stubs.Cube.FatoMetaData fatoMetaData;

    public SetChavePrimaria() {
    }

    public SetChavePrimaria(
           int cube,
           org.globus.cube.stubs.Cube.FatoMetaData fatoMetaData) {
           this.cube = cube;
           this.fatoMetaData = fatoMetaData;
    }


    /**
     * Gets the cube value for this SetChavePrimaria.
     * 
     * @return cube
     */
    public int getCube() {
        return cube;
    }


    /**
     * Sets the cube value for this SetChavePrimaria.
     * 
     * @param cube
     */
    public void setCube(int cube) {
        this.cube = cube;
    }


    /**
     * Gets the fatoMetaData value for this SetChavePrimaria.
     * 
     * @return fatoMetaData
     */
    public org.globus.cube.stubs.Cube.FatoMetaData getFatoMetaData() {
        return fatoMetaData;
    }


    /**
     * Sets the fatoMetaData value for this SetChavePrimaria.
     * 
     * @param fatoMetaData
     */
    public void setFatoMetaData(org.globus.cube.stubs.Cube.FatoMetaData fatoMetaData) {
        this.fatoMetaData = fatoMetaData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetChavePrimaria)) return false;
        SetChavePrimaria other = (SetChavePrimaria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.cube == other.getCube() &&
            ((this.fatoMetaData==null && other.getFatoMetaData()==null) || 
             (this.fatoMetaData!=null &&
              this.fatoMetaData.equals(other.getFatoMetaData())));
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
        _hashCode += getCube();
        if (getFatoMetaData() != null) {
            _hashCode += getFatoMetaData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetChavePrimaria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">setChavePrimaria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cube");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cube"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fatoMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", "fatoMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">fatoMetaData"));
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
