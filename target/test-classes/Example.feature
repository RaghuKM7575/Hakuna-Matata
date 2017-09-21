Feature: User Account-Test Login-Valid and Invalid Details

Scenario: User Account with valid user name or email and valid password credentials
Given a user want to use the browser[ browser]
And user should enter the valid URL
When a register user enter the valid user name or email
And a register user enter the valid password credentials
When user click on sign in button
Then page should navigate to home page
And user can able to login sucessfully

Scenario: User Account with invalid user name or email and valid password credentials
Given a user want to use the browser[browser]
And user should enter the valid URL
When a register user enter the invalid user name or email
And a register user enter the valid password credentials
When user click on sign in button
Then error message should be displayed

Scenario: User Account with valid user name or email and invalid password credentials
Given a user want to use the browser[browser]
And user should enter the valid URL
When a register user enter the valid user name or email
And a register user enter the invalid password credentials
When user click on sign in button
Then error message should be displayed


Scenario: User Account with without password credentilas
Given a user want to use the browser[browser]
And user should enter the valid URL
When a register user enter the valid user name or email
And  user click on sign in button
Then error message should be displayed as 'Password field is required'

Scenario: User Account with without user name or email
Given a user want to use the browser[browser]
And user should enter the valid URL
When a register user enter the valid password credentials
And  user click on sign in button
Then error message should be displayed as 'Username or e-mail address field is required'

Scenario: User Account with invalid user name or email and invalid password credentials
Given a user want to use the browser[ browser]
And user should enter the valid URL
When a register user enter the invalid user name or email
And a register user enter the invalid password credentials
When user click on sign in button
Then error message shoild be displayed as 'Sorry, unrecognised username or password'


| user name or email    |password   |browser         |
| kiran7575@outlook.com |kiran12345 |Firefox         |
| 123kiran@123.com      |kiran12345 |Chrome          |
| kiran7575@outlook.com |abcdefhuji |Internet Explorer|
| kiran7575@outlook.com |           |Safari           |
|                       |kiran12345 |Opera            |
|1234@123.com           |0909  0900 |IE 7,8,9,10 versions|