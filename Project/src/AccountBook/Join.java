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
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.lang.model.element.Element;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

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
<<<<<<< HEAD
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\SwingProject\\SwingProject\\Project\\src\\AccountBook\\pigbank.png"));
=======
		setIconImage(Toolkit.getDefaultToolkit().getImage(Join.class.getResource("/AccountBook/pigbank.png")));
>>>>>>> branch 'master' of https://github.com/Jung0322/SwingProject.git
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 191);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("회원가입을 환영합니다.");
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("ID 입력 : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblNewLabel_1);

		id = new JTextField();
		id.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(id);
		id.setColumns(10);

		JButton overlop = new JButton("중복 확인");
		overlop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean flag = dao.overlop(id.getText());
				if(flag) {
					System.out.println("aa");
					JOptionPane.showMessageDialog(null, "존재하는 id가 있습니다.");
				}else {
					JOptionPane.showMessageDialog(null, "사용가능한 id 입니다.");
				}
			}
		});
		panel_1.add(overlop);

		JLabel Label = new JLabel("\uC774\uB984 \uC785\uB825 : ");
		Label.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(Label);

		name = new JTextField();
		panel_1.add(name);
		name.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("");
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uC785\uB825 : ");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblNewLabel_4);

		password = new JTextField();
		panel_1.add(password);
		password.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("");
		panel_1.add(lblNewLabel_5);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);

		JButton confirm = new JButton("확인");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean flag = dao.join(id.getText(), password.getText(), name.getText());
				if(flag) {
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
					login login = new login();
					login.setVisible(true);
//					frame.setVisible(false);
//					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "회원가입이 실패하였습니다.");
				}
			}
		});
		panel_2.add(confirm);

		JButton cancel = new JButton("취소");
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