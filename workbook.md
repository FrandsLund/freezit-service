# Workbook for the work to be done

## Hexagonal Architecture - Sources

[Hexagonal Architecture – What Is It? Why Use It?](https://www.happycoders.eu/software-craftsmanship/hexagonal-architecture/)
[Hexagonal Architecture with Java – Tutorial](https://www.happycoders.eu/software-craftsmanship/hexagonal-architecture-java/)

## Technical debt

### Fix multiple resources folders

There shouldn't be a separate resource folder for each module, unless they overwrite the parents resource

### Figure out where to place this dependency

        <dependency>
            <groupId>io.quarkus</groupId>
            <artifactId>quarkus-arc</artifactId>
        </dependency>

### Find BOM for current quarkus version

- Find the BOM for current quarkus version to ensure that all dependency versions are listed, or if you need to add a
  version manually

### Consider custom exceptions for post conditions

[NotEnoughItemsInStockException](https://www.happycoders.eu/software-craftsmanship/hexagonal-architecture-java/#:~:text=in%20service%20classes.-,NotEnoughItemsInStockException,-The%20increaseQuantityBy(%E2%80%A6))

## Read through the following:

### Understand the different pom scopes

### How to import dependecies the correct way

[Importing APIs or Implementations?](https://www.happycoders.eu/software-craftsmanship/hexagonal-architecture-quarkus/#:~:text=Importing%20APIs%20or%20Implementations%3F)