/**
 * ���ܣ���¼��̨ʵ��
 */
package com.qq.client.model;

import com.qq.common.User;

public class QQClientUser {
	
	// �����û��Ϸ���
	public boolean checkUser(User u) {
		return new QQClientConServer().SendLoginInfoTOServer(u);
	}
}
