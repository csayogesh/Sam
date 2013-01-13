package app;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class PersistanceStorage {
    public static void storeDimensions(Dimension dimension) throws IOException {
        if (dimension != null) {
            String fileName = "data/dimensions";
            File dims = new File(fileName);
            if (dims.exists())
                dims.delete();
            PrintWriter pw = new PrintWriter(new File(fileName));
            while (dimension.getParent() != null)
                dimension = dimension.getParent();
            storeAllChildNodes(pw, dimension);
            pw.flush();
            pw.close();
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
}
