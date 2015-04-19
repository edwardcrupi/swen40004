%SWEN40004 - Modelling Complex Software Systems
%Naser Soueid 359161 & Edward Crupi 538156

#Aims
The aim of this study is to examine the similarities and differences in behaviour of two software implementations of the Ethnocentrism model first described by Ross Axelrod and Ross Hammond ([2003](#references)). The first model examined is a NetLogo implementation that is included as part of the NetLogo model library, the second is our own implementation, written in Java and is an attempt to replicate the first NetLogo model. Both implementations are examined statistically to try and discern if they can be determined to follow a normal distribution via Shapiro Wilks tests, and if they appear to represent the same underlying solution set using Kolmogorov-Smirnov testing. The outputs are also examined as line graphs of multiple runs and as histograms to verify the results of these tests visually.

#Background
Ethnocentrism is a set of attitudes and behaviours ubiquitous in our world, manifested in the formation of communities, nationalistic zeal, and similar examples of collective action based on like individual's mutually recognized common identity. Ethnocentrism refers to the tendency to cooperate with members of the same ethnic type and to avoid cooperating with members of another ethnic type. It has been said that the world peace's biggest threat is the 'clash of civilizations' ([Huntington, 1996](#references)). This makes Axelrod & Hammond's model and the notion of ethnocentrism itself a particularly interesting domain. The model aims to represent the emergent cooperation strategies in areas where different ethnicities coexist.

##Agents
In the world of the Ethnocentrism model there are 4 distinct strategies an Agent may adopt during it's existance:

- Ethnocentrism: Co-operate only with agents of the same type (here our types are colours) and defect from different types. CD
- Egoism: Co-operate with no one, defect from everyone. DD
- Cosmopolitan: Co-operate only with agents of different types, defect from own type. DC
- Altruism: Co-operate with all agents, defect from no one. CC

##Complexity
The model is a complex system, it can be described as such due to four defining properties: Emergence, Self-Organization, Decentralization and propensity for Feedback.  The model shows emergent grouping of ethnic behaviour that organizes itself from the simple evolutionary rules of local competition between individuals. The model is a decentralized (agent-based, each agent is autonomous) cellular-automaton where there is no single controller or 'leader' that is sensitive to fluctuations in group size based on the size of the grid. It is due to this emergent, self-organizing, decentralized behaviour and sensitivity to feedback that the model may be described as a complex system. 

#Model Design
The NetLogo model has many reporting functions that it uses to show the state of the system at any given passed moment in time. The main variables being reported were the number CC, CD, DD and DC agents extant within the system 

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
Our system uses a reporting method that outputs the numbers of Ethnocentric, Cosmopolitan, Egoistic and Altruistic agents as 4 respective variables: `CD`, `DC`, `DD` and `CC`. These are the main variables we use to compare our models for similarity of output.

#Experimentation
We explore the following hypotheses:

1. The results from the Java implementation are robust against a change from deterministic updating (top left to bottom right) to stochastic updating.
2. The Java implementation will produce output consistent with the NetLogo implementation.

We are operating under the hypothesis that there will be little difference in the two updating methods in scenarious with fairly densely populated grids. Deterministic updating may lead to population clusters around the top of the grid where the evolving populations are small, thus starting with an empty grid, relatively high death rates, low immigration allowances relative to the maximum number of agents and low probabilities of reproduction are more likely to manifest this behaviour. If there are any differences between the two updating methods, they are likely to be more blatent at these parameter values and thus easier to test for.

All tests will be run only on the number of Ethnocentrists at tick 350. Through repeated observation of the output of all three variations of the model tested, this appers to represent a sufficiently stable, representative point in the output. Due to experimental limitations, testing on the number of the other three classes is done only between the NetLogo model and the Java implementation to give some insight into whether they are consistent. 

#Results
##Analysing the Output
###Experiment 1: Deterministic vs. Stochastic Updating
The updating method used by NetLogo is not explicitly stated. Here we see if a change in updating method results in a systematic change in the output of our Java model, and then explore which, if either, produce results more statistically similar to the NetLogo model.

Below are comparisons of the output of our Java model using NetLogo's default parameter settings, starting with an empty grid:

```
mutation-rate 								= .0050
death-rate									= .1
immigrants-per-day 							= 1
immigrant-chance-cooperate-with-same 		= .5
immigrant-change-cooperate-with-different	= .5
initial-PTR									= .12
cost-of-giving								= .01
gain-of-receiving							= .03
```

![Comparison of Outputs](Default Run/comparison.png)\
\centerline{Figure 2. Comparison of Model Outputs}

A comparison of the frequency histograms as well as the Kolgorov-Smirnov test reveals that at the default settings (with the initial state of the system unpopulated and a limit of 1 immigrant per turn) on a 30x30 space reveals that the results are significantly different. Our results imply that at these settings with stochastic updating matches the output from NetLogo far more closesly. 

We repeated this test with a fully populated grid and found that, contrary to our expectation, a comparison of the relevant histograms as well as the results of a Kolgorov-Smirnov test of the output from Deterministic vs Stochastic updating reveals that the resulting distributions of generated values are significantly different. 

Once again our stochastically updating java implementation more closely matches the equivalent NetLogo output.

##Experiment 2: NetLogo vs. Java with Stochastic Updating
###Default values



###Joint Variation



#Discussion
Discuss inherent bias to birth to left of agent if matrix is sparse due to reproduce function's implementation. (looks for empty space at left first)

Discuss use of Kolmogorov-Smirnov with relatively small samples
Discuss choice of KS given apparently normally distributed results. Was it necessary? Put choice down to the fact that the Shapiro-Wilk measured greater normality in NetLogo output vs our own, and KS allowed our tests to remain robust to samples that were not normally distributed. 

Discuss use of Shapiro-Wilk and introduced bias by large datasets (ours is not too large, relatively speaking)

Shapiro Wilk seems to suggest normal distribution quite strongly, particularly for NetLogo output. Discuss.


Discuss use of histogram to verify result of Shapiro-Wilk

Discuss that most comparisons test only on CD, using as an indicator. Not enough time to run and compare for all. 

Discuss possibility of getting multiple samples from surrounding tick numbers to improve sample size without large computation cost. Might provide better 
results.

#References

[Axelrod & Hammond, 2003](http://www-personal.umich.edu/~axe/research/AxHamm_Ethno.pdf), Midwest Political Science Convention, April 3-6, 2003, Chicago, IL

[Rapoport & Chammah, 1970](http://www.press.umich.edu/pdf/9780472061655-fm.pdf), 'Prisoner's Dilemma - A Study in Conflict and Cooperation', The University of Michigan, ISBN 0-472-96165-8

[Huntington, 1996](http://www.academia.edu/4610592/Samuel_P_Huntington_The_Clash_of_Civilizations_and_the_Remaking_of_World_Order_1996) The Clash of Civilizations and the Remaking of World Order. New York: Simon and Schuster, ISBN 0-684-81164-2

#Appendix
![NetLogo](Histograms/NetLogoEmptyDefaultHistogram.png)
\centerline{Figure 1.1.}

![Java - Stochastic](Histograms/JavaStochEmptyDefaultHistogram.png)
\centerline{Figure 1.2.}

![Java - Deterministic](Histograms/JavaDetEmptyDefaultHistogram.png)
\centerline{Figure 1.3.}

![Java: Empty, Stochastic vs Deterministic, Kolmogorov-Smirnov](Tables/StochvsDetEmptyKS.png)
\centerline{Figure 1.4.}

![NetLogo](Histograms/NetLogoFullDefault.png)
\centerline{Figure 1.4.}

![Java - Stochastic](Histograms/JavaFullStochasticDefault.png)
\centerline{Figure 1.5.}

![Java - Deterministic](Histograms/JavaFullDeterministicDefault.png)
\centerline{Figure 1.5.}

![Java: Full, Stochastic vs Deterministic, Kolmogorov-Smirnov](Tables/KSJavaDetvsJavaStochDefaultFull.png)
\centerline{Figure 1.4.}

![NetLogo v Java Stochastic, Empty, Kolmogorov-Smirnov](Tables/NetLogoJavaDefaultEmptyKS.png)
\centerline{Figure 1.4.}

![NetLogo v Java Stochastic, Full, Kolmogorov-Smirnov](Tables/KSNetLogoJavaDefaultFull.png)
\centerline{Figure 1.4.}

![NetLogo v Java Stochastic, Full, Kolmogorov-Smirnov](Tables/KSNetLogoJavaDefaultFull.png)
\centerline{Figure 1.4.}

![NetLogo Normality - Shapiro Wilk](Tables/NetLogoEmptyDefaultDS.png)
\centerline{Figure 1.4.}

![Java Stochastic Normality - Shapiro Wilk](Tables/JavaEmptyDefaultDS.png)
\centerline{Figure 1.4.}

![Java Deterministic Normality - Shapiro Wilk](Tables/JavaDeterministicEmptyDefaultDS.png)
\centerline{Figure 1.4.}