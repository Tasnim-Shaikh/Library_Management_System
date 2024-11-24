import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.util.*;
//create class
	
	class DatePicker 
	{
		//define variables
	        int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	        int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
	        //create object of JLabel with alignment
	        JLabel l = new JLabel("", JLabel.CENTER);
	        //define variable
	        String day = "";
	        //declaration
	        JDialog d;
	        //create object of JButton
	        JButton[] button = new JButton[49];
	  
	        public DatePicker(JFrame parent)//create constructor 
	        {
	        	//create object
	                d = new JDialog();
	                //set modal true
	                d.setModal(true);
	                //define string
	                String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
	                //create JPanel object and set layout
	                JPanel p1 = new JPanel(new GridLayout(7, 7));
	                //set size
	                p1.setPreferredSize(new Dimension(430, 120));
	                //for loop condition
	                for (int x = 0; x < button.length; x++) 
	                {		
	                	//define variable
	                        final int selection = x;
	                        //create object of JButton
	                        button[x] = new JButton();
	                        //set focus painted false
	                        button[x].setFocusPainted(false);
	                        //set background colour
	                        button[x].setBackground(Color.white);
	                        //if loop condition
	                        if (x > 6)
	                        //add action listener
	                        button[x].addActionListener(new ActionListener() 
	                        {
	                                 public void actionPerformed(ActionEvent ae) 
	                                 {
	                                       day = button[selection].getActionCommand();
	                                       //call dispose() method
	                                       d.dispose();
	                                 }
	                        });
	                        if (x < 7)//if loop condition 
	                        {
	                                button[x].setText(header[x]);
	                                //set fore ground colour
	                                button[x].setForeground(Color.red);
	                        }
	                        p1.add(button[x]);//add button
	                }
	                //create JPanel object with grid layout
	                JPanel p2 = new JPanel(new GridLayout(1, 3));
	                
	                //create object of button for previous month
	                JButton previous = new JButton("<< Previous");
	                //add action command
	                previous.addActionListener(new ActionListener() 
	                {
	                        public void actionPerformed(ActionEvent ae) 
	                        {
	                            //decrement month by 1
	                            month--;
	                            //call method
	                            displayDate();
	                        }
	                });
	                p2.add(previous);//add button
	                p2.add(l);//add label
	                //create object of button for next month
	                JButton next = new JButton("Next >>");
	                //add action command
	                next.addActionListener(new ActionListener()
	                {
	                        public void actionPerformed(ActionEvent ae) 
	                        {
	                             //increment month by 1
	                             month++;
	                             //call method
	                            displayDate();
	                        }
	                });
	                p2.add(next);// add next button
	                //set border alignment
	                d.add(p1, BorderLayout.CENTER);
	                d.add(p2, BorderLayout.SOUTH);
	                d.pack();
	                //set location
	                d.setLocationRelativeTo(parent);
	                //call method
	                displayDate();
	                //set visible true
	                d.setVisible(true);
	        }

	        public void displayDate() 
	        {
	        	for (int x = 7; x < button.length; x++)//for loop
	        	button[x].setText("");//set text
	      	        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");	
	                //create object of SimpleDateFormat 
	                java.util.Calendar cal = java.util.Calendar.getInstance();			
	                //create object of java.util.Calendar 
	        	cal.set(year, month, 1); //set year, month and date
	         	//define variables
	        	int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
	        	int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
	        	//condition
	        	for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
	        	//set text
	        	button[x].setText("" + day);
	        	l.setText(sdf.format(cal.getTime()));
	        	//set title
	        	d.setTitle("Date Picker");
	        }

	        public String setPickedDate() 
	        {
	        	//if condition
	        	if (day.equals(""))
	        		return day;
	            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
	            java.util.Calendar cal = java.util.Calendar.getInstance();
	            cal.set(year, month, Integer.parseInt(day));
	            return sdf.format(cal.getTime());
	        }
	}

public class Issue_Book extends JFrame implements ActionListener,MouseListener{

