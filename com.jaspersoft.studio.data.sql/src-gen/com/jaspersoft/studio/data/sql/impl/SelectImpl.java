/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.FetchFirst;
import com.jaspersoft.studio.data.sql.Limit;
import com.jaspersoft.studio.data.sql.Offset;
import com.jaspersoft.studio.data.sql.OrColumn;
import com.jaspersoft.studio.data.sql.OrExpr;
import com.jaspersoft.studio.data.sql.OrGroupByColumn;
import com.jaspersoft.studio.data.sql.OrOrderByColumn;
import com.jaspersoft.studio.data.sql.OrTable;
import com.jaspersoft.studio.data.sql.Select;
import com.jaspersoft.studio.data.sql.SelectSubSet;
import com.jaspersoft.studio.data.sql.SqlPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Select</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getOp <em>Op</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getSelect <em>Select</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getCols <em>Cols</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getTbl <em>Tbl</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getWhereExpression <em>Where Expression</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getGroupByEntry <em>Group By Entry</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getHavingEntry <em>Having Entry</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getOrderByEntry <em>Order By Entry</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getLim <em>Lim</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getOffset <em>Offset</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SelectImpl#getFetchFirst <em>Fetch First</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SelectImpl extends SelectQueryImpl implements Select
{
  /**
   * The cached value of the '{@link #getOp() <em>Op</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOp()
   * @generated
   * @ordered
   */
  protected EList<SelectSubSet> op;

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
  protected OrColumn cols;

  /**
   * The cached value of the '{@link #getTbl() <em>Tbl</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTbl()
   * @generated
   * @ordered
   */
  protected OrTable tbl;

  /**
   * The cached value of the '{@link #getWhereExpression() <em>Where Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWhereExpression()
   * @generated
   * @ordered
   */
  protected OrExpr whereExpression;

  /**
   * The cached value of the '{@link #getGroupByEntry() <em>Group By Entry</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGroupByEntry()
   * @generated
   * @ordered
   */
  protected OrGroupByColumn groupByEntry;

  /**
   * The cached value of the '{@link #getHavingEntry() <em>Having Entry</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHavingEntry()
   * @generated
   * @ordered
   */
  protected OrExpr havingEntry;

  /**
   * The cached value of the '{@link #getOrderByEntry() <em>Order By Entry</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOrderByEntry()
   * @generated
   * @ordered
   */
  protected OrOrderByColumn orderByEntry;

  /**
   * The cached value of the '{@link #getLim() <em>Lim</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLim()
   * @generated
   * @ordered
   */
  protected Limit lim;

  /**
   * The cached value of the '{@link #getOffset() <em>Offset</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOffset()
   * @generated
   * @ordered
   */
  protected Offset offset;

  /**
   * The cached value of the '{@link #getFetchFirst() <em>Fetch First</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFetchFirst()
   * @generated
   * @ordered
   */
  protected FetchFirst fetchFirst;

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
  public EList<SelectSubSet> getOp()
  {
    if (op == null)
    {
      op = new EObjectContainmentEList<SelectSubSet>(SelectSubSet.class, this, SqlPackage.SELECT__OP);
    }
    return op;
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
  public OrColumn getCols()
  {
    return cols;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCols(OrColumn newCols, NotificationChain msgs)
  {
    OrColumn oldCols = cols;
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
  public void setCols(OrColumn newCols)
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
  public OrTable getTbl()
  {
    return tbl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTbl(OrTable newTbl, NotificationChain msgs)
  {
    OrTable oldTbl = tbl;
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
  public void setTbl(OrTable newTbl)
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
  public OrExpr getWhereExpression()
  {
    return whereExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWhereExpression(OrExpr newWhereExpression, NotificationChain msgs)
  {
    OrExpr oldWhereExpression = whereExpression;
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
  public void setWhereExpression(OrExpr newWhereExpression)
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
  public OrGroupByColumn getGroupByEntry()
  {
    return groupByEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetGroupByEntry(OrGroupByColumn newGroupByEntry, NotificationChain msgs)
  {
    OrGroupByColumn oldGroupByEntry = groupByEntry;
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
  public void setGroupByEntry(OrGroupByColumn newGroupByEntry)
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
  public OrExpr getHavingEntry()
  {
    return havingEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetHavingEntry(OrExpr newHavingEntry, NotificationChain msgs)
  {
    OrExpr oldHavingEntry = havingEntry;
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
  public void setHavingEntry(OrExpr newHavingEntry)
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
  public OrOrderByColumn getOrderByEntry()
  {
    return orderByEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOrderByEntry(OrOrderByColumn newOrderByEntry, NotificationChain msgs)
  {
    OrOrderByColumn oldOrderByEntry = orderByEntry;
    orderByEntry = newOrderByEntry;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__ORDER_BY_ENTRY, oldOrderByEntry, newOrderByEntry);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOrderByEntry(OrOrderByColumn newOrderByEntry)
  {
    if (newOrderByEntry != orderByEntry)
    {
      NotificationChain msgs = null;
      if (orderByEntry != null)
        msgs = ((InternalEObject)orderByEntry).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__ORDER_BY_ENTRY, null, msgs);
      if (newOrderByEntry != null)
        msgs = ((InternalEObject)newOrderByEntry).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__ORDER_BY_ENTRY, null, msgs);
      msgs = basicSetOrderByEntry(newOrderByEntry, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__ORDER_BY_ENTRY, newOrderByEntry, newOrderByEntry));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Limit getLim()
  {
    return lim;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLim(Limit newLim, NotificationChain msgs)
  {
    Limit oldLim = lim;
    lim = newLim;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__LIM, oldLim, newLim);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLim(Limit newLim)
  {
    if (newLim != lim)
    {
      NotificationChain msgs = null;
      if (lim != null)
        msgs = ((InternalEObject)lim).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__LIM, null, msgs);
      if (newLim != null)
        msgs = ((InternalEObject)newLim).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__LIM, null, msgs);
      msgs = basicSetLim(newLim, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__LIM, newLim, newLim));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Offset getOffset()
  {
    return offset;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOffset(Offset newOffset, NotificationChain msgs)
  {
    Offset oldOffset = offset;
    offset = newOffset;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__OFFSET, oldOffset, newOffset);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOffset(Offset newOffset)
  {
    if (newOffset != offset)
    {
      NotificationChain msgs = null;
      if (offset != null)
        msgs = ((InternalEObject)offset).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__OFFSET, null, msgs);
      if (newOffset != null)
        msgs = ((InternalEObject)newOffset).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__OFFSET, null, msgs);
      msgs = basicSetOffset(newOffset, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__OFFSET, newOffset, newOffset));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FetchFirst getFetchFirst()
  {
    return fetchFirst;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFetchFirst(FetchFirst newFetchFirst, NotificationChain msgs)
  {
    FetchFirst oldFetchFirst = fetchFirst;
    fetchFirst = newFetchFirst;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__FETCH_FIRST, oldFetchFirst, newFetchFirst);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFetchFirst(FetchFirst newFetchFirst)
  {
    if (newFetchFirst != fetchFirst)
    {
      NotificationChain msgs = null;
      if (fetchFirst != null)
        msgs = ((InternalEObject)fetchFirst).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__FETCH_FIRST, null, msgs);
      if (newFetchFirst != null)
        msgs = ((InternalEObject)newFetchFirst).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SELECT__FETCH_FIRST, null, msgs);
      msgs = basicSetFetchFirst(newFetchFirst, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SELECT__FETCH_FIRST, newFetchFirst, newFetchFirst));
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
      case SqlPackage.SELECT__OP:
        return ((InternalEList<?>)getOp()).basicRemove(otherEnd, msgs);
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
      case SqlPackage.SELECT__ORDER_BY_ENTRY:
        return basicSetOrderByEntry(null, msgs);
      case SqlPackage.SELECT__LIM:
        return basicSetLim(null, msgs);
      case SqlPackage.SELECT__OFFSET:
        return basicSetOffset(null, msgs);
      case SqlPackage.SELECT__FETCH_FIRST:
        return basicSetFetchFirst(null, msgs);
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
      case SqlPackage.SELECT__OP:
        return getOp();
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
      case SqlPackage.SELECT__ORDER_BY_ENTRY:
        return getOrderByEntry();
      case SqlPackage.SELECT__LIM:
        return getLim();
      case SqlPackage.SELECT__OFFSET:
        return getOffset();
      case SqlPackage.SELECT__FETCH_FIRST:
        return getFetchFirst();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case SqlPackage.SELECT__OP:
        getOp().clear();
        getOp().addAll((Collection<? extends SelectSubSet>)newValue);
        return;
      case SqlPackage.SELECT__SELECT:
        setSelect((String)newValue);
        return;
      case SqlPackage.SELECT__COLS:
        setCols((OrColumn)newValue);
        return;
      case SqlPackage.SELECT__TBL:
        setTbl((OrTable)newValue);
        return;
      case SqlPackage.SELECT__WHERE_EXPRESSION:
        setWhereExpression((OrExpr)newValue);
        return;
      case SqlPackage.SELECT__GROUP_BY_ENTRY:
        setGroupByEntry((OrGroupByColumn)newValue);
        return;
      case SqlPackage.SELECT__HAVING_ENTRY:
        setHavingEntry((OrExpr)newValue);
        return;
      case SqlPackage.SELECT__ORDER_BY_ENTRY:
        setOrderByEntry((OrOrderByColumn)newValue);
        return;
      case SqlPackage.SELECT__LIM:
        setLim((Limit)newValue);
        return;
      case SqlPackage.SELECT__OFFSET:
        setOffset((Offset)newValue);
        return;
      case SqlPackage.SELECT__FETCH_FIRST:
        setFetchFirst((FetchFirst)newValue);
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
      case SqlPackage.SELECT__OP:
        getOp().clear();
        return;
      case SqlPackage.SELECT__SELECT:
        setSelect(SELECT_EDEFAULT);
        return;
      case SqlPackage.SELECT__COLS:
        setCols((OrColumn)null);
        return;
      case SqlPackage.SELECT__TBL:
        setTbl((OrTable)null);
        return;
      case SqlPackage.SELECT__WHERE_EXPRESSION:
        setWhereExpression((OrExpr)null);
        return;
      case SqlPackage.SELECT__GROUP_BY_ENTRY:
        setGroupByEntry((OrGroupByColumn)null);
        return;
      case SqlPackage.SELECT__HAVING_ENTRY:
        setHavingEntry((OrExpr)null);
        return;
      case SqlPackage.SELECT__ORDER_BY_ENTRY:
        setOrderByEntry((OrOrderByColumn)null);
        return;
      case SqlPackage.SELECT__LIM:
        setLim((Limit)null);
        return;
      case SqlPackage.SELECT__OFFSET:
        setOffset((Offset)null);
        return;
      case SqlPackage.SELECT__FETCH_FIRST:
        setFetchFirst((FetchFirst)null);
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
      case SqlPackage.SELECT__OP:
        return op != null && !op.isEmpty();
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
      case SqlPackage.SELECT__ORDER_BY_ENTRY:
        return orderByEntry != null;
      case SqlPackage.SELECT__LIM:
        return lim != null;
      case SqlPackage.SELECT__OFFSET:
        return offset != null;
      case SqlPackage.SELECT__FETCH_FIRST:
        return fetchFirst != null;
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
