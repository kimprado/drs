/**
 * AgHav.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.drs.stubs.Cube;

public class AgHav  implements java.io.Serializable {
    private int logico;
    private int agregacao;
    private int comparacao;
    private java.lang.String valor;

    public AgHav() {
    }

    public AgHav(
           int agregacao,
           int comparacao,
           int logico,
           java.lang.String valor) {
           this.logico = logico;
           this.agregacao = agregacao;
           this.comparacao = comparacao;
           this.valor = valor;
    }


    /**
     * Gets the logico value for this AgHav.
     * 
     * @return logico
     */
    public int getLogico() {
        return logico;
    }


    /**
     * Sets the logico value for this AgHav.
     * 
     * @param logico
     */
    public void setLogico(int logico) {
        this.logico = logico;
    }


    /**
     * Gets the agregacao value for this AgHav.
     * 
     * @return agregacao
     */
    public int getAgregacao() {
        return agregacao;
    }


    /**
     * Sets the agregacao value for this AgHav.
     * 
     * @param agregacao
     */
    public void setAgregacao(int agregacao) {
        this.agregacao = agregacao;
    }


    /**
     * Gets the comparacao value for this AgHav.
     * 
     * @return comparacao
     */
    public int getComparacao() {
        return comparacao;
    }


    /**
     * Sets the comparacao value for this AgHav.
     * 
     * @param comparacao
     */
    public void setComparacao(int comparacao) {
        this.comparacao = comparacao;
    }


    /**
     * Gets the valor value for this AgHav.
     * 
     * @return valor
     */
    public java.lang.String getValor() {
        return valor;
    }


    /**
     * Sets the valor value for this AgHav.
     * 
     * @param valor
     */
    public void setValor(java.lang.String valor) {
        this.valor = valor;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AgHav)) return false;
        AgHav other = (AgHav) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.logico == other.getLogico() &&
            this.agregacao == other.getAgregacao() &&
            this.comparacao == other.getComparacao() &&
            ((this.valor==null && other.getValor()==null) || 
             (this.valor!=null &&
              this.valor.equals(other.getValor())));
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
        _hashCode += getLogico();
        _hashCode += getAgregacao();
        _hashCode += getComparacao();
        if (getValor() != null) {
            _hashCode += getValor().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AgHav.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">agHav"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "logico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agregacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "agregacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comparacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "comparacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valor"));
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
