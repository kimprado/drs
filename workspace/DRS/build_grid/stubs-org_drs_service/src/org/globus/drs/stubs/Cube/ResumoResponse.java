/**
 * ResumoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.drs.stubs.Cube;

public class ResumoResponse  implements java.io.Serializable {
    private org.globus.drs.stubs.Cube.Atributo[] resumoAtributos;
    private org.globus.drs.stubs.Cube.Atributo[] resumoOrdemAtributos;
    private org.globus.drs.stubs.Cube.Atributo[] resumoOrdemDados;

    public ResumoResponse() {
    }

    public ResumoResponse(
           org.globus.drs.stubs.Cube.Atributo[] resumoAtributos,
           org.globus.drs.stubs.Cube.Atributo[] resumoOrdemAtributos,
           org.globus.drs.stubs.Cube.Atributo[] resumoOrdemDados) {
           this.resumoAtributos = resumoAtributos;
           this.resumoOrdemAtributos = resumoOrdemAtributos;
           this.resumoOrdemDados = resumoOrdemDados;
    }


    /**
     * Gets the resumoAtributos value for this ResumoResponse.
     * 
     * @return resumoAtributos
     */
    public org.globus.drs.stubs.Cube.Atributo[] getResumoAtributos() {
        return resumoAtributos;
    }


    /**
     * Sets the resumoAtributos value for this ResumoResponse.
     * 
     * @param resumoAtributos
     */
    public void setResumoAtributos(org.globus.drs.stubs.Cube.Atributo[] resumoAtributos) {
        this.resumoAtributos = resumoAtributos;
    }

    public org.globus.drs.stubs.Cube.Atributo getResumoAtributos(int i) {
        return this.resumoAtributos[i];
    }

    public void setResumoAtributos(int i, org.globus.drs.stubs.Cube.Atributo _value) {
        this.resumoAtributos[i] = _value;
    }


    /**
     * Gets the resumoOrdemAtributos value for this ResumoResponse.
     * 
     * @return resumoOrdemAtributos
     */
    public org.globus.drs.stubs.Cube.Atributo[] getResumoOrdemAtributos() {
        return resumoOrdemAtributos;
    }


    /**
     * Sets the resumoOrdemAtributos value for this ResumoResponse.
     * 
     * @param resumoOrdemAtributos
     */
    public void setResumoOrdemAtributos(org.globus.drs.stubs.Cube.Atributo[] resumoOrdemAtributos) {
        this.resumoOrdemAtributos = resumoOrdemAtributos;
    }

    public org.globus.drs.stubs.Cube.Atributo getResumoOrdemAtributos(int i) {
        return this.resumoOrdemAtributos[i];
    }

    public void setResumoOrdemAtributos(int i, org.globus.drs.stubs.Cube.Atributo _value) {
        this.resumoOrdemAtributos[i] = _value;
    }


    /**
     * Gets the resumoOrdemDados value for this ResumoResponse.
     * 
     * @return resumoOrdemDados
     */
    public org.globus.drs.stubs.Cube.Atributo[] getResumoOrdemDados() {
        return resumoOrdemDados;
    }


    /**
     * Sets the resumoOrdemDados value for this ResumoResponse.
     * 
     * @param resumoOrdemDados
     */
    public void setResumoOrdemDados(org.globus.drs.stubs.Cube.Atributo[] resumoOrdemDados) {
        this.resumoOrdemDados = resumoOrdemDados;
    }

    public org.globus.drs.stubs.Cube.Atributo getResumoOrdemDados(int i) {
        return this.resumoOrdemDados[i];
    }

    public void setResumoOrdemDados(int i, org.globus.drs.stubs.Cube.Atributo _value) {
        this.resumoOrdemDados[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResumoResponse)) return false;
        ResumoResponse other = (ResumoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resumoAtributos==null && other.getResumoAtributos()==null) || 
             (this.resumoAtributos!=null &&
              java.util.Arrays.equals(this.resumoAtributos, other.getResumoAtributos()))) &&
            ((this.resumoOrdemAtributos==null && other.getResumoOrdemAtributos()==null) || 
             (this.resumoOrdemAtributos!=null &&
              java.util.Arrays.equals(this.resumoOrdemAtributos, other.getResumoOrdemAtributos()))) &&
            ((this.resumoOrdemDados==null && other.getResumoOrdemDados()==null) || 
             (this.resumoOrdemDados!=null &&
              java.util.Arrays.equals(this.resumoOrdemDados, other.getResumoOrdemDados())));
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
        if (getResumoAtributos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResumoAtributos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResumoAtributos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getResumoOrdemAtributos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResumoOrdemAtributos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResumoOrdemAtributos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getResumoOrdemDados() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResumoOrdemDados());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResumoOrdemDados(), i);
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
        new org.apache.axis.description.TypeDesc(ResumoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">resumoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resumoAtributos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", "resumoAtributos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">atributo"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resumoOrdemAtributos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", "resumoOrdemAtributos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">atributo"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resumoOrdemDados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", "resumoOrdemDados"));
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
