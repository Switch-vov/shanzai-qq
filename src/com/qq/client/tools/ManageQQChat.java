/**
 * 功能：管理用户聊天界面
 */
package com.qq.client.tools;

import java.util.*;

import com.qq.client.view.QQChat;

public class ManageQQChat {
	private static HashMap<String, QQChat> hm = new HashMap<String, QQChat>();

	// 加入一个聊天界面
	public static void addQQChat(String loginIdAndFriendId, QQChat qqChat) {
		hm.put(loginIdAndFriendId, qqChat);
	}

	// 获取一个聊天界面
	public static QQChat getQQChat(String loginIdAndFriendId) {
		return (QQChat) hm.get(loginIdAndFriendId);
	}
}
