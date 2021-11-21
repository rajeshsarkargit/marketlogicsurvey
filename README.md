# marketlogicsurvey
Market Logic Survey Microservice, Sample Survey Questions with 4 option answers, we are adding Questions with 4 respective asnwers, And provided api to Delete any Questions By Id and exposed Api to Get All Questions and its answers, Also exposed api to Get particular Questions and its answers. Exposed an Api to submit List of Questions with 1 correct answer for each question.

For swagger : http://localhost:8081/swagger-ui.html

H2 Database is used : http://localhost:8081/h2-console/
Driver Class: org.h2.Driver,
JDBC URL:jdbc:h2:mem:surveydb,
User Name:sa,
Password:

Provided 5 rest apis:

POST question : http://http://localhost:8081/survey/questions
sample request: {
	"question":
		{
			"question":"Question 1?"
		},
	"answers":[
		{
			"answer":"answer 1"
		},
		{
			"answer":"answer 2"
		}
	]
}

DELETE particular question : http://localhost:8081/survey/questions/1

POST submit answers of that question : http://localhost:8081/survey/submitQuestions
sample request :
[
{
"question":
  {
    "questionId":1,
    "description":"How was your experience?"
  },
"answer":{
	    "answerId": 1
	}
}
]

GET all Questions : http://localhost:8081/survey/questions
GET particular question : http://localhost:8081/survey/questions/1

Added junit tests as well
