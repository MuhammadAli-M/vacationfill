# Introduce Auth

## Database Tables

### Users

| Column             | Type   |
|--------------------|--------|
| id                 | String |
| name               | String |
| email              | String |
| vacationDaysCredit | Number |
| role               | String |

Regarding roles, we start as simple roles them we can extend to have roles and permissions.

---

## APIs

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