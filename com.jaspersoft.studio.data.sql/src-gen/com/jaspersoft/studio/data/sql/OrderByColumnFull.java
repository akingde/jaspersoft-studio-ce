/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Order By Column Full</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.OrderByColumnFull#getColOrder <em>Col Order</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getOrderByColumnFull()
 * @model
 * @generated
 */
public interface OrderByColumnFull extends OrderByColumns
{
  /**
   * Returns the value of the '<em><b>Col Order</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Col Order</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Col Order</em>' containment reference.
   * @see #setColOrder(Column)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getOrderByColumnFull_ColOrder()
   * @model containment="true"
   * @generated
   */
  Column getColOrder();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.OrderByColumnFull#getColOrder <em>Col Order</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Col Order</em>' containment reference.
   * @see #getColOrder()
   * @generated
   */
  void setColOrder(Column value);

} // OrderByColumnFull
