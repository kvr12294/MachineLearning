package mlproject.algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class NBHandler {
	private String testFileName;
	private String trainFileName;
	private Evaluation evaluation;
	private Evaluation evaluationTest;
	
	public NBHandler(String trainFileNameIn, String testFileNameIn){
		trainFileName = trainFileNameIn;
		testFileName = testFileNameIn;
		process();
	}
	
	public void process(){
		int numFolds = 10;
		try{
			BufferedReader br = new BufferedReader(new FileReader(trainFileName));
			//BufferedReader br1 = new BufferedReader(new FileReader(testFileName));
			double percent = 60.0;
			Instances trainData = new Instances(br);
			//Instances testData = new Instances(br1);
			trainData.setClassIndex(0);
			//testData.setClassIndex(0);
			br.close();
			//br1.close();
			
			Classifier nb = (Classifier) new NaiveBayes();
			System.out.println("Performing " + percent + "% split evaluation.");
			int trainSize = (int) Math.round(trainData.numInstances() * percent/100);
			int testSize = trainData.numInstances() - trainSize;
			
			Instances trainData1 = new Instances(trainData, 0, trainSize);
			Instances testData1 = new Instances(trainData, trainSize, testSize);
			
			nb.buildClassifier(trainData1);
			
			for (int i=0; i < testData1.numInstances(); i++) {
				   double pred = nb.classifyInstance(testData1.instance(i)); 
				   System.out.print("ID: " + testData1.instance(i));
				   System.out.print(", actual: " + testData1.classAttribute().value((int) testData1.instance(i).classValue()));  
				   System.out.println(", predicted: " + testData1.classAttribute().value((int) pred));
			}
			
			setEvaluation(new Evaluation(trainData1));
			setEvaluationTest(new Evaluation(trainData1));
			evaluation.crossValidateModel(nb, trainData1, numFolds, new Random(1));
			evaluationTest.evaluateModel(nb, testData1);
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
		System.out.println("==========Naive Bayes==========");
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
