package Client.Models;

import java.io.Serializable;

public class SpecialFeature implements Serializable {
    private int specialFeatureInt;
    private String specialFeatureString;

    public String StringOrInt(){
        if(specialFeatureString == null)
            return "int";
        else
            return "String";

    }

    public SpecialFeature(String specialFeatureString) {
        try {
            this.specialFeatureInt = Integer.parseInt(specialFeatureString);
        } catch (NumberFormatException e) {
            this.specialFeatureString = specialFeatureString;
        }
    }

    public void setSpecialFeatureInt(int specialFeatureInt) {
        this.specialFeatureInt = specialFeatureInt;
    }

    public void setSpecialFeatureString(String specialFeatureString) {
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
