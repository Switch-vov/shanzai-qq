/**
 * ���ܣ������û��������
 */
package com.qq.client.tools;

import java.util.*;

import com.qq.client.view.QQChat;

public class ManageQQChat {
	private static HashMap<String, QQChat> hm = new HashMap<String, QQChat>();

	// ����һ���������
	public static void addQQChat(String loginIdAndFriendId, QQChat qqChat) {
		hm.put(loginIdAndFriendId, qqChat);
	}

	// ��ȡһ���������
	public static QQChat getQQChat(String loginIdAndFriendId) {
		return (QQChat) hm.get(loginIdAndFriendId);
	}
}
