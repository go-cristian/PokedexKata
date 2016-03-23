package co.iyubinest.tddpokedex.list;

import co.iyubinest.tddpokedex.Pokemon;
import java.util.List;

/**
 * Created by iyubinest on 3/21/16.
 */
public interface PokemonListView {
  void show(List<Pokemon> pokemons);

  void retry();
}
