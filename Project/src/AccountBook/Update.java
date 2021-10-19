package AccountBook;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import com.sun.corba.se.impl.transport.DefaultIORToSocketInfoImpl;

import InFo.InfoDAO;
import InFo.InfoDTO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.security.auth.callback.ConfirmationCallback;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.Font;
import static AccountBook.login.id;

public class Update extends JFrame {

	private JPanel contentPane;
	private JTextField dayTxt, moneyTxt;
	private JComboBox<String> combdBox;
	private String PayKind[] = { "식비", "교통", "주거/통신", "경조사/회비", "패션/미용", "교육", "문화생활", "기타" };
	private String IncomeKind[] = { "월급", "부수입", "상여", "금융소득", "용돈", "기타" };
	private JPanel panel_1;
	private static Update frame;
	private int val;
	public InfoDAO dao;
	public InfoDTO dto;
	private JRadioButton rdbtnExpense, rdbtnIncom;
	private String kind = "지출";

	public Update(int val) {

		dao = new InfoDAO();
		dto = new InfoDTO();
		dto = dao.noSelect(val);

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
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String cmd = e.getActionCommand();
				if (cmd.equals("확인")) {
					
					
					System.out.println(kind);
					//날짜 가져오기
					System.out.println(dayTxt.getText());
					//내역 가져오기
					System.out.println(combdBox.getSelectedItem().toString());
					//금액 가져오기
					System.out.println(moneyTxt.getText());
					System.out.println(val);

					boolean flag = dao.update(dayTxt.getText(), combdBox.getSelectedItem().toString(), kind, moneyTxt.getText(), val);

					if (flag) {
						JOptionPane.showMessageDialog(null, "수정 완료 되었습니다.");
						Main main = new Main();
						main.setVisible(true);
						dispose();
					}
				}

			}
		});
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

		DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdFormat.format(dto.getDay());

		dayTxt = new JTextField();
		panel_1.add(dayTxt);
		dayTxt.setColumns(10);
		dayTxt.setText(today);

		JLabel lblNewLabel_2 = new JLabel("\uB0B4\uC5ED");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 20));
		panel_1.add(lblNewLabel_2);

		combdBox = new JComboBox();
		combdBox.setModel(new DefaultComboBoxModel(PayKind));
		panel_1.add(combdBox);

		JLabel lblNewLabel_3 = new JLabel("\uAE08\uC561");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 20));
		panel_1.add(lblNewLabel_3);

		String strMoney = String.valueOf(dto.getMoney());

		moneyTxt = new JTextField();
		panel_1.add(moneyTxt);
		moneyTxt.setColumns(10);
		moneyTxt.setText(strMoney);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("\uC218\uC815");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		panel_2.add(lblNewLabel);

		rdbtnIncom = new JRadioButton("\uC218\uC785");

		rdbtnIncom.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				combdBox.setModel(new DefaultComboBoxModel(IncomeKind));

			}
		});
		panel_2.add(rdbtnIncom);
		rdbtnExpense = new JRadioButton("\uC9C0\uCD9C");
		rdbtnExpense.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				combdBox.setModel(new DefaultComboBoxModel(PayKind));
			}
		});
		rdbtnExpense.setSelected(true);
		panel_2.add(rdbtnExpense);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnIncom);
		group.add(rdbtnExpense);

		if (dto.getSort().equals("수입")) {
			rdbtnIncom.setSelected(true);

			switch (dto.getContent()) {
			case "월급":
				combdBox.setSelectedIndex(0);
				break;
			case "부수입":
				combdBox.setSelectedIndex(1);
				break;
			case "상여":
				combdBox.setSelectedIndex(2);
				break;
			case "금융소득":
				combdBox.setSelectedIndex(3);
				break;
			case "용돈":
				combdBox.setSelectedIndex(4);
				break;
			case "기타":
				combdBox.setSelectedIndex(5);
				break;

			}

		} else {
			rdbtnExpense.setSelected(true);

			switch (dto.getContent()) {
			case "식비":
				combdBox.setSelectedIndex(0);
				break;
			case "교통":
				combdBox.setSelectedIndex(1);
				break;
			case "주거/통신":
				combdBox.setSelectedIndex(2);
				break;
			case "경조사/회비":
				combdBox.setSelectedIndex(3);
				break;
			case "패션/미용":
				combdBox.setSelectedIndex(4);
				break;
			case "교육":
				combdBox.setSelectedIndex(5);
				break;
			case "문화생활":
				combdBox.setSelectedIndex(6);
				break;
			case "기타":
				combdBox.setSelectedIndex(7);
				break;
			}

		}

	}

}
