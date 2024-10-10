package tabletennistable;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AppTest {
    @Mock
    LeagueRenderer renderer;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPrintsCurrentState()
    {
        initMocks();
        League league = new League();
        Mockito.when(renderer.render(league)).thenReturn("Rendered League");

        App app = new App(league, renderer, null);

        Assert.assertEquals("Rendered League", app.sendCommand("print"));
    }
}
