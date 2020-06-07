package Client.View.Menus;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public enum MessageType {

    INFORMATION("information"),ERROR("error"),SUCCESS("success");

    private LinearGradient linearGradient;

    MessageType(String mode){
        Stop[] stops;
        switch (mode){
            case "information": {
                stops = new Stop[]{new Stop(0, Color.BLACK), new Stop(1, Color.valueOf("#dd813f"))};
                linearGradient = new LinearGradient(0.22541966426858512, 0.0, 0.2158273381294964, 0.9760191846522782, true, CycleMethod.NO_CYCLE, stops);
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



//  <LinearGradient endX="0.15347721822541963" endY="1.0" startX="0.7386091127098321">
//                  <stops>
//                      <Stop>
//                     <color>
//                        <Color red="0.0470588244497776" green="0.1411764770746231" blue="0.07058823853731155" />
//                     </color></Stop>
//                      <Stop offset="1.0">
//                     <color>
//                        <Color red="1.0" />
//                     </color></Stop>
//                  </stops>
//              </LinearGradient>




//<LinearGradient endX="0.2158273381294964" endY="0.9760191846522782" startX="0.22541966426858512">
//                   <stops>
//                       <Stop color="BLACK" />
//                       <Stop color="#dd813f" offset="1.0" />
//                   </stops>
//               </LinearGradient>