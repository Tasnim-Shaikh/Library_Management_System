import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
public class login_Page extends JFrame implements MouseListener,ActionListener{

	JLabel img;
	JPasswordField t2;
	JButton b2,b3,jb;
	int c=1;
	TextField t1;
	String s,str;
	Statement stmt;
	ResultSet rs;
	DefaultTableModel dtm;
	Connection con;
	
	login_Page()
	
	{
		Container c=getContentPane();
		c.setLayout(null);
		JPanel p1=new JPanel();
		p1.setBackground(new Color(110,4,48));
		p1.setBounds(0,0,220,500);
		c.add(p1);
		p1.setLayout(null);
		JPanel p3=new JPanel();
		JLabel l=new JLabel("Unlocking the ");
		JLabel ll=new JLabel("World of Words...");
		l.setForeground(Color.WHITE);
		ll.setForeground(Color.WHITE);
		l.setFont(new Font("Times New Roman",Font.BOLD,25));
		ll.setFont(new Font("Times New Roman",Font.BOLD,25));
		l.setBounds(10,200,180,30);
		ll.setBounds(30,230,190,30);
		p1.add(l);
		p1.add(ll);
		p3.setBounds(200,0,500,100);
		p3.setBackground(Color.white);
		p3.setLayout(null);
		c.add(p3);
		JPanel p2=new JPanel();
		p2.setBackground(Color.WHITE);
		p2.setBounds(200,0,500,500);
		c.add(p2);
		
		JLabel l1=new JLabel("Saraswati Sanchay",JLabel.CENTER);
		l1.setForeground(new Color(110,4,48));
		Font f=new Font("Times New Roman",Font.BOLD,30);
		l1.setBounds(35,50,400,50);
		l1.setFont(f);
		p3.add(l1);
		p2.setLayout(new GridBagLayout());
		GridBagConstraints gb=new GridBagConstraints();		
		gb.gridx=0;
		gb.gridy=0;
		gb.insets=new Insets(5,5,5,5);
		gb.ipady=5;
		JLabel l2=new JLabel("Username : ");
		l2.setForeground(new Color(110,4,48));
		l2.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l2,gb);
		gb.gridx=1;
		gb.gridy=0;
		gb.gridwidth=2;
		gb.anchor=GridBagConstraints.WEST;
		 t1=new TextField(17);
		 t1.setFont(new Font("Times New Roman",Font.BOLD,15));
		p2.add(t1,gb);
		gb.gridx=0;
		gb.gridy=2;
		gb.gridwidth=1;
		gb.anchor=GridBagConstraints.CENTER;
		JLabel l3=new JLabel("Password : ");
		l3.setForeground(new Color(110,4,48));
		l3.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l3,gb);
		gb.gridx=1;
		gb.gridy=2;
		gb.gridwidth=2;
		gb.anchor=GridBagConstraints.WEST;
		 t2=new JPasswordField(13);
		 t2.setEchoChar('*');
		 t2.setFont(new Font("Times New Roman",Font.BOLD,15));
		t2.setEchoChar('*');
		p2.add(t2,gb);
		gb.gridx=2;
		gb.gridy=2;
		gb.insets=new Insets(5,50,5,20);
		gb.anchor=GridBagConstraints.WEST;
		
		ImageIcon im=new ImageIcon("images/eye.jpg");
		 img=new JLabel(im);
		p2.add(img,gb);
		gb.gridx=0;
		gb.gridy=3;
		gb.insets=new Insets(20,60,5,20);
		gb.ipadx=15;
		gb.ipady=15;
		gb.gridwidth=1;
		 jb=new JButton("Forgot Password");
		jb.setBackground(new Color(110,4,48));
		jb.setForeground(Color.WHITE);
		jb.setFont(new Font("Times New Roman",Font.BOLD,15));
		p2.add(jb,gb);
		gb.gridx=1;
		gb.gridy=3;
		gb.insets=new Insets(20,5,5,20);
		
		gb.anchor=GridBagConstraints.WEST;
		 b2=new JButton("Login");
		b2.setFont(new Font("Times New Roman",Font.BOLD,20));
		b2.setBackground(new Color(110,4,48));
		b2.setForeground(Color.WHITE);
		p2.add(b2,gb);
		gb.gridx=2;
		gb.gridy=3;
		 b3=new JButton("Admin");
		b3.setBackground(new Color(110,4,48));
		b3.setForeground(Color.WHITE);
		b3.setFont(new Font("Times New Roman",Font.BOLD,15));
		p2.add(b3,gb);
		
		img.addMouseListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		jb.addActionListener(this);
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		login_Page gd=new login_Page();
		gd.setVisible(true);
		gd.setTitle("De");
		gd.setSize(700,500);

	
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		if(c==1)
		{
			t2.setEchoChar('\0');
			c=0;
		}
		else
		{
			t2.setEchoChar('*');
			c=1;
		}
		
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
		// TODO Auto-generated method stub
		if(arg0.getSource()==b2)
		{
			if(t1.getText().length()>0 && t2.getText().length()>0)
			{
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:7979/Library","root","anu02");
				 
					stmt=con.createStatement();
			    	rs=stmt.executeQuery("select * from Librarian order by Lid asc");
			    	String uname=t1.getText();
			    	String pass=t2.getText();
			    	String un,ps;
			    	int flag1=0;
			    	int flag2=0;
			    	while(rs.next())
			    	{
			    		un=rs.getString("name");
			    		ps=rs.getString("pass");
			    		if(un.equals(uname)&&ps.equals(pass))
			    		{
			    			flag1=1;
			    			break;
			    		}
			    		
			    	}
			    	if(flag1==1)
			    	{
			    		this.setVisible(false);
			    		LMS_Menu lm=new LMS_Menu();
			    		lm.setSize(700,500);
			    		lm.setVisible(true);
			    	}
			    	else
			    	{
			    		JOptionPane.showMessageDialog(this, "Invalid UserName or password");
			    	}
			    	
				}
				catch(Exception e)
				{
					
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "TextField should not be blank");
			}
			
		}
		else if(arg0.getSource()==b3)
		{
			this.setVisible(false);
			Admin a=new Admin();
			a.setSize(700,500);
			a.setVisible(true);
		}
		else if(arg0.getSource()==jb)
		{
			this.setVisible(false);
			forgot f=new forgot();
			f.setSize(700,550);
			f.setVisible(true);
			
		}
		}
	}
