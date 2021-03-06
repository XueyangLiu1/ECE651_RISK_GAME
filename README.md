# ECE651_G10_RISK

## Running Instruction
 1. Go to root directory in the project.
 2. Run 'gradle build'
 3. Run server first by 'gradle run-server'.
 4. Run several clients by 'gradle run-client'
 5. Enjoy the game!

```mermaid
classDiagram
 
RuleChecker "1" <-- "1" Server
    SufficientUnitChecker "1" <|-- "1" RuleChecker~T extends Order~
    AdjacentTerritoryChecker "1" <|-- "1" RuleChecker~T extends Order~
    SelfTerritoryChecker "1" <|-- "1" RuleChecker~T extends Order~
    PlayerSelfOrderChecker "1" <|-- "1" RuleChecker~T extends Order~
    EnemyTerritoryChecker "1" <|-- "1" RuleChecker~T extends Order~
    ConnectedTerritoryChecker "1" <|-- "1" RuleChecker~T extends Order~
    TerritoryExistChecker "1" <|-- "1" RuleChecker~T extends Order~
    AttackFoodChecker "1" <|-- "1" RuleChecker~T extends Order~
    CanUpgradeTechChecker "1" <|-- "1" RuleChecker~T extends Order~
    TechUpgradeRangeChecker "1" <|-- "1" RuleChecker~T extends Order~
    SufficientTechResourceChecker "1" <|-- "1" RuleChecker~T extends Order~
    UnitUpgradeRangeChecker "1" <|-- "1" RuleChecker~T extends Order~
    SelfUpgradeOrderChecker "1" <|-- "1" RuleChecker~T extends Order~
    UnitUpgradeTechChecker "1" <|-- "1" RuleChecker~T extends Order~
    UpgradeSufficientUnitChecker "1" <|-- "1" RuleChecker~T extends Order~
    TechResourceUnitChecker "1" <|-- "1" RuleChecker~T extends Order~
    MoveFoodChecker "1" <|-- "1" RuleChecker~T extends Order~
    Server "1" --> "n" Player
    Server "1" --> "1" Map
    Map "1" --> "n" Territory
    Player "1" --> "n" Unit
    TextMapFactory "1" <|-- "1" MapFactory
    MapFactory -- Map: "Create"
    Client "1" --> "1" View
    MoveOrder "1" <|-- "1" Order
    AttackOrder "1" <|-- "1" Order
    UpgradeUnitOrder "1" <|-- "1" Order
    UpgradeTechOrder "1" <|-- "1" Order
    OrderProcessor "1" --> "n" Order
    AddUnitOrder "1" <|-- "1" Order
    Server "1" --> "1" OrderProcessor
    AttackOrder "1" --> "n" Dice
    Player "1" --> "1" Resources


    class Server{
        -int numUnitPerPlayer
        -int numTerritoryPerPlayer
        +checkRule()
        -combat()
        +readInput()
        -checkEndGame()
        +assignTerritory()
        +distributeResults()
    }

    class ShortestPath {
        -GameMap map
    }

    class OrderProcessor {
        -HashMap~Player, Vector~Order~~ attacksInOneTurn
        +acceptOrder()
        +executeEndTurnOrders()
        -merge()
        -Vector~Order~ obtainAllAttackOrders()
    }
    
    class Order {
        -int playerID
        +execute()
        +getPlayerID()
    }

    class MoveOrder {
        -Territory source
        -Territory dest
        -int unitNum
        -GameMap gMap
        +getNumUnit()
        +getSourceTerritory()
        +getTargetTerritory()
    }

    class AttackOrder {
        -Territory attacker
        -Territory defender
        -int unitNum
        -Player owner
        -GameMap gMap
        +getNumUnit()
        +getSourceTerritory()
        +getTargetTerritory()
    }
    
    class UpgradeUnitOrder {
        -int level
        -GameMap gMap
        -String source
        -int unitNum
        +execute()
    }

    class UpgradeTechOrder {
        -GameMap gMap
        +execute()
    }

    class UpgradeOrder {

    }

    class UnitUpgradeOrder {

    }

    class TechnologyUpgradeOrder {

    }

    class Dice{
        -int sides
        +roll()
    }

    class Client {
        -int PlayerID
        -HashSet~String~ normalOrderSet
        -HashMap~String, String~ orderKeyMap
        -HashMap~String, Runnable~ commandMap
        +connectGame()
        +doPlacement()
        +playGame()
    }

    class RuleChecker~T extends Order~ {
        -RuleChecker nextRule
        -checkMyRule()
        +checkOrder()
    }

    class ConnectedTerritoryChecker {

    }

    class AdjacentTerritoryChecker {

    }

    class SufficientUnitChecker {

    }
    
    class PlayerSelfOrderChecker {

    }

    class SelfTerritoryChecker {

    }

    class EnemyTerritoryChecker {

    }

    class TerritoryExistChecker {

    }

    class AttackFoodChecker{

    }

    class CanUpgradeTechChecker{

    }

    class TechUpgradeRangeChecker{

    }

    class SufficientTechResourceChecker{

    }

    class UnitUpgradeRangeChecker{

    }

    class SelfUpgradeOrderChecker{

    }

    class UnitUpgradeTechChecker{

    }

    class UpgradeSufficientUnitChecker{

    }

    class TechResourceUnitChecker{

    }

    class MoveFoodChecker{

    }

    class Player {
        -int ID
        -boolean isUpgrade
        -HashMap~String, int~ resourcesTotal
        -int technologyLevel
        +pickTerritory()
        +setUnits()
        +commitOrders()
        +destoryUnit()
    }

    class Map {
        -HashMap~Territory, Player~ ownership
    }

    class View {

    }

    class MapFactory {
        +creatMap()
    }

    class TextMapFactory {

    }

    class Territory {
        -String name
        -HashSet~Territory~ neighbours
        -HashMap~String,List~Unit~~ units 
        -HashMap~String,int~ resources
        +getNumUnit()
    }

    class Resources{
        -int foodResource
        -int techResource
        -int techLevel
        +consumeFood()
        +consumeTech()   
    }

    class Unit {
        -Player owner
        -int level
        -Territory position 
    }  
    


```
