### Market Price Handler Solution

This solution is made using Spring Boot :) 

To run the application, execute:
```bash
gradlew bootRun
```

The endpoint of latest price is available at:
[http://localhost:8080/price/latestPrice](http://localhost:8080/price/latestPrice)

To run tests, execute:
```bash
gradlew test
```

To see the latest price output, please run:
```bash
gradlew test --tests "com.santanderefx.rest.PriceRestTest.printLatestPriceEndpoint"
```