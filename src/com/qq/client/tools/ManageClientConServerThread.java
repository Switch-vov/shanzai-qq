/**
 * ���ܣ�����ͻ��˺ͷ����������߳�
 */
package com.qq.client.tools;

import java.util.*;

public class ManageClientConServerThread {
	// �ͻ�������������ӵļ���
	private static HashMap<String, ClientConServerThread> hm = new HashMap<String, ClientConServerThread>();

	// �Ѵ����õ�ClientConServerThread�������hm
	public static void addClientConServerThread(String uid, ClientConServerThread ccst) {
		hm.put(uid, ccst);
	}

	// ͨ��uidȡ���߳�
	public static ClientConServerThread getClientServerThread(String uid) {
		return (ClientConServerThread) hm.get(uid);
	}
}
