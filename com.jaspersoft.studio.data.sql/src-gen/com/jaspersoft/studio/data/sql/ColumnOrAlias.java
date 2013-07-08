/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Column Or Alias</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.ColumnOrAlias#getAllCols <em>All Cols</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getColumnOrAlias()
 * @model
 * @generated
 */
public interface ColumnOrAlias extends Columns
{
  /**
   * Returns the value of the '<em><b>All Cols</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>All Cols</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>All Cols</em>' attribute.
   * @see #setAllCols(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getColumnOrAlias_AllCols()
   * @model
   * @generated
   */
  String getAllCols();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ColumnOrAlias#getAllCols <em>All Cols</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>All Cols</em>' attribute.
   * @see #getAllCols()
   * @generated
   */
  void setAllCols(String value);

} // ColumnOrAlias
