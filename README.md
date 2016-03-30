# OnlineAssignment

User can build and run project using command: gradlew clean build bootRun
This single command also include unit tests

To check result on UI
Search http://localhost:8080/findWord?word=  with the English word

For example, to check word "Paris"
Search http://localhost:8080/findWord?word=Paris

User can only search a string that contains [a-zA-Z]

The display on the UI is a string that tells how many number of requests for this word, and how many number of occurrence for this word in the files.




Edge Cases Defined:
1. Each word is case sensitive. Friend and friend are counted as two different words.
2. friend and friend's are both counted as "friend"; friends and friends' are both counted as "friends"
