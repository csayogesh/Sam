package app.interfaces;

import app.Dimension;

public class CreateDimension extends Interface {
    private Dimension dimension;

    public CreateDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public void takeAction() {
        System.out.println("Enter Dimension Name: ");
        sc.nextLine();
        String name = sc.nextLine();
        dimension.addDimension(name);
    }

    @Override
    public void displaySpecificMenuOptions() {
    }
}
