package co.iyubinest.tddpokedex.list;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import co.iyubinest.tddpokedex.Pokemon;
import co.iyubinest.tddpokedex.R;
import co.iyubinest.tddpokedex.common.BaseFragment;
import co.iyubinest.tddpokedex.detail.PokemonDetailFragment;
import co.iyubinest.tddpokedex.list.ui.PokemonsView;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by iyubinest on 3/22/16.
 */
public class PokemonListFragment extends BaseFragment implements PokemonListView {
  private PokemonListPresenter presenter;
  private PokemonsView pokemonsView;
  private View loadingView;
  private View retryView;
  private final PokemonSelected callback = new PokemonSelected() {
    @Override public void onPokemonSelected(Pokemon pokemon) {
      go(PokemonDetailFragment.with(pokemon));
    }
  };

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.pokemons, container, false);
    pokemonsView = (PokemonsView) view.findViewById(R.id.pokemons);
    loadingView = view.findViewById(R.id.loading);
    retryView = view.findViewById(R.id.retry);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    pokemonsView.setCallback(callback);
    if (presenter == null) setPresenter(getPresenter());
  }

  private PokemonListPresenter getPresenter() {
    return new PokemonListPresenter(this, null);
  }

  public void setPresenter(PokemonListPresenter presenter) {
    this.presenter = presenter;
    show(loadingView);
  }

  @Override public String getTitle() {
    return "";
  }

  @Override public void show(List<Pokemon> pokemons) {
    show(pokemonsView);
    pokemonsView.add(pokemons);
  }

  @Override public void retry() {
    show(retryView);
  }

  private void show(View view) {
    pokemonsView.setVisibility(GONE);
    loadingView.setVisibility(GONE);
    retryView.setVisibility(GONE);
    view.setVisibility(VISIBLE);
  }
}
