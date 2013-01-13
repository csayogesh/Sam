package app.interfaces;

import app.Dimension;

public class CreateDimension extends Interface {
    private Dimension dimension;

    public CreateDimension(Dimension dimension) {
        this.dimension = dimension;
        continueInput = false;
    }

    @Override
    public boolean takeAction() {
        System.out.println("Enter Dimension Name: ");
        sc.nextLine();
        String name = sc.nextLine();
        dimension.addDimension(name);
        return false;
    }

    @Override
    public void displaySpecificMenuOptions() {
    }
}
