package co.iyubinest.tddpokedex.detail;

import co.iyubinest.tddpokedex.Pokemon;

/**
 * Created by iyubinest on 3/22/16.
 */
public class PokemonDetailPresenter {

  public PokemonDetailPresenter(PokemonDetailView view, Pokemon pokemon) {
    if (pokemon == null) {
      view.close();
    } else {
      view.show(pokemon);
    }
  }
}
