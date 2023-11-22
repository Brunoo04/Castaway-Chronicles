## LDTS_project-l01gr03 - <PROJECT NAME>

**Our project**:

In this exciting game you arrive at an isolated island after a ship wreck and you receive unsettling news.

This project was developed by *Bruno Aguiar* (*up202205619*@fe.up.pt), *Francisco Fernandes* (*up202208485*@fe.up.pt) and *Lara Coelho* (*up202208689*@fe.up.pt) for LDTS 2023⁄24.

### IMPLEMENTED FEATURES

- 
- Images

### PLANNED FEATURES

- Explore the island;
- Talk with characters;
- Interact with objects;
- Progress in the story;
- Uncover new endings.

### DESIGN

- **Problem in Context.** The description of the design context and the concrete problem that motivated the instantiation of the pattern. Someone else other than the original developer should be able to read and understand all the motivations for the decisions made. When refering to the implementation before the pattern was applied, don’t forget to [link to the relevant lines of code](https://help.github.com/en/articles/creating-a-permanent-link-to-a-code-snippet) in the appropriate version.
- **The Pattern.** Identify the design pattern to be applied, why it was selected and how it is a good fit considering the existing design context and the problem at hand.
- **Implementation.** Show how the pattern roles, operations and associations were mapped to the concrete design classes. Illustrate it with a UML class diagram, and refer to the corresponding source code with links to the relevant lines (these should be [relative links](https://help.github.com/en/articles/about-readmes#relative-links-and-image-paths-in-readme-files). When doing this, always point to the latest version of the code.
- **Consequences.** Benefits and liabilities of the design after the pattern instantiation, eventually comparing these consequences with those of alternative solutions.

**Example of one of such subsections**:

------

#### THE PLAYER CAN CHANGE BETWEEN STATES(GAME, BACKPACK, MAP)

**Problem in Context**

There would be a lot of scattered conditional logic when changing the between the different states and within them, as we can have different backgrounds, objects, NPCs and locations that change as the game develops. This is a violation of the **Single Responsability Principle** [...]

**The Pattern**

We have applied the **State** pattern. This pattern allows you to represent different states with different subclasses. We can switch to a different state of the application by switching to another implementation (i.e., another subclass). This pattern allowed to address the identified problems because […].

**Implementation**

The following figure shows how the pattern’s roles were mapped to the application classes.

![img](https://www.fe.up.pt/~arestivo/page/img/examples/lpoo/state.svg) (como passar imagem?)

These classes can be found in the following files:

- Files with classes.

**Consequences**

The use of the State Pattern in the current design allows the following benefits:

- 

#### KNOWN CODE SMELLS

> This section should describe 3 to 5 different code smells that you have identified in your current implementation.

### TESTING

- Screenshot of coverage report.
- Link to mutation testing report.

### SELF-EVALUATION

> In this section describe how the work regarding the project was divided between the students. In the event that members of the group do not agree on a work distribution, the group should send an email to the teacher explaining the disagreement.

**Division of work**:

- Bruno: 33,3333%
- Francisco: 33,3333%
- Lara: 33,3333%
