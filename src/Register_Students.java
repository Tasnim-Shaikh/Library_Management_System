import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.CallableStatement;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
public class Register_Students extends JFrame implements MouseListener,ActionListener,ListSelectionListener{

	
	PreparedStatement ptmt;
	Statement stmt;
	ResultSet rs;
	DefaultTableModel dtm;
	Connection con;
	JTextField t1,t2,t4;
	JComboBox t3;
	String ph;
	int selectedRow=0;
	JButton b1,b2,b3,b4;
	int i=0;
	String name,dep,sem,phone;
	JTable jt;
	Register_Students()
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
		JLabel l2=new JLabel("Maintain Students",JLabel.CENTER);
		l2.setForeground(Color.WHITE);
		Font f1=new Font("Times New Roman",Font.BOLD,30);
		l2.setBounds(400,50,400,50);
		l2.setFont(f1);
		p1.add(l2);
		JLabel l3=new JLabel("Register Students");
		l3.setForeground(new Color(110,4,48));
		l3.setBounds(520,20,250,30);
		l3.setFont(new Font("Times New Roman",Font.BOLD,27));
		p2.add(l3);
		
		
		
		JLabel l4=new JLabel("Name");
		l4.setForeground(new Color(110,4,48));
		l4.setBounds(150,75,100,30);
		l4.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l4);
		
		 t1=new JTextField(20);
		t1.setBounds(150,120,170,30);
		t1.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(t1);
		
		JLabel l5=new JLabel("Department ");
		l5.setForeground(new Color(110,4,48));
		l5.setBounds(400,75,150,30);
		l5.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l5);
		
		 t2=new JTextField(20);
		t2.setBounds(400,120,170,30);
		t2.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(t2);
		
		JLabel l6=new JLabel("Semester");
		l6.setForeground(new Color(110,4,48));
		l6.setBounds(650,75,150,30);
		l6.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l6);
		
		 t3=new JComboBox();
		t3.addItem("1st");
		t3.addItem("2nd");
		t3.addItem("3rd");
		t3.addItem("4th");
		t3.addItem("5th");
		t3.setBounds(650,120,170,30);
		t3.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(t3);
		
		JLabel l7=new JLabel("Phone");
		l7.setForeground(new Color(110,4,48));
		l7.setBounds(900,75,150,30);
		l7.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l7);
		
		 t4=new JTextField(20);
		t4.setBounds(900,120,170,30);
		t4.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(t4);
	
		 b1=new JButton("Save");
		b1.setBounds(380,200,100,30);
		b1.setBackground(new Color(110,4,48));
		b1.setForeground(Color.WHITE);
		b1.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(b1);
		
		b2=new JButton("Edit");
		b2.setBounds(540,200,100,30);
		b2.setBackground(new Color(110,4,48));
		b2.setForeground(Color.white);
		b2.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(b2);
		
		 b3=new JButton("Delete");
		b3.setBounds(700,200,100,30);
		b3.setBackground(new Color(110,4,48));
		b3.setForeground(Color.WHITE);
		b3.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(b3);
		
	
		
		 b4=new JButton("Refresh");
		b4.setBounds(540,250,100,30);
		b4.setBackground(new Color(110,4,48));
		b4.setForeground(Color.white);
		b4.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(b4);
		
		
		ImageIcon im=new ImageIcon("images/arr.png");
		JLabel l10=new JLabel(im);
		l10.setBounds(5,0,50,50);
		p2.add(l10);
		l10.addMouseListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:7979/Library","root","anu02");
		    stmt=con.createStatement();
	    	rs=stmt.executeQuery("select * from student order by Sid asc");
			
			String cols[]= {"Sid","Sname","Sdep","Ssem","Sphone"};
			String rows[][]= {{"1","Abc","Co","2nd","8010934254"}};
			dtm=new DefaultTableModel(rows,cols);
			 jt=new JTable(dtm);
			JScrollPane jsp=new JScrollPane(jt,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			jsp.setBounds(120,320,950,320);
			p2.add(jsp);
	    	dtm.setRowCount(0);
	    	while(rs.next())
	    	{
	    		Vector v=new Vector();
	    		for(int i=1;i<=100;i++)
	    		{
	    			v.add(rs.getInt("Sid"));
	    			v.add(rs.getString("Sname"));
	    			v.add(rs.getString("Sdep"));
	    			v.add(rs.getString("Ssem"));
	    			v.add(rs.getString("Sphone"));
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
						    rs=stmt.executeQuery("select Sname,Sdep,Ssem,Sphone from student where Sid="+selectedRow);
							while(rs.next())
							{
								name="anu";
								name=rs.getString("Sname");
								dep=rs.getString("Sdep");
								sem=rs.getString("Ssem");
								phone=rs.getString("Sphone");
							}
							t1.setText(name);
							t2.setText(dep);
							t3.setSelectedItem(sem);
							t4.setText(phone);
							

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
	public static void main(String[] args) throws Exception
	{
		Register_Students r1=new Register_Students();
		r1.setTitle("Register_Students");
		r1.setVisible(true);
		r1.setSize(1200,850);
		r1.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setVisible(false);
		LMS_Menu l1=new LMS_Menu();
		l1.setVisible(true);
		l1.setSize(700,500);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		i++;
		// TODO Auto-generated method stub
		
		if(arg0.getSource()==b1)
		{
			
			if(t1.getText().length()>0 &&t2.getText().length()>0 && t4.getText().length()>0)
			{
				String name=t1.getText();
				String dep=t2.getText();
				String sem=(String) t3.getSelectedItem();
				String mob=t4.getText();
				int flag=0;
				
				String nm;
				String dp;
				String se;
				try 
				{
					stmt=con.createStatement();
					rs=stmt.executeQuery("select * from Student order by Sid asc");
					while(rs.next())
					{
						ph=rs.getString(5);
						nm=rs.getString(2);
						dp=rs.getString(3);
						se=rs.getString(4);	
						if(ph.equals(mob) && nm.equals(name) && dp.equals(dep) && se.equals(sem))
						{
							flag=1;
							break;
						}
					}
					if(flag==0 && mob.length()==10)
					{
						stmt=con.createStatement();
						rs=stmt.executeQuery("select max(Sid) from student");
						while(rs.next())
						{
							 i=rs.getInt(1);
							i=i+1;
						}
						String sql = "{CALL insertData(?,?,?,?,?)}";
						CallableStatement cs =(CallableStatement)con.prepareCall(sql);
						cs.setInt(1, i);
						cs.setString(2, name);
						cs.setString(3, dep);
						cs.setString(4, sem);
						cs.setString(5,mob);
						cs.executeUpdate();
						//int x=ptmt.executeUpdate();	
						 stmt=con.createStatement();
						 rs=stmt.executeQuery("select * from student");
						 Vector v=new Vector();
						 v.add(i);
						 v.add(name);
						 v.add(dep);
						 v.add(sem);
						 v.add(mob);
						 dtm.addRow(v);
						JOptionPane.showMessageDialog(this,"Data inserted Successfully");
					}
					else if(flag==1)
					{
						JOptionPane.showMessageDialog(this,"Student is Already exist");
					}
					else if(mob.length()!=10)
					{
						JOptionPane.showMessageDialog(this,"Mobile number should be of 10 digit");
					}
				
				}
				 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "TextField should not be blank");
			}
		
	
		}
		else if(arg0.getSource()==b3)
		{
			try
			{
				name=t1.getText();
				dep=t2.getText();
				sem=(String) t3.getSelectedItem();
				phone=t4.getText();
				stmt=con.createStatement();
				int x=stmt.executeUpdate("delete from student where Sid="+selectedRow);
				stmt=con.createStatement();
		    	rs=stmt.executeQuery("select * from student order by Sid asc");
		    	dtm.setRowCount(0);
		    	
				while(rs.next())
			    {Vector v=new Vector();
			   		
			   		for(int i=1;i<=100;i++)
			   		{
			   			v.add(rs.getInt("Sid"));
			   			v.add(rs.getString("Sname"));
			   			v.add(rs.getString("Sdep"));
			   			v.add(rs.getString("Ssem"));
			   			v.add(rs.getString("Sphone"));
			   		}
			    	dtm.addRow(v);
			    }	
			}
			
			catch(Exception e)
			{
				
			}
			JOptionPane.showMessageDialog(this, "Data Deleted Successfully");
		}
		else if(arg0.getSource()==b4)
		{
			t1.setText("");
			t2.setText("");
			t3.setSelectedIndex(0);
			t4.setText("");
		}
		else if(arg0.getSource()==b2)
		{
			name=t1.getText();
			dep=t2.getText();
			sem=(String) t3.getSelectedItem();
			String ph=t4.getText();
			try
			{
				String query="update student set Sname=?,Sdep=?,Ssem=?,Sphone=? where Sid="+selectedRow;
				ptmt=con.prepareStatement(query);
				ptmt.setString(1, name);
				ptmt.setString(2, dep);
				ptmt.setString(3, sem);
				ptmt.setString(4, ph);
				int x=ptmt.executeUpdate();
				if(x==1)
				{
					JOptionPane.showMessageDialog(this, "Data Updated Successfully...");
				}
				
				stmt=con.createStatement();
				rs=stmt.executeQuery("select * from student order by Sid asc");
				dtm.setRowCount(0);
		    	while(rs.next())
		    	{
		    		Vector v=new Vector();
		    		for(int i=1;i<=100;i++)
		    		{
		    			v.add(rs.getInt("Sid"));
		    			v.add(rs.getString("Sname"));
		    			v.add(rs.getString("Sdep"));
		    			v.add(rs.getString("Ssem"));
		    			v.add(rs.getString("Sphone"));
		   			}
		    		dtm.addRow(v);
		    	}
				
			}
			catch(Exception e)
			{
				
			}
			
	
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
		
	}


		
	