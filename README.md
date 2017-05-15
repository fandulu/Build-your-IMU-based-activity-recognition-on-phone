# Java FFT and Random Forests implementaion for activity recognition

The acceleration data is helpful in activity recognition. A lot of smartphones have implemented this kind of function. But, suppose you want to personalize your own activity recognition, for instance, different training in the Gym, then you might need to train your own model and implement it.

Here, I followed the wonderful tutorial from [Martin](http://pielot.org/tag/randomforest/), using Weka and some scripts to
make the Fandom Forests class.

I also use the opensource FFT class. However, there are some tricks to setting the FFT shift window, you need to adjust it
base on your data smapling rate.
