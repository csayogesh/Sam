package app;

import generic.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Dimension {
    private double value = -1;
    private Map<String, Dimension> subDimension = new HashMap<>();

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

    public static void main(String[] args) {
        Dimension root = new Dimension();
        root.insertDimension("MPSC",3);
        root.insertDimension("Book",3);
        root.insertDimension("Job Search.System Design.Coding",3);
        root.insertDimension("Job.Work",3);
        System.out.println();
    }
}
