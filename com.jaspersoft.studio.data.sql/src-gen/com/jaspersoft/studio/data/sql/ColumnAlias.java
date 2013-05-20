/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Column Alias</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.ColumnAlias#getColAlias <em>Col Alias</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getColumnAlias()
 * @model
 * @generated
 */
public interface ColumnAlias extends EObject
{
  /**
   * Returns the value of the '<em><b>Col Alias</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Col Alias</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Col Alias</em>' attribute.
   * @see #setColAlias(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getColumnAlias_ColAlias()
   * @model
   * @generated
   */
  String getColAlias();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ColumnAlias#getColAlias <em>Col Alias</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Col Alias</em>' attribute.
   * @see #getColAlias()
   * @generated
   */
  void setColAlias(String value);

} // ColumnAlias
