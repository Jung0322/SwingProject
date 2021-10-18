package AccountBook;

import java.awt.BorderLayout;
import static AccountBook.login.id;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import InFo.InfoDAO;
import InFo.InfoDTO;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField text_income;
	private JTextField text_expence;
	private JTextField text_total;
	private JTable table;
	public static Main frame;
	private List<InfoDTO> list;
	private InfoDAO dao = new InfoDAO();
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Income");
		panel.add(lblNewLabel);
		
		text_income = new JTextField();
		text_income.setText(String.valueOf(income()));
		panel.add(text_income);
		text_income.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Expence");
		panel.add(lblNewLabel_1);
		
		text_expence = new JTextField();
		text_expence.setText(String.valueOf(expence()));
		panel.add(text_expence);
		text_expence.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Total");
		panel.add(lblNewLabel_2);
		
		text_total = new JTextField();
		text_total.setText(String.valueOf(income()-expence()));
		panel.add(text_total);
		text_total.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton input = new JButton("Input");
		input.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Input input = new Input();
				input.setVisible(true);
				dispose();
				
			}
		});
		panel_1.add(input);
		
		JButton edit = new JButton("Edit");
		edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Update update = new Update();
				update.setVisible(true);
				dispose();
			}
		});
		panel_1.add(edit);
		
		JButton report = new JButton("statistic");
		report.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(income()==0&&expence()==0) {
					JOptionPane.showMessageDialog(null, "통계할 데이터가 없습니다.");
				}else {
					Report report = new Report();
					report.setVisible(true);
					dispose();
				}
			}
		});
		panel_1.add(report);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		String columnNames [] = {"Day","Sort","Content","Money"};
		model = new DefaultTableModel(columnNames,0) {
            
            @Override
             public boolean isCellEditable (int row,int column) {
               return false;
            }
                      
         };
         
         table.setModel(model);
         
         scrollPane.setViewportView(table);
         showTable();
        
	}

	//수입 함수
	public int income() {
		list = dao.select(id);
		int  sum = 0;
		for(int i = 0 ; i<list.size();i++) {
			InfoDTO dto = list.get(i);
			if(dto.getSort().equals("수입")) {
				sum+=dto.getMoney();
			}
			
		}
		return sum;
		
	}
	
	//지출함수
	public int expence() {
		list = dao.select(id);
		int  sum = 0;
		for(int i = 0 ; i<list.size();i++) {
			InfoDTO dto = list.get(i);
			if(dto.getSort().equals("지출")) {
				sum+=dto.getMoney();
			}
			
		}
		return sum;
		
	}
	// select 보여주는 함수
	public void showTable() {
		if(!list.isEmpty()) {
			for(InfoDTO dto : list) {
				Vector<Object> newVec = new Vector<Object>();
				newVec.add(dto.getDay());
				newVec.add(dto.getSort());
				newVec.add(dto.getContent());
				newVec.add(dto.getMoney());
				model.addRow(newVec);
			}
		}
	}

}

