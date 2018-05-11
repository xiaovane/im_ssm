package api.emchat.api.impl;

import api.emchat.api.SendMessageAPI;
import api.emchat.comm.EasemobAPI;
import api.emchat.comm.OrgInfo;
import api.emchat.comm.ResponseHandler;
import api.emchat.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.api.MessagesApi;
import io.swagger.client.model.Msg;

public class EasemobSendMessage implements SendMessageAPI {
    private ResponseHandler responseHandler = new ResponseHandler();
    private MessagesApi api = new MessagesApi();
    
    public Object sendMessage(final Object payload) {
        return responseHandler.handle(new EasemobAPI() {
            
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameMessagesPost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(), (Msg) payload);
            }
        });
    }
}
