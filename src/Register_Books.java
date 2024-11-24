import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
public class Register_Books extends JFrame implements MouseListener,ActionListener{

	JLabel l9;
	PreparedStatement ptmt;
	Statement stmt;
	ResultSet rs;
	DefaultTableModel dtm;
	Connection con;
	JTable jt;
	JButton b10;
	JTextField t1,t2,t3,t4;
	JButton b1,b2,b3,b4;
	int selectedRow;
	String name,author,cover;
	int quantity,price;
	String s="book1.jpg";
	FileDialog fd;
	int i=0;
	String nm,au;
	int flag=0;
	Register_Books()
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
		JLabel l2=new JLabel("Maintain Books",JLabel.CENTER);
		l2.setForeground(Color.WHITE);
		Font f1=new Font("Times New Roman",Font.BOLD,30);
		l2.setBounds(400,50,400,50);
		l2.setFont(f1);
		p1.add(l2);
		JLabel l3=new JLabel("Register Books");
		l3.setForeground(new Color(110,4,48));
		l3.setBounds(520,20,250,30);
		l3.setFont(new Font("Times New Roman",Font.BOLD,27));
		p2.add(l3);
		
		JLabel l4=new JLabel("Book Name");
		l4.setForeground(new Color(110,4,48));
		l4.setBounds(100,100,150,30);
		l4.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l4);
		
		JLabel l5=new JLabel("Author Name");
		l5.setForeground(new Color(110,4,48));
		l5.setBounds(320,100,150,30);
		l5.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l5);
		
		 t1=new JTextField(20);
		t1.setBounds(100,140,170,30);
		t1.setFont(new Font("Aerial black",Font.PLAIN,17));
		p2.add(t1);
		
		 t2=new JTextField(20);
		t2.setBounds(320,140,170,30);
		t2.setFont(new Font("Aerial black",Font.PLAIN,17));
		p2.add(t2);
		
		JLabel l7=new JLabel("Price");
		l7.setForeground(new Color(110,4,48));
		l7.setBounds(100,190,150,30);
		l7.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l7);
		
		JLabel l6=new JLabel("Quantity");
		l6.setForeground(new Color(110,4,48));
		l6.setBounds(320,190,150,30);
		l6.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l6);
		
		 t3=new JTextField(20);
		t3.setBounds(100,230,170,30);
		t3.setFont(new Font("Aerial black",Font.PLAIN,17));
		p2.add(t3);
		
		 t4=new JTextField(20);
		t4.setBounds(320,230,170,30);
		t4.setFont(new Font("Aerial black",Font.PLAIN,17));
		p2.add(t4);
		
		JLabel l8=new JLabel("Cover");
		l8.setForeground(new Color(110,4,48));
		l8.setBounds(670,100,150,30);
		l8.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l8);
		
		
		 b1=new JButton("Save");
		b1.setBounds(920,130,100,30);
		b1.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(b1);
		b1.setBackground(new Color(110,4,48));
		b1.setForeground(Color.WHITE);
		
		 b2=new JButton("Edit");
		b2.setBounds(920,180,100,30);
		b2.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(b2);
		b2.setBackground(new Color(110,4,48));
		b2.setForeground(Color.WHITE);
		
		 b3=new JButton("Delete");
		b3.setBounds(920,230,100,30);
		b3.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(b3);
		b3.setBackground(new Color(110,4,48));
		b3.setForeground(Color.WHITE);
		
		 b4=new JButton("Browse");
		b4.setBounds(670,300,100,30);
		b4.setFont(new Font("Times New Roman",Font.BOLD,20));
		p2.add(b4);
		b4.setBackground(new Color(110,4,48));
		b4.setForeground(Color.WHITE);
		
		ImageIcon im=new ImageIcon("images/arr.png");
		JLabel l10=new JLabel(im);
		l10.setBounds(5,0,50,50);
		p2.add(l10);
				
		ImageIcon im1=new ImageIcon("images/book1.jpg");
		 l9=new JLabel(im1);
		l9.setBounds(640,140,150,140);
		p2.add(l9);
		

		
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		l10.addMouseListener(this);
	
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:7979/Library","root","anu02");
		    stmt=con.createStatement();
		    rs=stmt.executeQuery("select * from Book order by Bid asc");	
			String cols[]= {"Bid","BName","Author","Price","Quantity","Cover"};
			String rows[][]= {{"1","Clang","Dennis","8010934254","USA","abc.jpg"}};
			dtm=new DefaultTableModel(rows,cols);
			 jt=new JTable(dtm);
			JScrollPane jsp=new JScrollPane(jt,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			jsp.setBounds(120,350,950,300);
			p2.add(jsp);
			dtm.setRowCount(0);
	    	while(rs.next())
	    	{
	    		Vector v=new Vector();
	    		for(int i=1;i<=100;i++)
	    		{
	    			v.add(rs.getInt("Bid"));
	    			v.add(rs.getString("Bname"));
	    			v.add(rs.getString("Bauthor"));
	    			v.add(rs.getInt("Price"));
	    			v.add(rs.getInt("Quantity"));
	    			v.add(rs.getString("Cover"));
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
						    rs=stmt.executeQuery("select Bname,Bauthor,Price,Quantity,Cover from Book where Bid="+selectedRow);
							while(rs.next())
							{
								name=rs.getString("Bname");
								author=rs.getString("Bauthor");
								price=rs.getInt("Price");
								quantity=rs.getInt("Quantity");
								cover=rs.getString("Cover");
							}
							t1.setText(name);
							t2.setText(author);
							t3.setText(Integer.toString(price));
							t4.setText(Integer.toString(quantity));
							ImageIcon i=new ImageIcon("images/"+cover);
							l9.setIcon(i);
	
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
	public static void main(String[] args)throws Exception
	{
		Register_Books r=new Register_Books();
		r.setVisible(true);
		r.setTitle("Register_Books");
		r.setSize(1200,850);
		r.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b4)
		{
			fd=new FileDialog(this);
			fd.setVisible(true);
			s=fd.getFile();
		
			ImageIcon im2=new ImageIcon("images/"+s);
			l9.setIcon(im2);
		}
		
		
		if(e.getSource()==b1)
		{
			if(t1.getText().length()>0 && t2.getText().length()>0 && t3.getText().length()>0 && t4.getText().length()>0)
			{
				i++;
				int flag=0;
				// TODO Auto-generated method stub
				String name=t1.getText();
				String author=t2.getText();
				int price=Integer.parseInt(t3.getText());
				int quantity=Integer.parseInt(t4.getText());
				String cover=s;
				try 
				{
					stmt=con.createStatement();
					rs=stmt.executeQuery("select * from Book order by Bid asc");
					while(rs.next())
					{
						nm=rs.getString(2);
						au=rs.getString(3);
						if(nm.equals(name) && au.equals(author))
						{
							flag=1;
							break;
						}
					}
				
					if(flag==0 && price>10 && quantity!=0)
					{
						stmt=con.createStatement();
						rs=stmt.executeQuery("select max(Bid) from Book");
						while(rs.next())
						{
							 i=rs.getInt(1);
							i=i+1;
						}
					
						ptmt=con.prepareStatement("insert into Book values(?,?,?,?,?,?)");
						ptmt.setInt(1, i);
						ptmt.setString(2, name);
						ptmt.setString(3, author);
						ptmt.setInt(4, price);
						ptmt.setInt(5,quantity);
						ptmt.setString(6, cover);
						
						int x=ptmt.executeUpdate();	
						 stmt=con.createStatement();
						 rs=stmt.executeQuery("select * from Book");
						 Vector v=new Vector();
						 v.add(i);
						 v.add(name);
						 v.add(author);
						 v.add(price);
						 v.add(quantity);
						 v.add(cover);
						 dtm.addRow(v);
						JOptionPane.showMessageDialog(this, "Data inserted Successfully");
					}
					else if(flag==1)
					{
						JOptionPane.showMessageDialog(this, "Book is Duplicated");
					}
					else if(quantity==0)
					{
						JOptionPane.showMessageDialog(this, "Quantity should be greater than 0");
					}
					else if(price<=10)
					{
						JOptionPane.showMessageDialog(this, "Price should be greater than 10");
					}
					
				}
				 catch (SQLException e1) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
			}		
			else
			{
				JOptionPane.showMessageDialog(this, "TextField should not be blank");
			}
		}
		else if(e.getSource()==b2)
		{
			if(t1.getText().length()>0 && t2.getText().length()>0 && t3.getText().length()>0 && t4.getText().length()>0)
			{
				name=t1.getText();
				author=t2.getText();
				price=Integer.parseInt(t3.getText());
				quantity=Integer.parseInt(t4.getText());
				cover=s;
				try
				{
					String query="update Book set Bname=?,Bauthor=?,Price=?,Quantity=?,Cover=? where Bid="+selectedRow;
					ptmt=con.prepareStatement(query);
					ptmt.setString(1, name);
					ptmt.setString(2, author);
					ptmt.setInt(3, price);
					ptmt.setInt(4, quantity);
					ptmt.setString(5, cover);
					int x=ptmt.executeUpdate();
					if(x==1)
					{
						JOptionPane.showMessageDialog(this, "Data Updated Successfully...");
					}
					stmt=con.createStatement();
					rs=stmt.executeQuery("select * from Book order by Bid asc");
					dtm.setRowCount(0);
			    	while(rs.next())
			    	{
			    		Vector v=new Vector();
			    		for(int i=1;i<=100;i++)
			    		{
			    			v.add(rs.getInt("Bid"));
			    			v.add(rs.getString("Bname"));
			    			v.add(rs.getString("Bauthor"));
			    			v.add(rs.getInt("Price"));
			    			v.add(rs.getInt("Quantity"));
			    			v.add(rs.getString("Cover"));
			   			}
			    		dtm.addRow(v);
			    	}
					
				}
				catch(Exception e2)
				{
					
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "TextField Should not be blank");
			}
		}
		else if(e.getSource()==b3)
		{
			if(t1.getText().length()>0 && t2.getText().length()>0 && t3.getText().length()>0 && t4.getText().length()>0)
			{
				try
				{
					name=t1.getText();
					author=t2.getText();
					price=Integer.parseInt(t3.getText());
					quantity=Integer.parseInt(t4.getText());
					cover=s;
					stmt=con.createStatement();
					int x=stmt.executeUpdate("delete from Book where Bid="+selectedRow);
					stmt=con.createStatement();
			    	rs=stmt.executeQuery("select * from Book order by Bid asc");
			    	dtm.setRowCount(0);
			    	
					while(rs.next())
				    {Vector v=new Vector();
				   		
				   		for(int i=1;i<=100;i++)
				   		{
				   			v.add(rs.getInt("Bid"));
				   			v.add(rs.getString("Bname"));
				   			v.add(rs.getString("Bauthor"));
				   			v.add(rs.getInt("Price"));
				   			v.add(rs.getInt("Quantity"));
				   			v.add(rs.getString("Cover"));
				   		}
				    	dtm.addRow(v);
				    }	
				}
				catch(Exception e2)
				{}
				JOptionPane.showMessageDialog(this, "Data Deleted Successfully");
			}
			else
			{
				JOptionPane.showMessageDialog(this, "TextField should not be blank");
			}
		
	}
	}
}
		