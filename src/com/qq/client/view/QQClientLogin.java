/**
 * ����:qq�ͻ��˵�½����
 */
package com.qq.client.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.qq.client.model.QQClientUser;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;
import com.qq.client.tools.*;

public class QQClientLogin extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8764348608194857251L;

	// �����ϲ����
	JLabel jbl1;

	// �����в����
	JTabbedPane jtp;
	JPanel jp2, jp3, jp4;
	JLabel jp2_jbl1, jp2_jbl2, jp2_jbl3, jp2_jbl4;
	JTextField jp2_jtf1;
	JPasswordField jp2_jpf1;
	JButton jp2_jb1;
	JCheckBox jp2_jcb1, jp2_jcb2;

	// �����²����
	JPanel jp1;
	JButton jp1_jb1, jp1_jb2, jp1_jb3;

	// ���췽��
	public QQClientLogin() {
		// �����ϲ�
		jbl1 = new JLabel(new ImageIcon("images/tou.gif"));

		// �����в�
		jtp = new JTabbedPane();
		jp3 = new JPanel();
		jp4 = new JPanel();
		// jp2����Ϊ���񲼾�
		jp2 = new JPanel(new GridLayout(3, 3));
		jp2_jbl1 = new JLabel("QQ����", JLabel.CENTER);
		jp2_jbl2 = new JLabel("QQ����", JLabel.CENTER);
		jp2_jbl3 = new JLabel("��������", JLabel.CENTER);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jbl4 = new JLabel("�������뱣��", JLabel.CENTER);

		jp2_jb1 = new JButton(new ImageIcon("images/clear.gif"));
		jp2_jcb1 = new JCheckBox("�����½");
		jp2_jcb2 = new JCheckBox("��ס����");
		jp2_jtf1 = new JTextField(20);
		jp2_jpf1 = new JPasswordField(20);

		// ��ӵ�jp2
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf1);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf1);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jbl4);

		// ��ӵ�jtp
		jtp.add(jp2, "QQ����");
		jtp.add(jp3, "�ֻ�����");
		jtp.add(jp4, "�����ʼ�");

		// �����²�
		jp1 = new JPanel();
		jp1_jb1 = new JButton(new ImageIcon("images/denglu.gif"));
		// ��Ӧ�û������¼
		jp1_jb1.addActionListener(this);
		jp1_jb2 = new JButton(new ImageIcon("images/quxiao.gif"));
		jp1_jb3 = new JButton(new ImageIcon("images/xiangdao.gif"));

		// ��ӵ�jp1
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);

		// ��ӵ�JFrame
		add(jbl1, "North");
		add(jp1, "South");
		add(jtp, "Center");

		// ���ô���
		setTitle("ɽկQQ--��¼ҳ");
		setIconImage(new ImageIcon("images/qq.gif").getImage());
		setSize(350, 240);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		QQClientLogin qqcl = new QQClientLogin();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jp1_jb1) {
			// �����¼��ť
			// ��֤
			// �����ͻ����û������
			QQClientUser qqClientUser = new QQClientUser();

			// �����û���Ϣ����
			User u = new User();
			u.setUserId(jp2_jtf1.getText().trim());
			u.setPasswd(new String(jp2_jpf1.getPassword()));

			// ��֤�û��Ƿ�Ϸ�
			if (qqClientUser.checkUser(u)) {
				try {
					// �Ѵ��������б���ǰ�ڻ�ȡ��Ϣ֮ǰ
					QQFriendList qqList = new QQFriendList(u.getUserId());
					ManageQQFriendList.addQQFriendList(u.getUserId(), qqList);

					// ����һ��Ҫ�󷵻����ߺ��ѵİ�
					@SuppressWarnings("unused")
					ObjectOutputStream oos = new ObjectOutputStream(
							ManageClientConServerThread
									.getClientServerThread(u.getUserId())
									.getS().getOutputStream());
					// Message����
					Message m = new Message();
					m.setMesType(MessageType.MESSAGE_GET_ONLINEFRIEND);
					// ָ����ȡ�����ĸ�qq�ŵĺ�����Ϣ
					m.setSender(u.getUserId());
					oos.writeObject(m);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				// �رյ�¼����
				dispose();
			} else {
				// ������
				JOptionPane.showMessageDialog(this, "�û��������������");
			}
		}
	}
}
