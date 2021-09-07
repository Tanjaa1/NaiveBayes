package rs.ac.uns.ftn.projekat.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import rs.ac.uns.ftn.projekat.front.MainFrame;
import unbbayes.io.exception.LoadException;

public class MyApp{ 
	static String filePath = "train.csv";
	static ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
	
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
						t.add(d);
					data = t;
				
					if(d.size()<9)
						d.add("");
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
	
	public double denominator(ArrayList<String> values) {
		double result = 1.0;
		ArrayList<Integer> count=new ArrayList<Integer>(Collections.nCopies(9, 0));
		for(int i = 0; i < data.size() ;i++) {
			for(int k = 1; k < count.size() ;k++) {
				if(data.get(i).get(k).equals(values.get(k)))
					count.set(k, count.get(k)+1);
			}
		}		
		//P(BC)=P(B)*P(C)
		for (int j = 1; j < count.size(); j++) {
			if(count.get(j)!=0)
			result=result*count.get(j)/ (data.size()*1.0);
		}
		
		return result;
	}
	
	public double classEP(String classEP,ArrayList<String> values) {
		double result = 1.0;
		int countIsClass = 0;
		ArrayList<Integer> count=new ArrayList<Integer>(Collections.nCopies(9, 0));
		
		for(int i = 0; i < data.size() ;i++) {
			if(data.get(i).get(0).equals(classEP))
				countIsClass++;
			for(int k = 1; k < count.size() ;k++) {
				if(data.get(i).get(k).equals(values.get(k)) && data.get(i).get(0).equals(classEP))
					count.set(k, count.get(k)+1);
			}
		}
		//P(BC|A)*P(A)=P(A)*P(B|A)*P(C|A)=P(A)*P(BA)/P(A)*P(CA)/P(A)
		result=countIsClass/ (data.size()*1.0);//P(A)

		for (int j = 1; j < count.size(); j++) {//P(?A)/P(A)
			if(count.get(j)!=0)
				result*=(count.get(j)/(countIsClass*1.0));
		}
		return result;
	}
	

	public static void main(String[] args)throws LoadException, IOException {
		
		MainFrame mf=MainFrame.getInstance();
		mf.setVisible(true);
		
		
//		ArrayList<String> proba=new ArrayList<String>();
//		proba.add("0"); //survived
//		proba.add("0"); //class
//		proba.add("male"); // gender
//		proba.add(""); //age
//		proba.add("0"); //sibsp
//		proba.add("0");//parch
//		proba.add("");//fare
//		proba.add("");//cabin//noooooo
//		proba.add("S");//embarked
//		
//		MyApp ba = new MyApp();
//		ba.readTable(filePath);
//		
//		double d1 = 0,d2 = 0,d3=0,d4=0,d5=0;
//		
//		//P(A|BC)= P(BC|A)*P(A)/P(BC)
//		d1 =ba.classEP("0", proba) / ba.denominator(proba);
//		d2 = ba.classEP("1", proba) / ba.denominator(proba);
//		
//		System.out.println("Probability of 1:"+d1);
//		System.out.println("Probability of 2::"+d2);
		
	
	}

}
