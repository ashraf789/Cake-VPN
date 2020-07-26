/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package de.blinkt.openvpn.core;
/**
 * Created by arne on 15.11.16.
 */
public interface IOpenVPNServiceInternal extends android.os.IInterface
{
  /** Default implementation for IOpenVPNServiceInternal. */
  public static class Default implements de.blinkt.openvpn.core.IOpenVPNServiceInternal
  {
    @Override public boolean protect(int fd) throws android.os.RemoteException
    {
      return false;
    }
    @Override public void userPause(boolean b) throws android.os.RemoteException
    {
    }
    /**
         * @param replaceConnection True if the VPN is connected by a new connection.
         * @return true if there was a process that has been send a stop signal
         */
    @Override public boolean stopVPN(boolean replaceConnection) throws android.os.RemoteException
    {
      return false;
    }
    @Override public void addAllowedExternalApp(java.lang.String packagename) throws android.os.RemoteException
    {
    }
    @Override public boolean isAllowedExternalApp(java.lang.String packagename) throws android.os.RemoteException
    {
      return false;
    }
    @Override public void challengeResponse(java.lang.String repsonse) throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements de.blinkt.openvpn.core.IOpenVPNServiceInternal
  {
    private static final java.lang.String DESCRIPTOR = "de.blinkt.openvpn.core.IOpenVPNServiceInternal";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an de.blinkt.openvpn.core.IOpenVPNServiceInternal interface,
     * generating a proxy if needed.
     */
    public static de.blinkt.openvpn.core.IOpenVPNServiceInternal asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof de.blinkt.openvpn.core.IOpenVPNServiceInternal))) {
        return ((de.blinkt.openvpn.core.IOpenVPNServiceInternal)iin);
      }
      return new de.blinkt.openvpn.core.IOpenVPNServiceInternal.Stub.Proxy(obj);
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
        case TRANSACTION_protect:
        {
          data.enforceInterface(descriptor);
          int _arg0;
          _arg0 = data.readInt();
          boolean _result = this.protect(_arg0);
          reply.writeNoException();
          reply.writeInt(((_result)?(1):(0)));
          return true;
        }
        case TRANSACTION_userPause:
        {
          data.enforceInterface(descriptor);
          boolean _arg0;
          _arg0 = (0!=data.readInt());
          this.userPause(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_stopVPN:
        {
          data.enforceInterface(descriptor);
          boolean _arg0;
          _arg0 = (0!=data.readInt());
          boolean _result = this.stopVPN(_arg0);
          reply.writeNoException();
          reply.writeInt(((_result)?(1):(0)));
          return true;
        }
        case TRANSACTION_addAllowedExternalApp:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          this.addAllowedExternalApp(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_isAllowedExternalApp:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          boolean _result = this.isAllowedExternalApp(_arg0);
          reply.writeNoException();
          reply.writeInt(((_result)?(1):(0)));
          return true;
        }
        case TRANSACTION_challengeResponse:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          this.challengeResponse(_arg0);
          reply.writeNoException();
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements de.blinkt.openvpn.core.IOpenVPNServiceInternal
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
      @Override public boolean protect(int fd) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        boolean _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(fd);
          boolean _status = mRemote.transact(Stub.TRANSACTION_protect, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().protect(fd);
          }
          _reply.readException();
          _result = (0!=_reply.readInt());
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public void userPause(boolean b) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(((b)?(1):(0)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_userPause, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().userPause(b);
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
           * @param replaceConnection True if the VPN is connected by a new connection.
           * @return true if there was a process that has been send a stop signal
           */
      @Override public boolean stopVPN(boolean replaceConnection) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        boolean _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(((replaceConnection)?(1):(0)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_stopVPN, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().stopVPN(replaceConnection);
          }
          _reply.readException();
          _result = (0!=_reply.readInt());
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public void addAllowedExternalApp(java.lang.String packagename) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(packagename);
          boolean _status = mRemote.transact(Stub.TRANSACTION_addAllowedExternalApp, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().addAllowedExternalApp(packagename);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public boolean isAllowedExternalApp(java.lang.String packagename) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        boolean _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(packagename);
          boolean _status = mRemote.transact(Stub.TRANSACTION_isAllowedExternalApp, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().isAllowedExternalApp(packagename);
          }
          _reply.readException();
          _result = (0!=_reply.readInt());
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public void challengeResponse(java.lang.String repsonse) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(repsonse);
          boolean _status = mRemote.transact(Stub.TRANSACTION_challengeResponse, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().challengeResponse(repsonse);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      public static de.blinkt.openvpn.core.IOpenVPNServiceInternal sDefaultImpl;
    }
    static final int TRANSACTION_protect = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_userPause = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_stopVPN = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_addAllowedExternalApp = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    static final int TRANSACTION_isAllowedExternalApp = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
    static final int TRANSACTION_challengeResponse = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
    public static boolean setDefaultImpl(de.blinkt.openvpn.core.IOpenVPNServiceInternal impl) {
      if (Stub.Proxy.sDefaultImpl == null && impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static de.blinkt.openvpn.core.IOpenVPNServiceInternal getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  public boolean protect(int fd) throws android.os.RemoteException;
  public void userPause(boolean b) throws android.os.RemoteException;
  /**
       * @param replaceConnection True if the VPN is connected by a new connection.
       * @return true if there was a process that has been send a stop signal
       */
  public boolean stopVPN(boolean replaceConnection) throws android.os.RemoteException;
  public void addAllowedExternalApp(java.lang.String packagename) throws android.os.RemoteException;
  public boolean isAllowedExternalApp(java.lang.String packagename) throws android.os.RemoteException;
  public void challengeResponse(java.lang.String repsonse) throws android.os.RemoteException;
}
