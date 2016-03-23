package co.iyubinest.tddpokedex.detail;

import co.iyubinest.tddpokedex.Pokemon;

/**
 * Created by iyubinest on 3/22/16.
 */
public interface PokemonDetailView {

  void show(Pokemon pokemon);

  void close();
}
