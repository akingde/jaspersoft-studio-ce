/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Column Names</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.ColumnNames#getColName <em>Col Name</em>}</li>
 * </ul>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getColumnNames()
 * @model
 * @generated
 */
public interface ColumnNames extends FromValuesColumnNames
{
  /**
   * Returns the value of the '<em><b>Col Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Col Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Col Name</em>' attribute.
   * @see #setColName(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getColumnNames_ColName()
   * @model
   * @generated
   */
  String getColName();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ColumnNames#getColName <em>Col Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Col Name</em>' attribute.
   * @see #getColName()
   * @generated
   */
  void setColName(String value);

} // ColumnNames
