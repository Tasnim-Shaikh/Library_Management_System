import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.Vector;
public class Register_Librarian extends JFrame implements MouseListener,ActionListener{

	JLabel l10;
	PreparedStatement ptmt;
	Statement stmt;
	ResultSet rs;
	DefaultTableModel dtm;
	Connection con;
	JTextField t1,t2,t3;
	int i=1;
	int selectedRow;
	String name,pass,phone;
	JButton b1,b2,b3;
	Register_Librarian()
	{
		Container c=getContentPane();
		c.setLayout(null);
		JPanel p1=new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(110,4,48));
		p1.setBounds(0,0,1200,100);
		c.add(p1);
		JPanel p2=new JPanel();
		p2.setLayout(null);
		p2.setBounds(0,100,1200,670);
		p2.setBackground(Color.WHITE);
		c.add(p2);
		JPanel p3=new JPanel();
		p3.setLayout(null);
		p3.setBounds(0,770,1200,40);
		p3.setBackground(new Color(110,4,48));
		c.add(p3);
		
		JLabel l1=new JLabel("Saraswati Sanchay",JLabel.CENTER);
		l1.setForeground(Color.WHITE);
		Font f=new Font("Times New Roman",Font.BOLD,30);
		l1.setBounds(400,10,400,50);
		l1.setFont(f);
		p1.add(l1);
		JLabel l2=new JLabel("Maintain Librarians",JLabel.CENTER);
		l2.setForeground(Color.WHITE);
		Font f1=new Font("Times New Roman",Font.BOLD,27);
		l2.setBounds(400,50,400,50);
		l2.setFont(f1);
		p1.add(l2);
		JLabel l3=new JLabel("Register Librarians");
		l3.setForeground(new Color(110,4,48));
		l3.setBounds(490,25,250,30);
		l3.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l3);
		
		JLabel l4=new JLabel("Name");
		l4.setBounds(320,75,100,30);
		l4.setForeground(new Color(110,4,48));
		l4.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l4);
		
		 t1=new JTextField(20);
		t1.setBounds(320,120,170,30);
		t1.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(t1);
		
		JLabel l5=new JLabel("Password ");
		l5.setForeground(new Color(110,4,48));
		l5.setBounds(550,75,150,30);
		l5.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l5);
		
		 t2=new JTextField(20);
		t2.setBounds(550,120,170,30);
		t2.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(t2);
		
		JLabel l6=new JLabel("Phone No");
		l6.setForeground(new Color(110,4,48));
		l6.setBounds(820,75,150,30);
		l6.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l6);
		
		 t3=new JTextField(20);
		t3.setBounds(820,120,170,30);
		t3.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(t3);
		
		 b1=new JButton("Save");
		 b1.setForeground(Color.WHITE);
		b1.setBounds(430,170,100,30);
		b1.setBackground(new Color(110,4,48));
		b1.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(b1);
		
		 b2=new JButton("Edit");
		b2.setBounds(600,170,100,30);
		b2.setForeground(Color.WHITE);
		b2.setBackground(new Color(110,4,48));
		b2.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(b2);
		
		 b3=new JButton("Delete");
		b3.setBounds(780,170,100,30);
		b3.setForeground(Color.WHITE);
		b3.setBackground(new Color(110,4,48));
		b3.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(b3);
		
		ImageIcon im=new ImageIcon("images/arr.png");
		l10=new JLabel(im);
		l10.setBounds(0,0,50,50);
		p2.add(l10);
