package de.hdm.itp.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The Interface LoginServiceAsync.
 */
public interface LoginServiceAsync {
  
  /**
   * Login.
   *
   * @param requestUri the request uri
   * @param async the async
   */
  public void login(String requestUri, AsyncCallback<LoginInfo> async);
}
