/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.Table#getTbl <em>Tbl</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getTable()
 * @model
 * @generated
 */
public interface Table extends EObject
{
  /**
   * Returns the value of the '<em><b>Tbl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tbl</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tbl</em>' attribute.
   * @see #setTbl(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getTable_Tbl()
   * @model
   * @generated
   */
  String getTbl();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Table#getTbl <em>Tbl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tbl</em>' attribute.
   * @see #getTbl()
   * @generated
   */
  void setTbl(String value);

} // Table
