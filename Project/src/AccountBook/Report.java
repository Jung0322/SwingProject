package AccountBook;

import static AccountBook.login.id;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class Report extends JFrame {
	private List<InfoDTO> list;
	private InfoDAO dao = new InfoDAO();
	private JPanel contentPane;

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
		setTitle("통계");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\SwingProject\\SwingProject\\Project\\src\\AccountBook\\pigbank.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		DrawPanel drawPanel = new DrawPanel();
		contentPane.add(drawPanel);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
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
	}

	class DrawPanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			list = dao.select(id);
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
			sum = expence + income; //sum 값
			long expence1 = (int) 360.0 * expence / sum;
			long income1 = (int) 360.0 * income / sum;

			g.setColor(Color.blue);
			g.fillArc(50, 20, 200, 200, 0, (int) income1);
			g.setColor(Color.red);
			g.fillArc(50, 20, 200, 200, (int)income1, (int)expence1);
			g.setFont(new Font("굴림체", Font.PLAIN, 12));
			g.setColor(Color.blue);
			g.drawString("수입: " + Math.round((double)(100 * (double)income / sum *100))/100.0 + "%", 300, 150);
			g.setColor(Color.red);
			g.drawString("지출: " + Math.round((double)(100 * (double)expence / sum*100))/100.0 + "%", 300, 180);
		}
	}
}
