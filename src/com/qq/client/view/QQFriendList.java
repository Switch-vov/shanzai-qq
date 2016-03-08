/**
 * ���ܣ����ѽ���(�����ҵĺ��ѣ���������İ����)
 */
package com.qq.client.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.qq.client.tools.ManageQQChat;
import com.qq.common.Message;

public class QQFriendList extends JFrame implements ActionListener,
		MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7933056442552732399L;

	// ��Ƭ����
	CardLayout c1;

	// ��һ�ſ�Ƭ
	JPanel jphy1, jphy2, jphy3;
	JButton jphy_jb1, jphy_jb2, jphy_jb3;
	JScrollPane jphy_jsp;

	// �ڶ��ſ�Ƭ
	JPanel jpmsr1, jpmsr2, jpmsr3;
	JButton jpmsr_jb1, jpmsr_jb2, jpmsr_jb3;
	JScrollPane jpmsr_jsp;

	// �����ſ�Ƭ
	JPanel jphmd1, jphmd2, jphmd3;
	JButton jphmd_jb1, jphmd_jb2, jphmd_jb3;
	JScrollPane jphmd_jsp;

	String ownerId;

	JLabel[] jbls1, jbls2, jbls3;
	// ��Ǻ����Ƿ�����
	Boolean[] jbls1Flag;

	// �������ߺ���
	public void updateFriend(Message m) {
		String onLineFriend[] = m.getContent().split(" ");

		for (int i = 0; i < onLineFriend.length; i++) {

			jbls1[Integer.parseInt(onLineFriend[i]) - 1].setEnabled(true);
			jbls1Flag[Integer.parseInt(onLineFriend[i]) - 1] = true;
		}
	}

	// ���췽��
	public QQFriendList(String ownerId) {
		this.ownerId = ownerId;
		// �����һ�ſ�Ƭ(�����б�)
		jphy_jb1 = new JButton("�ҵĺ���");
		jphy_jb2 = new JButton("İ����");
		jphy_jb2.addActionListener(this);
		jphy_jb3 = new JButton("������");
		jphy_jb3.addActionListener(this);
		jphy1 = new JPanel(new BorderLayout());
		// �ٶ���50������
		jphy2 = new JPanel(new GridLayout(50, 1, 4, 4));

		// ��ʼ������
		jbls1 = new JLabel[50];
		jbls1Flag = new Boolean[50];
		for (int i = 0; i < jbls1.length; i++) {
			jbls1[i] = new JLabel(i + 1 + "", new ImageIcon("images/mm.jpg"),
					JLabel.LEFT);
			jbls1[i].setEnabled(false);
			jbls1Flag[i] = false;
			if (jbls1[i].getText().equals(ownerId)) {
				jbls1[i].setEnabled(true);
				jbls1Flag[i] = true;
			}
			jbls1[i].addMouseListener(this);
			// jphy2��ʼ��50������
			jphy2.add(jbls1[i]);
		}
		jphy_jsp = new JScrollPane(jphy2);
		jphy3 = new JPanel(new GridLayout(2, 1));

		// ��ť����jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);

		// ����jphy1,��jphy1��ʼ��
		jphy1.add(jphy_jb1, "North");
		jphy1.add(jphy_jsp, "Center");
		jphy1.add(jphy3, "South");

		// ����ڶ��ſ�Ƭ
		jpmsr_jb1 = new JButton("�ҵĺ���");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2 = new JButton("İ����");
		jpmsr_jb3 = new JButton("������");
		jpmsr_jb3.addActionListener(this);
		jpmsr1 = new JPanel(new BorderLayout());
		// �ٶ���20��İ����
		jpmsr2 = new JPanel(new GridLayout(20, 1, 4, 4));

		// ��ʼ��İ����
		jbls2 = new JLabel[20];
		for (int i = 0; i < jbls2.length; i++) {
			jbls2[i] = new JLabel("İ����" + (i + 1), new ImageIcon(
					"images/mm.jpg"), JLabel.LEFT);
			jbls2[i].setEnabled(false);
			jbls2[i].addMouseListener(this);
			jpmsr2.add(jbls2[i]);
		}
		jpmsr_jsp = new JScrollPane(jpmsr2);
		// jpmsr2��ʼ��20��İ����
		jpmsr3 = new JPanel(new GridLayout(2, 1));

		// ��ť����jpmsr3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);

		// ����jpmsr1,��jpmsr1��ʼ��
		jpmsr1.add(jpmsr3, "North");
		jpmsr1.add(jpmsr_jsp, "Center");
		jpmsr1.add(jpmsr_jb3, "South");

		// ��������ſ�Ƭ
		jphmd_jb1 = new JButton("�ҵĺ���");
		jphmd_jb1.addActionListener(this);
		jphmd_jb2 = new JButton("İ����");
		jphmd_jb2.addActionListener(this);
		jphmd_jb3 = new JButton("������");
		jphmd1 = new JPanel(new BorderLayout());
		// �ٶ���������20����
		jphmd2 = new JPanel(new GridLayout(20, 1, 4, 4));

		// ��ʼ���������е���
		jbls3 = new JLabel[20];
		for (int i = 0; i < jbls3.length; i++) {
			jbls3[i] = new JLabel("������" + (i + 1), new ImageIcon(
					"images/mm.jpg"), JLabel.LEFT);
			jbls3[i].setEnabled(false);
			jbls3[i].addMouseListener(this);
			jphmd2.add(jbls3[i]);
		}
		jphmd_jsp = new JScrollPane(jphmd2);
		// ��jphmd2��ʼ��20��������
		jphmd3 = new JPanel(new GridLayout(3, 1));

		// ��ť����jphmd3
		jphmd3.add(jphmd_jb1);
		jphmd3.add(jphmd_jb2);
		jphmd3.add(jphmd_jb3);

		// ����jphmd1,��jphmd1��ʼ��
		jphmd1.add(jphmd3, "North");
		jphmd1.add(jphmd_jsp, "Center");

		// ��JFrame����ΪCardLayout����
		c1 = new CardLayout();
		setLayout(c1);
		// ����JFrame
		add(jphy1, "1");
		add(jpmsr1, "2");
		add(jphmd1, "3");

		// ���ô���
		setTitle("ɽկQQ--" + ownerId);
		setIconImage(new ImageIcon("images/qq.gif").getImage());
		setSize(250, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	// ����
	// public static void main(String[] args) {
	// QQFriendList qqfl = new QQFriendList();
	//
	// }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jphy_jb2 || e.getSource() == jphmd_jb2) {
			// �����İ���˰�ť����ʾ�ڶ��ſ�Ƭ
			c1.show(this.getContentPane(), "2");
		} else if (e.getSource() == jpmsr_jb1 || e.getSource() == jphmd_jb1) {
			// ����˺��Ѱ�ť����ʾ��һ�ſ�Ƭ
			c1.show(this.getContentPane(), "1");
		} else if (e.getSource() == jphy_jb3 || e.getSource() == jpmsr_jb3) {
			// ����˺�������ť����ʾ�����ſ�Ƭ
			c1.show(this.getContentPane(), "3");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// ��Ӧ�û�˫�����¼������õ����ѵı��
		if (e.getClickCount() == 2) {
			// �õ��ú��ѵı��
			String friendNo = ((JLabel) e.getSource()).getText();
			// ���������˲�����
			if (!jbls1Flag[Integer.parseInt(friendNo) - 1]) {
				JOptionPane.showMessageDialog(this, "���ܺͲ����ߵ�������");
			} else if (!friendNo.equals(ownerId)
					&& jbls1Flag[Integer.parseInt(friendNo) - 1]) {
				// ��������Լ���������
				QQChat qqChat = new QQChat(friendNo, ownerId);
				// �����������뵽������
				ManageQQChat.addQQChat(this.ownerId + " " + friendNo, qqChat);
			} else {
				//������Լ�
				JOptionPane.showMessageDialog(this, "���ܺ��Լ�����");
			}

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.black);
	}
}
