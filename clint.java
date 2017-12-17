package proj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.sql.*;
import java.util.Random;
/**
 *
 * @author Vishesh94
 */
public class clint extends JFrame implements ActionListener{
 JLabel l1, l2, l3,l4,l5,l6,l7;
     JTextField tf1, tf2, tf3,tf4,tf5,tf6,tf7;
     JButton btn1;
    JOptionPane jp = new JOptionPane();
	clint(){
                        //JPanel tkf= new JPanel();
                        //replaced above frame by panel const arg new FlowLayout(FlowLayout.LEFT,2,2)
                        l1=new JLabel("Company Name");
                        l2=new JLabel("Weight");
                        l3=new JLabel("Source");
                        l4=new JLabel("Date");
                        l5=new JLabel("Destination");
                        l6=new JLabel("Date");
                        btn1=new JButton("Submit & Generate");
                
                        tf1= new JTextField("",15);
                        tf2= new JTextField("",10);
                        tf3= new JTextField("",5);
                        tf4= new JTextField("",15);
                        tf5= new JTextField("",10);
                        tf6= new JTextField("",3);
                                          
                        JPanel o1 = new JPanel();
                        o1.setBorder(new TitledBorder("Contract for"));
                        o1.add(l1);o1.add(tf1);
                        o1.add(l2);o1.add(tf2);
                        
                        JPanel o2 = new JPanel();
                        o2.setBorder(new TitledBorder("Source"));
                        o2.add(l3);o2.add(tf3);
                        o2.add(l4);o2.add(tf4);
 
                        JPanel o3 = new JPanel();
                        o3.setBorder(new TitledBorder("Destination"));
                        o3.add(l5);o3.add(tf5);
                        o3.add(l6);o3.add(tf6);
 
                        //JPanel o = new JPanel();
                        setLayout(new GridLayout(4,1,5,5));
                        add(o1);add(o2);add(o3);add(btn1);
                        
                        btn1.addActionListener(this);
        }   
        
        public void actionPerformed(ActionEvent e){        
        if(e.getSource()==btn1){
            
               try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection com = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vishesh","1234");
                Statement st = com.createStatement();
                ResultSet rs = st.executeQuery("select * from consignment");
                int c = 1;
                while(rs.next()){ c++;}
                String s = "" +c;
                //random no t_id
                ResultSet qs = st.executeQuery("select * from truck");
                int tno = 1;
                while(rs.next()){ tno++;}
                Random randomGenerator = new Random();
                int rtno = randomGenerator.nextInt(tno);
                String rn = "" +rtno;
                PreparedStatement ws = com.prepareStatement("insert into consignment values(?,?,?,?,?,?,?,?)");
                    ws.setString(1,s);
                    ws.setString(2,rn);
                    ws.setString(3,tf3.getText());
                    ws.setString(4,tf4.getText());
                    ws.setString(5,tf5.getText());
                    ws.setString(6,tf6.getText());
                    ws.setString(7,tf2.getText());
                    ws.setString(8,tf1.getText());
                    ws.executeUpdate();
                JOptionPane.showMessageDialog(null, "New Consignment saved & C_ID is "+ s);   
                com.close();
		st.close();
            }
         catch(ClassNotFoundException cnf)
	  {
            jp.showMessageDialog(this,cnf,"EXCEPTION",jp.ERROR_MESSAGE);
	   }
	catch(SQLException sql)
	{
	jp.showMessageDialog(this,sql,"EXCEPTION",jp.ERROR_MESSAGE);
	}
        }

    }
}
