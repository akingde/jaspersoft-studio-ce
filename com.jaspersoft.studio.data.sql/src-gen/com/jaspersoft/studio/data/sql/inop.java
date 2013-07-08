/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>inop</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.inop#getSubquery <em>Subquery</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getinop()
 * @model
 * @generated
 */
public interface inop extends InOperator
{
  /**
   * Returns the value of the '<em><b>Subquery</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Subquery</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Subquery</em>' containment reference.
   * @see #setSubquery(Operand)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getinop_Subquery()
   * @model containment="true"
   * @generated
   */
  Operand getSubquery();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.inop#getSubquery <em>Subquery</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Subquery</em>' containment reference.
   * @see #getSubquery()
   * @generated
   */
  void setSubquery(Operand value);

} // inop
