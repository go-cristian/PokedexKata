package co.iyubinest.tddpokedex.list.ui;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import co.iyubinest.tddpokedex.Pokemon;
import co.iyubinest.tddpokedex.R;
import co.iyubinest.tddpokedex.list.PokemonSelected;
import java.util.List;

/**
 * Created by iyubinest on 3/22/16.
 */
public class PokemonsView extends ListView {
  private PokemonsAdapter pokemonsAdapter;
  private PokemonSelected callback;

  public PokemonsView(Context context) {
    super(context);
    init();
  }

  public PokemonsView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public PokemonsView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public PokemonsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init();
  }

  private void init() {
    pokemonsAdapter = new PokemonsAdapter(getContext());
    setAdapter(pokemonsAdapter);
    setOnItemClickListener(new OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (callback != null) callback.onPokemonSelected(pokemonsAdapter.getItem(position));
      }
    });
  }

  public void add(List<Pokemon> pokemons) {
    pokemonsAdapter.addAll(pokemons);
    pokemonsAdapter.notifyDataSetChanged();
  }

  public void setCallback(PokemonSelected callback) {
    this.callback = callback;
  }

  private class PokemonsAdapter extends ArrayAdapter<Pokemon> {
    public PokemonsAdapter(Context context) {
      super(context, R.layout.pokemon);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
      PokemonsViewHolder holder;
      if (convertView == null) {
        holder = convertViewFrom(parent);
      } else {
        holder = getFromTag(convertView);
      }
      holder.pokemon(getItem(position));
      return holder.getView();
    }

    private PokemonsViewHolder getFromTag(View convertView) {
      return (PokemonsViewHolder) convertView.getTag();
    }

    private PokemonsViewHolder convertViewFrom(ViewGroup parent) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon, parent, false);
      PokemonsViewHolder holder = new PokemonsViewHolder(view);
      view.setTag(holder);
      return holder;
    }
  }

  private class PokemonsViewHolder {
    private final TextView title;
    private View view;

    public PokemonsViewHolder(View view) {
      this.view = view;
      this.title = (TextView) view.findViewById(R.id.title);
    }

    public View getView() {
      return view;
    }

    public void pokemon(Pokemon pokemon) {
      title.setText(pokemon.getName());
    }
  }
}
