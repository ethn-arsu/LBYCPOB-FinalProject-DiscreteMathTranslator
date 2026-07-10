# Discrete Math Translator - A Rule-Based English-to-Discrete Mathematics Notation Translator

### TEAM MEMBERS
Ethan Edgar C. Arsua - ethn-arsu

Aeon Alphonse E. Mauricio - aeons-spaghetti

Zamuel Paolo L. Robes - zamuelrobes-cmd

### PROBLEM STATEMENT & GOALS
Discrete Mathematics is a core subject in many Computer Studies, Engineering, and Mathematics programs. Students often find it challenging to convert English statements into formal discrete mathematics notation, particularly in topics such as propositional logic, predicate logic, and basic set theory. The project aims to address this learning gap by providing a rule-based translator that converts supported English sentence patterns into their corresponding discrete mathematics notation.

The project aims to:
- Translate supported English statements into formal discrete mathematics notation.
- Help students understand the relationship between English statements and mathematical symbols.
- Provide a simple, user-friendly educational tool developed with object-oriented programming (OOP) principles.

### TARGET USER
- Information Technology students
- Computer Science students
- Computer Engineering students
- Engineering students taking Discrete Mathematics
- Mathematics students taking introductory logic and set theory.

### BRIEF DESCRIPTION
The Discrete Math Translator is a Java-based program designed to translate standard English sentence structures into their discrete mathematics equivalents. The application is designed to accept English input (derived from a set of sentence structures) and recognize common logical structures, including conjunctions, disjunctions, implications, and negations.

After the translation, the program displays mathematical notation along with an explanation of which logical rules were found and how they are present in the given sentence. If the entered statement does not match any supported pattern, the application will handle the exception, inform the user which kind of input is accepted by the program, and suggest supported input formats. 

The program uses a rule-based approach and supports predefined sentence structures commonly used in introductory Discrete Mathematics. As such, the program is not intended for translating unrestricted natural language.

### CORE OOP CONCEPTS
- Encapsulation:
  - Each translation rule contains its own internal fields and translation logic. 
  - Classes use private fields and public methods to ensure that data is accessed and can only be modified through controlled methods.

- Inheritance: 
  - Different translation rule classes inherit methods from a common TranslationRule abstract class, allowing them to share common behaviors while implementing their own logic without the need to rewrite methods for each translation rule.
  
- Polymorphism:
  - Each translation rule overrides shared methods defined in the parent class. 
  - At runtime, the program will determine the appropriate translation rule based on the user's input and executes the corresponding implementation without needing to know the specific translation being used. 
  
- Abstraction:
  - The TranslationRule abstract class defines common behaviors that every translation rule must implement without specifying how they are performed. 
  - The main translator interacts with these rules through the abstract class, allowing it to use the different translation rules without depending on their individual implementations.

### INITIAL CLASS IDEAS
- TranslationRule: 
  - Abstract class that serves as the blueprint for all translation rules.
- ConditionalRule: 
  - Implements translation for implication statements.
- ConjunctionRule: 
  - Implements translation for conjunction statements.
- NegationRule: 
  - Implements translation for negation statements.
- TranslatorEngine: 
  - Coordinates the translation process by selecting the appropriate rule based on user input.
- Expression: 
  - Stores the original English statement, mathematical notation, and generated explanation.
- TranslationResult: 
  - Stores the final notation, assigned variables, detected rule, and explanation before displaying it to the user.
- UserInterface: 
  - Handles user interaction via JavaFX.

### USER STORIES
- As an engineering student, I want to input an implication statement so that I can instantly see its formal notation.
- As a struggling discrete math student, I want to see a breakdown of the translation process so that I may learn and improve my discrete math skills.
- As a computer science student, I want to know when my input is unsupported so I can rewrite it correctly.

### CORE FEATURES:
- Pattern Matching & Translation:
    - Rule-based pattern matching to recognize supported English sentence structures.
    - Translation of English statements into discrete mathematics notation.
- Legend & Variable Assignment:
    - Legend generation that maps variables to their corresponding English statements.
    - Automatic variable assignment (e.g., P, Q, R) for identified propositions.
- Step-by-step Breakdown:
    - Step-by-step explanation of how the translation was performed.
    - User-friendly feedback for unsupported or invalid sentence structures.
    - "Supported Formats" reference page containing examples of accepted sentence patterns.
  
### SAMPLE IMAGES:
- Main Menu:
<img width="1920" height="1080" alt="1" src="https://github.com/user-attachments/assets/e8b9b66e-494a-4557-b29a-d0b1be564a4d" />

- Translation Page:
<img width="1920" height="1080" alt="2" src="https://github.com/user-attachments/assets/5378538b-a96d-4ef9-9723-638d30d094fe" />

- Results:
<img width="1920" height="1080" alt="3" src="https://github.com/user-attachments/assets/5e384d26-9157-43a6-a366-eda33ecd10e6" />

- Supported Formats page:
<img width="1920" height="1080" alt="4" src="https://github.com/user-attachments/assets/13711bc3-6b1f-4306-b00b-d1244246185a" />

- About page
<img width="1920" height="1080" alt="5" src="https://github.com/user-attachments/assets/9d4b37c7-a259-4cde-861d-aed9d755587d" />


