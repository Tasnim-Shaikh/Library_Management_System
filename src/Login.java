import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Login extends JFrame {

	JProgressBar jb;
	JLabel load;
	Login()
	{
		Container c=getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(110,4,48));
		JLabel title=new JLabel("Saraswati Sanchay",JLabel.CENTER);
		title.setForeground(Color.white);
		Font f=new Font("Times New Roman",Font.BOLD,45);
		title.setFont(f);
		title.setBounds(10,27,700,50);
		title.setBackground(new Color(66,12,36));
		c.add(title);
		ImageIcon i=new ImageIcon("images/Library2.jpg");
		JLabel img=new JLabel(i);
		img.setBounds(20,50,640,400);
		c.add(img);
		 jb=new JProgressBar(0,100);
		jb.setValue(0);
		jb.setBounds(20,410,640,25);
		jb.setStringPainted(true);
		jb.setForeground(Color.green);
		c.add(jb);
		 load=new JLabel("Loading....");
		load.setBounds(590,380,80,30);
		c.add(load);
	}
	public void progress()
	{
		int i=0;
		while(i<=100)
		{
			
			try
			{
				jb.setValue(i);
				i=i+5;
				if(i==75)
				{
					load.setText("Launching....");
				}
				if(i==100)
				{
					this.setVisible(false);
					login_Page l=new login_Page();
					l.setVisible(true);
					l.setSize(700,500);
					
				}
				Thread.sleep(200);
			}
			catch(Exception e)
			{
				
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login l=new Login();
		l.setVisible(true);
		l.setSize(700,500);
		l.setTitle("Login");
		l.setBackground(new Color(110,4,48));
		l.progress();
	}

}
