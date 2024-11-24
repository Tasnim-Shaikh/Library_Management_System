import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.sql.CallableStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
class DatePicker1
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

        public DatePicker1(JFrame parent)//create constructor 
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

public class Return_Book extends JFrame implements ActionListener,MouseListener{

	JTextField t5;
	PreparedStatement ptmt;
	Statement stmt;
	ResultSet rs;
	DefaultTableModel dtm,dtm1;
	Connection con;
	int selectedRow;
	JTextField t1,t2,t3;
	int rid,sid,bi;
	String date1;
	JTable jt,jt1;
	JButton b1,b2,b;
	int i=0;
	String s,date;
	int bid;
	int Qty,FinalQty;
	JTextField l11;
	String bn;
	Return_Book()
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
		Font f=new Font("Times New Roman",Font.BOLD,30);
		l1.setForeground(Color.white);
		l1.setBounds(400,10,400,50);
		l1.setFont(f);
		p1.add(l1);
		JLabel l2=new JLabel("Return Books",JLabel.CENTER);
		l2.setForeground(Color.white);
		Font f1=new Font("Times New Roman",Font.BOLD,30);
		l2.setBounds(400,50,400,50);
		l2.setFont(f1);
		p1.add(l2);
		JLabel l3=new JLabel("Return Books from Students");
		l3.setForeground(Color.white);
		l3.setBounds(450,20,350,30);
		l3.setFont(new Font("Times New Roman",Font.BOLD,27));
		p2.add(l3);
		
		JLabel l7=new JLabel("Rented Books");
	
