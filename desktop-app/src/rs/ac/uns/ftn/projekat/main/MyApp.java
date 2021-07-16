package rs.ac.uns.ftn.projekat.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MyApp{ 
	static String filePath = "mushrooms.csv";
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
					for(int i = 0; i < str1.length ; i++) {
						d.add(str1[i]);
					}
					t.add(d);
					data = t;
				}else firstLine=true;
				System.out.println(d);
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
		double result = 1;
		ArrayList<Integer> count=new ArrayList<Integer>(Collections.nCopies(23, 0));
		for(int i = 0; i < data.size() ;i++) {
			
			if(data.get(i).get(1).equals(values.get(1))) {
				count.set(1, count.get(1)+1);
			}
			if(data.get(i).get(2).equals(values.get(2))) {
				count.set(2, count.get(2)+1);
			}
			if(data.get(i).get(3).equals(values.get(3))) {
				count.set(3, count.get(3)+1);
			}
			if(data.get(i).get(4).equals(values.get(4))) {
				count.set(4, count.get(4)+1);
			}
			if(data.get(i).get(5).equals(values.get(5))) {
				count.set(5, count.get(5)+1);
			}
			if(data.get(i).get(6).equals(values.get(6))) {
				count.set(6, count.get(6)+1);
			}
			if(data.get(i).get(7).equals(values.get(7))) {
				count.set(7, count.get(7)+1);
			}
			if(data.get(i).get(8).equals(values.get(8))) {
				count.set(8, count.get(8)+1);
			}
			if(data.get(i).get(9).equals(values.get(9))) {
				count.set(9, count.get(9)+1);
			}
			if(data.get(i).get(10).equals(values.get(10))) {
				count.set(10, count.get(10)+1);
			}
			if(data.get(i).get(11).equals(values.get(11))) {
				count.set(11, count.get(11)+1);
			}
			if(data.get(i).get(12).equals(values.get(12))) {
				count.set(12, count.get(12)+1);
			}
			if(data.get(i).get(13).equals(values.get(13))) {
				count.set(13, count.get(13)+1);
			}
			if(data.get(i).get(14).equals(values.get(14))) {
				count.set(14, count.get(14)+1);
			}
			if(data.get(i).get(15).equals(values.get(15))) {
				count.set(15, count.get(15)+1);
			}
			if(data.get(i).get(16).equals(values.get(16))) {
				count.set(16, count.get(16)+1);
			}
			if(data.get(i).get(17).equals(values.get(17))) {
				count.set(17, count.get(17)+1);
			}
			if(data.get(i).get(18).equals(values.get(18))) {
				count.set(18, count.get(18)+1);
			}
			if(data.get(i).get(19).equals(values.get(19))) {
				count.set(19, count.get(19)+1);
			}
			if(data.get(i).get(20).equals(values.get(20))) {
				count.set(20, count.get(20)+1);
			}
			if(data.get(i).get(21).equals(values.get(21))) {
				count.set(21, count.get(21)+1);
			}
			if(data.get(i).get(22).equals(values.get(22))) {
				count.set(22, count.get(22)+1);
			}
		}
		
		//P(BC)=P(B)*P(C)
		for (int j = 0; j < count.size(); j++) {
			if(count.get(j)!=0)
			result*=count.get(j)/ (data.size()*1.0);
		}
		
		System.out.println(result);
		return result;
	}
	
	public double classEP(String classEP,ArrayList<String> values) {
		double result = 1;
		int countIsClass = 0;
		ArrayList<Integer> count=new ArrayList<Integer>(Collections.nCopies(23, 0));
		
		for(int i = 0; i < data.size() ;i++) {
			if(data.get(i).get(0).equals(classEP)) {
				countIsClass++;
			}
			if(data.get(i).get(1).equals(values.get(1)) && data.get(i).get(0).equals(classEP)) {
				count.set(1, count.get(1)+1);
			}
			if(data.get(i).get(2).equals(values.get(2)) && data.get(i).get(0).equals(classEP)) {
				count.set(2, count.get(2)+1);
			}
			if(data.get(i).get(3).equals(values.get(3)) && data.get(i).get(0).equals(classEP)) {
				count.set(3, count.get(3)+1);
			}
			if(data.get(i).get(4).equals(values.get(4)) && data.get(i).get(0).equals(classEP)) {
				count.set(4, count.get(4)+1);
			}
			if(data.get(i).get(5).equals(values.get(5)) && data.get(i).get(0).equals(classEP)) {
				count.set(5, count.get(5)+1);
			}
			if(data.get(i).get(6).equals(values.get(6)) && data.get(i).get(0).equals(classEP)) {
				count.set(6, count.get(6)+1);
			}
			if(data.get(i).get(7).equals(values.get(7)) && data.get(i).get(0).equals(classEP)) {
				count.set(7, count.get(7)+1);
			}
			if(data.get(i).get(8).equals(values.get(8)) && data.get(i).get(0).equals(classEP)) {
				count.set(8, count.get(8)+1);
			}
			if(data.get(i).get(9).equals(values.get(9)) && data.get(i).get(0).equals(classEP)) {
				count.set(9, count.get(9)+1);
			}
			if(data.get(i).get(10).equals(values.get(10)) && data.get(i).get(0).equals(classEP)) {
				count.set(10, count.get(10)+1);
			}
			if(data.get(i).get(11).equals(values.get(11)) && data.get(i).get(0).equals(classEP)) {
				count.set(11, count.get(11)+1);
			}
			if(data.get(i).get(12).equals(values.get(12)) && data.get(i).get(0).equals(classEP)) {
				count.set(12, count.get(12)+1);
			}
			if(data.get(i).get(13).equals(values.get(13)) && data.get(i).get(0).equals(classEP)) {
				count.set(13, count.get(13)+1);
			}
			if(data.get(i).get(14).equals(values.get(14)) && data.get(i).get(0).equals(classEP)) {
				count.set(14, count.get(14)+1);
			}
			if(data.get(i).get(15).equals(values.get(15)) && data.get(i).get(0).equals(classEP)) {
				count.set(15, count.get(15)+1);
			}
			if(data.get(i).get(16).equals(values.get(16)) && data.get(i).get(0).equals(classEP)) {
				count.set(16, count.get(16)+1);
			}
			if(data.get(i).get(17).equals(values.get(17)) && data.get(i).get(0).equals(classEP)) {
				count.set(17, count.get(17)+1);
			}
			if(data.get(i).get(18).equals(values.get(18)) && data.get(i).get(0).equals(classEP)) {
				count.set(18, count.get(18)+1);
			}
			if(data.get(i).get(19).equals(values.get(19)) && data.get(i).get(0).equals(classEP)) {
				count.set(19, count.get(19)+1);
			}
			if(data.get(i).get(20).equals(values.get(20)) && data.get(i).get(0).equals(classEP)) {
				count.set(20, count.get(20)+1);
			}
			if(data.get(i).get(21).equals(values.get(21)) && data.get(i).get(0).equals(classEP)) {
				count.set(21, count.get(21)+1);
			}
			if(data.get(i).get(22).equals(values.get(22)) && data.get(i).get(0).equals(classEP)) {
				count.set(22, count.get(22)+1);
			}
		}
		//P(BC|A)*P(A)=P(A)*P(B|A)*P(C|A)
		result=countIsClass/ (data.size()*1.0);//P(A)
		
		for (int j = 1; j < count.size(); j++) {
			if(count.get(j)!=0)
			result*=(count.get(j)/ (countIsClass*1.0));//P(B|A)=P(BA)/P(B)
		}
		System.out.println(result+classEP);
		return result;
	}
	
	public String compared(ArrayList<String> valuse) {
		String str = "";
		double d1 = 0,d2 = 0;
		
		//P(A|BC)= P(BC|A)*P(A)/P(BC)
		d1 =classEP("e", valuse) / denominator(valuse);
		d2 = classEP("p", valuse) / denominator(valuse);
		if(d1 > d2) {
			str = "e";
		}else {
			str = "p.";
		}
		System.out.println("Probability of e:"+d1);
		System.out.println("Probability of P::"+d2);
		System.out.println(str);
		return str;
	}

	public static void main(String[] args){
		ArrayList<String> proba=new ArrayList<String>();
		proba.add("");
		proba.add("k");
		proba.add("y");
		proba.add("n");
		proba.add("f");
		proba.add("f");
		proba.add("f");
		proba.add("c");
		proba.add("n");
		proba.add("b");
		proba.add("t");
		proba.add("?");
		proba.add("k");
		proba.add("s");
		proba.add("p");
		proba.add("w");
		proba.add("p");
		proba.add("w");
		proba.add("o");
		proba.add("e");
		proba.add("w");
		proba.add("v");
		proba.add("d");
		
		MyApp ba = new MyApp();
		ba.readTable(filePath);
		ba.denominator(proba);
		ba.compared(proba);
	
	}

}