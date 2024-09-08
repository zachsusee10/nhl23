NHL24 Contract Evaluation Simulator


Overview
The NHL24 Contract Evaluation Simulator is a Java-based tool designed to predict player performance over the duration of a contract and evaluate if the player is worth the contract value based on various factors. It simulates player stats like goals, assists, points, and overall rating progression, taking into account player type, age, position, and other relevant metrics. This tool can be used to analyze whether a player is expected to meet or exceed the value of their contract over time, helping teams make informed decisions.

Features
Player Simulation: Simulates player performance over multiple seasons based on real-world factors like age, position, and potential.
Expected Value Calculation: Calculates the player's expected value in terms of goals, assists, points, plus/minus, and overall rating.
Contract Value Analysis: Assesses whether a player is expected to meet or exceed the value of their contract, factoring in the current salary cap.
Detailed Output: Provides yearly projections for expected goals, assists, points, overall rating, and expected contract value.
Usage


Input Parameters:

Player name, age, overall rating, and position
Player type and potential (varies by position)
Last season’s performance (points, goals, assists, and plus/minus)
Contract details (length in years, contract value, and current salary cap)
Run the Simulation: The simulator will predict the player's performance each year and evaluate their contract value.


View Output: The simulation results will show:

Expected goals, assists, points, and plus/minus for each year of the contract
Expected overall rating for each year
The percentage of time the player's contract is expected to be worth the projected value
The player’s projected contract value based on their expected performance


Example
Year 1:
Expected goals: 30
Expected assists: 40
Expected points: 70
Expected overall: 87
Expected +/-: 15

Player X's contract will be worth their expected value 85% of the time.
Player X's expected value in year 1 equates to a $7.5M contract.
How It Works
The simulator runs multiple iterations for each year of a player's contract, simulating performance outcomes based on statistical inputs and modifiers. The simulation adjusts for player aging, potential, and position type, adding a degree of randomness to reflect real-world player development. Contract evaluations are based on comparisons between a player's expected performance and the contract's value under the current salary cap.

Installation
Clone the repository:
git clone https://github.com/your-username/NHL24-Contract-Evaluation.git

Compile and run the project:
javac contract.java
java contract


Future Enhancements
Integration of historical player data for improved prediction accuracy
Expansion to include goalie-specific performance metrics
Adding graphical output to visualize player performance over the contract term
License
This project is licensed under the MIT License.

Author: [Zachary Susee]
Contact: [susee@wustl.edu]
GitHub: [zachsusee10]
