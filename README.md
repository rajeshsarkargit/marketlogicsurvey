# marketlogicsurvey
Market Logic Survey Microservice

For swagger : http://localhost:8081/swagger-ui.html

providing post api to 
  add question : http://http://localhost:8081/survey/questions
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
  
  delete particular question : http://localhost:8081/survey/questions/1
  
  submit answers of that question : http://localhost:8081/survey/submitQuestions
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
    
    To get all Questions : http://localhost:8081/survey/questions
    To get particular question : http://localhost:8081/survey/questions/1
    
    Added junit tests as well
