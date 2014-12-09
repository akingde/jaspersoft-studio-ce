/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Analytical</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.FunctionAnalytical#getAnClause <em>An Clause</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getFunctionAnalytical()
 * @model
 * @generated
 */
public interface FunctionAnalytical extends EObject
{
  /**
   * Returns the value of the '<em><b>An Clause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>An Clause</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>An Clause</em>' containment reference.
   * @see #setAnClause(AnalyticClause)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getFunctionAnalytical_AnClause()
   * @model containment="true"
   * @generated
   */
  AnalyticClause getAnClause();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.FunctionAnalytical#getAnClause <em>An Clause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>An Clause</em>' containment reference.
   * @see #getAnClause()
   * @generated
   */
  void setAnClause(AnalyticClause value);

} // FunctionAnalytical
