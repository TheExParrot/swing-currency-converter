# Java Swing Currency Converter

A currency conversion application that allows for users to easily convert between a variety of different currencies.
The currencies in the application are based on [Fawaz Ahmed's Currency API](https://github.com/fawazahmed0/currency-api), which includes country currencies as well as a large number of Cryptocurrencies.

The application functions using an intuitive and easy to use GUI that allows the user to select currencies and input a value. The application the automatically fetches the exchange rate from the API. The back-end of the application uses a cache to store exchange rates from previously used currencies to avoid excessive API calls, increasing performance and usability.

**APIs:** 
- https://github.com/fawazahmed0/currency-api

**Maven Dependencies**:
- [com.google.code.gson gson 2.10.1](https://mvnrepository.com/artifact/com.google.code.gson/gson/2.10.1) 
