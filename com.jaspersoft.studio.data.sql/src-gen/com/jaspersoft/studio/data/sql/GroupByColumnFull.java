/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Group By Column Full</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.GroupByColumnFull#getGroupByColumn <em>Group By Column</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getGroupByColumnFull()
 * @model
 * @generated
 */
public interface GroupByColumnFull extends GroupByColumns
{
  /**
   * Returns the value of the '<em><b>Group By Column</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group By Column</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group By Column</em>' containment reference.
   * @see #setGroupByColumn(Column)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getGroupByColumnFull_GroupByColumn()
   * @model containment="true"
   * @generated
   */
  Column getGroupByColumn();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.GroupByColumnFull#getGroupByColumn <em>Group By Column</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Group By Column</em>' containment reference.
   * @see #getGroupByColumn()
   * @generated
   */
  void setGroupByColumn(Column value);

} // GroupByColumnFull
