package AccountBook;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import User.UserDAO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame {
 
   private JPanel contentPane;
   public static String id;
   private JTextField TextId;
   private JPasswordField Textpassword;
   public UserDAO dao = new UserDAO();
   private static login frame;
 
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
            	frame = new login();
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
   public login() {
      setFont(new Font("IM혜민 Bold", Font.PLAIN, 12));
      setTitle("가계부");
      setIconImage(Toolkit.getDefaultToolkit().getImage(login.class.getResource("/AccountBook/pigbank.png")));
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 480, 620);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);
      
      JPanel panel = new JPanel();
      panel.setBackground(Color.WHITE);
      contentPane.add(panel, BorderLayout.NORTH);
      panel.setLayout(new GridLayout(0, 1, 0, 0));
      
      JLabel lblNewLabel_23 = new JLabel("");
      lblNewLabel_23.setBackground(Color.WHITE);
      panel.add(lblNewLabel_23);
      
      JLabel lblNewLabel_1 = new JLabel("\"알뜰살뜰 가계부\"");
      lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 30));
      panel.add(lblNewLabel_1);
      
      JLabel lblNewLabel_24 = new JLabel("티끌모아 건물주되자 ♪");
      lblNewLabel_24.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_24.setFont(new Font("Dialog", Font.PLAIN, 18));
      panel.add(lblNewLabel_24);
      
      JLabel lblNewLabel_25 = new JLabel("");
      lblNewLabel_25.setFont(new Font("굴림", Font.PLAIN, 20));
      lblNewLabel_25.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_25.setIcon(new ImageIcon(login.class.getResource("/AccountBook/free-icon-piggy-bank-2489008 (2).png")));
      panel.add(lblNewLabel_25);
      
      JPanel panel_1 = new JPanel();
      panel_1.setBackground(Color.WHITE);
      contentPane.add(panel_1, BorderLayout.CENTER);
      panel_1.setLayout(new GridLayout(0, 4, 0, 0));
      
      JLabel lblNewLabel_2 = new JLabel("");
      panel_1.add(lblNewLabel_2);
      
      JLabel lblNewLabel_4 = new JLabel("");
      panel_1.add(lblNewLabel_4);
      
      JLabel lblNewLabel_3 = new JLabel("");
      panel_1.add(lblNewLabel_3);
      
      JLabel lblNewLabel_22 = new JLabel("");
      panel_1.add(lblNewLabel_22);
      
      JLabel lblNewLabel_5 = new JLabel("");
      panel_1.add(lblNewLabel_5);
      
      JLabel lblNewLabel_7 = new JLabel("ID");
      lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_7.setFont(new Font("IM혜민 Bold", Font.PLAIN, 20));
      panel_1.add(lblNewLabel_7);
      
      TextId = new JTextField();
      TextId.setFont(new Font("굴림", Font.PLAIN, 20));
      panel_1.add(TextId);
      TextId.setColumns(10);
      
      JLabel lblNewLabel_6 = new JLabel("");
      panel_1.add(lblNewLabel_6);
      
      JLabel lblNewLabel_8 = new JLabel("");
      panel_1.add(lblNewLabel_8);
      
      JLabel lblNewLabel_11 = new JLabel("");
      panel_1.add(lblNewLabel_11);
      
      JLabel lblNewLabel_15 = new JLabel("");
      panel_1.add(lblNewLabel_15);
      
      JLabel lblNewLabel_10 = new JLabel("");
      panel_1.add(lblNewLabel_10);
      
      JLabel lblNewLabel_12 = new JLabel("");
      panel_1.add(lblNewLabel_12);
      
      JLabel lblNewLabel_9 = new JLabel("PW");
      lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_9.setFont(new Font("IM혜민 Bold", Font.PLAIN, 20));
      panel_1.add(lblNewLabel_9);
      
      Textpassword = new JPasswordField();
      Textpassword.setEchoChar('*');
      Textpassword.setFont(new Font("굴림", Font.PLAIN, 20));
      Textpassword.setText("");
      panel_1.add(Textpassword);
      Textpassword.setColumns(10);
      
      JLabel lblNewLabel_13 = new JLabel("");
      panel_1.add(lblNewLabel_13);
      
      JLabel lblNewLabel_16 = new JLabel("");
      panel_1.add(lblNewLabel_16);
      
      JLabel lblNewLabel_14 = new JLabel("");
      panel_1.add(lblNewLabel_14);
      
      JLabel lblNewLabel_17 = new JLabel("");
      panel_1.add(lblNewLabel_17);
      
      JLabel lblNewLabel_18 = new JLabel("");
      panel_1.add(lblNewLabel_18);
      
      JPanel panel_2 = new JPanel();
      panel_2.setBackground(Color.WHITE);
      contentPane.add(panel_2, BorderLayout.SOUTH);
      panel_2.setLayout(new GridLayout(0, 2, 0, 0));
      
      JButton btnNewButton = new JButton("로그인");
      btnNewButton.setIcon(new ImageIcon(login.class.getResource("/AccountBook/check-mark-3-16.png")));
      btnNewButton.setBackground(Color.BLACK);
      btnNewButton.setForeground(Color.WHITE);
      btnNewButton.setBackground(Color.BLACK);
      btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 20));
      btnNewButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Boolean flag =  dao.login(TextId.getText(), Textpassword.getText());
			if(flag) {
				JOptionPane.showMessageDialog(null, "로그인이 완료되었습니다.");
				id = TextId.getText();
				Main main = new Main();
				main.setVisible(true);
//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "로그인이 실패히었습니다.");
			}
			
		}
	});
      panel_2.add(btnNewButton);
      
      JButton btnNewButton_1 = new JButton("회원가입");
      btnNewButton_1.setIcon(new ImageIcon(login.class.getResource("/AccountBook/arrow-34-16.png")));
      btnNewButton_1.setBackground(Color.BLACK);
      btnNewButton_1.setForeground(Color.WHITE);
      btnNewButton_1.setBackground(Color.BLACK);
      btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Join join = new Join();
				join.setVisible(true);
//				frame.setVisible(false);
				dispose();
			}
		});
      btnNewButton_1.setFont(new Font("Dialog", Font.PLAIN, 20));
      panel_2.add(btnNewButton_1);
      
      JLabel lblNewLabel = new JLabel("");
      panel_2.add(lblNewLabel);
      
      JLabel lblNewLabel_19 = new JLabel("");
      panel_2.add(lblNewLabel_19);
      
      JLabel lblNewLabel_20 = new JLabel("");
      panel_2.add(lblNewLabel_20);
   }

}


