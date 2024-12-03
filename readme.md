Description

This project implements the FooBarQuix Kata, a simple algorithm that transforms a number into a string following specific rules. The application provides two implementations:
1.	A REST controller for real-time transformations.
2.	A batch processor for transforming numbers from a file and writing the results to another file.

Technologies Used

	•	Maven: Build and dependency management.
	•	Java: Core language (version 17 or later).
	•	Spring Boot: Framework for REST and Batch processing (version 3.4 or later).

Rules of Transformation

	1.	If the number is divisible by 3 or contains 3, the resulting string will include "FOO".
	2.	If the number is divisible by 5 or contains 5, the resulting string will include "BAR".
	3.	If the number contains 7, the resulting string will include "QUIX".
	4.	The rule “divisible by” takes precedence over the rule “contains”.
	5.	The content is analyzed from left to right.
	6.	If no rules are matched, the number is returned as a string.

Application Features

1. REST API

The application exposes an endpoint for transforming individual numbers:

Endpoint:
POST /api/v1/parse
Request:
{ number : "15" }
Response:
{
"result": "FOOBAR"
}

Setup and Usage

Prerequisites

	1.	Java 17 or higher installed.
	2.	Maven installed.

Steps to Run the Application

1. Clone the repository: 

        git clone https://github.com/your-repo/foo-bar-quix.git
        cd foo-bar-quix
2. Build the application:

         mvn clean install

3. Run the application:
        
        java -jar target/foo-bar-quix.jar

Running the REST API

After starting the application, the REST API will be available at http://localhost:8080. Use tools like Postman or Curl to test the API.
in the resources folder, there are an example with postman, that can be used to test the Rest API
