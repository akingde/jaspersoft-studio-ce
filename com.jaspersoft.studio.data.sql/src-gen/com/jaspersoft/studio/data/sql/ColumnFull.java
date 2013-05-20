/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Column Full</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.ColumnFull#getColAlias <em>Col Alias</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.ColumnFull#getColName <em>Col Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getColumnFull()
 * @model
 * @generated
 */
public interface ColumnFull extends ColumnOrAlias
{
  /**
   * Returns the value of the '<em><b>Col Alias</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Col Alias</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Col Alias</em>' containment reference.
   * @see #setColAlias(ColumnAlias)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getColumnFull_ColAlias()
   * @model containment="true"
   * @generated
   */
  ColumnAlias getColAlias();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ColumnFull#getColAlias <em>Col Alias</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Col Alias</em>' containment reference.
   * @see #getColAlias()
   * @generated
   */
  void setColAlias(ColumnAlias value);

  /**
   * Returns the value of the '<em><b>Col Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Col Name</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Col Name</em>' containment reference.
   * @see #setColName(Column)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getColumnFull_ColName()
   * @model containment="true"
   * @generated
   */
  Column getColName();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ColumnFull#getColName <em>Col Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Col Name</em>' containment reference.
   * @see #getColName()
   * @generated
   */
  void setColName(Column value);

} // ColumnFull
