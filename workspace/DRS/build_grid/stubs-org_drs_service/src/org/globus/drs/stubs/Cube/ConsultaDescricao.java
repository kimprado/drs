/**
 * ConsultaDescricao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.drs.stubs.Cube;

public class ConsultaDescricao  implements java.io.Serializable {
    private int cubeEntry;
    private int consulta;
    private java.lang.String uri;
    private int cubo;
    private int tabela;

    public ConsultaDescricao() {
    }

    public ConsultaDescricao(
           int consulta,
           int cubeEntry,
           int cubo,
           int tabela,
           java.lang.String uri) {
           this.cubeEntry = cubeEntry;
           this.consulta = consulta;
           this.uri = uri;
           this.cubo = cubo;
           this.tabela = tabela;
    }


    /**
     * Gets the cubeEntry value for this ConsultaDescricao.
     * 
     * @return cubeEntry
     */
    public int getCubeEntry() {
        return cubeEntry;
    }


    /**
     * Sets the cubeEntry value for this ConsultaDescricao.
     * 
     * @param cubeEntry
     */
    public void setCubeEntry(int cubeEntry) {
        this.cubeEntry = cubeEntry;
    }


    /**
     * Gets the consulta value for this ConsultaDescricao.
     * 
     * @return consulta
     */
    public int getConsulta() {
        return consulta;
    }


    /**
     * Sets the consulta value for this ConsultaDescricao.
     * 
     * @param consulta
     */
    public void setConsulta(int consulta) {
        this.consulta = consulta;
    }


    /**
     * Gets the uri value for this ConsultaDescricao.
     * 
     * @return uri
     */
    public java.lang.String getUri() {
        return uri;
    }


    /**
     * Sets the uri value for this ConsultaDescricao.
     * 
     * @param uri
     */
    public void setUri(java.lang.String uri) {
        this.uri = uri;
    }


    /**
     * Gets the cubo value for this ConsultaDescricao.
     * 
     * @return cubo
     */
    public int getCubo() {
        return cubo;
    }


    /**
     * Sets the cubo value for this ConsultaDescricao.
     * 
     * @param cubo
     */
    public void setCubo(int cubo) {
        this.cubo = cubo;
    }


    /**
     * Gets the tabela value for this ConsultaDescricao.
     * 
     * @return tabela
     */
    public int getTabela() {
        return tabela;
    }


    /**
     * Sets the tabela value for this ConsultaDescricao.
     * 
     * @param tabela
     */
    public void setTabela(int tabela) {
        this.tabela = tabela;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaDescricao)) return false;
        ConsultaDescricao other = (ConsultaDescricao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.cubeEntry == other.getCubeEntry() &&
            this.consulta == other.getConsulta() &&
            ((this.uri==null && other.getUri()==null) || 
             (this.uri!=null &&
              this.uri.equals(other.getUri()))) &&
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
        _hashCode += getCubeEntry();
        _hashCode += getConsulta();
        if (getUri() != null) {
            _hashCode += getUri().hashCode();
        }
        _hashCode += getCubo();
        _hashCode += getTabela();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaDescricao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">consultaDescricao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cubeEntry");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cubeEntry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consulta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uri");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uri"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
