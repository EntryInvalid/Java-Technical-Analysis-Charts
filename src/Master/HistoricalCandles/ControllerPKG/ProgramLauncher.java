package Master.HistoricalCandles.ControllerPKG;

// Initializes the ControllerPKG to start the program from a dedicated file
public class ProgramLauncher {

    private Controller controller;
    private ProgramLauncher launcher;

    public void Launch () {
        launcher = new ProgramLauncher();
        this.controller = new Controller(launcher);
    }

}