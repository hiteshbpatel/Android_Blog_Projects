package com.acadgild.android.volley.network;


import com.acadgild.android.volley.utils.CommonUtilities;

/**
 * @author Preetika
 *
 */
public interface OnWebServiceResult {
	public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type);
}
