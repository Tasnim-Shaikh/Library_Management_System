import java.sql.Connection;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class Report extends JFrame implements MouseListener {
	JLabel l4;
	Report()
	{
		Container c=getContentPane();
		
		JPanel p1=new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(110,4,48));
		p1.setBounds(0,0,1200,100);
		c.add(p1);
		
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
		JPanel p2=new JPanel();
		p2.setLayout(null);
		p2.setBounds(0,100,1200,670);
		p2.setBackground(Color.WHITE);
		c.add(p2);
		
		JLabel l11=new JLabel("Record of Books Purchased by Each Student");
		l11.setBounds(300,200,600,70);
		l11.setFont(f);
		p2.add(l11);
		
		JLabel l2=new JLabel("Report of Student",JLabel.CENTER);
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
		
		ImageIcon im=new ImageIcon("images/arr.png");
		l4=new JLabel(im);
		l4.setBounds(0,100,50,50);
		p2.add(l4);
	
		l4.addMouseListener(this);
		try {
			
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:7979/Library","root","anu02");
		    Statement stmt=con.createStatement();
	    	ResultSet rs=stmt.executeQuery("select Sid,Sname,count(Bid) as cc from v1 group by Sid;");
			
			String cols[]= {"Sid","Sname","issue_count"};
			String rows[][]= {{"","",""}};
			DefaultTableModel dtm=new DefaultTableModel(rows,cols);
			JTable jt=new JTable(dtm);
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
	    			v.add(rs.getString("cc"));
	   			}
	    		dtm.addRow(v);
	    	}
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
}
	public static void main(String[] args)
	{
		Report r1=new Report();
		r1.setTitle("Report");
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
