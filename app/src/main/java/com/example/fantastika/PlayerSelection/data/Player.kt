package com.example.fantastika.PlayerSelection.data

data class Player(
    val name: String,
    val price: Int,
    val rating: Int = 2,
    val team: String = ""
)

val allTeams = listOf(
    "Lakers",
    "Warriors",
    "Nets",
    "Celtics",
    "Bulls",
    "Heat",
    "Mavericks",
    "Suns"
)

val allPlayers = listOf(
    // Lakers (5 players)
    Player("LebronLebronLebronLebronLebronLebron", 1200, 5, "Lakers"),
    Player("Anthony Davis", 950, 4, "Lakers"),
    Player("Austin Reaves", 600, 3, "Lakers"),
    Player("D'Angelo Russell", 750, 3, "Lakers"),
    Player("Rui Hachimura", 500, 3, "Lakers"),

    // Warriors (5 players)
    Player("Stephen Curry", 1150, 5, "Warriors"),
    Player("Klay Thompson", 800, 4, "Warriors"),
    Player("Draymond Green", 700, 4, "Warriors"),
    Player("Andrew Wiggins", 650, 3, "Warriors"),
    Player("Chris Paul", 600, 3, "Warriors"),

    // Nets (5 players)
    Player("James Harden", 900, 4, "Nets"),
    Player("Mikal Bridges", 700, 3, "Nets"),
    Player("Cameron Johnson", 550, 3, "Nets"),
    Player("Nic Claxton", 600, 3, "Nets"),
    Player("Spencer Dinwiddie", 500, 2, "Nets"),

    // Celtics (5 players)
    Player("Jayson Tatum", 1100, 5, "Celtics"),
    Player("Jaylen Brown", 1000, 4, "Celtics"),
    Player("Kristaps Porzingis", 800, 4, "Celtics"),
    Player("Derrick White", 650, 3, "Celtics"),
    Player("Jrue Holiday", 750, 4, "Celtics"),

    // Bulls (5 players)
    Player("Michael Jordan", 2000, 5, "Bulls"),
    Player("DeMar DeRozan", 850, 4, "Bulls"),
    Player("Zach LaVine", 800, 4, "Bulls"),
    Player("Nikola Vucevic", 700, 3, "Bulls"),
    Player("Coby White", 550, 3, "Bulls"),

    // Heat (5 players)
    Player("Jimmy Butler", 1000, 4, "Heat"),
    Player("Bam Adebayo", 900, 4, "Heat"),
    Player("Tyler Herro", 750, 3, "Heat"),
    Player("Kyle Lowry", 600, 3, "Heat"),
    Player("Caleb Martin", 500, 2, "Heat"),

    // Mavericks (5 players)
    Player("Luka Doncic", 1300, 5, "Mavericks"),
    Player("Kyrie Irving", 1000, 4, "Mavericks"),
    Player("Dereck Lively", 600, 3, "Mavericks"),
    Player("Tim Hardaway Jr", 650, 3, "Mavericks"),
    Player("Josh Green", 500, 2, "Mavericks"),

    // Suns (5 players)
    Player("Kevin Durant", 1250, 5, "Suns"),
    Player("Devin Booker", 1100, 5, "Suns"),
    Player("Bradley Beal", 900, 4, "Suns"),
    Player("Jusuf Nurkic", 700, 3, "Suns"),
    Player("Grayson Allen", 550, 3, "Suns")
)