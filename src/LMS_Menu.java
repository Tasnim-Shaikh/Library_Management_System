import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LMS_Menu extends JFrame implements MouseListener,ActionListener{

	JLabel l4;
	JButton b1,b3,b4,b5,b6;
	LMS_Menu()
	{
		Container c=getContentPane();
		setLayout(null);
		JPanel p1=new JPanel();
		p1.setBounds(0,0,700,100);
		p1.setBackground(new Color(110,4,48));
		JPanel p2=new JPanel();
		p2.setBounds(0,100,700,330);
		p2.setBackground(Color.WHITE);
		JPanel p3=new JPanel();
		p3.setBounds(0,430,700,50);
		p3.setBackground(new Color(110,4,48));
		c.add(p1);
		c.add(p2);
		c.add(p3);
		p1.setLayout(null);
		JLabel l1=new JLabel("Saraswati Sanchay",JLabel.CENTER);
		l1.setForeground(Color.WHITE);
		Font f=new Font("Times New Roman",Font.BOLD,30);
		l1.setBounds(150,25,400,50);
		l1.setFont(f);
		p1.add(l1);
		p2.setLayout(null);
		 b1=new JButton("Books");
		b1.setBounds(170,100,150,50);
		b1.setBackground(new Color(110,4,48));
		b1.setForeground(Color.WHITE);
		b1.setFont(new Font("Times New Roman",Font.BOLD,20));
		p2.add(b1);
		
//		 b2=new JButton("Librarian");
//		b2.setBounds(280,100,130,50);
//		b2.setBackground(Color.orange);
//		b2.setFont(new Font("Times New Roman",Font.BOLD,20));
//		p2.add(b2);
		 b3=new JButton("Student");
		b3.setBounds(390,100,150,50);
		b3.setBackground(new Color(110,4,48));
		b3.setForeground(Color.WHITE);
		b3.setFont(new Font("Times New Roman",Font.BOLD,20));
		p2.add(b3);
		 b5=new JButton("Issue Book");
		b5.setBounds(170,200,150,50);
		b5.setBackground(new Color(110,4,48));
		b5.setForeground(Color.WHITE);
		b5.setFont(new Font("Times New Roman",Font.BOLD,20));
		p2.add(b5);
		 b4=new JButton("Return Book");
		b4.setBounds(390,200,150,50);
		b4.setBackground(new Color(110,4,48));
		b4.setForeground(Color.WHITE);
		b4.setFont(new Font("Times New Roman",Font.BOLD,20));
		p2.add(b4);
		
		 b6=new JButton("Report");
			b6.setBounds(280,270,150,50);
			b6.setBackground(new Color(110,4,48));
			b6.setForeground(Color.WHITE);
			b6.setFont(new Font("Times New Roman",Font.BOLD,20));
			p2.add(b6);
		ImageIcon im=new ImageIcon("images/arr.png");
		 l4=new JLabel(im);
		l4.setBounds(15,0,50,50);
		p2.add(l4);
		l4.addMouseListener(this);
		b1.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LMS_Menu l1=new LMS_Menu();
		l1.setVisible(true);
		l1.setTitle("LMS_MENUS");
		l1.setSize(700,500);
		l1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.setVisible(false);
		login_Page lp=new login_Page();
		lp.setSize(700,500);
		lp.setVisible(true);
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==b1)
		{
			this.setVisible(false);
			Register_Books lp=new Register_Books();
			lp.setSize(1200,850);
			lp.setVisible(true);
		}
//		else if(e.getSource()==b2)
//		{
//			this.setVisible(false);
//			Register_Librarian lp=new Register_Librarian();
//			lp.setSize(1200,850);
//			lp.setVisible(true);
//		}
		else if(e.getSource()==b3)
		{
			this.setVisible(false);
			Register_Students lp=new Register_Students();
			lp.setSize(1200,850);
			lp.setVisible(true);
		}
		else if(e.getSource()==b5)
		{
			this.setVisible(false);
			Issue_Book lp=new Issue_Book();
			lp.setSize(1200,850);
			lp.setVisible(true);
		}
		else if(e.getSource()==b4)
		{
			this.setVisible(false);
			Return_Book lp=new Return_Book();
			lp.setSize(1200,850);
			lp.setVisible(true);
		}
		else if(e.getSource()==b6)
		{
			this.setVisible(false);
			Report rp=new Report();
			rp.setSize(1200,800);
			rp.setVisible(true);
		}
	}

}
