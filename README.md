## Home task for Java Junior position

## Employee management system

#### This is a WEB application that presents the interface of the user management system.

##### The system supports the following role model:


| Role     | Available actions                  
| -------- |---------------------------       
| `USER`   | Authorization (Login)              
|          | Display a list of users (List)
|          | Display details of any user (View)
|          | Logout 
| `ADMIN`  | Authorization (Login)                     
|          | Display a list of users (List)                                       
|          | Display details of any user (View)                                       
|          | Creating a new user (New/Edit)        
|          | Editing an existing user (New/Edit)                                      
|          | Lock/Unlock a user (Lock/Unlock)                                      
|          | Logout                                      
|          |                                       

##### The main entity in the system is *UserAccount*.
| Field             | Description                  
| --------          |---------------------------      
| `Username`        | Latin characters only
|                   | Must be unique
|                   | Length must be 3-16 characters
| `Password`        | Only latin characters and numbers
|                   | At least one character
|                   | Length must be 3-16 characters
|                   | Should be stored in the database in hashed form
|                   | using bcrypt algorithm.
| `First name`      | Latin characters only
|                   | Length must be 3-16 characters
| `Last name`       | Latin characters only
|                   | Length must be 3-16 characters
| `Status`          | User state
|                   | ACTIVE or INACTIVE
|                   | On UI should be dropdown
| `Created at`      | User's date of creation.
|                   | Is set automatically
|                   | when saving to the database.

#### There are the following pages in the system:
| Page               | URL               | Description                   | Available actions (depending of the role)                  
| --------           |-------------      | -------------                 | -------------    
| `Login`            | */login*          | Authorization in the system   | Login to the system
|                    |                   |                               | If the user state is INACTIVE, then logging in is not possible;
| `List`             | */user*           | List of users                 | Filtration of the list by Username, Role and pagination
| `View`             | */user/{id}*      | Viewing details of the user   | Lock/Unlock: should change the ACTIVE/INACTIVE state.
| `New`              | */user/new*       | Creating a new user           | A message on the UI should be shown if validation errors are present.
| `Edit`             | */user/{id}/edit* | Editing the user              | A message on the UI should be shown if validation errors are present.



### Tech stack:
- Java 8
- Spring (Spring Boot, Spring MVC, Spring Data, Spring Security)
- PostgreSQL 
- Thymeleaf
- Maven


### You can check application on the following [URL](http://employeemanagementsystem-env.eba-tpexepmv.eu-central-1.elasticbeanstalk.com/login) with credentials :
#### `Username: Admin`

#### `Password: Admin1!`


