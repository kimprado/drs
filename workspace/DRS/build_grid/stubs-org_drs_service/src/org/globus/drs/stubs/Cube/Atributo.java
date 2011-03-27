/**
 * Atributo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.drs.stubs.Cube;

public class Atributo  implements java.io.Serializable {
    private int consulta;
    private int cubeEntry;
    private int cubo;
    private java.lang.String cuboNome;
    private int tabela;
    private java.lang.String tabelaNome;
    private int campo;
    private java.lang.String campoNome;
    private boolean agrupar;
    private boolean mostrar;
    private int mostrarPosicao;
    private boolean ordenar;
    private int ordenarPosicao;
    private int ordenarDirecao;
    private java.lang.String uri;
    private org.globus.drs.stubs.Cube.Opcoes[] opcoes;
    private org.globus.drs.stubs.Cube.AgSel[] agSel;
    private org.globus.drs.stubs.Cube.AgHav[] agHav;
    private java.lang.String idObjeto;

    public Atributo() {
    }

    public Atributo(
           org.globus.drs.stubs.Cube.AgHav[] agHav,
           org.globus.drs.stubs.Cube.AgSel[] agSel,
           boolean agrupar,
           int campo,
           java.lang.String campoNome,
           int consulta,
           int cubeEntry,
           int cubo,
           java.lang.String cuboNome,
           java.lang.String idObjeto,
           boolean mostrar,
           int mostrarPosicao,
           org.globus.drs.stubs.Cube.Opcoes[] opcoes,
           boolean ordenar,
           int ordenarDirecao,
           int ordenarPosicao,
           int tabela,
           java.lang.String tabelaNome,
           java.lang.String uri) {
           this.consulta = consulta;
           this.cubeEntry = cubeEntry;
           this.cubo = cubo;
           this.cuboNome = cuboNome;
           this.tabela = tabela;
           this.tabelaNome = tabelaNome;
           this.campo = campo;
           this.campoNome = campoNome;
           this.agrupar = agrupar;
           this.mostrar = mostrar;
           this.mostrarPosicao = mostrarPosicao;
           this.ordenar = ordenar;
           this.ordenarPosicao = ordenarPosicao;
           this.ordenarDirecao = ordenarDirecao;
           this.uri = uri;
           this.opcoes = opcoes;
           this.agSel = agSel;
           this.agHav = agHav;
           this.idObjeto = idObjeto;
    }


    /**
     * Gets the consulta value for this Atributo.
     * 
     * @return consulta
     */
    public int getConsulta() {
        return consulta;
    }


    /**
     * Sets the consulta value for this Atributo.
     * 
     * @param consulta
     */
    public void setConsulta(int consulta) {
        this.consulta = consulta;
    }


    /**
     * Gets the cubeEntry value for this Atributo.
     * 
     * @return cubeEntry
     */
    public int getCubeEntry() {
        return cubeEntry;
    }


    /**
     * Sets the cubeEntry value for this Atributo.
     * 
     * @param cubeEntry
     */
    public void setCubeEntry(int cubeEntry) {
        this.cubeEntry = cubeEntry;
    }


    /**
     * Gets the cubo value for this Atributo.
     * 
     * @return cubo
     */
    public int getCubo() {
        return cubo;
    }


    /**
     * Sets the cubo value for this Atributo.
     * 
     * @param cubo
     */
    public void setCubo(int cubo) {
        this.cubo = cubo;
    }


    /**
     * Gets the cuboNome value for this Atributo.
     * 
     * @return cuboNome
     */
    public java.lang.String getCuboNome() {
        return cuboNome;
    }


    /**
     * Sets the cuboNome value for this Atributo.
     * 
     * @param cuboNome
     */
    public void setCuboNome(java.lang.String cuboNome) {
        this.cuboNome = cuboNome;
    }


    /**
     * Gets the tabela value for this Atributo.
     * 
     * @return tabela
     */
    public int getTabela() {
        return tabela;
    }


    /**
     * Sets the tabela value for this Atributo.
     * 
     * @param tabela
     */
    public void setTabela(int tabela) {
        this.tabela = tabela;
    }


    /**
     * Gets the tabelaNome value for this Atributo.
     * 
     * @return tabelaNome
     */
    public java.lang.String getTabelaNome() {
        return tabelaNome;
    }


    /**
     * Sets the tabelaNome value for this Atributo.
     * 
     * @param tabelaNome
     */
    public void setTabelaNome(java.lang.String tabelaNome) {
        this.tabelaNome = tabelaNome;
    }


    /**
     * Gets the campo value for this Atributo.
     * 
     * @return campo
     */
    public int getCampo() {
        return campo;
    }


    /**
     * Sets the campo value for this Atributo.
     * 
     * @param campo
     */
    public void setCampo(int campo) {
        this.campo = campo;
    }


    /**
     * Gets the campoNome value for this Atributo.
     * 
     * @return campoNome
     */
    public java.lang.String getCampoNome() {
        return campoNome;
    }


    /**
     * Sets the campoNome value for this Atributo.
     * 
     * @param campoNome
     */
    public void setCampoNome(java.lang.String campoNome) {
        this.campoNome = campoNome;
    }


    /**
     * Gets the agrupar value for this Atributo.
     * 
     * @return agrupar
     */
    public boolean isAgrupar() {
        return agrupar;
    }


    /**
     * Sets the agrupar value for this Atributo.
     * 
     * @param agrupar
     */
    public void setAgrupar(boolean agrupar) {
        this.agrupar = agrupar;
    }


    /**
     * Gets the mostrar value for this Atributo.
     * 
     * @return mostrar
     */
    public boolean isMostrar() {
        return mostrar;
    }


    /**
     * Sets the mostrar value for this Atributo.
     * 
     * @param mostrar
     */
    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }


    /**
     * Gets the mostrarPosicao value for this Atributo.
     * 
     * @return mostrarPosicao
     */
    public int getMostrarPosicao() {
        return mostrarPosicao;
    }


    /**
     * Sets the mostrarPosicao value for this Atributo.
     * 
     * @param mostrarPosicao
     */
    public void setMostrarPosicao(int mostrarPosicao) {
        this.mostrarPosicao = mostrarPosicao;
    }


    /**
     * Gets the ordenar value for this Atributo.
     * 
     * @return ordenar
     */
    public boolean isOrdenar() {
        return ordenar;
    }


    /**
     * Sets the ordenar value for this Atributo.
     * 
     * @param ordenar
     */
    public void setOrdenar(boolean ordenar) {
        this.ordenar = ordenar;
    }


    /**
     * Gets the ordenarPosicao value for this Atributo.
     * 
     * @return ordenarPosicao
     */
    public int getOrdenarPosicao() {
        return ordenarPosicao;
    }


    /**
     * Sets the ordenarPosicao value for this Atributo.
     * 
     * @param ordenarPosicao
     */
    public void setOrdenarPosicao(int ordenarPosicao) {
        this.ordenarPosicao = ordenarPosicao;
    }


    /**
     * Gets the ordenarDirecao value for this Atributo.
     * 
     * @return ordenarDirecao
     */
    public int getOrdenarDirecao() {
        return ordenarDirecao;
    }


    /**
     * Sets the ordenarDirecao value for this Atributo.
     * 
     * @param ordenarDirecao
     */
    public void setOrdenarDirecao(int ordenarDirecao) {
        this.ordenarDirecao = ordenarDirecao;
    }


    /**
     * Gets the uri value for this Atributo.
     * 
     * @return uri
     */
    public java.lang.String getUri() {
        return uri;
    }


    /**
     * Sets the uri value for this Atributo.
     * 
     * @param uri
     */
    public void setUri(java.lang.String uri) {
        this.uri = uri;
    }


    /**
     * Gets the opcoes value for this Atributo.
     * 
     * @return opcoes
     */
    public org.globus.drs.stubs.Cube.Opcoes[] getOpcoes() {
        return opcoes;
    }


    /**
     * Sets the opcoes value for this Atributo.
     * 
     * @param opcoes
     */
    public void setOpcoes(org.globus.drs.stubs.Cube.Opcoes[] opcoes) {
        this.opcoes = opcoes;
    }

    public org.globus.drs.stubs.Cube.Opcoes getOpcoes(int i) {
        return this.opcoes[i];
    }

    public void setOpcoes(int i, org.globus.drs.stubs.Cube.Opcoes _value) {
        this.opcoes[i] = _value;
    }


    /**
     * Gets the agSel value for this Atributo.
     * 
     * @return agSel
     */
    public org.globus.drs.stubs.Cube.AgSel[] getAgSel() {
        return agSel;
    }


    /**
     * Sets the agSel value for this Atributo.
     * 
     * @param agSel
     */
    public void setAgSel(org.globus.drs.stubs.Cube.AgSel[] agSel) {
        this.agSel = agSel;
    }

    public org.globus.drs.stubs.Cube.AgSel getAgSel(int i) {
        return this.agSel[i];
    }

    public void setAgSel(int i, org.globus.drs.stubs.Cube.AgSel _value) {
        this.agSel[i] = _value;
    }


    /**
     * Gets the agHav value for this Atributo.
     * 
     * @return agHav
     */
    public org.globus.drs.stubs.Cube.AgHav[] getAgHav() {
        return agHav;
    }


    /**
     * Sets the agHav value for this Atributo.
     * 
     * @param agHav
     */
    public void setAgHav(org.globus.drs.stubs.Cube.AgHav[] agHav) {
        this.agHav = agHav;
    }

    public org.globus.drs.stubs.Cube.AgHav getAgHav(int i) {
        return this.agHav[i];
    }

    public void setAgHav(int i, org.globus.drs.stubs.Cube.AgHav _value) {
        this.agHav[i] = _value;
    }


    /**
     * Gets the idObjeto value for this Atributo.
     * 
     * @return idObjeto
     */
    public java.lang.String getIdObjeto() {
        return idObjeto;
    }


    /**
     * Sets the idObjeto value for this Atributo.
     * 
     * @param idObjeto
     */
    public void setIdObjeto(java.lang.String idObjeto) {
        this.idObjeto = idObjeto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Atributo)) return false;
        Atributo other = (Atributo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.consulta == other.getConsulta() &&
            this.cubeEntry == other.getCubeEntry() &&
            this.cubo == other.getCubo() &&
            ((this.cuboNome==null && other.getCuboNome()==null) || 
             (this.cuboNome!=null &&
              this.cuboNome.equals(other.getCuboNome()))) &&
            this.tabela == other.getTabela() &&
            ((this.tabelaNome==null && other.getTabelaNome()==null) || 
             (this.tabelaNome!=null &&
              this.tabelaNome.equals(other.getTabelaNome()))) &&
            this.campo == other.getCampo() &&
            ((this.campoNome==null && other.getCampoNome()==null) || 
             (this.campoNome!=null &&
              this.campoNome.equals(other.getCampoNome()))) &&
            this.agrupar == other.isAgrupar() &&
            this.mostrar == other.isMostrar() &&
            this.mostrarPosicao == other.getMostrarPosicao() &&
            this.ordenar == other.isOrdenar() &&
            this.ordenarPosicao == other.getOrdenarPosicao() &&
            this.ordenarDirecao == other.getOrdenarDirecao() &&
            ((this.uri==null && other.getUri()==null) || 
             (this.uri!=null &&
              this.uri.equals(other.getUri()))) &&
            ((this.opcoes==null && other.getOpcoes()==null) || 
             (this.opcoes!=null &&
              java.util.Arrays.equals(this.opcoes, other.getOpcoes()))) &&
            ((this.agSel==null && other.getAgSel()==null) || 
             (this.agSel!=null &&
              java.util.Arrays.equals(this.agSel, other.getAgSel()))) &&
            ((this.agHav==null && other.getAgHav()==null) || 
             (this.agHav!=null &&
              java.util.Arrays.equals(this.agHav, other.getAgHav()))) &&
            ((this.idObjeto==null && other.getIdObjeto()==null) || 
             (this.idObjeto!=null &&
              this.idObjeto.equals(other.getIdObjeto())));
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
        _hashCode += getCubeEntry();
        _hashCode += getCubo();
        if (getCuboNome() != null) {
            _hashCode += getCuboNome().hashCode();
        }
        _hashCode += getTabela();
        if (getTabelaNome() != null) {
            _hashCode += getTabelaNome().hashCode();
        }
        _hashCode += getCampo();
        if (getCampoNome() != null) {
            _hashCode += getCampoNome().hashCode();
        }
        _hashCode += (isAgrupar() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isMostrar() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getMostrarPosicao();
        _hashCode += (isOrdenar() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getOrdenarPosicao();
        _hashCode += getOrdenarDirecao();
        if (getUri() != null) {
            _hashCode += getUri().hashCode();
        }
        if (getOpcoes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOpcoes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOpcoes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAgSel() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAgSel());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAgSel(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAgHav() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAgHav());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAgHav(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIdObjeto() != null) {
            _hashCode += getIdObjeto().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Atributo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">atributo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consulta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cubeEntry");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cubeEntry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cubo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cubo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cuboNome");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cuboNome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tabela");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tabela"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tabelaNome");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tabelaNome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("campo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "campo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("campoNome");
        elemField.setXmlName(new javax.xml.namespace.QName("", "campoNome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agrupar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "agrupar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mostrar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mostrar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mostrarPosicao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mostrarPosicao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordenar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ordenar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordenarPosicao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ordenarPosicao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordenarDirecao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ordenarDirecao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uri");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uri"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("opcoes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", "opcoes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">opcoes"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agSel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", "agSel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">agSel"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agHav");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", "agHav"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube", ">agHav"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idObjeto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idObjeto"));
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
