package co.iyubinest.tddpokedex.detail;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import co.iyubinest.tddpokedex.Pokemon;
import co.iyubinest.tddpokedex.R;
import co.iyubinest.tddpokedex.common.BaseFragment;

/**
 * Created by iyubinest on 3/22/16.
 */
public class PokemonDetailFragment extends BaseFragment {
  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.pokemon_detail, container, false);
    return view;
  }

  @Override public String getTitle() {
    return "";
  }

  public static BaseFragment with(Pokemon pokemon) {
    return new PokemonDetailFragment();
  }
}
