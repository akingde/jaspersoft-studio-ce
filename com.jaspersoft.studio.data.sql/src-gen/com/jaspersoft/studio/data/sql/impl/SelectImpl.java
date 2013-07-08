/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.Columns;
import com.jaspersoft.studio.data.sql.FullExpression;
import com.jaspersoft.studio.data.sql.GroupByColumns;
import com.jaspersoft.studio.data.sql.Select;
import com.jaspersoft.studio.data.sql.SqlPackage;
import com.jaspersoft.studio.data.sql.Tables;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Select</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getSelect <em>Select</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getCols <em>Cols</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getTbl <em>Tbl</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getWhereExpression <em>Where Expression</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getGroupByEntry <em>Group By Entry</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getHavingEntry <em>Having Entry</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SelectImpl extends ModelImpl implements Select
{
  /**
   * The default value of the '{@link #getSelect() <em>Select</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSelect()
   * @generated
   * @ordered
   */
  protected static final String SELECT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSelect() <em>Select</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSelect()
   * @generated
   * @ordered
   */
  protected String select = SELECT_EDEFAULT;

  /**
   * The cached value of the '{@link #getCols() <em>Cols</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCols()
   * @generated
   * @ordered
   */
  protected Columns cols;

  /**
   * The cached value of the '{@link #getTbl() <em>Tbl</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTbl()
   * @generated
   * @ordered
   */
  protected Tables tbl;

  /**
   * The cached value of the '{@link #getWhereExpression() <em>Where Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWhereExpression()
   * @generated
   * @ordered
   */
  protected FullExpression whereExpression;

  /**
   * The cached value of the '{@link #getGroupByEntry() <em>Group By Entry</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGroupByEntry()
   * @generated
   * @ordered
   */
  protected GroupByColumns groupByEntry;

  /**
   * The cached value of the '{@link #getHavingEntry() <em>Having Entry</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHavingEntry()
   * @generated
   * @ordered
   */
  protected FullExpression havingEntry;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SelectImpl()
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
    return SqlPackage.Literals.SELECT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSelect()
  {
    return select;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSelect(String newSelect)
  {
    String oldSelect = select;
    select = newSelect;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__SELECT, oldSelect, select));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Columns getCols()
  {
    return cols;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCols(Columns newCols, NotificationChain msgs)
  {
    Columns oldCols = cols;
    cols = newCols;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__COLS, oldCols, newCols);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCols(Columns newCols)
  {
    if (newCols != cols)
    {
      NotificationChain msgs = null;
      if (cols != null)
        msgs = ((InternalEObject)cols).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__COLS, null, msgs);
      if (newCols != null)
        msgs = ((InternalEObject)newCols).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__COLS, null, msgs);
      msgs = basicSetCols(newCols, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__COLS, newCols, newCols));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Tables getTbl()
  {
    return tbl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTbl(Tables newTbl, NotificationChain msgs)
  {
    Tables oldTbl = tbl;
    tbl = newTbl;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__TBL, oldTbl, newTbl);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTbl(Tables newTbl)
  {
    if (newTbl != tbl)
    {
      NotificationChain msgs = null;
      if (tbl != null)
        msgs = ((InternalEObject)tbl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__TBL, null, msgs);
      if (newTbl != null)
        msgs = ((InternalEObject)newTbl).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__TBL, null, msgs);
      msgs = basicSetTbl(newTbl, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__TBL, newTbl, newTbl));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FullExpression getWhereExpression()
  {
    return whereExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWhereExpression(FullExpression newWhereExpression, NotificationChain msgs)
  {
    FullExpression oldWhereExpression = whereExpression;
    whereExpression = newWhereExpression;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__WHERE_EXPRESSION, oldWhereExpression, newWhereExpression);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWhereExpression(FullExpression newWhereExpression)
  {
    if (newWhereExpression != whereExpression)
    {
      NotificationChain msgs = null;
      if (whereExpression != null)
        msgs = ((InternalEObject)whereExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__WHERE_EXPRESSION, null, msgs);
      if (newWhereExpression != null)
        msgs = ((InternalEObject)newWhereExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__WHERE_EXPRESSION, null, msgs);
      msgs = basicSetWhereExpression(newWhereExpression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__WHERE_EXPRESSION, newWhereExpression, newWhereExpression));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupByColumns getGroupByEntry()
  {
    return groupByEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetGroupByEntry(GroupByColumns newGroupByEntry, NotificationChain msgs)
  {
    GroupByColumns oldGroupByEntry = groupByEntry;
    groupByEntry = newGroupByEntry;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__GROUP_BY_ENTRY, oldGroupByEntry, newGroupByEntry);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGroupByEntry(GroupByColumns newGroupByEntry)
  {
    if (newGroupByEntry != groupByEntry)
    {
      NotificationChain msgs = null;
      if (groupByEntry != null)
        msgs = ((InternalEObject)groupByEntry).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__GROUP_BY_ENTRY, null, msgs);
      if (newGroupByEntry != null)
        msgs = ((InternalEObject)newGroupByEntry).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__GROUP_BY_ENTRY, null, msgs);
      msgs = basicSetGroupByEntry(newGroupByEntry, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__GROUP_BY_ENTRY, newGroupByEntry, newGroupByEntry));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FullExpression getHavingEntry()
  {
    return havingEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetHavingEntry(FullExpression newHavingEntry, NotificationChain msgs)
  {
    FullExpression oldHavingEntry = havingEntry;
    havingEntry = newHavingEntry;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__HAVING_ENTRY, oldHavingEntry, newHavingEntry);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHavingEntry(FullExpression newHavingEntry)
  {
    if (newHavingEntry != havingEntry)
    {
      NotificationChain msgs = null;
      if (havingEntry != null)
        msgs = ((InternalEObject)havingEntry).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__HAVING_ENTRY, null, msgs);
      if (newHavingEntry != null)
        msgs = ((InternalEObject)newHavingEntry).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__HAVING_ENTRY, null, msgs);
      msgs = basicSetHavingEntry(newHavingEntry, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__HAVING_ENTRY, newHavingEntry, newHavingEntry));
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
      case SqlPackage.SELECT__COLS:
        return basicSetCols(null, msgs);
      case SqlPackage.SELECT__TBL:
        return basicSetTbl(null, msgs);
      case SqlPackage.SELECT__WHERE_EXPRESSION:
        return basicSetWhereExpression(null, msgs);
      case SqlPackage.SELECT__GROUP_BY_ENTRY:
        return basicSetGroupByEntry(null, msgs);
      case SqlPackage.SELECT__HAVING_ENTRY:
        return basicSetHavingEntry(null, msgs);
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
      case SqlPackage.SELECT__SELECT:
        return getSelect();
      case SqlPackage.SELECT__COLS:
        return getCols();
      case SqlPackage.SELECT__TBL:
        return getTbl();
      case SqlPackage.SELECT__WHERE_EXPRESSION:
        return getWhereExpression();
      case SqlPackage.SELECT__GROUP_BY_ENTRY:
        return getGroupByEntry();
      case SqlPackage.SELECT__HAVING_ENTRY:
        return getHavingEntry();
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
      case SqlPackage.SELECT__SELECT:
        setSelect((String)newValue);
        return;
      case SqlPackage.SELECT__COLS:
        setCols((Columns)newValue);
        return;
      case SqlPackage.SELECT__TBL:
        setTbl((Tables)newValue);
        return;
      case SqlPackage.SELECT__WHERE_EXPRESSION:
        setWhereExpression((FullExpression)newValue);
        return;
      case SqlPackage.SELECT__GROUP_BY_ENTRY:
        setGroupByEntry((GroupByColumns)newValue);
        return;
      case SqlPackage.SELECT__HAVING_ENTRY:
        setHavingEntry((FullExpression)newValue);
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
      case SqlPackage.SELECT__SELECT:
        setSelect(SELECT_EDEFAULT);
        return;
      case SqlPackage.SELECT__COLS:
        setCols((Columns)null);
        return;
      case SqlPackage.SELECT__TBL:
        setTbl((Tables)null);
        return;
      case SqlPackage.SELECT__WHERE_EXPRESSION:
        setWhereExpression((FullExpression)null);
        return;
      case SqlPackage.SELECT__GROUP_BY_ENTRY:
        setGroupByEntry((GroupByColumns)null);
        return;
      case SqlPackage.SELECT__HAVING_ENTRY:
        setHavingEntry((FullExpression)null);
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
      case SqlPackage.SELECT__SELECT:
        return SELECT_EDEFAULT == null ? select != null : !SELECT_EDEFAULT.equals(select);
      case SqlPackage.SELECT__COLS:
        return cols != null;
      case SqlPackage.SELECT__TBL:
        return tbl != null;
      case SqlPackage.SELECT__WHERE_EXPRESSION:
        return whereExpression != null;
      case SqlPackage.SELECT__GROUP_BY_ENTRY:
        return groupByEntry != null;
      case SqlPackage.SELECT__HAVING_ENTRY:
        return havingEntry != null;
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
    result.append(" (select: ");
    result.append(select);
    result.append(')');
    return result.toString();
  }

} //SelectImpl
