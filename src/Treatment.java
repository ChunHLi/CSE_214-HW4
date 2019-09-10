import java.util.Comparator;

/**
 * @author Ritwik Banerjee
 */
public class Treatment {

    String name;
    String diseaseTreated;
    double probabilityOfSuccess;
    double pricePerUnit;

    public Treatment(String name, String diseaseTreated, double probabilityOfSuccess, double pricePerUnit){
        this.name = name;
        this.diseaseTreated = diseaseTreated;
        this.probabilityOfSuccess = probabilityOfSuccess;
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public int hashCode() {
        int  result;
        long temp;
        result = name.hashCode();
        result = 31 * result + diseaseTreated.hashCode();
        temp = Double.doubleToLongBits(probabilityOfSuccess);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pricePerUnit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString(){
        return "Treatment{name='" + this.name + "', " +
                "diseaseTreated:'" + this.diseaseTreated + "', " +
                "probabilityOfSuccess:" + this.probabilityOfSuccess + ", " +
                "pricePerUnit:" + this.pricePerUnit + "}";
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof Treatment)){
            return false;
        }
        Treatment typeCast = (Treatment)other;
        return this.name == typeCast.name &&
                this.diseaseTreated == typeCast.diseaseTreated &&
                this.probabilityOfSuccess == typeCast.probabilityOfSuccess &&
                this.pricePerUnit == typeCast.pricePerUnit;
    }

    public static class PriceBasedTreatmentComparator implements Comparator<Treatment>{
        public PriceBasedTreatmentComparator(){};
        @Override
        public int compare(Treatment a, Treatment b){
            if (a.pricePerUnit < b.pricePerUnit){
                return 1;
            }
            else if (a.pricePerUnit == b.pricePerUnit){
                return 0;
            }
            else{
                return -1;
            }
        }
    }

    public static class SuccessBasedTreatmentComparator implements Comparator<Treatment>{

        public SuccessBasedTreatmentComparator(){};
        @Override
        public int compare(Treatment a, Treatment b){
            if (a.probabilityOfSuccess > b.probabilityOfSuccess){
                return 1;
            }
            else if (a.probabilityOfSuccess == b.probabilityOfSuccess){
                return 0;
            }
            else{
                return -1;
            }
        }
    }
}