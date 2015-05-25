/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unpivot In Clause Arg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.UnpivotInClauseArg#getPcols <em>Pcols</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.UnpivotInClauseArg#getCfuls <em>Cfuls</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getUnpivotInClauseArg()
 * @model
 * @generated
 */
public interface UnpivotInClauseArg extends UnpivotInClauseArgs
{
  /**
   * Returns the value of the '<em><b>Pcols</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pcols</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pcols</em>' containment reference.
   * @see #setPcols(PivotColumns)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getUnpivotInClauseArg_Pcols()
   * @model containment="true"
   * @generated
   */
  PivotColumns getPcols();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.UnpivotInClauseArg#getPcols <em>Pcols</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pcols</em>' containment reference.
   * @see #getPcols()
   * @generated
   */
  void setPcols(PivotColumns value);

  /**
   * Returns the value of the '<em><b>Cfuls</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cfuls</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cfuls</em>' containment reference.
   * @see #setCfuls(PivotColumns)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getUnpivotInClauseArg_Cfuls()
   * @model containment="true"
   * @generated
   */
  PivotColumns getCfuls();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.UnpivotInClauseArg#getCfuls <em>Cfuls</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cfuls</em>' containment reference.
   * @see #getCfuls()
   * @generated
   */
  void setCfuls(PivotColumns value);

} // UnpivotInClauseArg
