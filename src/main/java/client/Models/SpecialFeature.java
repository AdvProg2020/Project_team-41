package Client.Models;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialFeature that = (SpecialFeature) o;
        return specialFeatureInt == that.specialFeatureInt &&
                Objects.equals(specialFeatureString, that.specialFeatureString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialFeatureInt, specialFeatureString);
    }
}
