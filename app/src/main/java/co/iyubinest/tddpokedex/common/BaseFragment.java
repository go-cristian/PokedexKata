package co.iyubinest.tddpokedex.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by iyubinest on 3/22/16.
 */
public abstract class BaseFragment extends Fragment {
  public abstract String getTitle();

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setRetainInstance(true);
  }

  protected void go(BaseFragment fragment) {
    ((NavigationActivity) getActivity()).add(fragment);
  }
}
