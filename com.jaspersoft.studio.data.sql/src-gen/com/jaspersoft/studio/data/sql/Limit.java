/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Limit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.Limit#getL1 <em>L1</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Limit#getL2 <em>L2</em>}</li>
 * </ul>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getLimit()
 * @model
 * @generated
 */
public interface Limit extends EObject
{
  /**
   * Returns the value of the '<em><b>L1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>L1</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>L1</em>' attribute.
   * @see #setL1(Long)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getLimit_L1()
   * @model
   * @generated
   */
  Long getL1();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Limit#getL1 <em>L1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>L1</em>' attribute.
   * @see #getL1()
   * @generated
   */
  void setL1(Long value);

  /**
   * Returns the value of the '<em><b>L2</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>L2</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>L2</em>' attribute.
   * @see #setL2(Long)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getLimit_L2()
   * @model
   * @generated
   */
  Long getL2();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Limit#getL2 <em>L2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>L2</em>' attribute.
   * @see #getL2()
   * @generated
   */
  void setL2(Long value);

} // Limit
