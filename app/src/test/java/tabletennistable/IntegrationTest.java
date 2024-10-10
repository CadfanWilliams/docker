package tabletennistable;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class IntegrationTest
{
    @Test
    public void testPrintsEmptyGame()
    {
        App app = CreateApp();

        Assert.assertEquals("No players yet", app.sendCommand("print"));
    }

    @Test
    public void testMultipleGamesBeingPlayedWhileSaving()
    {
        App app = CreateApp();
        FileService fileService = new FileService();

        League league1 = new League();

        league1.addPlayer("Alice");
        league1.addPlayer("Bob");
        league1.addPlayer("Charlie");
        league1.addPlayer("David");

        league1.recordWin("Bob", "Alice");
        league1.recordWin("David", "Charlie");

        fileService.save("app/src/test/java/tabletennistable", league1);

        League league2 = new League();
        league2.addPlayer("Ashley");
        league2.addPlayer("Brian");
        league2.addPlayer("Connor");
        league2.addPlayer("Daniel");

        league2.recordWin("Brian", "Ashley");
        league2.recordWin("Daniel", "Connor");

        fileService.save("app/src/test/java/tabletennistable", league2);

        fileService.load("app/src/test/java/tabletennistable");
        fileService.load("app/src/test/java/tabletennistable");

        Assert.assertEquals("Bob", league1.getWinner());
        Assert.assertEquals("Brian", league2.getWinner());



    }

    private App CreateApp()
    {
        return new App(new League(), new LeagueRenderer(), new FileService());
    }
}
