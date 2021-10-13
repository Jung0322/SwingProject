package AccountBook;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class Expense extends JFrame implements ItemListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Expense frame = new Expense();
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
	public Expense() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("\uC9C0\uCD9C");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("\uD655\uC778");
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.WEST);
		
		JLabel lblNewLabel_1 = new JLabel("\uB0A0\uC9DC");
		panel_2.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(8);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(9, 1, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("\uB0B4\uC5ED");
		panel_3.add(lblNewLabel_2);
		
		JRadioButton rdo1 = new JRadioButton("\uC2DD\uBE44");
		panel_3.add(rdo1);
		
		JRadioButton rdo2 = new JRadioButton("\uAD50\uD1B5");
		panel_3.add(rdo2);
		
		JRadioButton rdo3 = new JRadioButton("\uC8FC\uAC70/\uD1B5\uC2E0");
		panel_3.add(rdo3);
		
		JRadioButton rdo4 = new JRadioButton("\uACBD\uC870\uC0AC/\uD68C\uBE44");
		panel_3.add(rdo4);
		
		JRadioButton rdo5 = new JRadioButton("\uD328\uC158/\uBBF8\uC6A9");
		panel_3.add(rdo5);
		
		JRadioButton rdo6 = new JRadioButton("\uAD50\uC721");
		panel_3.add(rdo6);
		
		JRadioButton rdo7 = new JRadioButton("\uBB38\uD654\uC0DD\uD65C");
		panel_3.add(rdo7);
		
		JRadioButton rdo8 = new JRadioButton("\uAE30\uD0C0");
		panel_3.add(rdo8);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdo1);
		group.add(rdo2);
		group.add(rdo3);
		group.add(rdo4);
		group.add(rdo5);
		group.add(rdo6);
		group.add(rdo7);
		group.add(rdo8);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.EAST);
		
		JLabel lblNewLabel_3 = new JLabel("\uAE08\uC561");
		panel_4.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		panel_4.add(textField_1);
		textField_1.setColumns(8);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

}
