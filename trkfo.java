/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author Vishesh94
 */

class trkfo extends JFrame implements ActionListener{
     JLabel l1, l2, l3;
     JTextField tf1, tf2, tf3;
     JButton btn1;
     JOptionPane jp = new JOptionPane();
	trkfo(){
                        //JPanel tkf= new JPanel();
                        //replaced above frame by panel const arg new FlowLayout(FlowLayout.LEFT,2,2)
                        l1=new JLabel("Driver Name");
                        l2=new JLabel("Licence");
                        l3=new JLabel("Weight (Truck)");
                
                        btn1=new JButton("Submit & Generate");
                
                        tf1= new JTextField("",15);
                        tf2= new JTextField("",10);
                        tf3= new JTextField("",5);
                        
                        JPanel o1 = new JPanel();
                        JPanel o2 = new JPanel();
                        JPanel o3 = new JPanel();
                        JPanel o = new JPanel();
                        o1.add(l1);o1.add(tf1);
                        o2.add(l2);o2.add(tf2);
                        o3.add(l3);o3.add(tf3);
                        o.add(btn1);
                        add(o1);add(o2);add(o3);add(o);
                        setLayout(new GridLayout(4,1,5,5));
                        
                        btn1.addActionListener(this);
        }
    
    
    public void actionPerformed(ActionEvent e){        
        if(e.getSource()==btn1){
        //generate save & t_id
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection com = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vishesh","1234");
                Statement st = com.createStatement();
                ResultSet rs = st.executeQuery("select * from truck");
                int c = 1;
                while(rs.next()){ c++;}
                String s = "" +c;
                PreparedStatement ws = com.prepareStatement("insert into truck values(?,?,?,?)");
                    ws.setString(1,s);
                    ws.setString(3,tf1.getText());
                    ws.setString(2,tf2.getText());
                    ws.setString(4,tf3.getText());
                    ws.executeUpdate();
                JOptionPane.showMessageDialog(null, "New Truck saved & T_ID is "+ s);   
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

    /*
    public static void main(String[] args){
        trkfo obj=new trkfo();
        obj.setVisible(true);
        obj.setSize(750, 500);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setTitle("Registration Form in Java");
        
        
        
    }*/