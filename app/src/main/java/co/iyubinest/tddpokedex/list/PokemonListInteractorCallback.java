package co.iyubinest.tddpokedex.list;

import co.iyubinest.tddpokedex.Pokemon;
import java.util.List;

/**
 * Created by iyubinest on 3/22/16.
 */
public interface PokemonListInteractorCallback {
  void success(List<Pokemon> pokemons);

  void fail();
}
