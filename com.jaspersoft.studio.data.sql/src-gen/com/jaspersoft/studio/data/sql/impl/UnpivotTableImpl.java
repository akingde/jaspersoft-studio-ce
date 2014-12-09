/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.PivotColumns;
import com.jaspersoft.studio.data.sql.PivotForClause;
import com.jaspersoft.studio.data.sql.SqlPackage;
import com.jaspersoft.studio.data.sql.UnpivotInClause;
import com.jaspersoft.studio.data.sql.UnpivotTable;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unpivot Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.UnpivotTableImpl#getPcols <em>Pcols</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.UnpivotTableImpl#getPfor <em>Pfor</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.UnpivotTableImpl#getInop <em>Inop</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UnpivotTableImpl extends MinimalEObjectImpl.Container implements UnpivotTable
{
  /**
   * The cached value of the '{@link #getPcols() <em>Pcols</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPcols()
   * @generated
   * @ordered
   */
  protected PivotColumns pcols;

  /**
   * The cached value of the '{@link #getPfor() <em>Pfor</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPfor()
   * @generated
   * @ordered
   */
  protected PivotForClause pfor;

  /**
   * The cached value of the '{@link #getInop() <em>Inop</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInop()
   * @generated
   * @ordered
   */
  protected UnpivotInClause inop;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected UnpivotTableImpl()
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
    return SqlPackage.Literals.UNPIVOT_TABLE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PivotColumns getPcols()
  {
    return pcols;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPcols(PivotColumns newPcols, NotificationChain msgs)
  {
    PivotColumns oldPcols = pcols;
    pcols = newPcols;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.UNPIVOT_TABLE__PCOLS, oldPcols, newPcols);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPcols(PivotColumns newPcols)
  {
    if (newPcols != pcols)
    {
      NotificationChain msgs = null;
      if (pcols != null)
        msgs = ((InternalEObject)pcols).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.UNPIVOT_TABLE__PCOLS, null, msgs);
      if (newPcols != null)
        msgs = ((InternalEObject)newPcols).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.UNPIVOT_TABLE__PCOLS, null, msgs);
      msgs = basicSetPcols(newPcols, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.UNPIVOT_TABLE__PCOLS, newPcols, newPcols));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PivotForClause getPfor()
  {
    return pfor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPfor(PivotForClause newPfor, NotificationChain msgs)
  {
    PivotForClause oldPfor = pfor;
    pfor = newPfor;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.UNPIVOT_TABLE__PFOR, oldPfor, newPfor);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPfor(PivotForClause newPfor)
  {
    if (newPfor != pfor)
    {
      NotificationChain msgs = null;
      if (pfor != null)
        msgs = ((InternalEObject)pfor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.UNPIVOT_TABLE__PFOR, null, msgs);
      if (newPfor != null)
        msgs = ((InternalEObject)newPfor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.UNPIVOT_TABLE__PFOR, null, msgs);
      msgs = basicSetPfor(newPfor, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.UNPIVOT_TABLE__PFOR, newPfor, newPfor));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnpivotInClause getInop()
  {
    return inop;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetInop(UnpivotInClause newInop, NotificationChain msgs)
  {
    UnpivotInClause oldInop = inop;
    inop = newInop;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.UNPIVOT_TABLE__INOP, oldInop, newInop);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInop(UnpivotInClause newInop)
  {
    if (newInop != inop)
    {
      NotificationChain msgs = null;
      if (inop != null)
        msgs = ((InternalEObject)inop).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.UNPIVOT_TABLE__INOP, null, msgs);
      if (newInop != null)
        msgs = ((InternalEObject)newInop).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.UNPIVOT_TABLE__INOP, null, msgs);
      msgs = basicSetInop(newInop, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.UNPIVOT_TABLE__INOP, newInop, newInop));
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
      case SqlPackage.UNPIVOT_TABLE__PCOLS:
        return basicSetPcols(null, msgs);
      case SqlPackage.UNPIVOT_TABLE__PFOR:
        return basicSetPfor(null, msgs);
      case SqlPackage.UNPIVOT_TABLE__INOP:
        return basicSetInop(null, msgs);
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
      case SqlPackage.UNPIVOT_TABLE__PCOLS:
        return getPcols();
      case SqlPackage.UNPIVOT_TABLE__PFOR:
        return getPfor();
      case SqlPackage.UNPIVOT_TABLE__INOP:
        return getInop();
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
      case SqlPackage.UNPIVOT_TABLE__PCOLS:
        setPcols((PivotColumns)newValue);
        return;
      case SqlPackage.UNPIVOT_TABLE__PFOR:
        setPfor((PivotForClause)newValue);
        return;
      case SqlPackage.UNPIVOT_TABLE__INOP:
        setInop((UnpivotInClause)newValue);
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
      case SqlPackage.UNPIVOT_TABLE__PCOLS:
        setPcols((PivotColumns)null);
        return;
      case SqlPackage.UNPIVOT_TABLE__PFOR:
        setPfor((PivotForClause)null);
        return;
      case SqlPackage.UNPIVOT_TABLE__INOP:
        setInop((UnpivotInClause)null);
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
      case SqlPackage.UNPIVOT_TABLE__PCOLS:
        return pcols != null;
      case SqlPackage.UNPIVOT_TABLE__PFOR:
        return pfor != null;
      case SqlPackage.UNPIVOT_TABLE__INOP:
        return inop != null;
    }
    return super.eIsSet(featureID);
  }

} //UnpivotTableImpl
