package de.school;

import java.text.DecimalFormat;

public class Main {

	public static void main(String[] args) {
		new Main().start();
	}

	private final int starsAmount = 40;
	private final int subAmount = 56;

	public void start() {
		double gross = 2375.65;

		double wageTax = 20;
		double solidarityFee = 4;
		double pensionInsurance = 9.75;
		double healthInsurance = 7.3;
		double unemploymentInsurance = 3.25;
		double careInsurance = 0.8;

		double wage = calc(gross, wageTax);
		double solidarity = calc(wage, solidarityFee);
		double pension = calc(gross, pensionInsurance);
		double health = calc(gross, healthInsurance);
		double unemployment = calc(gross, unemploymentInsurance);
		double care = calc(gross, careInsurance);

		double sum = wage
			+ solidarity
			+ pension
			+ health
			+ unemployment
			+ care;
		double net = Double.parseDouble(round(gross - sum));

		System.out.print("\033[1m\t\t");
		printAmount('*', starsAmount);
		printWithStarsFormat("Dieses Programm berechnet");
		printWithStarsFormat("das Netto-Gehalt abhängig");
		printWithStarsFormat("vom eingegeben Brutto-Gehalt");
		printWithStarsFormat("");
		printWithStarsFormat("Angenommene Prozentsätze:");
		printWithStarsFormat("Lohnsteuer: " + round(wageTax) + "% vom Gehalt");
		printWithStarsFormat("Solidaritätsbeitrag: " + round(solidarityFee) + "% von der Lst.");
		printWithStarsFormat("Rentenversicherung: " + round(pensionInsurance) + "% vom Gehalt");
		printWithStarsFormat("Krankenversicherung: " + round(healthInsurance) + "% vom Gehalt");
		printWithStarsFormat("Arbeitslosenversicherung: " + round(unemploymentInsurance) + "% v. G.");
		printWithStarsFormat("Pflegeversicherung: " + round(careInsurance) + "% vom Gehalt");
		System.out.print("\t\t");
		printAmount('*', starsAmount);
		print("");
		print("\033[0m");
		printAmount('-', subAmount);
		print("\033[1m");
		print("");
		printAmount('-', subAmount);
		printWithSubFormat("Brutto Gehalt", gross);
		printAmount('-', subAmount);
		printWithSubFormat("Abzüge", null);
		printWithSubFormat("Lohnsteuer", wage);
		printWithSubFormat("Solidaritätsbeitrag", solidarity);
		printWithSubFormat("Rentenversicherung", pension);
		printWithSubFormat("Krankenversicherung", health);
		printWithSubFormat("Arbeitslosenversicherung", unemployment);
		printWithSubFormat("Pflegeversicherung", care);
		printAmount('-', subAmount);
		printWithSubFormat("Summe Abzüge", sum);
		printAmount('-', subAmount);
		printWithSubFormat("Gehalt Netto", net);
		printAmount('-', subAmount);
	}

	private void print(String text) {
		System.out.println(text);
	}

	private void printAmount(char character, int amount) {
		print(String.valueOf(character).repeat(Math.max(0, amount)));
	}

	private void printWithStarsFormat(String text) {
		printWithFormat("\t\t* " + text, starsAmount + 2, '*', false);
	}

	private void printWithSubFormat(String text, Double after) {
		printWithFormat("| " + text, subAmount - 8, '|', true);
		System.out.println(" " + (after == null ? "" : after));
	}

	private void printWithFormat(String text, int needed, char character, boolean after) {
		int length = text.length() - count(text, "ä")
			- count(text, "ö")
			- count(text, "ü");


		StringBuilder textBuilder = new StringBuilder(text);
		while (length < needed - 1) {
			textBuilder.append(" ");
			length++;
		}
		text = textBuilder.toString();

		text += character;

		if (after)
			System.out.print(text);
		else
			print(text);
	}

	private String round(double d) {
		DecimalFormat decimalFormat = new DecimalFormat("###.##");
		return decimalFormat.format(d).replace(",", ".");
	}

	private int count(String text, String what) {
		return text.split(what, -1).length - 1;
	}

	private double calc(double a, double b) {
		return Double.parseDouble(round(a * (b / 100)));
	}
}
