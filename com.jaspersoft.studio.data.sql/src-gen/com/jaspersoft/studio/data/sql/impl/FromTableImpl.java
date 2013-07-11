/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.FromTable;
import com.jaspersoft.studio.data.sql.JoinType;
import com.jaspersoft.studio.data.sql.OrExpr;
import com.jaspersoft.studio.data.sql.SqlPackage;
import com.jaspersoft.studio.data.sql.TableOrAlias;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>From Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.FromTableImpl#getTable <em>Table</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.FromTableImpl#getJoin <em>Join</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.FromTableImpl#getOnTable <em>On Table</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.FromTableImpl#getJoinExpr <em>Join Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FromTableImpl extends OrTableImpl implements FromTable
{
  /**
   * The cached value of the '{@link #getTable() <em>Table</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTable()
   * @generated
   * @ordered
   */
  protected TableOrAlias table;

  /**
   * The default value of the '{@link #getJoin() <em>Join</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJoin()
   * @generated
   * @ordered
   */
  protected static final JoinType JOIN_EDEFAULT = JoinType.INNER_JOIN;

  /**
   * The cached value of the '{@link #getJoin() <em>Join</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJoin()
   * @generated
   * @ordered
   */
  protected JoinType join = JOIN_EDEFAULT;

  /**
   * The cached value of the '{@link #getOnTable() <em>On Table</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOnTable()
   * @generated
   * @ordered
   */
  protected TableOrAlias onTable;

  /**
   * The cached value of the '{@link #getJoinExpr() <em>Join Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJoinExpr()
   * @generated
   * @ordered
   */
  protected OrExpr joinExpr;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FromTableImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return SqlPackage.Literals.FROM_TABLE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TableOrAlias getTable()
  {
    return table;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTable(TableOrAlias newTable, NotificationChain msgs)
  {
    TableOrAlias oldTable = table;
    table = newTable;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.FROM_TABLE__TABLE, oldTable, newTable);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTable(TableOrAlias newTable)
  {
    if (newTable != table)
    {
      NotificationChain msgs = null;
      if (table != null)
        msgs = ((InternalEObject)table).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FROM_TABLE__TABLE, null, msgs);
      if (newTable != null)
        msgs = ((InternalEObject)newTable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FROM_TABLE__TABLE, null, msgs);
      msgs = basicSetTable(newTable, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.FROM_TABLE__TABLE, newTable, newTable));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JoinType getJoin()
  {
    return join;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setJoin(JoinType newJoin)
  {
    JoinType oldJoin = join;
    join = newJoin == null ? JOIN_EDEFAULT : newJoin;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.FROM_TABLE__JOIN, oldJoin, join));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TableOrAlias getOnTable()
  {
    return onTable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOnTable(TableOrAlias newOnTable, NotificationChain msgs)
  {
    TableOrAlias oldOnTable = onTable;
    onTable = newOnTable;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.FROM_TABLE__ON_TABLE, oldOnTable, newOnTable);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOnTable(TableOrAlias newOnTable)
  {
    if (newOnTable != onTable)
    {
      NotificationChain msgs = null;
      if (onTable != null)
        msgs = ((InternalEObject)onTable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FROM_TABLE__ON_TABLE, null, msgs);
      if (newOnTable != null)
        msgs = ((InternalEObject)newOnTable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FROM_TABLE__ON_TABLE, null, msgs);
      msgs = basicSetOnTable(newOnTable, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.FROM_TABLE__ON_TABLE, newOnTable, newOnTable));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrExpr getJoinExpr()
  {
    return joinExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetJoinExpr(OrExpr newJoinExpr, NotificationChain msgs)
  {
    OrExpr oldJoinExpr = joinExpr;
    joinExpr = newJoinExpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.FROM_TABLE__JOIN_EXPR, oldJoinExpr, newJoinExpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setJoinExpr(OrExpr newJoinExpr)
  {
    if (newJoinExpr != joinExpr)
    {
      NotificationChain msgs = null;
      if (joinExpr != null)
        msgs = ((InternalEObject)joinExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FROM_TABLE__JOIN_EXPR, null, msgs);
      if (newJoinExpr != null)
        msgs = ((InternalEObject)newJoinExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FROM_TABLE__JOIN_EXPR, null, msgs);
      msgs = basicSetJoinExpr(newJoinExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.FROM_TABLE__JOIN_EXPR, newJoinExpr, newJoinExpr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case SqlPackage.FROM_TABLE__TABLE:
        return basicSetTable(null, msgs);
      case SqlPackage.FROM_TABLE__ON_TABLE:
        return basicSetOnTable(null, msgs);
      case SqlPackage.FROM_TABLE__JOIN_EXPR:
        return basicSetJoinExpr(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case SqlPackage.FROM_TABLE__TABLE:
        return getTable();
      case SqlPackage.FROM_TABLE__JOIN:
        return getJoin();
      case SqlPackage.FROM_TABLE__ON_TABLE:
        return getOnTable();
      case SqlPackage.FROM_TABLE__JOIN_EXPR:
        return getJoinExpr();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case SqlPackage.FROM_TABLE__TABLE:
        setTable((TableOrAlias)newValue);
        return;
      case SqlPackage.FROM_TABLE__JOIN:
        setJoin((JoinType)newValue);
        return;
      case SqlPackage.FROM_TABLE__ON_TABLE:
        setOnTable((TableOrAlias)newValue);
        return;
      case SqlPackage.FROM_TABLE__JOIN_EXPR:
        setJoinExpr((OrExpr)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case SqlPackage.FROM_TABLE__TABLE:
        setTable((TableOrAlias)null);
        return;
      case SqlPackage.FROM_TABLE__JOIN:
        setJoin(JOIN_EDEFAULT);
        return;
      case SqlPackage.FROM_TABLE__ON_TABLE:
        setOnTable((TableOrAlias)null);
        return;
      case SqlPackage.FROM_TABLE__JOIN_EXPR:
        setJoinExpr((OrExpr)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case SqlPackage.FROM_TABLE__TABLE:
        return table != null;
      case SqlPackage.FROM_TABLE__JOIN:
        return join != JOIN_EDEFAULT;
      case SqlPackage.FROM_TABLE__ON_TABLE:
        return onTable != null;
      case SqlPackage.FROM_TABLE__JOIN_EXPR:
        return joinExpr != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (join: ");
    result.append(join);
    result.append(')');
    return result.toString();
  }

} //FromTableImpl
