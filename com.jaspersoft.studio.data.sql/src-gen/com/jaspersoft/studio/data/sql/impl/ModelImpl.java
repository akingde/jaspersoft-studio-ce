/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.Columns;
import com.jaspersoft.studio.data.sql.GroupByColumns;
import com.jaspersoft.studio.data.sql.HavingEntry;
import com.jaspersoft.studio.data.sql.Model;
import com.jaspersoft.studio.data.sql.OrderByColumns;
import com.jaspersoft.studio.data.sql.SqlPackage;
import com.jaspersoft.studio.data.sql.Tables;
import com.jaspersoft.studio.data.sql.WhereEntry;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ModelImpl#getCol <em>Col</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ModelImpl#getTbl <em>Tbl</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ModelImpl#getWhereEntry <em>Where Entry</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ModelImpl#getGroupByEntry <em>Group By Entry</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ModelImpl#getHavingEntry <em>Having Entry</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ModelImpl#getOrderByEntry <em>Order By Entry</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelImpl extends MinimalEObjectImpl.Container implements Model
{
  /**
   * The cached value of the '{@link #getCol() <em>Col</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCol()
   * @generated
   * @ordered
   */
  protected Columns col;

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
   * The cached value of the '{@link #getWhereEntry() <em>Where Entry</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWhereEntry()
   * @generated
   * @ordered
   */
  protected WhereEntry whereEntry;

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
  protected HavingEntry havingEntry;

  /**
   * The cached value of the '{@link #getOrderByEntry() <em>Order By Entry</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOrderByEntry()
   * @generated
   * @ordered
   */
  protected OrderByColumns orderByEntry;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ModelImpl()
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
    return SqlPackage.Literals.MODEL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Columns getCol()
  {
    return col;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCol(Columns newCol, NotificationChain msgs)
  {
    Columns oldCol = col;
    col = newCol;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__COL, oldCol, newCol);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCol(Columns newCol)
  {
    if (newCol != col)
    {
      NotificationChain msgs = null;
      if (col != null)
        msgs = ((InternalEObject)col).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__COL, null, msgs);
      if (newCol != null)
        msgs = ((InternalEObject)newCol).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__COL, null, msgs);
      msgs = basicSetCol(newCol, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__COL, newCol, newCol));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__TBL, oldTbl, newTbl);
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
        msgs = ((InternalEObject)tbl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__TBL, null, msgs);
      if (newTbl != null)
        msgs = ((InternalEObject)newTbl).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__TBL, null, msgs);
      msgs = basicSetTbl(newTbl, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__TBL, newTbl, newTbl));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WhereEntry getWhereEntry()
  {
    return whereEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWhereEntry(WhereEntry newWhereEntry, NotificationChain msgs)
  {
    WhereEntry oldWhereEntry = whereEntry;
    whereEntry = newWhereEntry;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__WHERE_ENTRY, oldWhereEntry, newWhereEntry);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWhereEntry(WhereEntry newWhereEntry)
  {
    if (newWhereEntry != whereEntry)
    {
      NotificationChain msgs = null;
      if (whereEntry != null)
        msgs = ((InternalEObject)whereEntry).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__WHERE_ENTRY, null, msgs);
      if (newWhereEntry != null)
        msgs = ((InternalEObject)newWhereEntry).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__WHERE_ENTRY, null, msgs);
      msgs = basicSetWhereEntry(newWhereEntry, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__WHERE_ENTRY, newWhereEntry, newWhereEntry));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__GROUP_BY_ENTRY, oldGroupByEntry, newGroupByEntry);
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
        msgs = ((InternalEObject)groupByEntry).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__GROUP_BY_ENTRY, null, msgs);
      if (newGroupByEntry != null)
        msgs = ((InternalEObject)newGroupByEntry).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__GROUP_BY_ENTRY, null, msgs);
      msgs = basicSetGroupByEntry(newGroupByEntry, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__GROUP_BY_ENTRY, newGroupByEntry, newGroupByEntry));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HavingEntry getHavingEntry()
  {
    return havingEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetHavingEntry(HavingEntry newHavingEntry, NotificationChain msgs)
  {
    HavingEntry oldHavingEntry = havingEntry;
    havingEntry = newHavingEntry;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__HAVING_ENTRY, oldHavingEntry, newHavingEntry);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHavingEntry(HavingEntry newHavingEntry)
  {
    if (newHavingEntry != havingEntry)
    {
      NotificationChain msgs = null;
      if (havingEntry != null)
        msgs = ((InternalEObject)havingEntry).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__HAVING_ENTRY, null, msgs);
      if (newHavingEntry != null)
        msgs = ((InternalEObject)newHavingEntry).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__HAVING_ENTRY, null, msgs);
      msgs = basicSetHavingEntry(newHavingEntry, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__HAVING_ENTRY, newHavingEntry, newHavingEntry));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrderByColumns getOrderByEntry()
  {
    return orderByEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOrderByEntry(OrderByColumns newOrderByEntry, NotificationChain msgs)
  {
    OrderByColumns oldOrderByEntry = orderByEntry;
    orderByEntry = newOrderByEntry;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__ORDER_BY_ENTRY, oldOrderByEntry, newOrderByEntry);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOrderByEntry(OrderByColumns newOrderByEntry)
  {
    if (newOrderByEntry != orderByEntry)
    {
      NotificationChain msgs = null;
      if (orderByEntry != null)
        msgs = ((InternalEObject)orderByEntry).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__ORDER_BY_ENTRY, null, msgs);
      if (newOrderByEntry != null)
        msgs = ((InternalEObject)newOrderByEntry).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__ORDER_BY_ENTRY, null, msgs);
      msgs = basicSetOrderByEntry(newOrderByEntry, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__ORDER_BY_ENTRY, newOrderByEntry, newOrderByEntry));
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
      case SqlPackage.MODEL__COL:
        return basicSetCol(null, msgs);
      case SqlPackage.MODEL__TBL:
        return basicSetTbl(null, msgs);
      case SqlPackage.MODEL__WHERE_ENTRY:
        return basicSetWhereEntry(null, msgs);
      case SqlPackage.MODEL__GROUP_BY_ENTRY:
        return basicSetGroupByEntry(null, msgs);
      case SqlPackage.MODEL__HAVING_ENTRY:
        return basicSetHavingEntry(null, msgs);
      case SqlPackage.MODEL__ORDER_BY_ENTRY:
        return basicSetOrderByEntry(null, msgs);
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
      case SqlPackage.MODEL__COL:
        return getCol();
      case SqlPackage.MODEL__TBL:
        return getTbl();
      case SqlPackage.MODEL__WHERE_ENTRY:
        return getWhereEntry();
      case SqlPackage.MODEL__GROUP_BY_ENTRY:
        return getGroupByEntry();
      case SqlPackage.MODEL__HAVING_ENTRY:
        return getHavingEntry();
      case SqlPackage.MODEL__ORDER_BY_ENTRY:
        return getOrderByEntry();
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
      case SqlPackage.MODEL__COL:
        setCol((Columns)newValue);
        return;
      case SqlPackage.MODEL__TBL:
        setTbl((Tables)newValue);
        return;
      case SqlPackage.MODEL__WHERE_ENTRY:
        setWhereEntry((WhereEntry)newValue);
        return;
      case SqlPackage.MODEL__GROUP_BY_ENTRY:
        setGroupByEntry((GroupByColumns)newValue);
        return;
      case SqlPackage.MODEL__HAVING_ENTRY:
        setHavingEntry((HavingEntry)newValue);
        return;
      case SqlPackage.MODEL__ORDER_BY_ENTRY:
        setOrderByEntry((OrderByColumns)newValue);
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
      case SqlPackage.MODEL__COL:
        setCol((Columns)null);
        return;
      case SqlPackage.MODEL__TBL:
        setTbl((Tables)null);
        return;
      case SqlPackage.MODEL__WHERE_ENTRY:
        setWhereEntry((WhereEntry)null);
        return;
      case SqlPackage.MODEL__GROUP_BY_ENTRY:
        setGroupByEntry((GroupByColumns)null);
        return;
      case SqlPackage.MODEL__HAVING_ENTRY:
        setHavingEntry((HavingEntry)null);
        return;
      case SqlPackage.MODEL__ORDER_BY_ENTRY:
        setOrderByEntry((OrderByColumns)null);
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
      case SqlPackage.MODEL__COL:
        return col != null;
      case SqlPackage.MODEL__TBL:
        return tbl != null;
      case SqlPackage.MODEL__WHERE_ENTRY:
        return whereEntry != null;
      case SqlPackage.MODEL__GROUP_BY_ENTRY:
        return groupByEntry != null;
      case SqlPackage.MODEL__HAVING_ENTRY:
        return havingEntry != null;
      case SqlPackage.MODEL__ORDER_BY_ENTRY:
        return orderByEntry != null;
    }
    return super.eIsSet(featureID);
  }

} //ModelImpl
