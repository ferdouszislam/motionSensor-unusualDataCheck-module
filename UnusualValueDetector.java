public class UnusualValueDetector {

    // object responsible for calculating standard deviation and mean
    private StandardDeviationMean standardDeviationMean;

    // state of last received value/sample
    private boolean isValueUnusual = false;

    // number of data used to calculate initial standard deviation & mean
    // (this is connected to how much time you want to wait initially before starting to detect)
    private final int initialSampleCount = 0;

    // to respond to unusual value detection
    private unusualValueResponse unusualValueResponse=null;

    public UnusualValueDetector(StandardDeviationMean standardDeviationMean) {
        // using the 'UnusualValueDetector' object without responder assigned

        this.standardDeviationMean = standardDeviationMean;
    }

    public UnusualValueDetector(StandardDeviationMean standardDeviationMean,
                                UnusualValueDetector.unusualValueResponse unusualValueResponse
    ) {
        this.standardDeviationMean = standardDeviationMean;

        // assigning the responder class
        this.unusualValueResponse = unusualValueResponse;
    }

    public void analyzeMotionSensorData(double x, double y, double z){
        // calculate if current data(resultant) is unusual or not

        // resultant of three axis-wise vectors
        double resultant = Math.sqrt(x*x + y*y + z*z);

        // the resultants are the samples for mean & standard deviation calculation
        standardDeviationMean.addSample(resultant);

        // let mean & standard deviation get calculated with some initial data before starting to detect
        if(standardDeviationMean.getNumberOfData()<initialSampleCount)
            return ;

        if(     // X > (Mean + StandardDeviation)
                isGreater(standardDeviationMean.getX(),
                        standardDeviationMean.getMean()+standardDeviationMean.getStandardDeviation())
                ||
                // X < (Mean - StandardDeviation)
                isGreater(standardDeviationMean.getMean()-standardDeviationMean.getStandardDeviation(),
                        standardDeviationMean.getX())
        ) {

            isValueUnusual = true;

            // call the response method if responder was assigned
            if(unusualValueResponse!=null)
                unusualValueResponse.unusualValueDetected();

        }

        else
            isValueUnusual = false;
    }

    private boolean isGreater(double a, double b){
        // proper way to check inequality for 'double' datatype

        double EPS = 1e-9;
        return (a-b) > EPS; // true iff a>b
    }


    // getters & setters
    public StandardDeviationMean getStandardDeviationMean() {
        return standardDeviationMean;
    }

    public boolean isValueUnusual() {
        return isValueUnusual;
    }



    // implement this interface to respond to unusual value detection
    public interface unusualValueResponse{

        // invoked by the implementer when unusual value is detected
        void unusualValueDetected();

    }
}
