
## Selenium project

The project involves writing automated tests for the backend and frontend of an eBook rental application.
## Sources

[Backand](https://ta-ebookrental-prod-kodilla-xmhvai.mo1.mogenius.io)

[Frontend](https://ta-bookrental-fe.onrender.com)

[Entire backend](https://github.com/kodilla/ta-ebooklibrary-backend)

[Controllers](https://github.com/kodilla/ta-ebooklibrary-backend/tree/master/src/main/java/com/kodilla/ebooklibrary/controller)

## Documentation
[Test cases](https://1drv.ms/x/s!AuoBz13j3BP2hLAwr1U95giP8iqKwQ?e=kQ6oGc)

[Backend test reports](https://github.com/mowmimalpa/Test-Project-Selenium/blob/main/TA_backand_test.postman_test_run.json) 

[Endpoint test scenarios](https://github.com/mowmimalpa/Test-Project-Selenium/blob/main/TA_backand.postman_collection.json)



## Specification

The application being created is intended to perform the following functionality:

Screen 1 - user login/registration

All other screens are available after logging in.

Screen 2 - list of titles

displayed list: author, title, year of publication
ability to add, edit, delete, and access the list of copies

Screen 3 - list of copies

displayed list: purchase date, status (in stock, rented)
ability to add copies, edit, delete, and access the list of rentals

Screen 4 - list of rentals

displayed list: customer's first and last name, rental date, expiration date
ability to enter and delete a rental
ability to edit

Screen 5 - rental

form with fields: customer's first and last name (1 field), rental date, expiration date
when entering as a new rental, the first two fields are available for editing (the second is set by default to sysdate()+3, the first to sysdate())
when entering as an edit, all fields are available for editing.
each user works independently on their own list of eBooks - this is implemented by the backend.

