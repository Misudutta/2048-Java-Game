## About

2048 is a single-player sliding puzzle game played on an n×n grid deceptively simple in 
concept, surprisingly deep in execution. Every turn, you choose a direction UP, DOWN, LEFT, 
or RIGHT and every tile on the board slides as far as it can go. When two tiles with the 
same value collide, they merge into one, doubling in value. A 2 meets a 2 and becomes a 4. 
Two 512s slam together and you've got 1024. Chain enough of those together and you're 
chasing the mythical 2048 tile and beyond.

The catch? After every move, a new tile spawns in a random empty cell either a 2 or a 4. 
The board is always getting fuller, your options are always getting tighter, and one bad move 
can cascade into a completely locked grid. When no move can change the state of the board, 
it's game over.

This implementation is built in Java using the Processing library for rendering, giving the 
game smooth tile animations as blocks glide across the grid. The board size is configurable 
at launch via command line arguments a 4×4 grid is the classic experience, but crank it up 
to 6×6 or 8×8 for a longer, more chaotic game. A live timer tracks how long you've survived, 
freezing the moment the board locks up. You can also click any empty cell to manually spawn 
a tile, giving you just enough control to feel responsible for your own downfall.

Press 'r' to restart. You'll need it.

## Prerequisites

- **Java JDK 11+** — [Download here](https://adoptium.net/)
- **Gradle** (or use the included Gradle wrapper)

No need to install Processing manually — it's pulled in as a dependency via Gradle.

---

## Setup

Clone the repo and navigate into it:

```bash
git clone <your-repo-url>
cd <repo-folder>
```

---

## Running the Game

### Default 4×4 board

```bash
./gradlew run
```

> On Windows, use `gradlew.bat run`

### Custom board size (e.g. 6×6)

```bash
./gradlew run --args="6"
```

If an invalid or no argument is provided, the game defaults to a 4×4 grid.

---

## Controls

| Input | Action |
|---|---|
| `↑ ↓ ← →` Arrow keys | Move tiles |
| Left click on empty cell | Spawn a 2 or 4 tile |
| `r` | Restart after game over |


