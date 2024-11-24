import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Admin extends JFrame implements MouseListener,ActionListener {

	JPasswordField t1;
	int c=1;
	JLabel img,l4;
	Admin()
	{
		setLayout(null);
		Container c=getContentPane();
		JPanel p1=new JPanel();
		p1.setBounds(0,0,700,100);
		p1.setBackground(new Color(110,4,48));
		p1.setLayout(null);
		String str="Saraswati Sanchay";
		JLabel l1=new JLabel(str,JLabel.CENTER);
		l1.setForeground(Color.white);
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
		JLabel l2=new JLabel("Admin Login");
		l2.setForeground(new Color(110,4,48));
		l2.setBounds(260,150,150,30);
		l2.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l2);
		JLabel l3=new JLabel("Password");
		l3.setForeground(new Color(110,4,48));
		
		l3.setBounds(210,240,150,30);
		l3.setFont(new Font("Times New Roman",Font.BOLD,25));
		p2.add(l3);
		 t1=new JPasswordField(30);
		 t1.setEchoChar('*');
		 t1.setFont(new Font("Times New Roman",Font.BOLD,23));	
		t1.setBounds(330,240,170,30);
		p2.add(t1);
		
		
		ImageIcon img1=new ImageIcon("images/eye.jpg");
		 img=new JLabel(img1);
		img.setBounds(500,240,30,30);
		p2.add(img);
		img.addMouseListener(this);
		
		JButton b1=new JButton("Sign in");
		b1.setForeground(Color.white);
		b1.setFont(new Font("Times New Roman",Font.BOLD,23));
		b1.setBackground(new Color(110,4,48));
		b1.setBounds(280,300,130,30);
		p2.add(b1);
		ImageIcon im=new ImageIcon("images/arr.png");
		 l4=new JLabel(im);
		l4.setBounds(0,110,100,50);
		p2.add(l4);
		l4.addMouseListener(this);
		b1.addActionListener(this);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Admin a=new Admin();
		a.setVisible(true);
		a.setSize(700,500);
		a.setTitle("Admin Login");
		a.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==l4)
		{
			this.setVisible(false);
			login_Page lp=new login_Page();
			lp.setSize(700,500);
			lp.setVisible(true);	
		}
		else if(e.getSource()==img)
		{
			if(c==1)
			{
				t1.setEchoChar('\0');
				c=0;
			}
			else
			{
				t1.setEchoChar('*');
				c=1;
			}
		}
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
	public void actionPerformed(ActionEvent ae)
	{
		if(t1.getText().equals("Admin"))
		{
			this.setVisible(false);
			Register_Librarian l=new Register_Librarian();
			l.setSize(1200,850);
			l.setVisible(true);	
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Invalid Password\nyou are not admin");
		}
		
		
	}

}
