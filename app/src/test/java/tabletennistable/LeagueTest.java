package tabletennistable;

import org.assertj.core.api.Assertions;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LeagueTest {
    @Test
    public void testAddPlayer() {
        // Given
        League league = new League();

        // When
        league.addPlayer("Bob");

        //then
        Assert.assertEquals(1, league.getRows().size());
        List<String> firstRowPlayers = league.getRows().get(0).getPlayers();
        Assert.assertEquals(1, firstRowPlayers.size());
        Assert.assertThat(firstRowPlayers, IsCollectionContaining.hasItem("Bob"));
    }

    @Test
    public void testAddDuplicatePlayer() {
        // Given
        League league = new League();

        // When
        league.addPlayer("Bob");

        // Then
        Assertions.assertThatThrownBy(() -> league.addPlayer("Bob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot add player Bob because they are already in the game");
    }

    @Test
    public void testGetRows() {
        //Given
        League league = new League();

        //When
        //6 PLayers are added to the league

        league.addPlayer("Bob");
        league.addPlayer("Alice");
        league.addPlayer("Charlie");
        league.addPlayer("David");
        league.addPlayer("Eve");
        league.addPlayer("Frank");

        //Then
        //There should be three rows

        Assert.assertEquals(3, league.getRows().size());
    }

    @Test
    public void testRecordWin() {
        //Given
        League league = new League();

        //When
        league.addPlayer("Bob");
        league.addPlayer("Alice");
        league.addPlayer("Charlie");
        league.addPlayer("David");

        league.recordWin("Alice", "Bob");
        league.recordWin("Charlie", "Alice");
        league.recordWin("Alice", "Charlie");
        league.recordWin("David", "Bob");

        //then
        //Alice will be first and bob will be last
        Assert.assertEquals("Alice", league.getRows().get(0).getPlayers().get(0));
        Assert.assertEquals("Bob", league.getRows().get(2).getPlayers().get(0));
    }

    @Test
    public void testRecordWinTwoRowsApartExpectError() {
        //Given
        League league = new League();

        //When
        league.addPlayer("Bob");
        league.addPlayer("Alice");
        league.addPlayer("Charlie");
        league.addPlayer("David");

        league.recordWin("Alice", "Bob");
        league.recordWin("Charlie", "Alice");
        league.recordWin("Alice", "Charlie");

        Assertions.assertThatThrownBy(() -> league.recordWin("David", "Alice"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot record match result. Winner David must be one row below loser Alice");

    }

    @Test
    public void testGetWinner() {
        //Given
        League league = new League();

        //When
        league.addPlayer("Bob");
        league.addPlayer("Alice");

        league.recordWin("Alice", "Bob");

        //then

        Assert.assertEquals("Alice", league.getWinner());
    }
}