	JTextField t3;
	JTextField t1,t2,t5;
	JButton b1,b2;
	PreparedStatement ptmt;
	Statement stmt;
	ResultSet rs;
	DefaultTableModel dtm,dtm1;
	Connection con;
	int selectedRow;
    JTable jt,jt1;
    String name,author,cover;
	int quantity,price;
	JButton b;
	int i;
	int bid;
	Issue_Book()
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
		JLabel l2=new JLabel("Issue Books",JLabel.CENTER);
		l2.setForeground(Color.WHITE);
		Font f1=new Font("Times New Roman",Font.BOLD,30);
		l2.setBounds(400,50,400,50);
		l2.setFont(f1);
		p1.add(l2);
		JLabel l3=new JLabel("Issue Books To Students");
		l3.setBounds(450,20,300,30);
		l3.setForeground(new Color(110,4,48));
		l3.setFont(new Font("Times New Roman",Font.BOLD,27));
		p2.add(l3);
		
		JLabel l4=new JLabel("Book");
		l4.setForeground(new Color(110,4,48));
		l4.setBounds(30,100,100,30);
		l4.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l4);
		
		JLabel l9=new JLabel("Qty");
		l9.setForeground(new Color(110,4,48));
		l9.setBounds(320,100,100,30);
		l9.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l9);
		
		 t5=new JTextField();
			t5.setBounds(400,100,170,30);
			t5.setFont(new Font("Aerial black",Font.BOLD,17));
			p2.add(t5);
		
		 t1=new JTextField(20);
		t1.setBounds(140,100,170,30);
		t1.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(t1);
		t1.setEditable(false);
		
		JLabel l5=new JLabel("Stud ID");
		l5.setForeground(new Color(110,4,48));
		l5.setBounds(30,150,100,30);
		l5.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l5);
		
		 t2=new JTextField();
		t2.setBounds(140,150,170,30);
		t2.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(t2);
		
		JLabel l6=new JLabel("Date");
		l6.setForeground(new Color(110,4,48));
		l6.setBounds(320,150,100,30);
		l6.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l6);
		
		 t3=new JTextField(20);
		t3.setBounds(400,150,170,30);
		t3.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(t3);
		
		 b=new JButton("D");
		b.setBounds(550,150,40,30);
		b.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(b);
		b.setBackground(new Color(110,4,48));
		b.setForeground(Color.WHITE);
		b.addActionListener(this);
	
		 b1=new JButton("Borrow");
		b1.setBounds(140,200,100,30);
		b1.setForeground(Color.WHITE);
		b1.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(b1);
		b1.setBackground(new Color(110,4,48));
		
		 b2=new JButton("Reset");
		 b2.setForeground(Color.WHITE);
		b2.setBounds(280,200,100,30);
		b2.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(b2);
		b2.setBackground(new Color(110,4,48));
		
		JLabel l7=new JLabel("Book List");
		l7.setBounds(250,250,150,30);
		l7.setForeground(new Color(110,4,48));
		l7.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l7);
		
	
		JLabel l8=new JLabel("Issued List");
		l8.setForeground(new Color(110,4,48));
		l8.setBounds(810,150,150,30);
		l8.setFont(new Font("Times New Roman",Font.BOLD,20));
		p2.add(l8);
		
		
		
		ImageIcon im=new ImageIcon("images/arr.png");
		JLabel l10=new JLabel(im);
		l10.setBounds(5,0,50,50);
		p2.add(l10);
		l10.addMouseListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:7979/Library","root","anu02");
		    stmt=con.createStatement();
	    	rs=stmt.executeQuery("select * from Book order by Bid asc");
		
		
			String cols[]= {"Bid","Bname","Author","Price","Qty","Cover"};
			String rows[][]= {{}};
			dtm=new DefaultTableModel(rows,cols);
			 jt=new JTable(dtm);
			JScrollPane jsp=new JScrollPane(jt,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			jsp.setBounds(30,300,540,360);
			p2.add(jsp);
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
		try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:7979/Library","root","anu02");
			    stmt=con.createStatement();
		    	rs=stmt.executeQuery("select * from Rentol order by Rid asc");
			
			String cols1[]= {"INum","Bid","Sid","BookIssued","IssuedDate"};
			String rows1[][]= {{"","","","",""}};
			dtm1=new DefaultTableModel(rows1,cols1);
			 jt1=new JTable(dtm1);
			JScrollPane jsp1=new JScrollPane(jt1,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			jsp1.setBounds(600,200,540,450);
			p2.add(jsp1);
			while(rs.next())
	    	{
	    		Vector v=new Vector();
	    		for(int i=1;i<=100;i++)
	    		{
	    			v.add(rs.getInt("Rid"));
	    			v.add(rs.getInt("Bid"));
	    			v.add(rs.getInt("Sid"));
	    			v.add(rs.getInt("Bissued"));
	    			v.add(rs.getString("Idate"));	
	   			}
	    		dtm1.addRow(v);
	    	}
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	
		
	
	public static void main(String args[])
	{
		Issue_Book i=new Issue_Book();
		i.setVisible(true);
		i.setTitle("Issue_Book");
		i.setSize(1200,850);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
			//set text i.e. date
		if(arg0.getSource()==b)
		{
			t3.setText(new DatePicker(this).setPickedDate());
		}
		
		if(arg0.getSource()==b1)
		{
			if(t1.getText().length()>0 && t2.getText().length()>0 && t3.getText().length()>0 && t5.getText().length()>0)
			{
			i++;
			// TODO Auto-generated method stub
			String name=t1.getText();
			int qty=Integer.parseInt(t5.getText());
			int sid=Integer.parseInt(t2.getText());
			String date=t3.getText();
			
			int Qt=0;
				try {
				stmt=con.createStatement();
				rs=stmt.executeQuery("select Quantity,Bid from Book where Bid="+selectedRow);
				while(rs.next())
				{
					Qt=rs.getInt(1);
					bid=rs.getInt(2);
				}
			}
			catch(Exception e)
			{
				
			}
			
				if(qty<=Qt && qty!=0 )
				{int x=0;
					try 
					{
						stmt=con.createStatement();
						rs=stmt.executeQuery("select max(Rid) from Rentol");
						while(rs.next())
						{
							 i=rs.getInt(1);
							 i=i+1;
						}
						
						String str[]=date.split("-");
						String str_rev="";
						for(int i=str.length-1;i>=0;i--)
						{
							if(i>=1) {
							str_rev=str_rev.concat(str[i]+"-");}
							else
							{
								str_rev=str_rev.concat(str[i]);
							}
						}
						System.out.println(str_rev);
						
					ptmt=con.prepareStatement("insert into Rentol values(?,?,?,?,?)");			
						ptmt.setInt(1, i);
						ptmt.setInt(2,bid);
						ptmt.setInt(3, sid);
						ptmt.setInt(4, qty);
						ptmt.setString(5, str_rev);
						 x=ptmt.executeUpdate();	
						 stmt=con.createStatement();
						 rs=stmt.executeQuery("select * from Rentol");
						 Vector v=new Vector();
						 v.add(i);
						 v.add(bid);
						 v.add(sid);
						 v.add(qty);
						 v.add(str_rev);
						 dtm1.addRow(v);
						 
						
					}
					 catch (SQLException e1) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
					
					try
					{
						if(x==1) {
						int finalQty=Qt-qty;
						String query="update Book set Quantity=? where Bid="+selectedRow;
						ptmt=con.prepareStatement(query);
						ptmt.setInt(1, finalQty);
						int x1=ptmt.executeUpdate();
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
					if(x==1)
					{
						JOptionPane.showMessageDialog(b1,"Data Inserted Successfully...");
					}
					else
					{
						JOptionPane.showMessageDialog(b1,"Student Doesnt Exist`...");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Quantity should be less than or equal to available quantity\nor it should not 0");
				}
		
			}
			else
			{
				JOptionPane.showMessageDialog(this, "TextField Should not blank");
			}
			
		}
		else if(arg0.getSource()==b2)
		{
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t5.setText("");
		}
		

	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setVisible(false);
		LMS_Menu l=new LMS_Menu();
		l.setVisible(true);
		l.setSize(700,500);
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
	}
