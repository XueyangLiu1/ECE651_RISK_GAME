package edu.duke.ece651_g10.server;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class V1OrderProcessorTest {
  @Test
  public void test_acceptOrder() {
    FixedGameMapFactory factory = new FixedGameMapFactory();
    GameMap gMap = factory.createGameMap(3);
    Player player = new Player(null, null);
    gMap.getTerritory("Elantris").increaseUnit(5, 0);
    gMap.getTerritory("Roshar").increaseUnit(4, 6);
    Player p = mock(Player.class);
    MoveOrder mOrder = new MoveOrder(1, "Elantris", "Roshar", 3, gMap, p, 0);
    AttackOrder aOrder = new AttackOrder(1, "Elantris", "Narnia", 1, gMap, player, 0);
    AttackOrder aOrder2 = new AttackOrder(1, "Elantris", "Scadrial", 1, gMap, player, 0);
    V1OrderProcessor op = new V1OrderProcessor();
    op.acceptOrder(mOrder);
    op.acceptOrder(aOrder);
    op.acceptOrder(aOrder2);
    op.executeEndTurnOrders();
    assertEquals(7, gMap.getTerritory("Roshar").getNumUnit());
    assertEquals(4, gMap.getTerritory("Roshar").getArmyWithLevel(6).getArmyUnits());
    assertEquals(3, gMap.getTerritory("Roshar").getArmyWithLevel(0).getArmyUnits());
    assertEquals(1, gMap.getTerritory("Narnia").getArmyWithLevel(0).getArmyUnits());
    assertEquals(1, gMap.getTerritory("Scadrial").getArmyWithLevel(0).getArmyUnits());
    assertEquals(0, gMap.getTerritory("Elantris").getNumUnit());

  }

  @Test
  public void test_attackLevel(){
    FixedGameMapFactory factory = new FixedGameMapFactory();
    GameMap gMap = factory.createGameMap(3);
    Player player = new Player(null, null);
    gMap.getTerritory("Elantris").increaseUnit(5, 0);
    gMap.getTerritory("Elantris").increaseUnit(2, 3);
    AttackOrder aOrder1 = new AttackOrder(1, "Elantris", "Narnia", 5, gMap, player, 0);
    AttackOrder aOrder2 = new AttackOrder(1, "Elantris", "Narnia", 2, gMap, player, 3);
    ArrayList<Integer> arr1 = new ArrayList<>(Arrays.asList(5, 0, 0, 0, 0, 0, 0));
    ArrayList<Integer> arr2 = new ArrayList<>(Arrays.asList(0, 0, 0, 2, 0, 0, 0));
    assertEquals(5, aOrder1.getSourceTerritory().getArmyWithLevel(0).getArmyUnits());
    assertEquals(2, aOrder2.getSourceTerritory().getArmyWithLevel(3).getArmyUnits());
    assertEquals(arr1, aOrder1.getAttackLevel());
    assertEquals(arr2, aOrder2.getAttackLevel());
  }

  @Test
  public void test_merge(){
    FixedGameMapFactory factory = new FixedGameMapFactory();
    GameMap gMap = factory.createGameMap(3);
    Player player = new Player(null, null);
    gMap.getTerritory("Elantris").increaseUnit(5, 0);
    gMap.getTerritory("Elantris").increaseUnit(2, 3);
    gMap.getTerritory("Roshar").increaseUnit(6, 1);
    gMap.getTerritory("Roshar").increaseUnit(3, 4);
    AttackOrder aOrder1 = new AttackOrder(1, "Elantris", "Scadrial", 2, gMap, player, 0);
    AttackOrder aOrder2 = new AttackOrder(1, "Roshar", "Scadrial", 1, gMap, player, 1);
    V1OrderProcessor op = new V1OrderProcessor();
    op.acceptOrder(aOrder1);
    op.acceptOrder(aOrder2);
    op.executeEndTurnOrders();
    assertEquals(3, gMap.getTerritory("Elantris").getArmyWithLevel(0).getArmyUnits());
    assertEquals(5, gMap.getTerritory("Roshar").getArmyWithLevel(1).getArmyUnits());
    assertEquals(2, gMap.getTerritory("Scadrial").getArmyWithLevel(0).getArmyUnits());
    assertEquals(1, gMap.getTerritory("Scadrial").getArmyWithLevel(1).getArmyUnits());
    assertEquals(0, gMap.getTerritory("Roshar").getArmyWithLevel(3).getArmyUnits());
    assertEquals(3, gMap.getTerritory("Scadrial").getNumUnit());
  }

  @Test
  public void test_1v0(){
    FixedGameMapFactory factory = new FixedGameMapFactory();
    GameMap gMap = factory.createGameMap(3);
    Player player1 = new Player(null, null);
    Player player2 = new Player(null, null);
    gMap.getTerritory("Elantris").setOwner(player1);
    gMap.getTerritory("Narnia").setOwner(player2);
    gMap.getTerritory("Elantris").increaseUnit(1, 0);
    gMap.getTerritory("Narnia").increaseUnit(1, 6);
    AttackOrder aOrder1 = new AttackOrder(1, "Elantris", "Narnia", 1, gMap, player1, 0);
    AttackOrder aOrder2 = new AttackOrder(1, "Narnia", "Elantris", 1, gMap, player2, 6);
    V1OrderProcessor op = new V1OrderProcessor();
    op.acceptOrder(aOrder1);
    op.acceptOrder(aOrder2);
    op.executeEndTurnOrders();
    assertEquals(player2.getPlayerID(), gMap.getTerritory("Elantris").getOwner().getPlayerID());
    assertEquals(player1.getPlayerID(), gMap.getTerritory("Narnia").getOwner().getPlayerID());
  }

  @Test
  public void test_attack(){
    FixedGameMapFactory factory = new FixedGameMapFactory();
    GameMap gMap = factory.createGameMap(3);
    Player player = new Player(null, null);
    gMap.getTerritory("Elantris").increaseUnit(5, 0);
    gMap.getTerritory("Elantris").increaseUnit(2, 3);
    gMap.getTerritory("Roshar").increaseUnit(6, 1);
    gMap.getTerritory("Roshar").increaseUnit(3, 4);
    gMap.getTerritory("Scadrial").increaseUnit(5, 1);
    MoveOrder mOrder = new MoveOrder(1, "Elantris", "Roshar", 3, gMap, player, 0);
    AttackOrder aOrder1 = new AttackOrder(1, "Elantris", "Scadrial", 2, gMap, player, 0);
    AttackOrder aOrder2 = new AttackOrder(1, "Roshar", "Scadrial", 1, gMap, player, 1);
    V1OrderProcessor op = new V1OrderProcessor();
    op.acceptOrder(mOrder);
    assertEquals(2, gMap.getTerritory("Elantris").getArmyWithLevel(0).getArmyUnits());
    op.acceptOrder(aOrder1);
    op.acceptOrder(aOrder2);
    ArrayList<Integer> arr1 = new ArrayList<>(Arrays.asList(2, 1, 0, 0, 0, 0, 0));
    assertEquals(arr1, aOrder1.getAttackLevel());
    op.executeEndTurnOrders();
    //assertEquals(3, gMap.getTerritory("Scadrial").getNumUnit());
  }
}











