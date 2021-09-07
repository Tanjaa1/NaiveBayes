package rs.ac.uns.ftn.projekat.front;

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
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;
import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;
import unbbayes.io.exception.LoadException;

import rs.ac.uns.ftn.projekat.backend.Bayes;

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
	
	static ProbabilisticNetwork net;
	static IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
	static List<Node> nodeList;
	
	public static MainFrame getInstance() throws LoadException, IOException {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	
	private MainFrame() throws LoadException, IOException
	{
		BaseIO io = new NetIO();
		net= (ProbabilisticNetwork)io.load(new File("unb.net"));
	 	algorithm.setNetwork(net);
		algorithm.run();			
		nodeList = net.getNodes();
		
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth *2/3, screenHeight *4/5);
		setTitle("Titanic");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage((new ImageIcon("resources/attack.png")).getImage());
		setResizable(false);
		//statusBar
		this.setLayout(new BorderLayout());
		JPanel statusPanel = new JPanel();

		
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setPreferredSize(new Dimension(this.getWidth(), 25));
		statusPanel.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		
		
//		JLabel statusJLabel  = new JLabel(this.getTitle());
//		statusPanel.add(statusJLabel,BorderLayout.CENTER);
//		
		Box box=Box.createVerticalBox();
		this.add(box,BorderLayout.CENTER);
		JLabel JLabel=new JLabel("Would you survive the sinking of the Titanic?");
		JLabel.setFont(new Font("Calibri", Font.BOLD, 21));
		panel.add(JLabel);
		box.add(panel);
		
		JPanel panelGender= new JPanel();
		panelGender.setAlignmentX(RIGHT_ALIGNMENT);
		panelGender.setBackground(Color.getHSBColor(100, 100, 100));
		panelGender.add(gender);
		panelGender.add(female);
		panelGender.add(male);
		box.add(panelGender);

		JPanel panelAge= new JPanel();
		panelAge.setAlignmentX(RIGHT_ALIGNMENT);
		panelAge.setBackground(Color.getHSBColor(100, 100, 100));
		panelAge.add(age);
		panelAge.add(ageText);
		box.add(panelAge);
		
		JPanel panelClass= new JPanel();
		panelClass.setAlignmentX(RIGHT_ALIGNMENT);
		panelClass.setBackground(Color.getHSBColor(100, 100, 100));
		panelClass.add(pclass);
		panelClass.add(c1);
		panelClass.add(c2);
		panelClass.add(c3);
		box.add(panelClass);
		
		JPanel panelSibp= new JPanel();
		panelSibp.setAlignmentX(RIGHT_ALIGNMENT);
		panelSibp.setBackground(Color.getHSBColor(100, 100, 100));
		panelSibp.add(sibp);
		panelSibp.add(sibpText);
		box.add(panelSibp);
		
		JPanel panelParch= new JPanel();
		panelParch.setAlignmentX(RIGHT_ALIGNMENT);
		panelParch.setBackground(Color.getHSBColor(100, 100, 100));
		panelParch.add(parch);
		panelParch.add(parchText);
		box.add(panelParch);
		
//		JPanel panelEmberked= new JPanel();
//		panelEmberked.setAlignmentX(RIGHT_ALIGNMENT);
//		panelEmberked.setBackground(Color.getHSBColor(100, 100, 100));
//		panelEmberked.add(embarked);
//		panelEmberked.add(emberkedS);
//		panelEmberked.add(emberkedC);
//		panelEmberked.add(emberkedQ);
//		box.add(panelEmberked);
		
		JPanel panelFare= new JPanel();
		panelFare.setAlignmentX(RIGHT_ALIGNMENT);
		panelFare.setBackground(Color.getHSBColor(100, 100, 100));
		panelFare.add(fare);
		panelFare.add(fareText);
		box.add(panelFare);
		
		JPanel panelB= new JPanel();
		panelB.setAlignmentX(RIGHT_ALIGNMENT);
		panelB.setBackground(Color.getHSBColor(100, 100, 100));
		JButton jb=new JButton("Finish");
		panelB.add(jb);
		box.add(panelB);
		
		JPanel panelResult= new JPanel();
		panelResult.setAlignmentX(RIGHT_ALIGNMENT);
		panelB.setBackground(Color.getHSBColor(100, 100, 100));
		panelResult.add(resultJava);
		box.add(panelResult);
		
		JPanel panelResultUnb= new JPanel();
		panelResultUnb.setAlignmentX(RIGHT_ALIGNMENT);
		panelResultUnb.setBackground(Color.getHSBColor(100, 100, 100));
		panelResultUnb.add(resultUnb);
		box.add(panelResultUnb);
		
		SimpleDateFormat date_format=new SimpleDateFormat("HH:mm:ss  dd.MM.yyyy.");
		JLabel timedate=new JLabel(date_format.format(new GregorianCalendar().getTime()));
		Timer timer=new Timer(500,new ActionListener() {
			
			@Override
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
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(male.isSelected()) {
					female.setSelected(false);
					ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("Gender");
					factNode.addFinding(0);
				}
			}
		});
		
		female.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(female.isSelected()) {
					male.setSelected(false);
					ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("Gender");
					factNode.addFinding(1);
			}
				
			}
		});
		
		c1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(c1.isSelected()) {
					c2.setSelected(false);
					c3.setSelected(false);
					ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("PClass");
					factNode.addFinding(0);
				}
			}
		});
		c2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(c2.isSelected()) {
					c1.setSelected(false);
					c3.setSelected(false);
					ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("PClass");
					factNode.addFinding(1);
				}
			}
		});		
		
		c3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(c3.isSelected()) {
					c2.setSelected(false);
					c1.setSelected(false);
					ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("PClass");
					factNode.addFinding(2);
				}
			}
		});	
		
