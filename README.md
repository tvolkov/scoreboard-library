# scoreboard-library
Live Football World Cup Scoreboard

Structure
* The domain model looks like following
```mermaid
classDiagram
      Match *-- Team
      Team *-- TeamName
      Score : +int homeTeamScore

[//]: # (      Score : +int awayTeamScore)
      Match : +Team homeTeam

[//]: # (      Match : +Team awayTeam)
      Team : +TeamName teamName
      class Match {
          +Team homeTeam
          +Team awayTeam
      }
      class Team {
          +TeamName teamName
      }
      class Score {
           +int homeTeamScore
           +int awayTeamScore
      }
```

Implementation considerations
* Current implementation does not validate team names, I think, production-ready library would require some validation (e.g. non-existing country)
* When updating score, in my opinion it would make sense to throw an exception if the new score for any team is lower than the existing (I'm not a football expert, but I never saw this happening during the game). 
Also it might make sense to only allow incrementing score (i.e. only allow to increase by 1), but I decided to leave it out as I was not 100% sure if it would bring any value
* The logic for determining which match was started earlier relies on the order of insertion `Match` objects in the underlying data structure (I chose `LinkedHashMap` exactly because of this)
However for real-life scenarios I think I'd base this on a timestamp (ideally the `Match` class should have the information about when it started), since this is generally more standard and reliable approach (insertion order approach seemed to be more simple to implement)
* 