/**
 * ���ܣ����������Ľ���
 * �ͻ���Ҫ���ڶ�ȡ��״̬�����������߳�
 */
package com.qq.client.view;

import java.awt.event.*;
import java.io.*;
import java.util.Date;

import javax.swing.*;

import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.MessageType;

public class QQChat extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 582207281703443438L;
	// �������
	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	JScrollPane jsp;

	private String ownerId;
	private String friendId;

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	// ��ʾ��Ϣ
	public void showMessage(Message m) {
		String info = m.getSender() + "��"
				+ m.getGetter() + "˵��" + m.getContent() + "\r\n";
		System.out.println("QQChat:" + info);
		jta.append(info);
	}

	// ���췽��
	public QQChat(String friend, String ownerId) {
		this.ownerId = ownerId;
		this.friendId = friend;
		// �������
		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		jtf = new JTextField(20);
		jb = new JButton("����");
		jb.addActionListener(this);
		jp = new JPanel();

		// �����jp
		jp.add(jtf);
		jp.add(jb);

		// ��ӵ�JFrame
		add(jsp, "Center");
		add(jp, "South");

		// ���ô���
		setTitle(ownerId + "���ں�" + friend + "����");
		setIconImage(new ImageIcon("images/qq.gif").getImage());
		setSize(400, 300);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb) {
			// ����˷��Ͱ�ť
			// ������Ϣ
			Message m = new Message();
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setContent(jtf.getText());
			m.setMesType(MessageType.MESSAGE_COMM);
			m.setSendTime(new Date().toString());
			// �����Լ���jtas
			String info =  m.getSender() + "��"
					+ m.getGetter() + "˵��" + m.getContent() + "\r\n";
			jta.append(info);

			// ���jtf
			jtf.setText("");

			// �ͻ���A���͸�������
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						ManageClientConServerThread
								.getClientServerThread(this.ownerId).getS()
								.getOutputStream());
				oos.writeObject(m);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	// ����
	// public static void main(String[] args) {
	// QQChat qqct = new QQChat("1");
	// }

}
