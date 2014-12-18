/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pivot Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.PivotTable#getPfun <em>Pfun</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.PivotTable#getPfor <em>Pfor</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.PivotTable#getPin <em>Pin</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getPivotTable()
 * @model
 * @generated
 */
public interface PivotTable extends EObject
{
  /**
   * Returns the value of the '<em><b>Pfun</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pfun</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pfun</em>' containment reference.
   * @see #setPfun(PivotFunctions)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getPivotTable_Pfun()
   * @model containment="true"
   * @generated
   */
  PivotFunctions getPfun();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.PivotTable#getPfun <em>Pfun</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pfun</em>' containment reference.
   * @see #getPfun()
   * @generated
   */
  void setPfun(PivotFunctions value);

  /**
   * Returns the value of the '<em><b>Pfor</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pfor</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pfor</em>' containment reference.
   * @see #setPfor(PivotForClause)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getPivotTable_Pfor()
   * @model containment="true"
   * @generated
   */
  PivotForClause getPfor();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.PivotTable#getPfor <em>Pfor</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pfor</em>' containment reference.
   * @see #getPfor()
   * @generated
   */
  void setPfor(PivotForClause value);

  /**
   * Returns the value of the '<em><b>Pin</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pin</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pin</em>' containment reference.
   * @see #setPin(PivotInClause)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getPivotTable_Pin()
   * @model containment="true"
   * @generated
   */
  PivotInClause getPin();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.PivotTable#getPin <em>Pin</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pin</em>' containment reference.
   * @see #getPin()
   * @generated
   */
  void setPin(PivotInClause value);

} // PivotTable
