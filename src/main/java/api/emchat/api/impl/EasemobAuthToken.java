package api.emchat.api.impl;

import api.emchat.api.AuthTokenAPI;
import api.emchat.comm.TokenUtil;


public class EasemobAuthToken implements AuthTokenAPI{

	public Object getAuthToken(){
		return TokenUtil.getAccessToken();
	}
}
