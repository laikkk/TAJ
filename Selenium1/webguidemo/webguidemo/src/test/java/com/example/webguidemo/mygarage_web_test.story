Scenario: User searches for a single step
 
Given user is on Home page
When click on Enter to Garage button
Then Login page appear

Given user is on Home page
When click on Enter to Garage button
When login as test@test.pl 123456
Then alertbox contains Invalid

Given user is on Home page
When click on Enter to Garage button
When click on register link
Then Register page appear

Given user is on Home page
When click on Enter to Garage button
When click on register link
When register as test test@test.pl haslo haslo
Then register alertbox contains UserName