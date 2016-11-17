/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Row</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.Row#getRowValues <em>Row Values</em>}</li>
 * </ul>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getRow()
 * @model
 * @generated
 */
public interface Row extends Rows
{
  /**
   * Returns the value of the '<em><b>Row Values</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Row Values</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Row Values</em>' containment reference.
   * @see #setRowValues(RowValues)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getRow_RowValues()
   * @model containment="true"
   * @generated
   */
  RowValues getRowValues();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Row#getRowValues <em>Row Values</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Row Values</em>' containment reference.
   * @see #getRowValues()
   * @generated
   */
  void setRowValues(RowValues value);

} // Row
