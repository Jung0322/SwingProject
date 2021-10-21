package AccountBook;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import InFo.InfoDAO;
import InFo.InfoDTO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Toolkit;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;


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
		setTitle("수정");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Update.class.getResource("/AccountBook/pigbank.png")));
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
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.SOUTH);
		JButton confirm = new JButton("\uD655\uC778");
		confirm.setForeground(Color.WHITE);
		confirm.setBackground(Color.BLACK);
		confirm.setFont(new Font("IM혜민 Bold", Font.PLAIN, 15));
		confirm.setHorizontalAlignment(SwingConstants.RIGHT);
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("확인")) {
					boolean flag = dao.update(dayTxt.getText(), combdBox.getSelectedItem().toString(), kind,
							moneyTxt.getText(), val);
					if (flag) {
						JOptionPane.showMessageDialog(null, "수정 완료 되었습니다.");
					} else {
						JOptionPane.showMessageDialog(null, "수정 실패.");
					}
					Main main = new Main();
					main.setVisible(true);
					dispose();
				}

			}
		});
		panel.add(confirm);
		JButton delete = new JButton("\uC0AD\uC81C");
		delete.setForeground(new Color(255, 255, 255));
		delete.setBackground(Color.BLACK);
		delete.setFont(new Font("IM혜민 Bold", Font.PLAIN, 15));
		delete.setHorizontalAlignment(SwingConstants.RIGHT);
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String cmd = e.getActionCommand();
				if (cmd.equals("삭제")) {
					boolean deleteFlag = dao.deleteRow(val);
					if (deleteFlag) {
						JOptionPane.showMessageDialog(null, "삭제 되었습니다.");
					} else {
						JOptionPane.showMessageDialog(null, "삭제 실패.");
					}
					Main main = new Main();
					main.setVisible(true);
					dispose();
				}
			}
		});
		panel.add(delete);
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(3, 2, 0, 0));
		JLabel lblNewLabel_1 = new JLabel("\uB0A0\uC9DC");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("IM혜민 Bold", Font.PLAIN, 20));
		panel_1.add(lblNewLabel_1);
		
		DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdFormat.format(dto.getDay());
		
		dayTxt = new JTextField();
		dayTxt.setFont(new Font("IM혜민 Bold", Font.PLAIN, 15));
		panel_1.add(dayTxt);
		dayTxt.setColumns(10);
		dayTxt.setText(today);

		JLabel lblNewLabel_2 = new JLabel("내역");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("IM혜민 Bold", Font.PLAIN, 20));


		panel_1.add(lblNewLabel_2);
		combdBox = new JComboBox();
		combdBox.setFont(new Font("IM혜민 Bold", Font.PLAIN, 15));
		combdBox.setModel(new DefaultComboBoxModel(PayKind));
		panel_1.add(combdBox);
		JLabel lblNewLabel_3 = new JLabel("\uAE08\uC561");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("IM혜민 Bold", Font.PLAIN, 20));

		panel_1.add(lblNewLabel_3);
		
		String strMoney = String.valueOf(dto.getMoney());
		
		moneyTxt = new JTextField();
		moneyTxt.setFont(new Font("IM혜민 Bold", Font.PLAIN, 15));
		panel_1.add(moneyTxt);
		moneyTxt.setColumns(10);
		moneyTxt.setText(strMoney);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		contentPane.add(panel_2, BorderLayout.NORTH);


		JLabel lblNewLabel = new JLabel(" 수정");
		lblNewLabel.setIcon(new ImageIcon(Update.class.getResource("/AccountBook/edit (2).png")));
		lblNewLabel.setFont(new Font("IM혜민 Bold", Font.PLAIN, 25));

		panel_2.add(lblNewLabel);
		rdbtnIncom = new JRadioButton("\uC218\uC785");

		rdbtnIncom.setFont(new Font("IM혜민 Bold", Font.PLAIN, 15));
		rdbtnIncom.setBackground(Color.WHITE);


		rdbtnIncom.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				combdBox.setModel(new DefaultComboBoxModel(IncomeKind));
				kind = "수입";

			}
		});

		JLabel lblNewLabel_4 = new JLabel("  ");
		panel_2.add(lblNewLabel_4);
		panel_2.add(rdbtnIncom);
		rdbtnExpense = new JRadioButton("\uC9C0\uCD9C");
		rdbtnExpense.setFont(new Font("IM혜민 Bold", Font.PLAIN, 15));
		rdbtnExpense.setBackground(Color.WHITE);
		rdbtnExpense.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				combdBox.setModel(new DefaultComboBoxModel(PayKind));
				kind = "지출";
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

