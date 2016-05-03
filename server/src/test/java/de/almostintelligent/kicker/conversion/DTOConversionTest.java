package de.almostintelligent.kicker.conversion;

import de.almostintelligent.kicker.api.dto.PlayerDTO;
import de.almostintelligent.kicker.model.Account;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DTOConversionTest {

    @Test
    public void shouldCreatePlayerDTO() {
        Account account = new Account();
        account.setName("name");
        account.setEmail("email");
        account.setId("someid");

        PlayerDTO playerDTO = new PlayerDTO(account);

        assertThat(playerDTO.getPlayerId(), is("someid"));
        assertThat(playerDTO.getName(), is("name"));
        assertThat(playerDTO.getEmail(), is("email"));

    }

}
