/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Full Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.FullExpression#getOp1 <em>Op1</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.FullExpression#getIn <em>In</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.FullExpression#getBetween <em>Between</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.FullExpression#getLike <em>Like</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.FullExpression#getComp <em>Comp</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getFullExpression()
 * @model
 * @generated
 */
public interface FullExpression extends EObject
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
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getFullExpression_Op1()
   * @model containment="true"
   * @generated
   */
  Operand getOp1();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.FullExpression#getOp1 <em>Op1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op1</em>' containment reference.
   * @see #getOp1()
   * @generated
   */
  void setOp1(Operand value);

  /**
   * Returns the value of the '<em><b>In</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>In</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>In</em>' containment reference.
   * @see #setIn(InOperator)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getFullExpression_In()
   * @model containment="true"
   * @generated
   */
  InOperator getIn();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.FullExpression#getIn <em>In</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>In</em>' containment reference.
   * @see #getIn()
   * @generated
   */
  void setIn(InOperator value);

  /**
   * Returns the value of the '<em><b>Between</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Between</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Between</em>' containment reference.
   * @see #setBetween(Between)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getFullExpression_Between()
   * @model containment="true"
   * @generated
   */
  Between getBetween();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.FullExpression#getBetween <em>Between</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Between</em>' containment reference.
   * @see #getBetween()
   * @generated
   */
  void setBetween(Between value);

  /**
   * Returns the value of the '<em><b>Like</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Like</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Like</em>' attribute.
   * @see #setLike(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getFullExpression_Like()
   * @model
   * @generated
   */
  String getLike();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.FullExpression#getLike <em>Like</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Like</em>' attribute.
   * @see #getLike()
   * @generated
   */
  void setLike(String value);

  /**
   * Returns the value of the '<em><b>Comp</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Comp</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Comp</em>' containment reference.
   * @see #setComp(Comparison)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getFullExpression_Comp()
   * @model containment="true"
   * @generated
   */
  Comparison getComp();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.FullExpression#getComp <em>Comp</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comp</em>' containment reference.
   * @see #getComp()
   * @generated
   */
  void setComp(Comparison value);

} // FullExpression
