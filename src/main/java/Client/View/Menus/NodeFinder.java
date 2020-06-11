package Client.View.Menus;

import javafx.scene.Node;
import javafx.scene.Parent;

public class NodeFinder {
    public static Node getParentById(Node childNode,String fxId) {
        Node node = childNode;
        while(node != null){
            if(node.getId() != null && node.getId().equals(fxId))
                return node;
            else
                node = node.getParent();
        }
        return null;
    }

    public static Node getChildById(Parent parentNode,String fxId){
        Node foundNode = null;
        for (Node node : parentNode.getChildrenUnmodifiable()) {
            if(node.getId() != null && node.getId().equals(fxId))
                return node;
            else {
                if (node instanceof Parent)
                    foundNode = getChildById((Parent) node, fxId);
            }
            if(foundNode != null){
                return foundNode;
            }
        }
        return null;
    }
}
