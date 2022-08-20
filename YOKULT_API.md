FORMAT: 1A
HOST: http://localhost:8080/yokult

# Yokult

Yokult provides different features for demostrating our side project features. There are varies functions like Member/Admin Login, Doctor Appointment, Customer checkout, Nurse Schedule, etc.

# Group Members

Resources related to member in the API.

## Member [/api/0.02/member/{id}]

+ Parameters

    + id: TGA001 (string) - An unique identifier of the member.

### Get Member infomation [GET]

+   Request

    + Headers

            Authorization: Bearer {token}

+   Response 200 (application/json)

          {
            "memID": "TGA001",
            "memPassword": "123",
            "memEmail": "tga001@gmail.com",
            "memName": "古龍",
            "memBirth": "2022+04+11",
            "memCellPhone": "0910123456",
            "memAddress": "110台北市信義區市府路45號",
            "memStatus": "APPROVED"
          }

## Member [/api/0.02/member/register]

### Create a New Member [POST]

You may create a new member using this action. It takes a JSON object
containing a member ID and member information in the form.

+   Request (application/json)

              {
                "memID": "TGA9998",
                "memPassword": "123",
                "memEmail": "aaa@gmail.com",
                "memName": "吳大猷",
                "memCellPhone": "0910123456",
                "memBirth": "1907+09+29"
              }

+   Response 201 (application/json)

    +   Body

            {
              "msg" : success
            }

## Member [/api/0.02/member/login]

### Member Login [POST]

Member may login the website using this action. It takes a JSON object
containing a member ID and member password in the form. When member login successes, server will send a JWT token with 30 minutes permission.

+   Request (application/json)

        {
          "memID": "TGA9998",
          "memPassword": "123",
        }

+   Response 200 (application/json)

    +   Body

            {
                "msg": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUR0EwMDEiLCJleHAiOjE2NjEwMDc3MTV9.y8GeKOsmxtERduySqsjzQ3qCLWVHzuEa4lz2dT429ZkN_LeX5ShlsN1WOZhe2kp7Fp7MtNlDzwigDV6l5wqTxQ"
            }
