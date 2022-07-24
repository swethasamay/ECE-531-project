
* Commands for running the script: 
chmod +x scriptName.sh
./script.sh



* Database information: 
Database created is named 'consoles'. It is a 'console' named table that contains id and name as columns. 


* AWS EC2 instancecPublic IP is http://54.183.3.255:8080

* Curl commands

To get list of all the ojects: curl http://54.183.3.255:8080/ 

To get object based on ID: curl http://54.183.3.255:8080/id 

To post a record on to the database: curl -X POST http://54.183.3.255:8080/ id NAME

To put: curl -X PUT http://54.183.3.255:8080/ id NAME

To delete an ID record:curl -X DELETE http://54.183.3.255:8080/id



