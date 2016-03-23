package co.iyubinest.tddpokedex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iyubinest on 3/22/16.
 */
public class TestFixtures {

  public static final String NAME = "Charmander";
  public static final int NUMBER = 1;
  public static final String IMAGE = BuildConfig.APP_URL + BuildConfig.IMAGE_PATH + NUMBER;
  public static final List<String> ABILITIES = new ArrayList<>();

  static {
    ABILITIES.add("Atack 1");
    ABILITIES.add("Atack 2");
    ABILITIES.add("Atack 3");
    ABILITIES.add("Atack 4");
  }

  public static List<Pokemon> get() {
    List<Pokemon> pokemons = new ArrayList<>();
    for (int i = 0; i < 5; i++) pokemons.add(Pokemon.create(NAME, i, ABILITIES));
    return pokemons;
  }
}
