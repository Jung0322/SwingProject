package AccountBook;

import java.awt.BorderLayout;
import static AccountBook.login.id;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class MainInput extends JFrame {
	
	private static MainInput frame;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainInput();
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
	public MainInput() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton income = new JButton("\uC218\uC785");
		income.setBounds(72, 99, 125, 31);
		income.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				income in = new income();
				in.setVisible(true);
				frame.setVisible(false);
				
			}
		});
		contentPane.add(income);
		
		JButton expense = new JButton("\uC9C0\uCD9C");
		expense.setBounds(227, 99, 125, 31);
		expense.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Expense out = new Expense();
				out.setVisible(true);
				frame.setVisible(false);
				
			}
		});
		contentPane.add(expense);
	}
}
