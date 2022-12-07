# Initial RFC

This document demonstrates the design of the **vacationfill**, an app for managing vacations for any company.

**Stack:**
- **Kotlin** programming language
- **Spring Boot** framework
- **PostgreSQL** database
 

## Database 

### Vacations

| Column      | Type   |
|-------------|--------|
| id          | String |
| startDate   | Date   |
| endDate     | Date   |
| state       | String | PENDING|APPROVED|REJECTED|CANCELED
| type        | String | ANNUAL|SICK|CAUSAL
| requestedAt | Date   |

---

## APIs

### Vacations

```
/vacations                      [Post]
/vacations/:id/reject           [Post]
/vacations/:id/accept           [Post]
/vacations/:id/cancel           [Post]
/vacations                      [Get]
/vacations/:vacationId          [Get]
```

## Architecture

- API Layer 
- Service Layer 
- Data Access Layer (Dao)