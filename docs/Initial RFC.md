# Initial RFC

This document demonstrates the design of the **vacationfill**, an app for managing vacations for any company.

**Stack:**
- **Kotlin** programming language
- **Spring Boot** framework
- **PostgreSQL** database
 

## Database Tables 

**Users:**

| Column                | Type          |
| --------------------- | ------------- |
| id                    | String        |
| name                  | String        |
| email                 | String        |
| vacationDaysCredit    | Number        |
| role                  | String        |

Regarding roles, we start as simple roles them we can extend to have roles and permissions.

**Vacations:**

| Column        | Type          |
| ------------- | ------------- |
| id            | String        |
| startDate     | Date          |
| endDate       | Date          |
| state         | String        | PENDING|APPROVED|REJECTED|CANCELED
| type          | String        | ANNUAL|SICK|CAUSAL
| requestedAt   | Date          |


## API 

### Auth
```
/signIn             [POST]
/signUp             [POST]
/signOut            [POST]
```

### Employees
```
/employees/new          [POST]
/employees/delete       [Delete]
```

### Vacations
```
/vacations/new                  [Post]
/vacations/:id/reject           [Post]
/vacations/:id/accept           [Post]
/vacations/:employeeId          [Get]
```

## Architecture

- API Layer 
- Service Layer 
- Data Access Layer (Dao)