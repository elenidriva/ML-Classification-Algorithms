# My Naive Bayes Email Classifier
Email tracking application for spam and ham messages - Message classification implemented with Naïve Bayes.

<p align="center">
  <img src="https://i.ibb.co/RCRkJRB/overall.png">
</p>

## Table of Contents
* [General info](#general-info)
* [Key features](#key-features)
* [Technologies & Tools](#technologies--tools)
* [Setup & Run](#setup--run)
* [ChartGo](#chartgo)
* [License](#license)


## General info
I trained and assessed my classifier for different train-test splits (10/90, 20/80, 30/70, 40/60, 50/50,
  60/40, 70/30, 80/20, 90/10) using Enron-Spam and Ling-Spam datasets.

The following metrics are used to assess the Classifier:
* Accuracy
* Precision
* Recall
* F1

The algorithm exports the results in a .txt automatically, which also includes the True Positives, True
Negatives, False Positives and False Negatives for each train-test split.
High accuracy and scores were achieved overall. Indicatively, for the 90/10 train-test split:
* Precision: 99%
* Recall: 84%
* F1: 90%

In the results.txt file I have provided all the results of metrics generated by my classifier.

## Key features
### DataSets: Enron-Spam and Ling-Spam
The data inside the datasets do not belong to me, nor are the copyrights mine.
They can both be found [here](http://www2.aueb.gr/users/ion/publications.html).

### Laplace Smoothing
In general, if the word "Art" does not exist in the category Ham (or Spam), then we have:
 P(art | Ham) = 0, leading to P(art and craft  | Ham) = 0
It is problematic when a frequency-based probability is zero, because it will wipe out all the information in the other probabilities, and we need to find a solution for this.

In statistics, Laplace Smoothing is a technique to smooth categorical data. Laplace Smoothing is introduced to solve the problem of zero probability.
### Use of Log probability
Since we're dealing with small numbers, it's safer to use log-probabilities.
```
// Calculation of P(spam) and P(Ham)
double pSpam = spamCount / totalCount;
double pHam = hamCount / totalCount;
// Log-probabilities.
double emailIsSpam = Math.log(pSpam);
double emailIsHam = Math.log(pHam);
// As we can easily understand we are dealing with small numbers (and to be precise, we're dealing with
// numbers between 0 and 1) and we apply multiplication/division to them, which can lead to extremely tiny
// numbers, which is often impossible to represent them in double or long double.
```
## Technologies & Tools
* Java 13
* Eclipse IDE for Java Developers

## Setup & Run
To run this project, you just need to simply download it.
Via command line:
```
$ cd  ../where_you_want_it_to_be_saved
$ git clone https://github.com/elenidriva/ML-Classification-Algorithms.git
```
Or download manually.

Import it in a programming environment or (i.e. Eclipse IDE for Enterprise Java Developers) or execute via command line.

## ChartGo
The graphs were generated by ChartGo and are used only for demonstration puroposes.
[ChartGo's official website](https://www.chartgo.com/)

## License
 The application is under the MIT license.
