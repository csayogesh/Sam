package app;

import generic.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Dimension {
    private double value = -1;
    private Map<String, Dimension> subDimension = new HashMap<>();

    public static void main(String[] args) {
        Dimension root = new Dimension();
        root.insertDimension("MPSC", 3);
        root.insertDimension("Book", 3);
        root.insertDimension("Job Search.System Design.Coding", 3);
        root.insertDimension("Job.Work", 3);
        root.deleteDimension("Job.Work");
        System.out.println();
    }

    private void insertDimension(String dimension, double target) {
        if (!StringUtils.isSet(dimension)) {
            value = target;
            return;
        }
        int dotIndex = dimension.indexOf('.');
        String id = dimension;
        String remainingDimension = "";
        if (dotIndex != -1) {
            id = dimension.substring(0, dotIndex);
            remainingDimension = dimension.substring(dotIndex + 1);
        }
        Dimension dimObject = subDimension.getOrDefault(id, new Dimension());
        subDimension.putIfAbsent(id, dimObject);
        dimObject.insertDimension(remainingDimension, target);
    }

    private void insertImmediateChild(String leafDimension, double target) {
        if (StringUtils.isSet(leafDimension)) {
            Dimension dimObj = subDimension.getOrDefault(leafDimension, new Dimension());
            dimObj.value = target;
            subDimension.putIfAbsent(leafDimension, dimObj);
        }
    }

    private String[] splitChildDimension(String dimension) {
        int lastDotIndex = dimension.lastIndexOf('.');
        String parent = "";
        String child = dimension;
        if (lastDotIndex != -1) {
            parent = dimension.substring(0, lastDotIndex);
            child = dimension.substring(lastDotIndex + 1);
        }
        return new String[]{parent, child};
    }

    private Dimension getParentDimension(String dimension) {
        String[] parChild = splitChildDimension(dimension);
        if (StringUtils.isSet(parChild[0]))
            return getDimension(parChild[0]);
        return this;
    }

    private Dimension getDimension(String dimension) {
        if ("".equals(dimension)) return this;
        if (!StringUtils.isSet(dimension)) return null;

        int dotIndex = dimension.indexOf('.');
        String id = dimension;
        String remainingDimension = "";
        if (dotIndex != -1) {
            id = dimension.substring(0, dotIndex);
            remainingDimension = dimension.substring(dotIndex + 1);
        }
        Dimension dimObject = subDimension.getOrDefault(id, null);
        if (!StringUtils.isSet(remainingDimension) || dimObject == null)
            return dimObject;
        return dimObject.getDimension(remainingDimension);
    }

    private void deleteDimension(String dimension) {
        Dimension parDim = getParentDimension(dimension);
        if (parDim != null)
            parDim.deleteImmediateChild(splitChildDimension(dimension)[1]);
    }

    private void deleteImmediateChild(String childDimension) {
        subDimension.remove(childDimension);
    }
}
