/**
 * LigacaoMetaData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.cube.stubs.Cube;

public class LigacaoMetaData  implements java.io.Serializable {
    private org.globus.cube.stubs.Cube.FieldMetaData estrangeiro;
    private org.globus.cube.stubs.Cube.FieldMetaData primario;

    public LigacaoMetaData() {
    }

    public LigacaoMetaData(
           org.globus.cube.stubs.Cube.FieldMetaData estrangeiro,
           org.globus.cube.stubs.Cube.FieldMetaData primario) {
           this.estrangeiro = estrangeiro;
           this.primario = primario;
    }


    /**
     * Gets the estrangeiro value for this LigacaoMetaData.
     * 
     * @return estrangeiro
     */
    public org.globus.cube.stubs.Cube.FieldMetaData getEstrangeiro() {
        return estrangeiro;
    }


    /**
     * Sets the estrangeiro value for this LigacaoMetaData.
     * 
     * @param estrangeiro
     */
    public void setEstrangeiro(org.globus.cube.stubs.Cube.FieldMetaData estrangeiro) {
        this.estrangeiro = estrangeiro;
    }


    /**
     * Gets the primario value for this LigacaoMetaData.
     * 
     * @return primario
     */
    public org.globus.cube.stubs.Cube.FieldMetaData getPrimario() {
        return primario;
    }


    /**
     * Sets the primario value for this LigacaoMetaData.
     * 
     * @param primario
     */
    public void setPrimario(org.globus.cube.stubs.Cube.FieldMetaData primario) {
        this.primario = primario;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LigacaoMetaData)) return false;
        LigacaoMetaData other = (LigacaoMetaData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.estrangeiro==null && other.getEstrangeiro()==null) || 
             (this.estrangeiro!=null &&
              this.estrangeiro.equals(other.getEstrangeiro()))) &&
            ((this.primario==null && other.getPrimario()==null) || 
             (this.primario!=null &&
              this.primario.equals(other.getPrimario())));
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
        if (getEstrangeiro() != null) {
            _hashCode += getEstrangeiro().hashCode();
        }
        if (getPrimario() != null) {
            _hashCode += getPrimario().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LigacaoMetaData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">ligacaoMetaData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estrangeiro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", "estrangeiro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">fieldMetaData"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", "primario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">fieldMetaData"));
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
