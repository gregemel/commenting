
# purpose:
turning requirements into unit tests, then a working spring-boot microservice

### requirements
  * Reviewer should be able to create Comment on EformQuestion
  * Reviewer should be able to set Visibility on Comment to ReviewersOnly, Everyone.
  * Reviewer can save Comment on Question on Submission
  * Reviewer can edit own Comments
  * Reviewers should only be able to change a visibility of own comment
  * Reviewers should not be able to change the visibility of another reviewer's comment (unless Analyst or Admin)
  * Reviewers should be able to change Status of own Comment, but cannot change the status of other's comments (unless Analyst or Admin)
  * Reviewers should be able to change the status of a comment with visibility set to "Visible to Everyone"
  * Reviewers can add Comment to Questions until the Submission is closed, approved.
  * Reviewer: when Reviewer saves edited Comment, the date/timestamp will be marked Edited
  * Reviewer should be able to see the Name of the Reviewer on a Comment
  * Comment which is visible to everyone can have multiple statuses, default to "General"
  * Comment status can also be set to "Unaddressed / Addressed and Resolved"
  * Comment save requires authorization (user with IACUC Analyst role) or Sys Admin(user with IACUC System Administrator role)
  * Comment can be Reply to Comment
  * Comment can be a new thread
  * Comments have timestamps and Edited flags
  * Comments have authors to determine ownership, right to edit and change status
  * Comment replies are not ordered by timestamp, after editing they keep their position within the thread
  * =incomplete= when Submission sent to Researchers contains Comments with Status of Unaddressed, research team needs to address them all before sending submission back to IACUC office. (can't model this)
    * perhaps: Submission cannot be sent to IACUC Office with Unaddressed Comments???
  * Submission has Status: can be created, approved, or closed.
  * Researcher does not see Reviewer's name on. Instead of reviewer's full name, show "Reviewer"
  * Given a Comment has Visibility set to "Visible to Everyone" and Status is set to "Unaddressed", when Comment changes Visibility to "Visible to only Analysts and Reviewers", then Status is set to "General"
  * Researcher should be able to reply to Comment on Submission with Status "unaddressed"
  * Researcher cannot change Comment Visibility
  * Researcher can edit own Comments
  
