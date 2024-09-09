
# NHL 24 Contract Evaluator

This is a Java-based tool for the video game NHL 24, designed to predict player performance over the duration of a contract and evaluate if the player is worth the contract value based on various factors. It simulates player stats like goals, assists, points, and overall rating progression, taking into account player type, age, position, and other relevant metrics. This tool can be used to analyze whether a player is expected to meet or exceed the value of their contract over time, helping players make informed decisions.

## Features

- **Player Simulation**: Simulates player performance over multiple seasons based on real-world factors like age, position, and potential.
- **Expected Value Calculation**: Calculates the player's expected value in terms of goals, assists, points, plus/minus, and overall rating.
- **Contract Value Analysis**: Assesses whether a player is expected to meet or exceed the value of their contract, factoring in the current salary cap.
- **Detailed Output**: Provides yearly projections for expected goals, assists, points, overall rating, and expected contract value.

## Setup

1. Clone the repository:
   ```bash
   git clone git clone https://github.com/your-username/nhl23
   ```
2. Compile and run the project:
```bash
   javac contract.java java contract
   ```

## Usage

1. Run the project
2. Input the following parameters:
- Player name
- Age
- Overall Rating
- Position
- Player Type 
- Potential (varies by position) 
- Last season’s performance (points, goals, assists, and plus/minus) 
- Contract details (length in years, contract value, and current salary cap)
3. The simulation will then run and produce output similar to this:
```bash
   Year 1:
Expected goals: 13.0
Expected assists: 51.0
Expected points: 64.0
Expected overall: 95
Expected +-: 2.0

cale makar's contract will be worth his expected value in year 1 40.0 of the time.

cale makar's expected value in year 1 will equate to a 11.0M dollar contract
   ```

## Disclaimer

This project is a **work-in-progress** and is not yet feature-complete. It is currently in a **development phase**, with the primary focus being functionality and user experience improvements.



## License

This project is open-source and available under the MIT License.
