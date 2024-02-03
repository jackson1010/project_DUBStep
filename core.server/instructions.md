
http://localhost:8080/api/auth/createAccount
{"username":"testuser","password":"test"}

http://localhost:8080/api/auth/signin
{"username":"testuser","password":"test"}

http://localhost:8080/api/auth/updateContact
{"userId":10000,"address":"testaddress","email":"test@testmail.com","mobileNumber":"98765432", "homeNumber":"65432109"}


http://localhost:8080/api/auth/createAccount{"username":"testuser","password":"test"}
http://localhost:8080/api/auth/signin
{"username":"testuser","password":"test"}

http://localhost:8080/api/auth/updateContact
{"userId":10000,"address":"testaddress","email":"test@testmail.com","mobileNumber":"98765432", "homeNumber":"65432109"}

http://localhost:8080/api/auth/insertProfile
{"userId":10000,
"userProfile":{"userId":10000,"firstName": "tester","lastName": "test","dateOfBirth": "2000-02-01","gender": "MALE","height": 1.55,"weight": 45.5,"bloodType":"O_NEGATIVE"},
"contactDetails":{"userId":10000,"address":"testaddress","email":"test@testmail.com","mobileNumber":"98765432", "homeNumber":"65432109"}
}
http://localhost:8080/api/auth/updateProfile{"userId":10000,"firstName": "tester","lastName": "test","dateOfBirth": "2000-02-01","gender": "MALE","height": 1.55,"weight": 45.5,"bloodType":"O_NEGATIVE"}
http://localhost:8080/api/gpt/{userId}"
{"query": "why is the sky blue","number":1}

http://localhost:8080/api/gpt/getAll/{userid}
http://localhost:8080/api/reports/getAll/{userid}


http://localhost:8080/api/auth/profile/{userid}
{"userProfile":{"userId":10000,"firstName": "tester","lastName": "test","dateOfBirth": "2000-02-01","gender": "MALE","height": 1.55,"weight": 45.5,"bloodType":"O_NEGATIVE"},"contactDetails":{"userId":10000,"address":"testaddress","email":"test@testmail.com","mobileNumber":"98765432", "homeNumber":"65432109"}
}