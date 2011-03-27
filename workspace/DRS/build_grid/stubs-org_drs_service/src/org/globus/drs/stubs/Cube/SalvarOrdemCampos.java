/**
 * SalvarOrdemCampos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.drs.stubs.Cube;

public class SalvarOrdemCampos  implements java.io.Serializable {
    private org.globus.drs.stubs.Cube.Atributo[] ordemAtributos;

    public SalvarOrdemCampos() {
    }

    public SalvarOrdemCampos(
           org.globus.drs.stubs.Cube.Atributo[] ordemAtributos) {
           this.ordemAtributos = ordemAtributos;
    }


    /**
     * Gets the ordemAtributos value for this SalvarOrdemCampos.
     * 
     * @return ordemAtributos
     */
    public org.globus.drs.stubs.Cube.Atributo[] getOrdemAtributos() {
        return ordemAtributos;
    }


    /**
     * Sets the ordemAtributos value for this SalvarOrdemCampos.
     * 
     * @param ordemAtributos
     */
    public void setOrdemAtributos(org.globus.drs.stubs.Cube.Atributo[] ordemAtributos) {
        this.ordemAtributos = ordemAtributos;
    }

    public org.globus.drs.stubs.Cube.Atributo getOrdemAtributos(int i) {
        return this.ordemAtributos[i];
    }

    public void setOrdemAtributos(int i, org.globus.drs.stubs.Cube.Atributo _value) {
        this.ordemAtributos[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalvarOrdemCampos)) return false;
        SalvarOrdemCampos other = (SalvarOrdemCampos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ordemAtributos==null && other.getOrdemAtributos()==null) || 
             (this.ordemAtributos!=null &&
              java.util.Arrays.equals(this.ordemAtributos, other.getOrdemAtributos())));
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
        if (getOrdemAtributos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOrdemAtributos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOrdemAtributos(), i);
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
        new org.apache.axis.description.TypeDesc(SalvarOrdemCampos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">salvarOrdemCampos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordemAtributos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", "ordemAtributos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">atributo"));
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
