package Client.View.Menus;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public enum MessageTypeShow {

    INFORMATION("information"),ERROR("error"),SUCCESS("success");

    private LinearGradient linearGradient;

    MessageTypeShow(String mode){
        Stop[] stops;
        switch (mode){
            case "information": {
                stops = new Stop[]{new Stop(0, Color.BLACK), new Stop(1, Color.valueOf("#dd813f"))};
                linearGradient = new LinearGradient(0.7386091127098321, 0.0, 0.15347721822541963, 1, true, CycleMethod.NO_CYCLE, stops);
                break;
            }
            case "error": {
                stops = new Stop[]{new Stop(0, Color.BLACK), new Stop(1, Color.RED)};
                linearGradient = new LinearGradient(0.7386091127098321, 0.0, 0.15347721822541963, 1.0, true, CycleMethod.NO_CYCLE, stops);
                break;
            }
            case "success": {
                stops = new Stop[]{new Stop(0, Color.valueOf("#0c2412")), new Stop(1, Color.valueOf("#0f8032"))};
                linearGradient = new LinearGradient(0.7386091127098321, 0.0, 0.15347721822541963, 1.0, true, CycleMethod.NO_CYCLE, stops);
                break;
            }
        }

    }

    public LinearGradient getLinearGradient() {
        return linearGradient;
    }
}


