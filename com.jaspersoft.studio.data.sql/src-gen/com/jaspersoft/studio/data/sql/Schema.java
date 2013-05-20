/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Schema</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.Schema#getSchem <em>Schem</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getSchema()
 * @model
 * @generated
 */
public interface Schema extends TableFull
{
  /**
   * Returns the value of the '<em><b>Schem</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Schem</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Schem</em>' attribute.
   * @see #setSchem(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getSchema_Schem()
   * @model
   * @generated
   */
  String getSchem();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Schema#getSchem <em>Schem</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Schem</em>' attribute.
   * @see #getSchem()
   * @generated
   */
  void setSchem(String value);

} // Schema
