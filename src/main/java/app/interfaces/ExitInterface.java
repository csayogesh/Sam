package app.interfaces;

public class ExitInterface extends Interface {
    @Override
    public void displaySpecificMenuOptions() {
    }

    public static void main(String[] args) {
        new ExitInterface().handleUserInput();
    }
}
