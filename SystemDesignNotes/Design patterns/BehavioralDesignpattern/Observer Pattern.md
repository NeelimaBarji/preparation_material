# Observer Pattern



# Command Pattern
Command Pattern is one of the Behavioral Design Pattern. It is used to implement loose coupling in a request-response model.

Problem:

Imagine you're developing a basic text editor with buttons for bold, italic, and
underline text formatting.
Without the Command Pattern, the buttons directly interact with the TextEditor
class, and you’d end up hardcoding behavior into the UI classes, making them tightly
coupled.

By introducing the Command Pattern, we can decouple the actions (bold, italic,
underline) from the UI components (buttons), making the design more flexible and
maintainable. The buttons no longer need to know about the editor directly but
instead work with generic Command objects.

Structure:
● Command: Interface for executing operations.
● Invoker: Sends the command.
● Receiver: Performs the operation.

Command Pattern Benefits
Decoupling of Invoker and Receiver: The button (invoker) doesn't know the
details of the TextEditor (receiver), making the system more flexible and
reusable.
● Command History and Undo: Commands can be logged for undo/redo
functionality.
● Task Queuing: Commands can be stored in a queue and executed later,
making it useful for task scheduling.
● Extensibility: New commands can be added easily without modifying existing
code. For example, adding a ChangeColorCommand only requires creating a
new command class.


<img width="912" height="664" alt="Screenshot 2026-09-24 at 1 23 29 PM" src="https://github.com/user-attachments/assets/ce1c8fb9-b07d-4dcd-894a-806553313c61" />

Command Pattern Use Cases
GUI Applications:
● Commands can be associated with buttons, menus, and keyboard shortcuts in
applications like text editors, spreadsheets, or drawing software.
Task Scheduling:
● Commands can be placed in a queue and executed later, useful in batch
processing or deferred task execution

Undo/Redo Functionality:
● Commands can be stored and rolled back to provide undo and redo
capabilities, especially in applications like IDEs, word processors, or graphics
software.
Macro Recording:
● Actions performed by the user can be recorded as a series of commands,
which can then be played back as macros.
Increased Complexity: Introducing the Command Pattern can lead to more
classes and complexity, especially when there are many different commands.
● Overhead: Each operation becomes an object, which may add memory and
performance overhead in systems with large numbers of commands.


# Template Method Pattern

The Template Design Pattern is a behavioral design pattern that defines the basic structure of an algorithm in a superclass, while 
allowing subclasses to provide specific implementations of certain steps of the algorithm without modifying its overall structure. 
It promotes code reuse and enforces a common algorithm structure across multiple subclasses.

## Problem: 
Consider a scenario where you have different data parsers (e.g., CSV, XML,
and JSON). Each parser follows the same steps: open file, parse data, and
close file.
Without the Template Method Pattern, you might end up duplicating the
common steps in each parser class.

### problem in code:
Code duplication: The openFile() and closeFile() methods are
duplicated in both parsers.
● Any changes to the common logic would require changes in every parser,
violating the DRY (Don’t Repeat Yourself) principle.

## Solution:
Problem: Different parts of an algorithm may need to vary in subclasses, but the
overall structure should remain consistent.
Solution: The Template Method Pattern defines the skeleton of an algorithm in a
base class and lets subclasses override specific steps.
Structure:
● Abstract Class: Defines the algorithm skeleton.
● Concrete Subclasses: Override specific steps of the algorithm.


<img width="751" height="627" alt="Screenshot 2026-09-24 at 1 34 33 PM" src="https://github.com/user-attachments/assets/070a3c01-de85-4fc0-b098-b183058c440e" />


### Benefits

● Code Reuse: Common code is moved to the parent class, promoting reuse
and reducing duplication.
● Flexibility: Subclasses can vary certain steps in the algorithm, while keeping
the overall structure intact.
● Consistency: Ensures that the high-level structure of the algorithm remains
consistent, even when subclass behavior differs.

#### use cases:
UI Frameworks: Rendering a UI element might follow a fixed set of steps (initialize, draw, finish),
but the details of how each element is drawn are left to subclasses.
Document Processing: A framework might define the skeleton for reading, processing, and
saving documents, while specific formats (e.g., Word, PDF) provide their own processing logic.
Game Development: A game loop (initialize, update, render) can be defined in a base class, with
specific games implementing their own logic for updating and rendering.
The Template Method Pattern is ideal for situations where a common algorithm exists, but some
steps may need to be redefined by subclasses. It helps enforce structure and promotes
reusability, while allowing flexibility where needed.

----


# Iterator Pattern Motivation
Suppose you have a collection, such as an array or list, and you need to
provide a mechanism for accessing its elements. Without the iterator pattern,
the client code needs to understand how the collection is structured, and
different collections would require different methods to traverse them.

## Problems:
● The client needs to know the internal structure of the collection (array in this
case).
● If we change the collection type (e.g., from an array to a linked list), we would
need to modify the client code.
● It’s harder to implement different traversal strategies.

Problem: How to access elements in a collection without exposing its internal
representation.
Solution: The Iterator Pattern provides a way to traverse a collection without
revealing its underlying structure, offering a uniform interface for traversal.
Structure:
● Iterator: Interface for traversing a collection.
● Collection: Holds the elements and provides an iterator.

Iterator Pattern Benefits

Separation of Concerns: The traversal logic is separated from the collection
itself, allowing you to change one without affecting the other.
Uniform Interface: The same interface (Iterator) is used to traverse
different types of collections, making the code more flexible.
Simplified Client Code: The client doesn’t need to know the underlying data
structure, reducing coupling and making the code easier to maintain.
Multiple Traversal Strategies: You can implement multiple types of iterators
(e.g., forward, backward, filtered) without changing the collection.

Iterator Pattern Use Cases

1. Java Collections Framework:
○ The Java Collections Framework (e.g., ArrayList, HashSet) uses the iterator pattern
to provide a common interface (Iterator) for traversing different types of collections.
2. Database Cursors:
○ In database programming, cursors are used to iterate over result sets. The iterator
pattern can abstract this traversal, making it easier to work with data from a database
without exposing the underlying query mechanism.
3. Tree Traversals:
○ In tree data structures, the iterator pattern can be used to traverse nodes using different
strategies like depth-first or breadth-first, without exposing the tree's internal structure.
4. File Systems:
○ File systems can use the iterator pattern to traverse directories and files without exposing
the internal details of how files and folders are stored.


Iterator Pattern Drawbacks
1. 2. Additional Complexity: Implementing the iterator pattern can add extra layers of abstraction,
especially for small or simple collections where direct traversal is sufficient.
Increased Overhead: For small collections or when the structure is unlikely to change, the
overhead of creating iterators may not be justified.


