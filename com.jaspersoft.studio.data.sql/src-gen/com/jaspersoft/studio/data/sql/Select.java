/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Select</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.Select#getOp <em>Op</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Select#getSelect <em>Select</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Select#getCols <em>Cols</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Select#getTbl <em>Tbl</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Select#getWhereExpression <em>Where Expression</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Select#getGroupByEntry <em>Group By Entry</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Select#getHavingEntry <em>Having Entry</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Select#getOrderByEntry <em>Order By Entry</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Select#getLim <em>Lim</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Select#getOffset <em>Offset</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Select#getFetchFirst <em>Fetch First</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getSelect()
 * @model
 * @generated
 */
public interface Select extends SelectQuery
{
  /**
   * Returns the value of the '<em><b>Op</b></em>' containment reference list.
   * The list contents are of type {@link com.jaspersoft.studio.data.sql.SelectSubSet}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' containment reference list.
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getSelect_Op()
   * @model containment="true"
   * @generated
   */
  EList<SelectSubSet> getOp();

  /**
   * Returns the value of the '<em><b>Select</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Select</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Select</em>' attribute.
   * @see #setSelect(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getSelect_Select()
   * @model
   * @generated
   */
  String getSelect();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Select#getSelect <em>Select</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Select</em>' attribute.
   * @see #getSelect()
   * @generated
   */
  void setSelect(String value);

  /**
   * Returns the value of the '<em><b>Cols</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cols</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cols</em>' containment reference.
   * @see #setCols(OrColumn)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getSelect_Cols()
   * @model containment="true"
   * @generated
   */
  OrColumn getCols();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Select#getCols <em>Cols</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cols</em>' containment reference.
   * @see #getCols()
   * @generated
   */
  void setCols(OrColumn value);

  /**
   * Returns the value of the '<em><b>Tbl</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tbl</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tbl</em>' containment reference.
   * @see #setTbl(OrTable)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getSelect_Tbl()
   * @model containment="true"
   * @generated
   */
  OrTable getTbl();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Select#getTbl <em>Tbl</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tbl</em>' containment reference.
   * @see #getTbl()
   * @generated
   */
  void setTbl(OrTable value);

  /**
   * Returns the value of the '<em><b>Where Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Where Expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Where Expression</em>' containment reference.
   * @see #setWhereExpression(OrExpr)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getSelect_WhereExpression()
   * @model containment="true"
   * @generated
   */
  OrExpr getWhereExpression();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Select#getWhereExpression <em>Where Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Where Expression</em>' containment reference.
   * @see #getWhereExpression()
   * @generated
   */
  void setWhereExpression(OrExpr value);

  /**
   * Returns the value of the '<em><b>Group By Entry</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group By Entry</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group By Entry</em>' containment reference.
   * @see #setGroupByEntry(OrGroupByColumn)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getSelect_GroupByEntry()
   * @model containment="true"
   * @generated
   */
  OrGroupByColumn getGroupByEntry();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Select#getGroupByEntry <em>Group By Entry</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Group By Entry</em>' containment reference.
   * @see #getGroupByEntry()
   * @generated
   */
  void setGroupByEntry(OrGroupByColumn value);

  /**
   * Returns the value of the '<em><b>Having Entry</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Having Entry</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Having Entry</em>' containment reference.
   * @see #setHavingEntry(OrExpr)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getSelect_HavingEntry()
   * @model containment="true"
   * @generated
   */
  OrExpr getHavingEntry();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Select#getHavingEntry <em>Having Entry</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Having Entry</em>' containment reference.
   * @see #getHavingEntry()
   * @generated
   */
  void setHavingEntry(OrExpr value);

  /**
   * Returns the value of the '<em><b>Order By Entry</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Order By Entry</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Order By Entry</em>' containment reference.
   * @see #setOrderByEntry(OrOrderByColumn)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getSelect_OrderByEntry()
   * @model containment="true"
   * @generated
   */
  OrOrderByColumn getOrderByEntry();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Select#getOrderByEntry <em>Order By Entry</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Order By Entry</em>' containment reference.
   * @see #getOrderByEntry()
   * @generated
   */
  void setOrderByEntry(OrOrderByColumn value);

  /**
   * Returns the value of the '<em><b>Lim</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lim</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lim</em>' containment reference.
   * @see #setLim(Limit)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getSelect_Lim()
   * @model containment="true"
   * @generated
   */
  Limit getLim();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Select#getLim <em>Lim</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lim</em>' containment reference.
   * @see #getLim()
   * @generated
   */
  void setLim(Limit value);

  /**
   * Returns the value of the '<em><b>Offset</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Offset</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Offset</em>' containment reference.
   * @see #setOffset(Offset)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getSelect_Offset()
   * @model containment="true"
   * @generated
   */
  Offset getOffset();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Select#getOffset <em>Offset</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Offset</em>' containment reference.
   * @see #getOffset()
   * @generated
   */
  void setOffset(Offset value);

  /**
   * Returns the value of the '<em><b>Fetch First</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fetch First</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fetch First</em>' containment reference.
   * @see #setFetchFirst(FetchFirst)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getSelect_FetchFirst()
   * @model containment="true"
   * @generated
   */
  FetchFirst getFetchFirst();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Select#getFetchFirst <em>Fetch First</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fetch First</em>' containment reference.
   * @see #getFetchFirst()
   * @generated
   */
  void setFetchFirst(FetchFirst value);

} // Select
