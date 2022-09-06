# Finding K-Top frequency with Count-Min Sketch algorithm

![sketch](https://user-images.githubusercontent.com/96411307/187157930-8cf05787-b637-4601-aa9d-05abdcf2890e.png)

## Basic Overview
The Count-Min Sketch(CMS) is a great data structure for estimating the frequencies of different elements in a data stream.
The Count-Min sketch provides a different kind of solution to count tracking.

### *What’s the point of a Count-Min Sketch?*
It allocates a fixed amount of space to store count information, which does not change over time even as more and more counts are updated.
It uses hash functions to map events to frequencies, but unlike a hash table uses only sub-linear space, at the expense of overcounting some events due to collisions.


### *Why should I use a CMS?*
It is useful whenever space requirements could be problematic and exact results are not needed. The data structure is designed in a way that allows freely trading off accuracy and space requirements.


## Project Overview

With the Count-Min Sketch algorithm implementation, the search for the most frequently occurring elements (e.g. words, numbers, and chars) from the input data will be performed, also known as "K-Top Frequency".

The project built with general **Java core knowledge's:** 
- **OOP**
- **Collections**
- **Stream Api**
- **SOLID**
- **Dependency Injection**

## What will the application do?
The input data set is read from a text file, this file where words and symbols are stored, it is a simple book. 
The task is to find a certain number of the **most frequently repeated words** in the text and sort them in descending order.

The task must be broken down into certain steps:
<br>a) Read data from a text file. - 'input_file.txt'
<br>b) Words must be separated into a list or array using punctuation marks such as: “!;?,;,; ," etc.
<br>c) Some words that are obvious as articles, pronouns, prepositions, and others should be omitted, which means, removed from the main group of words that will be used for further processing.
Such words should be placed in a special file of such words - 'skip_words.txt'.
<br>d) All other application work that is being done.

The application will use next arguments:
   - input path: path to file with a textual file. 
   - k number: number of top frequent elements that we are looking for. 
   - m number: count-min sketch buffer size. 
   - p number: number of independent hash functions. 
   - c number: number of bits per counter.

## :floppy_disk:Project Files Description

<ul>
  <li><b>Main.java</b> - The main file. Where we are able to run an application.</li>
  <li><b>changeover package</b> - Where all methods calling are occurred.</li>
  <li><b>handler package</b> - Where an application handles different data types.</li>
  <li><b>hash package</b> - Where hash functions are executed.</li>
  <li><b>ktop package</b> - Where desired frequencies are counted.</li>
  <li><b>reader package</b> - Where input stream is read.</li>
  <li><b>service package</b> - Where main methods for CMS, data filtering, data sorting, data printing are executed.</li>
  <li><b>type package</b> - Where we have models of different data types.</li>
  <li><b>writer package</b> - Where data is written to the file.</li>
</ul>

## What is needed to run up this application

- JDK 11 (or higher)
- Maven Apache 3.6.0 (or higher). Maven installation can be omitted in case if start up is done from the main method.

## How to run this application on your computer
You are able to run an application with **two options**:

:heavy_plus_sign:First option:
Run this application using a main method.

:heavy_plus_sign:Second option:
Run this application using a terminal and appropriate commands.

In the both options, it will be proposed to you how do you prefer to start up an application:

- Auto mode
    - With default parameters
- Manual mode
    - You are able to input your personnel arguments


_In the both modes you will be proposed to write data to the file._

### :computer:Before starting up with any options.
First, you have to be sure that you have installed following components:
- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Apache Maven](https://maven.apache.org/download.cgi)
- Maven assembly plugin 2.6 (or higher) in the pom.xml.

:white_check_mark: Clone this project to your IDE as a Maven project.

:white_check_mark: Carefully check a pom.xml file if any errors have occurred - fix them.
    

#### If you have chosen the <u>first option</u>:
1) Open Main.java class.
2) Click on the "run" sign to start up an application.
3) Application has been started
4) Now, you have to follow application hints.

#### If you have chosen the <u>second option</u>:
1) Check in your CLI if maven was installed.
```
mvn -version
```
if you have a positive result you can continue with the manual mode, otherwise you have to install a Maven Apache.

2) Be sure that you are in the proper directory.
```
cd <directory name>
```
3) Use the following command to build up a jar file.
```
mvn install
```
4) Check if a **target** folder has been created.
```
dir or ls with git
```
5) Move into the /target directory.
```
cd target
```
6) Check if a **jar** was created.
```
dir or ls with git
```
You can copy the full name of the jar file, and recommended using a jar with dependencies.

7) Use the next command to run up an application. _Note_, you have to be in the /target directory to do that.
```
java -jar<full name of jar file>.jar
```
Now, application has been started. Then, from two proposal modes - choose a **manual mode**.

#### Which arguments can you use in the Manual mode :
- Input data file path. Choose a default file or enter the path of your personnel file.
- Skip word file path. Choose a default or input yours.
- Top frequent element. Input a number of elements which you are looking for.
- Count-Min Sketch buffer size. Input a number which represents the width of the sketch.
- Independent hash-functions. Input a number of hash-functions which represent a row quantity of the sketch.
- Bits per counter. Choose a data type which is used for the counter size of the sketch and hash function operation.

## What do you expect from the application?

The way of how the Count-Min Sketch is constructed, the result guarantees that the "best estimate" is either exactly equal to the true count or greater than the true count. 
The CMS guarantees that this "best estimate" will never be less than the real count of the certain element.

## Output example:
```
+------------------+------------------+------------------+------------------|
|       word       |     freq_ref     |   freq_approx    |     error %      |
+------------------+------------------+------------------+------------------|
|       Buck       |       360        |       366        |       1.67       |
|       dogs       |       118        |       144        |      22.03       |
|     Thornton     |       102        |       113        |      10.78       |
|    Gutenberg     |        84        |       102        |      21.43       |
|     Project      |        84        |        91        |       8.33       |
|      Spitz       |        65        |        89        |      36.92       |
|       dog        |        76        |        88        |      15.79       |
|       sled       |        63        |        85        |      34.92       |
|       men        |        69        |        83        |      20.29       |
|       Fran       |        60        |        75        |       25.0       |
+------------------+------------------+------------------+------------------|
```
Where:
  - **freq_ref** is the real quantity of the element.
  - **freq_approx** is the estimated quantity of a certain element.
  - Where **error** is ((freq_apprx - freq_ref) / freq_ref) * 100
