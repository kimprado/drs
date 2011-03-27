/**
 * GetResumo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.drs.stubs.Cube;

public class GetResumo  implements java.io.Serializable {
    private int consulta;
    private int cubo;
    private int tabela;

    public GetResumo() {
    }

    public GetResumo(
           int consulta,
           int cubo,
           int tabela) {
           this.consulta = consulta;
           this.cubo = cubo;
           this.tabela = tabela;
    }


    /**
     * Gets the consulta value for this GetResumo.
     * 
     * @return consulta
     */
    public int getConsulta() {
        return consulta;
    }


    /**
     * Sets the consulta value for this GetResumo.
     * 
     * @param consulta
     */
    public void setConsulta(int consulta) {
        this.consulta = consulta;
    }


    /**
     * Gets the cubo value for this GetResumo.
     * 
     * @return cubo
     */
    public int getCubo() {
        return cubo;
    }


    /**
     * Sets the cubo value for this GetResumo.
     * 
     * @param cubo
     */
    public void setCubo(int cubo) {
        this.cubo = cubo;
    }


    /**
     * Gets the tabela value for this GetResumo.
     * 
     * @return tabela
     */
    public int getTabela() {
        return tabela;
    }


    /**
     * Sets the tabela value for this GetResumo.
     * 
     * @param tabela
     */
    public void setTabela(int tabela) {
        this.tabela = tabela;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetResumo)) return false;
        GetResumo other = (GetResumo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.consulta == other.getConsulta() &&
            this.cubo == other.getCubo() &&
            this.tabela == other.getTabela();
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
        _hashCode += getConsulta();
        _hashCode += getCubo();
        _hashCode += getTabela();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetResumo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">getResumo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consulta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cubo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cubo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tabela");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tabela"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
