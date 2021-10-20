package AccountBook;

import static AccountBook.login.id;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import InFo.InfoDAO;
import InFo.InfoDTO;

import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JProgressBar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.FlowLayout;

public class Report extends JFrame {
	private List<InfoDTO> list;
	private InfoDAO dao = new InfoDAO();
	private JPanel contentPane;
	private String month[] = { "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" };
	private JComboBox<String> comboBox;
	JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report frame = new Report();
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
	public Report() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));


		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main main = new Main();
				main.setVisible(true);
				dispose();

			}
		});

		panel.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(month));
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (comboBox.getSelectedItem().toString().equals("1월")) {
					list = dao.chartselect(id, "1");
					if (!list.isEmpty()) {
						DrawPanel drawPanel = new DrawPanel();
						contentPane.add(drawPanel);
						setContentPane(contentPane);
					}
				}else if(comboBox.getSelectedItem().toString().equals("2월")) {
					list = dao.chartselect(id, "2");
					if (!list.isEmpty()) {
						DrawPanel drawPanel = new DrawPanel();
						contentPane.add(drawPanel);
						setContentPane(contentPane);
					}
				}else if(comboBox.getSelectedItem().toString().equals("3월")) {
					list = dao.chartselect(id, "3");
					if (!list.isEmpty()) {
						DrawPanel drawPanel = new DrawPanel();
						contentPane.add(drawPanel);
						setContentPane(contentPane);
					}
				}else if(comboBox.getSelectedItem().toString().equals("4월")) {
					list = dao.chartselect(id, "4");
					if (!list.isEmpty()) {
						DrawPanel drawPanel = new DrawPanel();
						contentPane.add(drawPanel);
						setContentPane(contentPane);
					}
				}else if(comboBox.getSelectedItem().toString().equals("5월")) {
					list = dao.chartselect(id, "5");
					if (!list.isEmpty()) {
						DrawPanel drawPanel = new DrawPanel();
						contentPane.add(drawPanel);
						setContentPane(contentPane);
					}
				}else if(comboBox.getSelectedItem().toString().equals("6월")) {
					list = dao.chartselect(id, "6");
					if (!list.isEmpty()) {
						DrawPanel drawPanel = new DrawPanel();
						contentPane.add(drawPanel);
						setContentPane(contentPane);
					}
				}else if(comboBox.getSelectedItem().toString().equals("7월")) {
					list = dao.chartselect(id, "7");
					if (!list.isEmpty()) {
						DrawPanel drawPanel = new DrawPanel();
						contentPane.add(drawPanel);
						setContentPane(contentPane);
					}
				}else if(comboBox.getSelectedItem().toString().equals("8월")) {
					list = dao.chartselect(id, "8");
					if (!list.isEmpty()) {
						DrawPanel drawPanel = new DrawPanel();
						contentPane.add(drawPanel);
						setContentPane(contentPane);
					}
				}else if(comboBox.getSelectedItem().toString().equals("9월")) {
					list = dao.chartselect(id, "9");
					if (!list.isEmpty()) {
						DrawPanel drawPanel = new DrawPanel();
						contentPane.add(drawPanel);
						setContentPane(contentPane);
					}
				}else if(comboBox.getSelectedItem().toString().equals("10월")) {
					list = dao.chartselect(id, "10");
					if (!list.isEmpty()) {
						DrawPanel drawPanel = new DrawPanel();
						contentPane.add(drawPanel);
						setContentPane(contentPane);
					}
				}else if(comboBox.getSelectedItem().toString().equals("11월")) {
					list = dao.chartselect(id, "11");
					if (!list.isEmpty()) {
						DrawPanel drawPanel = new DrawPanel();
						contentPane.add(drawPanel);
						setContentPane(contentPane);
					}
				}else if(comboBox.getSelectedItem().toString().equals("12월")) {
					list = dao.chartselect(id, "12");
					if (!list.isEmpty()) {
						DrawPanel drawPanel = new DrawPanel();
						contentPane.add(drawPanel);
						setContentPane(contentPane);
					}
				}
			}
		});
		panel_1.add(comboBox);
	}

	class DrawPanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			long sum = 0;
			long expence = 0;
			long income = 0;
			for (InfoDTO a : list) {
				if (a.getSort().equals("지출")) {
					expence += a.getMoney();
				} else {
					income += a.getMoney();
				}
			}
			sum = expence + income; // sum 값
			long expence1 = (int) 360.0 * expence / sum;
			long income1 = (int) 360.0 * income / sum;

			g.setColor(Color.blue);
			g.fillArc(50, 20, 200, 200, 0, (int) income1);
			g.setColor(Color.red);
			g.fillArc(50, 20, 200, 200, (int) income1, (int) expence1);
			g.setFont(new Font("굴림체", Font.PLAIN, 12));
			g.setColor(Color.blue);
			g.drawString("수입: " + Math.round((double) (100 * (double) income / sum * 100)) / 100.0 + "%", 300, 150);
			g.setColor(Color.red);
			g.drawString("지출: " + Math.round((double) (100 * (double) expence / sum * 100)) / 100.0 + "%", 300, 180);
		}
	}
}
