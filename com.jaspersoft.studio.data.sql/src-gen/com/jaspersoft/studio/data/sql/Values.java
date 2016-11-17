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
 * A representation of the model object '<em><b>Values</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.Values#getRows <em>Rows</em>}</li>
 * </ul>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getValues()
 * @model
 * @generated
 */
public interface Values extends EObject
{
  /**
   * Returns the value of the '<em><b>Rows</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rows</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rows</em>' containment reference.
   * @see #setRows(Rows)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getValues_Rows()
   * @model containment="true"
   * @generated
   */
  Rows getRows();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Values#getRows <em>Rows</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rows</em>' containment reference.
   * @see #getRows()
   * @generated
   */
  void setRows(Rows value);

} // Values
