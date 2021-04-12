Feature: Compose New Outlook Email
Description: To test the Compose email feature of outlook.

Scenario: User Logs in Outlook and sends a new mail to recipient
	Given User has successfully logged in Outlook application
	When User clicks on New Message button
	And drafts email and sends email  