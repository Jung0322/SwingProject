package AccountBook;

import static AccountBook.login.id;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Formatter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	private String Month[] = { "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" };
	private String Year[] = {"2021년","2020년","2019년","2018년","2017년","2016년","2015년","2014년"};
	private JComboBox<String> comboBox;
	JPanel panel;
	private JComboBox yearBox;
	private int year=21;
	private int month=1;
	private DecimalFormat formatter = new DecimalFormat("###,###원");
	

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Report.class.getResource("/AccountBook/pigbank.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		list = dao.chartselect(id, 1,21);
		DrawPanel drawPanel = new DrawPanel();

		contentPane.add(drawPanel);
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton.setFont(new Font("IM혜민 Bold", Font.PLAIN, 23));
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
		comboBox.setFont(new Font("IM혜민 Bold", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(Month));
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				String month_selected = comboBox.getSelectedItem().toString();

				month = Integer.parseInt(month_selected.substring(0, month_selected.lastIndexOf("월")));

				try {
					list = dao.chartselect(id, month,year);
				} catch (Exception e2) {
					// TODO: handle exception
				}
				if (!list.isEmpty()) {
					drawPanel.repaint();
				} else {
					drawPanel.repaint();
				}
			}
		});
		
		yearBox = new JComboBox();
		yearBox.setFont(new Font("IM혜민 Bold", Font.PLAIN, 20));
		yearBox.setModel(new DefaultComboBoxModel(Year));
		yearBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String year_selected = yearBox.getSelectedItem().toString();

				year = Integer.parseInt(year_selected.substring(2, year_selected.lastIndexOf("년")));
				
				try {
					list = dao.chartselect(id, month,year);
				} catch (Exception e2) {
					// TODO: handle exception
				}
				if (!list.isEmpty()) {
					drawPanel.repaint();
				} else {
					drawPanel.repaint();
				}
				
			}
		});
		panel_1.add(yearBox);
		panel_1.add(comboBox);
	}

	class DrawPanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			long sum = 0;
			long expence = 0;
			long income = 0;
			long expence1 = 0;
			long income1 = 0;
			for (InfoDTO a : list) {
				if (a.getSort().equals("지출")) {
					expence += a.getMoney();
				} else {
					income += a.getMoney();
				}
			}
			sum = expence + income; // sum 값
			if (sum != 0) {
				expence1 = (int) 360.0 * expence / sum;
				income1 = (int) 360.0 * income / sum;
				g.setColor(Color.blue);
				g.fillArc(50, 20, 300, 300, 0, (int) income1);
				g.setColor(Color.red);
				g.fillArc(50, 20, 300, 300, (int) income1, (int) expence1);
				g.setFont(new Font("굴림체", Font.PLAIN, 20));
				g.setColor(Color.blue);
				g.drawString("수입: " + formatter.format(income) + "(" +  Math.round((double) (100 * (double) income / sum * 100)) / 100.0
						+ "%" + ")", 380, 270);
				g.setColor(Color.red);
				g.drawString("지출: " + formatter.format(expence) + "(" + Math.round((double) (100 * (double) expence / sum * 100)) / 100.0
						+ "%" + ")", 380, 240);
			}else {
				g.setColor(Color.gray);
				g.fillArc(50, 20, 300, 300, 0, 360);
				g.setFont(new Font("굴림체", Font.PLAIN, 20));
				g.setColor(Color.blue);
				g.drawString("수입: 0원(0%)", 380, 280);
				g.setColor(Color.red);
				g.drawString("지출: 0원(0%)", 380, 250);
			}
		}
	}
}
