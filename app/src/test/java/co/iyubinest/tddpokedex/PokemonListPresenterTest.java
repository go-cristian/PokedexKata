package co.iyubinest.tddpokedex;

import co.iyubinest.tddpokedex.list.PokemonListInteractor;
import co.iyubinest.tddpokedex.list.PokemonListInteractorCallback;
import co.iyubinest.tddpokedex.list.PokemonListPresenter;
import co.iyubinest.tddpokedex.list.PokemonListView;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by iyubinest on 3/21/16.
 */
public class PokemonListPresenterTest implements PokemonListView {

  private static final String FINISHED = "FINISHED";
  private static final String RETRY = "RETRY";
  private static final List<Pokemon> POKEMONS = TestFixtures.get();

  private PokemonListPresenter presenter;

  private String state;

  private List<Pokemon> statePokemons;

  @Test public void testShowLoading() {
    givenPresenter();
    whenRequestingData();
    thenLoadingViewIsShown();
  }

  @Test public void testShowPokemons() {
    givenPresenterWithPokemons();
    whenRequestingData();
    thenPokemonsAreRequested();
  }

  @Test public void testShowFirstLoadErrorShowsRetry() {
    givenPresenterWithNoPokemons();
    whenRequestingData();
    thenShowsRetryView();
  }

  @Test public void testShowPokemonsSecondPage() {
    givenPresenterWithPokemons();
    whenRequestingData();
    whenRequestingData();
    thenPokemonsAreRequestedTwice();
  }

  private void givenPresenter() {
    presenter = new PokemonListPresenter(this, getNoResponseInteractor());
  }

  private void givenPresenterWithPokemons() {
    presenter = new PokemonListPresenter(this, getSuccessInteractor());
  }

  private void givenPresenterWithNoPokemons() {
    presenter = new PokemonListPresenter(this, getNoPokemonsInteractor());
  }

  private void whenRequestingData() {
    presenter.requestPokemons();
  }

  private void thenLoadingViewIsShown() {
    Assert.assertNull(state);
    Assert.assertNull(statePokemons);
  }

  private void thenPokemonsAreRequested() {
    Assert.assertEquals(FINISHED, state);
    Assert.assertEquals(POKEMONS, statePokemons);
  }

  private void thenShowsRetryView() {
    Assert.assertEquals(RETRY, state);
    Assert.assertNull(statePokemons);
  }

  private void thenPokemonsAreRequestedTwice() {
    Assert.assertEquals(FINISHED, state);
    ArrayList<Pokemon> expected = new ArrayList<>();
    expected.addAll(POKEMONS);
    expected.addAll(POKEMONS);
    Assert.assertEquals(expected, statePokemons);
  }

  private PokemonListInteractor getSuccessInteractor() {
    return new PokemonListInteractor() {
      @Override public void getPokemons(PokemonListInteractorCallback callback) {
        callback.success(POKEMONS);
      }
    };
  }

  private PokemonListInteractor getNoResponseInteractor() {
    return new PokemonListInteractor() {
      @Override public void getPokemons(PokemonListInteractorCallback callback) {
      }
    };
  }

  private PokemonListInteractor getNoPokemonsInteractor() {
    return new PokemonListInteractor() {
      @Override public void getPokemons(PokemonListInteractorCallback callback) {
        callback.fail();
      }
    };
  }

  @Override public void show(List<Pokemon> pokemons) {
    state = FINISHED;
    statePokemons = pokemons;
  }

  @Override public void retry() {
    state = RETRY;
  }
}
