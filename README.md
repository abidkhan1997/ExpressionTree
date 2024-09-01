//import java.util.Stack;

// Homework T:  Expression trees.

// In the project you need to code one class that extends the abstract class ExpressionTree.  

// Your class needs to code one constructor and one output method as well as any helper methods
// that you find useful.
// The goal is to process mathematical expressions like (1+2)*(3+4) that are entered as strings.

// In the main program that tests your class,
// the Utility getInput method reads a mathematical expression that is typed as input.
// The expression can contain any combination of numbers, variable identifiers and mathematical operations.
// These mathematical operations are +, -, *, /, ( and )

// The constructor should turn an input expression into the content of a binary (expression) tree.
// The tree can then be printed in prefix, postfix, or fully parenthesised infix notation.
// The first two of these methods have been coded for you in the abstract class ExpressionTree, you need to code the third.

// For example the expression   5 + (x / y + z * 7) - 2  would be printed as
//                           - + 5 + / x y * z 7 2  in prefix order
// or                          5 x y / z 7 * + + 2 -  in postfix order
// or               (( 5 + ((x / y) + (z * 7))) - 2)  in fully parenthesised infix notation.


// To simplify things in this project the - sign can only be used between 2 quantities that are being subtracted.
// This will prevent you from ever working with a negative number such as -2 which would involve a different
// use for the - character.  However the simplification means that your code does not need to work out which meaning
// to attach to any copy of a - character.  

// Another simplification for this project is that you may assume that only correct mathematical expressions are entered.
// You do not need to check that an input String from getInput makes sense as an expression.
// If an illegal expression is encountered, your program can behave in any way that is convenient for you.

// You do not need any more than 100 lines of code in the class.

// The hard part of this assignment is the constructor.  
// In terms of planning it, I suggest that you think about which operation is the last to be performed in the expression.
// You can finish quickly using a recursion.
//
// The fullyParenthesised method can also be done using a recursion --- 
//  this will require an auxiliary recursive method to call on.

// Suggested strategy:
// 1. Make a constructor that deals with expressions without parentheses  (1st step partial credit).
// 2. Make a fullyParenthesised output method  (2nd step partial credit).
// 3. Adapt the constructor to deal with parentheses.  Hint a Stack will help here.  (Last part of credit).
