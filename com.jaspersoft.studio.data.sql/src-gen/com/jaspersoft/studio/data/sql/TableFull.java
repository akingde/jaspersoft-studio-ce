/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Full</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.TableFull#getTblAlias <em>Tbl Alias</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getTableFull()
 * @model
 * @generated
 */
public interface TableFull extends TableOrAlias
{
  /**
   * Returns the value of the '<em><b>Tbl Alias</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tbl Alias</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tbl Alias</em>' containment reference.
   * @see #setTblAlias(DbObjectName)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getTableFull_TblAlias()
   * @model containment="true"
   * @generated
   */
  DbObjectName getTblAlias();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.TableFull#getTblAlias <em>Tbl Alias</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tbl Alias</em>' containment reference.
   * @see #getTblAlias()
   * @generated
   */
  void setTblAlias(DbObjectName value);

} // TableFull
