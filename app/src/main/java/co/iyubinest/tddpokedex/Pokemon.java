package co.iyubinest.tddpokedex;

import java.util.List;

/**
 * Created by iyubinest on 3/21/16.
 */
public class Pokemon {
  private final String name;
  private final int number;
  private final List<String> abilities;

  private Pokemon(String name, int number, List<String> abilities) {
    this.name = name;
    this.number = number;
    this.abilities = abilities;
  }

  public static Pokemon create(String name, int number, List<String> abilities) {
    return new Pokemon(name, number, abilities);
  }

  public String getName() {
    return name;
  }

  public int getNumber() {
    return number;
  }

  public String getImage() {
    return BuildConfig.APP_URL + BuildConfig.IMAGE_PATH + number;
  }

  public List<String> getAbilities() {
    return abilities;
  }
}
