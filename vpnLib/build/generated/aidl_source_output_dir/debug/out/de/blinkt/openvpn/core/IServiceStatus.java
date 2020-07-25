/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package de.blinkt.openvpn.core;
public interface IServiceStatus extends android.os.IInterface
{
  /** Default implementation for IServiceStatus. */
  public static class Default implements de.blinkt.openvpn.core.IServiceStatus
  {
    /**
              * Registers to receive OpenVPN Status Updates and gets a
              * ParcelFileDescript back that contains the log up to that point
              */
    @Override public android.os.ParcelFileDescriptor registerStatusCallback(de.blinkt.openvpn.core.IStatusCallbacks cb) throws android.os.RemoteException
    {
      return null;
    }
    /**
               * Remove a previously registered callback interface.
               */
    @Override public void unregisterStatusCallback(de.blinkt.openvpn.core.IStatusCallbacks cb) throws android.os.RemoteException
    {
    }
    /**
             * Returns the last connedcted VPN
             */
    @Override public java.lang.String getLastConnectedVPN() throws android.os.RemoteException
    {
      return null;
    }
    /**
              * Sets a cached password
              */
    @Override public void setCachedPassword(java.lang.String uuid, int type, java.lang.String password) throws android.os.RemoteException
    {
    }
    /**
           * Gets the traffic history
           */
    @Override public de.blinkt.openvpn.core.TrafficHistory getTrafficHistory() throws android.os.RemoteException
    {
      return null;
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements de.blinkt.openvpn.core.IServiceStatus
  {
    private static final java.lang.String DESCRIPTOR = "de.blinkt.openvpn.core.IServiceStatus";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an de.blinkt.openvpn.core.IServiceStatus interface,
     * generating a proxy if needed.
     */
    public static de.blinkt.openvpn.core.IServiceStatus asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof de.blinkt.openvpn.core.IServiceStatus))) {
        return ((de.blinkt.openvpn.core.IServiceStatus)iin);
      }
      return new de.blinkt.openvpn.core.IServiceStatus.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
      switch (code)
      {
        case INTERFACE_TRANSACTION:
        {
          reply.writeString(descriptor);
          return true;
        }
        case TRANSACTION_registerStatusCallback:
        {
          data.enforceInterface(descriptor);
          de.blinkt.openvpn.core.IStatusCallbacks _arg0;
          _arg0 = de.blinkt.openvpn.core.IStatusCallbacks.Stub.asInterface(data.readStrongBinder());
          android.os.ParcelFileDescriptor _result = this.registerStatusCallback(_arg0);
          reply.writeNoException();
          if ((_result!=null)) {
            reply.writeInt(1);
            _result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
          }
          else {
            reply.writeInt(0);
          }
          return true;
        }
        case TRANSACTION_unregisterStatusCallback:
        {
          data.enforceInterface(descriptor);
          de.blinkt.openvpn.core.IStatusCallbacks _arg0;
          _arg0 = de.blinkt.openvpn.core.IStatusCallbacks.Stub.asInterface(data.readStrongBinder());
          this.unregisterStatusCallback(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_getLastConnectedVPN:
        {
          data.enforceInterface(descriptor);
          java.lang.String _result = this.getLastConnectedVPN();
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        case TRANSACTION_setCachedPassword:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          int _arg1;
          _arg1 = data.readInt();
          java.lang.String _arg2;
          _arg2 = data.readString();
          this.setCachedPassword(_arg0, _arg1, _arg2);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_getTrafficHistory:
        {
          data.enforceInterface(descriptor);
          de.blinkt.openvpn.core.TrafficHistory _result = this.getTrafficHistory();
          reply.writeNoException();
          if ((_result!=null)) {
            reply.writeInt(1);
            _result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
          }
          else {
            reply.writeInt(0);
          }
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements de.blinkt.openvpn.core.IServiceStatus
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public java.lang.String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      /**
                * Registers to receive OpenVPN Status Updates and gets a
                * ParcelFileDescript back that contains the log up to that point
                */
      @Override public android.os.ParcelFileDescriptor registerStatusCallback(de.blinkt.openvpn.core.IStatusCallbacks cb) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        android.os.ParcelFileDescriptor _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_registerStatusCallback, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().registerStatusCallback(cb);
          }
          _reply.readException();
          if ((0!=_reply.readInt())) {
            _result = android.os.ParcelFileDescriptor.CREATOR.createFromParcel(_reply);
          }
          else {
            _result = null;
          }
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
                 * Remove a previously registered callback interface.
                 */
      @Override public void unregisterStatusCallback(de.blinkt.openvpn.core.IStatusCallbacks cb) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_unregisterStatusCallback, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().unregisterStatusCallback(cb);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /**
               * Returns the last connedcted VPN
               */
      @Override public java.lang.String getLastConnectedVPN() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getLastConnectedVPN, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getLastConnectedVPN();
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      /**
                * Sets a cached password
                */
      @Override public void setCachedPassword(java.lang.String uuid, int type, java.lang.String password) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(uuid);
          _data.writeInt(type);
          _data.writeString(password);
          boolean _status = mRemote.transact(Stub.TRANSACTION_setCachedPassword, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().setCachedPassword(uuid, type, password);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /**
             * Gets the traffic history
             */
      @Override public de.blinkt.openvpn.core.TrafficHistory getTrafficHistory() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        de.blinkt.openvpn.core.TrafficHistory _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getTrafficHistory, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getTrafficHistory();
          }
          _reply.readException();
          if ((0!=_reply.readInt())) {
            _result = de.blinkt.openvpn.core.TrafficHistory.CREATOR.createFromParcel(_reply);
          }
          else {
            _result = null;
          }
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      public static de.blinkt.openvpn.core.IServiceStatus sDefaultImpl;
    }
    static final int TRANSACTION_registerStatusCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_unregisterStatusCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_getLastConnectedVPN = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_setCachedPassword = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    static final int TRANSACTION_getTrafficHistory = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
    public static boolean setDefaultImpl(de.blinkt.openvpn.core.IServiceStatus impl) {
      if (Stub.Proxy.sDefaultImpl == null && impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static de.blinkt.openvpn.core.IServiceStatus getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  /**
            * Registers to receive OpenVPN Status Updates and gets a
            * ParcelFileDescript back that contains the log up to that point
            */
  public android.os.ParcelFileDescriptor registerStatusCallback(de.blinkt.openvpn.core.IStatusCallbacks cb) throws android.os.RemoteException;
  /**
             * Remove a previously registered callback interface.
             */
  public void unregisterStatusCallback(de.blinkt.openvpn.core.IStatusCallbacks cb) throws android.os.RemoteException;
  /**
           * Returns the last connedcted VPN
           */
  public java.lang.String getLastConnectedVPN() throws android.os.RemoteException;
  /**
            * Sets a cached password
            */
  public void setCachedPassword(java.lang.String uuid, int type, java.lang.String password) throws android.os.RemoteException;
  /**
         * Gets the traffic history
         */
  public de.blinkt.openvpn.core.TrafficHistory getTrafficHistory() throws android.os.RemoteException;
}
