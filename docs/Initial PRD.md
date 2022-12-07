# Initial PRD

## Context

I want to build vacations system for company employees. 
This system shall allow employees to: 
- request vacations
- list their vacations with their approval state.

And shall allow managers to:
- accept employees' vacations and reject them.

## User stories 

1. As an employee, I shall submit a vacation request.
1. As an employee, I shall cancel vacation request.
1. As an employee, I shall list my vacations with their approval states.
1. As a manager, I shall add new employees.
1. As a manager, I shall remove existing employees.
1. As a manager, I shall list some employee's vacations with their approval states.
1. As a manager, I shall accept a requested vacation.
1. As a manager, I shall reject a requested vacation.

### As an employee, I shall submit a vacation request.

- The vacation start date shall be greater than the current date

### As an employee, I shall cancel vacation request.

- The current date shall be greater than or equal the vacation start date
- Only the employee can cancel his/her vacations.

### As an employee, I shall list my vacations with their approval states.

- Only the employee and his/her manager can list the employee's vacations.

