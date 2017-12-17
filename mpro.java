
package proj;

/**
 *
 * @author Vishesh94
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.sql.*;

public class mpro extends JFrame implements ActionListener {
                JButton trkid =new JButton("Truck ID");
		JButton clid = new JButton("Client ID");
		JButton search = new JButton("Search");
                JLabel sa = new JLabel();
                JRadioButton r1;
                JRadioButton r2;
                JTextField tf;
                JOptionPane jp = new JOptionPane();
	mpro(){
		JPanel p1 = new JPanel(/*new FlowLayout(1,2,2)*/);
		p1.add(trkid);
		p1.add(clid);
		p1.setBorder(new TitledBorder("Create New"));
		
		JPanel p2 = new JPanel();
		tf = new JTextField("",17);
                
		r1=new JRadioButton("T_ID");
                r2=new JRadioButton("C_ID");
                
                r1.setSelected(true);
                final ButtonGroup group = new ButtonGroup();
                group.add(r1);
                group.add(r2);
                
		p2.add(tf);
		p2.add(r1);
                p2.add(r2);
                p2.add(search);
		p2.setBorder(new TitledBorder("Search"));
		
		setLayout(new GridLayout(2,1,5,2));
		add(p1);
		add(p2);
                
		trkid.addActionListener(this);
		clid.addActionListener(this);
                r1.setActionCommand("T_ID");
                r2.setActionCommand("C_ID");
		//search.addActionListener(this);
                
                search.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String command = group.getSelection().getActionCommand();
                try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection com = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vishesh","1234");
                Statement st = com.createStatement();
                
                //Check the selection
                if (command.equals("T_ID")) {
                    ResultSet rs = st.executeQuery("select * from TRUCK where T_ID="+tf.getText());
			while(rs.next())
		       	{JOptionPane.showMessageDialog(null,"Driver   "+ rs.getString("Driv_Name")
                         +"\n"+"License "+ rs.getString("Licence") + "\n" + "Weight  " + rs.getString("Weightt")+" Ton");}
                }
                if(command.equals("C_ID")) {
                    ResultSet rs = st.executeQuery("select * from consignment where C_ID="+tf.getText());
			while(rs.next())
		       	{JOptionPane.showMessageDialog(null,"T_ID"+ rs.getString("T_ID")
                                                    +"\n"+"Source"+rs.getString("source")
                                                    +"\n"+"Source Date" + rs.getString("sdate")
                                                    +"\n"+"Destination"+ rs.getString("destination")
                                                    +"\n"+"Destination Date" + rs.getString("ddate")
                                                    +"\n"+"Weight (ton)"+ rs.getString("weight")
                                                    +"\n"+"Company" + rs.getString("Company")
                                );}
                }
                com.close();
                st.close();
            }
	catch(ClassNotFoundException cnf)
	  {
            jp.showMessageDialog(null,cnf,"EXCEPTION",jp.ERROR_MESSAGE);
	   }
	catch(SQLException sql)
	{
	jp.showMessageDialog(null,sql,"EXCEPTION",jp.ERROR_MESSAGE);
	}
            }
        });
                
	}
       
        public void actionPerformed(ActionEvent e){
        if(e.getSource()==trkid){
            JFrame trk = new trkfo();
            trk.setTitle("Truck ID");
            trk.pack();
            trk.setSize(400,250);
            trk.setVisible(true);
		}
        if(e.getSource()==clid){
            JFrame oj = new clint();
            oj.setTitle("Client ID");
            oj.setSize(400,350);
            oj.setVisible(true);
		}
        }
        
        public static void main(String[] args){
		JFrame frame = new mpro();
		frame.setTitle("Truck Management");
		frame.setSize(400,250);
		//frame.;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}