		l7.setBounds(250,250,150,30);
		l7.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l7);
		
		
		
		JLabel l8=new JLabel("Returned Books");
		
		l8.setBounds(810,150,150,30);
		l8.setFont(new Font("Times New Roman",Font.BOLD,20));
		p2.add(l8);
		
	
		
		ImageIcon im=new ImageIcon("images/arr.png");
		JLabel l10=new JLabel(im);
		l10.setBounds(5,0,50,50);
		p2.add(l10);
		
		JLabel l5=new JLabel("Stud ID");
		l5.setBounds(30,150,100,30);
		l5.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l5);
		
		t2=new JTextField();
		t2.setBounds(140,150,170,30);
		t2.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(t2);
		
		JLabel l6=new JLabel("Rent");
		l6.setBounds(320,150,150,30);
		l6.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l6);
		t2.setEditable(false);
		t3=new JTextField(20);
		t3.setBounds(470,150,170,30);
		t3.setFont(new Font("Aerial black",Font.BOLD,17));
		t3.setEditable(false);
		p2.add(t3);
		b1=new JButton("Return");
		b1.setForeground(Color.white);
		b1.setBounds(140,200,100,30);
		b1.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(b1);
		b1.setBackground(new Color(110,4,48));
		
		b2=new JButton("Reset");
		b2.setForeground(Color.white);
		b2.setBounds(340,200,100,30);
		b2.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(b2);
		b2.setBackground(new Color(110,4,48));
		
		JLabel l4=new JLabel("Book ID");
	
		l4.setBounds(30,100,100,30);
		l4.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l4);
		
		t1=new JTextField(20);
	t1.setBounds(140,100,170,30);
		t1.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(t1);
		t1.setEditable(false);
		JLabel l9=new JLabel("Issue Date");
	
		l9.setBounds(320,100,150,30);
		l9.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l9);
		
		 t5=new JTextField(20);
		t5.setBounds(470,100,170,30);
		t5.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(t5);
		t5.setEditable(false);
		b=new JButton("D");
		b.setForeground(Color.white);
	//	b.setBounds(640,100,30,30);
		b.setFont(new Font("Aerial black",Font.BOLD,17));
		p2.add(b);
		b.setBackground(new Color(110,4,48));
		b.addActionListener(this);
		 l11=new JTextField();
		l11.setForeground(new Color(110,4,48));
		l11.setBounds(840,100,160,30);
		
		l11.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l11);
		l11.setEditable(false);
		
		JButton b12=new JButton("Fine");
		b12.setBounds(670,100,150,30);
		b12.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(b12);
		b12.setBackground(new Color(110,4,48));
		b12.setForeground(Color.WHITE);
		
		
		l10.addMouseListener(this);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b12.addActionListener(this);
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:7979/Library","root","anu02");
			    stmt=con.createStatement();
		    	rs=stmt.executeQuery("select * from Rentol order by Rid asc");
			
				String cols[]= {"INum","Bid","Sid","BookIssued","IssuedDate"};
				String rows[][]= {{"","","","",""}};
				dtm1=new DefaultTableModel(rows,cols);
				 jt1=new JTable(dtm1);
				JScrollPane jsp=new JScrollPane(jt1,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				jsp.setBounds(30,300,540,360);
				p2.add(jsp);
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
			 	ListSelectionModel model=jt1.getSelectionModel();
				model.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e)
					{
						if(!model.isSelectionEmpty()) {
							selectedRow=jt1.getSelectedRow();
							String value=jt1.getModel().getValueAt(selectedRow, 0).toString();
							selectedRow=Integer.parseInt(value);
							
							try {
								stmt=con.createStatement();
							    rs=stmt.executeQuery("select Sid,Bid,Bissued,Idate from Rentol where Rid="+selectedRow);
								while(rs.next())
								{
									
									sid=rs.getInt("Sid");
									bid=rs.getInt("Bid");
									bi=rs.getInt("Bissued");
									date1=rs.getString("Idate");
									
								}
							
								t2.setText(""+sid);
								t3.setText(""+bi);
								t1.setText(""+bid);
								t5.setText(""+date1);
							}
							catch(Exception e1)
							{
								
							}
						}
					}
				});
			
			
		}
		catch(Exception e2)
		{
			
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:7979/Library","root","anu02");
		    stmt=con.createStatement();
	    	rs=stmt.executeQuery("select * from Return_b order by Reid asc");
	    	String cols[]= {"RetId","Sid","BookIssued","IssuedDate","ReturnDate"};
			String rows[][]= {{"","","","",""}};
			dtm=new DefaultTableModel(rows,cols);
			jt=new JTable(dtm);
			JScrollPane jsp=new JScrollPane(jt,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			jsp.setBounds(600,200,540,450);
			p2.add(jsp);
			while(rs.next())
	    	{
	    		Vector v=new Vector();
	    		for(int i=1;i<=100;i++)
	    		{
	    			v.add(rs.getInt("Reid"));
	    			v.add(rs.getInt("Sid"));
	    			v.add(rs.getInt("Bookret"));
	    			v.add(rs.getString("Isdate"));
	    			v.add(rs.getString("Retdate"));	
	   			}
	    		dtm.addRow(v);
	    	}
			
		}
		catch(Exception e)
		{
			
		}
	}
	public static void main(String args[])
	{
		Return_Book i=new Return_Book();
		i.setVisible(true);
		i.setTitle("Issue_Book");
		i.setSize(1200,850);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		// TODO Auto-generated method stub
		if(arg0.getSource()==b)
		{
			t5.setText(new DatePicker(this).setPickedDate());
		}
		else if(arg0.getSource()==b1)
		{
			
				
					
					 if(t1.getText().length()>0 && t2.getText().length()>0 && t3.getText().length()>0&&t5.getText().length()>0)
					 {
						 i++;
							int sid=Integer.parseInt(t2.getText());
							int rent=Integer.parseInt(t3.getText());
							String date1=t5.getText();
							String date="07-12-2005";
					try 
					{
						
							stmt=con.createStatement();
						    rs=stmt.executeQuery("select Idate,Bid from Rentol where Rid="+selectedRow);
							while(rs.next())
							{
								bid=rs.getInt("Bid");
								date=rs.getString("Idate");
							}
						
							stmt=con.createStatement();
							rs=stmt.executeQuery("select max(Reid) from Return_b");
							while(rs.next())
							{
								 i=rs.getInt(1);
								 i=i+1;
							}
							
							  LocalDate currentDate = LocalDate.now();
							
							String curr_str_dt=currentDate.toString();
							String sql = "SELECT calc_fine(?,?,?)";
						
							PreparedStatement ps = con.prepareStatement(sql);
							ps.setInt(1, sid);        // Set the sid parameter (int type)
							ps.setString(2,date1);
							ps.setString(3, curr_str_dt);// Set the str_rev parameter (date as a string)
						
							ResultSet rs = ps.executeQuery();  // Execute the query
					int fine=0;
							if (rs.next()) {
							     fine = rs.getInt(1);  // The first column in the result set is the fine returned by the function
							    System.out.println("Fine: " + fine);
							}
							l11.setText(Integer.toString(fine));
							rs.close();  // Close the result set
							ps.close();  // Close the prepared statement
							System.out.println("hi");
							ptmt=con.prepareStatement("insert into Return_b values(?,?,?,?,?)");
						
							ptmt.setInt(1, i);
							ptmt.setInt(2,sid);
							ptmt.setInt(3, rent);
							ptmt.setString(4, date1);
							ptmt.setString(5, curr_str_dt);
							
							int x=ptmt.executeUpdate();	
						
							 stmt=con.createStatement();
							 if(x>=1)
							 {
								 System.out.println("Inserted");
							 }
							 else
							 {
								 System.out.println("Failed");
							 }
							 rs=stmt.executeQuery("select * from Return_b");
							 Vector v=new Vector();
							 v.add(i);
							 v.add(sid);
							 v.add(rent);
							 v.add(date1);
							 v.add(curr_str_dt);
							 dtm.addRow(v);
							
							 stmt=con.createStatement();
							
							 rs=stmt.executeQuery("select Quantity from Book where Bid="+bid);
							 while(rs.next())
							 {
								 Qty=rs.getInt("Quantity");
							 }
							 	FinalQty=Qty+rent;
								String query="update Book set Quantity=? where Bid="+bid;
								ptmt=con.prepareStatement(query);
								ptmt.setInt(1, FinalQty);
								int x1=ptmt.executeUpdate();
								
								stmt=con.createStatement();
								int x2=stmt.executeUpdate("delete from Rentol where Rid="+selectedRow);
								
								
								stmt=con.createStatement();
						    	rs=stmt.executeQuery("select * from Rentol order by Rid asc");
						    	dtm1.setRowCount(0);
								while(rs.next())
							    {
									Vector v1=new Vector();
							   		
							   		for(int i=1;i<=100;i++)
							   		{
							   			v1.add(rs.getInt("Rid"));
						    			v1.add(rs.getInt("Bid"));
						    			v1.add(rs.getInt("Sid"));
						    			v1.add(rs.getInt("Bissued"));
						    			v1.add(rs.getString("Idate"));
						    			
							   		}
							   		dtm1.addRow(v1);
							    	
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
}