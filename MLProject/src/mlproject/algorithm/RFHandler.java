package mlproject.algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

public class RFHandler {
	private String testFileName;
	private String trainFileName;
	private Evaluation evaluation;
	private Evaluation evaluationTest;
	
	public RFHandler(String trainFileNameIn, String testFileNameIn){
		testFileName = testFileNameIn;
		trainFileName = trainFileNameIn;
		process();
	}
	
	public void process(){
		int numFolds = 10;
		try{
			RandomForest rf = new RandomForest();
			BufferedReader br = new BufferedReader(new FileReader(trainFileName));
			double percent = 60.0;
			Instances trainData = new Instances(br);
			trainData.setClassIndex(0);
			
			System.out.println("Performing " + percent + "% split evaluation.");
			int trainSize = (int) Math.round(trainData.numInstances() * percent/100);
			int testSize = trainData.numInstances() - trainSize;
			
			Instances trainData1 = new Instances(trainData, 0, trainSize);
			Instances testData1 = new Instances(trainData, trainSize, testSize);
			
			rf.buildClassifier(trainData1);
			for (int i=0; i < testData1.numInstances(); i++) {
				   double pred = rf.classifyInstance(testData1.instance(i)); 
				   System.out.print("ID: " + testData1.instance(i));
				   System.out.print(", actual: " + testData1.classAttribute().value((int) testData1.instance(i).classValue()));  
				   System.out.println(", predicted: " + testData1.classAttribute().value((int) pred));
			}
			br.close();
			
			setEvaluation(new Evaluation(trainData1));
			setEvaluationTest(new Evaluation(trainData1));
	        evaluation.crossValidateModel(rf, trainData1, numFolds, new Random(1));
	        evaluationTest.evaluateModel(rf, testData1);
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void display(){
		try{
			System.out.println(evaluation.toSummaryString("\nResults\n======\n", true));
			System.out.println(evaluationTest.toSummaryString("\nTest Results\n======\n", true));
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void resultDisplay() {
		System.out.println("==========Randomized Forest==========");
		if(evaluation != null || evaluationTest != null){
			System.out.println("Training Error: "+(double)evaluation.incorrect()/evaluation.numInstances()+"%");
			System.out.println("Testing Error: "+(double)evaluationTest.incorrect()/evaluationTest.numInstances()+"%");
		}
		else{
			System.out.println("Results are not generated!!!!");
		}
	}

	public String getTestFileName() {
		return testFileName;
	}

	public void setTestFileName(String testFileName) {
		this.testFileName = testFileName;
	}
	
	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public String getTrainFileName() {
		return trainFileName;
	}

	public void setTrainFileName(String trainFileName) {
		this.trainFileName = trainFileName;
	}

	public Evaluation getEvaluationTest() {
		return evaluationTest;
	}

	public void setEvaluationTest(Evaluation evaluationTest) {
		this.evaluationTest = evaluationTest;
	}
}
