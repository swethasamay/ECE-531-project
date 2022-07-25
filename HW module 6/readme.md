
* Commands for running the script: 
chmod +x scriptName.sh

./script.sh



* Database information: 
Database created is named 'consoles'. It is a 'console' named table that contains age and name as columns. 


* AWS EC2 instancecPublic IP is http://52.53.212.163:8080

* Curl commands

To get list of all the ojects: curl http://52.53.212.163:8080/ 

To get object based on age: curl http://52.53.212.163:8080/age 

To post a record on to the database: curl -X POST http://52.53.212.163:8080/ -d NAME

To put: curl -X PUT http://52.53.212.163:8080/ -d NAME

To delete an age in the record:curl -X DELETE http://52.53.212.163:8080/age



