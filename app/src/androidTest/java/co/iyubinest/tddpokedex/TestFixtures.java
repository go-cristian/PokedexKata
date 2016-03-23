package co.iyubinest.tddpokedex;

import android.view.View;
import co.iyubinest.tddpokedex.list.ui.PokemonsView;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by iyubinest on 3/22/16.
 */
public class TestFixtures {
  private static final String NAME = "Pokemon ";
  private static final List<String> ABILITIES = new ArrayList<>();
  public static final int SIZE = 10;

  static {
    for (int i = 0; i < 4; i++) ABILITIES.add("Ability " + i);
  }

  public static List<Pokemon> getPokemons() {
    List<Pokemon> pokemons = new ArrayList<>();
    for (int i = 0; i < SIZE; i++) pokemons.add(Pokemon.create(NAME + i, i, ABILITIES));
    return pokemons;
  }

  public static Matcher<? super View> withPokemonListSize(final int size) {
    return new TypeSafeMatcher<View>() {

      @Override protected boolean matchesSafely(View view) {
        return ((PokemonsView) view).getCount() == size;
      }

      @Override public void describeTo(Description description) {
        description.appendText("List should show " + size + " Pokemons");
      }
    };
  }
}
