[![Build Status](https://travis-ci.com/lampovachan/Exhibition-Spring.svg?branch=master)](https://travis-ci.com/lampovachan/Exhibition-Spring)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/25819972efac4941a1eeb6a4609176ac)](https://www.codacy.com/gh/lampovachan/Exhibition-Spring/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=lampovachan/Exhibition-Spring&amp;utm_campaign=Badge_Grade)

Design and implement a REST API using Hibernate and Spring Boot without frontend.

The task is:

Build a voting system for deciding where to go for tourists.

2 types of users: admin and regular users.
Admin can input a museum and it's exhibitions (2-5 items usually, just an exhibition name and price).
Exhibitions can be changed (admins do the updates).
Users can vote on which museum they want to go.
Only one vote counted per user.
If user votes again the same day:
If it is before 11:00 we asume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed.
Each museum provides new exhibitions periodically.
