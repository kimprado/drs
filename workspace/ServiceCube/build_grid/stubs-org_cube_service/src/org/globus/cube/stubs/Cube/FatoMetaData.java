/**
 * FatoMetaData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.cube.stubs.Cube;

public class FatoMetaData  implements java.io.Serializable {
    private java.lang.String name;
    private org.globus.cube.stubs.Cube.FieldMetaData[] fieldMetaData;
    private org.globus.cube.stubs.Cube.DimensaoMetaData[] dimensaoMetaData;

    public FatoMetaData() {
    }

    public FatoMetaData(
           org.globus.cube.stubs.Cube.DimensaoMetaData[] dimensaoMetaData,
           org.globus.cube.stubs.Cube.FieldMetaData[] fieldMetaData,
           java.lang.String name) {
           this.name = name;
           this.fieldMetaData = fieldMetaData;
           this.dimensaoMetaData = dimensaoMetaData;
    }


    /**
     * Gets the name value for this FatoMetaData.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this FatoMetaData.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the fieldMetaData value for this FatoMetaData.
     * 
     * @return fieldMetaData
     */
    public org.globus.cube.stubs.Cube.FieldMetaData[] getFieldMetaData() {
        return fieldMetaData;
    }


    /**
     * Sets the fieldMetaData value for this FatoMetaData.
     * 
     * @param fieldMetaData
     */
    public void setFieldMetaData(org.globus.cube.stubs.Cube.FieldMetaData[] fieldMetaData) {
        this.fieldMetaData = fieldMetaData;
    }

    public org.globus.cube.stubs.Cube.FieldMetaData getFieldMetaData(int i) {
        return this.fieldMetaData[i];
    }

    public void setFieldMetaData(int i, org.globus.cube.stubs.Cube.FieldMetaData _value) {
        this.fieldMetaData[i] = _value;
    }


    /**
     * Gets the dimensaoMetaData value for this FatoMetaData.
     * 
     * @return dimensaoMetaData
     */
    public org.globus.cube.stubs.Cube.DimensaoMetaData[] getDimensaoMetaData() {
        return dimensaoMetaData;
    }


    /**
     * Sets the dimensaoMetaData value for this FatoMetaData.
     * 
     * @param dimensaoMetaData
     */
    public void setDimensaoMetaData(org.globus.cube.stubs.Cube.DimensaoMetaData[] dimensaoMetaData) {
        this.dimensaoMetaData = dimensaoMetaData;
    }

    public org.globus.cube.stubs.Cube.DimensaoMetaData getDimensaoMetaData(int i) {
        return this.dimensaoMetaData[i];
    }

    public void setDimensaoMetaData(int i, org.globus.cube.stubs.Cube.DimensaoMetaData _value) {
        this.dimensaoMetaData[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FatoMetaData)) return false;
        FatoMetaData other = (FatoMetaData) obj;
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
            ((this.fieldMetaData==null && other.getFieldMetaData()==null) || 
             (this.fieldMetaData!=null &&
              java.util.Arrays.equals(this.fieldMetaData, other.getFieldMetaData()))) &&
            ((this.dimensaoMetaData==null && other.getDimensaoMetaData()==null) || 
             (this.dimensaoMetaData!=null &&
              java.util.Arrays.equals(this.dimensaoMetaData, other.getDimensaoMetaData())));
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
        if (getFieldMetaData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFieldMetaData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFieldMetaData(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDimensaoMetaData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDimensaoMetaData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDimensaoMetaData(), i);
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
        new org.apache.axis.description.TypeDesc(FatoMetaData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">fatoMetaData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", "fieldMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">fieldMetaData"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dimensaoMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", "dimensaoMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">dimensaoMetaData"));
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
