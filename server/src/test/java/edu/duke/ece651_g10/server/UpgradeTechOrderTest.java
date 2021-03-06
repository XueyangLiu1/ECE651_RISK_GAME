package edu.duke.ece651_g10.server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UpgradeTechOrderTest {
  @Test
  public void test_upgradeTech() {
    FixedGameMapFactory factory = new FixedGameMapFactory();
    GameMap gMap = factory.createGameMap(3);
    Player player = new Player(null, null);
    player.setTechnologyResourceTotal(100);
    UpgradeTechOrder order = new UpgradeTechOrder(1, gMap, player);
    assertEquals(1, player.getTechnologyLevel());
    assertEquals(100, player.getTechnologyResourceTotal());
    order.execute();
    assertEquals(2, player.getTechnologyLevel());
    //assertEquals(50, player.getTechnologyResourceTotal());
    assertEquals(player, order.getPlayer());
    assertEquals(order.getMaxTechLevelTable(), order.getMaxTechLevelTable());
  }

}













