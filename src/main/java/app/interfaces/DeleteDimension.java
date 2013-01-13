package app.interfaces;

import app.Dimension;

public class DeleteDimension extends Interface {
    private final Dimension dimension;

    public DeleteDimension(Dimension dimension) {
        this.dimension = dimension;
        continueInput = false;
    }

    @Override
    public boolean takeAction() {
        return dimension.deleteDimension();
    }

    @Override
    public void displaySpecificMenuOptions() {
    }
}
