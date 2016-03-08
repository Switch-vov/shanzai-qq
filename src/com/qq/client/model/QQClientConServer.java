/**
 * ���ܣ��ͻ������ӷ������ĺ�̨
 */
package com.qq.client.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.qq.client.tools.ClientConServerThread;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

public class QQClientConServer {
	public Socket s;

	// ���͵�һ������
	public boolean SendLoginInfoTOServer(Object o) {
		boolean b = false;
		try {
			// ����127.0.0.1��9999�˿�
			s = new Socket("127.0.0.1", 9999);
			// ������������˺���Ϣ
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);

			// �ӷ������յ���֤�Ƿ�ͨ����Message����
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms = (Message) ois.readObject();
			// ��֤��¼�Ƿ�ɹ�
			if (ms.getMesType().equals(MessageType.MESSAGE_SUCCEED)) {
				// ����һ����qq�ͷ������˱���ͨѶ���ӵ��߳�
				ClientConServerThread ccst = new ClientConServerThread(s);
				// �������߳�
				ccst.start();
				ManageClientConServerThread.addClientConServerThread(
						((User) o).getUserId(), ccst);

				b = true;
			} else {
				// �ر�Scoket
				s.close();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

		}
		return b;
	}
}
