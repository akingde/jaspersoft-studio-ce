/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Alias</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.TableAlias#getTblAlias <em>Tbl Alias</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getTableAlias()
 * @model
 * @generated
 */
public interface TableAlias extends EObject
{
  /**
   * Returns the value of the '<em><b>Tbl Alias</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tbl Alias</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tbl Alias</em>' attribute.
   * @see #setTblAlias(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getTableAlias_TblAlias()
   * @model
   * @generated
   */
  String getTblAlias();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.TableAlias#getTblAlias <em>Tbl Alias</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tbl Alias</em>' attribute.
   * @see #getTblAlias()
   * @generated
   */
  void setTblAlias(String value);

} // TableAlias
