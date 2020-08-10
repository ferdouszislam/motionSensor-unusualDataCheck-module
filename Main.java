import java.util.ArrayList;

public class Main{

    // to hold dummy data
    static ArrayList<Double[]> sensorData = new ArrayList<>();

    static UnusualValueDetector unusualValueDetector;

    public static void main(String[] args) {

        initializeDummyData();

        unusualValueDetector = new UnusualValueDetector(new StandardDeviationMean());

        sensorData.forEach( (value) -> {

            double x = value[0], y = value[1], z = value[2];

            unusualValueDetector.analyzeMotionSensorData(x, y, z);

            if(unusualValueDetector.isValueUnusual())
                System.out.println("unusual value = "
                        + Math.floor(unusualValueDetector.getResultant()*100)/100
                        +" [this is usage of 'UnusualValueDetector' class without any responder]"
                );

        });

    }

    public static void initializeDummyData(){

        // add some dummy data, this is supposed to come from sensor
        for(int i=0;i<10;i++) {

            double x = Math.random()*10;
            double y = Math.random()*10;
            double z = Math.random()*10;

            sensorData.add( new Double[] {x, y, z} );
        }
    }
}
