package co.iyubinest.tddpokedex.common;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import co.iyubinest.tddpokedex.R;
import co.iyubinest.tddpokedex.list.PokemonListFragment;

public class NavigationActivity extends AppCompatActivity {
  private BaseFragment lastFragment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_navigation);
    add(new PokemonListFragment());
  }

  public void add(BaseFragment fragment) {
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
      fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
          R.anim.slide_in_left, R.anim.slide_out_right);
    }
    if (lastFragment == null || !lastFragment.getTitle().equals(fragment.getTitle())) {
      lastFragment = fragment;
      fragmentTransaction.replace(R.id.content, fragment)
          .addToBackStack(fragment.getTitle())
          .commit();
    }
  }

  @Override public void onBackPressed() {
    if (getSupportFragmentManager().getBackStackEntryCount() == 1) finish();
    super.onBackPressed();
  }

  public BaseFragment getLastFragment() {
    return lastFragment;
  }
}
