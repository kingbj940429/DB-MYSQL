
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class bookListSwing_15011752 extends JFrame implements ActionListener {
   JButton btnOk, btnReset, btnOk2, btnOk3, btnOk4;
   JTextArea txtResult;
   JPanel pn1;

   Connection con;
   Statement stmt;
   ResultSet rs;
   String Driver = "";
   String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
   String userid = "madang";
   String pwd = "madang";

   public bookListSwing_15011752() {
      super("15011752/�躴��");
      layInit();
      conDB();
      setVisible(true);
      setBounds(200, 200, 800, 600); //������ġ,������ġ,���α���,���α���
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public void layInit() {
      btnOk = new JButton("�˻�1 (book)");
      btnOk2 = new JButton("�˻�2 (orders)");
      btnOk3 = new JButton("�˻�3 (customer)");
      btnOk4 = new JButton("�Է� 1");
      btnReset = new JButton("�ʱ�ȭ");
      txtResult = new JTextArea();
      pn1 = new JPanel();
      pn1.add(btnOk);
      pn1.add(btnOk2);
      pn1.add(btnOk3);
      pn1.add(btnOk4);
      pn1.add(btnReset);
      txtResult.setEditable(false);
      JScrollPane scrollPane = new JScrollPane(txtResult);
      add("North", pn1);
      add("Center", scrollPane);
      try { /* �����ͺ��̽��� �����ϴ� ���� */
          System.out.println("�����ͺ��̽� ���� �غ�...");
          con = DriverManager.getConnection(url, userid, pwd);
          System.out.println("�����ͺ��̽� ���� ����");
       } catch (SQLException e1) {
          e1.printStackTrace();
       }
      btnOk.addActionListener(this);
      btnOk2.addActionListener(this);
      btnOk3.addActionListener(this);
      btnOk4.addActionListener(this);
      btnReset.addActionListener(this);
   }

   public void conDB() {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         System.out.println("����̹� �ε� ����");
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      try { /* �����ͺ��̽��� �����ϴ� ���� */
//         System.out.println("�����ͺ��̽� ���� �غ�...");
         con = DriverManager.getConnection(url, userid, pwd);
//         System.out.println("�����ͺ��̽� ���� ����");
      } catch (SQLException e1) {
         e1.printStackTrace();
      }
      try {
         stmt = con.createStatement();
         String query = "SELECT * FROM Book ";
         String query2 = "SELECT * FROM Orders ";
         String query3 = "SELECT * FROM Customer ";
         if (e.getSource() == btnOk) {//book�� ���� ��.
            txtResult.setText("");
            txtResult.setText("BOOKNO           BOOK NAME       PUBLISHER      PRICE\n");
            rs = stmt.executeQuery(query);
            while (rs.next()) {
               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4)
                     + "\n";
               txtResult.append(str);
            }
            
         }
         else if (e.getSource() == btnOk2) {//orders�� ���Ѱ�
             txtResult.setText("");
             txtResult.setText("ORDERID          CUSTID              BOOKID              SALEPRICE          ORDERDATE\n");
             rs = stmt.executeQuery(query2);
             while (rs.next()) {
                String str2 = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4) + "\t    " + rs.getString(5)
                      + "\n";
                txtResult.append(str2);
             }
             
          }
         else if (e.getSource() == btnOk3) {//customer�� ���Ѱ�
             txtResult.setText("");
             txtResult.setText("CUSTID              NAME                   ADDRESS              PHONE\n");
             rs = stmt.executeQuery(query3);
             while (rs.next()) {
                String str3 = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t    " + rs.getString(4)
                      + "\n";
                txtResult.append(str3);
             }
             
          }else if (e.getSource() == btnReset) {
            txtResult.setText("");
         }
      } catch (Exception e2) {
         System.out.println("���� �б� ���� :" + e2);
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (stmt != null)
               stmt.close();
            if (con != null)
               con.close();
         } catch (Exception e3) {
            // TODO: handle exception
         }
      }
   }

   public static void main(String[] args) {
      new bookListSwing_15011752();
   }
}

