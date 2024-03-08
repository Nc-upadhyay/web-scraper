# Grab Food Delivery Web Scraper

This project is a web scraper developed to extract specific information from the Grab Food Delivery website for
locations in Singapore. It extracts restaurant lists and their details, such as cuisine, rating, delivery time,
promotional offers, and more.

## Objective

The objective of this assignment is to develop a web scraper to extract specific information from the Grab Food Delivery
website. The scraper should be able to handle challenges such as blocking, authentication, etc., during the data
extraction process.

## Technologies Used

This project is implemented using the following technologies:

- Java 17
- Spring Boot
- Jsoup (for web scraping)
- Logging (for logging messages)
- Gzip (for compressing extracted data into .gz files)
- Swagger (for API documentation)

## Problem Statement

Develop a scraper to scrape Grab Food Delivery for locations in Singapore. Extract restaurant lists and their details
according to the provided schema. Implement the scraper using object-oriented programming concepts and optimize it for
scalability.

## Tasks Performed by the Scraper

- Extract the restaurant list of the selected location along with restaurant details.
- Create a unique restaurant list.
- Extract the fee delivery and estimated delivery.

## Result Format

The extracted data is saved in gzip of ndjson format and also provided to user by api.

## Programming Contracts

- Code follows OOP concepts.
- Code structure follows programming language standards.
- Code is optimized for higher load using multithreading, multiprocessing, or other paradigms.
- Consistent comment and logging format is used.

## Documentation

- Comprehensive comments explain the purpose of each section of the code.

## Overall Approach and Methodology

The web scraper was developed using Java 17 and Spring Boot framework. Jsoup library was utilized for web scraping. The
scraper extracts information from the Grab Food Delivery website for specified locations in Singapore. It follows
object-oriented programming concepts and adheres to programming language standards.

The approach involved fetching the HTML document from the target website, parsing it using Jsoup, and extracting
relevant information such as restaurant names, cuisines, ratings, delivery times, promotional offers, and more. The
extracted data is then structured into a schema-defined format and logged for further analysis.

# Quality Control (QC)

The following quality control measures have been implemented to ensure the reliability and accuracy of the web scraper:

## 1. Error Handling

- **Comprehensive Exception Handling:** The code includes robust exception handling to handle various types of errors,
  such as missing data, connection timeouts, and unexpected changes in website structure.

- **Logging of Errors:** Errors and exceptions are logged with appropriate details to facilitate debugging and
  troubleshooting.

## 2. Data Integrity

- **Data Validation:** Extracted data is validated to ensure its integrity and consistency with the expected schema.

- **Handling Dynamic Content:** The scraper has been designed to handle dynamic content loaded via JavaScript, ensuring
  comprehensive data extraction.

## 2. Performance Optimization

- **Minimization of Response Time:** Efforts have been made to minimize response time by optimizing network requests and
  data processing algorithms.

## 4. Documentation and Logging

- **Comprehensive Comments:** The code includes comprehensive comments to explain the purpose of each section, method,
  and variable, enhancing readability and maintainability.

- **Logging Format:** Consistent logging format has been maintained throughout the codebase to facilitate monitoring and
  debugging.

## Challenges Faced During Scraping Process

- **HTML Structure Variations:** One challenge encountered was the variations in the HTML structure of the website,
  which required careful selection of CSS selectors to accurately extract the desired information.

- **Handling Exceptions:** Exception handling was crucial to address errors such as missing data, connection timeouts,
  and unexpected changes in website structure.

- **Extracting dynamic data:** Extracting dynamic data or data loaded at runtime by scripting languages is challenging.
  For example, images loaded into the page after a delay of 1 or 2 seconds.

## Possible Improvements or Optimizations

- **Enhanced Error Handling:** Implement more robust error handling mechanisms to gracefully handle unexpected errors
  and edge cases encountered during scraping.

- **Rate Limiting Strategies:** Implement rate limiting strategies to ensure compliance with the website's terms of
  service and prevent IP blocking.

- **Scalability:** Optimize the scraper for higher load by incorporating multi-threading or asynchronous processing
  techniques to improve performance and scalability.

- **Dynamic Content Handling:** Enhance the scraper to handle dynamic content loaded via JavaScript to extract more
  comprehensive data.

- **Proxy Rotation:** Integrate proxy rotation mechanisms to avoid IP blocking and distribute requests across multiple
  IP addresses.

- **Monitoring and Logging:** Implement comprehensive logging and monitoring to track scraping activities, detect
  errors, and analyze performance metrics.

- **Automated Testing:** Develop automated tests to validate the scraper's functionality and ensure robustness against
  website changes.

By addressing these areas, the scraper can be further improved to enhance its reliability, scalability, and compliance
with website policies, thereby optimizing the data extraction process from the Grab Food Delivery website.

## Usage

1. Run the provided scraper to extract data from the Grab Food Delivery website.
2. Access the extracted data in gzip of ndjson format or API format for further analysis or processing.

## Submit By

- [Naveen Chandra]
- [nc.upadhyay20@gmail.com]

