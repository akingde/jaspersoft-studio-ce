/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.SelectQuery;
import com.jaspersoft.studio.data.sql.SqlPackage;
import com.jaspersoft.studio.data.sql.WithColumns;
import com.jaspersoft.studio.data.sql.WithQuery;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>With Query</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.WithQueryImpl#getW <em>W</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.WithQueryImpl#getWname <em>Wname</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.WithQueryImpl#getWithCols <em>With Cols</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.WithQueryImpl#getQuery <em>Query</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WithQueryImpl extends MinimalEObjectImpl.Container implements WithQuery
{
  /**
   * The default value of the '{@link #getW() <em>W</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getW()
   * @generated
   * @ordered
   */
  protected static final String W_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getW() <em>W</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getW()
   * @generated
   * @ordered
   */
  protected String w = W_EDEFAULT;

  /**
   * The default value of the '{@link #getWname() <em>Wname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWname()
   * @generated
   * @ordered
   */
  protected static final String WNAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getWname() <em>Wname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWname()
   * @generated
   * @ordered
   */
  protected String wname = WNAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getWithCols() <em>With Cols</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWithCols()
   * @generated
   * @ordered
   */
  protected WithColumns withCols;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected WithQueryImpl()
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
    return SqlPackage.Literals.WITH_QUERY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getW()
  {
    return w;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setW(String newW)
  {
    String oldW = w;
    w = newW;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.WITH_QUERY__W, oldW, w));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getWname()
  {
    return wname;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWname(String newWname)
  {
    String oldWname = wname;
    wname = newWname;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.WITH_QUERY__WNAME, oldWname, wname));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WithColumns getWithCols()
  {
    return withCols;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWithCols(WithColumns newWithCols, NotificationChain msgs)
  {
    WithColumns oldWithCols = withCols;
    withCols = newWithCols;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.WITH_QUERY__WITH_COLS, oldWithCols, newWithCols);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWithCols(WithColumns newWithCols)
  {
    if (newWithCols != withCols)
    {
      NotificationChain msgs = null;
      if (withCols != null)
        msgs = ((InternalEObject)withCols).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.WITH_QUERY__WITH_COLS, null, msgs);
      if (newWithCols != null)
        msgs = ((InternalEObject)newWithCols).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.WITH_QUERY__WITH_COLS, null, msgs);
      msgs = basicSetWithCols(newWithCols, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.WITH_QUERY__WITH_COLS, newWithCols, newWithCols));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.WITH_QUERY__QUERY, oldQuery, newQuery);
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
        msgs = ((InternalEObject)query).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.WITH_QUERY__QUERY, null, msgs);
      if (newQuery != null)
        msgs = ((InternalEObject)newQuery).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.WITH_QUERY__QUERY, null, msgs);
      msgs = basicSetQuery(newQuery, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.WITH_QUERY__QUERY, newQuery, newQuery));
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
      case SqlPackage.WITH_QUERY__WITH_COLS:
        return basicSetWithCols(null, msgs);
      case SqlPackage.WITH_QUERY__QUERY:
        return basicSetQuery(null, msgs);
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
      case SqlPackage.WITH_QUERY__W:
        return getW();
      case SqlPackage.WITH_QUERY__WNAME:
        return getWname();
      case SqlPackage.WITH_QUERY__WITH_COLS:
        return getWithCols();
      case SqlPackage.WITH_QUERY__QUERY:
        return getQuery();
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
      case SqlPackage.WITH_QUERY__W:
        setW((String)newValue);
        return;
      case SqlPackage.WITH_QUERY__WNAME:
        setWname((String)newValue);
        return;
      case SqlPackage.WITH_QUERY__WITH_COLS:
        setWithCols((WithColumns)newValue);
        return;
      case SqlPackage.WITH_QUERY__QUERY:
        setQuery((SelectQuery)newValue);
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
      case SqlPackage.WITH_QUERY__W:
        setW(W_EDEFAULT);
        return;
      case SqlPackage.WITH_QUERY__WNAME:
        setWname(WNAME_EDEFAULT);
        return;
      case SqlPackage.WITH_QUERY__WITH_COLS:
        setWithCols((WithColumns)null);
        return;
      case SqlPackage.WITH_QUERY__QUERY:
        setQuery((SelectQuery)null);
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
      case SqlPackage.WITH_QUERY__W:
        return W_EDEFAULT == null ? w != null : !W_EDEFAULT.equals(w);
      case SqlPackage.WITH_QUERY__WNAME:
        return WNAME_EDEFAULT == null ? wname != null : !WNAME_EDEFAULT.equals(wname);
      case SqlPackage.WITH_QUERY__WITH_COLS:
        return withCols != null;
      case SqlPackage.WITH_QUERY__QUERY:
        return query != null;
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
    result.append(" (w: ");
    result.append(w);
    result.append(", wname: ");
    result.append(wname);
    result.append(')');
    return result.toString();
  }

} //WithQueryImpl
