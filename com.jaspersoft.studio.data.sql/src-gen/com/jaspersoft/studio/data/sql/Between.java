/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Between</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.Between#getOp1 <em>Op1</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Between#getOp2 <em>Op2</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getBetween()
 * @model
 * @generated
 */
public interface Between extends EObject
{
  /**
   * Returns the value of the '<em><b>Op1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op1</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op1</em>' containment reference.
   * @see #setOp1(Operand)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getBetween_Op1()
   * @model containment="true"
   * @generated
   */
  Operand getOp1();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Between#getOp1 <em>Op1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op1</em>' containment reference.
   * @see #getOp1()
   * @generated
   */
  void setOp1(Operand value);

  /**
   * Returns the value of the '<em><b>Op2</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op2</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op2</em>' containment reference.
   * @see #setOp2(Operand)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getBetween_Op2()
   * @model containment="true"
   * @generated
   */
  Operand getOp2();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Between#getOp2 <em>Op2</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op2</em>' containment reference.
   * @see #getOp2()
   * @generated
   */
  void setOp2(Operand value);

} // Between
