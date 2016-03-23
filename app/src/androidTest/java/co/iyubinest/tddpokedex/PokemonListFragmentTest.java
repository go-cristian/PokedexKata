package co.iyubinest.tddpokedex;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import co.iyubinest.tddpokedex.common.NavigationActivity;
import co.iyubinest.tddpokedex.list.PokemonListFragment;
import co.iyubinest.tddpokedex.list.PokemonListInteractor;
import co.iyubinest.tddpokedex.list.PokemonListInteractorCallback;
import co.iyubinest.tddpokedex.list.PokemonListPresenter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class PokemonListFragmentTest {
  @Rule public final ActivityTestRule<NavigationActivity> mActivityRule;
  protected NavigationActivity activity;
  private PokemonListFragment fragment;

  public PokemonListFragmentTest() {
    this.mActivityRule = new ActivityTestRule<>(NavigationActivity.class);
  }

  @Before public void setup() throws InterruptedException {
    activity = mActivityRule.getActivity();
    fragment = (PokemonListFragment) activity.getLastFragment();
  }

  @Test public void testShowLoading() {
    onView(withId(R.id.loading)).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    onView(withId(R.id.loading_text)).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        .check(matches(withText(activity.getString(R.string.loading))));
    onView(withId(R.id.retry)).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    onView(withId(R.id.pokemons)).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
  }

  /*@Test public void testShowDetail() {
    fragment.setPresenter(new PokemonListPresenter(fragment, new PokemonListInteractor() {
      @Override public void getPokemons(PokemonListInteractorCallback callback) {
        callback.success(TestFixtures.getPokemons());
      }
    }));
    onData(anything()).inAdapterView(withId(R.id.pokemons)).atPosition(0).perform(click());
    onView(withId(R.id.pokemon_detail)).check(matches(isDisplayed()));
    onView(withId(R.id.pokemons)).check(matches(isDisplayed()));
  }*/
/*  @Test public void testShowPokemons() {
    fragment.setPresenter(new PokemonListPresenter(fragment, new PokemonListInteractor() {
      @Override public void getPokemons(PokemonListInteractorCallback callback) {
        callback.success(TestFixtures.getPokemons());
      }
    }));
    onView(withId(R.id.pokemons)).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        .check(matches(withPokemonListSize(SIZE)));
    onView(withId(R.id.loading)).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    onView(withId(R.id.retry_button)).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
  }*/

  @Test public void testShowRetry() throws InterruptedException {
    activity.runOnUiThread(new Runnable() {
      @Override public void run() {
        fragment.setPresenter(new PokemonListPresenter(fragment, new PokemonListInteractor() {
          @Override public void getPokemons(PokemonListInteractorCallback callback) {
            callback.fail();
          }
        }));
      }
    });
    Thread.sleep(2000);
    onView(withId(R.id.retry)).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    onView(withId(R.id.retry_button)).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        .check(matches(withText(activity.getString(R.string.retry))));
    onView(withId(R.id.loading)).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    onView(withId(R.id.pokemons)).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
  }
}
