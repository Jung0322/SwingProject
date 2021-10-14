package AccountBook;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import InFo.InfoDAO;
import User.UserDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.Font;
import static AccountBook.login.id;

public class Input extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField tfDate;
	private JTextField tfMoney;
	private JComboBox<String> combdBox;
	private String PayKind[] = { "식비", "교통", "주거/통신", "경조사/회비", "패션/미용", "교육", "문화생활", "기타" };
	private String IncomeKind[] = { "월급", "부수입", "상여", "금융소득", "용돈", "기타" };
	private JPanel panel_1;
	private static Input frame;
	public InfoDAO dao = new InfoDAO();
	
	//sort를 가져오기 위하여 kind 선언
	private String kind = "지출";
	//userid는 항상 불러와야 함
	private String userid;
	
//더이상 독립적인 페이지가 아니므로 생략
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					frame = new Input();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public Input(String userid) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton confirm = new JButton("\uD655\uC778");
		confirm.setHorizontalAlignment(SwingConstants.RIGHT);
		confirm.addActionListener(this);
		panel.add(confirm);

		JButton delete = new JButton("\uC0AD\uC81C");
		delete.setHorizontalAlignment(SwingConstants.RIGHT);
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		panel.add(delete);

		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(3, 2, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("\uB0A0\uC9DC");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 20));
		panel_1.add(lblNewLabel_1);

		tfDate = new JTextField();
		panel_1.add(tfDate);
		tfDate.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\uB0B4\uC5ED");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 20));
		panel_1.add(lblNewLabel_2);

		combdBox = new JComboBox();
		combdBox.setModel(new DefaultComboBoxModel(PayKind));
		panel_1.add(combdBox);

		JLabel lblNewLabel_3 = new JLabel("\uAE08\uC561");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 20));
		panel_1.add(lblNewLabel_3);

		tfMoney = new JTextField();
		panel_1.add(tfMoney);
		tfMoney.setColumns(10);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("\uC785\uB825");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		panel_2.add(lblNewLabel);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("\uC218\uC785");
		rdbtnNewRadioButton.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				combdBox.setModel(new DefaultComboBoxModel(IncomeKind));
				kind = "수입";
				

			}
		});
		panel_2.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("\uC9C0\uCD9C");
		rdbtnNewRadioButton_1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				combdBox.setModel(new DefaultComboBoxModel(PayKind));
				kind="지출";
			}
		});
		rdbtnNewRadioButton_1.setSelected(true);
		panel_2.add(rdbtnNewRadioButton_1);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		
		
		this.userid = userid;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("확인")) {
			//사용자 입력값 가져오기
			//라디오 버튼 선택된 값 가져오기
			System.out.println(kind);
			//날짜 가져오기
			System.out.println(tfDate.getText());
			//내역 가져오기
			System.out.println(combdBox.getSelectedItem().toString());
			//금액 가져오기
			System.out.println(tfMoney.getText());
			
			
			boolean flag =  dao.Input(tfDate.getText(), combdBox.getSelectedItem().toString(),tfMoney.getText(), kind,userid);
			if(flag) {
				Main main = new Main(userid);
				main.setVisible(true);
				frame.setVisible(false);
				
			}
						
		}
		
	}

}