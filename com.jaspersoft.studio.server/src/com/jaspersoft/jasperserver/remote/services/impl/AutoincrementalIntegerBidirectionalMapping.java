/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/

package com.jaspersoft.jasperserver.remote.services.impl;

/**
 * @author Yaroslav.Kovalchyk
 * @version $Id: AutoincrementalIntegerBidirectionalMapping.java 23005 2012-04-05 12:47:10Z ykovalchyk $
 */
public interface AutoincrementalIntegerBidirectionalMapping<V> extends BidirectionalMapping<Integer,V> {
    Integer put(V value);
}
