package AccountBook;

import java.awt.BorderLayout;
import static AccountBook.login.id;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
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
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Main extends JFrame{

	private JPanel contentPane;
	private JTextField text_income;
	private JTextField text_expence;
	private JTextField text_total;
	private JTable table;
	public static Main frame;
	private List<InfoDTO> list;
	private InfoDAO dao = new InfoDAO();
	private DefaultTableModel model;
	private int val = 0;

	private DecimalFormat formatter = new DecimalFormat("###,###원");
	private String kind[] = { "전체","수입", "지출" };
	private JComboBox<String> comboBox;



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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/AccountBook/pigbank.png")));
		setTitle("내 가계부");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("수입");
		lblNewLabel.setFont(new Font("IM혜민 Bold", Font.PLAIN, 20));
		panel.add(lblNewLabel);
		
		text_income = new JTextField();
		text_income.setFont(new Font("IM혜민 Bold", Font.PLAIN, 20));
		text_income.setHorizontalAlignment(SwingConstants.RIGHT);
	    text_income.setText(formatter.format(income()));
		panel.add(text_income);
		text_income.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("지출");
		lblNewLabel_1.setFont(new Font("IM혜민 Bold", Font.PLAIN, 20));
		panel.add(lblNewLabel_1);
		
		text_expence = new JTextField();
		text_expence.setFont(new Font("IM혜민 Bold", Font.PLAIN, 20));
		text_expence.setHorizontalAlignment(SwingConstants.RIGHT);
		text_expence.setText(formatter.format(expence()));
		panel.add(text_expence);
		text_expence.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("총합");
		lblNewLabel_2.setFont(new Font("IM혜민 Bold", Font.PLAIN, 20));
		panel.add(lblNewLabel_2);
		
		text_total = new JTextField();
		text_total.setFont(new Font("IM혜민 Bold", Font.PLAIN, 20));
		text_total.setHorizontalAlignment(SwingConstants.RIGHT);
		text_total.setText(formatter.format(income()-expence()));
		panel.add(text_total);
		text_total.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		
		JButton Input = new JButton("입력");
		Input.setFont(new Font("IM혜민 Bold", Font.PLAIN, 20));
		Input.setHorizontalAlignment(SwingConstants.LEFT);
		Input.setIcon(new ImageIcon(Main.class.getResource("/AccountBook/plus-16.png")));
		Input.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Input input = new Input();
				input.setVisible(true);
				dispose();
				
			}
		});
		panel_1.add(Input);
		
		JButton edit = new JButton("수정");
		edit.setFont(new Font("IM혜민 Bold", Font.PLAIN, 20));
		edit.setIcon(new ImageIcon(Main.class.getResource("/AccountBook/edit-2-16.gif")));
		edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
	
				try {
					val = (int) model.getValueAt(table.getSelectedRow(), 0);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
					
				
				if (cmd.equals("수정")) {
					if(val==0) {
						JOptionPane.showMessageDialog(null, "수정할 내역을 선택해주세요.");
					}else {
						Update update = new Update(val);
						update.setVisible(true);
						dispose();
					}
								
				
				}
			}
		});
		panel_1.add(edit);
		
		JButton report = new JButton("통계");
		report.setFont(new Font("IM혜민 Bold", Font.PLAIN, 20));
		report.setIcon(new ImageIcon(Main.class.getResource("/AccountBook/pie-chart-16.png")));
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
		//table.addMouseListener(this);
		table.setRowHeight(30);
		table.setFont(new Font("IM혜민 Bold", Font.PLAIN, 20));
		
		String columnNames [] = {"No","Day","Sort","Content","Money"};
		model = new DefaultTableModel(columnNames,0) {
            
            @Override
             public boolean isCellEditable (int row,int column) {
               return false;
            }
                      
         };
         
        table.setModel(model);
        table.getColumnModel().getColumn(0).setMinWidth(0);
	    table.getColumnModel().getColumn(0).setMaxWidth(0);	
         
	    comboBox = new JComboBox();
	    comboBox.setFont(new Font("IM혜민 Bold", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(kind));
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (comboBox.getSelectedItem().toString().equals("수입")) {
					IncomeshowTable();
				} else if(comboBox.getSelectedItem().toString().equals("지출")){
					ExpenseshowTable();
				}else {
					TotalshowTable();
				}

				
			}
		});
		panel.add(comboBox);
		 scrollPane.setViewportView(table);
		TotalshowTable();
        
 
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
	public void IncomeshowTable() {
		model.setNumRows(0);
		if (!list.isEmpty()) {
			for (InfoDTO dto : list) {
				if (dto.getSort().equals("수입")) {
					Vector<Object> newVec = new Vector<Object>();
					newVec.add(dto.getNo());
					newVec.add(dto.getDay());
					newVec.add(dto.getSort());
					newVec.add(dto.getContent());

					newVec.add(formatter.format(dto.getMoney()));
					model.addRow(newVec);
				}
			}
		}
	}

	

	public void ExpenseshowTable() {
		model.setNumRows(0);
		if (!list.isEmpty()) {
			for (InfoDTO dto : list) {
				if (dto.getSort().equals("지출")) {
					Vector<Object> newVec = new Vector<Object>();
					newVec.add(dto.getNo());
					newVec.add(dto.getDay());
					newVec.add(dto.getSort());
					newVec.add(dto.getContent());
					newVec.add(formatter.format(dto.getMoney()));
					model.addRow(newVec);
				}
			}
		}
	}
	
	public void TotalshowTable() {
		model.setNumRows(0);
		if (!list.isEmpty()) {
			for (InfoDTO dto : list) {
					Vector<Object> newVec = new Vector<Object>();
					newVec.add(dto.getNo());
					newVec.add(dto.getDay());
					newVec.add(dto.getSort());
					newVec.add(dto.getContent());
					newVec.add(formatter.format(dto.getMoney()));
					model.addRow(newVec);
				
			}
		}
	}


}
