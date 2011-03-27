package org.cube.service.impl;

import javax.xml.namespace.QName;

public interface CubeQNames {
	public static final String NS = "http://www.globus.org/namespaces/examples/core/Cube";
	
	public static final QName RP_Cube = new QName(NS, "Cube");

	public static final QName RESOURCE_PROPERTIES = new QName(NS,
			"MathResourceProperties");
}
