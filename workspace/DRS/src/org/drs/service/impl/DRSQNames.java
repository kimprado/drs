package org.drs.service.impl;

import javax.xml.namespace.QName;

public interface DRSQNames {
	public static final String NS = "http://www.globus.org/namespaces/examples/core/Cube";
	
	public static final QName RP_DRS = new QName(NS, "DRSURI");

	public static final QName RESOURCE_PROPERTIES = new QName(NS,
			"MathResourceProperties");
}
