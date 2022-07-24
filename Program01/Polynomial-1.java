//
// Ethan Gutknecht
// Program 1 - Polynomials
// The purpose of this program is to manipulate polynomials
// 9/20/2020
//


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Polynomial {
	
	
	private ArrayList<Term> terms;
	private class Term implements Comparable<Term> {
		double coefficient;
		int exponent;

		public Term(double c, int e) {
			coefficient = c;
			exponent = e;
		}
		
		@Override
		public int compareTo( Term node ) {
			
			return node.exponent - this.exponent;
		}
	}



	public Polynomial() { // default constructor
		terms = new ArrayList<Term>();
	}

	
	
	public Polynomial(Polynomial poly) { // copy constructor
		terms = poly.terms;
	}

	
	
	public Polynomial(ArrayList<Double> coef, ArrayList<Integer> expon) {
		terms = new ArrayList<Term>();
		
		if ( coef.size() != expon.size() )
			throw new IllegalArgumentException("Error in input in constructor");
		
		for ( int i = 0; i < expon.size(); i++) {
			Term term = new Term(coef.get(i), expon.get(i));
			terms.add(term);
		}
	}


	
	public boolean equals(Polynomial poly) { // checks for equality
		if (this.toString().equals(poly.toString()) == true) {
			return true;
		} else return false;
	}

	
	
	public Polynomial add(Polynomial poly) {
		int i = 0; // i corrisponds to this
		int j = 0; // j corresponds to poly
		Polynomial sum = new Polynomial();
		
		while (i < terms.size() && j < poly.terms.size()) {
			if (terms.get(i).exponent == poly.terms.get(j).exponent) {
				Term newTerm = new Term(terms.get(i).coefficient + poly.terms.get(j).coefficient, terms.get(i).exponent);
				
				if (newTerm.coefficient != 0) sum.terms.add(newTerm);
				i++; j++;
				
				
			} else if (terms.get(i).exponent > poly.terms.get(j).exponent) {
				sum.terms.add(terms.get(i));
				i++;
				
				
			} else {
				sum.terms.add(poly.terms.get(j));
				j++;
				
				
			}
		}
		while (j < poly.terms.size()) {
			sum.terms.add(poly.terms.get(j));
			j++;
		}
		
		while (i < terms.size()) {
			sum.terms.add(terms.get(i));
			i++;
		}
			
		return sum;
	}


	
	public Polynomial subtract(Polynomial poly) {
		//create a polynomial that is simply a constant of -1
		ArrayList<Double> coefficient = new ArrayList<Double>(Arrays.asList(-1.0));
		ArrayList<Integer> exponent = new ArrayList<Integer>(Arrays.asList(0));
		Polynomial polyNegOne = new Polynomial(coefficient, exponent);
		System.out.println(poly.toString());
		
		Polynomial negatedPolynomial = new Polynomial (poly.multiply (polyNegOne) );
		System.out.println(negatedPolynomial.toString());
		
		Polynomial difference = new Polynomial( add(negatedPolynomial) );
		
		return difference;
	}

	
	
	public Polynomial multiply(Polynomial poly) {
		ArrayList<Double> coefficients = new ArrayList<Double>();
		ArrayList<Integer> exponents = new ArrayList<Integer>();
		
		for (Term thisTerm : terms) {  //loop through all terms in this product
			for (Term polyTerm : poly.terms) { //go through every polyTerm for every thisTerm
				exponents.add(thisTerm.exponent + polyTerm.exponent);
				coefficients.add(thisTerm.coefficient * polyTerm.coefficient);
			}
		}
		
		
		//Used this loop for debugging
		//for (int i = 0; i < exponents.size(); i++) {  //loop through all terms in this product
		//	System.out.println("   Exponent #" + i + " :  " + exponents.get(i));
		//	System.out.println("Coefficient #" + i + " :  " + coefficients.get(i));
		//}
		
		
		//create a loop that will combine like terms
		for (int currentTermIndex = 0; currentTermIndex < coefficients.size(); currentTermIndex++) {
			for (int termToCheckIndex = 0; termToCheckIndex < coefficients.size(); termToCheckIndex++) {
				
				//If they are not the same number and the exponents are the same - combine like terms
				if (currentTermIndex != termToCheckIndex && exponents.get(termToCheckIndex) == exponents.get(currentTermIndex)) {
					coefficients.set(currentTermIndex, coefficients.get(currentTermIndex) + coefficients.get(termToCheckIndex));
					coefficients.remove(termToCheckIndex);
					exponents.remove(termToCheckIndex);
					termToCheckIndex--;
				}
			}
		}

		
		//Sort the terms in correct order using bubble sort
		int tempStorageExp;
		double tempStorageCoeff;
		boolean sorted = false;
		
		while (!sorted) {
			sorted = true;
			
			for (int j = 0; j < exponents.size() - 1; j++) {
				if (exponents.get(j) < exponents.get(j + 1)) {
					//temp stores values
					tempStorageCoeff = coefficients.get(j);
					tempStorageExp   = exponents.get(j);
					
					//switches lower index with higher
					coefficients.set(j, coefficients.get(j + 1));
					exponents.set(j, exponents.get(j + 1));
					
					//switches high index with temp Storage of higher
					coefficients.set(j + 1, tempStorageCoeff);
					exponents.set(j + 1, tempStorageExp);
					
					sorted = false;
				}
			}
		}
		
		
		//there was probably a WAYYYY easier way to code this but my method works :)
		Polynomial multiply = new Polynomial(coefficients, exponents);
		return multiply;
	}

	
	
	public double evaluate(double value) { // evaluate the polynomial with the parameter value for the variable
		double returnValue = 0.0;
		
		for (Term term : terms) {
			returnValue += term.coefficient * Math.pow(value, term.exponent);
		}
		
		return returnValue;
	}

	
	
	//RETURNS A POLLYNOMIAL CHANGE IT BACK
	public Polynomial derivative() { // finds the derivative of host polynomial
		Polynomial der = new Polynomial();
		for (Term term : terms) {
			if (term.exponent != 0) {
				Term newTerm = new Term(term.coefficient * term.exponent, term.exponent - 1);
				der.terms.add(newTerm);
			}
		}
		
		return der;
	}

	
	
	public String toString() {
		StringBuilder result = new StringBuilder();

		for (Term term : terms) {
			if (Math.abs(term.coefficient) < 0.0000000001) { // skip if coefficient is zero
			} else if (term.exponent > 1) {
				if (term.coefficient > 0.0) {
					result.append("+ " + term.coefficient + "x^" + term.exponent + " ");
				} else {
					result.append("- " + -1 * term.coefficient + "x^" + term.exponent + " ");
				}
			} else if (term.exponent == 1) {
				if (term.coefficient > 0.0) {
					result.append("+ " + term.coefficient + "x ");
				} else {
					result.append("- " + -1 * term.coefficient + "x ");
				}
			} else { // term.exponent == 0
				if (term.coefficient > 0.0) {
					result.append("+ " + term.coefficient + " ");
				} else {
					result.append("- " + -1 * term.coefficient + " ");
				}
			}
		}
		if (result.length() == 0)
			return "0";
		if (result.charAt(0) == '+')
			result.deleteCharAt(0);
		while (result.charAt(0) == ' ')
			result.deleteCharAt(0);
		while (result.charAt(result.length() - 1) == ' ')
			result.deleteCharAt(result.length() - 1);
		if (result.charAt(0) == '-' && result.charAt(1) == ' ') result.deleteCharAt(1);

		return result.toString();
	}
}
