package AccountBook;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import User.UserDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField textPw;
	private JTextField textId;
	public static String id;
	public UserDAO dao = new UserDAO();
	private static login frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new login();
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
	public login() {
		setTitle("알뜰살뜰 가계부_티끌모아 건물주되자!!");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\SwingProject\\SwingProject\\Project\\src\\AccountBook\\pigbank.png"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel lbl1 = new JLabel("");
		panel_1.add(lbl1);
		
		JLabel lbl2 = new JLabel("");
		panel_1.add(lbl2);
		
		JLabel lbl3 = new JLabel("");
		panel_1.add(lbl3);
		
		JLabel lbl4 = new JLabel("");
		panel_1.add(lbl4);
		
		JLabel lbl5 = new JLabel("");
		panel_1.add(lbl5);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("굴림", Font.BOLD, 14));
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblId);
		
		textId = new JTextField();
		panel_1.add(textId);
		textId.setColumns(10);
		
		JLabel lbl6 = 	new JLabel("");
		lbl6.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl6.setIcon(null);

		panel_1.add(lbl6);
		
		JLabel lbl7 = new JLabel("");
		panel_1.add(lbl7);
		
		JLabel lblPw = new JLabel("PW");
		lblPw.setFont(new Font("굴림", Font.BOLD, 14));
		lblPw.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblPw);
		
		textPw = new JTextField();
		panel_1.add(textPw);
		textPw.setColumns(10);
		
		JLabel lbl8 = new JLabel("");
		panel_1.add(lbl8);
		
		JLabel lbl9 = new JLabel("");
		panel_1.add(lbl9);
		
		JLabel lbl10 = new JLabel("");
		panel_1.add(lbl10);
		
		JLabel lbl11 = new JLabel("");
		panel_1.add(lbl11);
		
		JLabel lbl12 = new JLabel("");
		panel_1.add(lbl12);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setVgap(20);
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnLogin = new JButton("로그인");
		btnLogin.setIcon(new ImageIcon("C:\\SwingProject\\SwingProject\\Project\\src\\AccountBook\\check-mark-3-16.png"));
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean flag =  dao.login(textId.getText(), textPw.getText());
				if(flag) {
					JOptionPane.showMessageDialog(null, "로그인이 완료되었습니다.");
					id = textId.getText();
					Main main = new Main();
					main.setVisible(true);
//					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "로그인이 실패히었습니다.");
				}
				

}
		});
		panel_2.add(btnLogin);
		
		JButton btnJoin = new JButton("회원가입");
		btnJoin.setIcon(new ImageIcon("C:\\SwingProject\\SwingProject\\Project\\src\\AccountBook\\arrow-34-16.png"));
		btnJoin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Join join = new Join();
				join.setVisible(true);
//				frame.setVisible(false);
				dispose();
			}
		});
		panel_2.add(btnJoin);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(20);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("알뜰살뜰 가계부");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		panel.add(lblNewLabel);
	}

}