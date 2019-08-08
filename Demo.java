import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List; 
import java.io.*; 
import java.lang.reflect.Field;

/*
 * This code implements the FFT and Random Forests for activity recognition
 * It can be implanted to Android Framework
 */

public class Demo
{
    public static void main(String[] args) 
    {
        //Get data from csv file
        String filename_data ="data.csv";
        File file_data = new File(filename_data);
        List<Double> data = new ArrayList<Double>();
        
        try{
        	Scanner inputStream = new Scanner(file_data);
        	while(inputStream.hasNext()){
        		String temp = inputStream.next();
        		data.add(Double.parseDouble(temp));
        		//System.out.println(temp);
       	}
			inputStream.close();
			/*
			for(int i=0;i<64;i++){
    			System.out.println(data.get(i));
			} 
			*/
        }catch (FileNotFoundException e){
        	e.printStackTrace();
        }

        //Get the true label from csv file
        String filename_label ="label.csv";
        File file_label = new File(filename_label);
        List<String> label_true = new ArrayList<String>();
        System.out.println("True label:");
        try{
        	Scanner inputStream = new Scanner(file_label);
        	while(inputStream.hasNext()){
        		String temp = inputStream.next();
        		label_true.add(temp);	
        		System.out.println(temp);
       	}
			inputStream.close();
		
        }catch (FileNotFoundException e){
        	e.printStackTrace();
        }

        // Apply FFT 
		
	//Data size
	int n = data.size();
	//FFT window step
	int step = 32;
	//Feature matrix size
	int rows = n/step-1;
	int columns = 64/2+1; 
	//System.out.println(rows);
	Double [][] features = new Double [rows][columns];
	List<String> label_prediction = new ArrayList<String>();
	System.out.println("Prediction label:");

	for (int i=0; i < rows; i++){ 
		// Choose your own windows size base on the data
		FFT fft = new FFT(64); // window size = 6.4 seconds, sampling rate : 0.1 HZ
	double[] im = new double[64];
	double[] re = new double[64];
	int data_start = step*(i+1)-step;

	RandomForest rf = new DetectorRandomForest();


	for(int k=0;k<64;k++){
			re[k]= data.get(k+data_start);
			im[k]=.0;
		}
		fft.fft(re,im);
		for (int j=0; j < columns; j++){	
			double a =  20*Math.log10(Math.abs(re[j]));
			int b = (int) a;
			a = (double) b;
			features[i][j]= a;
			String fieldName = "f"+(j+1);
			try
			{
				Field field = rf.getClass().getDeclaredField(fieldName);
				field.setDouble(rf,a);
			}catch(Exception e){}
		}

		Prediction p =rf.runClassification();
		label_prediction.add(p.label);
		System.out.println(p.label);

	}


	int numTotal = 0;
	int numCorrect = 0;
	int label_size = label_true.size();

	for(int k=0; k<label_size ; k++){
		boolean correct = false;
		correct = label_true.get(k).equals(label_prediction.get(k));

		if (correct) {
			numCorrect++;
		}
	}
	    int accInt = 10000 * numCorrect / label_size;
		double acc = accInt / 10000.0;
		System.out.println(label_size  + ": accuracy: " + acc);


		//output the features

		try{
		File file_out = new File ("output.csv"); 
		PrintWriter pw = new PrintWriter(file_out);
		for (int i=0; i < rows; i++){
			for (int j=0; j < columns; j++){
				pw.print(features[i][j]+ ",");
			}	
		}
		pw.flush();
		pw.close();	
		}catch (FileNotFoundException e){
		e.printStackTrace();
		}

    }
}
