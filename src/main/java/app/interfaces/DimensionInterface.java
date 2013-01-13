package app.interfaces;

import app.Dimension;
import app.PersistanceStorage;

import java.io.IOException;
import java.util.List;

public class DimensionInterface extends Interface {
    private Dimension dimension;

    private DimensionInterface(Dimension dimension) {
        this.dimension = dimension;
    }

    public static void main(String[] args) throws IOException {
        new DimensionInterface(PersistanceStorage.getRootDimension()).handleUserInput();
    }

    @Override
    public boolean takeAction() {
        return false;
    }

    @Override
    public void displaySpecificMenuOptions() {
        actions.add(new CreateDimension(dimension));
        System.out.println(actions.size() + ": Add Dimension");
        actions.add(new DeleteDimension(dimension));
        System.out.println(actions.size() + ": Delete Dimension");
        System.out.println();

        List<Dimension> subDims = dimension.getSubDimension();
        for (Dimension dimension : subDims) {
            actions.add(new DimensionInterface(dimension));
            System.out.println(this.actions.size() + ": Select " + dimension.getRawId());
        }
    }
}
