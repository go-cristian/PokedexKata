package co.iyubinest.tddpokedex;
import android.support.test.espresso.IdlingResource;

/**
 * Created by iyubinest on 3/22/16.
 */
public class ElapsedIdlingResource implements IdlingResource {
  private final long startTime;
  private final long waitingTime;
  private ResourceCallback resourceCallback;

  public ElapsedIdlingResource(long waitingTime) {
    this.startTime = System.currentTimeMillis();
    this.waitingTime = waitingTime;
  }

  @Override public String getName() {
    return ElapsedIdlingResource.class.getName() + ":" + waitingTime;
  }

  @Override public boolean isIdleNow() {
    long elapsed = System.currentTimeMillis() - startTime;
    boolean idle = (elapsed >= waitingTime);
    if (idle) {
      resourceCallback.onTransitionToIdle();
    }
    return idle;
  }

  @Override public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
    this.resourceCallback = resourceCallback;
  }
}
