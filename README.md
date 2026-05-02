# MulImp Backend
A Spring Boot backend for a multiplayer social deduction game where
players answer prompts, discuss, and vote to identify an imposter.

## Tech Stack
This backend is a fully RESTful server that utilises polling to connect players.
This backend was designed using SpringBoot.

For dependencies:
1. Spring Web
2. Spring Validation
3. Spring DevTools 
4. Lombok

## Architecture
This backend was designed following OOP principles and utilised the
popular "Controller -> Service -> Store -> Model" model.

Controllers are the layer that communicate with incoming requests.
It stands as the API layer with the outside world with REST endpoints
and coordinating request/response mapping

Services hold the internal logic. The rules of conduct live here for
what is valid and is not valid from the user/server

Stores are just local repositories that show an interface for services
to get data. Stores are responsible for managing data and responding to
service requests for the data. Note that since no 'game' logic lives here,
the only error checking you would expect are just 'not found' types.

Models are just classes and abstractions of a selection of data, usually
they are considered the individual block inside a store.

## Game Flow
The game flow is designed as a simple FSM as seen below

```text
IN_LOBBY -> ANSWERING -> DISCUSSION -> RESULTS -> IN_LOBBY
```

Each state has the expected logic for movement.
i.e. IN_LOBBY - POST /start -> ANSWERING
or ANSWERING - TIMEOUT / all players POST /answer -> DISCUSSION
and etc...

## API Endpoints
This list is non-exhaustive but are for the most important endpoints

### POST /create
Creates a lobby with a UUID lobbyId and creates a playerId the user
will use to soft 'authenticate' their identity for certain requests

### POST /join
Joins a lobby using the UUID. Players are to share this UUID with their
friends.

### POST /start
Starts a game

### GET /state
This is a soon-to-be deprecated method. Moving forward, it may make sense to
refactor how state information is shared with users as this and the following
endpoint double some information and time has to be spent untangling which
endpoint the frontend depends on

### GET /player-state
Gets information pertaining the game that is relevant AND only to be
shown to the user. i.e. real vs imposter question.

### POST /answer and POST /vote
These endpoints correspond to submitting an answer or vote

## Design Decisions
### Client-Server
A goal of this architecture instead of peer-to-peer was
to ensure that users couldn't fuddle information to their
peers and that there was a central source of truth.

### In-memory store
Using a DB would be an extra level for this demo that
was wholely unnecessary for the goals of this small demo.

### Thread-safety
SpringBoot utilises I believe Apache Tomcat (from research
and what appears in the console when running the app) which
uses thread pooling to allow applications to process multiple
requests simultaneously.

These details make it so thread safety and concurrency
must be taken seriously. Luckily with CSE2301 (with
Prof. Cosgrove), I was introduced to many parallel and
concurrency concepts and tools. The one chosen was to
synchronise on all modifying actions. Synchronised is 
more efficient and additionally using more complex 
locks would not be necessary for a project as small 
as this one.