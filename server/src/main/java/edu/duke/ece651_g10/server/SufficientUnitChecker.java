package edu.duke.ece651_g10.server;

/**
 * The RuleChecker to check whether it has sufficient unit
 */
public class SufficientUnitChecker extends RuleChecker<TerritoryToTerritoryOrder> {

  /**
   * The constructor of the SufficientUnitChecker
   *
   * @param next The next RuleChecker
   */
  public SufficientUnitChecker(RuleChecker<TerritoryToTerritoryOrder> next) {
    super(next);
  }

  /**  
   * Check whether the order is following the rule defined in the following
   * method.
   * 
   * @param order   the order frome the player
   * @param gameMap the game map of the game
   * @return if the rule is not violated, return null. Otherwise, return the
   *         reason causing the invalid placement
   */
  @Override
  protected String checkMyRule(TerritoryToTerritoryOrder order, GameMap gameMap) {
    if (order.getNumUnit() > order.getSourceTerritory().getArmyWithLevel(order.getLevel()).getArmyUnits()) {
      return "The territory does not have enough units with certain level.";
    }
    return null;
  }
}












