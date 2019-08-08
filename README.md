# 1.About this code
Java FFT and Random Forests implementation for IMU-based activity recognition

The acceleration data is helpful in activity recognition. A lot of smartphones have implemented this kind of function. But, suppose you want to personalize your activity recognition, for instance, different training in the Gym, then you might need to train your model and implement it.

<img src="https://github.com/fandulu/Build-your-IMU-based-activity-recognition-on-phone/blob/master/data_flow.png" width="500">


# 2.How to use this code
You can start the code from the Demo.java file by run
```
javac Demo.java
java Demo
```
I have attached the data.csv and label.csv for the Demo.java.

In data.csv, each value is the magnitude of IMU x,y,z, which is calculated by sqrt(x^2+y^2+y^2). 
Notes: it is better to directly use x,y,z, I use magnitude for the simplicity purpose.

In label.csv, each value is the corresponding activity state labels at that moment, as 'neutral', 'positive' and 'negetive'.
You can adapt to more complicated case.

Here, I followed the wonderful tutorial from [Martin](https://github.com/fandulu/Build-your-IMU-based-activity-recognition-on-phone/blob/master/RandomForest%20%E2%80%93%20Martin's%20Blog.pdf), using [Weka](https://www.cs.waikato.ac.nz/ml/weka/) and [androidrf](https://github.com/fandulu/androidrf) to train my data and obtain my DetectorRandomForest.java and Prediction.java.

Compare with Martin's tutorial, I add FFT.java to obtain FFT features. You can adjust the FFT shift window based on your data sampling rate.

After you generate DetectorRandomForest.java and Prediction.java, you can directly copy these files to your project to build the activity recognition app, it is self-contained.
