/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Windowing Clause Operand Following</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.WindowingClauseOperandFollowing#getExp <em>Exp</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getWindowingClauseOperandFollowing()
 * @model
 * @generated
 */
public interface WindowingClauseOperandFollowing extends EObject
{
  /**
   * Returns the value of the '<em><b>Exp</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Exp</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Exp</em>' containment reference.
   * @see #setExp(AnalyticExprArg)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getWindowingClauseOperandFollowing_Exp()
   * @model containment="true"
   * @generated
   */
  AnalyticExprArg getExp();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.WindowingClauseOperandFollowing#getExp <em>Exp</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Exp</em>' containment reference.
   * @see #getExp()
   * @generated
   */
  void setExp(AnalyticExprArg value);

} // WindowingClauseOperandFollowing
