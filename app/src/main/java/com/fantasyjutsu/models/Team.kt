package com.fantasyjutsu.models

data class Team(val localteam: Team, val league: League, val starting_at:String, val visitorteam:Team)