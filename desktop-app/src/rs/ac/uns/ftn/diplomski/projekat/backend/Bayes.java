package rs.ac.uns.ftn.diplomski.projekat.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

public class Bayes {
	private String trainFilePath = "train.csv";
	private String testFilePath = "test.csv";
	private String resultFilePath = "result.csv";
	private ArrayList<ArrayList<String>> train;// = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> test;// = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> testResult;// = new ArrayList<ArrayList<String>>();
	private Integer goodBT=0;
	private Integer goodBN=0;
	
	static ProbabilisticNetwork net;
	static IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
	
	public Bayes() throws Exception {
		BaseIO io = new NetIO();
		net= (ProbabilisticNetwork)io.load(new File("unb.net"));
	 	algorithm.setNetwork(net);
		algorithm.run();

		train=readTable(trainFilePath);
		testResult = readTable(resultFilePath);
		test=readTable(testFilePath);
	}
	
	public int BayesianNetworkStatistic() throws Exception {
		for (int i = 0; i<test.size();i++) {
			if(BayesianNetworkCalculate(test.get(i)).equals(testResult.get(i).get(1)))
					goodBN++;
		}

		return (int)(100*(goodBN/(1.00*(test.size()))));
	}
	
	public int BayesianTheoremStatistic() {
		for (int i = 0; i<test.size();i++) {
			if(BayesianTheoremCalculate(test.get(i)).equals(testResult.get(i).get(1)))
					goodBT++;
		}
		
		return (int)(100*(goodBT/(1.00*(test.size()))));
	}
	
	public ArrayList<ArrayList<String>> readTable(String filePath){
		ArrayList<String> d = null;
		ArrayList<ArrayList<String>> t = new ArrayList<ArrayList<String>>();
		File file = new File(filePath);
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
			BufferedReader bf = new BufferedReader(isr);
			String str = null;
			Boolean firstLine=false;
			while((str = bf.readLine()) != null) {		
				if(firstLine) {
					d = new ArrayList<String>();
					String[] str1 = str.split(",");
					for(int i = 0; i < str1.length ; i++)
						d.add(str1[i]);
					if(d.size()==6)
						d.add("");
					t.add(d);
				}else firstLine=true;
			}
			bf.close();
			isr.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("File does not exist!");
		}
		return t;
	}
	
	public double Denominator(ArrayList<String> values) {
		double result = 1.0;
		ArrayList<Integer> count=new ArrayList<Integer>(Collections.nCopies(values.size(), 0));
		for(int i = 0; i < train.size() ;i++) {
			for(int k = 1; k < count.size() ;k++) {
				if(!values.get(k).equals("")) {		
					if(train.get(i).get(k).equals(values.get(k)))
						count.set(k, count.get(k)+1);
				}
			}
		}		
		//P(BC)=P(B)*P(C)
		for (int j = 1; j < count.size(); j++) {
			if(count.get(j)!=0)
				result=result*count.get(j)/ (train.size()*1.0);
		}
		
		return result;
	}
	
	public double Numerator(String classEP,ArrayList<String> values) {
		double result = 1.0;
		int countIsClass = 0;
		ArrayList<Integer> count=new ArrayList<Integer>(Collections.nCopies(values.size(), 0));
		
		for(int i = 0; i < train.size() ;i++) {
			if(train.get(i).get(0).equals(classEP))
				countIsClass++;
			for(int k = 1; k < count.size() ;k++) {
				if(!values.get(k).equals("")) {
					if(train.get(i).get(k).equals(values.get(k)) && train.get(i).get(0).equals(classEP))
						count.set(k, count.get(k)+1);
				}
			}
		}
		//P(BC|A)*P(A)=P(A)*P(B|A)*P(C|A)=P(A)*P(BA)/P(A)*P(CA)/P(A)
		result=countIsClass/ (train.size()*1.0);//P(A)

		for (int j = 1; j < count.size(); j++) {//P(?A)/P(A)
			if(count.get(j)!=0)
				result*=(count.get(j)/(countIsClass*1.0));
		}
		return result;
	}
	
	public String BayesianTheoremCalculate(ArrayList<String> values){
		Double yes=0.0,no=0.0;
		//P(A|BC)= P(BC|A)*P(A)/P(BC)
		no=Numerator("0", values) / Denominator(values);
		yes=Numerator("1", values) / Denominator(values);
		if(yes>no)
			return "1";
		else
			return "0";
	}
	
	public String BayesianNetworkCalculate(ArrayList<String> values) throws Exception{

		algorithm.run();
		ProbabilisticNode nodePClass = (ProbabilisticNode)net.getNode("PClass");
		if(values.get(1).equals("1")) 
			nodePClass.addFinding(0);
		else if(values.get(1).equals("2"))
			nodePClass.addFinding(1);
		else if(values.get(1).equals("3"))
			nodePClass.addFinding(2);
		
		ProbabilisticNode nodeGender = (ProbabilisticNode)net.getNode("Gender");
		if(values.get(2).equals("female")) 
			nodeGender.addFinding(0);
		else if(values.get(2).equals("male"))
			nodeGender.addFinding(1);
		
		ProbabilisticNode nodeAge = (ProbabilisticNode)net.getNode("Age");
		if(!values.get(3).equals("")) {
			if(Double.parseDouble(values.get(3))<18) 
				nodeAge.addFinding(0);
			else if(Double.parseDouble(values.get(3))<55)
				nodeAge.addFinding(1);
			else 
				nodeAge.addFinding(2);
		}
		
		ProbabilisticNode nodeSibp = (ProbabilisticNode)net.getNode("Sibp");
		if(!values.get(4).equals("")) {
			if(Double.parseDouble(values.get(4))==0)
				nodeSibp.addFinding(0);
			else if(Double.parseDouble(values.get(4))<=3)
				nodeSibp.addFinding(1);
			else if(Double.parseDouble(values.get(4))<=6) 
				nodeSibp.addFinding(2);
			else 
				nodeSibp.addFinding(3);	
		}
		
		ProbabilisticNode nodeParch = (ProbabilisticNode)net.getNode("Parch");
			if(!values.get(5).equals("")) {
			if(Double.parseDouble(values.get(5))==0)
				nodeParch.addFinding(0);
			else if(Double.parseDouble(values.get(5))<=3)
				nodeParch.addFinding(1);
			else 
				nodeParch.addFinding(2);
		}
		
		ProbabilisticNode nodeFare = (ProbabilisticNode)net.getNode("Fare");
		if(!values.get(6).equals("")) {
			if(Double.parseDouble(values.get(6))<51.23)
				nodeFare.addFinding(0);
			else if(Double.parseDouble(values.get(6))<153.70)
				nodeFare.addFinding(1);
			else if(Double.parseDouble(values.get(6))<307.40)
				nodeFare.addFinding(2);
			else
				nodeFare.addFinding(3);
		}			
		
		//algorithm.run();
		net.updateEvidences();
		ProbabilisticNode nodeSurvived=(ProbabilisticNode)net.getNode("Survived");
		if(nodeSurvived.getMarginalAt(0)>nodeSurvived.getMarginalAt(1))
			return "1";
		else
			return "0";
	}
}
