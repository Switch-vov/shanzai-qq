/**
 * ������ѡ�İ���ˡ�����������
 */
package com.qq.client.tools;

import java.util.*;
import com.qq.client.view.QQFriendList;

public class ManageQQFriendList {
	private static HashMap<String, QQFriendList> hm = new HashMap<String, QQFriendList>();

	// ��������ӵ�������
	public static void addQQFriendList(String uid, QQFriendList qqFriendList) {
		hm.put(uid, qqFriendList);
	}

	// �Ӽ����л�ȡ����
	public static QQFriendList getQQFriendList(String uid) {
		return (QQFriendList) hm.get(uid);
	}
}
