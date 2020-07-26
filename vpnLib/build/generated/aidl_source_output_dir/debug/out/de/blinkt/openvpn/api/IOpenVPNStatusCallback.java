/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package de.blinkt.openvpn.api;
/**
 * Example of a callback interface used by IRemoteService to send
 * synchronous notifications back to its clients.  Note that this is a
 * one-way interface so the server does not block waiting for the client.
 */
public interface IOpenVPNStatusCallback extends android.os.IInterface
{
  /** Default implementation for IOpenVPNStatusCallback. */
  public static class Default implements de.blinkt.openvpn.api.IOpenVPNStatusCallback
  {
    /**
         * Called when the service has a new status for you.
         */
    @Override public void newStatus(java.lang.String uuid, java.lang.String state, java.lang.String message, java.lang.String level) throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements de.blinkt.openvpn.api.IOpenVPNStatusCallback
  {
    private static final java.lang.String DESCRIPTOR = "de.blinkt.openvpn.api.IOpenVPNStatusCallback";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an de.blinkt.openvpn.api.IOpenVPNStatusCallback interface,
     * generating a proxy if needed.
     */
    public static de.blinkt.openvpn.api.IOpenVPNStatusCallback asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof de.blinkt.openvpn.api.IOpenVPNStatusCallback))) {
        return ((de.blinkt.openvpn.api.IOpenVPNStatusCallback)iin);
      }
      return new de.blinkt.openvpn.api.IOpenVPNStatusCallback.Stub.Proxy(obj);
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
        case TRANSACTION_newStatus:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          java.lang.String _arg1;
          _arg1 = data.readString();
          java.lang.String _arg2;
          _arg2 = data.readString();
          java.lang.String _arg3;
          _arg3 = data.readString();
          this.newStatus(_arg0, _arg1, _arg2, _arg3);
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements de.blinkt.openvpn.api.IOpenVPNStatusCallback
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
           * Called when the service has a new status for you.
           */
      @Override public void newStatus(java.lang.String uuid, java.lang.String state, java.lang.String message, java.lang.String level) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(uuid);
          _data.writeString(state);
          _data.writeString(message);
          _data.writeString(level);
          boolean _status = mRemote.transact(Stub.TRANSACTION_newStatus, _data, null, android.os.IBinder.FLAG_ONEWAY);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().newStatus(uuid, state, message, level);
            return;
          }
        }
        finally {
          _data.recycle();
        }
      }
      public static de.blinkt.openvpn.api.IOpenVPNStatusCallback sDefaultImpl;
    }
    static final int TRANSACTION_newStatus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    public static boolean setDefaultImpl(de.blinkt.openvpn.api.IOpenVPNStatusCallback impl) {
      if (Stub.Proxy.sDefaultImpl == null && impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static de.blinkt.openvpn.api.IOpenVPNStatusCallback getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  /**
       * Called when the service has a new status for you.
       */
  public void newStatus(java.lang.String uuid, java.lang.String state, java.lang.String message, java.lang.String level) throws android.os.RemoteException;
}
