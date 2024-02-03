# scoreboard-library
Live Football World Cup Scoreboard

Implementation considerations
* since we build a library I've put together the `Scoreboard` interface in order to define library contract (i.e. public methods it provides)
* The domain model consists of `Team` and `Score`
* Current implementation does not validate team names, I think, production-ready library would require some validation (e.g. non-existing country)
* When updating score, in my opinion it would make sense to throw an exception if the new score for any team is lower than the existing (I'm not a football expert, but I never saw this happening during the game)
* 