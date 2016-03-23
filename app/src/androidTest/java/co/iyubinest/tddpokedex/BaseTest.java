package co.iyubinest.tddpokedex;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.util.HumanReadables;
import android.support.test.espresso.util.TreeIterables;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import co.iyubinest.tddpokedex.common.NavigationActivity;
import java.util.concurrent.TimeoutException;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by iyubinest on 3/22/16.
 */
@RunWith(AndroidJUnit4.class) @LargeTest public class BaseTest {
  @Rule public ActivityTestRule<NavigationActivity> mActivityRule =
      new ActivityTestRule<>(NavigationActivity.class);
  protected NavigationActivity activity;

  @Before public void setup() throws InterruptedException {
    activity = mActivityRule.getActivity();
  }

  /** Perform action of waiting for a specific view id. */
  public static ViewAction waitId(final int viewId, final long millis) {
    return new ViewAction() {
      @Override public Matcher<View> getConstraints() {
        return isRoot();
      }

      @Override public String getDescription() {
        return "wait for a specific view with id <" + viewId + "> during " + millis + " millis.";
      }

      @Override public void perform(final UiController uiController, final View view) {
        uiController.loopMainThreadUntilIdle();
        final long startTime = System.currentTimeMillis();
        final long endTime = startTime + millis;
        final Matcher<View> viewMatcher = withId(viewId);
        do {
          for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
            // found view with required ID
            if (viewMatcher.matches(child)) {
              return;
            }
          }
          uiController.loopMainThreadForAtLeast(50);
        } while (System.currentTimeMillis() < endTime);
        // timeout happens
        throw new PerformException.Builder().withActionDescription(this.getDescription())
            .withViewDescription(HumanReadables.describe(view))
            .withCause(new TimeoutException())
            .build();
      }
    };
  }
}
