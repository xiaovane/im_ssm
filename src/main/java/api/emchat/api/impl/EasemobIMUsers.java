package api.emchat.api.impl;


import api.emchat.api.IMUserAPI;
import api.emchat.comm.EasemobAPI;
import api.emchat.comm.OrgInfo;
import api.emchat.comm.ResponseHandler;
import api.emchat.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.api.UsersApi;
import io.swagger.client.model.NewPassword;
import io.swagger.client.model.Nickname;
import io.swagger.client.model.RegisterUsers;
import io.swagger.client.model.UserNames;


public class EasemobIMUsers  implements IMUserAPI {

	private UsersApi api = new UsersApi();
	private ResponseHandler responseHandler = new ResponseHandler();
	public Object createNewIMUserSingle(final Object payload) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersPost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME, (RegisterUsers) payload,TokenUtil.getAccessToken());
			}
		});
	}

	public Object createNewIMUserBatch(final Object payload) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersPost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME, (RegisterUsers) payload,TokenUtil.getAccessToken());
			}
		});
	}

	public Object getIMUserByUserName(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
		}
		});
	}

	public Object getIMUsersBatch(final Long limit,final String cursor) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),limit+"",cursor);
			}
		});
	}

	public Object deleteIMUserByUserName(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameDelete(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	public Object deleteIMUserBatch(final Long limit,final String cursor) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersDelete(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),limit+"",cursor);
			}
		});
	}

	public Object modifyIMUserPasswordWithAdminToken(final String userName, final Object payload) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernamePasswordPut(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,userName, (NewPassword) payload,TokenUtil.getAccessToken());
			}
		});
	}

	public Object modifyIMUserNickNameWithAdminToken(final String userName,final Object payload) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernamePut(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,userName, (Nickname) payload,TokenUtil.getAccessToken());
			}
		});
	}

	public Object addFriendSingle(final String userName,final String friendName) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernamePost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName,friendName);
			}
		});
	}

	public Object deleteFriendSingle(final String userName,final String friendName) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernameDelete(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName,friendName);
			}
		});
	}

	public Object getFriends(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameContactsUsersGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	public Object getBlackList(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameBlocksUsersGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	public Object addToBlackList(final String userName,final Object payload) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameBlocksUsersPost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName, (UserNames) payload);
			}
		});
	}

	public Object removeFromBlackList(final String userName,final String blackListName) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameBlocksUsersBlockedUsernameDelete(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName,blackListName);
			}
		});
	}

	public Object getIMUserStatus(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameStatusGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	public Object getOfflineMsgCount(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameOfflineMsgCountGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	public Object getSpecifiedOfflineMsgStatus(final String userName,final String msgId) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameOfflineMsgStatusMsgIdGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName,msgId);
			}
		});
	}

	public Object deactivateIMUser(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameDeactivatePost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	public Object activateIMUser(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameActivatePost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	public Object disconnectIMUser(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameDisconnectGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	public Object getIMUserAllChatGroups(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameJoinedChatgroupsGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	public Object getIMUserAllChatRooms(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameJoinedChatroomsGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}
}
