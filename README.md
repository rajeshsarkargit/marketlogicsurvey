# marketlogicsurvey
Market Logic Survey Microservice

providing post api to 
  add question localhost:8081/survey/questions
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
  delete particular question localhost:8081/survey/questions/1
  submit answers of that question localhost:8081/survey/submitQuestions
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
    To get all Questions localhost:8081/survey/questions
    To get particular question localhost:8081/survey/questions/1
