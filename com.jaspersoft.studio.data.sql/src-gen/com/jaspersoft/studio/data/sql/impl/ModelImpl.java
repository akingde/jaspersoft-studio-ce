/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.FetchFirst;
import com.jaspersoft.studio.data.sql.Limit;
import com.jaspersoft.studio.data.sql.Model;
import com.jaspersoft.studio.data.sql.Offset;
import com.jaspersoft.studio.data.sql.OrOrderByColumn;
import com.jaspersoft.studio.data.sql.SelectQuery;
import com.jaspersoft.studio.data.sql.SqlPackage;

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
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ModelImpl#getQuery <em>Query</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ModelImpl#getOrderByEntry <em>Order By Entry</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ModelImpl#getLim <em>Lim</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ModelImpl#getOffset <em>Offset</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ModelImpl#getFetchFirst <em>Fetch First</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelImpl extends MinimalEObjectImpl.Container implements Model
{
  /**
   * The cached value of the '{@link #getQuery() <em>Query</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQuery()
   * @generated
   * @ordered
   */
  protected SelectQuery query;

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
  public SelectQuery getQuery()
  {
    return query;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetQuery(SelectQuery newQuery, NotificationChain msgs)
  {
    SelectQuery oldQuery = query;
    query = newQuery;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__QUERY, oldQuery, newQuery);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setQuery(SelectQuery newQuery)
  {
    if (newQuery != query)
    {
      NotificationChain msgs = null;
      if (query != null)
        msgs = ((InternalEObject)query).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__QUERY, null, msgs);
      if (newQuery != null)
        msgs = ((InternalEObject)newQuery).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__QUERY, null, msgs);
      msgs = basicSetQuery(newQuery, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__QUERY, newQuery, newQuery));
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
  public void setOrderByEntry(OrOrderByColumn newOrderByEntry)
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__LIM, oldLim, newLim);
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
        msgs = ((InternalEObject)lim).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__LIM, null, msgs);
      if (newLim != null)
        msgs = ((InternalEObject)newLim).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__LIM, null, msgs);
      msgs = basicSetLim(newLim, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__LIM, newLim, newLim));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__OFFSET, oldOffset, newOffset);
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
        msgs = ((InternalEObject)offset).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__OFFSET, null, msgs);
      if (newOffset != null)
        msgs = ((InternalEObject)newOffset).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__OFFSET, null, msgs);
      msgs = basicSetOffset(newOffset, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__OFFSET, newOffset, newOffset));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__FETCH_FIRST, oldFetchFirst, newFetchFirst);
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
        msgs = ((InternalEObject)fetchFirst).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__FETCH_FIRST, null, msgs);
      if (newFetchFirst != null)
        msgs = ((InternalEObject)newFetchFirst).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__FETCH_FIRST, null, msgs);
      msgs = basicSetFetchFirst(newFetchFirst, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__FETCH_FIRST, newFetchFirst, newFetchFirst));
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
      case SqlPackage.MODEL__QUERY:
        return basicSetQuery(null, msgs);
      case SqlPackage.MODEL__ORDER_BY_ENTRY:
        return basicSetOrderByEntry(null, msgs);
      case SqlPackage.MODEL__LIM:
        return basicSetLim(null, msgs);
      case SqlPackage.MODEL__OFFSET:
        return basicSetOffset(null, msgs);
      case SqlPackage.MODEL__FETCH_FIRST:
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
      case SqlPackage.MODEL__QUERY:
        return getQuery();
      case SqlPackage.MODEL__ORDER_BY_ENTRY:
        return getOrderByEntry();
      case SqlPackage.MODEL__LIM:
        return getLim();
      case SqlPackage.MODEL__OFFSET:
        return getOffset();
      case SqlPackage.MODEL__FETCH_FIRST:
        return getFetchFirst();
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
      case SqlPackage.MODEL__QUERY:
        setQuery((SelectQuery)newValue);
        return;
      case SqlPackage.MODEL__ORDER_BY_ENTRY:
        setOrderByEntry((OrOrderByColumn)newValue);
        return;
      case SqlPackage.MODEL__LIM:
        setLim((Limit)newValue);
        return;
      case SqlPackage.MODEL__OFFSET:
        setOffset((Offset)newValue);
        return;
      case SqlPackage.MODEL__FETCH_FIRST:
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
      case SqlPackage.MODEL__QUERY:
        setQuery((SelectQuery)null);
        return;
      case SqlPackage.MODEL__ORDER_BY_ENTRY:
        setOrderByEntry((OrOrderByColumn)null);
        return;
      case SqlPackage.MODEL__LIM:
        setLim((Limit)null);
        return;
      case SqlPackage.MODEL__OFFSET:
        setOffset((Offset)null);
        return;
      case SqlPackage.MODEL__FETCH_FIRST:
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
      case SqlPackage.MODEL__QUERY:
        return query != null;
      case SqlPackage.MODEL__ORDER_BY_ENTRY:
        return orderByEntry != null;
      case SqlPackage.MODEL__LIM:
        return lim != null;
      case SqlPackage.MODEL__OFFSET:
        return offset != null;
      case SqlPackage.MODEL__FETCH_FIRST:
        return fetchFirst != null;
    }
    return super.eIsSet(featureID);
  }

} //ModelImpl
