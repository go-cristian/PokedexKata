package co.iyubinest.tddpokedex.list;

import co.iyubinest.tddpokedex.Pokemon;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iyubinest on 3/21/16.
 */
public class PokemonListPresenter {
  private final PokemonListView view;
  private final PokemonListInteractor interactor;
  private final List<Pokemon> requestedPokemons = new ArrayList<>();

  public PokemonListPresenter(PokemonListView view, PokemonListInteractor interactor) {
    this.view = view;
    this.interactor = interactor;
  }

  public void requestPokemons() {
    interactor.getPokemons(new PokemonListInteractorCallback() {
      @Override public void success(List<Pokemon> pokemons) {
        requestedPokemons.addAll(pokemons);
        view.show(requestedPokemons);
      }

      @Override public void fail() {
        view.retry();
      }
    });
  }
}