//		emberkedS.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(emberkedS.isSelected()) {
//					emberkedC.setSelected(false);
//					emberkedQ.setSelected(false);
//				}
//			}
//		});	
//		
//		emberkedC.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(emberkedC.isSelected()) {
//					emberkedS.setSelected(false);
//					emberkedQ.setSelected(false);
//				}
//			}
//		});	
//
//		emberkedQ.addActionListener(new ActionListener() {
//	
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(emberkedQ.isSelected()) {
//					emberkedC.setSelected(false);
//					emberkedS.setSelected(false);
//				}
//			}
//		});	
		
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean nonExist=false;
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
				else {
					nonExist=true;
					proba.add("");
				}
				if(male.isSelected())
					proba.add("male"); // gender
				else if(female.isSelected())
					proba.add("female");
				else{
					nonExist=true;
					proba.add("");
				}
				
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
				else {
					nonExist=true;
					proba.add("");
				
				for(int i=0;i<proba.size();i++)
					if(!proba.get(i).equals("")) {
						Bayes bayes=new Bayes();
						resultJava.setText("Result calculated using Bayes algorithm: "+bayes.Calculate(proba));				
						break;
					}
				}
				if(!ageText.getText().isEmpty()) {
					if(Integer.parseInt(ageText.getText())<18) {
						ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("Age");
						factNode.addFinding(0);
					}else if(Integer.parseInt(ageText.getText())<55) {
						ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("Age");
						factNode.addFinding(1);
					}else {
						ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("Age");
						factNode.addFinding(2);
					}				
				}
				
				if(!sibpText.getText().isEmpty()) {
					if(Integer.parseInt(sibpText.getText())==0) {
						ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("Sibp");
						factNode.addFinding(0);
					}else if(Integer.parseInt(sibpText.getText())<=3) {
						ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("Sibp");
						factNode.addFinding(1);
					}else if(Integer.parseInt(sibpText.getText())<=6) {
						ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("Sibp");
						factNode.addFinding(2);
					}else {
						ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("Sibp");
						factNode.addFinding(3);
					}				
				}
				
				
				if(!parchText.getText().isEmpty()) {
					if(Integer.parseInt(parchText.getText())==0) {
						ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("Parch");
						factNode.addFinding(0);
					}else if(Integer.parseInt(parchText.getText())<=3) {
						ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("Parch");
						factNode.addFinding(1);
					}else {
						ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("Parch");
						factNode.addFinding(2);
					}				
				}
				

				if(!fareText.getText().isEmpty()) {
					if(Integer.parseInt(fareText.getText())<51.23) {
						ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("Fare");
						factNode.addFinding(0);
					}else if(Integer.parseInt(fareText.getText())<153.70) {
						ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("Fare");
						factNode.addFinding(1);
					}else if(Integer.parseInt(fareText.getText())<307.40) {
						ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("Fare");
						factNode.addFinding(1);
					}else {
						ProbabilisticNode factNode = (ProbabilisticNode)net.getNode("Fare");
						factNode.addFinding(2);
					}				
				}
				
				try {
					net.updateEvidences();
		        	for (Node node : nodeList) {
		        		if(node.getDescription().equals("C5")) {
			        		if(((ProbabilisticNode)node).getMarginalAt(0) < ((ProbabilisticNode)node).getMarginalAt(1))
								resultUnb.setText("Result calculated using Bayes algorithm: You would survive!");
							else
								resultUnb.setText("Result calculated using Bayes algorithm: You would not survive!");
		        		}
		        	}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
        this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}
			@Override
			public void windowClosing(WindowEvent e) {
				//UKOLIKO ZELIMO DA SACUVAMO KADA IZADJE
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
		
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
			}
		});
		
	}	
	
}