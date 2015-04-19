%SWEN40004 - Modelling Complex Software Systems
%Naser Soueid 359161 & Edward Crupi 538156

#Aims
The aim of this study is to examine the similarities and differences in behaviour of two software implementations of the Ethnocentrism model first described by Ross Axelrod and Ross Hammond ([2003](#references)). The first model examined is a NetLogo implementation that comes standard as part of the NetLogo package, the second is our own implementation, written in Java and is an attempt to replicate the first NetLogo model. Both implementations are examined statistically to try and discern if they can be determined to follow a normal distribution via Shapiro Wilks tests, and if they appear to represent the same underlying solution set using Kolmogorov-Smirnov testing, as well as standard t-tests. The outputs are also examined as line graphs of multiple runs and as histograms to possibly find any trends or patterns that may not have emerged as a results of the other tests.

#Background
Ethnocentrism is a set of attitudes and behaviours that exist almost to the point of ubiquity in our world. Within this set, is the cooperation of members of the same ethnic type and non-cooperation (or defection from) members of another ethnic type. The model can be used as as a representation of different civilizations existing together in the same space. It has been said that the world peace's biggest threat is the 'clash of civilizations' ([Huntington, 1996](#references)). This makes Axelrod & Hammond's model and the notion of ethnocentrism itself a particularly interesting domain.

##Agents
In the world of the Ethnocentrism model there are 4 distinct strategies an Agent may adopt during it's existance:

- Ethnocentrism: Co-operate only with agents of the same type (here our types are colours) and defect from different types. CD
- Egoism: Co-operate with no one, defect from everyone. DD
- Cosmopolitan: Co-operate only with agents of different types, defect from own type. DC
- Altruism: Co-operate with all agents, defect from no one. CC

##Complexity
The model is a complex system, it can be described as such due to four defining properties: Emergence, Self-Organization, Decentralization and propensity for Feedback.  The model shows emergent grouping of ethnic behaviour that organizes itself from the simple evolutionary rules of local competition between individuals. The model is a decentralized (agent-based, each agent is autonomous) cellular-automaton where there is no single controller or 'leader' that is sensitive to fluctuations in group size based on the size of the grid. It is due to this emergent, self-organizing, decentralized behaviour and sensitivity to feedback that the model may be described as a complex system. 

#Model Design
The NetLogo model has many reporting function that it uses to show the state of the system at any given passed moment in time. The main variables being reported were the number of 

##Overview
The model evolves with one-move Prisoner's Dilemma type interactions ([Rapoport & Chammah,1970](#references)) occuring at every step, where there is a price to be payed in helping someone whilst also conversely a benefit to be gained in being helped by someone. To replicate the model in Java a CellularAutomaton class was created that was comprised of a Grid instance than contained many Cell insctances that may or may not (depending on input) have occupying Agent instance. A UML interpretation of the system is shown below.

##UML Diagram

![The System's Class Diagram](Diagram.png)\

\centerline{Figure 1. The System's Class Diagram}

##Implementation
There are 5 main methods within our model of Ethnocentrism that determines the final state of the model at any given step. They are:

1. Immigration, agents will immigrate to empty cells at a rate determined by the class variable: probImmigrant (probability of immigrant.
2. Interaction, agents will interact with other agents with whom they co-operate within their Von Neumann neighbourhood.
3. Reproduction, agents will reproduce with a likelihood determined by the class variable: `PTR` (potential to reproduce)
4. Death, agents will die out at a rate determined by the class variable: `dr` (death rate)
5. Report Results, statistics are reported and in our Java model's instance saved to a comma separated values (CSV) file.

Each step itself must iterate over all the agents and perform its necessary function to update the state of the system.

##Modelling Techniques
The system uses a reporting method that outputs the numbers of Ethnocentric, Cosmopolitan, Egoistic and Altruistic agents as 4 respective variables: `CD`, `DC`, `DD` and `CC`. These are the main variables we use to compare our models for similarity of output.

#Experimentation
We aim to explore the following hypothesis:
	1. The results from the Java implemnentation are robust against a change from deterministic updating (top left to bottom right) to stochastic updating
	2. The Java implementation will produce output consistent with the NetLogo implementation

## Deterministic vs. Stochastic Updating
The updating method used by NetLogo is not explicitly stated. Here we will see if a change in updating method results in a systematic change in the output of our Java model, and then explore which, if either, produce results more statistically similar to the NetLogo model.

We are operating under the hypothesis that there will be little difference in the two. Deterministic updating may lead to population clusters around the top of the graph where the evolving populations are small, thus relatively high death rates, low immigration rates and low probabilities of reproduction are more likely to manifest this behaviour. If there are any differences between the two updating methods, they are likely to be more blatent and thus easier to test for. 

##Analysing the Output

#Results

#Discussion
Discuss inherent bias to birth to left of agent if matrix is sparse.

#References

[Axelrod & Hammond, 2003](http://www-personal.umich.edu/~axe/research/AxHamm_Ethno.pdf), Midwest Political Science Convention, April 3-6, 2003, Chicago, IL

[Rapoport & Chammah, 1970](http://www.press.umich.edu/pdf/9780472061655-fm.pdf), 'Prisoner's Dilemma - A Study in Conflict and Cooperation', The University of Michigan, ISBN 0-472-96165-8

[Huntington, 1996](http://www.academia.edu/4610592/Samuel_P_Huntington_The_Clash_of_Civilizations_and_the_Remaking_of_World_Order_1996) The Clash of Civilizations and the Remaking of World
Order. New York: Simon and Schuster, ISBN 0-684-81164-2
