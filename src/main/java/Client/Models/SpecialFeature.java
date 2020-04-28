package Client.Models;

public class SpecialFeature {
    int specialFeatureInt;
    String specialFeatureString;
    public String StringOrInt(){
        if(specialFeatureString == null)
            return "int";
        else
            return "String";

    }

    public SpecialFeature(int specialFeatureInt) {
        this.specialFeatureInt = specialFeatureInt;
    }

    public SpecialFeature(String specialFeatureString) {
        this.specialFeatureString = specialFeatureString;
    }

    public int getSpecialFeatureInt() {
        return specialFeatureInt;
    }

    public String getSpecialFeatureString() {
        return specialFeatureString;
    }

    @Override
    public String toString() {
        if(StringOrInt().equals("String")){
            return specialFeatureString;
        }
        else
            return Integer.toString(specialFeatureInt);
    }
}
