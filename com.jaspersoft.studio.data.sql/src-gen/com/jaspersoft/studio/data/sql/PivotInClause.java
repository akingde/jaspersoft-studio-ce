/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pivot In Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.PivotInClause#getSq <em>Sq</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getPivotInClause()
 * @model
 * @generated
 */
public interface PivotInClause extends EObject
{
  /**
   * Returns the value of the '<em><b>Sq</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sq</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sq</em>' containment reference.
   * @see #setSq(SubQueryOperand)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getPivotInClause_Sq()
   * @model containment="true"
   * @generated
   */
  SubQueryOperand getSq();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.PivotInClause#getSq <em>Sq</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sq</em>' containment reference.
   * @see #getSq()
   * @generated
   */
  void setSq(SubQueryOperand value);

} // PivotInClause
