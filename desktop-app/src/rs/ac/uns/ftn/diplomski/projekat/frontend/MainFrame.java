package rs.ac.uns.ftn.diplomski.projekat.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import rs.ac.uns.ftn.diplomski.projekat.backend.Bayes;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 2593300780354379674L;
	
	private static MainFrame instance = null;
	static JTextField search = new JTextField();
	static JLabel gender=new JLabel("Gender");
	static JLabel pclass=new JLabel("Ticket class");
	static JLabel age=new JLabel("Age in years");
	static JLabel sibp=new JLabel("Of siblings / spouses aboard the Titanic");
	static JLabel parch=new JLabel("Of parents / children aboard the Titanic");
	static JLabel fare=new JLabel("Passenger fare");
	static JLabel embarked=new JLabel("Port of Embarkation");
	static JLabel resultJava=new JLabel("");
	static JLabel resultUnb=new JLabel("");
	static JLabel statistic=new JLabel("");
	static JRadioButton male = new JRadioButton("male");
	static JRadioButton female = new JRadioButton("female");
	static JRadioButton c1 = new JRadioButton("1");
	static JRadioButton c2 = new JRadioButton("2");
	static JRadioButton c3 = new JRadioButton("3");
	static JRadioButton emberkedS = new JRadioButton("Southampton");
	static JRadioButton emberkedC = new JRadioButton("Cherbourg");
	static JRadioButton emberkedQ = new JRadioButton("Queenstown");
	static TextField ageText=new TextField();
	static TextField sibpText=new TextField();
	static TextField parchText=new TextField();
	static TextField fareText=new TextField();
	private Bayes bayes;
	
	public static MainFrame getInstance() throws Exception {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	
	private MainFrame() throws Exception
	{
		
		bayes=new Bayes();
		statistic.setForeground(Color.GRAY);
		statistic.setText("Percentage of accuracy (Bayesian theorem / Bayesian network) on test set: "+bayes.BayesianTheoremStatistic()+"% / "+bayes.BayesianNetworkStatistic()+"%");
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth *1/3, screenHeight *3/5);
		setTitle("Titanic");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage((new ImageIcon("resources/attack.png")).getImage());
		setResizable(false);
		
		this.setLayout(new BorderLayout());
		JPanel statusPanel = new JPanel();

		
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setPreferredSize(new Dimension(this.getWidth(), 25));
		statusPanel.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
			
		Box box=Box.createVerticalBox();
		this.add(box,BorderLayout.CENTER);
		JLabel JLabel=new JLabel("Would you survive the sinking of the Titanic?");
		JLabel.setFont(new Font("Calibri", Font.BOLD, 21));
		panel.add(JLabel);
		box.add(panel);
		
		JPanel panelGender= new JPanel();
		panelGender.setAlignmentX(CENTER_ALIGNMENT);
		panelGender.setBackground(Color.getHSBColor(100, 100, 100));
		female.setBackground(Color.getHSBColor(100, 100, 100));
		male.setBackground(Color.getHSBColor(100, 100, 100));
		panelGender.add(gender);
		panelGender.add(female);
		panelGender.add(male);
		box.add(panelGender);

		JPanel panelAge= new JPanel();
		panelAge.setAlignmentX(CENTER_ALIGNMENT);
		panelAge.setBackground(Color.getHSBColor(100, 100, 100));
		panelAge.add(age);
		panelAge.add(ageText);
		box.add(panelAge);
		
		JPanel panelClass= new JPanel();
		panelClass.setAlignmentX(CENTER_ALIGNMENT);
		panelClass.setBackground(Color.getHSBColor(100, 100, 100));
		panelClass.add(pclass);
		c1.setBackground(Color.getHSBColor(100, 100, 100));
		c2.setBackground(Color.getHSBColor(100, 100, 100));
		c3.setBackground(Color.getHSBColor(100, 100, 100));
		panelClass.add(c1);
		panelClass.add(c2);
		panelClass.add(c3);
		box.add(panelClass);
		
		JPanel panelSibp= new JPanel();
		panelSibp.setAlignmentX(CENTER_ALIGNMENT);
		panelSibp.setBackground(Color.getHSBColor(100, 100, 100));
		panelSibp.add(sibp);
		panelSibp.add(sibpText);
		box.add(panelSibp);
		
		JPanel panelParch= new JPanel();
		panelParch.setAlignmentX(CENTER_ALIGNMENT);
		panelParch.setBackground(Color.getHSBColor(100, 100, 100));
		panelParch.add(parch);
		panelParch.add(parchText);
		box.add(panelParch);
		
		JPanel panelFare= new JPanel();
		panelFare.setAlignmentX(CENTER_ALIGNMENT);
		panelFare.setBackground(Color.getHSBColor(100, 100, 100));
		panelFare.add(fare);
		panelFare.add(fareText);
		box.add(panelFare);
		
		JPanel panelB= new JPanel();
		panelB.setAlignmentX(CENTER_ALIGNMENT);
		panelB.setBackground(Color.getHSBColor(100, 100, 100));
		JButton jb=new JButton("Finish");
		panelB.add(jb);
		box.add(panelB);
		
		JPanel panelStatistic= new JPanel();
		panelStatistic.setAlignmentX(CENTER_ALIGNMENT);
		panelStatistic.setBackground(Color.getHSBColor(100, 100, 100));
		panelStatistic.add(statistic);
		box.add(panelStatistic);
		
		JPanel panelResult= new JPanel();
		panelResult.setAlignmentX(CENTER_ALIGNMENT);
		panelB.setBackground(Color.getHSBColor(100, 100, 100));
		panelResult.add(resultJava);
		panelResult.add(resultUnb);
		box.add(panelResult);
		
		
		SimpleDateFormat date_format=new SimpleDateFormat("HH:mm:ss  dd.MM.yyyy.");
		final JLabel timedate=new JLabel(date_format.format(new GregorianCalendar().getTime()));
		Timer timer=new Timer(500,new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				timedate.setText(DateFormat.getDateTimeInstance().format(new Date()));
				
			}
		});
		timer.setRepeats(true);
		timer.setCoalesce(true);
		timer.setInitialDelay(0);
		timer.start();
		statusPanel.add(timedate,BorderLayout.EAST);
		
		male.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(male.isSelected()) {
					female.setSelected(false);
				}
			}
		});
		
		female.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(female.isSelected()) {
					male.setSelected(false);
			}
				
			}
		});
		
		c1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(c1.isSelected()) {
					c2.setSelected(false);
					c3.setSelected(false);
				}
			}
		});
		c2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(c2.isSelected()) {
					c1.setSelected(false);
					c3.setSelected(false);
				}
			}
		});		
		
		c3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(c3.isSelected()) {
					c2.setSelected(false);
					c1.setSelected(false);
				}
			}
		});	
		
		jb.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				resultJava.setText("");
				resultUnb.setText("");
				ArrayList<String> proba=new ArrayList<String>();
				proba.add(""); //survived
				if(c1.isSelected())
					proba.add("1"); //class
				else if(c2.isSelected())
					proba.add("2");
				else if(c3.isSelected())
					proba.add("3");
				else 
					proba.add("");
				
				if(male.isSelected())
					proba.add("male"); // gender
				else if(female.isSelected())
					proba.add("female");
				else
					proba.add("");
				
				
				proba.add(ageText.getText()); //age
				proba.add(sibpText.getText()); //sibsp
				proba.add(parchText.getText());//parch
				proba.add(fareText.getText());//fare
				proba.add("");//cabin//noooooo
				if(emberkedS.isSelected())
					proba.add("S");//embarked
				else if(emberkedC.isSelected())
					proba.add("C");
				else if(emberkedQ.isSelected())
					proba.add("Q");
				else 
					proba.add("");

				boolean nonExist=true;
				for(int i=0;i<proba.size();i++) {
					if(!proba.get(i).equals("")) {
						nonExist=false;				
						break;
					}
				}
				if(!nonExist) {
					try {
						if(bayes.BayesianTheoremCalculate(proba).equals("1"))
							resultJava.setText("Result calculated using Bayesian theorem: You would survive!");
						else
							resultJava.setText("Result calculated using Bayesian theorem: You would not survive!");
						
						if(bayes.BayesianNetworkCalculate(proba).equals("1"))
							resultUnb.setText("Result calculated using Bayesian network: You would survive!");
						else
							resultUnb.setText("Result calculated using Bayesian network: You would not survive!");
					} catch (Exception e1) {
							e1.printStackTrace();
					}
				}
			}
		});
		
        this.addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent e) {
			}
			
			public void windowIconified(WindowEvent e) {
			}
			
			public void windowDeiconified(WindowEvent e) {
			}
			
			public void windowDeactivated(WindowEvent e) {
				
			}
			public void windowClosing(WindowEvent e) {
				
			}
			
			public void windowClosed(WindowEvent e) {
		
			}
			
			public void windowActivated(WindowEvent e) {
				
			}
		});
		
	}	
	
}