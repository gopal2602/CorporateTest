package objectLocators;

public interface ObjectLocators {
	final String findFlights="//input[@value='Find Flights']";
	final String departureCity="//select[@name='fromPort']";
	final String destinationCity="//select[@name='toPort']";
	final String flightsSearchResultPage="//h3[contains(text(), 'Flights from ')]";
	final String choosethisFlight="//input[@value='Choose This Flight']";
	final String name="//input[@id='inputName']";
	final String address="//input[@id='address']";
	final String city="//input[@id='city']";
	final String state="//input[@id='state']";
	final String zipCode="//input[@id='zipCode']";
	final String cardType="//select[@id='cardType']";
	final String creditCardNumber="//input[@id='creditCardNumber']";
	final String creditCardMonth="//input[@id='creditCardMonth']";
	final String creditCardYear="//input[@id='creditCardYear']";
	final String nameOnCard="//input[@id='nameOnCard']";
	final String purchaseFlight="//input[@value='Purchase Flight']";
	final String purchaseConfirmation="//h1[text()='Thank you for your purchase today!']";
}
