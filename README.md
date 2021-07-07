# Api testing with RestAssured framework with maven

- RestAssured official website [click here][rest-assured]
- Maven official website [click here][maven]
## Testing

### Test framework for next 2 pages
1. Reqres
   Official website [Click here][reqres]

2. HttpBin
   Official website [Click here][httpbin] 

&nbsp;
### Test run with default environment

- Open terminal in project root
- Run `mvn test` command

&nbsp;
### Test run with custom environment

* Open terminal in project root
* Run `mvn test -DhttpBinBaseUrl=<ip-address-or-url-of-httpbin> -DreqresBaseUrl=<ip-address-or-url-of-reqres>`
    * example `mvn test -DhttpBinBaseUrl=https://reqres.in/api`

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
[rest-assured]: <https://rest-assured.io/>
[maven]: <https://maven.apache.org/>
[reqres]: <https://reqres.in/>
[httpbin]: <https://www.httpbin.org/>


