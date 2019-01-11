
# purpose:
turning requirements into unit tests, then a working spring-boot microservice

### Astoria: IACUC Commenting Rules
  * Commenter with assignment of Reviewer should be able to create a Comment on EformQuestion for a Submission
  * Commenter with assignment of Reviewer should be able to set Visibility on Comment to ReviewersOnly, Everyone.
  * Commenter with assignment of Reviewer can save Comment on Question on Submission
  * Commenter with assignment of Reviewer can edit own Comments
  * Commenter with assignment of Reviewer should only be able to change visibility of own comment
  * Commenter with assignment of Reviewer should not be able to change the visibility of another reviewer's comment (unless Analyst or Admin)
  * Commenter with assignment of Reviewer should be able to change Status of own Comment, but cannot change the status of other's comments (unless Analyst or Admin)
  * Commenter with assignment of Reviewer should be able to change the status of a comment with visibility set to "Visible to Everyone"
  * Commenter with assignment of Reviewer can add Comments to Questions until the Submission is closed, approved.
  * Commenter with assignment of Reviewer when Reviewer saves edited Comment, the date/timestamp will be marked Edited
  * Commenter with assignment of Reviewer should be able to see the Name of the Reviewer on a Comment
  * Commenter with assignment of Researcher should be able to reply to Comment on Submission with Status "unaddressed"
  * Commenter with assignment of Researcher cannot change Comment Visibility
  * Commenter with assignment of Researcher can edit own Comments
  * Commenter with assignment of Researcher does not see Reviewer's name on Comments. Instead of reviewer's full name, show "Reviewer"
  * Comment default status is "General"
  * Comment status can also be set to "Unaddressed / Addressed and Resolved"
  * Comment save requires authorization (user with IACUC Analyst role) or Sys Admin(user with IACUC System Administrator role)
  * Comment can be Reply to Comment
  * Comment can be a new thread
  * Comment has timestamps and Edited flags
  * Comment has authors to determine ownership, right to edit and change status
  * Comment has replies 
    * Comment has type of either "base" or "reply" (I think reply is a type of comment)
  * or Reply is a Comment
    * Reply has everything Comment has, but does not have status or visibility
    * Reply has order number so that they display in correct order on the gui
  * Comment replies are not ordered by timestamp, after editing they keep their position within the thread
  * Submission cannot be sent to IACUC Office with Unaddressed Comments
  * Submission has Status: can be created, approved, or closed.
  * Given a Comment has Visibility set to "Visible to Everyone" and Status is set to "Unaddressed", when Comment changes Visibility to "Visible to only Analysts and Reviewers", then Status is set to "General"
  
Other notes:
* the commenter doesn't have a user role in play.  Commenters are assigned to a submission as either Researcher or Reviewer (or PI, Analyst, etc.)
