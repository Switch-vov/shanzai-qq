/**
 * ���ܣ��ͻ������ӷ������߳�
 */
package com.qq.client.tools;

import java.net.*;
import java.io.*;

import com.qq.client.view.QQChat;
import com.qq.client.view.QQFriendList;
import com.qq.common.Message;
import com.qq.common.MessageType;

public class ClientConServerThread extends Thread {
	private Socket s;

	// ���췽��
	public ClientConServerThread(Socket s) {
		this.s = s;
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public void run() {
		while (true) {
			// ��ͣ�Ķ�ȡ�ӷ�������������Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(
						s.getInputStream());
				Message m = (Message) ois.readObject();
				System.out.println("��ȡ�ӷ�������������Ϣ" + m.getSender() + "��"
						+ m.getGetter() + "���ݣ�" + m.getContent());

				if (m.getMesType().equals(MessageType.MESSAGE_COMM)) {
					// ��ͨ��
					// �ӷ�������õ���Ϣ��ʾ����Ӧ���������
					QQChat qqChat = ManageQQChat.getQQChat(m.getGetter() + " "
							+ m.getSender());
					// ��ʾ
					qqChat.showMessage(m);
				} else if (m.getMesType().equals(
						MessageType.MESSAGE_RET_ONLINEFRIEND)) {
					// �������ߺ��ѵİ�
					System.out.println("�ͻ��˽��յ�" + m.getContent());
					String con = m.getContent();
					// getter������ڷ������Ľ����ߣ�Ҳ�����Լ���QQ
					String getter = m.getGetter();
					System.out.println("getter=" + getter);
					
					// �޸���Ӧ�ĺ����б�
					QQFriendList qqFriendList = ManageQQFriendList
							.getQQFriendList(getter);

					// �������ߺ���
					if (qqFriendList != null) {
						qqFriendList.updateFriend(m);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
