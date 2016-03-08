/**
 * 功能:qq客户端登陆界面
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

	// 定义上部组件
	JLabel jbl1;

	// 定义中部组件
	JTabbedPane jtp;
	JPanel jp2, jp3, jp4;
	JLabel jp2_jbl1, jp2_jbl2, jp2_jbl3, jp2_jbl4;
	JTextField jp2_jtf1;
	JPasswordField jp2_jpf1;
	JButton jp2_jb1;
	JCheckBox jp2_jcb1, jp2_jcb2;

	// 定义下部组件
	JPanel jp1;
	JButton jp1_jb1, jp1_jb2, jp1_jb3;

	// 构造方法
	public QQClientLogin() {
		// 处理上部
		jbl1 = new JLabel(new ImageIcon("images/tou.gif"));

		// 处理中部
		jtp = new JTabbedPane();
		jp3 = new JPanel();
		jp4 = new JPanel();
		// jp2设置为网格布局
		jp2 = new JPanel(new GridLayout(3, 3));
		jp2_jbl1 = new JLabel("QQ号码", JLabel.CENTER);
		jp2_jbl2 = new JLabel("QQ密码", JLabel.CENTER);
		jp2_jbl3 = new JLabel("忘记密码", JLabel.CENTER);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jbl4 = new JLabel("申请密码保护", JLabel.CENTER);

		jp2_jb1 = new JButton(new ImageIcon("images/clear.gif"));
		jp2_jcb1 = new JCheckBox("隐身登陆");
		jp2_jcb2 = new JCheckBox("记住密码");
		jp2_jtf1 = new JTextField(20);
		jp2_jpf1 = new JPasswordField(20);

		// 添加到jp2
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf1);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf1);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jbl4);

		// 添加到jtp
		jtp.add(jp2, "QQ号码");
		jtp.add(jp3, "手机号码");
		jtp.add(jp4, "电子邮件");

		// 处理下部
		jp1 = new JPanel();
		jp1_jb1 = new JButton(new ImageIcon("images/denglu.gif"));
		// 响应用户点击登录
		jp1_jb1.addActionListener(this);
		jp1_jb2 = new JButton(new ImageIcon("images/quxiao.gif"));
		jp1_jb3 = new JButton(new ImageIcon("images/xiangdao.gif"));

		// 添加到jp1
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);

		// 添加到JFrame
		add(jbl1, "North");
		add(jp1, "South");
		add(jtp, "Center");

		// 设置窗体
		setTitle("山寨QQ--登录页");
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
			// 点击登录按钮
			// 验证
			// 创建客户端用户类对象
			QQClientUser qqClientUser = new QQClientUser();

			// 创建用户信息对象
			User u = new User();
			u.setUserId(jp2_jtf1.getText().trim());
			u.setPasswd(new String(jp2_jpf1.getPassword()));

			// 验证用户是否合法
			if (qqClientUser.checkUser(u)) {
				try {
					// 把创建好友列表提前在获取消息之前
					QQFriendList qqList = new QQFriendList(u.getUserId());
					ManageQQFriendList.addQQFriendList(u.getUserId(), qqList);

					// 发送一个要求返回在线好友的包
					@SuppressWarnings("unused")
					ObjectOutputStream oos = new ObjectOutputStream(
							ManageClientConServerThread
									.getClientServerThread(u.getUserId())
									.getS().getOutputStream());
					// Message对象
					Message m = new Message();
					m.setMesType(MessageType.MESSAGE_GET_ONLINEFRIEND);
					// 指明获取的是哪个qq号的好友信息
					m.setSender(u.getUserId());
					oos.writeObject(m);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				// 关闭登录界面
				dispose();
			} else {
				// 错误处理
				JOptionPane.showMessageDialog(this, "用户名或者密码错误");
			}
		}
	}
}
