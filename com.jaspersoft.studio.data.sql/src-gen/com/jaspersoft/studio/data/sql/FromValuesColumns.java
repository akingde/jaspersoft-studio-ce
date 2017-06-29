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
 * A representation of the model object '<em><b>From Values Columns</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.FromValuesColumns#getFvCols <em>Fv Cols</em>}</li>
 * </ul>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getFromValuesColumns()
 * @model
 * @generated
 */
public interface FromValuesColumns extends EObject
{
  /**
   * Returns the value of the '<em><b>Fv Cols</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fv Cols</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fv Cols</em>' containment reference.
   * @see #setFvCols(FromValuesColumnNames)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getFromValuesColumns_FvCols()
   * @model containment="true"
   * @generated
   */
  FromValuesColumnNames getFvCols();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.FromValuesColumns#getFvCols <em>Fv Cols</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fv Cols</em>' containment reference.
   * @see #getFvCols()
   * @generated
   */
  void setFvCols(FromValuesColumnNames value);

} // FromValuesColumns
