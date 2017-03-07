
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws Exception 
	{
		if(args.length!=1)
		{
			System.err.println("No arguments");
			System.exit(1);
		}
	
		int totalyescount = 0, totalnocount = 0, totalcount = 0;
		Word newWord = null;
		HashMap<String, double []> hm = new HashMap<String, double []>();
		double p_yes = 0.0, p_no = 0.0;
		double p_tennisn = 0.0, p_tennisy = 0.0, p_yesx =0.0, p_nox = 0.0;
		double p_swindy = 0.0, p_swindn = 0.0, p_wwindy = 0.0, p_wwindn = 0.0;
		double p_hhumidy = 0.0, p_hhumidn = 0.0, p_nhumidy= 0.0, p_nhumidn = 0.0;
		double p_soutlooky = 0.0, p_soutlookn = 0.0, p_routlooky = 0.0, p_routlookn = 0.0, p_ooutlooky = 0.0, p_ooutlookn = 0.0;
		double p_htempy = 0.0, p_htempn = 0.0, p_mtempy = 0.0, p_mtempn = 0.0, p_ctempy = 0.0, p_ctempn = 0.0;
		ArrayList<Word> list=new ArrayList<Word>();
		FileProcessor file_proc = new FileProcessor(args[0]);
		String line = "";
		while ((line = file_proc.readLineFromFile()) != null) 
		{
			//System.out.println(line);
			String[] temp = line.split("\\s+");
				if(temp[4].equals("No"))
				{
					totalnocount++;
				}
				else
				{
					totalyescount++;
				}
				totalcount = totalnocount + totalyescount;
				Boolean present=false;
			for(String temp_word : temp)
			{
				if(!temp_word.equalsIgnoreCase(temp[temp.length-1]))
				{
					present=false;
					for(Word word : list)
					{
						if(word.getName().equalsIgnoreCase(temp_word))
						{
							present=true;
							if(temp[temp.length-1].equalsIgnoreCase("Yes"))
								word.setYes(word.getYes()+1);
						
							if(temp[temp.length-1].equalsIgnoreCase("No"))
								word.setNo(word.getNo()+1);
						}
					
					}
					if(!present)
					{
						if(temp[temp.length-1].equalsIgnoreCase("Yes"))
							newWord = new Word(temp_word,1,0);
							
						if(temp[temp.length-1].equalsIgnoreCase("No"))
							newWord= new Word(temp_word,0,1);
						
						list.add(newWord);	
					}		
				}			
			}
	}
		
		p_yes = (double) totalyescount / totalcount;
		p_no = (double) totalnocount / totalcount;
		
		System.out.println("probability of playing tennis is "+p_yes+" And probability of not playing tennis is "+p_no);
		for(Word word: list)
		{
			switch(word.getName())
			{
			case "Sunny":
				p_soutlooky = (double)word.getYes() / totalyescount;
				p_soutlookn = (double)word.getNo() / totalnocount;
				System.out.println("\nProbability of outlook with sunny/yes = "+p_soutlooky+" and sunny/no = "+p_soutlookn);
				break;
			case "Rain":
				p_routlooky = (double)word.getYes() / totalyescount;
				p_routlookn = (double)word.getNo() / totalnocount;
				System.out.println("Probability of outlook with rain/yes = "+p_routlooky+" and rain/no = "+p_routlookn);
				break;
			case "Overcast":
				p_ooutlooky = (double)word.getYes() / totalyescount;
				p_ooutlookn = (double)word.getNo() / totalnocount;
				System.out.println("Probability of outlook with overcast/yes = "+p_ooutlooky+" and overcast/no = "+p_ooutlookn);
				break;
			case "High":
				p_hhumidy = (double)word.getYes() / totalyescount;
				p_hhumidn = (double)word.getNo() / totalnocount;
				System.out.println("Probability of humidity with high/yes = "+p_hhumidy+" and high/no = "+p_hhumidn);
				break;
			case "Weak":
				p_wwindy = (double)word.getYes() / totalyescount;
				p_wwindn = (double)word.getNo() / totalnocount;
				System.out.println("Probability of wind with weak/yes = "+p_wwindy+" and wind/no = "+p_wwindn);
				break;
			case "Hot":
				p_htempy = (double)word.getYes() / totalyescount;
				p_htempn = (double)word.getNo() / totalnocount; 
				System.out.println("Probability of temperature with hot/yes = "+p_htempy+" and hot/no = "+p_htempn);
				break;
			case "Mild":
				p_mtempy = (double)word.getYes() / totalyescount;
				p_mtempn = (double)word.getNo() /totalnocount;
				System.out.println("Probability of temperature with mild/yes = "+p_mtempy+" and mild/no = "+p_mtempn);
				break;
			case "Cool":
				p_ctempy = (double)word.getYes() / totalyescount;
				p_ctempn = (double)word.getNo() / totalnocount;
				System.out.println("Probability of temperature with cool/yes = "+p_ctempy+" and cool/no = "+p_ctempn);
				break;
			case "Normal":
				p_nhumidy = (double)word.getYes() / totalyescount;
				p_nhumidn = (double)word.getNo() / totalnocount;
				System.out.println("Probability of humidity with normal/yes = "+p_nhumidy+" and normal/no = "+p_nhumidn);
				break;
			case "Strong":
				p_swindy = (double)word.getYes() / totalyescount;
				p_swindn = (double)word.getNo() / totalnocount;
				System.out.println("Probability of wind with strong/yes = "+p_swindy+" and strong/no = "+p_swindn);
				break;
			}
		}	
		
		String[] feature = {"sunny","rain","overcast","hot","mild","cool","high","normal","weak","strong"};
		double[] prob_no = {p_soutlookn,p_routlookn,p_ooutlookn,p_htempn,p_mtempn,p_ctempn,p_hhumidn,p_nhumidn,p_wwindn,p_swindn};
		double[] prob_yes = {p_soutlooky,p_routlooky,p_ooutlooky,p_htempy,p_mtempy,p_ctempy,p_hhumidy,p_nhumidy,p_wwindy,p_swindy};
		
		for(int i = 0; i < feature.length; i++)
		{
			double[] var = {prob_no[i],prob_yes[i]};
			hm.put(feature[i], var);
		}
		
		System.out.println("Enter values to predict whether to play tennis or not: \n");
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter value for outlook:(sunny/rain/overcast)");
		String s1 = scan.nextLine();
		System.out.println("Enter value for humidity:(high/normal)");
		String s2 = scan.nextLine();
		System.out.println("Enter value for temperature:(hot/mild/cool)");
		String s3 = scan.nextLine();
		System.out.println("Enter value for wind:(weak/strong)");
		String s4 = scan.nextLine();
		
		double[] oval = hm.get(s1.toLowerCase());
		double[] tval = hm.get(s3.toLowerCase());
		double[] hval = hm.get(s2.toLowerCase());
		double[] wval = hm.get(s4.toLowerCase());
		
		p_tennisy = oval[1] * tval[1] * hval[1] * wval[1];
		p_tennisn = oval[0] * tval[0] * hval[0] * wval[0];
		
		p_yesx = p_tennisy * p_yes;
		p_nox = p_tennisn * p_no;
		
		if(p_yesx > p_nox)
		{
			System.out.println("Can Play tennis");
		}
		else
		{
			System.out.println("Can not play Tennis");
		}
	}
}
