package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PersistanceStorage {
    private static String dimensionsFile = "data/dimensions";

    public static void storeDimensions(Dimension dimension) throws IOException {
        if (dimension != null) {
            PrintWriter pw = new PrintWriter(new File(dimensionsFile + "_temp"));
            while (dimension.getParent() != null)
                dimension = dimension.getParent();
            storeAllChildNodes(pw, dimension);
            pw.flush();
            pw.close();
            File dims = new File(dimensionsFile);
            if (dims.exists())
                dims.delete();
            dims = new File(dimensionsFile + "_temp");
            if (dims.exists())
                dims.renameTo(new File(dimensionsFile));
        }
    }

    private static void storeAllChildNodes(PrintWriter fw, Dimension dimension) {
        if (dimension.getSubDimension().size() == 0) {
            fw.println(dimension.getId());
            return;
        }
        for (Dimension subDim : dimension.getSubDimension())
            storeAllChildNodes(fw, subDim);
    }

    public static Dimension getRootDimension() throws FileNotFoundException {
        Dimension rootDimension = new Dimension("root");
        Scanner sc = new Scanner(new File(dimensionsFile));
        while (sc.hasNext()) {
            String line = sc.nextLine();
            Dimension dim = rootDimension;
            String[] path = line.split("\\.");
            for (int i = 1; i < path.length; i++) {
                Dimension matching = dim.getMatchingSubDimension(path[i]);
                if (matching == null) {
                    matching = dim.addDimension(path[i]);
                }
                dim = matching;
            }
        }
        return rootDimension;
    }
}
