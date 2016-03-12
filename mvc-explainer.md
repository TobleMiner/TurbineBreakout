Turbine Breakout - A MVC implementation of the game classic Breakout
====================================================================

#Task
As the most skilled minds of a bright future we were tasked with developing a new mindblowing game... - yet another implementation of breakout \o/

But this is not about any ordinary implementation of breakout. It's to be designed with the newest, hottest software design pattern in mind: MVC!


#Design goals
Our goal with this project was to design not only a MVC-compliant Breakout but also make it easily modify and extensible.


#Parted project
To make the result of this project more universally reusable it has been split into two parts:

1. The Game Engine  
   The game engine is called 'MVC-Turbine' and contains a lot of basic stuff like utilities for geometry and the basic MVC pattern of the game. It's split in multiple packages. Everything in 'de.mvcturbine.ui' is the view and everything that resides in 'de.mvcturbine.world' is the model. The package 'de.mvcturbine.game' contains the controller of the game engine. The only input of this controller is a fixed timed tick. Any more specific inputs must be handled by the game built on the engine.

2. The Game  
   The game itself is called 'TurbineBreakout' and contains all game specific logic. The layout of the MVC classes is basically the same. Just apply the perl regex 's/mvcturbine/mvcturbine.breakout/' to the above packages to get the package names in the game. Additional controllers are in 'de.mvcturbine.breakout.input'.


#Implementing the MVC pattern
To develop a cutting edge MVC breakout a little bit of thinking has to be done before diving straight into programming because implementing MVC correctly is not trivial. So we sat down whipped out our UML design tools and started drawing class diagrams. They were split into Mode, View and Controler.

The model contains the data of our Breakout.

It is composed of a breakout level (world) and all entities in this world. The world is observed by all views and notifies those of changes each time the controller notifies the model of a change.

The view is comprised of the view itself and multiple renders that are responsible for rendering entities. There is at least one view per world.

The controller is split into two parts. The first part is direct user input via keyboard or mouse the second part is a fixed game tick that is needed to perform tasks like updating the position of moving objects.

#How it works
Initially the game (Controller) is initialized. Then a world (Model) is added to it as an observer. The initialization method then adds a bunch of entities (Model) to the world. After that the views get initialized and are added as observers of the model. Finally a MouseInputListener (Controller) and a KeyInputListener (Controller) are added to modify the model accordingly to user input.

At this point the initialization of the game is finished.

Now the main game "loop" takes over. Each in game tick the game (Controller) notifies the world (Model) to update. The world then updates all entities (Model). After all entities are updated the world (Model) notifies its views to update the displayed content. The views then schedule a redraw of their content.

Outside of this game loop user input can cause an update of the world (Model) by modifying any part of it (e.g. an entity).

#Extensibility
The whole project is designed with extensibility in mind.
Originally we planned on adding an API for adding in game elements but time was too short and the API had to remain on the TODO list for now. Due to the design of the game it wouldn't be hard to add it though.

#Problems and TODOs
Of course the game isn't perfect after just 2 weeks of work. There are some issues and stuff we just didn't manage to implement in the given timeframe.
One such part are the physics of the game engine.
They are totally suited for collisions of one moving entity with a solid, static entity but as soon as moving entities collide physics don't behave the way you would expect because the game engine doesn't have a concept of weight, energy and momentum.

Another problem is the rendering process. For the sake of simplicity each entity is fully rendered each and every tick. This consumes a lot of CPU time. It would be far more efficient to render entities only if their appearance changes.

Also the tick update loop could be improved. A lot of entities perform collision checks. And currently they all check if they do collide with another entity by theirselves. This could be improved by performing these checks only once per tick per world by the world itself.

The current design doesn't allow for multiple balls. This could be changed fairly easily but the physics for colliding two moving object are in the making so it will come soon but isn't supported yet.

P.S. If you do want to run any tests with the lighthouse simulator look into 'src/de/mvcturbine/breakout/game/Breakout.java'. Our animations only work on the lighthouse.
