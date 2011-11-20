/**
 * AddCube.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.cube.stubs.Cube;

public class AddCube  implements java.io.Serializable {
    private java.lang.String name;
    private java.lang.String uri;
    private java.lang.String user;
    private java.lang.String password;
    private long millisecond;
    private java.lang.String fato;
    private org.globus.cube.stubs.Cube.CubeMetaData cube;

    public AddCube() {
    }

    public AddCube(
           org.globus.cube.stubs.Cube.CubeMetaData cube,
           java.lang.String fato,
           long millisecond,
           java.lang.String name,
           java.lang.String password,
           java.lang.String uri,
           java.lang.String user) {
           this.name = name;
           this.uri = uri;
           this.user = user;
           this.password = password;
           this.millisecond = millisecond;
           this.fato = fato;
           this.cube = cube;
    }


    /**
     * Gets the name value for this AddCube.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this AddCube.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the uri value for this AddCube.
     * 
     * @return uri
     */
    public java.lang.String getUri() {
        return uri;
    }


    /**
     * Sets the uri value for this AddCube.
     * 
     * @param uri
     */
    public void setUri(java.lang.String uri) {
        this.uri = uri;
    }


    /**
     * Gets the user value for this AddCube.
     * 
     * @return user
     */
    public java.lang.String getUser() {
        return user;
    }


    /**
     * Sets the user value for this AddCube.
     * 
     * @param user
     */
    public void setUser(java.lang.String user) {
        this.user = user;
    }


    /**
     * Gets the password value for this AddCube.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this AddCube.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the millisecond value for this AddCube.
     * 
     * @return millisecond
     */
    public long getMillisecond() {
        return millisecond;
    }


    /**
     * Sets the millisecond value for this AddCube.
     * 
     * @param millisecond
     */
    public void setMillisecond(long millisecond) {
        this.millisecond = millisecond;
    }


    /**
     * Gets the fato value for this AddCube.
     * 
     * @return fato
     */
    public java.lang.String getFato() {
        return fato;
    }


    /**
     * Sets the fato value for this AddCube.
     * 
     * @param fato
     */
    public void setFato(java.lang.String fato) {
        this.fato = fato;
    }


    /**
     * Gets the cube value for this AddCube.
     * 
     * @return cube
     */
    public org.globus.cube.stubs.Cube.CubeMetaData getCube() {
        return cube;
    }


    /**
     * Sets the cube value for this AddCube.
     * 
     * @param cube
     */
    public void setCube(org.globus.cube.stubs.Cube.CubeMetaData cube) {
        this.cube = cube;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddCube)) return false;
        AddCube other = (AddCube) obj;
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
            ((this.uri==null && other.getUri()==null) || 
             (this.uri!=null &&
              this.uri.equals(other.getUri()))) &&
            ((this.user==null && other.getUser()==null) || 
             (this.user!=null &&
              this.user.equals(other.getUser()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            this.millisecond == other.getMillisecond() &&
            ((this.fato==null && other.getFato()==null) || 
             (this.fato!=null &&
              this.fato.equals(other.getFato()))) &&
            ((this.cube==null && other.getCube()==null) || 
             (this.cube!=null &&
              this.cube.equals(other.getCube())));
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
        if (getUri() != null) {
            _hashCode += getUri().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        _hashCode += new Long(getMillisecond()).hashCode();
        if (getFato() != null) {
            _hashCode += getFato().hashCode();
        }
        if (getCube() != null) {
            _hashCode += getCube().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddCube.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">addCube"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uri");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uri"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("", "user"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("millisecond");
        elemField.setXmlName(new javax.xml.namespace.QName("", "millisecond"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cube");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", "cube"));
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
