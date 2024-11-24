import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.awt.*;
public class forgot extends JFrame implements ActionListener,MouseListener{

	JTextField t1,t3;
	JPasswordField t2;
	JButton b1,b2;
	JLabel l6;
	Statement stmt;
	ResultSet rs;
	DefaultTableModel dtm;
	Connection con;
	PreparedStatement ptmt;
	int lid;
	int c=1;
	JLabel img;
	JLabel l10;
	forgot()
	{
		setLayout(null);
		Container c=getContentPane();
		JPanel p1=new JPanel();
		p1.setBounds(0,0,700,100);
		p1.setBackground(new Color(110,4,48));
		p1.setLayout(null);
		JLabel l1=new JLabel("Saraswati Sanchay",JLabel.CENTER);
		l1.setForeground(Color.WHITE);
		Font f=new Font("Times New Roman",Font.BOLD,30);
		l1.setBounds(160,25,400,40);
		l1.setFont(f);
		p1.add(l1);
		c.add(p1);
		JPanel p2=new JPanel();
		p2.setBackground(Color.WHITE);
		p2.setBounds(0,0,700,500);
		p2.setLayout(null);
		c.add(p2);
		JLabel l2=new JLabel("Forgot your Password");
		l2.setForeground(new Color(110,4,48));
		l2.setBounds(240,150,250,30);
		l2.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l2);
		JLabel l3=new JLabel("Username");
		l3.setForeground(new Color(110,4,48));
		l3.setBounds(130,210,150,30);
		l3.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l3);
	
		JLabel l4=new JLabel("Password");
		l4.setForeground(new Color(110,4,48));
		l4.setBounds(130,270,150,30);
		l4.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l4);
		
		JLabel l5=new JLabel("Capcha");
		l5.setForeground(new Color(110,4,48));
		l5.setBounds(130,330,150,30);
		l5.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l5);
		
		 t1=new JTextField(20);
		t1.setFont(new Font("Times New Roman",Font.BOLD,23));	
		t1.setBounds(330,210,170,30);
		p2.add(t1);
		
		 t2=new JPasswordField(20);
		t2.setFont(new Font("Times New Roman",Font.BOLD,23));	
		t2.setBounds(330,270,170,30);
		p2.add(t2);
		t2.setEchoChar('*');
		
		ImageIcon img1=new ImageIcon("images/eye.jpg");
		 img=new JLabel(img1);
		img.setBounds(500,270,30,30);
		p2.add(img);
		img.addMouseListener(this);
		
		 t3=new JTextField(20);
		t3.setFont(new Font("Times New Roman",Font.BOLD,23));	
		t3.setBounds(330,330,170,30);
		p2.add(t3);
		
		 l6=new JLabel("X214d");
		 l6.setForeground(new Color(110,4,48));
		l6.setBounds(530,330,150,30);
		l6.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l6);
		
		 b1=new JButton("Forgot Password");
		b1.setFont(new Font("Times New Roman",Font.BOLD,23));
		b1.setBackground(new Color(110,4,48));
		b1.setForeground(Color.WHITE);
		b1.setBounds(200,400,250,30);
		p2.add(b1);
		
		 b2=new JButton("Regenerate");
		b2.setFont(new Font("Times New Roman",Font.BOLD,23));
		b2.setBackground(new Color(110,4,48));
		b2.setForeground(Color.WHITE);
		b2.setBounds(520,380,150,50);
		p2.add(b2);
		b1.addActionListener(this);
		b2.addActionListener(this);
		ImageIcon im=new ImageIcon("images/arr.png");
		l10=new JLabel(im);
		l10.setBounds(0,100,50,50);
		p2.add(l10);
		l10.addMouseListener(this);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		forgot a=new forgot();
		a.setVisible(true);
		a.setSize(700,550);
		a.setTitle("ForGot Password");
		a.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			if(t1.getText().length()>0 && t2.getText().length()>0 && t3.getText().length()>0)
			{
				String uname=t1.getText();		
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:7979/Library","root","anu02");
				    stmt=con.createStatement();
			    	rs=stmt.executeQuery("select * from Librarian order by Lid asc");
			    	int flag=0;
			    	while(rs.next())
			    	{
			    		lid=rs.getInt("Lid");
			    		String un=rs.getString("name");
			    		if(un.equals(uname))
			    		{
			    			flag=1;
			    			break;
			    		}
			    	}
			    	String cp=l6.getText();
			    	if(flag==1 && cp.equals(t3.getText()))
			    	{
			    		String pass=t2.getText();
			    		String query="update Librarian set pass=? where lid="+lid;
						ptmt=con.prepareStatement(query);
						ptmt.setString(1, pass);
						int x=ptmt.executeUpdate();
						if(x==1)
						{
							JOptionPane.showMessageDialog(this, "Data Updated Successfully...");
						}
							
			    	}  
			    	else if(flag!=1)
			    	{
			    		JOptionPane.showMessageDialog(this, "You are not Librarian");
			    	}
			    	else if(!(cp.equals(t3.getText())))
			    	{
			    		JOptionPane.showMessageDialog(this, "Invalid Capcha");
			    	}
			}
				catch(Exception e)
				{
				
				}
			
		}
			else
			{
				JOptionPane.showMessageDialog(this, "Textfield should not be blank");
			}
		
	
		}
		else if(ae.getSource()==b2)
		{
			l6.setText(gc());
			
		}
	}	
	public static String gc()
	{
		String capchastr="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String capcha="";
		for(int i=0;i<5;i++)
		{
			int index=(int)(Math.random()*capchastr.length());
			capcha=capcha+capchastr.charAt(index);
		}
		return capcha;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==img)
		{
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
		else if(arg0.getSource()==l10)
		{
				this.setVisible(false);
				login_Page lp=new login_Page();
				lp.setSize(700,500);
				lp.setVisible(true);
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
}