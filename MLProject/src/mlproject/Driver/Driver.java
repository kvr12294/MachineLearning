package mlproject.Driver;

import java.util.Scanner;

import mlproject.algorithm.NBHandler;
import mlproject.algorithm.RFHandler;

public class Driver {
	private String trainFileName;
	private String testFileName;
	private RFHandler rfhandle;
	private NBHandler nbhandle;
	
	public static void main(String args[]){
		Driver dr = new Driver();
		if(args.length != 1){
			System.out.println("Invalid Arguments");
			System.exit(-1);
		}
		dr.initialize(args);
		dr.process();
	}
	
	public void initialize(String args[]){
		setTrainFileName(args[0]);
	}
	
	public void process(){
		int choice = 0;
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		while (true) {
			System.out.println("Enter Your Choice:\n 1. RANDOMIZED FOREST\n 2. NAIVE-BAYES\n 3. Display Results\n 4. Exit");
			choice = s.nextInt();
			switch (choice) {
			case 1:
				rfhandle = new RFHandler(trainFileName, testFileName);
				rfhandle.display();
				break;
			case 2:
				nbhandle = new NBHandler(trainFileName, testFileName);
				nbhandle.display();
				break;
			case 3:
				if(rfhandle != null){
					rfhandle.resultDisplay();
				}
				
				if(nbhandle != null){
					nbhandle.resultDisplay();
				}
				System.out.print("\n");
				break;
			case 4:
				System.exit(0);
			default:
				System.out.println("Thank you!");
				System.exit(0);
			}
		}
	}

	public String getTrainFileName() {
		return trainFileName;
	}

	public void setTrainFileName(String trainFileName) {
		this.trainFileName = trainFileName;
	}

	public String getTestFileName() {
		return testFileName;
	}

	public void setTestFileName(String testFileName) {
		this.testFileName = testFileName;
	}

	public RFHandler getRfhandle() {
		return rfhandle;
	}

	public void setRfhandle(RFHandler rfhandle) {
		this.rfhandle = rfhandle;
	}

	public NBHandler getNbhandle() {
		return nbhandle;
	}

	public void setNbhandle(NBHandler nbhandle) {
		this.nbhandle = nbhandle;
	}
}
