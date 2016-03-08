/**
 * 功能：与好友聊天的界面
 * 客户端要处于读取的状态，所以做成线程
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
	// 定义组件
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

	// 显示消息
	public void showMessage(Message m) {
		String info = m.getSender() + "对"
				+ m.getGetter() + "说：" + m.getContent() + "\r\n";
		System.out.println("QQChat:" + info);
		jta.append(info);
	}

	// 构造方法
	public QQChat(String friend, String ownerId) {
		this.ownerId = ownerId;
		this.friendId = friend;
		// 创建组件
		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		jtf = new JTextField(20);
		jb = new JButton("发送");
		jb.addActionListener(this);
		jp = new JPanel();

		// 添加入jp
		jp.add(jtf);
		jp.add(jb);

		// 添加到JFrame
		add(jsp, "Center");
		add(jp, "South");

		// 设置窗体
		setTitle(ownerId + "正在和" + friend + "聊天");
		setIconImage(new ImageIcon("images/qq.gif").getImage());
		setSize(400, 300);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb) {
			// 点击了发送按钮
			// 填入信息
			Message m = new Message();
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setContent(jtf.getText());
			m.setMesType(MessageType.MESSAGE_COMM);
			m.setSendTime(new Date().toString());
			// 更新自己的jtas
			String info =  m.getSender() + "对"
					+ m.getGetter() + "说：" + m.getContent() + "\r\n";
			jta.append(info);

			// 清空jtf
			jtf.setText("");

			// 客户端A发送给服务器
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

	// 测试
	// public static void main(String[] args) {
	// QQChat qqct = new QQChat("1");
	// }

}
