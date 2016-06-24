/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.ScalarOperand;
import com.jaspersoft.studio.data.sql.SqlPackage;

import java.math.BigDecimal;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scalar Operand</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ScalarOperandImpl#getSostr <em>Sostr</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ScalarOperandImpl#getSodbl <em>Sodbl</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ScalarOperandImpl#getSodate <em>Sodate</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ScalarOperandImpl#getSotime <em>Sotime</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ScalarOperandImpl#getSodt <em>Sodt</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ScalarOperandImpl#getSoUInt <em>So UInt</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ScalarOperandImpl#getSoint <em>Soint</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScalarOperandImpl extends RowValueImpl implements ScalarOperand
{
  /**
   * The default value of the '{@link #getSostr() <em>Sostr</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSostr()
   * @generated
   * @ordered
   */
  protected static final String SOSTR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSostr() <em>Sostr</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSostr()
   * @generated
   * @ordered
   */
  protected String sostr = SOSTR_EDEFAULT;

  /**
   * The default value of the '{@link #getSodbl() <em>Sodbl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSodbl()
   * @generated
   * @ordered
   */
  protected static final BigDecimal SODBL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSodbl() <em>Sodbl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSodbl()
   * @generated
   * @ordered
   */
  protected BigDecimal sodbl = SODBL_EDEFAULT;

  /**
   * The default value of the '{@link #getSodate() <em>Sodate</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSodate()
   * @generated
   * @ordered
   */
  protected static final String SODATE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSodate() <em>Sodate</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSodate()
   * @generated
   * @ordered
   */
  protected String sodate = SODATE_EDEFAULT;

  /**
   * The default value of the '{@link #getSotime() <em>Sotime</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSotime()
   * @generated
   * @ordered
   */
  protected static final String SOTIME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSotime() <em>Sotime</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSotime()
   * @generated
   * @ordered
   */
  protected String sotime = SOTIME_EDEFAULT;

  /**
   * The default value of the '{@link #getSodt() <em>Sodt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSodt()
   * @generated
   * @ordered
   */
  protected static final String SODT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSodt() <em>Sodt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSodt()
   * @generated
   * @ordered
   */
  protected String sodt = SODT_EDEFAULT;

  /**
   * The default value of the '{@link #getSoUInt() <em>So UInt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSoUInt()
   * @generated
   * @ordered
   */
  protected static final Integer SO_UINT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSoUInt() <em>So UInt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSoUInt()
   * @generated
   * @ordered
   */
  protected Integer soUInt = SO_UINT_EDEFAULT;

  /**
   * The default value of the '{@link #getSoint() <em>Soint</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSoint()
   * @generated
   * @ordered
   */
  protected static final Integer SOINT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSoint() <em>Soint</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSoint()
   * @generated
   * @ordered
   */
  protected Integer soint = SOINT_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ScalarOperandImpl()
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
    return SqlPackage.Literals.SCALAR_OPERAND;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSostr()
  {
    return sostr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSostr(String newSostr)
  {
    String oldSostr = sostr;
    sostr = newSostr;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SCALAR_OPERAND__SOSTR, oldSostr, sostr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigDecimal getSodbl()
  {
    return sodbl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSodbl(BigDecimal newSodbl)
  {
    BigDecimal oldSodbl = sodbl;
    sodbl = newSodbl;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SCALAR_OPERAND__SODBL, oldSodbl, sodbl));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSodate()
  {
    return sodate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSodate(String newSodate)
  {
    String oldSodate = sodate;
    sodate = newSodate;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SCALAR_OPERAND__SODATE, oldSodate, sodate));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSotime()
  {
    return sotime;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSotime(String newSotime)
  {
    String oldSotime = sotime;
    sotime = newSotime;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SCALAR_OPERAND__SOTIME, oldSotime, sotime));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSodt()
  {
    return sodt;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSodt(String newSodt)
  {
    String oldSodt = sodt;
    sodt = newSodt;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SCALAR_OPERAND__SODT, oldSodt, sodt));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Integer getSoUInt()
  {
    return soUInt;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSoUInt(Integer newSoUInt)
  {
    Integer oldSoUInt = soUInt;
    soUInt = newSoUInt;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SCALAR_OPERAND__SO_UINT, oldSoUInt, soUInt));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Integer getSoint()
  {
    return soint;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSoint(Integer newSoint)
  {
    Integer oldSoint = soint;
    soint = newSoint;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SCALAR_OPERAND__SOINT, oldSoint, soint));
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
      case SqlPackage.SCALAR_OPERAND__SOSTR:
        return getSostr();
      case SqlPackage.SCALAR_OPERAND__SODBL:
        return getSodbl();
      case SqlPackage.SCALAR_OPERAND__SODATE:
        return getSodate();
      case SqlPackage.SCALAR_OPERAND__SOTIME:
        return getSotime();
      case SqlPackage.SCALAR_OPERAND__SODT:
        return getSodt();
      case SqlPackage.SCALAR_OPERAND__SO_UINT:
        return getSoUInt();
      case SqlPackage.SCALAR_OPERAND__SOINT:
        return getSoint();
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
      case SqlPackage.SCALAR_OPERAND__SOSTR:
        setSostr((String)newValue);
        return;
      case SqlPackage.SCALAR_OPERAND__SODBL:
        setSodbl((BigDecimal)newValue);
        return;
      case SqlPackage.SCALAR_OPERAND__SODATE:
        setSodate((String)newValue);
        return;
      case SqlPackage.SCALAR_OPERAND__SOTIME:
        setSotime((String)newValue);
        return;
      case SqlPackage.SCALAR_OPERAND__SODT:
        setSodt((String)newValue);
        return;
      case SqlPackage.SCALAR_OPERAND__SO_UINT:
        setSoUInt((Integer)newValue);
        return;
      case SqlPackage.SCALAR_OPERAND__SOINT:
        setSoint((Integer)newValue);
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
      case SqlPackage.SCALAR_OPERAND__SOSTR:
        setSostr(SOSTR_EDEFAULT);
        return;
      case SqlPackage.SCALAR_OPERAND__SODBL:
        setSodbl(SODBL_EDEFAULT);
        return;
      case SqlPackage.SCALAR_OPERAND__SODATE:
        setSodate(SODATE_EDEFAULT);
        return;
      case SqlPackage.SCALAR_OPERAND__SOTIME:
        setSotime(SOTIME_EDEFAULT);
        return;
      case SqlPackage.SCALAR_OPERAND__SODT:
        setSodt(SODT_EDEFAULT);
        return;
      case SqlPackage.SCALAR_OPERAND__SO_UINT:
        setSoUInt(SO_UINT_EDEFAULT);
        return;
      case SqlPackage.SCALAR_OPERAND__SOINT:
        setSoint(SOINT_EDEFAULT);
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
      case SqlPackage.SCALAR_OPERAND__SOSTR:
        return SOSTR_EDEFAULT == null ? sostr != null : !SOSTR_EDEFAULT.equals(sostr);
      case SqlPackage.SCALAR_OPERAND__SODBL:
        return SODBL_EDEFAULT == null ? sodbl != null : !SODBL_EDEFAULT.equals(sodbl);
      case SqlPackage.SCALAR_OPERAND__SODATE:
        return SODATE_EDEFAULT == null ? sodate != null : !SODATE_EDEFAULT.equals(sodate);
      case SqlPackage.SCALAR_OPERAND__SOTIME:
        return SOTIME_EDEFAULT == null ? sotime != null : !SOTIME_EDEFAULT.equals(sotime);
      case SqlPackage.SCALAR_OPERAND__SODT:
        return SODT_EDEFAULT == null ? sodt != null : !SODT_EDEFAULT.equals(sodt);
      case SqlPackage.SCALAR_OPERAND__SO_UINT:
        return SO_UINT_EDEFAULT == null ? soUInt != null : !SO_UINT_EDEFAULT.equals(soUInt);
      case SqlPackage.SCALAR_OPERAND__SOINT:
        return SOINT_EDEFAULT == null ? soint != null : !SOINT_EDEFAULT.equals(soint);
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
    result.append(" (sostr: ");
    result.append(sostr);
    result.append(", sodbl: ");
    result.append(sodbl);
    result.append(", sodate: ");
    result.append(sodate);
    result.append(", sotime: ");
    result.append(sotime);
    result.append(", sodt: ");
    result.append(sodt);
    result.append(", soUInt: ");
    result.append(soUInt);
    result.append(", soint: ");
    result.append(soint);
    result.append(')');
    return result.toString();
  }

} //ScalarOperandImpl
