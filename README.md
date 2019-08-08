# 1.About this code
Java FFT and Random Forests implementation for IMU-based activity recognition

The acceleration data is helpful in activity recognition. A lot of smartphones have implemented this kind of function. But, suppose you want to personalize your activity recognition, for instance, different training in the Gym, then you might need to train your model and implement it.

Here, I followed the wonderful tutorial from [Martin](http://pielot.org/tag/randomforest/), using Weka and some scripts to
make the Fandom Forests class.

I also use the opensource FFT class. However, there are some tricks to setting the FFT shift window, you need to adjust it
base on your data sampling rate.

You can directly copy these files to your project to build the activity recognition app, it is self-contained.


# 2.How to use this code
You can start the code from the Demo.java file by run
```
java Demo.java
```
I have attached the data.csv and label.csv for the Demo.java.

In data.csv, each value is the magnitude of IMU x,y,z, which is calculated by sqrt(x^2+y^2+y^2). 
Notes: it is better to directly use x,y,z, I use magnitude for the simplicity purpose.

In label.csv, each value is the corresponding activity state labels at that moment, as 'neutral', 'positive' and 'negetive'.
You can adapt to more complicated case.

After familar with Demo.java, it can be modifed to your project.

