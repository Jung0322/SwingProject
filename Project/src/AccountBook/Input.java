package AccountBook;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import InFo.InfoDAO;
import User.UserDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.Font;
import static AccountBook.login.id;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Input extends JFrame implements ActionListener {

   private JPanel contentPane;
   private JTextField tfDate;
   private JTextField tfMoney;
   private JComboBox<String> combdBox;
   private String PayKind[] = { "식비", "교통", "주거/통신", "경조사/회비", "패션/미용", "교육", "문화생활", "기타" };
   private String IncomeKind[] = { "월급", "부수입", "상여", "금융소득", "용돈", "기타" };
   private JPanel panel_1;
   private static Input frame;
   private JRadioButton rdbtnNewRadioButton_1;
   public InfoDAO dao = new InfoDAO();

   // sort를 가져오기 위하여 kind 선언
   private String kind = "지출";
   ButtonGroup group;

   public Input() {
	   setIconImage(Toolkit.getDefaultToolkit().getImage(Input.class.getResource("/AccountBook/pigbank.png")));
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 600, 450);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);

      JPanel panel = new JPanel();
      panel.setBackground(new Color(255, 255, 255));
      contentPane.add(panel, BorderLayout.SOUTH);

      JButton confirm = new JButton("\uD655\uC778");
      confirm.setFont(new Font("IM혜민 Bold", Font.PLAIN, 23));
      confirm.setForeground(new Color(255, 255, 255));
      confirm.setBackground(Color.BLACK);
      confirm.setHorizontalAlignment(SwingConstants.RIGHT);
      confirm.addActionListener(this);
      panel.add(confirm);

      JButton btnReset = new JButton("초기화");
      btnReset.setFont(new Font("IM혜민 Bold", Font.PLAIN, 23));
      btnReset.setForeground(Color.WHITE);
      btnReset.setBackground(Color.BLACK);
      btnReset.setHorizontalAlignment(SwingConstants.RIGHT);
      btnReset.addActionListener(this);
      panel.add(btnReset);

      panel_1 = new JPanel();
      panel_1.setBackground(new Color(255, 255, 255));
      contentPane.add(panel_1, BorderLayout.CENTER);
      panel_1.setLayout(new GridLayout(3, 2, 0, 0));

      JLabel lblNewLabel_1 = new JLabel("\uB0A0\uC9DC");
      lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_1.setBackground(new Color(255, 255, 255));
      lblNewLabel_1.setFont(new Font("IM혜민 Bold", Font.PLAIN, 28));
      panel_1.add(lblNewLabel_1);

      tfDate = new JTextField();
      tfDate.setFont(new Font("IM혜민 Bold", Font.PLAIN, 23));
      panel_1.add(tfDate);
      tfDate.setColumns(10);

      JLabel lblNewLabel_2 = new JLabel("\uB0B4\uC5ED");
      lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_2.setFont(new Font("IM혜민 Bold", Font.PLAIN, 28));
      panel_1.add(lblNewLabel_2);

      combdBox = new JComboBox();
      combdBox.setFont(new Font("IM혜민 Bold", Font.PLAIN, 23));
      combdBox.setModel(new DefaultComboBoxModel(PayKind));
      panel_1.add(combdBox);

      JLabel lblNewLabel_3 = new JLabel("\uAE08\uC561");
      lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_3.setFont(new Font("IM혜민 Bold", Font.PLAIN, 28));
      panel_1.add(lblNewLabel_3);

      tfMoney = new JTextField();
      tfMoney.setFont(new Font("IM혜민 Bold", Font.PLAIN, 23));
      panel_1.add(tfMoney);
      tfMoney.setColumns(10);

      JPanel panel_2 = new JPanel();
      panel_2.setBackground(new Color(255, 255, 255));
      contentPane.add(panel_2, BorderLayout.NORTH);

      JLabel lblNewLabel = new JLabel(" 입력");
      lblNewLabel.setIcon(new ImageIcon(Input.class.getResource("/AccountBook/edit (2).png")));
      lblNewLabel.setFont(new Font("IM혜민 Bold", Font.PLAIN, 30));
      panel_2.add(lblNewLabel);

      JRadioButton rdbtnNewRadioButton = new JRadioButton("\uC218\uC785");
      rdbtnNewRadioButton.setFont(new Font("IM혜민 Bold", Font.PLAIN, 23));
      rdbtnNewRadioButton.setBackground(new Color(255, 255, 255));
      rdbtnNewRadioButton.addItemListener(new ItemListener() {

         @Override
         public void itemStateChanged(ItemEvent e) {
            combdBox.setModel(new DefaultComboBoxModel(IncomeKind));
            kind = "수입";

         }
      });
      panel_2.add(rdbtnNewRadioButton);

      rdbtnNewRadioButton_1 = new JRadioButton("\uC9C0\uCD9C");
      rdbtnNewRadioButton_1.setFont(new Font("IM혜민 Bold", Font.PLAIN, 23));
      rdbtnNewRadioButton_1.setBackground(new Color(255, 255, 255));
      rdbtnNewRadioButton_1.addItemListener(new ItemListener() {

         @Override
         public void itemStateChanged(ItemEvent e) {
            combdBox.setModel(new DefaultComboBoxModel(PayKind));
            kind = "지출";
         }
      });
      rdbtnNewRadioButton_1.setSelected(true);
      panel_2.add(rdbtnNewRadioButton_1);

      group = new ButtonGroup();
      group.add(rdbtnNewRadioButton);
      group.add(rdbtnNewRadioButton_1);

   }

   @Override
   public void actionPerformed(ActionEvent e) {
      String cmd = e.getActionCommand();

      if (cmd.equals("확인")) {

         boolean flag = dao.Input(tfDate.getText(), combdBox.getSelectedItem().toString(), tfMoney.getText(), kind,
               id);
         if (flag) {
            Main main = new Main();
            main.setVisible(true);
            dispose();
         } else {
            JOptionPane.showMessageDialog(null, "정보를 형식에 맞게 입력하세요.");
         }

      }
      if (cmd.equals("초기화")) {
         group.clearSelection();
         rdbtnNewRadioButton_1.setSelected(true);
         tfDate.setText("");
         combdBox.setSelectedIndex(0);
         tfMoney.setText("");
      }

   }

}