package co.iyubinest.tddpokedex;

import co.iyubinest.tddpokedex.detail.PokemonDetailPresenter;
import co.iyubinest.tddpokedex.detail.PokemonDetailView;
import junit.framework.Assert;
import org.junit.Test;

import static co.iyubinest.tddpokedex.TestFixtures.ABILITIES;
import static co.iyubinest.tddpokedex.TestFixtures.IMAGE;
import static co.iyubinest.tddpokedex.TestFixtures.NAME;
import static co.iyubinest.tddpokedex.TestFixtures.NUMBER;

/**
 * Created by iyubinest on 3/22/16.
 */
public class PokemonDetailPresenterTest implements PokemonDetailView {

  private PokemonDetailPresenter presenter;
  private Pokemon statePokemon;
  private boolean error;

  @Test public void testInitPresenter() {
    givenPresenterWithPokemon();
    //whenNoAction
    thenShowsPokemonInfo();
  }

  private void thenShowsPokemonInfo() {
    Assert.assertEquals(NAME, statePokemon.getName());
    Assert.assertEquals(NUMBER, statePokemon.getNumber());
    Assert.assertEquals(IMAGE, statePokemon.getImage());
    Assert.assertEquals(ABILITIES, statePokemon.getAbilities());
  }

  @Test public void testInitPresenterFailure() {
    givenPresenterWithNoPokemon();
    //whenNoAction
    thenClosesView();
  }

  private void thenClosesView() {
    Assert.assertTrue(error);
    Assert.assertNull(statePokemon);
  }

  private void givenPresenterWithNoPokemon() {
    presenter = new PokemonDetailPresenter(this, null);
  }

  private void givenPresenterWithPokemon() {
    presenter = new PokemonDetailPresenter(this, Pokemon.create(NAME, NUMBER, ABILITIES));
  }

  @Override public void show(Pokemon pokemon) {
    statePokemon = pokemon;
  }

  @Override public void close() {
    error = true;
  }
}
