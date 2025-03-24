# Webserver for restaurant project Antons Skafferi
To run the webserver you will need the latest version of
- Docker
- Docker-compose
- Permissions to run docker without sudo/root/admin
Make sure they are accessable via $PATH. These should be automatically installed if you install docker desktop.

When cloning the project to IntelliJ you might need to create a run config. Go up to the right hand corner and select edit configurations ![image](https://github.com/user-attachments/assets/a00e10ad-73c0-4300-98a8-d5ce85987384)

Create a new config with the '+' button and select Spring Boot, give it a name and select java 17, select class "se.antons_skafferi.AntonsSkafferiSpringApplication" as main class then it should be up and running.

To initialize the database you will for now need to use these commands in the terminal, but a combined script will be made, make sure the docker container is up and running before running these, these should work on all operating systems 
```
docker exec -i antons-skafferi-spring-mysql-1 mysql -u myuser -psecret mydatabase < sql/01-drops.sql
docker exec -i antons-skafferi-spring-mysql-1 mysql -u myuser -psecret mydatabase < sql/02-tables.sql
docker exec -i antons-skafferi-spring-mysql-1 mysql -u myuser -psecret mydatabase < sql/03-inserts.sql
```
To run all in one script use this command in the terminal Mac/Linux
```
cat sql/01-drops.sql sql/02-tables.sql sql/03-inserts.sql | docker exec -i antons-skafferi-spring-mysql-1 mysql -u myuser -psecret mydatabase
```
Windows (untested might not work)
```
(Get-Content sql/01-drops.sql, sql/02-tables.sql, sql/03-inserts.sql -Raw) | docker exec -i antons-skafferi-spring-mysql-1 mysql -u myuser -psecret mydatabase
```
The server can then be found at localhost:8080
The menu can be accessed at localhost:8080/api/menu/all

### ÅÄÖ incorrecly displayed
In case the api endpoints are not showing ÅÄÖ correctly make sure to remove mysql_data before running the docker exec commands.
You might also need to run the following command to start from a clean slate.
```
docker system prune --volumes
```


## Building
To build the project to a docker image, first go to the maven tab in IntelliJ and select lifecycle and then package.
This should create a .jar file in the target folder.
Then run the following command in the terminal, you might need sudo/root/admin permissions.
```
docker build -t webbserver .
```
This will build the docker image with the .jar file in it.
To upload the image to dockerhub you will need to login to dockerhub in the terminal. 
Then run the following command, 
```
docker tag webbserver ndrs03/webbserver
docker push ndrs03/webbserver
```
This will upload the image to dockerhub. You will need collaborator permissions to the repository to push.

If any changes are made to the sql files or compose, update the files in GitHub.



# UML-Diagram
A bit messy but i think adequate for an overview. // Andreas
![image](https://github.com/user-attachments/assets/185ea796-074a-4492-8610-0d1910db9db7)

PlantUML code to recreate diagram:
```
@startuml
allowmixing
package Webserver{
class APIController {
  - Logger logger
  - DatabaseService databaseService

  ' #########################
  ' ### Person Endpoints ####
  ' #########################
  + getAllPersons(): List<Person>
  + getPersonByEmail(email: String): Person
  + getPersonByPhoneNumber(phoneNumber: String): Person
  + createPerson(person: Person): Person
  + deletePerson(personId: int): void

  ' #########################
  ' ### Item Endpoints ######
  ' #########################
  + getAllItems(): List<Items>
  + addItem(item: Items): Items
  + deleteItem(itemId: int): void

  ' #########################
  ' ### Lunch Endpoints #####
  ' #########################
  + getAllLunchItems(): List<Lunch>
  + getLunchItemsByDate(date: Date): List<Lunch>
  + getLunchItemsByWeekAndYear(week: int, year: int): List<Lunch>
  + addLunchItem(lunch: Lunch): Lunch
  + addItemsToLunch(lunchId: Integer, itemIds: List<Integer>): Lunch
  + deleteLunchItem(lunchId: int): void

  ' #########################
  ' ### Dinner Endpoints ####
  ' #########################
  + getAllDinnerItems(): List<Dinner>
  + getDinnerItemsByType(type: String): List<Dinner>
  + addDinnerItem(dinner: Dinner): Dinner
  + deleteDinnerItem(dinnerId: int): void

  ' #########################
  ' ### Booking Endpoints ###
  ' #########################
  + getBookings(type: String): List<Bokkings>
  + getBookingsByDate(date: Date): List<Bookings>
  + getBookingsByWeekAndYear(week: int, year: int): List<Bookings>
  + getConfirmedBookingsByTableIdAndDateTime(tableId: Integer, dateTime: String): ResponseEntity<Bookings>
  + getConfirmedBookingByPersonIdAndDate(personId: Integer, date: String): ResponseEntity<Bookings>
  + addBooking(booking: Bookings): Bookings
  + updateBookingStatus(id: Integer, status: Bookings.Status): Bookings
  + deleteBooking(bookingId: int): void

  ' #########################
  ' ### Event Endpoints #####
  ' #########################
  + getAllEvents(): List<Events>
  + getEventsByWeekAndYear(week: int, year: int): List<Events>
  + getEventsByDate(date: Date): List<Events>
  + addEvent(event: Events): Events
  + deleteEvent(eventId: int): void

  ' #########################
  ' ### Order Endpoints #####
  ' #########################
  + getAllOrders(): List<Orders>
  + getOrdersById(id: Integer): Orders
  + getOrdersByDate(date: Date): List<Orders>
  + getOrdersByStatus(status: Orders.Status): List<Orders>
  + createOrder(order: Orders): Orders
  + addMenuItemToOrder(orderId: int, dinnerId: Integer): Orders
  + addDrinkToOrder(orderId: int, drinkId: Integer): Orders
  + addStatusToOrder(orderId: int, status: Orders.Status): Orders
  + updateOrderNote(orderId: int, note: String): Orders
  + deleteOrder(orderId: int): void
  + deleteMenuItemFromOrder(orderId: int, foodOrderId: int): void
  + deleteDrinkFromOrder(orderId: int, drinkId: int): Orders

  ' #########################
  ' ### Tab Endpoints #######
  ' #########################
  + getAllTabs(): List<Tab>
  + getTabById(id: Integer): Tab
  + getTabByDate(date: String): List<Tab>
  + getTabByStatus(status: Tab.Status): List<Tab>
  + getTabByTable(table: Integer): List<Tab>
  + createTab(tab: Tab): Tab
  + updateTabStatus(tabId: int, status: Tab.Status): Tab
  + deleteTab(tabId: int): void

  ' #########################
  ' ### Drink Endpoints #####
  ' #########################
  + getAllDrinks(): List<Drinks>
  + getDrinkById(id: int): Drinks
  + getDrinksByType(type: String): List<Drinks>
  + addDrink(drink: Drinks): Drinks
  + updateDrinkPrice(drinkId: int, price: Integer): Drinks
  + deleteDrink(drinkId: int): void

  ' #########################
  ' ### Table Endpoints #####
  ' #########################
  + getAllTables(): List<Tables>
  + getTableById(id: int): Tables
  + getTablesByNumberOfSeats(room_for_people: int): List<Tables>
  + addTable(table: Tables): Tables
  + deleteTable(tableId: int): void

  ' #########################
  ' ### Employee Endpoints ##
  ' #########################
  + getAllEmployees(): List<Employee>
  + getEmployeeById(id: int): Employee
  + getEmployeesByName(name: String): List<Employee>
  + getEmployeePasswordByEmail(email: String): String
  + getHierarchyByEmployeeId(id: int): List<Role>
  + createEmployee(employee: Employee): ResponseEntity<Employee>
  + changeEmployeePassword(employeeId: int, password: String): Employee
  + changeEmployeeRole(employeeId: int, roleId: int): Employee
  + deleteEmployee(employeeId: int): void

  ' #########################
  ' ### Role Endpoints ######
  ' #########################
  + getAllRoles(): List<Role>
  + getRoleById(id: int): Role
  + createRole(role: Role): Role
  + changeRoleHierarchy(roleId: int, hierarchyLevel: Integer): Role
  + deleteRole(roleId: int): void
}


class DatabaseService {
  Middleman between Repositories and API endpoints, can only display static data. 
  To compress the diagram the methods were omitted.
}

Interface Repositories{
  Handles the requests to the SQL database, every entity in the database requires their own repository.
  This is a concatenation of all repositories.
}

Database db{
hide circle
hide empty members

' ##########################
' ### People Entities ####
' ##########################
class Role {
  + role_id [PK]
  + name
  + description
  + hierarchy_level
}

class Person {
  + person_id [PK]
  + first_name
  + last_name
  + date_of_birth
  + email [UQ]
  + phone_number [UQ]
}

class Employee {
  + employee_id [PK]
  + hiring_date
  + salary
  + password
  + person_id [FK]
  + role_id [FK]
}

' ##########################
' ### Menu Entities #######
' ##########################
class Items {
  + item_id [PK]
  + name
  + description
}

class Dinner {
  + dinner_id [PK]
  + name
  + description
  + time_to_make
  + type
  + price
}

class Drinks {
  + drink_id [PK]
  + name
  + type
  + description
  + price
}

class Lunch {
  + lunch_id [PK]
  + price
  + date
}

class LunchItems {
  + id [PK]
  + lunch_id [FK]
  + item_id [FK]
}

' ##########################
' ### Operations Entities #
' ##########################
class Tables {
  + table_number [PK]
  + room_for_people
}

class Bookings {
  + booking_id [PK]
  + date
  + start_time
  + end_time
  + table_number [FK]
  + status
  + person_id [FK]
}

class Events {
  + event_id [PK]
  + name
  + description
  + date
  + start_time
  + end_time
}

class Shift {
  + schedule_id [PK]
  + shift_date
  + start_time
  + end_time
}

class WorksShift {
  + schedule_id [FK,PK]
  + employee_id [FK,PK]
}

class Tab {
  + tab_id [PK]
  + opened_at
  + closed_at
  + status
  + last_updated_at
  + employee_id [FK]
  + table_number [FK]
}

class Orders {
  + order_id [PK]
  + date
  + status
  + employee_id [FK]
  + table_number [FK]
  + tab_id [FK]
  + note
}

class FoodOrder {
  + id [PK]
  + dinner_id [FK]
  + order_id [FK]
  + note
}

class DrinkOrder {
  + id [PK]
  + drink_id [FK]
  + order_id [FK]
  + quantity
}

legend right
  Database Schema with:
  - 17 Entities
  - All Attributes
  - PK = Primary Key
  - FK = Foreign Key
  - UQ = Unique
endlegend



}


APIController <--> DatabaseService
DatabaseService <--> Repositories
Repositories <--> db


note top of APIController
  APIController handles all REST endpoints
  for the restaurant management system.
  Organized by entity categories:
  - Persons
  - Items
  - Lunch/Dinner menus
  - Bookings
  - Events
  - Orders
  - Tabs
  - Drinks
  - Tables
  - Employees
  - Roles
end note

}

actor "User" as User


rectangle "Mobile App" {
  component "Android Frontend" as android
  component "Android Backend" as androidback
}

rectangle "Website" {
  component "Web Frontend" as web
  component "Web Backend" as webback
}

User --> android : Uses
User --> web : Uses
android <-> androidback
web <-> webback
androidback <..> APIController : Async HTTP request via Retrofit
webback <..> APIController : Async HTTP request

@enduml
```

