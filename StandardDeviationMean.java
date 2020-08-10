public class StandardDeviationMean {

    // necessary for calculating mean & standard deviation
    private double sumOfAllData = 0, numberOfData = 0, sumOfXMinusMeanWholeSquare = 0;

    // sample, mean, standard deviation
    private double X = 0, mean = 0, standardDeviation = 0;

    public StandardDeviationMean() {
    }

    public void addSample(double X){
        // update the mean & standard deviation with the new sample

        this.X = X;

        // mean calculation
        sumOfAllData+=X;
        numberOfData++;
        mean = sumOfAllData/numberOfData;

        // standard deviation calculation
        sumOfXMinusMeanWholeSquare += (X-mean)*(X-mean);
        standardDeviation = Math.sqrt(sumOfXMinusMeanWholeSquare/numberOfData);

    }


    public double getSumOfAllData() {
        return sumOfAllData;
    }

    public double getNumberOfData() {
        return numberOfData;
    }

    public double getSumOfXMinusMeanWholeSquare() {
        return sumOfXMinusMeanWholeSquare;
    }

    public double getX() {
        return X;
    }

    public double getMean() {
        return mean;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }
}
