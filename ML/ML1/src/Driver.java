import java.util.ArrayList;
import java.util.Arrays;

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
		double max = 0.0;
		double ivo = 0.0, ivt = 0.0, ivh = 0.0, ivw = 0.0;
		double sunnye = 0.0, raine = 0.0, hote = 0.0, highe = 0.0, milde = 0.0, weake = 0.0, stronge = 0.0, overcaste = 0.0, coole = 0.0, normale = 0.0;
		double totale = 0.0;
		double gainw = 0.0, gaino = 0.0, gainh = 0.0, gaint = 0.0;
		double gainrw = 0.0, gainrh = 0.0, gainro = 0.0, gainrt = 0.0; 
		int strongcount = 0, weakcount = 0, sunnycount = 0, hotcount = 0, highcount = 0, overcount = 0, raincount = 0, mildcount = 0, coolcount = 0, normalcount = 0;
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
		
		for(Word word: list)
		{
			switch(word.getName())
			{
			case "Sunny":
				sunnycount = word.getYes() + word.getNo();
				sunnye = -1 *(double) word.getYes()/sunnycount * (Math.log((double)word.getYes()/sunnycount))/Math.log(2.0) - (double) word.getNo()/sunnycount * (Math.log((double)word.getNo()/sunnycount))/Math.log(2.0);
				break;
			case "Rain":
				raincount = word.getYes() + word.getNo();
				raine = -1 *(double) word.getYes()/raincount * (Math.log((double)word.getYes()/raincount))/Math.log(2.0) - (double) word.getNo()/raincount * (Math.log((double)word.getNo()/raincount))/Math.log(2.0);
				break;
			case "Overcast":
				overcount = word.getYes() + word.getNo();
				overcaste = -1 *(double) word.getYes()/overcount * (Math.log((double)word.getYes()/overcount))/Math.log(2.0);
				break;
			case "High":
				highcount = word.getYes() + word.getNo();
				highe = -1 *(double) word.getYes()/highcount * (Math.log((double)word.getYes()/highcount))/Math.log(2.0) - (double) word.getNo()/highcount * (Math.log((double)word.getNo()/highcount))/Math.log(2.0);
				break;
			case "Weak":
				weakcount = word.getYes() + word.getNo();
				weake = -1 *(double) word.getYes()/weakcount * (Math.log((double)word.getYes()/weakcount))/Math.log(2.0) - (double) word.getNo()/weakcount * (Math.log((double)word.getNo()/weakcount))/Math.log(2.0);
				break;
			case "Hot":
				hotcount = word.getYes() + word.getNo();
				hote = -1 *(double) word.getYes()/hotcount * (Math.log((double)word.getYes()/hotcount))/Math.log(2.0) - (double) word.getNo()/hotcount * (Math.log((double)word.getNo()/hotcount))/Math.log(2.0);
				break;
			case "Mild":
				mildcount = word.getYes() + word.getNo();
				milde = -1 *(double) word.getYes()/mildcount * (Math.log((double)word.getYes()/mildcount))/Math.log(2.0) - (double) word.getNo()/mildcount * (Math.log((double)word.getNo()/mildcount))/Math.log(2.0);
				break;
			case "Cool":
				coolcount = word.getYes() + word.getNo();
				coole = -1 *(double) word.getYes()/coolcount * (Math.log((double)word.getYes()/coolcount))/Math.log(2.0) - (double) word.getNo()/coolcount * (Math.log((double)word.getNo()/coolcount))/Math.log(2.0);
				break;
			case "Normal":
				normalcount = word.getYes() + word.getNo();
				normale = -1 *(double) word.getYes()/normalcount * (Math.log((double)word.getYes()/normalcount))/Math.log(2.0) - (double) word.getNo()/normalcount * (Math.log((double)word.getNo()/normalcount))/Math.log(2.0);
				break;
			case "Strong":
				strongcount = word.getYes() + word.getNo();
				stronge = -1 *(double) word.getYes()/strongcount * Math.log((double)word.getYes()/strongcount)/Math.log(2.0) - (double) word.getNo()/strongcount * Math.log((double)word.getNo()/strongcount)/Math.log(2.0);
				break;
			}
		}	
		totale = -1 *(double) totalyescount/totalcount * (Math.log((double)totalyescount/totalcount))/Math.log(2) - (double) totalnocount/totalcount * (Math.log((double)totalnocount/totalcount))/Math.log(2);
		System.out.println("\n\nTotal Entropy is: " + totale);
		System.out.println("\n");
		gainw = totale - ((double)weakcount/totalcount) *weake - ((double)strongcount/totalcount) * stronge;
		gainh = totale - ((double)highcount/totalcount) *highe - ((double)normalcount/totalcount) * normale;
		gaino = totale - ((double)sunnycount/totalcount) *sunnye - ((double)raincount/totalcount) * raine - ((double)overcount/totalcount) * overcaste;
		gaint = totale - ((double)hotcount/totalcount) *hote - ((double)mildcount/totalcount) * milde - ((double)coolcount/totalcount) * coole;
		
		System.out.println("IG of wind is: "+ gainw);
		System.out.println("IG of humidity is: "+ gainh);
		System.out.println("IG of outlook is: "+ gaino);
		System.out.println("IG of temperature is: "+ gaint);
		
		ivo = - (((double)sunnycount / totalcount) *  (Math.log((double)sunnycount/totalcount)/Math.log(2.0))) - (((double)overcount / totalcount) * (Math.log((double)overcount/totalcount)/Math.log(2.0))) - (((double)raincount / totalcount) * (Math.log((double)raincount/totalcount)/Math.log(2.0))); 
		ivw = -(((double)strongcount / totalcount) * (Math.log((double)strongcount/totalcount)/Math.log(2.0))) - (((double)weakcount / totalcount) * (Math.log((double)weakcount/totalcount)/Math.log(2.0))); 
		ivh = -(((double)highcount / totalcount) * (Math.log((double)highcount/totalcount)/Math.log(2.0))) - (((double)normalcount / totalcount) * (Math.log((double)normalcount/totalcount)/Math.log(2.0)));;
		ivt = - (((double)hotcount / totalcount) *  (Math.log((double)hotcount/totalcount)/Math.log(2.0))) - (((double)mildcount / totalcount) * (Math.log((double)mildcount/totalcount)/Math.log(2.0))) - (((double)coolcount / totalcount) * (Math.log((double)coolcount/totalcount)/Math.log(2.0)));;
		
		gainro = gaino / ivo;
		gainrw = gainw / ivw;
		gainrh = gainh / ivh;
		gainrt = gaint / ivt;
		String feature = null;
		if(max < gainro)
		{
			max = gainro;
			feature = "outlook";
		}
		
		if(max < gainrw)
		{
			max = gainrw;
			feature = "wind";
		}
		
		if(max < gainrh)
		{
			max = gainrh;
			feature = "humidity";
		}
		
		if(max < gainrt)
		{
			max = gainrt;
			feature = "temperature";
		}
		
		System.out.println("\n");
		System.out.println("Gain ratio for outlook is : "+gainro);
		System.out.println("Gain ratio for humidity is : "+gainrh);
		System.out.println("Gain ratio for wind is : "+gainrw);
		System.out.println("Gain ratio for temperature is : "+gainrt);
		
		System.out.println("Therefore Best feature for split feature is: "+feature);
		
	}
}