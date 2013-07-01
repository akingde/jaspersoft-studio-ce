/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Full</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.TableFull#getTblAlias <em>Tbl Alias</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.TableFull#getTbl <em>Tbl</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getTableFull()
 * @model
 * @generated
 */
public interface TableFull extends ColumnFull, TableOrAlias
{
  /**
   * Returns the value of the '<em><b>Tbl Alias</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tbl Alias</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tbl Alias</em>' containment reference.
   * @see #setTblAlias(TableAlias)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getTableFull_TblAlias()
   * @model containment="true"
   * @generated
   */
  TableAlias getTblAlias();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.TableFull#getTblAlias <em>Tbl Alias</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tbl Alias</em>' containment reference.
   * @see #getTblAlias()
   * @generated
   */
  void setTblAlias(TableAlias value);

  /**
   * Returns the value of the '<em><b>Tbl</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tbl</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tbl</em>' containment reference.
   * @see #setTbl(Table)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getTableFull_Tbl()
   * @model containment="true"
   * @generated
   */
  Table getTbl();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.TableFull#getTbl <em>Tbl</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tbl</em>' containment reference.
   * @see #getTbl()
   * @generated
   */
  void setTbl(Table value);

} // TableFull