//		
		l10.addMouseListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:7979/Library","root","anu02");
		    stmt=con.createStatement();
	    	rs=stmt.executeQuery("select * from Librarian order by Lid asc");
			
	    String rows[][]= {{"1","Anu","anu02","1230303"},
	    					  {"2","tasn","tas20","1234"}
};
			String cols[]= {"id","Lname","Lpass","Lphone"};
			dtm=new DefaultTableModel(rows,cols);
			JTable jt=new JTable(dtm);
			JScrollPane jsp=new JScrollPane(jt,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			jsp.setBounds(200,250,850,400);
			p2.add(jsp);
			dtm.setRowCount(0);
	    	while(rs.next())
	    	{
	    		Vector v=new Vector();
	    		for(int i=1;i<=100;i++)
	    		{
	    			v.add(rs.getInt("lid"));
	    			v.add(rs.getString("name"));
	    			v.add(rs.getString("pass"));
	    			v.add(rs.getString("phone"));
	   			}
	    		dtm.addRow(v);
	    	}
	    //	con.close();
	    	ListSelectionModel model=jt.getSelectionModel();
			model.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e)
				{
					if(!model.isSelectionEmpty()) {
						selectedRow=jt.getSelectedRow();
						String value=jt.getModel().getValueAt(selectedRow, 0).toString();
						selectedRow=Integer.parseInt(value);
						try {
							stmt=con.createStatement();
						    rs=stmt.executeQuery("select name,pass,phone from Librarian where Lid="+selectedRow);
							while(rs.next())
							{
								name=rs.getString("name");
								pass=rs.getString("pass");
								phone=rs.getString("phone");
							}
							t1.setText(name);
							t2.setText(pass);
							t3.setText(phone);
						}
						catch(Exception e1)
						{
							
						}
					}
				}
			});
		}
		catch(Exception e)
		{
			
		}
		

	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Register_Librarian r1=new Register_Librarian();
		r1.setSize(1200,850);
		r1.setVisible(true);
		r1.setTitle("Register_Librarian");
		r1.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.setVisible(false);
		LMS_Menu l1=new LMS_Menu();
		l1.setVisible(true);
		l1.setSize(700,500);
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		if(arg0.getSource()==b1)
		{
			if(t1.getText().length()>0 && t2.getText().length()>0 && t3.getText().length()>0)
			{
				i++;
				// TODO Auto-generated method stub
				String name=t1.getText();
				String pass=t2.getText();
				String phone=t3.getText();
				try 
				{
					stmt=con.createStatement();
					rs=stmt.executeQuery("select max(Lid) from Librarian");
					while(rs.next())
					{
						
						 i=rs.getInt(1);
						System.out.println(i);
						i=i+1;
					}
				
					ptmt=con.prepareStatement("insert into Librarian values(?,?,?,?)");
					ptmt.setInt(1, i);
					ptmt.setString(2, name);
					ptmt.setString(3, pass);
					ptmt.setString(4, phone);
					
					int x=ptmt.executeUpdate();	
					 stmt=con.createStatement();
					 rs=stmt.executeQuery("select * from Librarian");
					 Vector v=new Vector();
					 v.add(i);
					 v.add(name);
					 v.add(pass);
					 v.add(phone);
					 dtm.addRow(v);
					
				}
				 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
			else
			{
				JOptionPane.showMessageDialog(this, "TextField should not blank");
			}
		
		}
		else if(arg0.getSource()==b2)
		{
			name=t1.getText();
			pass=t2.getText();
			phone=t3.getText();
			try
			{
				String query="update Librarian set name=?,pass=?,phone=? where lid="+selectedRow;
				ptmt=con.prepareStatement(query);
				ptmt.setString(1, name);
				ptmt.setString(2, pass);
				ptmt.setString(3, phone);
				int x=ptmt.executeUpdate();
				if(x==1)
				{
					JOptionPane.showMessageDialog(this, "Data Updated Successfully...");
				}
				stmt=con.createStatement();
				rs=stmt.executeQuery("select * from Librarian order by lid asc");
				dtm.setRowCount(0);
		    	while(rs.next())
		    	{
		    		Vector v=new Vector();
		    		for(int i=1;i<=100;i++)
		    		{
		    			v.add(rs.getInt("lid"));
		    			v.add(rs.getString("name"));
		    			v.add(rs.getString("pass"));
		    			v.add(rs.getString("phone"));
		   			}
		    		dtm.addRow(v);
		    	}
				
			}
			catch(Exception e)
			{
				
			}
			
	
		}
		else if(arg0.getSource()==b3)
		{
			try
			{
				name=t1.getText();
				pass=t2.getText();	
				phone=t3.getText();
				stmt=con.createStatement();
				int x=stmt.executeUpdate("delete from Librarian where lid="+selectedRow);
				stmt=con.createStatement();
		    	rs=stmt.executeQuery("select * from Librarian order by lid asc");
		    	dtm.setRowCount(0);
		    	
				while(rs.next())
			    {Vector v=new Vector();
			   		
			   		for(int i=1;i<=100;i++)
			   		{
			   			v.add(rs.getInt("lid"));
			   			v.add(rs.getString("name"));
			   			v.add(rs.getString("pass"));
			   			v.add(rs.getString("phone"));
			   		}
			    	dtm.addRow(v);
			    }	
			}
			
			catch(Exception e)
			{
				
			}
			JOptionPane.showMessageDialog(this, "Data Deleted Successfully");
		}
		
	}
}


