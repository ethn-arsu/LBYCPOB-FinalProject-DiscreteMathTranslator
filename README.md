## Discrete Math Translator - A Rule-Based English-to-Discrete Mathematics Notation Translator

### TEAM MEMBERS:

Ethan Edgar C. Arsua - ethn-arsu

Aeon Alphonse E. Mauricio - foobar-trashdump

Zamuel Paolo L. Robes - zamuelrobes-cmd


### PROBLEM STATEMENT & GOALS:
The curriculum offered in several computer science, engineering, or mathematics programs at De La Salle University (DLSU) includes discrete mathematics as an essential and important subject for students in their respective fields. This subject is known to be difficult for students to translate English statements into mathematical notation, particularly in propositional logic, predicate logic, and basic set theory. The project aims to address this learning gap by providing a rule-based translator that converts supported English sentence patterns to mathematical expressions.

The project aims to:
- Translate supported English statements into discrete mathematical notation.
- Help students understand the relationship between English statements and mathematical symbols.
- Provide a simple, user-friendly educational tool developed with object-oriented programming (OOP) principles.

### TARGET USER:
- Computer Science and/or Engineering students
- Information Technology students
- Engineering students taking discrete mathematics
- Mathematics students taking introductory logic and set theory

### BRIEF DESCRIPTION:
The Discrete Math Translator is a Java-based program designed to translate standard English sentence structures into their discrete mathematics equivalents. The application is designed to accept English input (derived from a set of sentence structures) and recognize common logical structures, including conjunctions, disjunctions, implications, and negations.

After the translation, the program displays mathematical notation along with an explanation of which logical rules were found and how they are present in the given sentence. If the program fails to find any notation in the given sentence, the application will handle the exception and inform the user which kind of input is accepted by the program. 


### CORE OOP CONCEPTS
- Encapsulation: 
-	The logic components/ translation rules will contain their own internal data and logic. Program objects contain   private fields and public methods, allowing controlled access through said methods.
- Inheritance:
- Different translation rules inherit the methods from a shared parent class.
- Polymorphism: 
-	Individual translation implementation between objects is done via overriding the shared methods present, given a parent class. The program will select an appropriate rule at runtime based on user input.
- Abstraction: 
-	TranslationRule abstract parent class has methods that define shared behaviors that every translation rule must implement. The main will interact with these rules through the abstract without needing to know the individual implementation of the objects.

### INITIAL CLASS IDEAS:
- TranslationRule: Abstract class that serves as the blueprint for all translation rules
- ConditionalRule, ConjunctionRule, NegationRule: Concrete classes that implement their specific logic for translation
- TranslatorEngine: Controller class that orchestrates the translation's workflow

### USER STORIES (Recommended):
- As an engineering student, I want to input an implication statement so that I can instantly see its formal notation.
- As a struggling discrete math student, I want to see a breakdown of the translation process so that I may learn and improve my discrete math skills.

### CORE FEATURES (Recommended):
- Pattern Matching & Translation
- Legend & Variable Assignment
- Step-by-step Breakdown
