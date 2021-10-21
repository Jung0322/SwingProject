package AccountBook;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import User.UserDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.lang.model.element.Element;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

public class Join extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField name;
	private JTextField password;
	public UserDAO dao =  new UserDAO();
	public static Join frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Join();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Join() {
		setTitle("ȸ������");
		//Ÿ��Ʋ ������ ����
		setIconImage(Toolkit.getDefaultToolkit().getImage(Join.class.getResource("/Accountbook/JoinTitle.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 395, 370);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

//		JLabel lblNewLabel = new JLabel("ȸ�������� ȯ���մϴ�.");
//		lblNewLabel.setBackground(Color.WHITE);
//		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		panel.add(lblNewLabel);
		
		//���ο� ���� �߰� �� �󺧿� ������ �̹��� �߰�
		JLabel imgWelcome = new JLabel();
		imgWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon lbicon = new ImageIcon(Join.class.getResource("/Accountbook/JoinWelcome.jpg"));
		imgWelcome.setIcon(lbicon);
		
		panel.add(imgWelcome);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("ID �Է� : ");
		lblNewLabel_1.setFont(new Font("IM���� Bold", Font.PLAIN, 12));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblNewLabel_1);

		id = new JTextField();
		id.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(id);
		id.setColumns(10);

		JButton overlap = new JButton("�ߺ� Ȯ��");
		overlap.setFont(new Font("IM���� Bold", Font.PLAIN, 12));
		overlap.setForeground(Color.WHITE);
		overlap.setBackground(Color.BLACK);
		//�ߺ�Ȯ�� ��ư�� �̹��� ����
		overlap.setIcon(new ImageIcon(Join.class.getResource("/Accountbook/JoinDuplicationCheck.jpg")));
		
		//�̹��� ����
		overlap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean flag = dao.overlop(id.getText());
				if(flag) {
					System.out.println("aa");
					JOptionPane.showMessageDialog(null, "�����ϴ� id�� �ֽ��ϴ�.");
				}else {
					JOptionPane.showMessageDialog(null, "��밡���� id �Դϴ�.");
				}
			}
		});
		panel_1.add(overlap);

		JLabel Label = new JLabel("\uC774\uB984 \uC785\uB825 : ");
		Label.setFont(new Font("IM���� Bold", Font.PLAIN, 12));
		Label.setBackground(Color.WHITE);
		Label.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(Label);

		name = new JTextField();
		panel_1.add(name);
		name.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBackground(Color.WHITE);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uC785\uB825 : ");
		lblNewLabel_4.setFont(new Font("IM���� Bold", Font.PLAIN, 12));
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblNewLabel_4);

		password = new JTextField();
		panel_1.add(password);
		password.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBackground(Color.WHITE);
		panel_1.add(lblNewLabel_5);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		contentPane.add(panel_2, BorderLayout.SOUTH);

		JButton confirm = new JButton("Ȯ��");
		confirm.setFont(new Font("IM���� Bold", Font.PLAIN, 12));
		confirm.setForeground(Color.WHITE);
		confirm.setBackground(Color.BLACK);
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean flag = dao.join(id.getText(), password.getText(), name.getText());
				if(flag) {
					JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.");
					login login = new login();
					login.setVisible(true);
//					frame.setVisible(false);
//					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "ȸ�������� �����Ͽ����ϴ�.");
				}
			}
		});
		panel_2.add(confirm);

		JButton cancel = new JButton("���");
		cancel.setFont(new Font("IM���� Bold", Font.PLAIN, 12));
		cancel.setForeground(Color.WHITE);
		cancel.setBackground(Color.BLACK);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				password.setText("");
				name.setText("");
			}
		});
		panel_2.add(cancel);
	}
	
	

}