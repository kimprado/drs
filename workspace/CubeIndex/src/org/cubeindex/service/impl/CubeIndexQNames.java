package org.cubeindex.service.impl;

import javax.xml.namespace.QName;

public interface CubeIndexQNames {
	public static final String NS = "http://www.globus.org/namespaces/examples/core/Cube";
	
	public static final QName RP_CubeURI = new QName(NS, "cubeURI");

	public static final QName RESOURCE_PROPERTIES = new QName(NS,
			"MathResourceProperties");
}
